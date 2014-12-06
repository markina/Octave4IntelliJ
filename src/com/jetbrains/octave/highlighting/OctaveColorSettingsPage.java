package com.jetbrains.octave.highlighting;

import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import com.intellij.openapi.options.colors.AttributesDescriptor;
import com.intellij.openapi.options.colors.ColorDescriptor;
import com.intellij.openapi.options.colors.ColorSettingsPage;
import com.jetbrains.octave.OctaveIcons;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.Map;

public class OctaveColorSettingsPage implements ColorSettingsPage {
  private static final AttributesDescriptor[] DESCRIPTORS = new AttributesDescriptor[]{
    new AttributesDescriptor(OctaveSyntaxHighlighterColors.OCTAVE_KEYWORD, OctaveSyntaxHighlighterColors.KEYWORD),
    new AttributesDescriptor(OctaveSyntaxHighlighterColors.OCTAVE_OPERATORS, OctaveSyntaxHighlighterColors.OPERATORS),
    new AttributesDescriptor(OctaveSyntaxHighlighterColors.OCTAVE_STRING, OctaveSyntaxHighlighterColors.STRING),
    new AttributesDescriptor(OctaveSyntaxHighlighterColors.OCTAVE_NUMBER, OctaveSyntaxHighlighterColors.NUMBER),
    new AttributesDescriptor(OctaveSyntaxHighlighterColors.OCTAVE_PAR, OctaveSyntaxHighlighterColors.PAR),
    new AttributesDescriptor(OctaveSyntaxHighlighterColors.OCTAVE_BRACES, OctaveSyntaxHighlighterColors.BRACES),
    new AttributesDescriptor(OctaveSyntaxHighlighterColors.OCTAVE_BRACKETS, OctaveSyntaxHighlighterColors.BRACKETS),
    new AttributesDescriptor(OctaveSyntaxHighlighterColors.OCTAVE_COMMA, OctaveSyntaxHighlighterColors.COMMA),
    new AttributesDescriptor(OctaveSyntaxHighlighterColors.OCTAVE_DOT, OctaveSyntaxHighlighterColors.DOT),
    new AttributesDescriptor(OctaveSyntaxHighlighterColors.OCTAVE_SEMICOLON, OctaveSyntaxHighlighterColors.SEMICOLON),
    new AttributesDescriptor(OctaveSyntaxHighlighterColors.OCTAVE_COMMENT, OctaveSyntaxHighlighterColors.COMMENT),
    new AttributesDescriptor(OctaveSyntaxHighlighterColors.OCTAVE_BAD_CHARACTER, OctaveSyntaxHighlighterColors.BAD_CHARACTER),
  };


  @Nullable
  @Override
  public Icon getIcon() {
    return OctaveIcons.FILE;
  }

  @NotNull
  @Override
  public SyntaxHighlighter getHighlighter() {
    return new OctaveHighlighter();
  }

  @NotNull
  @Override
  public String getDemoText() {
    return "% You are reading the comment\n" +
           "# Another comment\n" +
           "a = (pi * 10)^2;\n" +
           "if a == 0\n" +
           "    b = 'zero';\n" +
           "else\n" +
           "    b = 'not zero';\n" +
           "endif\n" +
           "c = [1, 2, 3];\n";
  }

  @Nullable
  @Override
  public Map<String, TextAttributesKey> getAdditionalHighlightingTagToDescriptorMap() {
    return null;
  }

  @NotNull
  @Override
  public AttributesDescriptor[] getAttributeDescriptors() {
    return DESCRIPTORS;
  }

  @NotNull
  @Override
  public ColorDescriptor[] getColorDescriptors() {
    return ColorDescriptor.EMPTY_ARRAY;
  }

  @NotNull
  @Override
  public String getDisplayName() {
    return "Octave";
  }
}
