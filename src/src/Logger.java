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
public class Logger {
    private String className;

    public Logger(String className) {
        this.className = className;
    }
    
    public void debug(String msg){
        System.out.println(className + ": " + msg);
    }
    
    public void error(String msg){
        System.err.println(className + ": " + msg);
    }
}
