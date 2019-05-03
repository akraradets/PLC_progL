/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import src.logger.Logger;

/**
 *
 * @author akrarads
 */
public class Command {

    // static variable single_instance of type Singleton 
    private static volatile Command instance = null;
    private Logger logger = new Logger("Command");

    private Memory m = Memory.getInstance();

    private void Command() {
    }

    public static Command getInstance() {
        if (instance == null) {
            synchronized (Memory.class) {
                if (instance == null) {
                    instance = new Command();
                }
            }
        }
        return instance;
    }

    public void declare(String name, PrimObj p) {
        Environment e = m.getEnvironment();
        e.put(name, p);
    }

    public void declare(String name, PrimObj p, Object o) {
        p.setData(o);
        declare(name, p);
    }

    public void assign(String name, Object o) {
        Environment e = m.getEnvironment();
        e.update(name, o);
    }


    public  void condition () {        
    }

    public void variable () {
        
    }
    
    public PrimObj load(String name) {
        Environment e = m.getEnvironment();
        return e.get(name);
    }

    public PrimObj plus(PrimObj p1, PrimObj p2) {
        return new IntPrim();
    }

    public PrimObj minus(PrimObj p1, PrimObj p2) {
        return new IntPrim();
    }

    public PrimObj divide(PrimObj p1, PrimObj p2) {
        return new IntPrim();
    }

    public PrimObj multiply(PrimObj p1, PrimObj p2) {
        // only IntPrim is support
        if(p1 instanceof IntPrim && p2 instanceof IntPrim){
            return new IntPrim((Integer)p1.getData() * (Integer)p2.getData());
        }
        logger.error("Only [IntPrim] is support for multiplication");
        throw new Error("Only [IntPrim] is support for multiplication");
    }

}
