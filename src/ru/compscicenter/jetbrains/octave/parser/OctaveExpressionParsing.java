package ru.compscicenter.jetbrains.octave.parser;

import com.intellij.lang.PsiBuilder;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;
import ru.compscicenter.jetbrains.octave.lexer.OctaveTokenTypes;

/**
 * Created by Markina Margarita on 21.10.14.
 */
public class OctaveExpressionParsing extends Parsing {
  public OctaveExpressionParsing(OctaveParserContext context) {
    super(context);
  }

  public void parseExpressionStatement() {
    skipLineBreak();

    final IElementType firstToken = myPsiBuilder.getTokenType();


    if (firstToken == null) return;

    if (firstToken == OctaveTokenTypes.IF_KEYWORD) {
      parseIfExpression();
    }
    else if (firstToken == OctaveTokenTypes.FOR_KEYWORD) {
      parseForExpression();
    }
    else if (firstToken == OctaveTokenTypes.WHILE_KEYWORD) {
      parseWhileExpression();
    }
    else if (firstToken == OctaveTokenTypes.IDENTIFIER) { // todo
      parseExpression(); //todo
    } else {
      myPsiBuilder.error("bad character");
      myPsiBuilder.advanceLexer();
    }
  }

  private void parseWhileExpression() {
    final PsiBuilder.Marker whileExpression = myPsiBuilder.mark();
    myPsiBuilder.advanceLexer();
    parseConditionExpression();
    checkSetMatches(OctaveTokenTypes.SET_END_CONDITION_OR_ENUMERATE, "end_condition expected");
    skipLineBreak();
    while (myPsiBuilder.getTokenType() != null &&
           !OctaveTokenTypes.SET_ENDWHILE_KEYWORDS.contains(myPsiBuilder.getTokenType())) {
      parseExpressionStatement();
      checkSetMatches(OctaveTokenTypes.SET_END_STATEMENT, "end statement expected");
      skipLineBreak();
    }
    myPsiBuilder.advanceLexer();
    whileExpression.done(OctaveElementTypes.WHILE_STATEMENT);
  }

  private void parseExpression() { //todo
    if (myPsiBuilder.getTokenType() == OctaveTokenTypes.IDENTIFIER) {
      myPsiBuilder.advanceLexer();
      if (OctaveTokenTypes.SET_END_IDENTIFIER.contains(myPsiBuilder.getTokenType())) {
        return;
      }
      else {
        parseExpressionStatement();
      }
    }
  }

  private void parseForExpression() {
    final PsiBuilder.Marker forExpression = myPsiBuilder.mark();
    myPsiBuilder.advanceLexer();
    parseForEnumerateExpression();
    checkSetMatches(OctaveTokenTypes.SET_END_CONDITION_OR_ENUMERATE, "end_enumerate expected");
    skipLineBreak();
    while (myPsiBuilder.getTokenType() != null &&
           !OctaveTokenTypes.SET_ENDFOR_KEYWORDS.contains(myPsiBuilder.getTokenType())) {
      parseExpressionStatement();
      checkSetMatches(OctaveTokenTypes.SET_END_STATEMENT, "end statement expected");
      skipLineBreak();
    }
    myPsiBuilder.advanceLexer();
    forExpression.done(OctaveElementTypes.FOR_STATEMENT);
  }

  private void parseForEnumerateExpression() {
    final PsiBuilder.Marker enumerateExpression = myPsiBuilder.mark();
    parseExpressionStatement();
    enumerateExpression.done(OctaveElementTypes.ENUMERATE_STATEMENT);
  }

  private void parseIfExpression() {  // myPsiBuilder.getTokenType() == "if"
    final PsiBuilder.Marker ifExpression = myPsiBuilder.mark();
    myPsiBuilder.advanceLexer();
    parseConditionExpression();
    checkSetMatches(OctaveTokenTypes.SET_END_CONDITION_OR_ENUMERATE, "end_condition expected");
    skipLineBreak();
    while (myPsiBuilder.getTokenType() != null &&
           !OctaveTokenTypes.SET_ENDIF_KEYWORDS.contains(myPsiBuilder.getTokenType())) {
      if (myPsiBuilder.getTokenType() == OctaveTokenTypes.ELSE_KEYWORD) {
        myPsiBuilder.advanceLexer();
        while (myPsiBuilder.getTokenType() != null &&
               !OctaveTokenTypes.SET_ENDIF_KEYWORDS.contains(myPsiBuilder.getTokenType())) {
          parseExpressionStatement();
          checkSetMatches(OctaveTokenTypes.SET_END_STATEMENT, "end statement expected");
          skipLineBreak();
        }

        break;
      }
      else if (myPsiBuilder.getTokenType() == OctaveTokenTypes.ELSEIF_KEYWORD) {
        parseIfExpression();
        ifExpression.done(OctaveElementTypes.IF_STATEMENT);
        return;
      } else {
        parseExpressionStatement();
        checkSetMatches(OctaveTokenTypes.SET_END_STATEMENT, "end statement expected");
        skipLineBreak();
      }
    }
    checkSetMatches(OctaveTokenTypes.SET_ENDIF_KEYWORDS, "endif expected");
    ifExpression.done(OctaveElementTypes.IF_STATEMENT);
  }

  private void parseConditionExpression() {
    final PsiBuilder.Marker conditionExpression = myPsiBuilder.mark();
    parseExpressionStatement();

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

  private void skipLineBreak() {
    while (OctaveTokenTypes.SET_SPACES.contains(myPsiBuilder.getTokenType())) {
      myPsiBuilder.advanceLexer();
    }
  }
}
