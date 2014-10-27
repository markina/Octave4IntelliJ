package ru.compscicenter.jetbrains.octave.lexer;

import com.intellij.psi.tree.IElementType;
import junit.framework.TestCase;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class OctaveLexerTest extends TestCase {

  public void testAssignment() throws IOException {
    doTest("a = 1",
           "[a, Octave:IDENTIFIER]",
           "[ , Octave:SPACE]",
           "[=, Octave:EQ]",
           "[ , Octave:SPACE]",
           "[1, Octave:INTEGER_LITERAL]");
  }

  public void testNumericFloat() throws IOException {
    doTest("1.4",
           "[1.4, Octave:FLOAT_NUMBER_LITERAL]");
  }

  public void testNumericFloat2() throws IOException {
    doTest(".4",
           "[.4, Octave:FLOAT_NUMBER_LITERAL]");
  }

  public void testIdentifier() throws IOException {
    doTest("a_b_c",
           "[a_b_c, Octave:IDENTIFIER]");
  }

  public void testNumAndIdentifier() throws IOException {
    doTest("12b",
           "[12, Octave:INTEGER_LITERAL]",
           "[b, Octave:IDENTIFIER]");
  }

  public void testIdentifierWithCase() throws IOException {
    doTest("ABCabc",
           "[ABCabc, Octave:IDENTIFIER]");
  }

  public void testNaN() throws IOException {
    doTest("a5_=NaN",
           "[a5_, Octave:IDENTIFIER]",
           "[=, Octave:EQ]",
           "[NaN, Octave:NAN_KEYWORD]");
  }

  public void testBr() throws IOException {
    doTest("T = tables{t};",
           "[T, Octave:IDENTIFIER]",
           "[ , Octave:SPACE]",
           "[=, Octave:EQ]",
           "[ , Octave:SPACE]",
           "[tables, Octave:IDENTIFIER]",
           "[{, Octave:LBRACE]",
           "[t, Octave:IDENTIFIER]",
           "[}, Octave:RBRACE]",
           "[;, Octave:SEMICOLON]"
    );
  }

  public void testBrace() throws IOException {
    doTest("{4}",
           "[{, Octave:LBRACE]",
           "[4, Octave:INTEGER_LITERAL]",
           "[}, Octave:RBRACE]");
  }

  public void testBracket() throws IOException {
    doTest("[4]",
           "[[, Octave:LBRACKET]",
           "[4, Octave:INTEGER_LITERAL]",
           "[], Octave:RBRACKET]");
  }

  public void testPar() throws IOException {
    doTest("(4)",
           "[(, Octave:LPAR]",
           "[4, Octave:INTEGER_LITERAL]",
           "[), Octave:RPAR]"
    );
  }

  public void testIfKeyWords() throws IOException {
    doTest("if cond body else body end",
           "[if, Octave:IF_KEYWORD]",
           "[ , Octave:SPACE]",
           "[cond, Octave:IDENTIFIER]",
           "[ , Octave:SPACE]",
           "[body, Octave:IDENTIFIER]",
           "[ , Octave:SPACE]",
           "[else, Octave:ELSE_KEYWORD]",
           "[ , Octave:SPACE]",
           "[body, Octave:IDENTIFIER]",
           "[ , Octave:SPACE]",
           "[end, Octave:END_KEYWORD]"
    );
  }

  public void testString() throws IOException {
    doTest("'%5.2f & %5.2f & %5.2f \\n'",
           "['%5.2f & %5.2f & %5.2f \\n', Octave:STRING]");
  }

  public void testStringApostrophe() throws IOException {
    doTest("'I can''t escape'",
           "['I can''t escape', Octave:STRING]");
  }

  public void testStringDoubleQuote() throws IOException {
    doTest("'I \\can\"\"t escape\\'",
           "['I \\can\"\"t escape\\', Octave:STRING]");
  }

  public void testEscapeStringDouble() throws IOException {
    doTest("\"\\\"\"",
           "[\"\\\"\", Octave:STRING]");
  }

  public void testEscapeString() throws IOException {
    doTest("'\\r\\n\\t\\b\\a\\f\\v'",
           "['\\r\\n\\t\\b\\a\\f\\v', Octave:STRING]");
  }

  public void testFunction() throws IOException {
    doTest("hex2dec\"12b\"",
           "[hex2dec, Octave:IDENTIFIER]",
           "[\"12b\", Octave:STRING]");
  }

  public void testE() throws IOException {
    doTest("e",
           "[e, Octave:E_KEYWORD]");
  }

  public void testINF() throws IOException {
    doTest("inf",
           "[inf, Octave:INF_KEYWORD]");
  }

  public void testNegativeInteger() throws IOException {
    doTest("-45",
           "[-, Octave:MINUS]",
           "[45, Octave:INTEGER_LITERAL]");
  }

  public void testComplexNumber() throws IOException {
    doTest("5+6i",
           "[5, Octave:INTEGER_LITERAL]",
           "[+, Octave:PLUS]",
           "[6i, Octave:COMPLEX_LITERAL]"
    );
  }

  public void testComplexNumber2() throws IOException {
    doTest("6*i",
           "[6, Octave:INTEGER_LITERAL]",
           "[*, Octave:MULTIPLICATION]",
           "[i, Octave:COMPLEX_LITERAL]"
    );
  }

  public void testComplexNumber22() throws IOException {
    doTest("6*I",
           "[6, Octave:INTEGER_LITERAL]",
           "[*, Octave:MULTIPLICATION]",
           "[I, Octave:COMPLEX_LITERAL]"
    );
  }

  public void testHexNumeric() throws IOException {
    doTest("0x5",
           "[0x5, Octave:HEX_INTEGER]");
  }

  public void testHexNumeric1() throws IOException {
    doTest("0xFFAabcc340",
           "[0xFFAabcc340, Octave:HEX_INTEGER]");
  }

  public void testMatrix() throws IOException {
    doTest("y=0:.15:.7",
           "[y, Octave:IDENTIFIER]",
           "[=, Octave:EQ]",
           "[0, Octave:INTEGER_LITERAL]",
           "[:, Octave:COLON]",
           "[.15, Octave:FLOAT_NUMBER_LITERAL]",
           "[:, Octave:COLON]",
           "[.7, Octave:FLOAT_NUMBER_LITERAL]"
    );
  }

  public void testPiColon() throws IOException {
    doTest("pi:-pi/4:0",
           "[pi, Octave:PI_KEYWORD]",
           "[:, Octave:COLON]",
           "[-, Octave:MINUS]",
           "[pi, Octave:PI_KEYWORD]",
           "[/, Octave:DIVISION]",
           "[4, Octave:INTEGER_LITERAL]",
           "[:, Octave:COLON]",
           "[0, Octave:INTEGER_LITERAL]"
    );
  }

  public void testDotOperation() throws IOException {
    doTest("t./r.*r.+r",
           "[t, Octave:IDENTIFIER]",
           "[./, Octave:DOT_DIVISION]",
           "[r, Octave:IDENTIFIER]",
           "[.*, Octave:DOT_MULTIPLICATION]",
           "[r, Octave:IDENTIFIER]",
           "[.+, Octave:DOT_PLUS]",
           "[r, Octave:IDENTIFIER]"
    );
  }

  public void testLogicTrue() throws IOException {
    doTest("true",
           "[true, Octave:TRUE_KEYWORD]");
  }

  public void testLogicFalse() throws IOException {
    doTest("false",
           "[false, Octave:FALSE_KEYWORD]");
  }

  public void testIdentifierDotDot() throws IOException {
    doTest("..",
           "[., Octave:DOT]",
           "[., Octave:DOT]"
    );
  }







  //pi:-pi/4:0



















  private static void doTest(String text, String... expected) throws IOException {
    final OctaveLexer lexer = createLexer(text);
    for (String expectedTokenText : expected) {
      IElementType type = lexer.advance();
      if (type == null) {
        fail("Not enough tokens");
      }
      String tokenText = "[" + lexer.yytext() + ", " + type + "]";
      assertEquals(expectedTokenText, tokenText);
    }
    IElementType type = lexer.advance();
    if (type != null) fail("Too many tokens");
  }

  public static OctaveLexer createLexer(String text) {
    OctaveLexer lexer = new OctaveLexer((Reader)null);
    lexer.reset(text, 0, text.length(), OctaveLexer.YYINITIAL);
    return lexer;
  }
}
