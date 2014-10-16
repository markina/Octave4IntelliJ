package ru.compscicenter.jetbrains.octave.lexer;

import com.intellij.psi.tree.IElementType;
import junit.framework.TestCase;

import java.io.IOException;
import java.io.Reader;

public class OctaveLexerTest extends TestCase {

  public void testAssignment() throws IOException {
    doTest("a = 1", "[a, OctaveTypes:IDENTIFIER]");
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
