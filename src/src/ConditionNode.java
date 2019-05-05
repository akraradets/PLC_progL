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
        c.debug();
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
    
    
    public Object run() {
        logger.debug(this.command + " " + this.value);
        switch (command) {
            case "readObject":
                break;
            case "readVariable":
                this.value = m.findObject(this.varname);
                break;
            case "flipArgument":
                this.value = flipArgument(this.e1.value);
                break;
            case "evalExpression":
                this.value = this.e1.value;
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
            BoolPrim neg_data = (BoolPrim) o;
            neg_data.setNeg();
            return neg_data;
        }
        logger.error("Only [BoolPrim] is support for neg_argument");
        throw new Error("Only [BoolPrim] is support for neg_argument");
    }

}
