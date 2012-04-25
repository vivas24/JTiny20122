/* The following code was generated by JFlex 1.4.1 on 25/04/12 12:02 PM */

package Tiny;

import java_cup.runtime.*;
//import otros.*;


/**
 * This class is a scanner generated by 
 * <a href="http://www.jflex.de/">JFlex</a> 1.4.1
 * on 25/04/12 12:02 PM from the specification file
 * <tt>lexico.flex</tt>
 */
class Scanner implements java_cup.runtime.Scanner {

  /** This character denotes the end of file */
  public static final int YYEOF = -1;

  /** initial size of the lookahead buffer */
  private static final int ZZ_BUFFERSIZE = 16384;

  /** lexical states */
  public static final int YYINITIAL = 0;

  /** 
   * Translates characters to character classes
   */
  private static final char [] ZZ_CMAP = {
     0,  0,  0,  0,  0,  0,  0,  0,  0,  5,  3,  0,  0,  4,  0,  0, 
     0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 
     5, 29,  0,  0,  0,  0, 25,  0, 34, 35, 32, 30,  0, 31,  0, 33, 
     1,  1,  1,  1,  1,  1,  1,  1,  1,  1, 23, 36, 27, 24, 28,  0, 
     0,  2,  2,  2,  2,  2,  2,  2,  2,  2,  2,  2,  2,  2,  2,  2, 
     2,  2,  2,  2,  2,  2,  2,  2,  2,  2,  2,  0,  0,  0,  0,  0, 
     0,  7, 10,  2, 18, 11, 13, 12, 15,  8,  2,  2, 16,  6,  9,  2, 
    20,  2, 19, 17, 14, 21,  2, 22,  2,  2,  2, 37, 26, 38,  0,  0
  };

  /** 
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\1\0\1\1\1\2\1\3\1\4\1\1\1\5\7\3"+
    "\1\1\1\6\2\1\1\7\1\10\1\1\1\11\1\12"+
    "\1\13\1\14\1\15\1\16\1\17\1\1\1\4\1\3"+
    "\1\20\6\3\1\21\1\22\1\23\1\24\1\25\1\26"+
    "\1\0\1\3\1\27\6\3\1\30\1\3\1\31\1\32"+
    "\1\33\5\3\1\34\1\35\1\3\1\36\2\3\1\37";

  private static int [] zzUnpackAction() {
    int [] result = new int[70];
    int offset = 0;
    offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAction(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /** 
   * Translates a state to a row index in the transition table
   */
  private static final int [] ZZ_ROWMAP = zzUnpackRowMap();

