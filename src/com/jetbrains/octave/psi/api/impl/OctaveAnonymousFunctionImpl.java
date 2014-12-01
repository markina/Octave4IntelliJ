package com.jetbrains.octave.psi.api.impl;

import com.intellij.lang.ASTNode;
import com.jetbrains.octave.psi.OctaveElementImpl;
import com.jetbrains.octave.psi.api.OctaveAnonymousFunction;
import org.jetbrains.annotations.NotNull;

public class OctaveAnonymousFunctionImpl extends OctaveElementImpl implements OctaveAnonymousFunction {
  public OctaveAnonymousFunctionImpl(@NotNull final ASTNode astNode) {
    super(astNode);
  }
}
