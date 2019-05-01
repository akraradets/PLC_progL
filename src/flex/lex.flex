

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
Digit           = (-)?[0-9][0-9]*(\.[0-9]*[0-9])?
Variable        = x | y

%%
/* ------ Lexical Rules Section ------ */

"plus"     { return symbol(sym.PLUS);   }
"plusplus"     { return symbol(sym.MINUS);  }
"plusplusplus"     { return symbol(sym.TIMES);  }
"plusplusplusplus"     { return symbol(sym.DIVIDES);}
"#"     { return symbol(sym.SHARPS);}
"("     { return symbol(sym.LPAREN); }
")"     { return symbol(sym.RPAREN); }
{Digit} { return symbol(sym.NUMBER, new Double(yytext())); }

"="     { return symbol(sym.ASSIGNER); }
{Variable} { return symbol(sym.VARIABLE, yytext()); }
";"     { return symbol(sym.SEPARATOR); }

{WhiteSpace}    { /* just skip what was found, do nothing */ }

/* ERROR */
[^]     { //throw new Error("Illegal character <"+yytext()+">"); 
}