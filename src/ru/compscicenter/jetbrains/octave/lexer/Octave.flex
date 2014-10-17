package ru.compscicenter.jetbrains.octave.lexer;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import ru.compscicenter.jetbrains.octave.psi.OctaveTypes;
import com.intellij.psi.TokenType;

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

END_OF_LINE_COMMENT=("%")[^\r\n]*

LETTER = [a-zA-Z]|[:unicode_uppercase_letter:]|[:unicode_lowercase_letter:]|[:unicode_titlecase_letter:]|[:unicode_modifier_letter:]|[:unicode_other_letter:]|[:unicode_letter_number:]

IDENT_START = {LETTER}|"_"
IDENT_CONTINUE = {LETTER}|[0-9_]
IDENTIFIER = {IDENT_START}{IDENT_CONTINUE}*

// string constants
TWO_SINGLE_QUOTE_CHARACTERS = \'\'
TWO_DOUBLE_QUOTE_CHARACTERS = \"\"
QUOTED_LITERAL="'"({TWO_SINGLE_QUOTE_CHARACTERS}|[^\\\']|{ANY_ESCAPE_SEQUENCE})*?("'")?
DOUBLE_QUOTED_LITERAL=\"([^\\\"]|{TWO_DOUBLE_QUOTE_CHARACTERS}|{ANY_ESCAPE_SEQUENCE})*?(\")?
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

LONG_INTEGER = ({INTEGER} | {FLOAT_NUMBER})[Ll]
COMPLEX_NUMBER=(({FLOAT_NUMBER})|({INT_PART}))[i]


%%

<YYINITIAL> {
{END_OF_LINE_COMMENT}       { return OctaveTypes.COMMENT; }

[\n]                        { return OctaveTypes.LINE_BREAK; }
[\ ]                        { return OctaveTypes.SPACE; }
[\t]                        { return OctaveTypes.TAB; }
[\f]                        { return OctaveTypes.FORMFEED; }

"="                         { return OctaveTypes.EQ; }
";"                         { return OctaveTypes.SEMICOLON; }
","                         { return OctaveTypes.COMMA; }
"."                         { return OctaveTypes.DOT; }

// grouping
"("                         { return OctaveTypes.LPAR; }
")"                         { return OctaveTypes.RPAR; }
"["                         { return OctaveTypes.LBRACKET; }
"]"                         { return OctaveTypes.RBRACKET; }
"{"                         { return OctaveTypes.LBRACE; }
"}"                         { return OctaveTypes.RBRACE; }


//arithmetic
"-"                         { return OctaveTypes.MINUS; }
"+"                         { return OctaveTypes.PLUS; }
"*"                         { return OctaveTypes.MULTIPLICATION; }
"/"                         { return OctaveTypes.DIVISION; }
"^"                         { return OctaveTypes.POWER; }
"~"                         { return OctaveTypes.TILDE; }
":"                         { return OctaveTypes.COLON; }

// special constants
"NA"                        { return OctaveTypes.NA_KEYWORD; }
"Inf"                       { return OctaveTypes.INF_KEYWORD; }
"NaN"                       { return OctaveTypes.NAN_KEYWORD; }
"e"                         { return OctaveTypes.E_KEYWORD; }
"pi"                        { return OctaveTypes.PI_KEYWORD; }
"I"                         { return OctaveTypes.I_KEYWORD; }
"eps"                       { return OctaveTypes.EPS_KEYWORD; }
"realmax"                   { return OctaveTypes.REALMAX_KEYWORD; }
"realmin"                   { return OctaveTypes.REALMIN_KEYWORD; }


// logical constants
"TRUE"                      { return OctaveTypes.TRUE_KEYWORD; }
"FALSE"                     { return OctaveTypes.FALSE_KEYWORD; }

// relational
"<"                         { return OctaveTypes.LT; }
">"                         { return OctaveTypes.GT; }
"=="                        { return OctaveTypes.EQEQ; }
">="                        { return OctaveTypes.GE; }
"<="                        { return OctaveTypes.LE; }
"!="                        { return OctaveTypes.NOTEQ; }

// logical
"!"                         { return OctaveTypes.NOT; }
"|"                         { return OctaveTypes.OR; }
"&"                         { return OctaveTypes.AND; }
"||"                        { return OctaveTypes.DOUBLE_OR; }
"&&"                        { return OctaveTypes.DOUBLE_AND; }


"break"                     { return OctaveTypes.BREAK_KEYWORD; }
"case"                      { return OctaveTypes.CASE_KEYWORD; }
"catch"                     { return OctaveTypes.CATCH_KEYWORD; }
"classdef"                  { return OctaveTypes.CLASSDEF_KEYWORD; }
"continue"                  { return OctaveTypes.CONTINUE_KEYWORD; }
"do"                        { return OctaveTypes.DO_KEYWORD; }
"else"                      { return OctaveTypes.ELSE_KEYWORD; }
"elseif"                    { return OctaveTypes.ELSEIF_KEYWORD; }
"end"                       { return OctaveTypes.END_KEYWORD; }
"end_try_catch"             { return OctaveTypes.END_TRY_CATCH_KEYWORD; }
"end_unwind_protect"        { return OctaveTypes.END_UNWIND_PROTECT_KEYWORD; }
"endclassdef"               { return OctaveTypes.ENDCLASSDEF_KEYWORD; }
"endenumeration"            { return OctaveTypes.ENDENUMERATION_KEYWORD; }
"endevents"                 { return OctaveTypes.ENDEVENTS_KEYWORD; }
"endfor"                    { return OctaveTypes.ENDFOR_KEYWORD; }
"endfunction"               { return OctaveTypes.ENDFUNCTION_KEYWORD; }
"endif"                     { return OctaveTypes.ENDIF_KEYWORD; }
"endmethods"                { return OctaveTypes.ENDMETHODS_KEYWORD; }
"endparfor"                 { return OctaveTypes.ENDPARFOR_KEYWORD; }
"endproperties"             { return OctaveTypes.ENDPROPERTIES_KEYWORD; }
"endswitch"                 { return OctaveTypes.ENDSWITCH_KEYWORD; }
"endwhile"                  { return OctaveTypes.ENDWHILE_KEYWORD; }
"enumeration"               { return OctaveTypes.ENUMERATION_KEYWORD; }
"events"                    { return OctaveTypes.EVENTS_KEYWORD; }
"for"                       { return OctaveTypes.FOR_KEYWORD; }
"function"                  { return OctaveTypes.FUNCTION_KEYWORD; }
"global"                    { return OctaveTypes.GLOBAL_KEYWORD; }
"if"                        { return OctaveTypes.IF_KEYWORD; }
"methods"                   { return OctaveTypes.METHODS_KEYWORD; }
"otherwise"                 { return OctaveTypes.OTHERWISE_KEYWORD; }
"parfor"                    { return OctaveTypes.PARFOR_KEYWORD; }
"persistent"                { return OctaveTypes.PERSISTENT_KEYWORD; }
"properties"                { return OctaveTypes.PROPERTIES_KEYWORD; }
"return"                    { return OctaveTypes.RETURN_KEYWORD; }
"static"                    { return OctaveTypes.STATIC_KEYWORD; }
"switch"                    { return OctaveTypes.SWITCH_KEYWORD; }
"try"                       { return OctaveTypes.TRY_KEYWORD; }
"until"                     { return OctaveTypes.UNTIL_KEYWORD; }
"unwind_protect"            { return OctaveTypes.UNWIND_PROTECT_KEYWORD; }
"unwind_protect_cleanup"    { return OctaveTypes.UNWIND_PROTECT_CLEANUP_KEYWORD; }
"while"                     { return OctaveTypes.WHILE_KEYWORD; }

{STRING}                    { return OctaveTypes.STRING; }

// numeric constants
{INTEGER}                   { return OctaveTypes.INTEGER_LITERAL; }
{FLOAT_NUMBER}              { return OctaveTypes.FLOAT_NUMBER_LITERAL; }

// complex constants
{COMPLEX_NUMBER}            { return OctaveTypes.COMPLEX_LITERAL; }

// integer constants
{LONG_INTEGER}              { return OctaveTypes.LONG_LITERAL; }


{IDENTIFIER}                { return OctaveTypes.IDENTIFIER; }

.                           { return OctaveTypes.BAD_CHARACTER; }
}
