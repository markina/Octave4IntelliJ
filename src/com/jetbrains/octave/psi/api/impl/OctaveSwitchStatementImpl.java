package com.jetbrains.octave.psi.api.impl;

import com.intellij.lang.ASTNode;
import com.jetbrains.octave.psi.OctaveElementImpl;
import com.jetbrains.octave.psi.api.OctaveSwitchStatement;
import org.jetbrains.annotations.NotNull;

public class OctaveSwitchStatementImpl extends OctaveElementImpl implements OctaveSwitchStatement {
  public OctaveSwitchStatementImpl(@NotNull final ASTNode astNode) {
    super(astNode);
  }
}