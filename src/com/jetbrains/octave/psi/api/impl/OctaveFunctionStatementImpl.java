package com.jetbrains.octave.psi.api.impl;

import com.intellij.lang.ASTNode;
import com.jetbrains.octave.psi.OctaveElementImpl;
import com.jetbrains.octave.psi.api.OctaveFunctionStatement;
import org.jetbrains.annotations.NotNull;

public class OctaveFunctionStatementImpl extends OctaveElementImpl implements OctaveFunctionStatement {
  public OctaveFunctionStatementImpl(@NotNull final ASTNode astNode) {
    super(astNode);
  }
}
