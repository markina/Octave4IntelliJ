package com.jetbrains.octave.parser;

import com.intellij.testFramework.ParsingTestCase;
import com.intellij.testFramework.TestDataPath;

@TestDataPath("/testData/fromGithub3")
public class FromGithubTest3 extends ParsingTestCase {
  private static final String DATA_PATH = System.getProperty("user.dir") + "/testData/fromGithub3";

  public FromGithubTest3() {
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
    for (int i = 300; i <= 399; i++) {
      y = i;

      doTest(true);
    }
  }

}

