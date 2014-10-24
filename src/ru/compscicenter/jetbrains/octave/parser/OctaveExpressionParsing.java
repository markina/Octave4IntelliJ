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
    else if (firstToken == OctaveTokenTypes.SWITCH_KEYWORD) {
      parseSwitchExpression();
    }
    else if (firstToken == OctaveTokenTypes.BREAK_KEYWORD) {
      myPsiBuilder.advanceLexer();
    }
    else if (firstToken == OctaveTokenTypes.CONTINUE_KEYWORD) {
      myPsiBuilder.advanceLexer();
    }
    else if (firstToken == OctaveTokenTypes.IDENTIFIER) { // todo
      parseExpression(); //todo
    }
    else {
      myPsiBuilder.error("bad character");
      myPsiBuilder.advanceLexer();
    }
  }

  private void parseSwitchExpression() {
    final PsiBuilder.Marker switchExpression = myPsiBuilder.mark();
    checkMatches(OctaveTokenTypes.SWITCH_KEYWORD, "?switch?");
    parseSwitchParameter();
    checkSetMatches(OctaveTokenTypes.SET_END_AUXILIARY_STATEMENT, "end_condition expected");
    skipLineBreak();
    while (!isMatchesOrNull(OctaveTokenTypes.SET_ENDSWITCH_KEYWORDS)) {
      parseCaseStatement();
    }
    checkSetMatches(OctaveTokenTypes.SET_ENDWHILE_KEYWORDS, "endwhile expected");
    switchExpression.done(OctaveElementTypes.SWITCH_STATEMENT);
  }

  private void parseCaseStatement() {
    final PsiBuilder.Marker caseStatement = myPsiBuilder.mark();
    checkSetMatches(OctaveTokenTypes.SET_CASE_OR_OTHERWISE, "case_or_otherwise expected");
    while(!isMatchesOrNull(OctaveTokenTypes.SET_CASE_OR_OTHERWISE)) {
      checkSetMatches(OctaveTokenTypes.SET_CASE_OR_OTHERWISE, "?case or otherwise?");
      parseExpression(); // todo
      checkSetMatches(OctaveTokenTypes.SET_END_IDENTIFIER, "end identifier expected");
      parseExpressionStatement();
      checkSetMatches(OctaveTokenTypes.SET_END_STATEMENT, "end statement expected");
      skipLineBreak();
    }
    caseStatement.done(OctaveElementTypes.CASE_STATEMENT);
  }

  private void parseSwitchParameter() {
    final PsiBuilder.Marker switchParameterExpression = myPsiBuilder.mark();
    parseExpression(); //todo
    switchParameterExpression.done(OctaveElementTypes.SWITCH_PARAMETER_STATEMENT);
  }

  private void parseWhileExpression() {
    final PsiBuilder.Marker whileExpression = myPsiBuilder.mark();
    checkMatches(OctaveTokenTypes.WHILE_KEYWORD, "?while?");
    parseConditionExpression();
    checkSetMatches(OctaveTokenTypes.SET_END_AUXILIARY_STATEMENT, "end_condition expected");
    skipLineBreak();
    while (!isMatchesOrNull(OctaveTokenTypes.SET_ENDWHILE_KEYWORDS)) {
      parseExpressionStatement();
      checkSetMatches(OctaveTokenTypes.SET_END_STATEMENT, "end statement expected");
      skipLineBreak();
    }
    checkSetMatches(OctaveTokenTypes.SET_ENDWHILE_KEYWORDS, "endwhile expected");
    whileExpression.done(OctaveElementTypes.WHILE_STATEMENT);
  }

  private void parseExpression() { //todo
    if (myPsiBuilder.getTokenType() == OctaveTokenTypes.IDENTIFIER) {
      myPsiBuilder.advanceLexer();
      if (OctaveTokenTypes.SET_END_IDENTIFIER.contains(myPsiBuilder.getTokenType())) {
        //myPsiBuilder.advanceLexer();
        return;
      }
      else {
        parseExpressionStatement();
      }
    }
  }

  private void parseForExpression() {
    final PsiBuilder.Marker forExpression = myPsiBuilder.mark();
    checkMatches(OctaveTokenTypes.FOR_KEYWORD, "?for?");
    parseForEnumerateExpression();
    checkSetMatches(OctaveTokenTypes.SET_END_AUXILIARY_STATEMENT, "end_enumerate expected");
    skipLineBreak();
    while (!isMatchesOrNull(OctaveTokenTypes.SET_ENDFOR_KEYWORDS)) {
      parseExpressionStatement();
      checkSetMatches(OctaveTokenTypes.SET_END_STATEMENT, "end statement expected");
      skipLineBreak();
    }
    checkSetMatches(OctaveTokenTypes.SET_ENDFOR_KEYWORDS, "endif expected");
    forExpression.done(OctaveElementTypes.FOR_STATEMENT);
  }

  private void parseForEnumerateExpression() {
    final PsiBuilder.Marker enumerateExpression = myPsiBuilder.mark();
    parseExpression(); //todo
    enumerateExpression.done(OctaveElementTypes.ENUMERATE_STATEMENT);
  }

  private void parseIfExpression() {  // myPsiBuilder.getTokenType() == "if"
    final PsiBuilder.Marker ifExpression = myPsiBuilder.mark();
    checkSetMatches(OctaveTokenTypes.IF_OR_ELSE_KEYWORD, "?if?");
    parseConditionExpression();
    checkSetMatches(OctaveTokenTypes.SET_END_AUXILIARY_STATEMENT, "end_condition expected");
    skipLineBreak();
    while (!isMatchesOrNull(OctaveTokenTypes.SET_ENDIF_KEYWORDS)) {
      if (myPsiBuilder.getTokenType() == OctaveTokenTypes.ELSE_KEYWORD) {
        checkMatches(OctaveTokenTypes.ELSE_KEYWORD, "else expected");
        while (!isMatchesOrNull(OctaveTokenTypes.SET_ENDIF_KEYWORDS)) {
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
      }
      else {
        parseExpressionStatement();
        checkSetMatches(OctaveTokenTypes.SET_END_STATEMENT, "end statement expected");
        skipLineBreak();
      }
    }
    checkSetMatches(OctaveTokenTypes.SET_ENDIF_KEYWORDS, "endif expected");
    ifExpression.done(OctaveElementTypes.IF_STATEMENT);
  }

  private boolean isMatchesOrNull(TokenSet tokenSet) {
    return myPsiBuilder.getTokenType() == null ||
      tokenSet.contains(myPsiBuilder.getTokenType());
  }


  private void parseConditionExpression() {
    final PsiBuilder.Marker conditionExpression = myPsiBuilder.mark();
    parseExpression(); //todo
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
