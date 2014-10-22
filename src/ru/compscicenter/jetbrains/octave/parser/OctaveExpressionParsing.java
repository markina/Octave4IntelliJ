package ru.compscicenter.jetbrains.octave.parser;

import com.intellij.lang.PsiBuilder;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;
import ru.compscicenter.jetbrains.octave.lexer.OctaveTokenTypes;
import ru.compscicenter.jetbrains.octave.psi.OctaveTokenType;

/**
 * Created by Markina Margarita on 21.10.14.
 */
public class OctaveExpressionParsing extends Parsing {
  public OctaveExpressionParsing(OctaveParserContext context) {
    super(context);
  }

  public void parseExpressionStatement() {
    final IElementType firstToken = myPsiBuilder.getTokenType();

    if (firstToken == null) return;
    if (firstToken == OctaveTokenTypes.IF_KEYWORD) {
      parseIfExpression();
    }
    else if (firstToken == OctaveTokenTypes.IDENTIFIER) { //for example
      parseIdentifierExpression(); //for example
    }
  }

  private void parseIdentifierExpression() { //for example
    final PsiBuilder.Marker identifierExpression = myPsiBuilder.mark();
    myPsiBuilder.advanceLexer();
    identifierExpression.done(OctaveElementTypes.IDENTIFIER_STATEMENT); //for example
  }

  private void parseIfExpression() {
    final PsiBuilder.Marker ifExpression = myPsiBuilder.mark();
    parseConditionExpression();
    while(!checkSetMatches(OctaveTokenTypes.SET_ENDIF_KEYWORDS, "endif expected")) {
      if(checkMatches(OctaveTokenTypes.ELSE_KEYWORD, "else expected")) {
        while(!checkSetMatches(OctaveTokenTypes.SET_ENDIF_KEYWORDS, "endif expected")) {
          parseExpressionStatement();
          checkSetMatches(OctaveTokenTypes.SET_END_IDENTIFIER, "end identifier expected");
          skipLineBreak();
        }
      }
      parseExpressionStatement();
      checkSetMatches(OctaveTokenTypes.SET_END_IDENTIFIER, "end identifier expected");
      skipLineBreak();
    }
    ifExpression.done(OctaveElementTypes.IF_STATEMENT);
  }

  private void skipLineBreak() {
    while(checkMatches(OctaveTokenTypes.LINE_BREAK, "skip line break")) {
    }
  }

  private void parseConditionExpression() {
    final PsiBuilder.Marker conditionExpression = myPsiBuilder.mark();
    myPsiBuilder.advanceLexer();
    parseExpressionStatement();
    checkSetMatches(OctaveTokenTypes.SET_END_CONDITION, "end_condition expected");
    conditionExpression.done(OctaveElementTypes.CONDITION_STATEMENT);
  }

  private boolean checkMatches(IElementType token, String message) {
    if (myPsiBuilder.getTokenType() == token) {
      myPsiBuilder.advanceLexer();
      return true;
    }
    myPsiBuilder.error(message);
    return false;
  }

  private boolean checkSetMatches(TokenSet keywords, String message) {
    for (IElementType token : keywords.getTypes()) {
      if (myPsiBuilder.getTokenType() == token) {
        myPsiBuilder.advanceLexer();
        return true;
      }
    }
    myPsiBuilder.error(message);
    return false;
  }
}
