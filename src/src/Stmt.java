/*;
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.util.List;
import jdk.nashorn.internal.objects.NativeArray;
import src.logger.Logger;

/**
 *
 * @author akrarads
 */
public class Stmt {

    private Logger logger;
    private String command;
    private Object arg1;
    private Object arg2;
    private Object arg3;

    private Stmt(String c) {
        this.command = c;
        logger = new Logger("Stmt " + command);
    }

    private Stmt(String c, Object arg1) {
        this.command = c;
        this.arg1 = arg1;
        logger = new Logger("Stmt " + command + "," + arg1.toString());
    }

    private Stmt(String c, Object arg1, Object arg2) {
        this.command = c;
        this.arg1 = arg1;
        this.arg2 = arg2;
        logger = new Logger("Stmt " + command + "," + arg1.toString() + "," + arg2.toString());
    }

    private Stmt(String c, Object arg1, Object arg2, Object arg3) {
        this.command = c;
        this.arg1 = arg1;
        this.arg2 = arg2;
        this.arg3 = arg3;
        logger = new Logger("Stmt " + command + "," + arg1.toString() + "," + arg2.toString() + "," + arg3.toString());
    }

    public static Stmt empty() {
        return new Stmt("");
    }

    public static Stmt declare(String name, Object o) {
        return new Stmt("declare", (Object) name, o);
    }

    public static void declare(String name, PrimObj p, Object o) {
        p.setData(o);
        declare(name, p);
    }

    public static Stmt ifthen(Boolean bool, List<Stmt> block){
        System.out.println("ifthen" + bool.toString());
        if(bool){ 
            System.out.println("Bool is true" + bool.toString());
            Stmt.run(block);
        }
        return Stmt.empty();
    }
    
    public static void run(Stmt s) {
        s.run();
    }

    public static void run(List<Stmt> sl) {
        for (int i = 0; i < sl.size(); i++) {
            Stmt s = sl.get(i);
            s.run();
        }
    }

    public void run() {
        Command c = Command.getInstance();
        logger.debug("run");
        switch (this.command) {
            case "declare":
                c.declare((String) arg1, (PrimObj) arg2);
                break;
                
            case "":
                logger.debug("empty command");
                break;

            default:
                logger.error("Unsupport command <" + this.command);
                break;
        }

    }

//    public static void main(String[] args) {
//        Memory m = Memory.getInstance();
//        m.init();
//        Stmt one = Stmt.declare("x", PrimObj_Factory.get("int"));
//        one.run();
//        m.dumpMemory();
//    }
}
