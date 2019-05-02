/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.memory;

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
        if(type == "int"){
            return new IntPrim();
        }
//        else if(type == "bool"){
//            
//        }
//        else if(type == "string"){
//            
//        }
        else{
            logger.error("primitiveType <" + type + "> is not support!!");
            logger.error("Support primitiveType are " + type.toString());
            throw new Error("Not support primitiveType");
        }
    }

}
