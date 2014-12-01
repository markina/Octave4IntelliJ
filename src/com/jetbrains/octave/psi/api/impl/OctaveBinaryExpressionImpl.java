package com.jetbrains.octave.psi.api.impl;

import com.intellij.lang.ASTNode;
import com.jetbrains.octave.psi.OctaveElementImpl;
import com.jetbrains.octave.psi.api.OctaveBinaryExpression;
import org.jetbrains.annotations.NotNull;

public class OctaveBinaryExpressionImpl extends OctaveElementImpl implements OctaveBinaryExpression {
  public OctaveBinaryExpressionImpl(@NotNull final ASTNode astNode) {
    super(astNode);
  }
}