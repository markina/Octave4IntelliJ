package com.jetbrains.octave.highlighting;

import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.tree.IElementType;
import com.jetbrains.octave.lexer.OctaveLexerAdapter;
import com.jetbrains.octave.lexer.OctaveTokenTypes;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class OctaveHighlighter extends SyntaxHighlighterBase {
  private static final Map<IElementType, TextAttributesKey> ATTRIBUTES = new HashMap<>();

  static {
    fillMap(ATTRIBUTES, OctaveTokenTypes.SET_KEYWORD, OctaveSyntaxHighlighterColors.KEYWORD);

    fillMap(ATTRIBUTES, OctaveTokenTypes.OPERATORS, OctaveSyntaxHighlighterColors.OPERATORS);

    ATTRIBUTES.put(OctaveTokenTypes.STRING, OctaveSyntaxHighlighterColors.STRING);
    fillMap(ATTRIBUTES, OctaveTokenTypes.SET_NUMERIC_LITERAL, OctaveSyntaxHighlighterColors.NUMBER);

    ATTRIBUTES.put(OctaveTokenTypes.LPAR, OctaveSyntaxHighlighterColors.PAR);
    ATTRIBUTES.put(OctaveTokenTypes.RPAR, OctaveSyntaxHighlighterColors.PAR);

    ATTRIBUTES.put(OctaveTokenTypes.LBRACE, OctaveSyntaxHighlighterColors.BRACES);
    ATTRIBUTES.put(OctaveTokenTypes.RBRACE, OctaveSyntaxHighlighterColors.BRACES);

    ATTRIBUTES.put(OctaveTokenTypes.LBRACKET, OctaveSyntaxHighlighterColors.BRACKETS);
    ATTRIBUTES.put(OctaveTokenTypes.RBRACKET, OctaveSyntaxHighlighterColors.BRACKETS);

    ATTRIBUTES.put(OctaveTokenTypes.COMMA, OctaveSyntaxHighlighterColors.COMMA);
    ATTRIBUTES.put(OctaveTokenTypes.DOT, OctaveSyntaxHighlighterColors.DOT);
    ATTRIBUTES.put(OctaveTokenTypes.SEMICOLON, OctaveSyntaxHighlighterColors.SEMICOLON);

    ATTRIBUTES.put(OctaveTokenTypes.COMMENT, OctaveSyntaxHighlighterColors.COMMENT);

    ATTRIBUTES.put(OctaveTokenTypes.BAD_CHARACTER, OctaveSyntaxHighlighterColors.BAD_CHARACTER);
  }

  @NotNull
  @Override
  public Lexer getHighlightingLexer() {
    return new OctaveLexerAdapter();
  }

  @NotNull
  @Override
  public TextAttributesKey[] getTokenHighlights(IElementType type) {
    return pack(ATTRIBUTES.get(type));
  }
}
