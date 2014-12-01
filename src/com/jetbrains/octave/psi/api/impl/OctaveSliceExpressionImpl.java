package com.jetbrains.octave.psi.api.impl;

import com.intellij.lang.ASTNode;
import com.jetbrains.octave.psi.OctaveElementImpl;
import com.jetbrains.octave.psi.api.OctaveSliceStatement;
import org.jetbrains.annotations.NotNull;

public class OctaveSliceExpressionImpl extends OctaveElementImpl implements OctaveSliceStatement {
  public OctaveSliceExpressionImpl(@NotNull final ASTNode astNode) {
    super(astNode);
  }
}
