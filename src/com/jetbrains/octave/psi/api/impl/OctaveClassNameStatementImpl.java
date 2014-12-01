package com.jetbrains.octave.psi.api.impl;

import com.intellij.lang.ASTNode;
import com.jetbrains.octave.psi.OctaveElementImpl;
import com.jetbrains.octave.psi.api.OctaveClassNameStatement;
import org.jetbrains.annotations.NotNull;

public class OctaveClassNameStatementImpl extends OctaveElementImpl implements OctaveClassNameStatement {
  public OctaveClassNameStatementImpl(@NotNull final ASTNode astNode) {
    super(astNode);
  }
}
