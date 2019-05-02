

/* --------------------------Usercode Section------------------------ */
package src;
import java_cup.runtime.*;

%%

/* -----------------Options and Declarations Section----------------- */

%class Lexer

%line
%column

%cup

%{
    /* java_cup.runtime.Symbol with no value */
    private Symbol symbol(int type) {
        return new Symbol(type, yyline, yycolumn);
    }

    /* java_cup.runtime.Symbol with a value */
    private Symbol symbol(int type, Object value) {
        return new Symbol(type, yyline, yycolumn, value);
    }

    private void debug(String type){
        if(config.debug){
            if(type == "SEPARATOR")
                System.out.println("<"+type+">");
            else
                System.out.print("<"+type+">");
        }
    } 

    private void debug(String type, String text){
        if(config.debug){
            System.out.print("<"+type+","+text+">");
        }
    } 
%}


/* ------ Macro Declarations ------ */
LineTerminator  = \r | \n | \r\n
WhiteSpace      = {LineTerminator} | [ \t\f]

Text            = [\"]([^\"])*[\"]
Boolean         = (true)|(false)
Variable        = ([A-Z|a-z][A-Z|a-z|0-9|_]*)
Integer         = (-)?[0-9][0-9]*


%%
/* ------ Lexical Rules Section ------ */
// token
"="      { debug("ASSIGNER");  return symbol(sym.ASSIGNER);  }
";"      { debug("SEPARATOR"); return symbol(sym.SEPARATOR); }
// operation
"+"      { debug("PLUS");      return symbol(sym.PLUS); }
// primitive
"int"      { debug("PRIM_INTEGER"); return symbol(sym.PRIM_INTEGER); }
"bool"     { debug("PRIM_BOOLEAN"); return symbol(sym.PRIM_BOOLEAN); }
"string"   { debug("PRIM_STRING");  return symbol(sym.PRIM_STRING);  }
// Boolean
{Boolean}  { debug("BOOLEAN",yytext());  return symbol(sym.OBJECT, new Boolean(yytext())); }
{Variable} { debug("VARIABLE",yytext()); return symbol(sym.VARIABLE, new String(yytext())); }
{Integer}  { debug("NUMBER",yytext());   return symbol(sym.OBJECT, new Integer(yytext()));  }
{Text}     { debug("TEXT",yytext());     return symbol(sym.OBJECT, new String(yytext()));  }

{WhiteSpace}    { /* just skip what was found, do nothing */ }
/* ERROR */
[^]     { throw new Error("Illegal character <"+yytext()+">"); }