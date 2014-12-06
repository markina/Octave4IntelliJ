package com.jetbrains.octave;

import com.intellij.lang.Language;

public class OctaveLanguage extends Language {
  public static final OctaveLanguage INSTANCE = new OctaveLanguage();

  private OctaveLanguage() {
    super("Octave");
  }
}

