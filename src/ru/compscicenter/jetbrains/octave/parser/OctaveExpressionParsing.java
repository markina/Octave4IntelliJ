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
    else if (firstToken == OctaveTokenTypes.PARFOR_KEYWORD) {
      parseParforStatement();
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
    else if (firstToken == OctaveTokenTypes.CLASSDEF_KEYWORD) {
      parseClassdefStatement();
    }
    else if (firstToken == OctaveTokenTypes.METHODS_KEYWORD) {
      parseMethodStatement();
    }
    else if (firstToken == OctaveTokenTypes.ENUMERATION_KEYWORD) {
      parseEmumerationStatement();
    }
    else if (firstToken == OctaveTokenTypes.EVENTS_KEYWORD) {
      parseEventsStatement();
    }
    else if (firstToken == OctaveTokenTypes.PROPERTIES_KEYWORD) {
      parsePropertiesStatement();
    }
    else if (firstToken == OctaveTokenTypes.RETURN_KEYWORD) {
      parseReturnStatement();
    }
    else if (firstToken == OctaveTokenTypes.IDENTIFIER) { // todo
      parseExpression(); //todo
    }
    else {
      myPsiBuilder.error("bad character");
      myPsiBuilder.advanceLexer();
    }
  }

  private void parseReturnStatement() {
    final PsiBuilder.Marker returnExpression = myPsiBuilder.mark();
    checkMatches(OctaveTokenTypes.RETURN_KEYWORD, "?return?");
    skipLineBreak();
    parseExpressionStatement();
    returnExpression.done(OctaveElementTypes.RETURN_STATEMENT);
  }

  private void parsePropertiesStatement() {
    final PsiBuilder.Marker propertiesExpression = myPsiBuilder.mark();
    checkMatches(OctaveTokenTypes.PROPERTIES_KEYWORD, "?properties?");
    skipLineBreak();
    while (!isNullOrMatches(OctaveTokenTypes.SET_ENDPROPERTIES_KEYWORDS)) {
      parseExpressionStatement();
      skipLineBreak();
    }
    if (myPsiBuilder.getTokenType() != null) {
      checkSetMatches(OctaveTokenTypes.SET_ENDPROPERTIES_KEYWORDS, "endproperties expected");
    }
    propertiesExpression.done(OctaveElementTypes.PROPERTIES_STATEMENT);
  }

  private void parseEventsStatement() {
    final PsiBuilder.Marker eventsExpression = myPsiBuilder.mark();
    checkMatches(OctaveTokenTypes.EVENTS_KEYWORD, "?events?");
    skipLineBreak();
    while (!isNullOrMatches(OctaveTokenTypes.SET_ENDEVENTS_KEYWORDS)) {
      parseExpressionStatement();
      skipLineBreak();
    }
    if (myPsiBuilder.getTokenType() != null) {
      checkSetMatches(OctaveTokenTypes.SET_ENDEVENTS_KEYWORDS, "endevents expected");
    }
    eventsExpression.done(OctaveElementTypes.EVENTS_STATEMENT);
  }

  private void parseEmumerationStatement() {
    final PsiBuilder.Marker enumerationExpression = myPsiBuilder.mark();
    checkMatches(OctaveTokenTypes.ENUMERATION_KEYWORD, "?enumeration?");
    skipLineBreak();
    while (!isNullOrMatches(OctaveTokenTypes.SET_ENDENUMERATION_KEYWORDS)) {
      parseExpressionStatement();
      skipLineBreak();
    }
    if (myPsiBuilder.getTokenType() != null) {
      checkSetMatches(OctaveTokenTypes.SET_ENDENUMERATION_KEYWORDS, "endenumeration expected");
    }
    enumerationExpression.done(OctaveElementTypes.ENUMERATION_STATEMENT);
  }

  private void parseMethodStatement() {
    final PsiBuilder.Marker methodsExpression = myPsiBuilder.mark();
    checkMatches(OctaveTokenTypes.METHODS_KEYWORD, "?methods?");
    parseMethodsName();
    while (!isNullOrMatches(OctaveTokenTypes.SET_ENDMETHODS_KEYWORDS)) {
      parseExpressionStatement();
      skipLineBreak();
    }
    if (myPsiBuilder.getTokenType() != null) {
      checkSetMatches(OctaveTokenTypes.SET_ENDMETHODS_KEYWORDS, "endmethods expected");
    }
    methodsExpression.done(OctaveElementTypes.METHODS_STATEMENT);
  }

  private void parseMethodsName() {
    final PsiBuilder.Marker methodsNameExpression = myPsiBuilder.mark();
    parseExpression(); //todo
    methodsNameExpression.done(OctaveElementTypes.METHODS_NAME_STATEMENT);
  }

  private void parseClassdefStatement() {
    final PsiBuilder.Marker classdefExpression = myPsiBuilder.mark();
    checkMatches(OctaveTokenTypes.CLASSDEF_KEYWORD, "?classdef?");
    parseClassName();
    while (!isNullOrMatches(OctaveTokenTypes.SET_ENDCLASSDEF_KEYWORDS)) {
      parseExpressionStatement();
      skipLineBreak();
    }
    if (myPsiBuilder.getTokenType() != null) {
      checkSetMatches(OctaveTokenTypes.SET_ENDCLASSDEF_KEYWORDS, "endclassdef expected");
    }
    classdefExpression.done(OctaveElementTypes.CLASSDEF_STATEMENT);
  }

  private void parseClassName() {
    final PsiBuilder.Marker classNameExpression = myPsiBuilder.mark();
    parseExpression(); //todo
    classNameExpression.done(OctaveElementTypes.CLASS_NAME_STATEMENT);
  }


  private void parseFunctionStatement() {
    final PsiBuilder.Marker functionExpression = myPsiBuilder.mark();
    checkMatches(OctaveTokenTypes.FUNCTION_KEYWORD, "?function?");
    parseFunctionName();
    while (!isNullOrMatches(OctaveTokenTypes.SET_ENDFUNCTION_KEYWORDS)) {
      parseExpressionStatement();
      skipLineBreak();
    }
    if (myPsiBuilder.getTokenType() != null) {
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
    checkMatches(OctaveTokenTypes.TRY_KEYWORD, "?try?");
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
    doExpression.done(OctaveElementTypes.DO_STATEMENT);
  }

  private void parseSwitchStatement() {
    final PsiBuilder.Marker switchExpression = myPsiBuilder.mark();
    checkMatches(OctaveTokenTypes.SWITCH_KEYWORD, "?switch?");
    parseSwitchParameter();
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
    while (!isNullOrMatches(OctaveTokenTypes.SET_ENDSWITCH_KEYWORDS) && !isNullOrMatches(OctaveTokenTypes.SET_CASE_OR_OTHERWISE)) {
      parseExpressionStatement();
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
    while (!isNullOrMatches(OctaveTokenTypes.SET_ENDWHILE_KEYWORDS)) {
      parseExpressionStatement();
      skipLineBreak();
    }
    checkSetMatches(OctaveTokenTypes.SET_ENDWHILE_KEYWORDS, "endwhile expected");
    whileExpression.done(OctaveElementTypes.WHILE_STATEMENT);
  }

  private void parseExpression() { //todo
    if (myPsiBuilder.getTokenType() == OctaveTokenTypes.IDENTIFIER) {

      myPsiBuilder.advanceLexer();
      if (myPsiBuilder.getTokenType() == OctaveTokenTypes.PERSISTENT_KEYWORD) {
        checkMatches(OctaveTokenTypes.PERSISTENT_KEYWORD, "?persistent");
      }
      else if (myPsiBuilder.getTokenType() == OctaveTokenTypes.STATIC_KEYWORD) {
        checkMatches(OctaveTokenTypes.STATIC_KEYWORD, "?static");
      }
      if (OctaveTokenTypes.SET_END_IDENTIFIER.contains(myPsiBuilder.getTokenType())) {
        checkSetMatches(OctaveTokenTypes.SET_END_STATEMENT, "end statement expected");
        skipLineBreak();
        //myPsiBuilder.advanceLexer();
        return;
      }
      else {
        parseExpressionStatement();
      }
    }
  }

  private void parseParforStatement() {
    final PsiBuilder.Marker parforExpression = myPsiBuilder.mark();
    checkMatches(OctaveTokenTypes.PARFOR_KEYWORD, "?parfor?");
    parseForEnumerateExpression();
    while (!isNullOrMatches(OctaveTokenTypes.SET_ENDPARFOR_KEYWORDS)) {
      parseExpressionStatement();
      //checkSetMatches(OctaveTokenTypes.SET_END_STATEMENT, "end statement expected");
      skipLineBreak();
    }
    checkSetMatches(OctaveTokenTypes.SET_ENDPARFOR_KEYWORDS, "endparfor expected");
    parforExpression.done(OctaveElementTypes.PARFOR_STATEMENT);
  }


  private void parseForStatement() {
    final PsiBuilder.Marker forExpression = myPsiBuilder.mark();
    checkMatches(OctaveTokenTypes.FOR_KEYWORD, "?for?");
    parseForEnumerateExpression();
    while (!isNullOrMatches(OctaveTokenTypes.SET_ENDFOR_KEYWORDS)) {
      parseExpressionStatement();
    }
    checkSetMatches(OctaveTokenTypes.SET_ENDFOR_KEYWORDS, "endfor expected");
    forExpression.done(OctaveElementTypes.FOR_STATEMENT);
  }

  private void parseForEnumerateExpression() {
    final PsiBuilder.Marker enumerateExpression = myPsiBuilder.mark();
    parseExpression(); //todo
    enumerateExpression.done(OctaveElementTypes.ENUMERATE_STATEMENT);
  }

  private void parseIfStatement() {
    final PsiBuilder.Marker ifExpression = myPsiBuilder.mark();
    checkSetMatches(OctaveTokenTypes.IF_OR_ELSE_KEYWORD, "?if?");
    parseConditionExpression();
    while (!isNullOrMatches(OctaveTokenTypes.SET_ENDIF_KEYWORDS)) {
      if (myPsiBuilder.getTokenType() == OctaveTokenTypes.ELSE_KEYWORD) {
        checkMatches(OctaveTokenTypes.ELSE_KEYWORD, "else expected");
        while (!isNullOrMatches(OctaveTokenTypes.SET_ENDIF_KEYWORDS)) {
          parseExpressionStatement();
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
