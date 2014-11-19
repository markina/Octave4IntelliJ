package ru.compscicenter.jetbrains.octave.parser;

import com.intellij.lang.PsiBuilder;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;
import ru.compscicenter.jetbrains.octave.lexer.OctaveTokenTypes;

/**
 * Created by Markina Margarita on 21.10.14.
 */
public class OctaveExpressionParsing extends OctaveParsing {


  public OctaveExpressionParsing(OctaveParserContext context) {
    super(context);
  }

  public void parseExpression() {
    //skipLineBreak();
    final PsiBuilder.Marker expression = myPsiBuilder.mark();
    if (parseOrExpression()) {
      if (OctaveTokenTypes.SET_EQ_OR_OPERATION_EQ.contains(myPsiBuilder.getTokenType())) {
        feedMatches(OctaveTokenTypes.SET_EQ_OR_OPERATION_EQ, "Error: eq expected");
        parseOrExpression();
        expression.done(OctaveElementTypes.ASSIGNMENT_STATEMENT);
        return;
      }
      if (OctaveTokenTypes.SET_LEFT_BRACKETS.contains(myPsiBuilder.getTokenType())) {
        parseOrExpression();
      }
      if (isNullOrMatches(OctaveTokenTypes.SET_RITHT_BRACKETS)) {
        expression.done(OctaveElementTypes.EXPRESSION);
        return;
      }
      if (OctaveTokenTypes.SET_NUMBER_LITERAL.contains(myPsiBuilder.getTokenType())) {
        expression.done(OctaveElementTypes.EXPRESSION);
        return;
      }
      if (numberOfNesting == 0) {
        checkMatches(currentEndExpression, "end statement expected");
        expression.done(OctaveElementTypes.EXPRESSION);
        skipLineBreak();
        return;
      }
      expression.done(OctaveElementTypes.EXPRESSION);
      return;


    }
    expression.drop();
    myPsiBuilder.error("Expression expected");
    myPsiBuilder.advanceLexer();
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
    if (myPsiBuilder.getTokenType() != OctaveTokenTypes.COLON && !parseUnaryExpression()) {
      sliceExpression.drop();
      return false;
    }
    //skipLineBreak();
    while (OctaveTokenTypes.COLON == myPsiBuilder.getTokenType()) {
      feedMatches(OctaveTokenTypes.COLON, "Error: colon");
      if(myPsiBuilder.getTokenType() == OctaveTokenTypes.END_KEYWORD) {
        feedMatches(OctaveTokenTypes.END_KEYWORD, "Error: end keyword");
        continue;
      }
      parseUnaryExpression();
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

  private boolean parseMemberExpression() {
    PsiBuilder.Marker memberExpression = myPsiBuilder.mark();
    if (!parsePrimaryExpression()) {
      memberExpression.drop();
      return false;
    }

    skipApostrophe(); //todo change with string literal
    memberExpression.drop();
    return true;
  }

  private void skipIncrementDecrement() {
    if (OctaveTokenTypes.INCREMENT == myPsiBuilder.getTokenType()) {
      myPsiBuilder.advanceLexer();
    }
    else if (OctaveTokenTypes.DECREMENT == myPsiBuilder.getTokenType()) {
      myPsiBuilder.advanceLexer();
    }
  }

  public boolean parsePrimaryExpression() {
    if (myPsiBuilder == null) {
      return false;
    }
    IElementType currentToken = myPsiBuilder.getTokenType();
    if (currentToken == OctaveTokenTypes.IDENTIFIER) {
      buildTokenElement(OctaveElementTypes.REFERENCE_EXPRESSION);
      skipIncrementDecrement();
      skipApostrophe();
      parseInBracketsExpression();
      return true;
    }
    if (currentToken == OctaveTokenTypes.SIMPLE_KEYWORD) {
      feedMatches(OctaveTokenTypes.SIMPLE_KEYWORD, "Error: simple keyword");
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
    if (OctaveTokenTypes.STRING == (currentToken)) {
      buildTokenElement(OctaveElementTypes.STRING);
      return true;
    }
    if (OctaveTokenTypes.BOOLEAN_WORD.contains(currentToken)) {
      buildTokenElement(OctaveElementTypes.BOOLEAN_LITERAL);
      return true;
    }
    return parseInBracketsExpression();
  }

  private boolean parseInBracketsExpression() {
    IElementType currentToken = myPsiBuilder.getTokenType();
    if (currentToken == OctaveTokenTypes.ALL_COLON) {
      feedMatches(OctaveTokenTypes.ALL_COLON, "Error: all colon");
    }

    if (OctaveTokenTypes.SET_LEFT_BRACKETS.contains(currentToken)) {
      if (currentToken == OctaveTokenTypes.LPAR) {
        parseParExpression();
      }
      else if (currentToken == OctaveTokenTypes.LBRACKET) {
        parseBracketExpression();
      }
      else if (currentToken == OctaveTokenTypes.LBRACE) {
        parseBraceExpression();
      }

      skipApostrophe();
      return true;
    }
    return false;
  }

  private void skipApostrophe() {
    while (OctaveTokenTypes.APOSTROPHE == myPsiBuilder.getTokenType()) {
      myPsiBuilder.advanceLexer();
    }
  }

  private void parseBraceExpression() {
    final PsiBuilder.Marker bracketExpression = myPsiBuilder.mark();
    numberOfNesting++;
    TokenSet oldCurrentEndExpression = currentEndExpression;
    currentEndExpression = OctaveTokenTypes.SET_END_EXPRESSION_IN_BRACES;
    feedMatches(OctaveTokenTypes.LBRACE, "Error: left brace");
    while (!isNullOrMatches(OctaveTokenTypes.RBRACE)) {
      skipLineBreak();
      parseExpression();
      if (!isNullOrMatches(OctaveTokenTypes.RBRACE) && !isNullOrMatches(OctaveTokenTypes.IDENTIFIER)) {
        checkMatches(currentEndExpression, "end expression expected");
      }
    }
    checkMatches(OctaveTokenTypes.RBRACE, "} expected");

    currentEndExpression = oldCurrentEndExpression;
    numberOfNesting--;
    bracketExpression.done(OctaveElementTypes.BRACE_EXPRESSION);
  }

  private void parseBracketExpression() {
    final PsiBuilder.Marker bracketExpression = myPsiBuilder.mark();
    TokenSet oldCurrentEndExpression = currentEndExpression;
    currentEndExpression = OctaveTokenTypes.SET_END_EXPRESSION_IN_BRACKETS;
    numberOfNesting++;
    feedMatches(OctaveTokenTypes.LBRACKET, "Error: left bracket");
    while (!isNullOrMatches(OctaveTokenTypes.RBRACKET)) {
      skipLineBreak();
      parseExpression();
      if (!isNullOrMatches(OctaveTokenTypes.RBRACKET) && !isNullOrMatches(OctaveTokenTypes.IDENTIFIER)) {
        checkMatches(currentEndExpression, "end expression expecteed");
      }
    }
    checkMatches(OctaveTokenTypes.RBRACKET, "] expected");
    numberOfNesting--;
    currentEndExpression = oldCurrentEndExpression;
    bracketExpression.done(OctaveElementTypes.BRACKET_EXPRESSION);
  }

  private void parseParExpression() {
    final PsiBuilder.Marker bracketExpression = myPsiBuilder.mark();
    numberOfNesting++;
    TokenSet oldCurrentEndExpression = currentEndExpression;
    currentEndExpression = OctaveTokenTypes.SET_END_EXPRESSION_IN_PARS;
    feedMatches(OctaveTokenTypes.LPAR, "Error: right par");
    while (!isNullOrMatches(OctaveTokenTypes.RPAR)) {
      skipLineBreak();
      parseExpression();
      if (!isNullOrMatches(OctaveTokenTypes.RPAR) && !isNullOrMatches(OctaveTokenTypes.IDENTIFIER)) {
        checkMatches(currentEndExpression, "end expression expected in pars");
      }
    }
    checkMatches(OctaveTokenTypes.RPAR, ") expected");
    numberOfNesting--;
    currentEndExpression = oldCurrentEndExpression;
    bracketExpression.done(OctaveElementTypes.PAR_EXPRESSION);
  }
}
