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
public class StrPrim extends PrimObj{
    private Logger logger = new Logger("StrPrim");
    private String data;
    
    public StrPrim() {
        setType("string");
        data = null;
    }
    
    public StrPrim(String i) {
        setType("string");
        setData(i);
    }

    public StrPrim(Object o1, Object o2){
        data = cast(o1) + cast(o2);
    }
    
    @Override
    public String getData() {
        return data;
    }

    @Override
    public void setData(Object o) {
        data = cast(o);
        logger.debug("set Data: " + data.toString());
    }

    private String cast(Object o){
        if(o.getClass() == this.getClass()){
            StrPrim i = (StrPrim) o;
            return i.getData();
        }
        try {
            String i = (String) o;
//            // String will come in this "(.[^"])*"
//            // Remove first and last character which is <">
            i = i.substring(1, i.length() -1 );
            return i;
        } catch (Exception e) {
            logger.error("Wrong Type where expected <String> --> " + "The given Type is <" + o.getClass() +">");
            throw new Error("Wrong Type where expected <String> --> " + "The given Type is <" + o.getClass() +">");
        }
    }
    
//    public static void main(String[] args) {
//        String s = "\"asdasd\"";
//        s = s.substring( 1 , s.length()-1 );
//        System.out.println(s);
//    }
}
