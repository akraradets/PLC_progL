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
public class DeclareNode extends GenericNode {

    private Memory m = Memory.getInstance();
    private String value;
    private ExpressionNode e;

    private DeclareNode(String command, String name, ExpressionNode e) {
        this.command = command;
        this.value = name;
        this.e = e;
    }

    public static DeclareNode declare(String name, ExpressionNode e) {
        DeclareNode d = new DeclareNode("declare", name, e);
        e.addChild(d);
        return d;
    }

    public Object run() {
        System.out.println(this.command + " " + this.value);
        Environment table = m.getEnvironment();
        switch (this.command) {
            case "declare":
                table.put(this.value, PrimObj_Factory.get(this.e.value));
                break;

        }
        System.out.println(this.value);
        if (this.children.isEmpty() == false) {
            children.get("default").run();
        }
        return null;
    }
    public String toString(){
        String a = "";
        a = this.command + ":" + this.value;

        
        return a;
    }
}
