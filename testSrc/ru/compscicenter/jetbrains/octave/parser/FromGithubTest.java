package ru.compscicenter.jetbrains.octave.parser;

import com.intellij.testFramework.ParsingTestCase;
import com.intellij.testFramework.TestDataPath;

import java.io.IOException;

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

  Integer y = 0;


  public void test1() {
    y = 1;
    doTest(true);
  }

  public void test2() {
    y = 2;
    doTest(true);
  }

  public void test3() {
    y = 3;
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
    y = 5;
    doTest(true);
  }

  @Override
  protected String getTestName(boolean lowercaseFirstLetter) {

    return y.toString();
  }

  //public void test6() {
  //  doTest(true);
  //}

  //public void test7() {
  //  //setName("test7");
  //  System.out.print(getTestName(true));
  //  doTest(true);
  //
  //}

  public void test() {
    for (int i = 6; i < 13; i++) {
      y = i;
      //System.out.print(getTestName(true));
      doTest(true);
    }
  }
}

