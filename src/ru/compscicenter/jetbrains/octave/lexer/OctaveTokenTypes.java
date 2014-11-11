package ru.compscicenter.jetbrains.octave.lexer;

import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;
import ru.compscicenter.jetbrains.octave.parser.OctaveElementType;

public interface OctaveTokenTypes {

  public static final IElementType COMMENT = new OctaveElementType("COMMENT");
  public static final IElementType CRLF = new OctaveElementType("CRLF");

  public static final IElementType LINE_BREAK = new OctaveElementType("LINE_BREAK");
  public static final IElementType SPACE = new OctaveElementType("SPACE");
  public static final IElementType TAB = new OctaveElementType("TAB");
  public static final IElementType FORMFEED = new OctaveElementType("FORMFEED");


  public static final IElementType IDENTIFIER = new OctaveElementType("IDENTIFIER");

  //grouping
  public static final IElementType LPAR = new OctaveElementType("LPAR");    // (
  public static final IElementType RPAR = new OctaveElementType("RPAR");    // )
  public static final IElementType LBRACKET = new OctaveElementType("LBRACKET");    // [
  public static final IElementType RBRACKET = new OctaveElementType("RBRACKET");    // ]
  public static final IElementType LBRACE = new OctaveElementType("LBRACE");    // {
  public static final IElementType RBRACE = new OctaveElementType("RBRACE");    // }

  // special constants
  public static final IElementType NA_KEYWORD = new OctaveElementType("NA_KEYWORD");
  public static final IElementType INF_KEYWORD = new OctaveElementType("INF_KEYWORD");
  public static final IElementType NAN_KEYWORD = new OctaveElementType("NAN_KEYWORD");
  public static final IElementType E_KEYWORD = new OctaveElementType("E_KEYWORD");
  public static final IElementType PI_KEYWORD = new OctaveElementType("PI_KEYWORD");
  public static final IElementType EPS_KEYWORD = new OctaveElementType("EPS_KEYWORD");
  public static final IElementType REALMAX_KEYWORD = new OctaveElementType("REALMAX_KEYWORD");
  public static final IElementType REALMIN_KEYWORD = new OctaveElementType("REALMIN_KEYWORD");


  // logical
  public static final IElementType NOT = new OctaveElementType("NOT");    // !
  public static final IElementType OR = new OctaveElementType("OR");    // |
  public static final IElementType AND = new OctaveElementType("AND");    // &
  public static final IElementType DOUBLE_OR = new OctaveElementType("DOUBLE_OR");    // ||
  public static final IElementType DOUBLE_AND = new OctaveElementType("DOUBLE_AND");    // &&

  //true and false
  public static final IElementType TRUE_KEYWORD = new OctaveElementType("TRUE_KEYWORD");
  public static final IElementType FALSE_KEYWORD = new OctaveElementType("FALSE_KEYWORD");


  //string constants
  public static final IElementType STRING = new OctaveElementType("STRING");

  //division
  public static final IElementType COMMA = new OctaveElementType("COMMA");    // ,
  public static final IElementType DOT = new OctaveElementType("DOT");    // .
  public static final IElementType EQ = new OctaveElementType("EQ");    // =
  public static final IElementType SEMICOLON = new OctaveElementType("SEMICOLON");    // ;


  public static final IElementType INTEGER_LITERAL = new OctaveElementType("INTEGER_LITERAL");
  public static final IElementType FLOAT_NUMBER_LITERAL = new OctaveElementType("FLOAT_NUMBER_LITERAL");
  public static final IElementType COMPLEX_LITERAL = new OctaveElementType("COMPLEX_LITERAL");
  public static final IElementType HEX_INTEGER = new OctaveElementType("HEX_INTEGER");

  public static final TokenSet SET_NUMBER_LITERAL = TokenSet.create(
    INTEGER_LITERAL, FLOAT_NUMBER_LITERAL, COMPLEX_LITERAL, HEX_INTEGER
  );


  public static final IElementType DOT_DIVISION = new OctaveElementType("DOT_DIVISION");
  public static final IElementType DOT_MULTIPLICATION = new OctaveElementType("DOT_MULTIPLICATION");
  public static final IElementType DOT_PLUS = new OctaveElementType("DOT_PLUS");
  public static final IElementType DOT_MINUS = new OctaveElementType("DOT_MINUS");
  public static final IElementType DOT_POWER = new OctaveElementType("DOT_POWER");

