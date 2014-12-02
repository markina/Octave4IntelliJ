package com.jetbrains.octave.parser;

import com.intellij.testFramework.ParsingTestCase;
import com.intellij.testFramework.TestDataPath;

@TestDataPath("/testData/fromGithub")
public class FromGithubTest extends ParsingTestCase {
  private static final String DATA_PATH = System.getProperty("user.dir") + "/testData/fromGithub";

  public FromGithubTest() {
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
    for (int i = 1; i <= 47; i++) {
      y = i;

      doTest(true);
    }
  }
}

