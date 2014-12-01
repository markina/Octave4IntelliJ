package com.jetbrains.octave.psi.api.impl;

import com.intellij.lang.ASTNode;
import com.jetbrains.octave.psi.OctaveElementImpl;
import com.jetbrains.octave.psi.api.OctaveExpression;
import org.jetbrains.annotations.NotNull;

public class OctaveExpressionImpl extends OctaveElementImpl implements OctaveExpression {
  public OctaveExpressionImpl(@NotNull final ASTNode astNode) {
    super(astNode);
  }
}