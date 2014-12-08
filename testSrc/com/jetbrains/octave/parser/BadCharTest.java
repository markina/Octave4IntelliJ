package com.jetbrains.octave.parser;

import com.intellij.testFramework.ParsingTestCase;
import com.intellij.testFramework.TestDataPath;

@TestDataPath("/testData/badChar")
public class BadCharTest extends ParsingTestCase {
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

  public void testBadFor() {
    doTest(true);
  }

  public void testBadComment() {
    doTest(true);
  }

  public void testBadIdentifier() {
    doTest(true);
  }
}
