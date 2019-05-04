

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
Primitive       = (int)|(bool)|(string)
Boolean         = (true)|(false)
Variable        = ([A-Z|a-z][A-Z|a-z|0-9|_]*)
Integer         = [0-9][0-9]*


%%
/* ------ Lexical Rules Section ------ */
// token
"=" { debug("ASSIGNER");  return symbol(sym.ASSIGNER);  }
";" { debug("SEPARATOR"); return symbol(sym.SEPARATOR); }
"(" { debug("LPAREN");    return symbol(sym.LPAREN); }
")" { debug("RPAREN");    return symbol(sym.RPAREN); }
// operation
"+" { debug("OPER_PLUS", yytext());   return symbol(sym.OPER_PLUS, new String(yytext())); }
"-" { debug("OPER_MINUS", yytext());  return symbol(sym.OPER_MINUS, new String(yytext())); }
"/" { debug("OPER_DIVIDE", yytext()); return symbol(sym.OPER_DIVIDE, new String(yytext())); }
"*" { debug("OPER_MULTI", yytext());  return symbol(sym.OPER_MULTI, new String(yytext())); }
// primitive
{Primitive} { debug("PRIMITIVE", yytext()); return symbol(sym.PRIMITIVE, new String(yytext())); }

{Boolean}  { debug("BOOLEAN",yytext());  return symbol(sym.OBJECT, new Boolean(yytext())); }
{Variable} { debug("VARIABLE",yytext()); return symbol(sym.VARIABLE, new String(yytext())); }
{Integer}  { debug("NUMBER",yytext());   return symbol(sym.OBJECT, new Integer(yytext()));  }
{Text}     { debug("TEXT",yytext());     return symbol(sym.OBJECT, new String(yytext()));  }

{WhiteSpace}    { /* just skip what was found, do nothing */ }
/* ERROR */
[^]     { throw new Error("Illegal character <"+yytext()+">"); }