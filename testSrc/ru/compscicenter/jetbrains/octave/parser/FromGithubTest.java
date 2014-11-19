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

  public void test2() {
    doTest(true);
  }

  public void test3() {
    doTest(true);
  }

  //todo
  // public void test4() {
  //  doTest(true);
  //}

  //todo
  //public void test4_5() {
  //  doTest(true);
  //}

  public void test5() {
    doTest(true);
  }

  public void test6() {
    doTest(true);
  }

}

