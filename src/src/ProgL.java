
//----------------------------------------------------
// The following code was generated by CUP v0.11a beta 20060608
// Thu May 02 11:09:27 ICT 2019
//----------------------------------------------------

package src;

import memory.PrimObj;
import memory.Memory;
import java_cup.runtime.*;
import java.util.Hashtable;

/** CUP v0.11a beta 20060608 generated parser.
  * @version Thu May 02 11:09:27 ICT 2019
  */
public class ProgL extends java_cup.runtime.lr_parser {

  /** Default constructor. */
  public ProgL() {super();}

  /** Constructor which sets the default scanner. */
  public ProgL(java_cup.runtime.Scanner s) {super(s);}

  /** Constructor which sets the default scanner. */
  public ProgL(java_cup.runtime.Scanner s, java_cup.runtime.SymbolFactory sf) {super(s,sf);}

  /** Production table. */
  protected static final short _production_table[][] = 
    unpackFromStrings(new String[] {
    "\000\011\000\002\002\004\000\002\003\004\000\002\003" +
    "\003\000\002\002\003\000\002\002\003\000\002\002\003" +
    "\000\002\004\005\000\002\004\004\000\002\004\006" });

  /** Access to production table. */
  public short[][] production_table() {return _production_table;}

  /** Parse-action table. */
  protected static final short[][] _action_table = 
    unpackFromStrings(new String[] {
    "\000\016\000\010\004\004\005\011\006\006\001\002\000" +
    "\004\011\ufffe\001\002\000\004\011\016\001\002\000\004" +
    "\011\ufffc\001\002\000\004\002\015\001\002\000\006\002" +
    "\uffff\010\012\001\002\000\004\011\ufffd\001\002\000\012" +
    "\002\000\004\004\005\011\006\006\001\002\000\006\002" +
    "\ufffb\010\ufffb\001\002\000\010\004\004\005\011\006\006" +
    "\001\002\000\004\002\001\001\002\000\010\002\ufffa\007" +
    "\017\010\ufffa\001\002\000\004\013\020\001\002\000\006" +
    "\002\ufff9\010\ufff9\001\002" });

  /** Access to parse-action table. */
  public short[][] action_table() {return _action_table;}

  /** <code>reduce_goto</code> table. */
  protected static final short[][] _reduce_table = 
    unpackFromStrings(new String[] {
    "\000\016\000\010\002\004\003\006\004\007\001\001\000" +
    "\002\001\001\000\002\001\001\000\002\001\001\000\002" +
    "\001\001\000\002\001\001\000\002\001\001\000\006\002" +
    "\004\004\012\001\001\000\002\001\001\000\006\002\004" +
    "\004\012\001\001\000\002\001\001\000\002\001\001\000" +
    "\002\001\001\000\002\001\001" });

  /** Access to <code>reduce_goto</code> table. */
  public short[][] reduce_table() {return _reduce_table;}

  /** Instance of action encapsulation class. */
  protected CUP$ProgL$actions action_obj;

  /** Action encapsulation object initializer. */
  protected void init_actions()
    {
      action_obj = new CUP$ProgL$actions(this);
    }

  /** Invoke a user supplied parse action. */
  public java_cup.runtime.Symbol do_action(
    int                        act_num,
    java_cup.runtime.lr_parser parser,
    java.util.Stack            stack,
    int                        top)
    throws java.lang.Exception
  {
    /* call code in generated class */
    return action_obj.CUP$ProgL$do_action(act_num, parser, stack, top);
  }

  /** Indicates start state. */
  public int start_state() {return 0;}
  /** Indicates start production. */
  public int start_production() {return 0;}

  /** <code>EOF</code> Symbol index. */
  public int EOF_sym() {return 0;}

  /** <code>error</code> Symbol index. */
  public int error_sym() {return 1;}





}

/** Cup generated class to encapsulate user supplied action code.*/
class CUP$ProgL$actions {

 
    Memory m = Memory.getInstance();

  private final ProgL parser;

  /** Constructor */
  CUP$ProgL$actions(ProgL parser) {
    this.parser = parser;
  }

