package ru.compscicenter.jetbrains.octave.lexer;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import ru.compscicenter.jetbrains.octave.lexer.OctaveTokenTypes;
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

END_OF_LINE_COMMENT=("%"|"#")[^\r\n]*
NO_END_COMMENT = ("#"([^}]))|([^#])
COMMENT_BLOCK="#{"({NO_END_COMMENT})*("#}")
NO_END_COMMENT_2 = ("%"([^}]))|([^%])
COMMENT_BLOCK_2 = "%{"({NO_END_COMMENT_2})*("%}")

//todo #{ dfdf #} df


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

COMPLEX_NUMBER=(({FLOAT_NUMBER})|({INT_PART}))?[iI]

NEXT_LINE = [\n]
SPASE = [\ ]

%%

<YYINITIAL> {
{COMMENT_BLOCK}             { return OctaveTokenTypes.COMMENT; }
{COMMENT_BLOCK_2}           { return OctaveTokenTypes.COMMENT; }
{END_OF_LINE_COMMENT}       { return OctaveTokenTypes.COMMENT; }

{NEXT_LINE}*                { return OctaveTokenTypes.LINE_BREAK; }
{SPASE}*                    { return OctaveTokenTypes.SPACE; }
[\t]                        { return OctaveTokenTypes.TAB; }
[\f]                        { return OctaveTokenTypes.FORMFEED; }

"++"                        { return OctaveTokenTypes.INCREMENT; }
"--"                        { return OctaveTokenTypes.DECREMENT; }

"+="                        { return OctaveTokenTypes.OPERATION_PLUS_EQ; }
"-="                        { return OctaveTokenTypes.OPERATION_MINUS_EQ; }
"*="                        { return OctaveTokenTypes.OPERATION_MULT_EQ; }
"/="                        { return OctaveTokenTypes.OPERATION_DIV_EQ; }
"^="                        { return OctaveTokenTypes.OPERATION_POWER_EQ; }
".+="                       { return OctaveTokenTypes.OPERATION_DOT_PLUS_EQ; }
".-="                       { return OctaveTokenTypes.OPERATION_DOT_MINUS_EQ; }
".*="                       { return OctaveTokenTypes.OPERATION_DOT_MULT_EQ; }
"./="                       { return OctaveTokenTypes.OPERATION_DOT_DIV_EQ; }
".^="                       { return OctaveTokenTypes.OPERATION_DOT_POWER_EQ; }

//dot operation
"./"                        {return OctaveTokenTypes.DOT_DIVISION; }
".*"                        {return OctaveTokenTypes.DOT_MULTIPLICATION; }
".+"                        {return OctaveTokenTypes.DOT_PLUS; }
".-"                        {return OctaveTokenTypes.DOT_MINUS; }
".^"                        {return OctaveTokenTypes.DOT_POWER; }



"="                         { return OctaveTokenTypes.EQ; }
";"                         { return OctaveTokenTypes.SEMICOLON; }
","                         { return OctaveTokenTypes.COMMA; }
"."                         { return OctaveTokenTypes.DOT; }

// grouping
"("                         { return OctaveTokenTypes.LPAR; }
")"                         { return OctaveTokenTypes.RPAR; }
"["                         { return OctaveTokenTypes.LBRACKET; }
"]"                         { return OctaveTokenTypes.RBRACKET; }
"{"                         { return OctaveTokenTypes.LBRACE; }
"}"                         { return OctaveTokenTypes.RBRACE; }


//arithmetic
"-"                         { return OctaveTokenTypes.MINUS; }
"+"                         { return OctaveTokenTypes.PLUS; }
"*"                         { return OctaveTokenTypes.MULTIPLICATION; }
"/"                         { return OctaveTokenTypes.DIVISION; }
"^"                         { return OctaveTokenTypes.POWER; }
"~"                         { return OctaveTokenTypes.TILDE; }
":"                         { return OctaveTokenTypes.COLON; }
"@"                         { return OctaveTokenTypes.AT; }





// special constants
"NA"                        { return OctaveTokenTypes.NA_KEYWORD; }
"inf"                       { return OctaveTokenTypes.INF_KEYWORD; }
"Inf"                       { return OctaveTokenTypes.INF_KEYWORD; }
"NaN"                       { return OctaveTokenTypes.NAN_KEYWORD; }
"nan"                       { return OctaveTokenTypes.NAN_KEYWORD; }
"e"                         { return OctaveTokenTypes.E_KEYWORD; }
"pi"                        { return OctaveTokenTypes.PI_KEYWORD; }
"eps"                       { return OctaveTokenTypes.EPS_KEYWORD; }
"realmax"                   { return OctaveTokenTypes.REALMAX_KEYWORD; }
"realmin"                   { return OctaveTokenTypes.REALMIN_KEYWORD; }


// logical constants
"true"                      { return OctaveTokenTypes.TRUE_KEYWORD; }
"false"                     { return OctaveTokenTypes.FALSE_KEYWORD; }

// relational
"<"                         { return OctaveTokenTypes.LT; }
">"                         { return OctaveTokenTypes.GT; }
"=="                        { return OctaveTokenTypes.EQEQ; }
">="                        { return OctaveTokenTypes.GE; }
"<="                        { return OctaveTokenTypes.LE; }
"!="                        { return OctaveTokenTypes.NOTEQ; }

// logical
"!"                         { return OctaveTokenTypes.NOT; }
"|"                         { return OctaveTokenTypes.OR; }
"&"                         { return OctaveTokenTypes.AND; }
"||"                        { return OctaveTokenTypes.DOUBLE_OR; }
"&&"                        { return OctaveTokenTypes.DOUBLE_AND; }


"break"                     { return OctaveTokenTypes.BREAK_KEYWORD; }
"case"                      { return OctaveTokenTypes.CASE_KEYWORD; }
"catch"                     { return OctaveTokenTypes.CATCH_KEYWORD; }
"classdef"                  { return OctaveTokenTypes.CLASSDEF_KEYWORD; }
"continue"                  { return OctaveTokenTypes.CONTINUE_KEYWORD; }
"do"                        { return OctaveTokenTypes.DO_KEYWORD; }
"else"                      { return OctaveTokenTypes.ELSE_KEYWORD; }
"elseif"                    { return OctaveTokenTypes.ELSEIF_KEYWORD; }
"end"                       { return OctaveTokenTypes.END_KEYWORD; }
"end_try_catch"             { return OctaveTokenTypes.END_TRY_CATCH_KEYWORD; }
"end_unwind_protect"        { return OctaveTokenTypes.END_UNWIND_PROTECT_KEYWORD; }
"endclassdef"               { return OctaveTokenTypes.ENDCLASSDEF_KEYWORD; }
"endenumeration"            { return OctaveTokenTypes.ENDENUMERATION_KEYWORD; }
"endfor"                    { return OctaveTokenTypes.ENDFOR_KEYWORD; }
"endfunction"               { return OctaveTokenTypes.ENDFUNCTION_KEYWORD; }
"endif"                     { return OctaveTokenTypes.ENDIF_KEYWORD; }
"endmethods"                { return OctaveTokenTypes.ENDMETHODS_KEYWORD; }
"endparfor"                 { return OctaveTokenTypes.ENDPARFOR_KEYWORD; }
"endswitch"                 { return OctaveTokenTypes.ENDSWITCH_KEYWORD; }
"endwhile"                  { return OctaveTokenTypes.ENDWHILE_KEYWORD; }
"enumeration"               { return OctaveTokenTypes.ENUMERATION_KEYWORD; }
"for"                       { return OctaveTokenTypes.FOR_KEYWORD; }
"function"                  { return OctaveTokenTypes.FUNCTION_KEYWORD; }
"if"                        { return OctaveTokenTypes.IF_KEYWORD; }
"methods"                   { return OctaveTokenTypes.METHODS_KEYWORD; }
"try"                       { return OctaveTokenTypes.TRY_KEYWORD; }
"until"                     { return OctaveTokenTypes.UNTIL_KEYWORD; }
"unwind_protect"            { return OctaveTokenTypes.UNWIND_PROTECT_KEYWORD; }
"unwind_protect_cleanup"    { return OctaveTokenTypes.UNWIND_PROTECT_CLEANUP_KEYWORD; }
"while"                     { return OctaveTokenTypes.WHILE_KEYWORD; }
"endevents"                 { return OctaveTokenTypes.ENDEVENTS_KEYWORD; }
"events"                    { return OctaveTokenTypes.EVENTS_KEYWORD; }
"endproperties"             { return OctaveTokenTypes.ENDPROPERTIES_KEYWORD; }
"properties"                { return OctaveTokenTypes.PROPERTIES_KEYWORD; }
"otherwise"                 { return OctaveTokenTypes.OTHERWISE_KEYWORD; }
"parfor"                    { return OctaveTokenTypes.PARFOR_KEYWORD; }
"switch"                    { return OctaveTokenTypes.SWITCH_KEYWORD; }
"persistent"                { return OctaveTokenTypes.PERSISTENT_KEYWORD; }
"static"                    { return OctaveTokenTypes.STATIC_KEYWORD; }

"global"                    { return OctaveTokenTypes.GLOBAL_KEYWORD; }
"return"                    { return OctaveTokenTypes.RETURN_KEYWORD; }



{STRING}                    { return OctaveTokenTypes.STRING; }

// numeric constants
{HEX_INTEGER}               { return OctaveTokenTypes.HEX_INTEGER; }
{INTEGER}                   { return OctaveTokenTypes.INTEGER_LITERAL; }
{FLOAT_NUMBER}              { return OctaveTokenTypes.FLOAT_NUMBER_LITERAL; }


{COMPLEX_NUMBER}            { return OctaveTokenTypes.COMPLEX_LITERAL; }

{IDENTIFIER}                { return OctaveTokenTypes.IDENTIFIER; }

.                           { return OctaveTokenTypes.BAD_CHARACTER; }
}
