package com.jetbrains.octave.psi.api.impl;

import com.intellij.lang.ASTNode;
import com.jetbrains.octave.psi.OctaveElementImpl;
import com.jetbrains.octave.psi.api.OctaveMethodNameStatement;
import org.jetbrains.annotations.NotNull;

public class OctaveMethodsNameStatementImpl extends OctaveElementImpl implements OctaveMethodNameStatement {
  public OctaveMethodsNameStatementImpl(@NotNull final ASTNode astNode) {
    super(astNode);
  }
}