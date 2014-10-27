package ru.compscicenter.jetbrains.octave.lexer;

import com.intellij.psi.tree.IElementType;
import junit.framework.TestCase;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class OctaveLexerTest extends TestCase {

  public void testAssignment() throws IOException {
    doTest("a = 1", "[a, Octave:IDENTIFIER]", "[ , Octave:SPACE]", "[=, Octave:EQ]", "[ , Octave:SPACE]", "[1, Octave:INTEGER_LITERAL]");
  }

  public void testNumericFloat() throws IOException {
    doTest("1.4", "[1.4, Octave:FLOAT_NUMBER_LITERAL]");
  }

  public void testNumericFloat2() throws IOException {
    doTest(".4", "[.4, Octave:FLOAT_NUMBER_LITERAL]");
  }

  public void testIdentifier() throws IOException {
    doTest("a_b_c", "[a_b_c, Octave:IDENTIFIER]");
  }

  public void testNumAndIdentifier() throws IOException {
    doTest("12b", "[12, Octave:INTEGER_LITERAL]", "[b, Octave:IDENTIFIER]");
  }

  public void testIdentifierWithCase() throws IOException {
    doTest("ABCabc", "[ABCabc, Octave:IDENTIFIER]");
  }

  public void testNaN() throws IOException {
    doTest("a5_=NaN", "[a5_, Octave:IDENTIFIER]", "[=, Octave:EQ]", "[NaN, Octave:NAN_KEYWORD]");
  }

  public void testLong() throws IOException {
    doTest("555L", "[555L, Octave:LONG_LITERAL]");
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
           "[;, Octave:SEMICOLON]");
  }

  public void testBrace() throws IOException {
    doTest("{4}", "[{, Octave:LBRACE]", "[4, Octave:INTEGER_LITERAL]", "[}, Octave:RBRACE]");
  }

  public void testBracket() throws IOException {
    doTest("[4]", "[[, Octave:LBRACKET]", "[4, Octave:INTEGER_LITERAL]", "[], Octave:RBRACKET]");
  }

  public void testPar() throws IOException {
    doTest("(4)", "[(, Octave:LPAR]", "[4, Octave:INTEGER_LITERAL]", "[), Octave:RPAR]");
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
