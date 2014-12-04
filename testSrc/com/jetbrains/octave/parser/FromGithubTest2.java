package com.jetbrains.octave.parser;

import com.intellij.testFramework.ParsingTestCase;
import com.intellij.testFramework.TestDataPath;

@TestDataPath("/testData/fromGithub2")
public class FromGithubTest2 extends ParsingTestCase {
  private static final String DATA_PATH = System.getProperty("user.dir") + "/testData/fromGithub2";

  public FromGithubTest2() {
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
    for (int i = 200; i <= 299; i++) {
      y = i;

      doTest(true);
    }
  }



}

