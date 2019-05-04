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
public class ExpressionNode extends GenericNode {
    private Memory m = Memory.getInstance();
    private Logger logger = new Logger("ExpressionNode");
    public PrimObj value;
    private String command;
    private ExpressionNode e1, e2;
    private String varname;
    
    private ExpressionNode(Object o) {
        this.command = "readObject";
        this.value = PrimObj_Factory.get(o);
    }

    private ExpressionNode(String command) {
        this.value = null;
        this.command = command;
    }

    public static ExpressionNode readObject(Object o) {
        return new ExpressionNode(o);
    }

    public static ExpressionNode readVariable(String v){
        ExpressionNode e = new ExpressionNode("readVariable");
        e.varname = v;
        return e;
    }
    
    public static ExpressionNode flipSign(ExpressionNode e_pre) {
        ExpressionNode e = new ExpressionNode("flipSign");
        e.e1 = e_pre;
        e_pre.addChild(e);

        return e;
    }

    public static ExpressionNode add(ExpressionNode e1, ExpressionNode e2) {
        e1.addChild(e2.getRoot());
        ExpressionNode e = new ExpressionNode("add");
        e2.addChild(e);
        e.e1 = e1;
        e.e2 = e2;
        return e;
    }

    public static ExpressionNode minus(ExpressionNode e1, ExpressionNode e2) {
        e1.addChild(e2.getRoot());
        ExpressionNode e = new ExpressionNode("minus");
        e2.addChild(e);
        e.e1 = e1;
        e.e2 = e2;
        return e;
    }

    public static ExpressionNode multi(ExpressionNode e1, ExpressionNode e2) {
        e1.addChild(e2.getRoot());
        ExpressionNode e = new ExpressionNode("multi");
        e2.addChild(e);
        e.e1 = e1;
        e.e2 = e2;
        return e;
    }

    public static ExpressionNode divide(ExpressionNode e1, ExpressionNode e2) {
        e1.addChild(e2.getRoot());
        ExpressionNode e = new ExpressionNode("divide");
        e2.addChild(e);
        e.e1 = e1;
        e.e2 = e2;
        return e;
    }

    public Object run() {
        logger.debug(this.command + " " + this.value);
        switch (command) {
            case "readObject":
                break;
            case "readVariable":
                this.value = m.findObject(this.varname);
                break;
            case "add":
                this.value = add(this.e1.value, this.e2.value);
                break;
            case "minus":
                this.value = minus(this.e1.value, this.e2.value);
                break;
            case "multi":
                this.value = multi(this.e1.value, this.e2.value);
                break;
            case "divide":
                this.value = divide(this.e1.value, this.e2.value);
                break;
            case "flipSign":
                this.value = flipSign(this.e1.value);
                // this.value = this.e1.value * -1;
                break;

        }
        // logger.debug(this.value);
        if (this.children.isEmpty() == false) {
            children.get("default").run();
        }
        return null;
    }
    public String toString(){
        String a = "";
        if(this.command == "readVariable"){
            a = this.command + ":" + this.varname;
        }
        else{
            a = this.command + ":" + this.value;
        }
        
        return a;
    }
    
    
    /* From command section */
    private PrimObj add(PrimObj p1, PrimObj p2) {
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
    private PrimObj minus(PrimObj p1, PrimObj p2) {
        // only IntPrim is support
        if(p1 instanceof IntPrim && p2 instanceof IntPrim){
            return new IntPrim((Integer)p1.getData() - (Integer)p2.getData());
        }
        logger.error("Only [IntPrim] is support for subtraction");
        throw new Error("Only [IntPrim] is support for subtraction");
    }

    private PrimObj divide(PrimObj p1, PrimObj p2) {
        // only IntPrim is support
        if(p1 instanceof IntPrim && p2 instanceof IntPrim){
            return new IntPrim((Integer)p1.getData() / (Integer)p2.getData());
        }
        logger.error("Only [IntPrim] is support for divide");
        throw new Error("Only [IntPrim] is support for divide");
    }

    private PrimObj multi(PrimObj p1, PrimObj p2) {
        // only IntPrim is support
        if(p1 instanceof IntPrim && p2 instanceof IntPrim){
            return new IntPrim((Integer)p1.getData() * (Integer)p2.getData());
        }
        logger.error("Only [IntPrim] is support for multiplication");
        throw new Error("Only [IntPrim] is support for multiplication");
    }
    
    private PrimObj flipSign(PrimObj p){
         // only IntPrim is support
        if(p instanceof IntPrim){
            return new IntPrim((Integer)p.getData() * -1);
        }
        logger.error("Only [IntPrim] is support for multiplication");
        throw new Error("Only [IntPrim] is support for multiplication");       
    }
}
