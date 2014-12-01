package com.jetbrains.octave.psi.api.impl;

import com.intellij.lang.ASTNode;
import com.jetbrains.octave.psi.OctaveElementImpl;
import com.jetbrains.octave.psi.api.OctaveParforStatement;
import org.jetbrains.annotations.NotNull;

public class OctaveParforStatementImpl extends OctaveElementImpl implements OctaveParforStatement {
  public OctaveParforStatementImpl(@NotNull final ASTNode astNode) {
    super(astNode);
  }
}