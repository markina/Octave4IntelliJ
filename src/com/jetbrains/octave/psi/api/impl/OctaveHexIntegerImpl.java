package com.jetbrains.octave.psi.api.impl;

import com.intellij.lang.ASTNode;
import com.jetbrains.octave.psi.OctaveElementImpl;
import com.jetbrains.octave.psi.api.OctaveHexInteger;
import org.jetbrains.annotations.NotNull;

public class OctaveHexIntegerImpl extends OctaveElementImpl implements OctaveHexInteger {
  public OctaveHexIntegerImpl(@NotNull final ASTNode astNode) {
    super(astNode);
  }
}

