package com.jetbrains.octave.parser;

import com.intellij.lang.PsiBuilder;
import org.jetbrains.annotations.NotNull;

public class OctaveParserContext {
  private final OctaveExpressionParsing myExpressionParser;
  private final OctaveStatementParsing myStatementParser;
  private final PsiBuilder myPsiBuilder;


  public OctaveParserContext(@NotNull final PsiBuilder builder) {
    myPsiBuilder = builder;
    myExpressionParser = new OctaveExpressionParsing(this);
    myStatementParser = new OctaveStatementParsing(this);
  }


  public PsiBuilder getBuilder() {
    return myPsiBuilder;
  }

  public OctaveExpressionParsing getExpressionParser() {
    return myExpressionParser;
  }

  public OctaveStatementParsing getStatementParser() {
    return myStatementParser;
  }
}
