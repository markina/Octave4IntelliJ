package com.jetbrains.octave.psi.api.impl;

import com.intellij.lang.ASTNode;
import com.jetbrains.octave.psi.OctaveElementImpl;
import com.jetbrains.octave.psi.api.OctaveIfStatement;
import org.jetbrains.annotations.NotNull;

public class OctaveIfStatementImpl extends OctaveElementImpl implements OctaveIfStatement {
  public OctaveIfStatementImpl(@NotNull final ASTNode astNode) {
    super(astNode);
  }
}
