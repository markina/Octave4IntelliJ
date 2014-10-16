package ru.compscicenter.jetbrains.octave;

import com.intellij.lang.Language;

/**
 * Created by Markina Margarita on 01.10.14.
 */
public class OctaveLanguage extends Language {
  public static final OctaveLanguage INSTANCE = new OctaveLanguage();

  private OctaveLanguage() {
    super("Octave");
  }


}

