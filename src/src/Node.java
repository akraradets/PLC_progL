/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

/**
 *
 * @author akrarads
 */
public abstract class Node {

    private Node parent;
    private Hashtable<String, Node> children = new Hashtable<String, Node>();
    private String data;

    public Node(String data) {
        this.parent = null;
        this.data = data;
    }

    public Node(String data, Node parent) {
        this.data = data;
        this.parent = parent;
        this.parent.addChild("default", this);
    }

    public void setParent(Node parent){
        this.parent = parent;
    }
    
    public void addChild(String type, Node child) {
        children.put("default", child);
    }

    public void run() {
        System.out.println(data);
        if(!children.isEmpty()){
            if(true){
                children.get("true").run();
            }
            else{
                children.get("false").run();
            }
            children.get("default").run();
            
        }
        System.out.println(data);
        return;
    }

//    public static void main(String[] args) {
//        Node n_root = new Node("hello");
//        System.out.println("asdsad");
//        Node n_1 = new Node("world", n_root);
//        n_root.run();
//
//    }
}