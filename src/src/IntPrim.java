/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import src.PrimObj;
import src.logger.Logger;

/**
 *
 * @author akrarads
 */
public class IntPrim extends PrimObj {

    private Logger logger = new Logger("IntPrim");
    private Integer data;
    
    public IntPrim() {
        setType("int");
        data = null;
    }
    
    public IntPrim(Integer i) {
        setType("int");
        setData(i);
    }
    
    @Override
    public Integer getData() {
        return data;
    }

    @Override
    public void setData(Object o) {
        data = cast(o);
        logger.debug("set Data: " + o.toString());
    }
    
    private Integer cast(Object o){
        if(o.getClass() == this.getClass()){
            IntPrim i = (IntPrim) o;
            return i.getData();
        }
        try {
            Integer i = (Integer) o;
            return i;
        } catch (Exception e) {
            logger.error("Wrong Type where expected <Integer> --> " + "The given Type is <" + o.getClass() +">");
            throw new Error("Wrong Type");
        }
    }
}
