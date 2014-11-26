package ru.compscicenter.jetbrains.octave.lexer;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;

import java.util.Stack;

%%

%class OctaveLexer
%implements FlexLexer
%unicode
%function advance
%type IElementType
%eof{  return;
%eof}

CRLF= \n|\r|\r\n

WHITE_SPACE=[\ \t\f]

END_OF_LINE_COMMENT=(("%"|"#")[^{\r\n]+[^\r\n]*)|("%\n")|("#\n")
COMMENT_BLOCK = "#{" [^#] ~"#}" | "#{" "#"+ "}"
COMMENT_BLOCK_2 = "%{" [^%] ~"%}" | "%{" "%"+ "}"

LETTER = [a-zA-Z]|[:unicode_uppercase_letter:]|[:unicode_lowercase_letter:]|[:unicode_titlecase_letter:]|[:unicode_modifier_letter:]|[:unicode_other_letter:]|[:unicode_letter_number:]

IDENT_START = {LETTER}|"_"
IDENT_CONTINUE = {LETTER}|[0-9_]
IDENTIFIER = {IDENT_START}{IDENT_CONTINUE}*

// string constants
TWO_SINGLE_QUOTE_CHARACTERS = \'\'
TWO_DOUBLE_QUOTE_CHARACTERS = \"\"
QUOTED_LITERAL="'"({TWO_SINGLE_QUOTE_CHARACTERS}|[^\\\'\r\n]|{ANY_ESCAPE_SEQUENCE})*?("'")
DOUBLE_QUOTED_LITERAL=\"([^\\\"\r\n]|{TWO_DOUBLE_QUOTE_CHARACTERS}|{ANY_ESCAPE_SEQUENCE})*?(\")
ANY_ESCAPE_SEQUENCE = \\[^]
STRING=({QUOTED_LITERAL} | {DOUBLE_QUOTED_LITERAL})

// numeric constants
DIGIT = [0-9]
NONZERO_DIGIT = [1-9]
HEX_DIGIT = [0-9A-Fa-f]
OCT_DIGIT = [0-7]
NONZERO_OCT_DIGIT = [1-7]
HEX_INTEGER = 0[Xx]({HEX_DIGIT})+
DECIMAL_INTEGER = (({NONZERO_DIGIT}({DIGIT})*)|0)
INTEGER = {DECIMAL_INTEGER}|{HEX_INTEGER}

INT_PART = ({DIGIT})+
FRACTION = \.({DIGIT})+
EXPONENT = [eE][+\-]?({DIGIT})+
BINARY_EXPONENT = [pP][+\-]?({DIGIT})+
POINT_FLOAT=(({INT_PART})?{FRACTION})|({INT_PART}\.)
EXPONENT_HEX = ({HEX_INTEGER}|({HEX_INTEGER}{FRACTION})){BINARY_EXPONENT}
EXPONENT_FLOAT=(({INT_PART})|({POINT_FLOAT})){EXPONENT}
FLOAT_NUMBER=({POINT_FLOAT})|({EXPONENT_FLOAT})|({EXPONENT_HEX})

COMPLEX_NUMBER=(({FLOAT_NUMBER})|({INT_PART}))+[iI]

NEXT_LINE = [\n]
SPASE = [\ ]

%{
private IElementType getTypeOrIdentifier(IElementType typeConstWord) {
  if (zzCurrentPos - 1 < 0 || (zzCurrentPos - 1 >= 0 && zzBuffer.charAt(zzCurrentPos - 1) != '.')) {
    return typeConstWord;
  }
  else {
    return OctaveTokenTypes.IDENTIFIER;
  }
}

private Stack<IElementType> myExpectedBracketsStack = new Stack<>();

%}

%%

<YYINITIAL> {
{COMMENT_BLOCK}             { return OctaveTokenTypes.COMMENT; }
{COMMENT_BLOCK_2}           { return OctaveTokenTypes.COMMENT; }
{END_OF_LINE_COMMENT}       { return OctaveTokenTypes.COMMENT; }

"..."{WHITE_SPACE}*"\n"     { return OctaveTokenTypes.SPACE; }

{NEXT_LINE}*                { return OctaveTokenTypes.LINE_BREAK; }
{SPASE}*                    { return OctaveTokenTypes.SPACE; }
[\t]                        { return OctaveTokenTypes.TAB; }
[\f]                        { return OctaveTokenTypes.FORMFEED; }

{STRING}                    {if (!myExpectedBracketsStack.empty()
                                  && (myExpectedBracketsStack.peek() == OctaveTokenTypes.LBRACKET
                                  || myExpectedBracketsStack.peek() == OctaveTokenTypes.LBRACE)) {
                                if (zzCurrentPos - 1 >= 0 && zzBuffer.charAt(zzCurrentPos - 1) == ' ') {
                                  return OctaveTokenTypes.STRING;
                                }
                              }
                              for (int i = zzCurrentPos - 1; i >= 0; i--) {
                                if (zzBuffer.charAt(i) == '\n') {
                                  return OctaveTokenTypes.STRING;
                                }
                                if (('a' <= zzBuffer.charAt(i) && zzBuffer.charAt(i) <= 'z')
                                    || ('A' <= zzBuffer.charAt(i) && zzBuffer.charAt(i) <= 'Z')
                                    || zzBuffer.charAt(i) == ')'
                                    || zzBuffer.charAt(i) == '}'
                                    || zzBuffer.charAt(i) == ']'
                                  ) {
                                  zzMarkedPos = zzCurrentPos + 1;

                                  return OctaveTokenTypes.APOSTROPHE;
                                }
                                if (zzBuffer.charAt(i) == '='
                                    || zzBuffer.charAt(i) == '<'
                                    || zzBuffer.charAt(i) == '>'
                                    || zzBuffer.charAt(i) == '('
                                    || zzBuffer.charAt(i) == '{'
                                    || zzBuffer.charAt(i) == '['
                                    || zzBuffer.charAt(i) == ','
                                    || zzBuffer.charAt(i) == ';'
                                    || zzBuffer.charAt(i) == ':'
                                  //todo add
                                  ) {
                                  break;
                                }
                              }
                              return OctaveTokenTypes.STRING;
                            }

// numeric constants
{HEX_INTEGER}               { return OctaveTokenTypes.HEX_INTEGER; }
{INTEGER}                   { return OctaveTokenTypes.INTEGER_LITERAL; }
{FLOAT_NUMBER}              { return OctaveTokenTypes.FLOAT_NUMBER_LITERAL; }
{COMPLEX_NUMBER}            { return OctaveTokenTypes.COMPLEX_LITERAL; }



"++"                        { return OctaveTokenTypes.INCREMENT; }
"--"                        { return OctaveTokenTypes.DECREMENT; }


"+="                        { return OctaveTokenTypes.OPERATION_PLUS_EQ; }
"-="                        { return OctaveTokenTypes.OPERATION_MINUS_EQ; }
"&="                        { return OctaveTokenTypes.OPERATION_AND_EQ; }
"|="                        { return OctaveTokenTypes.OPERATION_OR_EQ; }
"*="                        { return OctaveTokenTypes.OPERATION_MULT_EQ; }
"/="                        { return OctaveTokenTypes.OPERATION_DIV_EQ; }
"\\="                       { return OctaveTokenTypes.OPERATION_LEFT_DIV_EQ; }
"^="                        { return OctaveTokenTypes.OPERATION_POWER_EQ; }
"**="                       { return OctaveTokenTypes.OPERATION_POWER_EQ; }

".+="                       { return OctaveTokenTypes.OPERATION_DOT_PLUS_EQ; }
".-="                       { return OctaveTokenTypes.OPERATION_DOT_MINUS_EQ; }
".*="                       { return OctaveTokenTypes.OPERATION_DOT_MULT_EQ; }
"./="                       { return OctaveTokenTypes.OPERATION_DOT_DIV_EQ; }
".^="                       { return OctaveTokenTypes.OPERATION_DOT_POWER_EQ; }
".**="                       { return OctaveTokenTypes.OPERATION_DOT_POWER_EQ; }

//dot operation
"./"                        {return OctaveTokenTypes.DOT_DIVISION; }
".*"                        {return OctaveTokenTypes.DOT_MULTIPLICATION; }
".+"                        {return OctaveTokenTypes.DOT_PLUS; }
".-"                        {return OctaveTokenTypes.DOT_MINUS; }
".^"                        {return OctaveTokenTypes.DOT_POWER; }
".**"                        {return OctaveTokenTypes.DOT_POWER; }
".\\"                         { return OctaveTokenTypes.DOT_LEFT_DIVISION; }
// todo ".'"                         { return OctaveTokenTypes.DOT_APOSTROPHE; }



"="                         { return OctaveTokenTypes.EQ; }
";"                         { return OctaveTokenTypes.SEMICOLON; }
","                         { return OctaveTokenTypes.COMMA; }
"."                         { return OctaveTokenTypes.DOT; }

// grouping
"("                         { myExpectedBracketsStack.add(OctaveTokenTypes.LPAR);
                              return OctaveTokenTypes.LPAR; }

")"                         { if(!myExpectedBracketsStack.empty() && myExpectedBracketsStack.peek() == OctaveTokenTypes.LPAR) {
                                myExpectedBracketsStack.pop();
                              }
                              return OctaveTokenTypes.RPAR; }

"["                         { myExpectedBracketsStack.add(OctaveTokenTypes.LBRACKET);
                              return OctaveTokenTypes.LBRACKET; }

"]"                         { if(!myExpectedBracketsStack.empty() && myExpectedBracketsStack.peek() == OctaveTokenTypes.LBRACKET) {
                                myExpectedBracketsStack.pop();
                              }
                              return OctaveTokenTypes.RBRACKET; }

"{"                         { myExpectedBracketsStack.add(OctaveTokenTypes.LBRACE);
                              return OctaveTokenTypes.LBRACE; }

"}"                         { if(!myExpectedBracketsStack.empty() && myExpectedBracketsStack.peek() == OctaveTokenTypes.LBRACE) {
                                myExpectedBracketsStack.pop();
                              }
                              return OctaveTokenTypes.RBRACE; }


//arithmetic
"-"                         { return OctaveTokenTypes.MINUS; }
"+"                         { return OctaveTokenTypes.PLUS; }
"*"                         { return OctaveTokenTypes.MULTIPLICATION; }
"/"                         { return OctaveTokenTypes.DIVISION; }
"\\"                         { return OctaveTokenTypes.LEFT_DIVISION; }
"^"                         { return OctaveTokenTypes.POWER; }
"**"                        { return OctaveTokenTypes.POWER; }
"~"                         { return OctaveTokenTypes.TILDE; }
":"                         { return OctaveTokenTypes.COLON; }
"@"                         { return OctaveTokenTypes.AT; }
"'"                         { return OctaveTokenTypes.APOSTROPHE; }




// special constants
"NA"                        { return getTypeOrIdentifier(OctaveTokenTypes.NA_KEYWORD); }
"inf"                       { return getTypeOrIdentifier(OctaveTokenTypes.INF_KEYWORD); }
"Inf"                       { return getTypeOrIdentifier(OctaveTokenTypes.INF_KEYWORD); }
"NaN"                       { return getTypeOrIdentifier(OctaveTokenTypes.NAN_KEYWORD); }
"nan"                       { return getTypeOrIdentifier(OctaveTokenTypes.NAN_KEYWORD); }
"e"                         { return getTypeOrIdentifier(OctaveTokenTypes.E_KEYWORD); }
"pi"                        { return getTypeOrIdentifier(OctaveTokenTypes.PI_KEYWORD); }
"eps"                       { return getTypeOrIdentifier(OctaveTokenTypes.EPS_KEYWORD); }
"realmax"                   { return getTypeOrIdentifier(OctaveTokenTypes.REALMAX_KEYWORD); }
"realmin"                   { return getTypeOrIdentifier(OctaveTokenTypes.REALMIN_KEYWORD); }


// logical constants
"true"                      { return getTypeOrIdentifier(OctaveTokenTypes.TRUE_KEYWORD); }
"false"                     { return getTypeOrIdentifier(OctaveTokenTypes.FALSE_KEYWORD); }

// relational
"<"                         { return OctaveTokenTypes.LT; }
">"                         { return OctaveTokenTypes.GT; }
"=="                        { return OctaveTokenTypes.EQEQ; }
">="                        { return OctaveTokenTypes.GE; }
"<="                        { return OctaveTokenTypes.LE; }
"!="                        { return OctaveTokenTypes.NOTEQ; }
"~="                        { return OctaveTokenTypes.NOTEQ; }
"<>"                        { return OctaveTokenTypes.NOTEQ; }

// logical
"!"                         { return OctaveTokenTypes.NOT; }
"|"                         { return OctaveTokenTypes.OR; }
"&"                         { return OctaveTokenTypes.AND; }
"||"                        { return OctaveTokenTypes.DOUBLE_OR; }
"&&"                        { return OctaveTokenTypes.DOUBLE_AND; }


"break"                     { return getTypeOrIdentifier(OctaveTokenTypes.BREAK_KEYWORD); }
"case"                      { return getTypeOrIdentifier(OctaveTokenTypes.CASE_KEYWORD); }
"catch"                     { return getTypeOrIdentifier(OctaveTokenTypes.CATCH_KEYWORD); }
"classdef"                  { return getTypeOrIdentifier(OctaveTokenTypes.CLASSDEF_KEYWORD); }
"continue"                  { return getTypeOrIdentifier(OctaveTokenTypes.CONTINUE_KEYWORD); }
"do"                        { return getTypeOrIdentifier(OctaveTokenTypes.DO_KEYWORD); }
"else"                      { return getTypeOrIdentifier(OctaveTokenTypes.ELSE_KEYWORD); }
"elseif"                    { return getTypeOrIdentifier(OctaveTokenTypes.ELSEIF_KEYWORD); }
"end"                       {
                                if (zzCurrentPos - 1 < 0 || zzCurrentPos + 3 >= zzBuffer.length() ||
                                    (zzCurrentPos - 1 >= 0 && zzBuffer.charAt(zzCurrentPos - 1) != ':'
                                    && zzCurrentPos + 3 < zzBuffer.length() && zzBuffer.charAt(zzCurrentPos + 3) != ':')) {
                                  return getTypeOrIdentifier(OctaveTokenTypes.END_KEYWORD);
                                }
                                else {
                                  return OctaveTokenTypes.IDENTIFIER;
                                } }
"end_try_catch"             { return getTypeOrIdentifier(OctaveTokenTypes.END_TRY_CATCH_KEYWORD); }
"end_unwind_protect"        { return getTypeOrIdentifier(OctaveTokenTypes.END_UNWIND_PROTECT_KEYWORD); }
"endclassdef"               { return getTypeOrIdentifier(OctaveTokenTypes.ENDCLASSDEF_KEYWORD); }
"endenumeration"            { return getTypeOrIdentifier(OctaveTokenTypes.ENDENUMERATION_KEYWORD); }
"endfor"                    { return getTypeOrIdentifier(OctaveTokenTypes.ENDFOR_KEYWORD); }
"endfunction"               { return getTypeOrIdentifier(OctaveTokenTypes.ENDFUNCTION_KEYWORD); }
"endif"                     { return getTypeOrIdentifier(OctaveTokenTypes.ENDIF_KEYWORD); }
"endmethods"                { return getTypeOrIdentifier(OctaveTokenTypes.ENDMETHODS_KEYWORD); }
"endparfor"                 { return getTypeOrIdentifier(OctaveTokenTypes.ENDPARFOR_KEYWORD); }
"endswitch"                 { return getTypeOrIdentifier(OctaveTokenTypes.ENDSWITCH_KEYWORD); }
"endwhile"                  { return getTypeOrIdentifier(OctaveTokenTypes.ENDWHILE_KEYWORD); }
"enumeration"               { return getTypeOrIdentifier(OctaveTokenTypes.ENUMERATION_KEYWORD); }
"for"                       { return getTypeOrIdentifier(OctaveTokenTypes.FOR_KEYWORD); }
"function"                  { return getTypeOrIdentifier(OctaveTokenTypes.FUNCTION_KEYWORD); }
"if"                        { return getTypeOrIdentifier(OctaveTokenTypes.IF_KEYWORD); }
"methods"                   { return getTypeOrIdentifier(OctaveTokenTypes.METHODS_KEYWORD); }
"try"                       { return getTypeOrIdentifier(OctaveTokenTypes.TRY_KEYWORD); }
"until"                     { return getTypeOrIdentifier(OctaveTokenTypes.UNTIL_KEYWORD); }
"unwind_protect"            { return getTypeOrIdentifier(OctaveTokenTypes.UNWIND_PROTECT_KEYWORD); }
"unwind_protect_cleanup"    { return getTypeOrIdentifier(OctaveTokenTypes.UNWIND_PROTECT_CLEANUP_KEYWORD); }
"while"                     { return getTypeOrIdentifier(OctaveTokenTypes.WHILE_KEYWORD); }
"endevents"                 { return getTypeOrIdentifier(OctaveTokenTypes.ENDEVENTS_KEYWORD); }
"events"                    { return getTypeOrIdentifier(OctaveTokenTypes.EVENTS_KEYWORD); }
"endproperties"             { return getTypeOrIdentifier(OctaveTokenTypes.ENDPROPERTIES_KEYWORD); }
"properties"                { return getTypeOrIdentifier(OctaveTokenTypes.PROPERTIES_KEYWORD); }
"otherwise"                 { return getTypeOrIdentifier(OctaveTokenTypes.OTHERWISE_KEYWORD); }
"parfor"                    { return getTypeOrIdentifier(OctaveTokenTypes.PARFOR_KEYWORD); }
"switch"                    { return getTypeOrIdentifier(OctaveTokenTypes.SWITCH_KEYWORD); }
"persistent"                { return getTypeOrIdentifier(OctaveTokenTypes.PERSISTENT_KEYWORD); }
"static"                    { return getTypeOrIdentifier(OctaveTokenTypes.STATIC_KEYWORD); }

"global"                    { return getTypeOrIdentifier(OctaveTokenTypes.GLOBAL_KEYWORD); }
"return"                    { return getTypeOrIdentifier(OctaveTokenTypes.RETURN_KEYWORD); }

{IDENTIFIER}                { return OctaveTokenTypes.IDENTIFIER; }


.                           { return OctaveTokenTypes.BAD_CHARACTER; }

}
