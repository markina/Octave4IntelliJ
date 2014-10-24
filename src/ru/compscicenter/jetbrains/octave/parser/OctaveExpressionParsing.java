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
      parseIfStatement();
    }
    else if (firstToken == OctaveTokenTypes.FOR_KEYWORD) {
      parseForStatement();
    }
    else if (firstToken == OctaveTokenTypes.WHILE_KEYWORD) {
      parseWhileStatement();
    }
    else if (firstToken == OctaveTokenTypes.SWITCH_KEYWORD) {
      parseSwitchStatement();
    }
    else if (firstToken == OctaveTokenTypes.DO_KEYWORD) {
      parseDoStatement();
    }
    else if (firstToken == OctaveTokenTypes.UNWIND_PROTECT_KEYWORD) {
      parseUnwindStatement();
    }
    else if (firstToken == OctaveTokenTypes.TRY_KEYWORD) {
      parseTryStatement();
    }
    else if (firstToken == OctaveTokenTypes.FUNCTION_KEYWORD) {
      parseFunctionStatement();
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

  private void parseFunctionStatement() {
    final PsiBuilder.Marker functionExpression = myPsiBuilder.mark();
    checkMatches(OctaveTokenTypes.FUNCTION_KEYWORD, "?function?");
    parseFunctionName();
    checkSetMatches(OctaveTokenTypes.SET_END_AUXILIARY_STATEMENT, "end_function_name expected");
    skipLineBreak();
    while (!isNullOrMatches(OctaveTokenTypes.SET_ENDFUNCTION_KEYWORDS)) {
      parseExpressionStatement();
      skipLineBreak();
    }
    if(myPsiBuilder.getTokenType() != null) {
      checkSetMatches(OctaveTokenTypes.SET_ENDFUNCTION_KEYWORDS, "endfunction expected");
    }
    functionExpression.done(OctaveElementTypes.FUNCTION_STATEMENT);
  }

  private void parseFunctionName() {
    final PsiBuilder.Marker functionNameExpression = myPsiBuilder.mark();
    parseExpression(); //todo
    functionNameExpression.done(OctaveElementTypes.FUNCTION_NAME_STATEMENT);
  }

  private void parseTryStatement() {
    final PsiBuilder.Marker tryStatement = myPsiBuilder.mark();
    checkMatches(OctaveTokenTypes.TRY_KEYWORD, "?catch?");
    skipLineBreak();
    while (!isNullOrMatches(OctaveTokenTypes.SET_ENDTRY_KEYWORDS) &&
           !isNullOrMatches(OctaveTokenTypes.CATCH_KEYWORD)) {
      parseExpressionStatement();
      skipLineBreak();
    }
    checkMatches(OctaveTokenTypes.CATCH_KEYWORD, "catch expected");
    skipLineBreak();
    while (!isNullOrMatches(OctaveTokenTypes.SET_ENDTRY_KEYWORDS)) {
      parseExpressionStatement();
      skipLineBreak();
    }
    checkSetMatches(OctaveTokenTypes.SET_ENDTRY_KEYWORDS, "endtry expected");
    tryStatement.done(OctaveElementTypes.TRY_STATEMENT);
  }

  private void parseUnwindStatement() {
    final PsiBuilder.Marker unwindStatement = myPsiBuilder.mark();
    checkMatches(OctaveTokenTypes.UNWIND_PROTECT_KEYWORD, "?unwind?");
    skipLineBreak();
    while (!isNullOrMatches(OctaveTokenTypes.SET_ENDUNWIND_KEYWORDS) &&
           !isNullOrMatches(OctaveTokenTypes.UNWIND_PROTECT_CLEANUP_KEYWORD)) {
      parseExpressionStatement();
      skipLineBreak();
    }
    checkMatches(OctaveTokenTypes.UNWIND_PROTECT_CLEANUP_KEYWORD, "unwind cleanup expected");
    skipLineBreak();
    while (!isNullOrMatches(OctaveTokenTypes.SET_ENDUNWIND_KEYWORDS)) {
      parseExpressionStatement();
      skipLineBreak();
    }
    checkSetMatches(OctaveTokenTypes.SET_ENDUNWIND_KEYWORDS, "?unwind end");
    unwindStatement.done(OctaveElementTypes.UNWIND_STATEMENT);
  }

  private void parseDoStatement() {
    final PsiBuilder.Marker doExpression = myPsiBuilder.mark();
    checkMatches(OctaveTokenTypes.DO_KEYWORD, "?do?");
    skipLineBreak();
    while (!isNullOrMatches(OctaveTokenTypes.UNTIL_KEYWORD)) {
      parseExpressionStatement();
      skipLineBreak();
    }
    checkMatches(OctaveTokenTypes.UNTIL_KEYWORD, "?until?");
    parseConditionExpression();
    checkSetMatches(OctaveTokenTypes.SET_END_AUXILIARY_STATEMENT, "end_condition expected");
    doExpression.done(OctaveElementTypes.DO_STATEMENT);
  }

  private void parseSwitchStatement() {
    final PsiBuilder.Marker switchExpression = myPsiBuilder.mark();
    checkMatches(OctaveTokenTypes.SWITCH_KEYWORD, "?switch?");
    parseSwitchParameter();
    checkSetMatches(OctaveTokenTypes.SET_END_AUXILIARY_STATEMENT, "end_switch_parameter expected");
    skipLineBreak();
    while (!isNullOrMatches(OctaveTokenTypes.SET_ENDSWITCH_KEYWORDS)) {
      parseCaseStatement();
    }
    checkSetMatches(OctaveTokenTypes.SET_ENDWHILE_KEYWORDS, "endwhile expected");
    switchExpression.done(OctaveElementTypes.SWITCH_STATEMENT);
  }

  private void parseCaseStatement() {
    final PsiBuilder.Marker caseStatement = myPsiBuilder.mark();
    checkSetMatches(OctaveTokenTypes.SET_CASE_OR_OTHERWISE, "case_or_otherwise expected");
    parseExpression(); // todo
    checkSetMatches(OctaveTokenTypes.SET_END_IDENTIFIER, "end identifier expected");
    while(!isNullOrMatches(OctaveTokenTypes.SET_ENDSWITCH_KEYWORDS) && !isNullOrMatches(OctaveTokenTypes.SET_CASE_OR_OTHERWISE)) {
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

  private void parseWhileStatement() {
    final PsiBuilder.Marker whileExpression = myPsiBuilder.mark();
    checkMatches(OctaveTokenTypes.WHILE_KEYWORD, "?while?");
    parseConditionExpression();
    checkSetMatches(OctaveTokenTypes.SET_END_AUXILIARY_STATEMENT, "end_condition expected");
    skipLineBreak();
    while (!isNullOrMatches(OctaveTokenTypes.SET_ENDWHILE_KEYWORDS)) {
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

  private void parseForStatement() {
    final PsiBuilder.Marker forExpression = myPsiBuilder.mark();
    checkMatches(OctaveTokenTypes.FOR_KEYWORD, "?for?");
    parseForEnumerateExpression();
    checkSetMatches(OctaveTokenTypes.SET_END_AUXILIARY_STATEMENT, "end_enumerate expected");
    skipLineBreak();
    while (!isNullOrMatches(OctaveTokenTypes.SET_ENDFOR_KEYWORDS)) {
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

  private void parseIfStatement() {  // myPsiBuilder.getTokenType() == "if"
    final PsiBuilder.Marker ifExpression = myPsiBuilder.mark();
    checkSetMatches(OctaveTokenTypes.IF_OR_ELSE_KEYWORD, "?if?");
    parseConditionExpression();
    checkSetMatches(OctaveTokenTypes.SET_END_AUXILIARY_STATEMENT, "end_condition expected");
    skipLineBreak();
    while (!isNullOrMatches(OctaveTokenTypes.SET_ENDIF_KEYWORDS)) {
      if (myPsiBuilder.getTokenType() == OctaveTokenTypes.ELSE_KEYWORD) {
        checkMatches(OctaveTokenTypes.ELSE_KEYWORD, "else expected");
        while (!isNullOrMatches(OctaveTokenTypes.SET_ENDIF_KEYWORDS)) {
          parseExpressionStatement();
          checkSetMatches(OctaveTokenTypes.SET_END_STATEMENT, "end statement expected");
          skipLineBreak();
        }
        break;
      }
      else if (myPsiBuilder.getTokenType() == OctaveTokenTypes.ELSEIF_KEYWORD) {
        parseIfStatement();
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

  private boolean isNullOrMatches(TokenSet tokenSet) {
    return myPsiBuilder.getTokenType() == null ||
      tokenSet.contains(myPsiBuilder.getTokenType());
  }

  private boolean isNullOrMatches(IElementType keyword) {
    return myPsiBuilder.getTokenType() == null ||
           keyword == myPsiBuilder.getTokenType();
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