  private static final String ZZ_ROWMAP_PACKED_0 =
    "\0\0\0\47\0\116\0\165\0\234\0\303\0\352\0\u0111"+
    "\0\u0138\0\u015f\0\u0186\0\u01ad\0\u01d4\0\u01fb\0\u0222\0\47"+
    "\0\u0249\0\u0270\0\u0297\0\u02be\0\u02e5\0\47\0\47\0\47"+
    "\0\47\0\47\0\47\0\47\0\u030c\0\47\0\u0333\0\165"+
    "\0\u035a\0\u0381\0\u03a8\0\u03cf\0\u03f6\0\u041d\0\47\0\47"+
    "\0\47\0\47\0\47\0\47\0\u0444\0\u046b\0\165\0\u0492"+
    "\0\u04b9\0\u04e0\0\u0507\0\u052e\0\u0555\0\47\0\u057c\0\165"+
    "\0\165\0\165\0\u05a3\0\u05ca\0\u05f1\0\u0618\0\u063f\0\165"+
    "\0\165\0\u0666\0\165\0\u068d\0\u06b4\0\165";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[70];
    int offset = 0;
    offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackRowMap(String packed, int offset, int [] result) {
    int i = 0;  /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int high = packed.charAt(i++) << 16;
      result[j++] = high | packed.charAt(i++);
    }
    return j;
  }

  /** 
   * The transition table of the DFA
   */
  private static final int [] ZZ_TRANS = zzUnpackTrans();

  private static final String ZZ_TRANS_PACKED_0 =
    "\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\4"+
    "\1\11\2\4\1\12\2\4\1\13\4\4\1\14\1\4"+
    "\1\15\1\16\1\17\1\20\1\21\1\22\1\23\1\24"+
    "\1\25\1\26\1\27\1\30\1\31\1\32\1\33\1\34"+
    "\1\35\1\2\50\0\1\3\47\0\1\4\3\0\21\4"+
    "\24\0\1\36\45\0\1\36\50\0\1\7\43\0\1\4"+
    "\3\0\1\4\1\37\17\4\22\0\1\4\3\0\7\4"+
    "\1\40\11\4\22\0\1\4\3\0\3\4\1\41\6\4"+
    "\1\42\6\4\22\0\1\4\3\0\11\4\1\43\7\4"+
    "\22\0\1\4\3\0\5\4\1\44\13\4\22\0\1\4"+
    "\3\0\3\4\1\45\15\4\22\0\1\4\3\0\15\4"+
    "\1\46\3\4\50\0\1\47\47\0\1\50\47\0\1\51"+
    "\44\0\1\52\46\0\1\53\46\0\1\54\16\0\46\55"+
    "\3\0\1\4\3\0\2\4\1\56\16\4\22\0\1\4"+
    "\3\0\14\4\1\57\4\4\22\0\1\4\3\0\13\4"+
    "\1\60\5\4\22\0\1\4\3\0\5\4\1\61\13\4"+
    "\22\0\1\4\3\0\1\4\1\62\14\4\1\63\2\4"+
    "\22\0\1\4\3\0\10\4\1\64\10\4\22\0\1\4"+
    "\3\0\2\4\1\65\16\4\20\0\46\55\1\66\2\0"+
    "\1\4\3\0\3\4\1\67\15\4\22\0\1\4\3\0"+
    "\5\4\1\70\13\4\22\0\1\4\3\0\3\4\1\71"+
    "\15\4\22\0\1\4\3\0\14\4\1\72\4\4\22\0"+
    "\1\4\3\0\5\4\1\73\13\4\22\0\1\4\3\0"+
    "\2\4\1\74\16\4\22\0\1\4\3\0\10\4\1\75"+
    "\10\4\22\0\1\4\3\0\4\4\1\76\14\4\22\0"+
    "\1\4\3\0\1\4\1\77\17\4\22\0\1\4\3\0"+
    "\12\4\1\100\6\4\22\0\1\4\3\0\5\4\1\101"+
    "\13\4\22\0\1\4\3\0\5\4\1\102\13\4\22\0"+
    "\1\4\3\0\10\4\1\103\10\4\22\0\1\4\3\0"+
    "\6\4\1\104\12\4\22\0\1\4\3\0\2\4\1\105"+
    "\16\4\22\0\1\4\3\0\3\4\1\106\15\4\20\0";

  private static int [] zzUnpackTrans() {
    int [] result = new int[1755];
    int offset = 0;
    offset = zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackTrans(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      value--;
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /* error codes */
  private static final int ZZ_UNKNOWN_ERROR = 0;
  private static final int ZZ_NO_MATCH = 1;
  private static final int ZZ_PUSHBACK_2BIG = 2;

  /* error messages for the codes above */
  private static final String ZZ_ERROR_MSG[] = {
    "Unkown internal scanner error",
    "Error: could not match input",
    "Error: pushback value was too large"
  };

  /**
   * ZZ_ATTRIBUTE[aState] contains the attributes of state <code>aState</code>
   */
  private static final int [] ZZ_ATTRIBUTE = zzUnpackAttribute();

  private static final String ZZ_ATTRIBUTE_PACKED_0 =
    "\1\0\1\11\15\1\1\11\5\1\7\11\1\1\1\11"+
    "\10\1\6\11\1\0\10\1\1\11\20\1";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[70];
    int offset = 0;
    offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAttribute(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /** the input device */
  private java.io.Reader zzReader;

  /** the current state of the DFA */
  private int zzState;

  /** the current lexical state */
  private int zzLexicalState = YYINITIAL;

  /** this buffer contains the current text to be matched and is
      the source of the yytext() string */
  private char zzBuffer[] = new char[ZZ_BUFFERSIZE];

  /** the textposition at the last accepting state */
  private int zzMarkedPos;

  /** the textposition at the last state to be included in yytext */
  private int zzPushbackPos;

  /** the current text position in the buffer */
  private int zzCurrentPos;

  /** startRead marks the beginning of the yytext() string in the buffer */
  private int zzStartRead;

  /** endRead marks the last character in the buffer, that has been read
      from input */
  private int zzEndRead;

  /** number of newlines encountered up to the start of the matched text */
  private int yyline;

  /** the number of characters up to the start of the matched text */
  private int yychar;

  /**
   * the number of characters from the last newline up to the start of the 
   * matched text
   */
  private int yycolumn;

  /** 
   * zzAtBOL == true <=> the scanner is currently at the beginning of a line
   */
  private boolean zzAtBOL = true;

  /** zzAtEOF == true <=> the scanner is at the EOF */
  private boolean zzAtEOF;

  /** denotes if the user-EOF-code has already been executed */
  private boolean zzEOFDone;

  /* user code: */
	public Scanner(java.io.InputStream r, SymbolFactory sf){
		this(r);
		this.sf=sf;
		lineanum=0;
		debug=true;
	}
	private SymbolFactory sf;
	private int lineanum;
	private boolean debug;


/******************************************************************
BORRAR SI NO SE NECESITA
	//TODO: Cambiar la SF por esto o ver que se hace
	//Crear un nuevo objeto java_cup.runtime.Symbol con informaci�n sobre el token actual sin valor
 	  private Symbol symbol(int type){
    		return new Symbol(type,yyline,yycolumn);
	  }
	//Crear un nuevo objeto java_cup.runtime.Symbol con informaci�n sobre el token actual con valor
	  private Symbol symbol(int type,Object value){
    		return new Symbol(type,yyline,yycolumn,value);
	  }
******************************************************************/


  /**
   * Creates a new scanner
   * There is also a java.io.InputStream version of this constructor.
   *
   * @param   in  the java.io.Reader to read input from.
   */
  Scanner(java.io.Reader in) {
    this.zzReader = in;
  }

  /**
   * Creates a new scanner.
   * There is also java.io.Reader version of this constructor.
   *
   * @param   in  the java.io.Inputstream to read input from.
   */
  Scanner(java.io.InputStream in) {
    this(new java.io.InputStreamReader(in));
  }


  /**
   * Refills the input buffer.
   *
   * @return      <code>false</code>, iff there was new input.
   * 
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  private boolean zzRefill() throws java.io.IOException {

    /* first: make room (if you can) */
    if (zzStartRead > 0) {
      System.arraycopy(zzBuffer, zzStartRead,
                       zzBuffer, 0,
                       zzEndRead-zzStartRead);

      /* translate stored positions */
      zzEndRead-= zzStartRead;
      zzCurrentPos-= zzStartRead;
      zzMarkedPos-= zzStartRead;
      zzPushbackPos-= zzStartRead;
      zzStartRead = 0;
    }

    /* is the buffer big enough? */
    if (zzCurrentPos >= zzBuffer.length) {
      /* if not: blow it up */
      char newBuffer[] = new char[zzCurrentPos*2];
      System.arraycopy(zzBuffer, 0, newBuffer, 0, zzBuffer.length);
      zzBuffer = newBuffer;
    }

    /* finally: fill the buffer with new input */
    int numRead = zzReader.read(zzBuffer, zzEndRead,
                                            zzBuffer.length-zzEndRead);

    if (numRead < 0) {
      return true;
    }
    else {
      zzEndRead+= numRead;
      return false;
    }
  }

    
  /**
   * Closes the input stream.
   */
  public final void yyclose() throws java.io.IOException {
    zzAtEOF = true;            /* indicate end of file */
    zzEndRead = zzStartRead;  /* invalidate buffer    */

    if (zzReader != null)
      zzReader.close();
  }


  /**
   * Resets the scanner to read from a new input stream.
   * Does not close the old reader.
   *
   * All internal variables are reset, the old input stream 
   * <b>cannot</b> be reused (internal buffer is discarded and lost).
   * Lexical state is set to <tt>ZZ_INITIAL</tt>.
   *
   * @param reader   the new input stream 
   */
  public final void yyreset(java.io.Reader reader) {
    zzReader = reader;
    zzAtBOL  = true;
    zzAtEOF  = false;
    zzEndRead = zzStartRead = 0;
    zzCurrentPos = zzMarkedPos = zzPushbackPos = 0;
    yyline = yychar = yycolumn = 0;
    zzLexicalState = YYINITIAL;
  }


  /**
   * Returns the current lexical state.
   */
  public final int yystate() {
    return zzLexicalState;
  }


  /**
   * Enters a new lexical state
   *
   * @param newState the new lexical state
   */
  public final void yybegin(int newState) {
    zzLexicalState = newState;
  }


  /**
   * Returns the text matched by the current regular expression.
   */
  public final String yytext() {
    return new String( zzBuffer, zzStartRead, zzMarkedPos-zzStartRead );
  }


  /**
   * Returns the character at position <tt>pos</tt> from the 
   * matched text. 
   * 
   * It is equivalent to yytext().charAt(pos), but faster
   *
   * @param pos the position of the character to fetch. 
   *            A value from 0 to yylength()-1.
   *
   * @return the character at position pos
   */
  public final char yycharat(int pos) {
    return zzBuffer[zzStartRead+pos];
  }


  /**
   * Returns the length of the matched text region.
   */
  public final int yylength() {
    return zzMarkedPos-zzStartRead;
  }


  /**
   * Reports an error that occured while scanning.
   *
   * In a wellformed scanner (no or only correct usage of 
   * yypushback(int) and a match-all fallback rule) this method 
   * will only be called with things that "Can't Possibly Happen".
   * If this method is called, something is seriously wrong
   * (e.g. a JFlex bug producing a faulty scanner etc.).
   *
   * Usual syntax/scanner level error handling should be done
   * in error fallback rules.
   *
   * @param   errorCode  the code of the errormessage to display
   */
  private void zzScanError(int errorCode) {
    String message;
    try {
      message = ZZ_ERROR_MSG[errorCode];
    }
    catch (ArrayIndexOutOfBoundsException e) {
      message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
    }

    throw new Error(message);
  } 


  /**
   * Pushes the specified amount of characters back into the input stream.
   *
   * They will be read again by then next call of the scanning method
   *
   * @param number  the number of characters to be read again.
   *                This number must not be greater than yylength()!
   */
  public void yypushback(int number)  {
    if ( number > yylength() )
      zzScanError(ZZ_PUSHBACK_2BIG);

    zzMarkedPos -= number;
  }


  /**
   * Contains user EOF-code, which will be executed exactly once,
   * when the end of file is reached
   */
  private void zzDoEOF() throws java.io.IOException {
    if (!zzEOFDone) {
      zzEOFDone = true;
      yyclose();
    }
  }


  /**
   * Resumes scanning until the next regular expression is matched,
   * the end of input is encountered or an I/O-Error occurs.
   *
   * @return      the next token
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  public java_cup.runtime.Symbol next_token() throws java.io.IOException {
    int zzInput;
    int zzAction;

    // cached fields:
    int zzCurrentPosL;
    int zzMarkedPosL;
    int zzEndReadL = zzEndRead;
    char [] zzBufferL = zzBuffer;
    char [] zzCMapL = ZZ_CMAP;

    int [] zzTransL = ZZ_TRANS;
    int [] zzRowMapL = ZZ_ROWMAP;
    int [] zzAttrL = ZZ_ATTRIBUTE;

    while (true) {
      zzMarkedPosL = zzMarkedPos;

      boolean zzR = false;
      for (zzCurrentPosL = zzStartRead; zzCurrentPosL < zzMarkedPosL;
                                                             zzCurrentPosL++) {
        switch (zzBufferL[zzCurrentPosL]) {
        case '\u000B':
        case '\u000C':
        case '\u0085':
        case '\u2028':
        case '\u2029':
          yyline++;
          yycolumn = 0;
          zzR = false;
          break;
        case '\r':
          yyline++;
          yycolumn = 0;
          zzR = true;
          break;
        case '\n':
          if (zzR)
            zzR = false;
          else {
            yyline++;
            yycolumn = 0;
          }
          break;
        default:
          zzR = false;
          yycolumn++;
        }
      }

      if (zzR) {
        // peek one character ahead if it is \n (if we have counted one line too much)
        boolean zzPeek;
        if (zzMarkedPosL < zzEndReadL)
          zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        else if (zzAtEOF)
          zzPeek = false;
        else {
          boolean eof = zzRefill();
          zzEndReadL = zzEndRead;
          zzMarkedPosL = zzMarkedPos;
          zzBufferL = zzBuffer;
          if (eof) 
            zzPeek = false;
          else 
            zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        }
        if (zzPeek) yyline--;
      }
      zzAction = -1;

      zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;
  
      zzState = zzLexicalState;


      zzForAction: {
        while (true) {
    
          if (zzCurrentPosL < zzEndReadL)
            zzInput = zzBufferL[zzCurrentPosL++];
          else if (zzAtEOF) {
            zzInput = YYEOF;
            break zzForAction;
          }
          else {
            // store back cached positions
            zzCurrentPos  = zzCurrentPosL;
            zzMarkedPos   = zzMarkedPosL;
            boolean eof = zzRefill();
            // get translated positions and possibly new buffer
            zzCurrentPosL  = zzCurrentPos;
            zzMarkedPosL   = zzMarkedPos;
            zzBufferL      = zzBuffer;
            zzEndReadL     = zzEndRead;
            if (eof) {
              zzInput = YYEOF;
              break zzForAction;
            }
            else {
              zzInput = zzBufferL[zzCurrentPosL++];
            }
          }
          int zzNext = zzTransL[ zzRowMapL[zzState] + zzCMapL[zzInput] ];
          if (zzNext == -1) break zzForAction;
          zzState = zzNext;

          int zzAttributes = zzAttrL[zzState];
          if ( (zzAttributes & 1) == 1 ) {
            zzAction = zzState;
            zzMarkedPosL = zzCurrentPosL;
            if ( (zzAttributes & 8) == 8 ) break zzForAction;
          }

        }
      }

      // store back cached position
      zzMarkedPos = zzMarkedPosL;

      switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
        case 3: 
          { if(debug) System.out.println("token ID");
				return sf.newSymbol("ID",sym.ID,new String(yytext()));
          }
        case 32: break;
        case 26: 
          { if(debug) System.out.println("token THEN");
			return sf.newSymbol("THEN",sym.THEN);
          }
        case 33: break;
        case 13: 
          { if(debug) System.out.println("token LPAREN");
			return sf.newSymbol("LPAREN",sym.LPAREN);
          }
        case 34: break;
        case 24: 
          { /* salto comentarios */ if(debug) System.out.println("token COMENTARIO");
          }
        case 35: break;
        case 29: 
          { if(debug) System.out.println("token WRITE");
			return sf.newSymbol("WRITE",sym.WRITE);
          }
        case 36: break;
        case 25: 
          { if(debug) System.out.println("token ELSE");
			return sf.newSymbol("ELSE",sym.ELSE);
          }
        case 37: break;
        case 11: 
          { if(debug) System.out.println("token TIMES");
			return sf.newSymbol("TIMES",sym.TIMES);
          }
        case 38: break;
        case 15: 
          { if(debug) System.out.println("token SEMI");
			return sf.newSymbol("SEMI",sym.SEMI);
          }
        case 39: break;
        case 4: 
          { lineanum++;
          }
        case 40: break;
        case 1: 
          { System.err.println("Caracter Ilegal encontrado en analisis lexico: " + yytext() + "\n");
          }
        case 41: break;
        case 6: 
          { if(debug) System.out.println("token EQ");
			return sf.newSymbol("EQ",sym.EQ);
          }
        case 42: break;
        case 27: 
          { if(debug) System.out.println("token READ");
			return sf.newSymbol("READ",sym.READ);
          }
        case 43: break;
        case 17: 
          { if(debug) System.out.println("token ASSIGN");
			return sf.newSymbol("ASSIGN",sym.ASSIGN);
          }
        case 44: break;
        case 14: 
          { if(debug) System.out.println("token RPAREN");
			return sf.newSymbol("RPAREN",sym.RPAREN);
          }
        case 45: break;
        case 5: 
          { /* saltos espacios en blanco*/
          }
        case 46: break;
        case 7: 
          { if(debug) System.out.println("token LT");
			return sf.newSymbol("LT",sym.LT);
          }
        case 47: break;
        case 30: 
          { if(debug) System.out.println("token REPEAT");
			return sf.newSymbol("REPEAT",sym.REPEAT);
          }
        case 48: break;
        case 16: 
          { if(debug) System.out.println("token IF");
			return sf.newSymbol("IF",sym.IF);
          }
        case 49: break;
        case 18: 
          { if(debug) System.out.println("token AND");
			return sf.newSymbol("AND",sym.AND);
          }
        case 50: break;
        case 31: 
          { if(debug) System.out.println("token MAINB");
			return sf.newSymbol("MAINB",sym.MAINB);
          }
        case 51: break;
        case 28: 
          { if(debug) System.out.println("token UNTIL");
			return sf.newSymbol("UNTIL",sym.UNTIL);
          }
        case 52: break;
        case 10: 
          { if(debug) System.out.println("token MINUS");
			return sf.newSymbol("MINUS",sym.MINUS);
          }
        case 53: break;
        case 23: 
          { if(debug) System.out.println("token END");
			return sf.newSymbol("END",sym.END);
          }
        case 54: break;
        case 22: 
          { if(debug) System.out.println("token NE");
			return sf.newSymbol("NE",sym.NE);
          }
        case 55: break;
        case 21: 
          { if(debug) System.out.println("token GE");
			return sf.newSymbol("GE",sym.GE);
          }
        case 56: break;
        case 20: 
          { if(debug) System.out.println("token LE");
			return sf.newSymbol("LE",sym.LE);
          }
        case 57: break;
        case 12: 
          { if(debug) System.out.println("token OVER");
			return sf.newSymbol("OVER",sym.OVER);
          }
        case 58: break;
        case 2: 
          { if(debug) System.out.println("token NUM");
			return sf.newSymbol("NUM",sym.NUM,new String(yytext()));
          }
        case 59: break;
        case 9: 
          { if(debug) System.out.println("token PLUS");
			return sf.newSymbol("PLUS",sym.PLUS);
          }
        case 60: break;
        case 19: 
          { if(debug) System.out.println("token OR");
			return sf.newSymbol("OR",sym.OR);
          }
        case 61: break;
        case 8: 
          { if(debug) System.out.println("token GT");
			return sf.newSymbol("GT",sym.GT);
          }
        case 62: break;
        default: 
          if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
            zzAtEOF = true;
            zzDoEOF();
              {     return sf.newSymbol("EOF",sym.EOF);
 }
          } 
          else {
            zzScanError(ZZ_NO_MATCH);
          }
      }
    }
  }


}
