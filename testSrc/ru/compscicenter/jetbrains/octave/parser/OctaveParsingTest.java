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
    doTest(true);
  }

  public void testIfElse() {
    doTest(true);
  }

  public void testIfElseif() {
    doTest(true);
  }

  public void testIfElseifComplex() {
    doTest(true);
  }

  public void testIfComplex() {
    doTest(true);
  }

  public void testFor() {
    doTest(true);
  }

  public void testForIf() {
    doTest(true);
  }

  public void testProperties() {
    doTest(true);
  }

  public void testReturn() {
    doTest(true);
  }

  public void testMethodsIf() {
    doTest(true);
  }

  public void testWhile() {
    doTest(true);
  }

  public void testFunctionIfWhile() {
    doTest(true);
  }

  public void testTry() {
    doTest(true);
  }

  public void testUnwind() {
    doTest(true);
  }

  public void testDo() {
    doTest(true);
  }

  public void testSwitch() {
    doTest(true);
  }






}
