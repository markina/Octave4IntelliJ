package com.jetbrains.octave.parser;

import com.intellij.lang.PsiBuilder;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;
import com.jetbrains.octave.lexer.OctaveTokenTypes;
import org.jetbrains.annotations.NotNull;

public class OctaveParsing {
  private final OctaveParserContext myContext;
  protected final PsiBuilder myPsiBuilder;

  public static final Logger LOG = Logger.getInstance(OctaveParsing.class.getName());
  public static final String EXPRESSION_EXPECTED = "Expression expected";
  public static final String END_EXPRESSION_EXPECTED = "',', ';' or '\\n' expected";

  public static int numberOfNesting = 0;

  public OctaveParsing(@NotNull final OctaveParserContext context) {
    myContext = context;
    myPsiBuilder = context.getBuilder();
  }


  public boolean isNullOrMatches(final TokenSet tokenSet) {
    return myPsiBuilder.getTokenType() == null ||
           tokenSet.contains(myPsiBuilder.getTokenType());
  }

  public boolean isNullOrMatches(final IElementType keyword) {
    return myPsiBuilder.getTokenType() == null ||
           keyword == myPsiBuilder.getTokenType();
  }

  public boolean checkMatches(final IElementType token, final String message) {
    if (isNullOrMatches(token)) {
      myPsiBuilder.advanceLexer();
      return true;
    }
    myPsiBuilder.error(message);
    return false;
  }


  public void feedMatches(final IElementType token, final String message) {
    LOG.assertTrue(myPsiBuilder.getTokenType() == token, message);
    myPsiBuilder.advanceLexer();
  }

  public void feedMatches(final TokenSet tokens, final String message) {
    LOG.assertTrue(tokens.contains(myPsiBuilder.getTokenType()), message);
    myPsiBuilder.advanceLexer();
  }

  public boolean checkMatches(final TokenSet keywords, final String message) {
    if (isNullOrMatches(keywords)) {
      myPsiBuilder.advanceLexer();
      return true;
    }

    myPsiBuilder.error(message);
    return false;
  }

  public void skipLineBreak() {
    while (OctaveTokenTypes.SET_SPACES.contains(myPsiBuilder.getTokenType()) ||
           OctaveTokenTypes.SET_END_EXPRESSION.contains(myPsiBuilder.getTokenType())) {
      myPsiBuilder.advanceLexer();
    }
  }


  public boolean skipIncrementDecrement() {
    if (OctaveTokenTypes.INCREMENT == myPsiBuilder.getTokenType()) {
      myPsiBuilder.advanceLexer();
      return true;
    }
    else if (OctaveTokenTypes.DECREMENT == myPsiBuilder.getTokenType()) {
      myPsiBuilder.advanceLexer();
      return true;
    }
    return false;
  }

  public boolean skipApostrophe() {
    if (myPsiBuilder.getTokenType() != OctaveTokenTypes.APOSTROPHE) {
      return false;
    }
    while (OctaveTokenTypes.APOSTROPHE == myPsiBuilder.getTokenType()) {
      myPsiBuilder.advanceLexer();
    }
    return true;
  }

  public void buildTokenElement(@NotNull final IElementType elementType) {
    final PsiBuilder.Marker marker = myPsiBuilder.mark();
    myPsiBuilder.advanceLexer();
    marker.done(elementType);
  }

  public OctaveParserContext getContext() {
    return myContext;
  }
}
