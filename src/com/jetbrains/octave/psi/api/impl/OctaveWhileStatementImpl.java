package com.jetbrains.octave.psi.api.impl;

import com.intellij.lang.ASTNode;
import com.jetbrains.octave.psi.OctaveElementImpl;
import com.jetbrains.octave.psi.api.OctaveWhileStatement;
import org.jetbrains.annotations.NotNull;

public class OctaveWhileStatementImpl extends OctaveElementImpl implements OctaveWhileStatement {
  public OctaveWhileStatementImpl(@NotNull final ASTNode astNode) {
    super(astNode);
  }
}