  public static final IElementType MINUS = new OctaveElementType("MINUS");  // -
  public static final IElementType PLUS = new OctaveElementType("PLUS");  // +
  public static final IElementType MULTIPLICATION = new OctaveElementType("MULTIPLICATION");  //*
  public static final IElementType DIVISION = new OctaveElementType("DIVISION");  // /
  public static final IElementType POWER = new OctaveElementType("POWER");  // ^
  public static final IElementType TILDE = new OctaveElementType("TILDE");  // ~
  public static final IElementType COLON = new OctaveElementType("COLON");  // :
  public static final IElementType AT = new OctaveElementType("AT");  // @
  public static final IElementType APOSTROPHE = new OctaveElementType("APOSTROPHE");  // '
  public static final IElementType ALL_COLON = new OctaveElementType("ALL_COLON");  // (:)


  public static final IElementType INCREMENT = new OctaveElementType("INCREMENT");
  public static final IElementType DECREMENT = new OctaveElementType("DECREMENT");
  public static final IElementType OPERATION_PLUS_EQ = new OctaveElementType("OPERATION_PLUS_EQ");
  public static final IElementType OPERATION_MINUS_EQ = new OctaveElementType("OPERATION_MINUS_EQ");
  public static final IElementType OPERATION_MULT_EQ = new OctaveElementType("OPERATION_MULT_EQ");
  public static final IElementType OPERATION_DIV_EQ = new OctaveElementType("OPERATION_DIV_EQ");
  public static final IElementType OPERATION_POWER_EQ = new OctaveElementType("OPERATION_POWER_EQ");
  public static final IElementType OPERATION_DOT_PLUS_EQ = new OctaveElementType("OPERATION_DOT_PLUS_EQ");
  public static final IElementType OPERATION_DOT_MINUS_EQ = new OctaveElementType("OPERATION_DOT_MINUS_EQ");
  public static final IElementType OPERATION_DOT_MULT_EQ = new OctaveElementType("OPERATION_DOT_MULT_EQ");
  public static final IElementType OPERATION_DOT_DIV_EQ = new OctaveElementType("OPERATION_DOT_DIV_EQ");
  public static final IElementType OPERATION_DOT_POWER_EQ = new OctaveElementType("OPERATION_DOT_POWER_EQ");


