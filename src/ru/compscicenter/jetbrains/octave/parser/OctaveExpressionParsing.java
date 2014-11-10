package ru.compscicenter.jetbrains.octave.parser;

import com.intellij.lang.PsiBuilder;
import com.intellij.psi.tree.IElementType;
import ru.compscicenter.jetbrains.octave.lexer.OctaveTokenTypes;

/**
 * Created by Markina Margarita on 21.10.14.
 */
public class OctaveExpressionParsing extends OctaveParsing {


  public OctaveExpressionParsing(OctaveParserContext context) {
    super(context);
  }

  public void parseExpressionStatement() {
    skipLineBreak();

    final IElementType currentToken = myPsiBuilder.getTokenType();

    if (OctaveTokenTypes.SET_END_STATEMENT.contains(currentToken)) {
      myPsiBuilder.advanceLexer();
      return;
    }

    if (currentToken == null) {
      return;
    }

    if (OctaveTokenTypes.SET_END_KEYWORDS.contains(currentToken)) {
      return;
    }

    if (OctaveTokenTypes.SET_INNER_KEYWORDS.contains(currentToken)) {
      return;
    }


    if (currentToken == OctaveTokenTypes.IF_KEYWORD) {
      parseIfStatement();
    }
    else if (currentToken == OctaveTokenTypes.FOR_KEYWORD) {
      parseForStatement();
    }
    else if (currentToken == OctaveTokenTypes.PARFOR_KEYWORD) {
      parseParforStatement();
    }
    else if (currentToken == OctaveTokenTypes.WHILE_KEYWORD) {
      parseWhileStatement();
    }
    else if (currentToken == OctaveTokenTypes.SWITCH_KEYWORD) {
      parseSwitchStatement();
    }
    else if (currentToken == OctaveTokenTypes.DO_KEYWORD) {
      parseDoStatement();
    }
    else if (currentToken == OctaveTokenTypes.UNWIND_PROTECT_KEYWORD) {
      parseUnwindStatement();
    }
    else if (currentToken == OctaveTokenTypes.TRY_KEYWORD) {
      parseTryStatement();
    }
    else if (currentToken == OctaveTokenTypes.FUNCTION_KEYWORD) {
      parseFunctionStatement();
    }
    else if (currentToken == OctaveTokenTypes.BREAK_KEYWORD) {
      myPsiBuilder.advanceLexer(); /// can be everywhere  :(
    }
    else if (currentToken == OctaveTokenTypes.CONTINUE_KEYWORD) {
      myPsiBuilder.advanceLexer(); /// can be everywhere  :(
    }
    else if (currentToken == OctaveTokenTypes.CLASSDEF_KEYWORD) {
      parseClassdefStatement();
    }
    else if (currentToken == OctaveTokenTypes.METHODS_KEYWORD) {
      parseMethodStatement();
    }
    else if (currentToken == OctaveTokenTypes.ENUMERATION_KEYWORD) {
      parseEmumerationStatement();
    }
    else if (currentToken == OctaveTokenTypes.EVENTS_KEYWORD) {
      parseEventsStatement();
    }
    else if (currentToken == OctaveTokenTypes.PROPERTIES_KEYWORD) {
      parsePropertiesStatement();
    }
    else if (currentToken == OctaveTokenTypes.RETURN_KEYWORD) {
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
    //skipLineBreak();
    while (!isNullOrMatches(OctaveTokenTypes.SET_ENDPROPERTIES_KEYWORDS)) {
      parseExpressionStatement();
    }
    if (myPsiBuilder.getTokenType() != null) {
      checkMatches(OctaveTokenTypes.SET_ENDPROPERTIES_KEYWORDS, "endproperties expected");
    }
    propertiesExpression.done(OctaveElementTypes.PROPERTIES_STATEMENT);
  }

  private void parseEventsStatement() {
    final PsiBuilder.Marker eventsExpression = myPsiBuilder.mark();
    feedMatches(OctaveTokenTypes.EVENTS_KEYWORD, "Error: events");
    //skipLineBreak();
    while (!isNullOrMatches(OctaveTokenTypes.SET_ENDEVENTS_KEYWORDS)) {
      parseExpressionStatement();
    }
    if (myPsiBuilder.getTokenType() != null) {
      checkMatches(OctaveTokenTypes.SET_ENDEVENTS_KEYWORDS, "endevents expected");
    }
    eventsExpression.done(OctaveElementTypes.EVENTS_STATEMENT);
  }


  private void parseEmumerationStatement() {
    final PsiBuilder.Marker enumerationExpression = myPsiBuilder.mark();
    feedMatches(OctaveTokenTypes.ENUMERATION_KEYWORD, "Error: enumeration");
    //skipLineBreak();
    while (!isNullOrMatches(OctaveTokenTypes.SET_ENDENUMERATION_KEYWORDS)) {
      parseExpressionStatement();
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
      //skipLineBreak();
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
      //skipLineBreak();
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
      //skipLineBreak();
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
    //skipLineBreak();
    while (!isNullOrMatches(OctaveTokenTypes.SET_ENDTRY_KEYWORDS) &&
           !isNullOrMatches(OctaveTokenTypes.CATCH_KEYWORD)) {
      parseExpressionStatement();
    }
    checkMatches(OctaveTokenTypes.CATCH_KEYWORD, "catch expected");
    while (!isNullOrMatches(OctaveTokenTypes.SET_ENDTRY_KEYWORDS)) {
      parseExpressionStatement();
    }
    checkMatches(OctaveTokenTypes.SET_ENDTRY_KEYWORDS, "endtry expected");
    tryStatement.done(OctaveElementTypes.TRY_STATEMENT);
  }

  private void parseUnwindStatement() {
    final PsiBuilder.Marker unwindStatement = myPsiBuilder.mark();
    feedMatches(OctaveTokenTypes.UNWIND_PROTECT_KEYWORD, "Error: unwind");
    //skipLineBreak();
    while (!isNullOrMatches(OctaveTokenTypes.SET_ENDUNWIND_KEYWORDS) &&
           !isNullOrMatches(OctaveTokenTypes.UNWIND_PROTECT_CLEANUP_KEYWORD)) {
      parseExpressionStatement();
    }
    checkMatches(OctaveTokenTypes.UNWIND_PROTECT_CLEANUP_KEYWORD, "unwind cleanup expected");
    while (!isNullOrMatches(OctaveTokenTypes.SET_ENDUNWIND_KEYWORDS)) {
      parseExpressionStatement();
    }
    feedMatches(OctaveTokenTypes.SET_ENDUNWIND_KEYWORDS, "Error: unwind_end");
    unwindStatement.done(OctaveElementTypes.UNWIND_STATEMENT);
  }

  private void parseDoStatement() {
    final PsiBuilder.Marker doExpression = myPsiBuilder.mark();
    feedMatches(OctaveTokenTypes.DO_KEYWORD, "Error: do");
    //skipLineBreak();
    while (!isNullOrMatches(OctaveTokenTypes.UNTIL_KEYWORD)) {
      parseExpressionStatement();
      //skipLineBreak();
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
    }
    checkMatches(OctaveTokenTypes.SET_ENDWHILE_KEYWORDS, "endwhile expected");
    whileExpression.done(OctaveElementTypes.WHILE_STATEMENT);
  }

  private void parseExpression() {
    final PsiBuilder.Marker expression = myPsiBuilder.mark();
    if (parseOrExpression()) {
      if (OctaveTokenTypes.SET_EQ_OR_OPERATION_EQ.contains(myPsiBuilder.getTokenType())) {
        feedMatches(OctaveTokenTypes.SET_EQ_OR_OPERATION_EQ, "Error: eq expected");
        parseOrExpression();
      }
      if (OctaveTokenTypes.SET_LEFT_BRACKETS.contains(myPsiBuilder.getTokenType())) {
        parseExpression();
      }
      if(isNullOrMatches(OctaveTokenTypes.SET_RITHT_BRACKETS)){
        expression.done(OctaveElementTypes.EXPRESSION);
        return;
      }
      checkMatches(OctaveTokenTypes.SET_END_STATEMENT, "end statement expected");
      expression.done(OctaveElementTypes.EXPRESSION);
      return;
    }
    expression.drop();
    myPsiBuilder.advanceLexer();
    myPsiBuilder.error("Expression expected");
  }

  private boolean parseOrExpression() {
    PsiBuilder.Marker orExpression = myPsiBuilder.mark();
    if (parseAndExpression()) {
      while (OctaveTokenTypes.SET_OR_OPERATIONS.contains(myPsiBuilder.getTokenType())) {
        feedMatches(OctaveTokenTypes.SET_OR_OPERATIONS, "Error: or operation");
        if (!parseAndExpression()) {
          myPsiBuilder.error("Expression expected");
        }
        orExpression.done(OctaveElementTypes.BINARY_EXPRESSION);
        orExpression = orExpression.precede();
      }
      orExpression.drop();
      return true;
    }
    else {
      orExpression.drop();
      return false;
    }
  }

  private boolean parseAndExpression() {
    PsiBuilder.Marker andExpression = myPsiBuilder.mark();

    if (parseNotExpression()) {

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
    else {
      andExpression.drop();
      return false;
    }
  }

  private boolean parseNotExpression() {
    if (myPsiBuilder.getTokenType() == OctaveTokenTypes.NOT) {
      final PsiBuilder.Marker notExpression = myPsiBuilder.mark();
      feedMatches(OctaveTokenTypes.NOT, "Error: not");
      if (parseNotExpression()) {
        notExpression.done(OctaveElementTypes.PREFIX_EXPRESSION);
        return true;
      }

      myPsiBuilder.error(EXPRESSION_EXPECTED);
      return true;
    }
    else {
      return parseComparisonExpression();
    }
  }

  private boolean parseComparisonExpression() {
    PsiBuilder.Marker comparisonExpression = myPsiBuilder.mark();
    if (parsePlusMinusExpression()) {
      //skipLineBreak();
      while (OctaveTokenTypes.COMPARISON_OPERATIONS.contains(myPsiBuilder.getTokenType())) {
        feedMatches(OctaveTokenTypes.COMPARISON_OPERATIONS, "Error: comparison");
        if (!parsePlusMinusExpression()) {
          myPsiBuilder.error(EXPRESSION_EXPECTED);
        }
        comparisonExpression.done(OctaveElementTypes.BINARY_EXPRESSION);
        comparisonExpression = comparisonExpression.precede();
      }
      comparisonExpression.drop();
      return true;
    }
    else {
      myPsiBuilder.error(EXPRESSION_EXPECTED);
      comparisonExpression.drop();
      return false;
    }
  }

  private boolean parsePlusMinusExpression() {
    PsiBuilder.Marker plusMinusExpression = myPsiBuilder.mark();
    if (!parseMultDivExpression()) {
      plusMinusExpression.drop();
      return false;
    }
    //skipLineBreak();
    while (OctaveTokenTypes.PLUS_MINUS_OPERATIONS.contains(myPsiBuilder.getTokenType())) {
      feedMatches(OctaveTokenTypes.PLUS_MINUS_OPERATIONS, "Error: plus_minus operation");
      if (!parseMultDivExpression()) {
        myPsiBuilder.error(EXPRESSION_EXPECTED);
      }
      plusMinusExpression.done(OctaveElementTypes.BINARY_EXPRESSION);
      plusMinusExpression = plusMinusExpression.precede();
    }

    plusMinusExpression.drop();
    return true;
  }

  private boolean parseMultDivExpression() {
    PsiBuilder.Marker multDivExpression = myPsiBuilder.mark();
    if (!parseSliceExpression()) {
      multDivExpression.drop();
      return false;
    }
    //skipLineBreak();
    while (OctaveTokenTypes.MULT_DIV_OPERATIONS.contains(myPsiBuilder.getTokenType())) {
      feedMatches(OctaveTokenTypes.MULT_DIV_OPERATIONS, "Error: mult_div operation");
      if (!parseSliceExpression()) {
        myPsiBuilder.error(EXPRESSION_EXPECTED);
      }
      multDivExpression.done(OctaveElementTypes.BINARY_EXPRESSION);
      multDivExpression = multDivExpression.precede();
    }

    multDivExpression.drop();
    return true;
  }

  private boolean parseSliceExpression() {
    PsiBuilder.Marker sliceExpression = myPsiBuilder.mark();
    if (!parseUnaryExpression()) {
      sliceExpression.drop();
      return false;
    }
    //skipLineBreak();
    while (OctaveTokenTypes.COLON == myPsiBuilder.getTokenType()) {
      feedMatches(OctaveTokenTypes.COLON, "Error: colon");
      if (!parseUnaryExpression()) {
        myPsiBuilder.error(EXPRESSION_EXPECTED);
      }
      sliceExpression.done(OctaveElementTypes.SLICE_EXPRESSION);
      sliceExpression = sliceExpression.precede();
    }

    sliceExpression.drop();
    return true;
  }

  private boolean parseUnaryExpression() {
    if (OctaveTokenTypes.UNARY_OPERATIONS.contains(myPsiBuilder.getTokenType())) {
      final PsiBuilder.Marker unaryEsprassion = myPsiBuilder.mark();
      feedMatches(OctaveTokenTypes.UNARY_OPERATIONS, "Error: unary operation");
      if (!parseUnaryExpression()) {
        myPsiBuilder.error(EXPRESSION_EXPECTED);
      }
      unaryEsprassion.done(OctaveElementTypes.PREFIX_EXPRESSION);
      return true;
    }
    else {
      return parsePowerExpression();
    }
  }

  private boolean parsePowerExpression() {
    PsiBuilder.Marker powerExpression = myPsiBuilder.mark();
    if (!parseMemberExpression()) {
      powerExpression.drop();
      return false;
    }
    //skipLineBreak();
    if (OctaveTokenTypes.POWER == (myPsiBuilder.getTokenType())) {
      feedMatches(OctaveTokenTypes.POWER, "Error: power");
      if (!parseUnaryExpression()) {
        myPsiBuilder.error(EXPRESSION_EXPECTED);
      }
      powerExpression.done(OctaveElementTypes.BINARY_EXPRESSION);
    }
    else {
      powerExpression.drop();
    }
    return true;
  }

  private boolean parseMemberExpression() {
    PsiBuilder.Marker memberExpression = myPsiBuilder.mark();
    if (!parsePrimaryExpression()) {
      memberExpression.drop();
      return false;
    }
    memberExpression.drop();
    return true;
  }

  private boolean parsePrimaryExpression() {
    if (myPsiBuilder == null) {
      return false;
    }
    IElementType currentToken = myPsiBuilder.getTokenType();
    if (currentToken == OctaveTokenTypes.IDENTIFIER) {
      buildTokenElement(OctaveElementTypes.IDENTIDIER);
      return true;
    }
    if (currentToken == OctaveTokenTypes.INTEGER_LITERAL) {
      buildTokenElement(OctaveElementTypes.INTEGER_LITERAL);
      return true;
    }
    if (currentToken == OctaveTokenTypes.COMPLEX_LITERAL) {
      buildTokenElement(OctaveElementTypes.COMPLEX_LITERAL);
      return true;
    }
    if (currentToken == OctaveTokenTypes.HEX_INTEGER) {
      buildTokenElement(OctaveElementTypes.HEX_INTEGER);
      return true;
    }
    if (currentToken == OctaveTokenTypes.FLOAT_NUMBER_LITERAL) {
      buildTokenElement(OctaveElementTypes.FLOAT_NUMBER_LITERAL);
      return true;
    }
    if (OctaveTokenTypes.SET_CONST.contains(currentToken)) {
      buildTokenElement(OctaveElementTypes.CONST);
      return true;
    }
    if (currentToken == OctaveTokenTypes.LPAR) {
      parseParExpression();
      return true;
    }
    if (currentToken == OctaveTokenTypes.LBRACKET) {
      parseBracketExpression();
      return true;
    }
    if (currentToken == OctaveTokenTypes.LBRACE) {
      parseBraceExpression();
      return true;
    }

    myPsiBuilder.advanceLexer();
    return false;
  }

  private void parseBraceExpression() {
    final PsiBuilder.Marker bracketExpression = myPsiBuilder.mark();
    feedMatches(OctaveTokenTypes.LBRACE, "Error: left brace");
    while (!isNullOrMatches(OctaveTokenTypes.RBRACE)) {
      parseExpressionStatement();
    }
    checkMatches(OctaveTokenTypes.RBRACE, "} expected");
    bracketExpression.done(OctaveElementTypes.BRACE_EXPRESSION);
  }

  private void parseBracketExpression() {
    final PsiBuilder.Marker bracketExpression = myPsiBuilder.mark();
    feedMatches(OctaveTokenTypes.LBRACKET, "Error: left bracket");
    while (!isNullOrMatches(OctaveTokenTypes.RBRACKET)) {
      parseExpressionStatement();
    }
    checkMatches(OctaveTokenTypes.RBRACKET, "] expected");
    bracketExpression.done(OctaveElementTypes.BRACKET_EXPRESSION);
  }

  private void parseParExpression() {
    final PsiBuilder.Marker bracketExpression = myPsiBuilder.mark();
    feedMatches(OctaveTokenTypes.LPAR, "Error: right par");
    while (!isNullOrMatches(OctaveTokenTypes.RPAR)) {
      parseExpressionStatement();
    }
    checkMatches(OctaveTokenTypes.RPAR, ") expected");
    bracketExpression.done(OctaveElementTypes.PAR_EXPRESSION);
  }

  private void parseParforStatement() {
    final PsiBuilder.Marker parforExpression = myPsiBuilder.mark();
    feedMatches(OctaveTokenTypes.PARFOR_KEYWORD, "Error: parfor");
    parseForEnumerateExpression();
    while (!isNullOrMatches(OctaveTokenTypes.SET_ENDPARFOR_KEYWORDS)) {
      parseExpressionStatement();
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
    //skipLineBreak();
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
    //skipLineBreak();
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
