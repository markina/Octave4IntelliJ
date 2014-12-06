package com.jetbrains.octave.parser;

import com.intellij.testFramework.ParsingTestCase;
import com.intellij.testFramework.TestDataPath;

@TestDataPath("/testData/fromGithub456789")
public class FromGithubTest456789 extends ParsingTestCase {
  private static final String DATA_PATH = System.getProperty("user.dir") + "/testData/fromGithub456789";

  public FromGithubTest456789() {
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

  public void test() {
    for (int i = 400; i <= 999; i++) {
      y = i;

      doTest(true);
    }
  }

}

