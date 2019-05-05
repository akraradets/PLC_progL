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
public class StatementNode extends GenericNode {

    private Memory m = Memory.getInstance();
    private Logger logger = new Logger("StatementNode");
    private String value;
    private ExpressionNode e;
    private String type;
    private ConditionNode c;
    private StatementNode s;
    private String libname;
    private ConditionNode argv;
    
    // From Decalre
    private StatementNode(String command, String type, String name, ExpressionNode e) {
        this.command = command;
        this.value = name;
        this.e = e;
        this.type = type;
    }
    
    private StatementNode(String command, String type, String name) {
        this.command = command;
        this.value = name;
        this.type = type;
    }
    
    public static StatementNode allocate(String type, String name) {
        StatementNode d = new StatementNode("allocate", type , name);
        return d;
    }
    
    public static StatementNode declare(String type, String name, ExpressionNode e) {
        StatementNode d = new StatementNode("declare", type , name, e);
        e.addChild(d);
        return d;
    }
    
    // New
    private StatementNode(String command) {
        this.command = command;
    }

    private StatementNode(String command, String name, ExpressionNode e) {
        this.command = command;
        this.value = name;
        this.e = e;
    }

    public static StatementNode empty(DeclareNode d) {
        StatementNode s = new StatementNode("empty");
        s.addChild(d.getRoot());
        return s;
    }

    public static StatementNode assign(String name, ExpressionNode e) {
        return new StatementNode("assign", name, e);
    }

    public static StatementNode ifthen(ConditionNode c, StatementNode s){
        StatementNode ifthen = new StatementNode("ifthen");
        ifthen.c = c;
        System.out.println(c.toString());
        ifthen.addChild("true", s);
        return ifthen;
    }
    
    public static StatementNode library(String libname, ConditionNode c){
        StatementNode library = new StatementNode("library");
        library.libname = libname;
        library.argv = c;
        c.addChild(library);
        return library;
    }
    
    public Object run() {
        Environment table = m.getEnvironment();
        switch (this.command) {
            // from declare
            case "declare":
                PrimObj p = PrimObj_Factory.get(this.type);
                p.setData(e.value);
                table.put(this.value, p);
                break;
            case "allocate":
                table.put(this.value, PrimObj_Factory.get(this.type));
                break;
            // new
            case "empty":
                logger.debug(this.command);
                break;
            case "ifthen":
                logger.debug("command:"+this.command);
                if(ifthen(this.c)){
                    logger.debug("command:"+this.command + ":: true");
                    children.get("true").run();
                }
                break;
            case "library":
                logger.debug("command:"+this.command+" Libname:"+this.libname);
                
                if(this.libname.equals("print")){
                    logger.debug(this.argv.toString());
                    UI.output = UI.output + this.argv.value.getData() +"\n";
                }
                break;
                
            default:
                logger.error("command:"+this.command+" is not match");
                break;
        }
        if (!children.isEmpty()) {
            children.get("default").run();
        }
        // return to parent
        return null;
    }
    
    private Boolean ifthen(ConditionNode c){
        c.run();
        if(c.value instanceof BoolPrim){
            return (Boolean) c.value.getData();
        }
        logger.error("ConditionNode did not load with BoolPrim " + c.value.getClass());
        throw new Error("ConditionNode did not load with BoolPrim");
    }
}
