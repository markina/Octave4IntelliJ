package com.jetbrains.octave.parser;

import com.intellij.testFramework.ParsingTestCase;
import com.intellij.testFramework.TestDataPath;

@TestDataPath("/testData/fromGithub1")
public class FromGithubTest1 extends ParsingTestCase {
  private static final String DATA_PATH = System.getProperty("user.dir") + "/testData/fromGithub1";

  public FromGithubTest1() {
    super("", "m", new OctaveParserDefinition());
  }

  @Override
  protected String getTestDataPath() {
    return DATA_PATH;
  }

  Integer y = 0;


  @Override
  protected String getTestName(boolean lowercaseFirstLetter) {

    return y.toString();
  }

  public void test2() {
    for (int i = 100; i <= 199; i++) {
      y = i;

      doTest(true);
    }
  }

}

