package com.jetbrains.octave.psi.api.impl;

import com.intellij.lang.ASTNode;
import com.jetbrains.octave.psi.OctaveElementImpl;
import com.jetbrains.octave.psi.api.OctaveConstExpression;
import org.jetbrains.annotations.NotNull;

public class OctaveConstExpressionImpl extends OctaveElementImpl implements OctaveConstExpression {
  public OctaveConstExpressionImpl(@NotNull final ASTNode astNode) {
    super(astNode);
  }
}
