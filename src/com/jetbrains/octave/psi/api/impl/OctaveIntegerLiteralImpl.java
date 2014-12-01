package com.jetbrains.octave.psi.api.impl;

import com.intellij.lang.ASTNode;
import com.jetbrains.octave.psi.OctaveElementImpl;
import com.jetbrains.octave.psi.api.OctaveIntegerLiteral;
import org.jetbrains.annotations.NotNull;

public class OctaveIntegerLiteralImpl extends OctaveElementImpl implements OctaveIntegerLiteral {
  public OctaveIntegerLiteralImpl(@NotNull final ASTNode astNode) {
    super(astNode);
  }
}
