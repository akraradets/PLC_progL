/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import src.PrimObj;
import java.util.Arrays;
import java.util.HashSet;
import src.logger.Logger;

/**
 *
 * @author akrarads
 */
public class PrimObj_Factory {

    private static Logger logger = new Logger("PrimObj_Factory");
    // A set of support dataType 

    public static final HashSet<String> primitive_list = new HashSet<>(Arrays.asList(new String[]{
        "int",
        "bool",
        "string"
    }));

    public static PrimObj get(String type) {
        if(type.equals("int")){
            return new IntPrim();
        }
        else if(type.equals("bool")){
            return new BoolPrim();
        }
        else if(type.equals("string")){
            return new StrPrim();
        }
        else if(type.equals("null")){
            return new NullPrim();
        }
        else{
            logger.error("primitiveType <" + type + "> is not support!!. \nSupport primitiveType are " + type.toString());
            throw new Error("primitiveType <" + type + "> is not support!!.\nSupport primitiveType are " + type.toString());
        }
    }
    
    public static PrimObj get(PrimObj p) {
        return p;
    }
    
    public static PrimObj get(Object o){
        if(o instanceof Integer){
            return new IntPrim((int) o);
        }
        else if (o instanceof String){
            return new StrPrim(o.toString());
        }
        else if (o instanceof Boolean){
            return new BoolPrim((boolean) o);
        }
        else{
            logger.error("Unsupport Object of type <"+o.getClass()+">");
            throw new Error("Unsupport Object of type <"+o.getClass()+">");
        }
    }
//    public static void main(String[] args) {
//        Integer i = 10;
//        get(i);
//        String ii = "10";
//        
//        Object a = ii;
//        if(a instanceof String) System.out.println("yes");
//        get(a);
//        Boolean iii = true;
//        get(iii);
//    }
}
