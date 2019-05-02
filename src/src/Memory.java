/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.util.*;
import jdk.nashorn.internal.parser.JSONParser;

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
    }

    public static Memory getInstance() {
        if (instance == null) {
            synchronized (Memory.class) {
                if (instance == null) {
                    instance = new Memory();
                }
            }
        }
        return instance;
    }

    public void declare(PrimObj p, String name, Object data) {
        // Create
        this.declare(p, name);
        Table table = stack.peek();
        table.update(name, data);
    }

    public void declare(PrimObj p, String name) {
        // Create
        Table table = stack.peek();
        table.put(p, name);
    }

    public DataObj retrive(String name) {
        // Read
        Table table = stack.peek();
        return table.get(name);
    }

    public void update(String name, Object data) {
        // Update
        Table table = stack.peek();
        table.update(name, data);
    }

    public void dumpMemory() {
        String mem = "Stack: \n";
        for(int i = 0; i < stack.size(); i++){
            Table t = stack.get(i);
            mem += "  " + t.tableName + ":\n";
            mem += "    " + t.toString() + "\n";
            
        }
        logger.debug(mem);
    }

    public void clean(){
        stack.clear();
        Table table = new Table(stack.size());
        stack.push(table);
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

    public String tableName;
    private Logger logger;
    private Hashtable<String, DataObj> table;
    private Integer level;

    public Table(Integer level) {
        this.level = level;
        tableName = "Table_" + level.toString();
        logger = new Logger(tableName);
        table = new Hashtable<String, DataObj>();
        logger.debug("New table:"+tableName);
    }

    public boolean containsKey(String name) {
        if (table.containsKey(name)) {
            return true;
        }
        return false;
    }

    public void put(PrimObj p, String name, Object data) {
        this.allocateMemory(p, name);
        this.update(name, data);
    }

    public void put(PrimObj p, String name) {
        this.allocateMemory(p, name);
    }
    
    private void allocateMemory(PrimObj p, String name){
        String dataType = p.getType();
        logger.debug("allocateMemory => " + dataType + " " + name);
        // Check if name is already declare
        if (table.containsKey(name)) {
            logger.error("Variable name <" + name + "> is already declared!!");
            throw new Error("Duplicate Variable Name");
        }
        table.put(name, new DataObj(p.getType(),null));
    }
    
    public DataObj get(String name) {
        if (table.containsKey(name) == false) {
            logger.error("Variable name <" + name + "> is not exist!!");
            throw new Error("Variable not found");
        }
        return table.get(name);
    }

    public void update(String name, Object data) {
        DataObj d = get(name);
        d.setData(data);
    }

    public String toString() {
        return table.toString();
    }
}

/* DataObj CLASS */
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

    public String toString(){
        String d = "null";
        if(data != null) d = data.toString();
        return "type:" + this.dataType + " value:" + d;
    }
}
