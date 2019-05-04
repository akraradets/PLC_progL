/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import src.logger.Logger;

/**
 *
 * @author akrarads
 */
public class BoolPrim extends PrimObj {
    private Logger logger = new Logger("BoolPrim");
    private Boolean data;
    
    public BoolPrim() {
        setType("bool");
        data = null;
    }
    
    public BoolPrim(Boolean i) {
        setType("bool");
        setData(i);
    }

    @Override
    public Boolean getData() {
        return data;
    }
    
    public void setNeg () {
        this.data = !data;
    }
    
    @Override
    public void setData(Object o) {
        data = cast(o);
        logger.debug("set Data: " + data.toString());
    }
    
    private Boolean cast(Object o){
        if(o.getClass() == this.getClass()){
            BoolPrim i = (BoolPrim) o;
            return i.getData();
        }
        try {
            Boolean i = (Boolean) o;
            return i;
        } catch (Exception e) {
            logger.error("Wrong Type where expected <Boolean> --> " + "The given Type is <" + o.getClass() +">");
            throw new Error("Wrong Type");
        }
    }
}
