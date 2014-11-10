package ru.compscicenter.jetbrains.octave.parser;

import com.intellij.lang.PsiBuilder;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;
import org.jetbrains.annotations.NotNull;
import ru.compscicenter.jetbrains.octave.lexer.OctaveTokenTypes;

import java.util.Stack;

/**
 * Created by Markina Margarita on 21.10.14.
 */
public class OctaveParsing {
  private final OctaveParserContext myContext;
  protected final PsiBuilder myPsiBuilder;

  public static final Logger LOG = Logger.getInstance(OctaveParsing.class.getName());
  public static final String EXPRESSION_EXPECTED = "Expression expected";

  public static int numberOfNesting = 0;
  public Stack<IElementType> stack = new Stack<IElementType>();

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
    while (OctaveTokenTypes.SET_SPACES.contains(myPsiBuilder.getTokenType())) {
      myPsiBuilder.advanceLexer();
    }
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
