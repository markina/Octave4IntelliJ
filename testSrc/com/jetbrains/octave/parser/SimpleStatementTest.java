package com.jetbrains.octave.parser;

import com.intellij.testFramework.ParsingTestCase;
import com.intellij.testFramework.TestDataPath;

@TestDataPath("/testData/simpleStatement")
public class SimpleStatementTest extends ParsingTestCase {
  private static final String DATA_PATH = System.getProperty("user.dir") + "/testData/simpleStatement";

  public SimpleStatementTest() {
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

  public void testParfor() {
    doTest(true);
  }

  public void testEnumeration() {
    doTest(true);
  }

  public void testClassdef() {
    doTest(true);
  }

  public void testIf2() {
    doTest(true);
  }

  public void testIdentifier() {
    doTest(true);
  }
}
