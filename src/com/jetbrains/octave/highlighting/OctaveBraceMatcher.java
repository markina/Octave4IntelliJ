package com.jetbrains.octave.highlighting;

import com.intellij.lang.BracePair;
import com.intellij.lang.PairedBraceMatcher;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IElementType;
import com.jetbrains.octave.lexer.OctaveTokenTypes;

public class OctaveBraceMatcher implements PairedBraceMatcher {

  private final BracePair[] PAIRS = new BracePair[]{
    new BracePair(OctaveTokenTypes.LPAR, OctaveTokenTypes.RPAR, false),
    new BracePair(OctaveTokenTypes.LBRACE, OctaveTokenTypes.RBRACE, false),
    new BracePair(OctaveTokenTypes.LBRACKET, OctaveTokenTypes.RBRACKET, false)
  };

  @Override
  public BracePair[] getPairs() {
    return PAIRS;
  }

  @Override
  public boolean isPairedBracesAllowedBeforeType(IElementType type, IElementType type1) {
    return true;
  }

  @Override
  public int getCodeConstructStart(PsiFile file, int i) {
    return i;
  }
}
