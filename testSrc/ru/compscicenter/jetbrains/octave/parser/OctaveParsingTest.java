package ru.compscicenter.jetbrains.octave.parser;

import com.intellij.testFramework.ParsingTestCase;
import com.intellij.testFramework.TestDataPath;

/**
 * Created by Markina Margarita on 06.11.14.
 */
@TestDataPath("/testData/")
public class OctaveParsingTest extends ParsingTestCase {
  private static final String DATA_PATH = System.getProperty("user.dir") + "/testData/";

  public OctaveParsingTest() {
    super("", "m", new OctaveParserDefinition());
  }

  @Override
  protected String getTestDataPath() {
    return DATA_PATH;
  }

  public void testIf() {
    doTest();
  }

  public void testIfElse() {
    doTest();
  }

  public void testIfElseif() {
    doTest();
  }


  private void doTest() {
    doTest(true);
  }
}
