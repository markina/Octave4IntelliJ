package com.jetbrains.octave.psi.api.impl;

import com.intellij.lang.ASTNode;
import com.jetbrains.octave.psi.OctaveElementImpl;
import com.jetbrains.octave.psi.api.OctavePrefixExpression;
import org.jetbrains.annotations.NotNull;

public class OctavePrefixExpressionImpl extends OctaveElementImpl implements OctavePrefixExpression {
  public OctavePrefixExpressionImpl(@NotNull final ASTNode astNode) {
    super(astNode);
  }
}
