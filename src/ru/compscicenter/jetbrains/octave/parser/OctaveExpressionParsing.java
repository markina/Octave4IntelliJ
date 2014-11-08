package ru.compscicenter.jetbrains.octave.parser;

import com.intellij.lang.PsiBuilder;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.psi.tree.IElementType;
import ru.compscicenter.jetbrains.octave.lexer.OctaveTokenTypes;

/**
 * Created by Markina Margarita on 21.10.14.
 */
public class OctaveExpressionParsing extends OctaveParsing {
  private static final Logger LOG = Logger.getInstance(OctaveExpressionParsing.class.getName());

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
      myPsiBuilder.advanceLexer(); /// can be everywhere  :(
    }
    else if (firstToken == OctaveTokenTypes.CONTINUE_KEYWORD) {
      myPsiBuilder.advanceLexer(); /// can be everywhere  :(
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
    else {
      parseExpression();
    }
  }

  private void parseReturnStatement() {

    final PsiBuilder.Marker returnExpression = myPsiBuilder.mark();
    feedMatches(OctaveTokenTypes.RETURN_KEYWORD, "Error: return");
    skipLineBreak();
    parseExpression();
    returnExpression.done(OctaveElementTypes.RETURN_STATEMENT);
  }

  private void parsePropertiesStatement() {
    final PsiBuilder.Marker propertiesExpression = myPsiBuilder.mark();
    feedMatches(OctaveTokenTypes.PROPERTIES_KEYWORD, "Error: properties");
    skipLineBreak();
    while (!isNullOrMatches(OctaveTokenTypes.SET_ENDPROPERTIES_KEYWORDS)) {
      parseExpressionStatement();
      skipLineBreak();
    }
    if (myPsiBuilder.getTokenType() != null) {
      checkMatches(OctaveTokenTypes.SET_ENDPROPERTIES_KEYWORDS, "endproperties expected");
    }
    propertiesExpression.done(OctaveElementTypes.PROPERTIES_STATEMENT);
  }

  private void parseEventsStatement() {
    final PsiBuilder.Marker eventsExpression = myPsiBuilder.mark();
    feedMatches(OctaveTokenTypes.EVENTS_KEYWORD, "Error: events");
    skipLineBreak();
    while (!isNullOrMatches(OctaveTokenTypes.SET_ENDEVENTS_KEYWORDS)) {
      parseExpressionStatement();
      skipLineBreak();
    }
    if (myPsiBuilder.getTokenType() != null) {
      checkMatches(OctaveTokenTypes.SET_ENDEVENTS_KEYWORDS, "endevents expected");
    }
    eventsExpression.done(OctaveElementTypes.EVENTS_STATEMENT);
  }


  private void parseEmumerationStatement() {
    final PsiBuilder.Marker enumerationExpression = myPsiBuilder.mark();
    feedMatches(OctaveTokenTypes.ENUMERATION_KEYWORD, "Error: enumeration");
    skipLineBreak();
    while (!isNullOrMatches(OctaveTokenTypes.SET_ENDENUMERATION_KEYWORDS)) {
      parseExpressionStatement();
      skipLineBreak();
    }
    if (myPsiBuilder.getTokenType() != null) {
      checkMatches(OctaveTokenTypes.SET_ENDENUMERATION_KEYWORDS, "endenumeration expected");
    }
    enumerationExpression.done(OctaveElementTypes.ENUMERATION_STATEMENT);
  }

  private void parseMethodStatement() {
    final PsiBuilder.Marker methodsExpression = myPsiBuilder.mark();
    feedMatches(OctaveTokenTypes.METHODS_KEYWORD, "Error: methods");
    parseMethodsName();
    while (!isNullOrMatches(OctaveTokenTypes.SET_ENDMETHODS_KEYWORDS)) {
      parseExpressionStatement();
      skipLineBreak();
    }
    if (myPsiBuilder.getTokenType() != null) {
      checkMatches(OctaveTokenTypes.SET_ENDMETHODS_KEYWORDS, "endmethods expected");
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
    feedMatches(OctaveTokenTypes.CLASSDEF_KEYWORD, "Error: classdef");
    parseClassName();
    while (!isNullOrMatches(OctaveTokenTypes.SET_ENDCLASSDEF_KEYWORDS)) {
      parseExpressionStatement();
      skipLineBreak();
    }
    if (myPsiBuilder.getTokenType() != null) {
      checkMatches(OctaveTokenTypes.SET_ENDCLASSDEF_KEYWORDS, "endclassdef expected");
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
    feedMatches(OctaveTokenTypes.FUNCTION_KEYWORD, "Error: function");
    parseFunctionName();
    while (!isNullOrMatches(OctaveTokenTypes.SET_ENDFUNCTION_KEYWORDS)) {
      parseExpressionStatement();
      skipLineBreak();
    }
    if (myPsiBuilder.getTokenType() != null) {
      checkMatches(OctaveTokenTypes.SET_ENDFUNCTION_KEYWORDS, "endfunction expected");
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
    feedMatches(OctaveTokenTypes.TRY_KEYWORD, "Error: try");
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
    checkMatches(OctaveTokenTypes.SET_ENDTRY_KEYWORDS, "endtry expected");
    tryStatement.done(OctaveElementTypes.TRY_STATEMENT);
  }

  private void parseUnwindStatement() {
    final PsiBuilder.Marker unwindStatement = myPsiBuilder.mark();
    feedMatches(OctaveTokenTypes.UNWIND_PROTECT_KEYWORD, "Error: unwind");
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
    feedMatches(OctaveTokenTypes.SET_ENDUNWIND_KEYWORDS, "Error: unwind_end");
    unwindStatement.done(OctaveElementTypes.UNWIND_STATEMENT);
  }

  private void parseDoStatement() {
    final PsiBuilder.Marker doExpression = myPsiBuilder.mark();
    feedMatches(OctaveTokenTypes.DO_KEYWORD, "Error: do");
    skipLineBreak();
    while (!isNullOrMatches(OctaveTokenTypes.UNTIL_KEYWORD)) {
      parseExpressionStatement();
      skipLineBreak();
    }
    feedMatches(OctaveTokenTypes.UNTIL_KEYWORD, "Error: until");
    parseConditionExpression();
    doExpression.done(OctaveElementTypes.DO_STATEMENT);
  }

  private void parseSwitchStatement() {
    final PsiBuilder.Marker switchExpression = myPsiBuilder.mark();
    feedMatches(OctaveTokenTypes.SWITCH_KEYWORD, "Error: switch");
    parseSwitchParameter();
    while (!isNullOrMatches(OctaveTokenTypes.SET_ENDSWITCH_KEYWORDS)) {
      parseCaseStatement();
    }
    checkMatches(OctaveTokenTypes.SET_ENDWHILE_KEYWORDS, "endwhile expected");
    switchExpression.done(OctaveElementTypes.SWITCH_STATEMENT);
  }

  private void parseCaseStatement() {
    final PsiBuilder.Marker caseStatement = myPsiBuilder.mark();
    checkMatches(OctaveTokenTypes.SET_CASE_OR_OTHERWISE, "case_or_otherwise expected");
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
    feedMatches(OctaveTokenTypes.WHILE_KEYWORD, "Error: while");
    parseConditionExpression();
    while (!isNullOrMatches(OctaveTokenTypes.SET_ENDWHILE_KEYWORDS)) {
      parseExpressionStatement();
      skipLineBreak();
    }
    checkMatches(OctaveTokenTypes.SET_ENDWHILE_KEYWORDS, "endwhile expected");
    whileExpression.done(OctaveElementTypes.WHILE_STATEMENT);
  }

  private void parseExpression() { //todo
    final PsiBuilder.Marker expression = myPsiBuilder.mark();
    if (parseOrExpression()) {
      if (OctaveTokenTypes.SET_EQ_OR_OPERATION_EQ.contains(myPsiBuilder.getTokenType())) {
        feedMatches(OctaveTokenTypes.SET_EQ_OR_OPERATION_EQ, "Error: eq expected");
        parseOrExpression();
        checkMatches(OctaveTokenTypes.SET_END_STATEMENT, "end statement expected");
        expression.done(OctaveElementTypes.EXPRESSION);
        return;
      }
      else {
        if (myPsiBuilder.getTokenType() == OctaveTokenTypes.IDENTIFIER) {
          feedMatches(OctaveTokenTypes.IDENTIFIER, "Error: identifier expected");
          expression.drop();
          return;
        }
      }
    }
    expression.drop();
    myPsiBuilder.advanceLexer();
    myPsiBuilder.error("Expression expected");
  }

  private boolean parseOrExpression() {
    PsiBuilder.Marker orExpression = myPsiBuilder.mark();
    if (!parseAndExpression()) {
      orExpression.drop();
      return false;
    }
    while (OctaveTokenTypes.SET_OR_OPERATIONS.contains(myPsiBuilder.getTokenType())) {
      feedMatches(OctaveTokenTypes.SET_OR_OPERATIONS, "Error: or operation");
      if(!parseAndExpression()) {
        myPsiBuilder.error("Expression expected");
      }
      orExpression.done(OctaveElementTypes.BINARY_EXPRESSION);
      orExpression = orExpression.precede();
    }

    orExpression.drop();
    return true;
  }

  private boolean parseAndExpression() {
    PsiBuilder.Marker andExpression = myPsiBuilder.mark();
    if(!parseNotExpression()) {
      andExpression.drop();
      return false;
    }
    while (OctaveTokenTypes.SET_AND_OPERATIONS.contains(myPsiBuilder.getTokenType())) {
      feedMatches(OctaveTokenTypes.SET_AND_OPERATIONS, "Error: and operation");
      if (!parseNotExpression()) {
        myPsiBuilder.error("Expression expected");
      }
      andExpression.done(OctaveElementTypes.BINARY_EXPRESSION);
      andExpression = andExpression.precede();
    }

    andExpression.drop();
    return true;
  }

  private boolean parseNotExpression() {
    return parsePrimaryExpression(); // todo
  }

  private boolean parsePrimaryExpression() {
    if(myPsiBuilder.getTokenType() == OctaveTokenTypes.IDENTIFIER) {
      buildTokenElement(OctaveElementTypes.IDENTIDIER);
      return true;
    }
    myPsiBuilder.advanceLexer();
    return false;
  }







    /* todo
    for t = 1:10
       3
       4
    end
     */
  //
  //if (myPsiBuilder.getTokenType() == OctaveTokenTypes.IDENTIFIER) {
  //  myPsiBuilder.advanceLexer();
  //  if (myPsiBuilder.getTokenType() == OctaveTokenTypes.PERSISTENT_KEYWORD) {
  //    feedMatches(OctaveTokenTypes.PERSISTENT_KEYWORD, "Error: persistent");
  //  }
  //  else if (myPsiBuilder.getTokenType() == OctaveTokenTypes.STATIC_KEYWORD) {
  //    feedMatches(OctaveTokenTypes.STATIC_KEYWORD, "Error: static");
  //  }
  //  if (OctaveTokenTypes.SET_END_IDENTIFIER.contains(myPsiBuilder.getTokenType())) {
  //    checkMatches(OctaveTokenTypes.SET_END_STATEMENT, "end statement expected");
  //    skipLineBreak();
  //    //myPsiBuilder.advanceLexer();
  //    return true;
  //  }
  //  else {
  //    parseExpressionStatement();
  //  }
  //}
  //if (OctaveTokenTypes.SET_NUMBER_LITERAL.contains(myPsiBuilder.getTokenType())) {
  //
  //}
  //return false;
  //}

  private void parseParforStatement() {
    final PsiBuilder.Marker parforExpression = myPsiBuilder.mark();
    feedMatches(OctaveTokenTypes.PARFOR_KEYWORD, "Error: parfor");
    parseForEnumerateExpression();
    while (!isNullOrMatches(OctaveTokenTypes.SET_ENDPARFOR_KEYWORDS)) {
      parseExpressionStatement();
      skipLineBreak();
    }
    checkMatches(OctaveTokenTypes.SET_ENDPARFOR_KEYWORDS, "endparfor expected");
    parforExpression.done(OctaveElementTypes.PARFOR_STATEMENT);
  }


  private void parseForStatement() {
    final PsiBuilder.Marker forExpression = myPsiBuilder.mark();
    feedMatches(OctaveTokenTypes.FOR_KEYWORD, "Error: for");
    parseForEnumerateExpression();
    while (!isNullOrMatches(OctaveTokenTypes.SET_ENDFOR_KEYWORDS)) {
      parseExpressionStatement();
    }
    checkMatches(OctaveTokenTypes.SET_ENDFOR_KEYWORDS, "endfor expected");
    forExpression.done(OctaveElementTypes.FOR_STATEMENT);
  }

  private void parseForEnumerateExpression() {
    final PsiBuilder.Marker enumerateExpression = myPsiBuilder.mark();
    parseExpression(); //todo
    enumerateExpression.done(OctaveElementTypes.ENUMERATE_STATEMENT);
  }

  private void parseIfStatement() {
    final PsiBuilder.Marker ifExpression = myPsiBuilder.mark();
    feedMatches(OctaveTokenTypes.IF_KEYWORD, "Error: if");
    parseConditionExpression();
    while (!isNullOrMatches(OctaveTokenTypes.SET_ENDIF_KEYWORDS)) {
      if (myPsiBuilder.getTokenType() == OctaveTokenTypes.ELSE_KEYWORD) {
        feedMatches(OctaveTokenTypes.ELSE_KEYWORD, "Error: else");
        while (!isNullOrMatches(OctaveTokenTypes.SET_ENDIF_KEYWORDS)) {
          parseExpressionStatement();
          skipLineBreak();
        }
        break;
      }
      else if (myPsiBuilder.getTokenType() == OctaveTokenTypes.ELSEIF_KEYWORD) {
        parseElseifStatement();
        skipLineBreak();
        break;
      }
      else {
        parseExpressionStatement();
      }
    }
    checkMatches(OctaveTokenTypes.SET_ENDIF_KEYWORDS, "endif expected");
    ifExpression.done(OctaveElementTypes.IF_STATEMENT);
  }

  private void parseElseifStatement() {
    final PsiBuilder.Marker ifExpression = myPsiBuilder.mark();
    feedMatches(OctaveTokenTypes.ELSEIF_KEYWORD, "Error: elseif");
    parseConditionExpression();
    while (!isNullOrMatches(OctaveTokenTypes.SET_ENDIF_KEYWORDS)) {
      if (myPsiBuilder.getTokenType() == OctaveTokenTypes.ELSE_KEYWORD) {
        feedMatches(OctaveTokenTypes.ELSE_KEYWORD, "Error: else");
        while (!isNullOrMatches(OctaveTokenTypes.SET_ENDIF_KEYWORDS)) {
          parseExpressionStatement();
        }
        break;
      }
      else if (myPsiBuilder.getTokenType() == OctaveTokenTypes.ELSEIF_KEYWORD) {
        parseElseifStatement();
        skipLineBreak();
        break;
      }
      else {
        parseExpressionStatement();
      }
    }
    ifExpression.done(OctaveElementTypes.ELSEIF_STATEMENT);
  }


  private void parseConditionExpression() {
    final PsiBuilder.Marker conditionExpression = myPsiBuilder.mark();
    parseExpression(); //todo
    conditionExpression.done(OctaveElementTypes.CONDITION_STATEMENT);
  }


}
