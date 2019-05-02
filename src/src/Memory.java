/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.util.*;

/**
 *
 * @author akrarads
 */
public class Memory {
    // static variable single_instance of type Singleton 
    private static volatile Memory instance = null; 
    
    private Logger logger = new Logger("Memory");

    // Memory Stack
    private Stack<Table> stack = new Stack<Table>();

    private Memory() {
        Table table = new Table(stack.size());
        stack.push(table);
    }

    public static Memory getInstance(){
        if(instance == null){
            synchronized(Memory.class){
                if(instance == null){
                    instance = new Memory();
                }
            }
        }
        return instance;
    }
    
    public void declare(String dataType, String name, Object data) {
        // Create
        Table table = stack.peek();
        table.put(dataType, name, data);
    }

    public DataObj retrive(String name) {
        // Read
        Table table = stack.peek();
        return table.get(name);
    }

    public void update(String name, Object data){
        // Update
        Table table = stack.peek();
        table.update(name, data);
    }
    
    public void dumpMemory() {
        Table table = stack.peek();
        logger.debug(stack.toString());
        logger.debug(table.toString());
    }

//    public static void main(String[] args) {
//        try {
//            Memory m = new Memory();
//            m.declare("int", "a", new Integer(1));
//            m.declare("int", "b", new Integer(1));
//            m.dumpMemory();
//            DataObj a = m.retrive("a");
//            DataObj b = m.retrive("b");
//            Integer c = ((Integer) a.getData());
//        } catch (Exception e) {
//            int i = 1000;
//            while (i > 0) {
//                i--;
//            }
//            throw e;
//        }
//    }
}

class Table {

    private Logger logger;
    private Hashtable<String, DataObj> table;
    private Integer level;

    // A set of support dataType 
    private static final HashSet<String> dataType_list = new HashSet<>(Arrays.asList(new String[]{
        "int",
        "bool",
        "string"
    }));

    public Table(Integer level) {
        this.level = level;
        logger = new Logger("Table_" + level.toString());
        table = new Hashtable<String, DataObj>();
        logger.debug("New table");
    }

    public boolean containsKey(String name) {
        if (table.containsKey(name)) {
            return true;
        }
        return false;
    }

    public void put(String dataType, String name, Object data) {
        logger.debug("put => " + dataType + " " + name + " = " + data.toString());
        // Check if dataType is valid
        if (dataType_list.contains(dataType) == false) {
            logger.error("dataType <" + dataType + "> is not support!!");
            logger.error("Support dataType are " + dataType_list.toString());
            throw new Error("Not support DataType");
        }

        // Check if name is already declare
        if (table.containsKey(name)) {
            logger.error("Variable name <" + name + "> is already declared!!");
            throw new Error("Duplicate Variable Name");
        }
        table.put(name, new DataObj(dataType, data));
    }

    public DataObj get(String name) {
        if (table.containsKey(name) == false) {
            logger.error("Variable name <" + name + "> is not exist!!");
            throw new Error("Variable not found");
        }
        return table.get(name);
    }

    public void update(String name, Object data){
        DataObj d = get(name);
        d.setData(data);
    }
    
    public String toString() {
        return table.toString();
    }
}

class DataObj {

    private String dataType;
    private Object data;

    public DataObj(String dataType, Object data) {
        this.dataType = dataType;
        this.data = data;
    }

    public Object getData() {
        return data;
    }

    
    public void setData(Object data) {
        this.data = data;
    }

    public String getDataType() {
        return dataType;
    }
    
}
