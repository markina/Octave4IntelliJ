package com.jetbrains.octave.lexer;

import com.intellij.lexer.FlexAdapter;

import java.io.Reader;

public class OctaveLexerAdapter extends FlexAdapter {
  public OctaveLexerAdapter() {
    super(new OctaveLexer((Reader)null));
  }
}
