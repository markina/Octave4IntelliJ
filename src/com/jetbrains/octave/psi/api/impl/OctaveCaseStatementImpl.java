package com.jetbrains.octave.psi.api.impl;

import com.intellij.lang.ASTNode;
import com.jetbrains.octave.psi.OctaveElementImpl;
import com.jetbrains.octave.psi.api.OctaveCaseStatement;
import org.jetbrains.annotations.NotNull;

public class OctaveCaseStatementImpl extends OctaveElementImpl implements OctaveCaseStatement {
  public OctaveCaseStatementImpl(@NotNull final ASTNode astNode) {
    super(astNode);
  }
}
