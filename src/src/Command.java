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

    public PrimObj load(String name) {
        Environment e = m.getEnvironment();
        return e.get(name);
    }

    public PrimObj neg_argument (PrimObj o) {
        if (o instanceof BoolPrim) {
            //Boolean data_o = (Boolean)o.getData();
            BoolPrim neg_data = (BoolPrim) o;
            neg_data.setNeg();
            return neg_data;
        }
        logger.error("Only [BoolPrim] is support for neg_argument");
        throw new Error("Only [BoolPrim] is support for neg_argument");
    }
    
    public PrimObj cond_and (PrimObj o1, PrimObj o2) {
        
        if (o1 instanceof BoolPrim) {
            if (o2 instanceof BoolPrim) {
                if ( (Boolean) o1.getData() && (Boolean) o2.getData() ) {
                    return PrimObj_Factory.get(true);
                }
                return PrimObj_Factory.get(false);
            }
            logger.error("Only [BoolPrim] is support for cond_and(o2 is not BoolPrim)");
            throw new Error("Only [BoolPrim] is support for cond_and(o2 is not BoolPrim)");
        }
        logger.error("Only [BoolPrim] is support for cond_and");
        throw new Error("Only [BoolPrim] is support for cond_and");
    }
    
    public PrimObj cond_or (PrimObj o1, PrimObj o2) {
        
        if (o1 instanceof BoolPrim) {
            if (o2 instanceof BoolPrim) {
                if ( (Boolean) o1.getData() || (Boolean) o2.getData() ) {
                    return PrimObj_Factory.get(true);
                }
                return PrimObj_Factory.get(false);
            }
            logger.error("Only [BoolPrim] is support for cond_or(o2 is not BoolPrim)");
            throw new Error("Only [BoolPrim] is support for cond_or(o2 is not BoolPrim)");
        }
        logger.error("Only [BoolPrim] is support for cond_or");
        throw new Error("Only [BoolPrim] is support for cond_or");
    }
    
    public PrimObj plus(PrimObj p1, PrimObj p2) {
        // only IntPrim is support
        if(p1 instanceof IntPrim && p2 instanceof IntPrim){
            return new IntPrim((Integer)p1.getData() + (Integer)p2.getData());
        }
        else if(p1 instanceof StrPrim && p2 instanceof StrPrim){
            return new StrPrim(p1,p2);
        }
        logger.error("Only [IntPrim,IntPrim] or [StrPrim,StrPrim] is support for addition");
        throw new Error("Only [IntPrim,IntPrim] or [StrPrim,StrPrim] is support for addition");
    }

    public PrimObj minus(PrimObj p1, PrimObj p2) {
        // only IntPrim is support
        if(p1 instanceof IntPrim && p2 instanceof IntPrim){
            return new IntPrim((Integer)p1.getData() - (Integer)p2.getData());
        }
        logger.error("Only [IntPrim] is support for subtraction");
        throw new Error("Only [IntPrim] is support for subtraction");
    }

    public PrimObj divide(PrimObj p1, PrimObj p2) {
        // only IntPrim is support
        if(p1 instanceof IntPrim && p2 instanceof IntPrim){
            return new IntPrim((Integer)p1.getData() / (Integer)p2.getData());
        }
        logger.error("Only [IntPrim] is support for divide");
        throw new Error("Only [IntPrim] is support for divide");
    }

    public PrimObj multiply(PrimObj p1, PrimObj p2) {
        // only IntPrim is support
        if(p1 instanceof IntPrim && p2 instanceof IntPrim){
            return new IntPrim((Integer)p1.getData() * (Integer)p2.getData());
        }
        logger.error("Only [IntPrim] is support for multiplication");
        throw new Error("Only [IntPrim] is support for multiplication");
    }
    
    public PrimObj neg_expression(PrimObj p){
         // only IntPrim is support
        if(p instanceof IntPrim){
            return new IntPrim((Integer)p.getData() * -1);
        }
        logger.error("Only [IntPrim] is support for multiplication");
        throw new Error("Only [IntPrim] is support for multiplication");       
    }

//    public static void main(String[] args) {
//        PrimObj a = PrimObj_Factory.get(new Boolean(true));
//        Command c = Command.getInstance();
//        c.neg_argument(a);
//        System.out.println(a.toString());
//        
//    }

}
