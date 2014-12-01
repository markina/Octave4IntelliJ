package com.jetbrains.octave.psi.api.impl;

import com.intellij.lang.ASTNode;
import com.jetbrains.octave.psi.OctaveElementImpl;
import com.jetbrains.octave.psi.api.OctaveTryStatement;
import org.jetbrains.annotations.NotNull;

public class OctaveTryStatementImpl extends OctaveElementImpl implements OctaveTryStatement {
  public OctaveTryStatementImpl(@NotNull final ASTNode astNode) {
    super(astNode);
  }
}