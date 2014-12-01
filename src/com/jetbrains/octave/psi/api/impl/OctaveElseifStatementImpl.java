package com.jetbrains.octave.psi.api.impl;

import com.intellij.lang.ASTNode;
import com.jetbrains.octave.psi.OctaveElementImpl;
import com.jetbrains.octave.psi.api.OctaveElseifStatement;
import org.jetbrains.annotations.NotNull;

public class OctaveElseifStatementImpl extends OctaveElementImpl implements OctaveElseifStatement {
  public OctaveElseifStatementImpl(@NotNull final ASTNode astNode) {
    super(astNode);
  }
}
