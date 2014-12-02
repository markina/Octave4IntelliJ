package com.jetbrains.octave.parser;

import com.intellij.lang.PsiBuilder;
import com.intellij.psi.tree.IElementType;
import com.jetbrains.octave.lexer.OctaveTokenTypes;

public class OctaveExpressionParsing extends OctaveParsing {




  public OctaveExpressionParsing(OctaveParserContext context) {
    super(context);
  }

  public boolean parseExpression() {
    //skipLineBreak();
    final PsiBuilder.Marker expression = myPsiBuilder.mark();

    if (myPsiBuilder.getTokenType() == OctaveTokenTypes.GLOBAL_KEYWORD) {
      feedMatches(OctaveTokenTypes.GLOBAL_KEYWORD, "Error: global");
    }
    if (myPsiBuilder.getTokenType() == OctaveTokenTypes.PERSISTENT_KEYWORD) {
      feedMatches(OctaveTokenTypes.PERSISTENT_KEYWORD, "Error: persistent");
    }
    if (parseAnonymousFunctionExpression()) {
      if (OctaveTokenTypes.SET_EQ_OR_OPERATION_EQ.contains(myPsiBuilder.getTokenType())) {
        feedMatches(OctaveTokenTypes.SET_EQ_OR_OPERATION_EQ, "Error: eq expected");
        parseAnonymousFunctionExpression();
        expression.done(OctaveElementTypes.ASSIGNMENT_STATEMENT);
        return true;
      }
      if (numberOfNesting == 0) {
        checkMatches(OctaveTokenTypes.SET_END_EXPRESSION, END_EXPRESSION_EXPECTED);
        expression.drop();
        skipLineBreak();
        return true;
      }
      expression.drop();
      return true;
    }
    expression.drop();
    myPsiBuilder.error(EXPRESSION_EXPECTED);
    myPsiBuilder.advanceLexer();
    return false;
  }


  private boolean parseAnonymousFunctionExpression() {
    if (myPsiBuilder.getTokenType() != OctaveTokenTypes.AT) {
      if(!parseOrExpression()) {
        myPsiBuilder.error(EXPRESSION_EXPECTED);
        myPsiBuilder.advanceLexer();
        return false;
      }
      else {
        return true;
      }
    }
    PsiBuilder.Marker anonymousFunctionExpression = myPsiBuilder.mark();
    feedMatches(OctaveTokenTypes.AT, "Error: at");


    if(myPsiBuilder.getTokenType() == OctaveTokenTypes.IDENTIFIER) {
      buildTokenElement(OctaveElementTypes.REFERENCE_EXPRESSION);
      anonymousFunctionExpression.done(OctaveElementTypes.ANONYMOUS_FUNCTION);
      return true;
    }
    if(myPsiBuilder.getTokenType() == OctaveTokenTypes.LPAR) {
      parseParExpression();
    }
    if(!OctaveTokenTypes.SET_END_EXPRESSION.contains(myPsiBuilder.getTokenType())) {
      if(!parseOrExpression()) {
        anonymousFunctionExpression.drop();
        myPsiBuilder.error("Expression anonymous functions");
        myPsiBuilder.advanceLexer();
        return false;
      }
    }
    anonymousFunctionExpression.done(OctaveElementTypes.ANONYMOUS_FUNCTION);
    return true;
  }

