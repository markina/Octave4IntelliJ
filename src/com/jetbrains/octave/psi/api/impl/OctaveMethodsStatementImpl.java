package com.jetbrains.octave.psi.api.impl;

import com.intellij.lang.ASTNode;
import com.jetbrains.octave.psi.OctaveElementImpl;
import com.jetbrains.octave.psi.api.OctaveMathodsStatement;
import org.jetbrains.annotations.NotNull;

public class OctaveMethodsStatementImpl extends OctaveElementImpl implements OctaveMathodsStatement {
  public OctaveMethodsStatementImpl(@NotNull final ASTNode astNode) {
    super(astNode);
  }
}