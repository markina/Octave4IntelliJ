package ru.compscicenter.jetbrains.octave.lexer;

import com.intellij.lexer.FlexAdapter;

import java.io.Reader;

/**
 * Created by Markina Margarita on 02.10.14.
 */
public class OctaveLexerAdapter extends FlexAdapter {
  public OctaveLexerAdapter() {
    super(new OctaveLexer((Reader) null));
  }
}
