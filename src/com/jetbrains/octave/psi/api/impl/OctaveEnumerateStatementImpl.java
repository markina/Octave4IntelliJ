package com.jetbrains.octave.psi.api.impl;

import com.intellij.lang.ASTNode;
import com.jetbrains.octave.psi.OctaveElementImpl;
import com.jetbrains.octave.psi.api.OctaveEnumerateStatement;
import org.jetbrains.annotations.NotNull;

public class OctaveEnumerateStatementImpl extends OctaveElementImpl implements OctaveEnumerateStatement {
  public OctaveEnumerateStatementImpl(@NotNull final ASTNode astNode) {
    super(astNode);
  }
}
