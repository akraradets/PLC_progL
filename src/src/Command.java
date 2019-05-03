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
    
    public void assign(String name, Object o){
        Environment e = m.getEnvironment();
        e.update(name, o);
    }
    
    public void operation(String name, Object o1, String oper, Object o2){
        logger.debug("Oper: " + oper);
        switch (oper) {
            case "+":
                
                break;
        
            default:
                logger.error("Operation <" + oper + "> not support");
                break;
        }
    }

    public  void condition () {
        
    }
}
