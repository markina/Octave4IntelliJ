package com.jetbrains.octave.psi.api.impl;

import com.intellij.lang.ASTNode;
import com.jetbrains.octave.psi.OctaveElementImpl;
import com.jetbrains.octave.psi.api.OctaveConditionStatement;
import org.jetbrains.annotations.NotNull;

public class OctaveConditionStatementImpl extends OctaveElementImpl implements OctaveConditionStatement {
  public OctaveConditionStatementImpl(@NotNull final ASTNode astNode) {
    super(astNode);
  }
}
