/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import src.PrimObj;
import java.util.Hashtable;
import src.logger.Logger;

/**
 *
 * @author akrarads
 */
public class Environment {

    public String tableName;
    private Logger logger;
    private Hashtable<String, PrimObj> table;
    private Hashtable<String, FunctionNode> table_function;
    private Integer level;

    public Environment(Integer level) {
        this.level = level;
        tableName = "Table_" + level.toString();
        logger = new Logger(tableName);
        table = new Hashtable<String, PrimObj>();
        table.put("return", PrimObj_Factory.get("null"));
        table_function = new Hashtable<String, FunctionNode>();
        logger.debug("New table:" + tableName);
    }

    public void put(String name, PrimObj p) {
        String dataType = p.getType();
        String data = "null";
        if(p.getData() != null) data = p.getData().toString();
        logger.debug("put => " + dataType + " " + name + " data:" + data);
        // Check if name is already declare
        if (table.containsKey(name)) {
            logger.error("Variable name <" + name + "> is already declared!!");
            throw new Error("Variable name <" + name + "> is already declared!!");
        }
        table.put(name, p);
    }
    
    public void put_func(String name, FunctionNode func){
        if (table_function.containsKey(name)){      
            logger.error("Function name <" + name + "> is already declared!!");
            throw new Error("Function name <" + name + "> is already declared!!");
        }
        table_function.put(name, func);
    }

    public PrimObj get(String name) {
        if (table.containsKey(name) == false) {
            logger.error("Variable name <" + name + "> is not exist!!");
            throw new Error("Variable name <" + name + "> is not exist!!");
        }
        return table.get(name);
    }

    public FunctionNode get_func(String name) {
        if (table_function.containsKey(name) == false) {
            logger.error("Function name <" + name + "> is not exist!!");
            throw new Error("Function name <" + name + "> is not exist!!");
        }
        return table_function.get(name);
    }
    
    public void update(String name, Object data) {
        Memory m = Memory.getInstance();
        PrimObj p = m.findObject(name);
        if(p instanceof NullPrim){
            p = PrimObj_Factory.get((PrimObj)data);
        }
        else{
            p.setData(data);
        }
        table.put(name, p);
    }

    @Override
    public String toString() {
        return table.toString() + "\n    " + table_function.toString();
    }
    
//    public static void main(String[] args) {
//        Environment t = new Environment(0);
//        PrimObj a = PrimObj_Factory.get("int");
//        a.setData(10);
//        t.put("a", a);
//        
//        PrimObj b = PrimObj_Factory.get("int");
//        b.setData(100);
//        t.put("b", b);
//        
//        PrimObj temp = PrimObj_Factory.get("int");
//        temp.plus(a,b);
//        t.put("c", temp);
//        System.out.println(t.toString());
//    }
}
