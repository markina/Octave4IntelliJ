package ru.compscicenter.jetbrains.octave.parser;

import com.intellij.testFramework.ParsingTestCase;
import com.intellij.testFramework.TestDataPath;

/**
 * Created by Markina Margarita on 11.11.14.
 */
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

  public void test1() {
    doTest(true);
  }
}

