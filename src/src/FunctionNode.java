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
    private StatementNode stm;
    private String name;
    
    public FunctionNode(String name){
        this.name = name;
    }
    
    public static FunctionNode declare(String name,StatementNode stm){
        FunctionNode func = new FunctionNode(null);
        func.stm = stm;
        return func;
    }
    
    public Object run(){
        stm.getRoot().run();
        return null;
    }
    
    public String toString(){
        return stm.toString();
    }
}
