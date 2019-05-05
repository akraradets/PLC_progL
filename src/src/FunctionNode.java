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
public class FunctionNode extends GenericNode{
    private Memory m = Memory.getInstance();
    private StatementNode stm;
    private StatementNode param;
    public ConditionNode argv;
    private String name;
    
    public FunctionNode(String name){
        this.name = name;
    }
    
    public static FunctionNode declare(String name,StatementNode stm, StatementNode param){
        FunctionNode func = new FunctionNode(null);
        func.stm = stm;
        func.param = param;
        return func;
    }
    
    public Object run(){
        m.newEnvironment();
        // delcare parameters
        param.getRoot().run();
        System.out.println("-------------------- New Environment ----------------------");
        stm.getRoot().run();
        m.dumpMemory();
        System.out.println("-------------------- Destroy Environment ----------------------");
        m.destroyEnvironment();
        return null;
    }
    
    public String toString(){
        String a = "Function:"+name+" param:"+param.type+"->"+param.value+" body:"+stm.toString();
        return a;
    }
}
