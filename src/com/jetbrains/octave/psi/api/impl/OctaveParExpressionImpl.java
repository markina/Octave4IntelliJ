package com.jetbrains.octave.psi.api.impl;

import com.intellij.lang.ASTNode;
import com.jetbrains.octave.psi.OctaveElementImpl;
import com.jetbrains.octave.psi.api.OctaveParStatement;
import org.jetbrains.annotations.NotNull;

public class OctaveParExpressionImpl extends OctaveElementImpl implements OctaveParStatement {
  public OctaveParExpressionImpl(@NotNull final ASTNode astNode) {
    super(astNode);
  }
}
