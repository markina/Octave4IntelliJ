package com.jetbrains.octave.psi.api.impl;

import com.intellij.lang.ASTNode;
import com.jetbrains.octave.psi.OctaveElementImpl;
import com.jetbrains.octave.psi.api.OctaveClassDefStatement;
import org.jetbrains.annotations.NotNull;

public class OctaveClassDefStatementImpl extends OctaveElementImpl implements OctaveClassDefStatement {
  public OctaveClassDefStatementImpl(@NotNull final ASTNode astNode) {
    super(astNode);
  }
}