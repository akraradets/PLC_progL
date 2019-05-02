/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.memory;

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
    private Integer level;

    public Environment(Integer level) {
        this.level = level;
        tableName = "Table_" + level.toString();
        logger = new Logger(tableName);
        table = new Hashtable<String, PrimObj>();
        logger.debug("New table:" + tableName);
    }

    public void put(String name, PrimObj p) {
        String dataType = p.getType();
        logger.debug("put => " + dataType + " " + name);
        // Check if name is already declare
        if (table.containsKey(name)) {
            logger.error("Variable name <" + name + "> is already declared!!");
            throw new Error("Duplicate Variable Name");
        }
        table.put(name, p);
    }

    public PrimObj get(String name) {
        if (table.containsKey(name) == false) {
            logger.error("Variable name <" + name + "> is not exist!!");
            throw new Error("Variable not found");
        }
        return table.get(name);
    }

    public void update(String name, Object data) {
        PrimObj p = get(name);
        p.setData(data);
        table.put(name, p);
    }

    @Override
    public String toString() {
        return table.toString();
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
