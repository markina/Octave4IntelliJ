package com.jetbrains.octave.psi.api.impl;

import com.intellij.lang.ASTNode;
import com.jetbrains.octave.psi.OctaveElementImpl;
import com.jetbrains.octave.psi.api.OctaveEnumerationStatement;
import org.jetbrains.annotations.NotNull;

public class OctaveEnumerationStatementImpl extends OctaveElementImpl implements OctaveEnumerationStatement {
  public OctaveEnumerationStatementImpl(@NotNull final ASTNode astNode) {
    super(astNode);
  }
}