/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.util.Hashtable;

/**
 *
 * @author akrarads
 */
public class GenericNode {
    protected Hashtable<String,GenericNode> children = new Hashtable<String, GenericNode>();
    protected GenericNode parent;
    protected String command;
    
    public void setParent(GenericNode parent){
        this.parent = parent;
    }
    
    public void addChild(GenericNode child){
        child.setParent(this);
        children.put("default", child);
        
    }
    
    public void addChild(String type, GenericNode child) {
        child.setParent(this);
        children.put(type, child);
    }

    public GenericNode getRoot(){
        if(this.parent == null)
            return this;
        return this.parent.getRoot();
    }
    
    public Object run() {
        // if not the last child
        if(!children.isEmpty()){
            children.get("default").run();            
        }
        // return to parent
        return null;
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
    
    public String toString(){
        String a = this.command;
        return a;
    }
}
