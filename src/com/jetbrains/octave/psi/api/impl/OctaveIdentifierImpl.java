package com.jetbrains.octave.psi.api.impl;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.jetbrains.octave.psi.OctaveElementImpl;
import com.jetbrains.octave.psi.api.OctaveIdentifier;
import com.jetbrains.octave.psi.api.OctaveStringLiteral;
import org.jetbrains.annotations.NotNull;

public class OctaveIdentifierImpl extends OctaveElementImpl implements OctaveIdentifier {
  public OctaveIdentifierImpl(@NotNull final ASTNode astNode) {
    super(astNode);
  }
}