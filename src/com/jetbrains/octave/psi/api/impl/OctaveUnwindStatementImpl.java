package com.jetbrains.octave.psi.api.impl;

import com.intellij.lang.ASTNode;
import com.jetbrains.octave.psi.OctaveElementImpl;
import com.jetbrains.octave.psi.api.OctaveUnwindStatement;
import org.jetbrains.annotations.NotNull;

public class OctaveUnwindStatementImpl extends OctaveElementImpl implements OctaveUnwindStatement {
  public OctaveUnwindStatementImpl(@NotNull final ASTNode astNode) {
    super(astNode);
  }
}