  public static final IElementType BREAK_KEYWORD = new OctaveElementType("BREAK_KEYWORD");
  public static final IElementType CASE_KEYWORD = new OctaveElementType("CASE_KEYWORD");
  public static final IElementType CATCH_KEYWORD = new OctaveElementType("CATCH_KEYWORD");
  public static final IElementType CLASSDEF_KEYWORD = new OctaveElementType("CLASSDEF_KEYWORD");
  public static final IElementType CONTINUE_KEYWORD = new OctaveElementType("CONTINUE_KEYWORD");
  public static final IElementType DO_KEYWORD = new OctaveElementType("DO_KEYWORD");
  public static final IElementType ELSE_KEYWORD = new OctaveElementType("ELSE_KEYWORD");
  public static final IElementType ELSEIF_KEYWORD = new OctaveElementType("ELSEIF_KEYWORD");
  public static final IElementType END_KEYWORD = new OctaveElementType("END_KEYWORD");
  public static final IElementType END_TRY_CATCH_KEYWORD = new OctaveElementType("END_TRY_CATCH_KEYWORD");
  public static final IElementType END_UNWIND_PROTECT_KEYWORD = new OctaveElementType("END_UNWIND_PROTECT_KEYWORD");
  public static final IElementType ENDCLASSDEF_KEYWORD = new OctaveElementType("ENDCLASSDEF_KEYWORD");
  public static final IElementType ENDENUMERATION_KEYWORD = new OctaveElementType("ENDENUMERATION_KEYWORD");
  public static final IElementType ENDEVENTS_KEYWORD = new OctaveElementType("ENDEVENTS_KEYWORD");
  public static final IElementType ENDFOR_KEYWORD = new OctaveElementType("ENDFOR_KEYWORD");
  public static final IElementType ENDFUNCTION_KEYWORD = new OctaveElementType("ENDFUNCTION_KEYWORD");
  public static final IElementType ENDIF_KEYWORD = new OctaveElementType("ENDIF_KEYWORD");
  public static final IElementType ENDMETHODS_KEYWORD = new OctaveElementType("ENDMETHODS_KEYWORD");
  public static final IElementType ENDPARFOR_KEYWORD = new OctaveElementType("ENDPARFOR_KEYWORD");
  public static final IElementType ENDPROPERTIES_KEYWORD = new OctaveElementType("ENDPROPERTIES_KEYWORD");
  public static final IElementType ENDSWITCH_KEYWORD = new OctaveElementType("ENDSWITCH_KEYWORD");
  public static final IElementType ENDWHILE_KEYWORD = new OctaveElementType("ENDWHILE_KEYWORD");
  public static final IElementType ENUMERATION_KEYWORD = new OctaveElementType("ENUMERATION_KEYWORD");
  public static final IElementType EVENTS_KEYWORD = new OctaveElementType("EVENTS_KEYWORD");
  public static final IElementType FOR_KEYWORD = new OctaveElementType("FOR_KEYWORD");
  public static final IElementType FUNCTION_KEYWORD = new OctaveElementType("FUNCTION_KEYWORD");
  public static final IElementType GLOBAL_KEYWORD = new OctaveElementType("GLOBAL_KEYWORD");
  public static final IElementType IF_KEYWORD = new OctaveElementType("IF_KEYWORD");
  public static final IElementType METHODS_KEYWORD = new OctaveElementType("METHODS_KEYWORD");
  public static final IElementType OTHERWISE_KEYWORD = new OctaveElementType("OTHERWISE_KEYWORD");
  public static final IElementType PARFOR_KEYWORD = new OctaveElementType("PARFOR_KEYWORD");
  public static final IElementType PERSISTENT_KEYWORD = new OctaveElementType("PERSISTENT_KEYWORD");
  public static final IElementType PROPERTIES_KEYWORD = new OctaveElementType("PROPERTIES_KEYWORD");
  public static final IElementType RETURN_KEYWORD = new OctaveElementType("RETURN_KEYWORD");
  public static final IElementType STATIC_KEYWORD = new OctaveElementType("STATIC_KEYWORD");
  public static final IElementType SWITCH_KEYWORD = new OctaveElementType("SWITCH_KEYWORD");
  public static final IElementType TRY_KEYWORD = new OctaveElementType("TRY_KEYWORD");
  public static final IElementType UNTIL_KEYWORD = new OctaveElementType("UNTIL_KEYWORD");
  public static final IElementType UNWIND_PROTECT_KEYWORD = new OctaveElementType("UNWIND_PROTECT_KEYWORD");
  public static final IElementType UNWIND_PROTECT_CLEANUP_KEYWORD = new OctaveElementType("UNWIND_PROTECT_CLEANUP_KEYWORD");
  public static final IElementType WHILE_KEYWORD = new OctaveElementType("WHILE_KEYWORD");

  // relational
  public static final IElementType LT = new OctaveElementType("LT");
  public static final IElementType GT = new OctaveElementType("GT");
  public static final IElementType EQEQ = new OctaveElementType("EQEQ");
  public static final IElementType GE = new OctaveElementType("GE");
  public static final IElementType LE = new OctaveElementType("LE");
  public static final IElementType NOTEQ = new OctaveElementType("NOTEQ");

  public static final IElementType BAD_CHARACTER = TokenType.BAD_CHARACTER;

  public static final TokenSet SET_ENDIF_KEYWORDS = TokenSet.create(ENDIF_KEYWORD, END_KEYWORD);
  public static final TokenSet SET_ENDFOR_KEYWORDS = TokenSet.create(ENDFOR_KEYWORD, END_KEYWORD);
  public static final TokenSet SET_ENDWHILE_KEYWORDS = TokenSet.create(ENDWHILE_KEYWORD, END_KEYWORD);
  public static final TokenSet SET_ENDSWITCH_KEYWORDS = TokenSet.create(END_KEYWORD, ENDSWITCH_KEYWORD);
  public static final TokenSet SET_ENDUNWIND_KEYWORDS = TokenSet.create(END_KEYWORD, END_UNWIND_PROTECT_KEYWORD);
  public static final TokenSet SET_ENDTRY_KEYWORDS = TokenSet.create(END_KEYWORD, END_TRY_CATCH_KEYWORD);
  public static final TokenSet SET_ENDFUNCTION_KEYWORDS = TokenSet.create(END_KEYWORD, ENDFUNCTION_KEYWORD);
  public static final TokenSet SET_ENDPARFOR_KEYWORDS = TokenSet.create(ENDPARFOR_KEYWORD, END_KEYWORD);
  public static final TokenSet SET_ENDCLASSDEF_KEYWORDS = TokenSet.create(ENDCLASSDEF_KEYWORD, END_KEYWORD);
  public static final TokenSet SET_ENDMETHODS_KEYWORDS = TokenSet.create(ENDMETHODS_KEYWORD, END_KEYWORD);
  public static final TokenSet SET_ENDENUMERATION_KEYWORDS = TokenSet.create(ENDENUMERATION_KEYWORD, END_KEYWORD);
  public static final TokenSet SET_ENDPROPERTIES_KEYWORDS = TokenSet.create(ENDPROPERTIES_KEYWORD, END_KEYWORD);
  public static final TokenSet SET_ENDEVENTS_KEYWORDS = TokenSet.create(ENDEVENTS_KEYWORD, END_KEYWORD);

