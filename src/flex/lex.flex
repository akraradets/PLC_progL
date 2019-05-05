

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
Primitive       = (int)|(bool)|(string)|(null)
Boolean         = (true)|(false)
While           = (while)
Function        = (function)
Return          = (return)
Library         = (print)
Variable        = ([A-Z|a-z][A-Z|a-z|0-9|_]*)
Integer         = [0-9][0-9]*


%%
/* ------ Lexical Rules Section ------ */
// token
"=" { debug("ASSIGNER");  return symbol(sym.ASSIGNER);  }
";" { debug("SEPARATOR"); return symbol(sym.SEPARATOR); }
"(" { debug("LPAREN");    return symbol(sym.LPAREN); }
")" { debug("RPAREN");    return symbol(sym.RPAREN); }
"{" { debug("LBRANCE");   return symbol(sym.LBRANCE); }
"}" { debug("RBRANCE");   return symbol(sym.RBRANCE); }
// if-else
"if"    { debug("IF");   return symbol(sym.IF);}
"else"  { debug("ELSE"); return symbol(sym.ELSE);}
// operation
"+" { debug("OPER_PLUS", yytext());   return symbol(sym.OPER_PLUS, new String(yytext())); }
"-" { debug("OPER_MINUS", yytext());  return symbol(sym.OPER_MINUS, new String(yytext())); }
"/" { debug("OPER_DIVIDE", yytext()); return symbol(sym.OPER_DIVIDE, new String(yytext())); }
"*" { debug("OPER_MULTI", yytext());  return symbol(sym.OPER_MULTI, new String(yytext())); }
// condition
"&&" { debug("COND_AND", yytext());   return symbol(sym.COND_AND, new String(yytext())); }
"||" { debug("COND_OR", yytext());    return symbol(sym.COND_OR, new String(yytext())); }
// comparator
"=="  { debug("COMP_EQUAL", yytext());    return symbol(sym.COMP_EQUAL, new String(yytext())); }
"<"   { debug("COMP_LESSTHAN", yytext());  return symbol(sym.COMP_LESSTHAN, new String(yytext())); }
">"   { debug("COMP_MORETHAN", yytext());  return symbol(sym.COMP_MORETHAN, new String(yytext())); }
">="  { debug("COMP_MOREorEQUAL", yytext());  return symbol(sym.COMP_MOREorEQUAL, new String(yytext())); }
"<="  { debug("COMP_LESSorEQUAL", yytext());  return symbol(sym.COMP_LESSorEQUAL, new String(yytext())); }
// argrument
"!"  { debug("ARGRU_NEG", yytext());   return symbol(sym.ARGRU_NEG, new String(yytext())); }
// primitive
{Primitive} { debug("PRIMITIVE", yytext()); return symbol(sym.PRIMITIVE, new String(yytext())); }
{Boolean}   { debug("BOOLEAN",yytext());  return symbol(sym.OBJECT, new Boolean(yytext())); }
{While}     { debug("WHILE",yytext());    return symbol(sym.WHILE, new String(yytext()));  }
{Function}  { debug("FUNCTION",yytext()); return symbol(sym.FUNCTION, new String(yytext()));  }
{Return}    { debug("RETURN",yytext());   return symbol(sym.RETURN, new String(yytext()));  }
{Library}   { debug("LIBRARY",yytext());  return symbol(sym.LIBRARY, new String(yytext()));  }
{Variable}  { debug("VARIABLE",yytext()); return symbol(sym.VARIABLE, new String(yytext())); }
{Integer}   { debug("NUMBER",yytext());   return symbol(sym.OBJECT, new Integer(yytext()));  }
{Text}      { debug("TEXT",yytext());     return symbol(sym.OBJECT, new String(yytext()));  }

{WhiteSpace}    { /* just skip what was found, do nothing */ }
/* ERROR */
[^]     { throw new Error("Illegal character <"+yytext()+">"); }