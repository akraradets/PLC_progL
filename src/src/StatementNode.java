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
    private StatementNode funcBody;
    private StatementNode funcParam;

    // From Decalre
    private StatementNode(String command, String type, String name) {
        this.command = command;
        this.value = name;
        this.type = type;
    }

    public static StatementNode allocate(String type, String name) {
        StatementNode d = new StatementNode("allocate", type, name);
        return d;
    }

    public static StatementNode declare_var(String type, String name, ConditionNode c) {
        StatementNode d = new StatementNode("declare_var", type, name);
        d.argv = c;
        c.addChild(d);
        return d;
    }

    public static StatementNode declare_func(String funcName, StatementNode declareParam, StatementNode body) {
        StatementNode func = new StatementNode("declare_func");
        // When we call function
        // 
        func.value = funcName;
        func.funcBody = body;
        func.funcParam = declareParam;
        return func;
    }
    
    public static StatementNode assign(String name, ConditionNode c) {
        StatementNode d = new StatementNode("assign");
        d.value = name;
        d.argv = c;
        c.addChild(d);
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

    public static StatementNode empty() {
        return  new StatementNode("empty");
    }

    public static StatementNode ifthen(ConditionNode c, StatementNode s) {
        StatementNode ifthen = new StatementNode("ifthen");
        ifthen.c = c;
        ifthen.addChild("true", s.getRoot());
        return ifthen;
    }

    public static StatementNode ifthenelse(ConditionNode c, StatementNode s1, StatementNode s2) {
        StatementNode ifthen = new StatementNode("ifthenelse");
        ifthen.c = c;
        ifthen.addChild("true", s1.getRoot());
        ifthen.addChild("false", s2.getRoot());
        return ifthen;
    }

    public static StatementNode whileloop(ConditionNode c, StatementNode s) {
        StatementNode ifthen = new StatementNode("whileloop");
        ifthen.c = c;
        ifthen.addChild("true", s.getRoot());
        return ifthen;
    }

    public static StatementNode library(String libname, ConditionNode c) {
        StatementNode library = new StatementNode("library");
        library.libname = libname;
        library.argv = c;
        c.addChild(library);
        return library;
    }

    public static StatementNode functionCall(String funcName, ConditionNode a){
        StatementNode func = new StatementNode("functionCall");
        func.value = funcName;
        return func;
    }
    
    public Object run() {
        Environment table = m.getEnvironment();
        System.out.println("RUNNNNNNNNNNN command:" + this.command);
        switch (this.command) {
            // from declare
            case "declare_var":
                PrimObj p = PrimObj_Factory.get(this.type);
                p.setData(argv.value);
                table.put(this.value, p);
                logger.debug("command:" + this.command + " name:" + this.value + " value:" + p.toString());
                break;
            case "declare_func":
                table.put_func(this.value, FunctionNode.declare(this.value,this.funcBody));
                logger.debug("command:" + this.command + " name:" + this.value);
                break;
            case "allocate":
                table.put(this.value, PrimObj_Factory.get(this.type));
                logger.debug("command:" + this.command + " name:" + this.value);
                break;
            // new
            case "empty":
                logger.debug(this.command);
                break;
            case "assign":
                table.update(this.value, this.argv.value);
                logger.debug("command:" + this.command + " name:" + this.value + " value:" + this.argv.toString());
                break;
            case "ifthen":
                logger.debug("command:" + this.command);
                if (eval(this.c)) {
                    logger.debug("command:" + this.command + ":: true");
                    children.get("true").run();
                }
                break;
            case "ifthenelse":
                logger.debug("command:" + this.command);
                if (eval(this.c)) {
                    logger.debug("command:" + this.command + ":: true");
                    children.get("true").run();
                } else {
                    logger.debug("command:" + this.command + ":: false");
                    children.get("false").run();
                }
                break;
            case "whileloop":
                logger.debug("command:" + this.command);
                while (eval(this.c)) {
                    logger.debug("command:" + this.command + ":: true");
                    children.get("true").run();
                }
                break;
            case "library":
                logger.debug("command:" + this.command + " LibName:" + this.libname);
                if (this.libname.equals("print")) {
                    logger.debug(this.argv.toString());
                    UI.output = UI.output + this.argv.value.getData() + "\n";
                }
                break;
            case "functionCall":
                logger.debug("command:" + this.command + " FuncName:" + this.value);
                functionCall(this.value);
                break;
            default:
                logger.error("command:" + this.command + " is not match");
                break;
        }
        if (children.containsKey("default")) {
            children.get("default").run();
        }
        // return to parent
        return null;
    }

    public String toString() {
        if (this.command.equals("ifthen")) {
            String a = "ifthen [true]:" + this.children.get("true").toString() + "[default]";
            return a;
        } else {
            String a = this.command;
            return a;
        }
    }

    private Boolean eval(ConditionNode c) {
        c.run();
        if (c.value instanceof BoolPrim) {
            return (Boolean) c.value.getData();
        }
        logger.error("ConditionNode did not load with BoolPrim " + c.value.getClass());
        throw new Error("ConditionNode did not load with BoolPrim");
    }
    
    private void functionCall(String funcName){
        FunctionNode f = m.findFunction(funcName);
        f.run();
        
    }
}
