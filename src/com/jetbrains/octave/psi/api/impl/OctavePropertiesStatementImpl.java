package com.jetbrains.octave.psi.api.impl;

import com.intellij.lang.ASTNode;
import com.jetbrains.octave.psi.OctaveElementImpl;
import com.jetbrains.octave.psi.api.OctavePropertiesStatement;
import org.jetbrains.annotations.NotNull;

public class OctavePropertiesStatementImpl extends OctaveElementImpl implements OctavePropertiesStatement {
  public OctavePropertiesStatementImpl(@NotNull final ASTNode astNode) {
    super(astNode);
  }
}