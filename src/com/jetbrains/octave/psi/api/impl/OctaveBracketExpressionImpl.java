package com.jetbrains.octave.psi.api.impl;

import com.intellij.lang.ASTNode;
import com.jetbrains.octave.psi.OctaveElementImpl;
import com.jetbrains.octave.psi.api.OctaveBracketExpression;
import org.jetbrains.annotations.NotNull;

public class OctaveBracketExpressionImpl extends OctaveElementImpl implements OctaveBracketExpression {
  public OctaveBracketExpressionImpl(@NotNull final ASTNode astNode) {
    super(astNode);
  }
}
