package com.jetbrains.octave.psi.api.impl;

import com.intellij.lang.ASTNode;
import com.jetbrains.octave.psi.OctaveElementImpl;
import com.jetbrains.octave.psi.api.OctaveBooleanLiteral;
import org.jetbrains.annotations.NotNull;

public class OctaveBooleanLiteralImpl extends OctaveElementImpl implements OctaveBooleanLiteral {
  public OctaveBooleanLiteralImpl(@NotNull final ASTNode astNode) {
    super(astNode);
  }
}