  private boolean parseOrExpression() {
    PsiBuilder.Marker orExpression = myPsiBuilder.mark();
    if (parseAndExpression()) {
      while (OctaveTokenTypes.SET_OR_OPERATIONS.contains(myPsiBuilder.getTokenType())) {
        feedMatches(OctaveTokenTypes.SET_OR_OPERATIONS, "Error: or operation");
        if (!parseAndExpression()) {
          myPsiBuilder.error(EXPRESSION_EXPECTED);
        }
        orExpression.done(OctaveElementTypes.BINARY_EXPRESSION);
        orExpression = orExpression.precede();
      }
      orExpression.done(OctaveElementTypes.EXPRESSION);
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
          myPsiBuilder.error(EXPRESSION_EXPECTED);
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

    if (myPsiBuilder.getTokenType() != OctaveTokenTypes.COLON) {
      if (!parseUnaryExpression()) {
        sliceExpression.drop();
        return false;
      }
      if (myPsiBuilder.getTokenType() != OctaveTokenTypes.COLON) {
        sliceExpression.drop();
        return true;
      }
    }

    //skipLineBreak();
    while (OctaveTokenTypes.COLON == myPsiBuilder.getTokenType()) {
      feedMatches(OctaveTokenTypes.COLON, "Error: colon");
      parseUnaryExpression();
    }

    sliceExpression.done(OctaveElementTypes.SLICE_EXPRESSION);
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
    if (!parsePrimaryExpression()) {
      powerExpression.drop();
      return false;
    }
    skipApostrophe();
    if (OctaveTokenTypes.POWER_OPERATIONS.contains(myPsiBuilder.getTokenType())) {
      feedMatches(OctaveTokenTypes.POWER_OPERATIONS, "Error: power");
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

  public boolean parsePrimaryExpression() {
    if (myPsiBuilder == null) {
      return false;
    }
    IElementType currentToken = myPsiBuilder.getTokenType();

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
    if (OctaveTokenTypes.STRING == (currentToken)) {
      buildTokenElement(OctaveElementTypes.STRING);
      return true;
    }
    if (OctaveTokenTypes.BOOLEAN_WORD.contains(currentToken)) {
      buildTokenElement(OctaveElementTypes.BOOLEAN_LITERAL);
      return true;
    }
    if(parseIdentifier()) {
      while(numberOfNesting == 0 && myPsiBuilder.getTokenType() == OctaveTokenTypes.IDENTIFIER) {
        parseIdentifier();
      }
      return true;
    }
    return false;
  }

  private boolean parseIdentifier() {
    if (myPsiBuilder.getTokenType() == OctaveTokenTypes.IDENTIFIER) {
      buildTokenElement(OctaveElementTypes.REFERENCE_EXPRESSION);

      if (myPsiBuilder.getTokenType() == OctaveTokenTypes.DOT) {
        if (parseDotOperator()) {
          skipIncrementDecrement();
          skipApostrophe();
          parseInBracketsExpression();
          return true;
        }
        else {
          return false;
        }
      }
      skipIncrementDecrement();
      skipApostrophe();
      parseInBracketsExpression();
      return true;
    }
    return parseInBracketsExpression();
  }

  private boolean parseDotOperator() {
    feedMatches(OctaveTokenTypes.DOT, "Error: dot");
    return parseIdentifier();
  }

  private boolean parseInBracketsExpression() {
    if (!OctaveTokenTypes.SET_LEFT_BRACKETS.contains(myPsiBuilder.getTokenType())) {
      return false;
    }

    while (OctaveTokenTypes.SET_LEFT_BRACKETS.contains(myPsiBuilder.getTokenType())) {
      if (myPsiBuilder.getTokenType() == OctaveTokenTypes.LPAR) {
        if(!parseParExpression()) {
          return false;
        }

      }
      else if (myPsiBuilder.getTokenType() == OctaveTokenTypes.LBRACKET) {
        if(!parseBracketExpression()) {
          return false;
        }
      }
      else if (myPsiBuilder.getTokenType() == OctaveTokenTypes.LBRACE) {
        if(!parseBraceExpression()) {
          return false;
        }
      }
      if (myPsiBuilder.getTokenType() == OctaveTokenTypes.DOT) {
        if (parseDotOperator()) {
          skipIncrementDecrement();
          skipApostrophe();
          if(!parseInBracketsExpression()){
            return false;
          }

        }
        else {
          return false;
        }
      }
      skipApostrophe();
    }
    return true;
  }

  private boolean parseBraceExpression() {
    final PsiBuilder.Marker bracketExpression = myPsiBuilder.mark();
    numberOfNesting++;
    feedMatches(OctaveTokenTypes.LBRACE, "Error: left brace");
    skipLineBreak();
    while (!isNullOrMatches(OctaveTokenTypes.RBRACE)) {
      parseAnonymousFunctionExpression();
      if (!isNullOrMatches(OctaveTokenTypes.RBRACE)
          && !isNullOrMatches(OctaveTokenTypes.IDENTIFIER)
          && !isNullOrMatches(OctaveTokenTypes.SET_LEFT_BRACKETS)
          && !OctaveTokenTypes.SET_STRING_NUMBER_LITERAL.contains(myPsiBuilder.getTokenType())) {
        checkMatches(OctaveTokenTypes.SET_END_EXPRESSION, END_EXPRESSION_EXPECTED);
      }
      skipLineBreak();
    }
    checkMatches(OctaveTokenTypes.RBRACE, "} expected");

    numberOfNesting--;
    bracketExpression.done(OctaveElementTypes.BRACE_EXPRESSION);
    return true;
  }

  private boolean parseBracketExpression() {
    final PsiBuilder.Marker bracketExpression = myPsiBuilder.mark();
    numberOfNesting++;
    feedMatches(OctaveTokenTypes.LBRACKET, "Error: left bracket");
    skipLineBreak();
    while (!isNullOrMatches(OctaveTokenTypes.RBRACKET)) {
      parseAnonymousFunctionExpression();
      if (!isNullOrMatches(OctaveTokenTypes.RBRACKET)
          && !isNullOrMatches(OctaveTokenTypes.IDENTIFIER)
          && !isNullOrMatches(OctaveTokenTypes.SET_LEFT_BRACKETS)
          && !OctaveTokenTypes.SET_STRING_NUMBER_LITERAL.contains(myPsiBuilder.getTokenType())) {
        checkMatches(OctaveTokenTypes.SET_END_EXPRESSION, END_EXPRESSION_EXPECTED);
      }
      skipLineBreak();
    }
    checkMatches(OctaveTokenTypes.RBRACKET, "] expected");
    numberOfNesting--;
    bracketExpression.done(OctaveElementTypes.BRACKET_EXPRESSION);
    return true;
  }

  private boolean parseParExpression() {
    final PsiBuilder.Marker bracketExpression = myPsiBuilder.mark();
    numberOfNesting++;
    feedMatches(OctaveTokenTypes.LPAR, "Error: left par");
    skipLineBreak();
    while (!isNullOrMatches(OctaveTokenTypes.RPAR)) {
      parseAnonymousFunctionExpression();
      if (!isNullOrMatches(OctaveTokenTypes.RPAR)
          && !isNullOrMatches(OctaveTokenTypes.IDENTIFIER)
          && !isNullOrMatches(OctaveTokenTypes.SET_LEFT_BRACKETS)) {
        checkMatches(OctaveTokenTypes.SET_END_EXPRESSION, END_EXPRESSION_EXPECTED);
      }
      skipLineBreak();
    }
    checkMatches(OctaveTokenTypes.RPAR, ") expected");
    numberOfNesting--;
    bracketExpression.done(OctaveElementTypes.PAR_EXPRESSION);
    return true;
  }
}
