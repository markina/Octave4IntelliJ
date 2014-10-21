package ru.compscicenter.jetbrains.octave.parser;

import com.intellij.lang.PsiBuilder;
import org.jetbrains.annotations.NotNull;

/**
 * Created by Markina Margarita on 21.10.14.
 */
public class OctaveParserContext {
  private final OctaveExpressionParsing myExpressionParser;
  private final PsiBuilder myPsiBuilder;


  public OctaveParserContext(@NotNull final PsiBuilder builder) {
    myPsiBuilder = builder;
    myExpressionParser = new OctaveExpressionParsing(this);
  }


  public PsiBuilder getBuilder() {
    return myPsiBuilder;
  }

  public OctaveExpressionParsing getExpressionParser() {
    return myExpressionParser;
  }
}
