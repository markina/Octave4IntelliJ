package com.jetbrains.octave.psi.api.impl;

import com.intellij.lang.ASTNode;
import com.jetbrains.octave.psi.OctaveElementImpl;
import com.jetbrains.octave.psi.api.OctaveDoStatement;
import org.jetbrains.annotations.NotNull;

public class OctaveDoStatementImpl extends OctaveElementImpl implements OctaveDoStatement {
  public OctaveDoStatementImpl(@NotNull final ASTNode astNode) {
    super(astNode);
  }
}
