package com.jetbrains.octave.psi.api.impl;

import com.intellij.lang.ASTNode;
import com.jetbrains.octave.psi.OctaveElementImpl;
import com.jetbrains.octave.psi.api.OctaveReturnStatement;
import org.jetbrains.annotations.NotNull;

public class OctaveReturnStatementImpl extends OctaveElementImpl implements OctaveReturnStatement {
  public OctaveReturnStatementImpl(@NotNull final ASTNode astNode) {
    super(astNode);
  }
}