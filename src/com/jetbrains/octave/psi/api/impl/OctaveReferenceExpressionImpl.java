package com.jetbrains.octave.psi.api.impl;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiReference;
import com.jetbrains.octave.psi.OctaveElementImpl;
import com.jetbrains.octave.psi.api.OctaveReferenceExpression;
import com.jetbrains.octave.reference.OctaveReferenceImpl;

public class OctaveReferenceExpressionImpl extends OctaveElementImpl implements OctaveReferenceExpression {
  public OctaveReferenceExpressionImpl(ASTNode astNode) {
    super(astNode);
  }

  @Override
  public PsiReference getReference() {
    return new OctaveReferenceImpl(this);
  }

  @Override
  public String getName() {
    return getText();
  }

}