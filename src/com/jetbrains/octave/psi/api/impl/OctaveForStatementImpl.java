package com.jetbrains.octave.psi.api.impl;

import com.intellij.lang.ASTNode;
import com.jetbrains.octave.psi.OctaveElementImpl;
import com.jetbrains.octave.psi.api.OctaveForStatement;
import org.jetbrains.annotations.NotNull;

public class OctaveForStatementImpl extends OctaveElementImpl implements OctaveForStatement {
  public OctaveForStatementImpl(@NotNull final ASTNode astNode) {
    super(astNode);
  }
}
