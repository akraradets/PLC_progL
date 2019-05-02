

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
%}


/* ------ Macro Declarations ------ */
LineTerminator  = \r | \n | \r\n
WhiteSpace      = {LineTerminator} | [ \t\f]
Integer         = (-)?[0-9][0-9]*
Variable        = ([A-Z|a-z][A-Z|a-z|0-9|_]*)
Text            = (\".*\")

%%
/* ------ Lexical Rules Section ------ */
// token
"="     { return symbol(sym.ASSIGNER); }
";"     { return symbol(sym.SEPARATOR); }
// primitive
"int"      { return symbol(sym.INTEGER); }
"bool"     { return symbol(sym.BOOLEAN); }
"string"   { return symbol(sym.STRING); }

{Variable} { return symbol(sym.VARIABLE, new String(yytext())); }

{Integer}  { return symbol(sym.NUMBER, new Integer(yytext())); }
{Text}     { return symbol(sym.TEXT, new String(yytext())); }

{WhiteSpace}    { /* just skip what was found, do nothing */ }

/* ERROR */
[^]     { //throw new Error("Illegal character <"+yytext()+">"); 
}