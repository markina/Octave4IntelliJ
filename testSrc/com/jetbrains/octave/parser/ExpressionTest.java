package com.jetbrains.octave.parser;

import com.intellij.testFramework.ParsingTestCase;
import com.intellij.testFramework.TestDataPath;

@TestDataPath("/testData/expression")
public class ExpressionTest extends ParsingTestCase {
  private static final String DATA_PATH = System.getProperty("user.dir") + "/testData/expression";

  public ExpressionTest() {
    super("", "m", new OctaveParserDefinition());
  }

  @Override
  protected String getTestDataPath() {
    return DATA_PATH;
  }

  public void testAndIdentifier() {
    doTest(true);
  }

  public void testOrIdentifier() {
    doTest(true);
  }

  public void testOrAnd() {
    doTest(true);
  }

  public void testPlus() {
    doTest(true);
  }

  public void testMinus() {
    doTest(true);
  }

  public void testMult() {
    doTest(true);
  }

  public void testDiv() {
    doTest(true);
  }

  public void testPlusMinus() {
    doTest(true);
  }

  public void testMultDiv() {
    doTest(true);
  }

  public void testNumberLiteral() {
    doTest(true);
  }

  public void testPower() {
    doTest(true);
  }

  public void testTest() {
    doTest(true);
  }

  public void testComplexNum() {
    doTest(true);
  }

  public void testPars() {
    doTest(true);
  }

  public void testArgsByFunction() {
    doTest(true);
  }

  public void testBrackets2() {
    doTest(true);
  }

  public void testBrackets() {
    doTest(true);
  }


  public void testSemicolonInBrace() {
    doTest(true);
  }

  public void testIncrementDecrement() {
    doTest(true);
  }

  public void testDotOperator() {
    doTest(true);
  }

  public void testDotOperator2() {
    doTest(true);
  }

  public void testDotOperatorWithConst() {
    doTest(true);
  }

  public void testStringLiteral() {
    doTest(true);
  }

  public void testStringLiteral2() {
    doTest(true);
  }

  public void testAnonymousFunction() {
    doTest(true);
  }

  public void testSemicolonInPar() {
    doTest(true);
  }

  public void testAt() {
    doTest(true);
  }

  public void testEnd() {
    doTest(true);
  }
}
