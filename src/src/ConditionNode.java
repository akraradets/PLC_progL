/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import jflex.Main;
import src.logger.Logger;

/**
 *
 * @author akrarads
 */
public class ConditionNode extends GenericNode {

    private Memory m = Memory.getInstance();
    private Logger logger = new Logger("ConditionNode");
    public PrimObj value;
    private String command;
    private ConditionNode c1, c2;
    private ExpressionNode e1, e2;
    private String varname;

    private ConditionNode(Object o) {
        this.command = "readObject";
        this.value = PrimObj_Factory.get(o);
    }

    private ConditionNode(String command) {
        this.value = null;
        this.command = command;
    }

    public static ConditionNode readObject(Object o) {
        return new ConditionNode(o);
    }

    public static ConditionNode readVariable(String v) {
        ConditionNode e = new ConditionNode("readVariable");
        e.varname = v;
        return e;
    }

    public static ConditionNode flipArgument(ConditionNode c_pre) {
        ConditionNode c = new ConditionNode("flipArgument");
        c.c1 = c_pre;
        c_pre.addChild(c);
//        c.debug();
        return c;
    }
    
    public static ConditionNode evalExpression(ExpressionNode e){
        ConditionNode c = new ConditionNode("evalExpression");
        c.e1 = e;
        e.addChild(c);
        return c;
    }    
    
    public static ConditionNode cond_and(ConditionNode c1,ConditionNode c2){
        c1.addChild(c2.getRoot());
        ConditionNode c = new ConditionNode("cond_and");
        c2.addChild(c);
        c.c1 = c1;
        c.c2 = c2;
        return c;
    }
    
    public static ConditionNode cond_or(ConditionNode c1,ConditionNode c2){
//        System.out.println("This is cond_or f");
        c1.addChild(c2.getRoot());
        ConditionNode c = new ConditionNode("cond_or");
        c2.addChild(c);
        c.c1 = c1;
        c.c2 = c2;
        return c;
    }
    
    public Object run() {
        switch (command) {
            case "readObject":
                logger.debug("command:"+this.command+" value:"+this.value);
                break;
            case "readVariable":
                this.value = m.findObject(this.varname);
                logger.debug("command:"+this.command+" value:"+this.value);
                break;
            case "flipArgument":
                this.value = flipArgument(this.c1.value);
                logger.debug("command:"+this.command+" value:"+this.value);
                break;
            case "cond_and":
                this.value = cond_and(this.c1.value,this.c2.value);
                logger.debug("command:"+this.command+" value:"+this.value);
                break;
            case "cond_or":
//                System.out.println("This is cond_or");
                this.value = cond_or(this.c1.value,this.c2.value);
//                System.out.println("This is c1"+this.c1.value);
//                System.out.println("This is c2"+this.c2.value);
                logger.debug("command:"+this.command+" value:"+this.value);
                break;
            case "evalExpression":
                this.value = this.e1.value;
                logger.debug("command:"+this.command+" value:"+this.value);
                break;
            default:
                logger.error("command:"+this.command+" is not match");
                break;
        }
        // logger.debug(this.value);
        if (this.children.isEmpty() == false) {
            children.get("default").run();
        }
        return null;
    }

    public String toString() {
        String a = "";
        if (this.command == "readVariable") {
            a = this.command + ":" + this.varname;
        } else {
            a = this.command + ":" + this.value;
        }
        return a;
    }
    
    private PrimObj flipArgument (PrimObj o) {
        if (o instanceof BoolPrim) {
            Boolean b = (Boolean) o.getData();
            return PrimObj_Factory.get(!b);
        }
        logger.error("Only [BoolPrim] is support for neg_argument");
        throw new Error("Only [BoolPrim] is support for neg_argument");
    }
    
    public PrimObj cond_and (PrimObj o1, PrimObj o2) {
        // only BoolPrim is support
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
        logger.error("Only [BoolPrim] is support for cond_and "+o1.getClass());
        throw new Error("Only [BoolPrim] is support for cond_and");
    }
    
    public PrimObj cond_or (PrimObj o1, PrimObj o2) {
        // only BoolPrim is support
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

}
