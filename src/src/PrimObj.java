/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.util.Arrays;
import java.util.HashSet;
import src.logger.Logger;

/**
 *
 * @author akrarads
 */
public abstract class PrimObj{

    private String type;
    private Object data;

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }

    public abstract Object getData();
    public abstract void setData(Object o);
    
    @Override
    public String toString() {
        String d = "null";
        if (getData() != null) {
            d = getData().toString();
        }
        return "type:" + this.type + " value:" + d;
    }
}
