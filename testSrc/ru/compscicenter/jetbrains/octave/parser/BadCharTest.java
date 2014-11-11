package ru.compscicenter.jetbrains.octave.parser;

import com.intellij.testFramework.ParsingTestCase;
import com.intellij.testFramework.TestDataPath;

/**
 * Created by Markina Margarita on 11.11.14.
 */
@TestDataPath("/testData/badChar")
public class BadCharTest extends ParsingTestCase{
  private static final String DATA_PATH = System.getProperty("user.dir") + "/testData/badChar";

  public BadCharTest() {
    super("", "m", new OctaveParserDefinition());
  }

  @Override
  protected String getTestDataPath() {
    return DATA_PATH;
  }


  public void testBadChar() {
    doTest(true);
  }

  public void testSemicolonInPar() {
    doTest(true);
  }



}
