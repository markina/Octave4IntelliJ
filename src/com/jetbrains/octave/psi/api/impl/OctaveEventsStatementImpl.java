package com.jetbrains.octave.psi.api.impl;

import com.intellij.lang.ASTNode;
import com.jetbrains.octave.psi.OctaveElementImpl;
import com.jetbrains.octave.psi.api.OctaveEventStatement;
import org.jetbrains.annotations.NotNull;

public class OctaveEventsStatementImpl extends OctaveElementImpl implements OctaveEventStatement {
  public OctaveEventsStatementImpl(@NotNull final ASTNode astNode) {
    super(astNode);
  }
}