  /** Method with the actual generated action code. */
  public final java_cup.runtime.Symbol CUP$ProgL$do_action(
    int                        CUP$ProgL$act_num,
    java_cup.runtime.lr_parser CUP$ProgL$parser,
    java.util.Stack            CUP$ProgL$stack,
    int                        CUP$ProgL$top)
    throws java.lang.Exception
    {
      /* Symbol object for return from actions */
      java_cup.runtime.Symbol CUP$ProgL$result;

      /* select the action based on the action number */
      switch (CUP$ProgL$act_num)
        {
          /*. . . . . . . . . . . . . . . . . . . .*/
          case 8: // declare ::= primitive VARIABLE ASSIGNER NUMBER 
            {
              Cupobj RESULT =null;
		int pleft = ((java_cup.runtime.Symbol)CUP$ProgL$stack.elementAt(CUP$ProgL$top-3)).left;
		int pright = ((java_cup.runtime.Symbol)CUP$ProgL$stack.elementAt(CUP$ProgL$top-3)).right;
		PrimObj p = (PrimObj)((java_cup.runtime.Symbol) CUP$ProgL$stack.elementAt(CUP$ProgL$top-3)).value;
		int vleft = ((java_cup.runtime.Symbol)CUP$ProgL$stack.elementAt(CUP$ProgL$top-2)).left;
		int vright = ((java_cup.runtime.Symbol)CUP$ProgL$stack.elementAt(CUP$ProgL$top-2)).right;
		String v = (String)((java_cup.runtime.Symbol) CUP$ProgL$stack.elementAt(CUP$ProgL$top-2)).value;
		int nleft = ((java_cup.runtime.Symbol)CUP$ProgL$stack.peek()).left;
		int nright = ((java_cup.runtime.Symbol)CUP$ProgL$stack.peek()).right;
		Integer n = (Integer)((java_cup.runtime.Symbol) CUP$ProgL$stack.peek()).value;
		 m.declare(p,v,n); 
              CUP$ProgL$result = parser.getSymbolFactory().newSymbol("declare",2, ((java_cup.runtime.Symbol)CUP$ProgL$stack.elementAt(CUP$ProgL$top-3)), ((java_cup.runtime.Symbol)CUP$ProgL$stack.peek()), RESULT);
            }
          return CUP$ProgL$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 7: // declare ::= primitive VARIABLE 
            {
              Cupobj RESULT =null;
		int pleft = ((java_cup.runtime.Symbol)CUP$ProgL$stack.elementAt(CUP$ProgL$top-1)).left;
		int pright = ((java_cup.runtime.Symbol)CUP$ProgL$stack.elementAt(CUP$ProgL$top-1)).right;
		PrimObj p = (PrimObj)((java_cup.runtime.Symbol) CUP$ProgL$stack.elementAt(CUP$ProgL$top-1)).value;
		int vleft = ((java_cup.runtime.Symbol)CUP$ProgL$stack.peek()).left;
		int vright = ((java_cup.runtime.Symbol)CUP$ProgL$stack.peek()).right;
		String v = (String)((java_cup.runtime.Symbol) CUP$ProgL$stack.peek()).value;
		 m.declare(p,v); 
              CUP$ProgL$result = parser.getSymbolFactory().newSymbol("declare",2, ((java_cup.runtime.Symbol)CUP$ProgL$stack.elementAt(CUP$ProgL$top-1)), ((java_cup.runtime.Symbol)CUP$ProgL$stack.peek()), RESULT);
            }
          return CUP$ProgL$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 6: // declare ::= declare SEPARATOR declare 
            {
              Cupobj RESULT =null;
		int d1left = ((java_cup.runtime.Symbol)CUP$ProgL$stack.elementAt(CUP$ProgL$top-2)).left;
		int d1right = ((java_cup.runtime.Symbol)CUP$ProgL$stack.elementAt(CUP$ProgL$top-2)).right;
		Cupobj d1 = (Cupobj)((java_cup.runtime.Symbol) CUP$ProgL$stack.elementAt(CUP$ProgL$top-2)).value;
		int d2left = ((java_cup.runtime.Symbol)CUP$ProgL$stack.peek()).left;
		int d2right = ((java_cup.runtime.Symbol)CUP$ProgL$stack.peek()).right;
		Cupobj d2 = (Cupobj)((java_cup.runtime.Symbol) CUP$ProgL$stack.peek()).value;
		 
              CUP$ProgL$result = parser.getSymbolFactory().newSymbol("declare",2, ((java_cup.runtime.Symbol)CUP$ProgL$stack.elementAt(CUP$ProgL$top-2)), ((java_cup.runtime.Symbol)CUP$ProgL$stack.peek()), RESULT);
            }
          return CUP$ProgL$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 5: // primitive ::= STRING 
            {
              PrimObj RESULT =null;
		 RESULT = new PrimObj("string"); 
              CUP$ProgL$result = parser.getSymbolFactory().newSymbol("primitive",0, ((java_cup.runtime.Symbol)CUP$ProgL$stack.peek()), ((java_cup.runtime.Symbol)CUP$ProgL$stack.peek()), RESULT);
            }
          return CUP$ProgL$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 4: // primitive ::= BOOLEAN 
            {
              PrimObj RESULT =null;
		 RESULT = new PrimObj("bool"); 
              CUP$ProgL$result = parser.getSymbolFactory().newSymbol("primitive",0, ((java_cup.runtime.Symbol)CUP$ProgL$stack.peek()), ((java_cup.runtime.Symbol)CUP$ProgL$stack.peek()), RESULT);
            }
          return CUP$ProgL$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 3: // primitive ::= INTEGER 
            {
              PrimObj RESULT =null;
		 RESULT = new PrimObj("int"); 
              CUP$ProgL$result = parser.getSymbolFactory().newSymbol("primitive",0, ((java_cup.runtime.Symbol)CUP$ProgL$stack.peek()), ((java_cup.runtime.Symbol)CUP$ProgL$stack.peek()), RESULT);
            }
          return CUP$ProgL$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 2: // initial ::= declare 
            {
              Cupobj RESULT =null;
		int dleft = ((java_cup.runtime.Symbol)CUP$ProgL$stack.peek()).left;
		int dright = ((java_cup.runtime.Symbol)CUP$ProgL$stack.peek()).right;
		Cupobj d = (Cupobj)((java_cup.runtime.Symbol) CUP$ProgL$stack.peek()).value;
		 
              CUP$ProgL$result = parser.getSymbolFactory().newSymbol("initial",1, ((java_cup.runtime.Symbol)CUP$ProgL$stack.peek()), ((java_cup.runtime.Symbol)CUP$ProgL$stack.peek()), RESULT);
            }
          return CUP$ProgL$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 1: // initial ::= declare SEPARATOR 
            {
              Cupobj RESULT =null;
		int dleft = ((java_cup.runtime.Symbol)CUP$ProgL$stack.elementAt(CUP$ProgL$top-1)).left;
		int dright = ((java_cup.runtime.Symbol)CUP$ProgL$stack.elementAt(CUP$ProgL$top-1)).right;
		Cupobj d = (Cupobj)((java_cup.runtime.Symbol) CUP$ProgL$stack.elementAt(CUP$ProgL$top-1)).value;

              CUP$ProgL$result = parser.getSymbolFactory().newSymbol("initial",1, ((java_cup.runtime.Symbol)CUP$ProgL$stack.elementAt(CUP$ProgL$top-1)), ((java_cup.runtime.Symbol)CUP$ProgL$stack.peek()), RESULT);
            }
          return CUP$ProgL$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 0: // $START ::= initial EOF 
            {
              Object RESULT =null;
		int start_valleft = ((java_cup.runtime.Symbol)CUP$ProgL$stack.elementAt(CUP$ProgL$top-1)).left;
		int start_valright = ((java_cup.runtime.Symbol)CUP$ProgL$stack.elementAt(CUP$ProgL$top-1)).right;
		Cupobj start_val = (Cupobj)((java_cup.runtime.Symbol) CUP$ProgL$stack.elementAt(CUP$ProgL$top-1)).value;
		RESULT = start_val;
              CUP$ProgL$result = parser.getSymbolFactory().newSymbol("$START",0, ((java_cup.runtime.Symbol)CUP$ProgL$stack.elementAt(CUP$ProgL$top-1)), ((java_cup.runtime.Symbol)CUP$ProgL$stack.peek()), RESULT);
            }
          /* ACCEPT */
          CUP$ProgL$parser.done_parsing();
          return CUP$ProgL$result;

          /* . . . . . .*/
          default:
            throw new Exception(
               "Invalid action number found in internal parse table");

        }
    }
}

