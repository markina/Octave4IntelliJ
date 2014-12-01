package com.jetbrains.octave.psi.api.impl;

import com.intellij.lang.ASTNode;
import com.jetbrains.octave.psi.OctaveElementImpl;
import com.jetbrains.octave.psi.api.OctaveBraceStatement;
import org.jetbrains.annotations.NotNull;

public class OctaveBraceExpressionImpl extends OctaveElementImpl implements OctaveBraceStatement {
  public OctaveBraceExpressionImpl(@NotNull final ASTNode astNode) {
    super(astNode);
  }
}
