/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.memory;

import src.PrimObj;
import java.util.*;
import src.logger.Logger;

/**
 *
 * @author akrarads
 */
public class Memory {

    // static variable single_instance of type Singleton 
    private static volatile Memory instance = null;

    private Logger logger = new Logger("Memory");

    // Memory Stack
    private Stack<Environment> stack = new Stack<Environment>();

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

    public void newEnvironment() {
        Environment table = new Environment(stack.size());
        stack.push(table);
    }

    public void init() {
        stack.clear();
        newEnvironment();
    }

    public Environment getEnvironment() {
        return stack.peek();
    }

    public void destroyEnvironment() {
        stack.pop();
    }

    public PrimObj findObject(String name) {
        for (int i = stack.size() - 1; i >= 0; i--) {
            Environment t = stack.get(i);
            try {
                return t.get(name);
            } catch (Error e) {
                logger.debug("name: " + name + " not found in here.");
            }

        }
        logger.error("Variable name:<" + name + "> is not exist.");
        throw new Error("Variable not found");
    }

    public void dumpMemory() {
        String mem = "Stack: \n";
        for (int i = 0; i < stack.size(); i++) {
            Environment t = stack.get(i);
            mem += "  " + t.tableName + ":\n";
            mem += "    " + t.toString() + "\n";
        }
        logger.debug(mem);
    }

//
//    public static void main(String[] args) {
//        Memory m = Memory.getInstance();
//        m.init();
//        
//        m.dumpMemory();
//        Environment e = m.getEnvironment();
//      
//        PrimObj a = PrimObj_Factory.get("int");
//        a.setData(10);
//        e.put("a", a);
//        m.dumpMemory();
//        // call some function
//        m.newEnvironment();
//        m.newEnvironment();
//        Environment e3 = m.getEnvironment();
//        PrimObj aa = PrimObj_Factory.get("int");
//        aa.setData(40);
//        e3.put("a", aa);
//        m.newEnvironment();
//        Environment e2 = m.getEnvironment();
//        PrimObj b = PrimObj_Factory.get("int");
//        b.setData(20);
//        e2.put("b", b);
//        m.dumpMemory();
//        
//        PrimObj f = m.findObject("a");
//        f.setData(60);
//        
//        m.dumpMemory();
//    }
}
