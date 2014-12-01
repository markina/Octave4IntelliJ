package com.jetbrains.octave.psi.api.impl;

import com.intellij.lang.ASTNode;
import com.jetbrains.octave.psi.OctaveElementImpl;
import com.jetbrains.octave.psi.api.OctaveFunctionNameStatement;
import org.jetbrains.annotations.NotNull;

public class OctaveFunctionNameStatementImpl extends OctaveElementImpl implements OctaveFunctionNameStatement {
  public OctaveFunctionNameStatementImpl(@NotNull final ASTNode astNode) {
    super(astNode);
  }
}
