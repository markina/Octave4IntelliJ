package com.jetbrains.octave.highlighting;

import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.HighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;

import static com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey;

public class OctaveSyntaxHighlighterColors {
  public static final String OCTAVE_KEYWORD = "OCTAVE_KEYWORD";
  public static final String OCTAVE_OPERATORS = "OCTAVE_OPERATORS";
  public static final String OCTAVE_STRING = "OCTAVE_STRING";
  public static final String OCTAVE_NUMBER = "OCTAVE_NUMBER";
  public static final String OCTAVE_PAR = "OCTAVE_PAR";
  public static final String OCTAVE_BRACES = "OCTAVE_BRACES";
  public static final String OCTAVE_BRACKETS = "OCTAVE_BRACKETS";
  public static final String OCTAVE_COMMA = "OCTAVE_COMMA";
  public static final String OCTAVE_DOT = "OCTAVE_DOT";
  public static final String OCTAVE_SEMICOLON = "OCTAVE_SEMICOLON";
  public static final String OCTAVE_COMMENT = "OCTAVE_COMMENT";
  public static final String OCTAVE_BAD_CHARACTER = "OCTAVE_BAD_CHARACTER";


  public static final TextAttributesKey KEYWORD = createTextAttributesKey(OCTAVE_KEYWORD, DefaultLanguageHighlighterColors.KEYWORD);
  public static final TextAttributesKey OPERATORS = createTextAttributesKey(OCTAVE_OPERATORS, DefaultLanguageHighlighterColors.OPERATION_SIGN);
  public static final TextAttributesKey STRING = createTextAttributesKey(OCTAVE_STRING, DefaultLanguageHighlighterColors.STRING);
  public static final TextAttributesKey NUMBER = createTextAttributesKey(OCTAVE_NUMBER, DefaultLanguageHighlighterColors.NUMBER);
  public static final TextAttributesKey PAR = createTextAttributesKey(OCTAVE_PAR, DefaultLanguageHighlighterColors.PARENTHESES);
  public static final TextAttributesKey BRACES = createTextAttributesKey(OCTAVE_BRACES, DefaultLanguageHighlighterColors.BRACES);
  public static final TextAttributesKey BRACKETS = createTextAttributesKey(OCTAVE_BRACKETS, DefaultLanguageHighlighterColors.BRACKETS);
  public static final TextAttributesKey COMMA = createTextAttributesKey(OCTAVE_COMMA, DefaultLanguageHighlighterColors.COMMA);
  public static final TextAttributesKey DOT = createTextAttributesKey(OCTAVE_DOT, DefaultLanguageHighlighterColors.DOT);
  public static final TextAttributesKey SEMICOLON = createTextAttributesKey(OCTAVE_SEMICOLON, DefaultLanguageHighlighterColors.SEMICOLON);
  public static final TextAttributesKey COMMENT = createTextAttributesKey(OCTAVE_COMMENT, DefaultLanguageHighlighterColors.LINE_COMMENT);
  public static final TextAttributesKey BAD_CHARACTER = createTextAttributesKey(OCTAVE_BAD_CHARACTER, HighlighterColors.BAD_CHARACTER);
}
