/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

/**
 *
 * @author akrarads
 */
public class ExpressionNode extends GenericNode {

    private Integer value;
    private String command;
    private ExpressionNode e1, e2;

    private ExpressionNode(Integer i) {
        this.command = "readObject";
        this.value = i;
    }

    private ExpressionNode(String command) {
        this.value = null;
        this.command = command;
    }

    public static ExpressionNode readObject(Object o) {
        if (o instanceof Integer) {
            System.out.println(o.toString());
            return new ExpressionNode((Integer) o);
        }
        throw new Error("Expect object to be integer");
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
        System.out.println(this.command + " " + this.value);
        switch (command) {
            case "readObject":
                break;
            case "add":
                this.value = this.e1.value + this.e2.value;
                break;
            case "minus":
                this.value = this.e1.value - this.e2.value;
                break;
            case "multi":
                this.value = this.e1.value * this.e2.value;
                break;
            case "divide":
                this.value = this.e1.value / this.e2.value;
                break;
            case "flipSign":
                this.value = this.e1.value * -1;
                
        }
        System.out.println(this.value);
        if (this.children.isEmpty() == false) {
            children.get("default").run();
        }
        return null;
    }
    
    public String toString(){
        String a = this.command + ":" + this.value;
        return a;
    }
    
    public void debug(){
        GenericNode a = this.getRoot();
        System.out.print(a.toString() + "->");
        while(a.children.isEmpty() == false){
            a = a.children.get("default");
            System.out.print(a.toString() + "->");
        }
        System.out.println("");
    }
}
