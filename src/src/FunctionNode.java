/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author akrarads
 */
public class FunctionNode extends GenericNode {

    private Memory m = Memory.getInstance();
    private StatementNode stm;
    private StatementNode param;
    public ConditionNode argv;
    private String name;
    public PrimObj output;
    private List<String> paramNameList = new ArrayList<String>();

    public FunctionNode(String name) {
        this.name = name;
    }

    public static FunctionNode declare(String name, StatementNode stm, StatementNode param) {
        FunctionNode func = new FunctionNode(null);
        func.stm = stm;
        func.param = param;
        return func;
    }

    public Object run() {
        m.newEnvironment();
        // delcare parameters
        param.getRoot().run();
        // If argument is not null
        StatementNode paramList = (StatementNode) param.getRoot();
        GenericNode firstArgu = argv.getRoot();
        while (paramList.value != null) {
            paramNameList.add(paramList.value);
            System.out.println("Some param JA -------------------------------");
            if (paramList.children.containsKey("default")) {
                paramList = (StatementNode) paramList.children.get("default");
            } else {
                break;
            }
        }
        for (int k = 0; k < paramNameList.size(); k++) {
            System.out.println(paramNameList.get(k));
        }
        System.out.println("----------------------- ARGV LIST");
        int count = 0;
        while (true) {
            if (firstArgu instanceof ConditionNode) {
                ConditionNode a = (ConditionNode) firstArgu;
                System.out.println(a.value);
//                if (argv.value instanceof NullPrim == false) {
                    StatementNode assign = StatementNode.assign_value(paramNameList.get(count), a.value);
//                    assign.argv = a;
                    System.out.println("BEFORE::::::::" + assign.toString());
                    assign.run();
                    System.out.println("AFTER::::::::" + assign.toString());
                    count++;
//                }
            }
            if (firstArgu.children.containsKey("default")) {
                firstArgu = firstArgu.children.get("default");
            } else {
                break;
            }
        }

//        argv.getRoot().debug();
//        if (argv.value instanceof NullPrim == false) {
//            StatementNode assign = StatementNode.assign(param.value, argv);
//            assign.argv = argv;
//            System.out.println("BEFORE::::::::" + assign.toString());
//            assign.run();
//            System.out.println("AFTER::::::::" + assign.toString());
//        }
        System.out.println("-------------------- New Environment ----------------------");
        stm.getRoot().run();
        m.dumpMemory();
        System.out.println("-------------------- Destroy Environment ----------------------");
        this.output = m.findObject("return");
        m.destroyEnvironment();
//        System.out.println("My OUTPUTTTTTTTTTTTTTTTTTT  " + this.output.toString());
        return null;
    }

    public String toString() {
        String a = "Function:" + name + " param:" + param.type + "->" + param.value + " body:" + stm.toString();
        return a;
    }
}
