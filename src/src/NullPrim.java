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
public class NullPrim extends PrimObj {

    private String type = "null";
    private Object data = new Object();

    public NullPrim() {

    }

    @Override
    public Object getData() {
        return this.data;
    }

    @Override
    public void setData(Object o) {

    }

}
