/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.util.Arrays;
import java.util.HashSet;

/**
 *
 * @author akrarads
 */
public class PrimObj {

    private Logger logger = new Logger("PrimObj");
    // A set of support dataType 
    private static final HashSet<String> primitive_list = new HashSet<>(Arrays.asList(new String[]{
        "int",
        "bool",
        "string"
    }));

    public String type;

    public PrimObj(String type) {
        // Check if dataType is valid
        if (primitive_list.contains(type) == false) {
            logger.error("primitiveType <" + type + "> is not support!!");
            logger.error("Support primitiveType are " + type.toString());
            throw new Error("Not support primitiveType");
        }
        this.type = type;
    }
    
    public String getType(){
        return this.type;
    }
    
//    public static void main(String[] args) {
//        PrimObj p = new PrimObj("sdf");
//    }
}