  public static final TokenSet SET_INNER_KEYWORDS = TokenSet.create(
    CASE_KEYWORD, CATCH_KEYWORD, ELSE_KEYWORD, ELSEIF_KEYWORD,
    UNWIND_PROTECT_CLEANUP_KEYWORD, UNTIL_KEYWORD, OTHERWISE_KEYWORD,
    RPAR, RBRACKET, RBRACE);
  public static final TokenSet SET_END_KEYWORDS = TokenSet.create(
    END_KEYWORD, END_TRY_CATCH_KEYWORD, END_UNWIND_PROTECT_KEYWORD, ENDCLASSDEF_KEYWORD,
    ENDENUMERATION_KEYWORD, ENDEVENTS_KEYWORD, ENDFOR_KEYWORD, ENDFUNCTION_KEYWORD,
    ENDIF_KEYWORD, ENDMETHODS_KEYWORD, ENDPARFOR_KEYWORD, ENDPROPERTIES_KEYWORD,
    ENDSWITCH_KEYWORD);

  public static final TokenSet SET_PUNCTUATION = TokenSet.create(COMMA, SEMICOLON, DOT, CRLF);
  public static final TokenSet SET_SPACES = TokenSet.create(SPACE, TAB, FORMFEED, LINE_BREAK);
  public static final TokenSet SET_END_EXPRESSION = TokenSet.create(COMMA, SEMICOLON, CRLF, LINE_BREAK);
  public static final TokenSet SET_END_EXPRESSION_IN_BRACKETS = TokenSet.orSet(SET_END_EXPRESSION, SET_NUMBER_LITERAL);
  public static final TokenSet SET_END_EXPRESSION_IN_BRACES = TokenSet.orSet(SET_END_EXPRESSION, SET_NUMBER_LITERAL);
  public static final TokenSet SET_END_EXPRESSION_IN_PARS = TokenSet.create(COMMA, CRLF, LINE_BREAK);
  public static final TokenSet SET_CASE_OR_OTHERWISE = TokenSet.create(CASE_KEYWORD, OTHERWISE_KEYWORD);

  public static final TokenSet SET_EQ_OR_OPERATION_EQ = TokenSet.create(
    EQ, OPERATION_PLUS_EQ, OPERATION_MINUS_EQ, OPERATION_MULT_EQ, OPERATION_DIV_EQ, OPERATION_POWER_EQ,
    OPERATION_DOT_PLUS_EQ, OPERATION_DOT_MINUS_EQ, OPERATION_DOT_MULT_EQ, OPERATION_DOT_DIV_EQ, OPERATION_DOT_POWER_EQ);
  public static final TokenSet SET_OR_OPERATIONS = TokenSet.create(OR, DOUBLE_OR);
  public static final TokenSet SET_AND_OPERATIONS = TokenSet.create(AND, DOUBLE_AND);
  public static final TokenSet COMPARISON_OPERATIONS = TokenSet.create(LT, GT, EQEQ, GE, LE, NOTEQ);
  public static final TokenSet PLUS_MINUS_OPERATIONS = TokenSet.create(
    PLUS, MINUS, DOT_PLUS, DOT_MINUS);
  public static final TokenSet POWER_OPERATIONS = TokenSet.create(POWER, DOT_POWER);
  public static final TokenSet MULT_DIV_OPERATIONS = TokenSet.create(
    MULTIPLICATION, DIVISION, DOT_MULTIPLICATION, DOT_DIVISION);
  public static final TokenSet UNARY_OPERATIONS = TokenSet.create(PLUS, MINUS);
  public static final TokenSet SET_CONST = TokenSet.create(
    E_KEYWORD, INF_KEYWORD, NAN_KEYWORD, NA_KEYWORD, PI_KEYWORD, EPS_KEYWORD, REALMAX_KEYWORD, REALMIN_KEYWORD);
  public static final TokenSet SET_RITHT_BRACKETS = TokenSet.create(RPAR, RBRACE, RBRACKET);
  public static final TokenSet SET_LEFT_BRACKETS = TokenSet.create(LPAR, LBRACE, LBRACKET);
}