package ru.compscicenter.jetbrains.octave.psi.api.impl;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiReference;
import ru.compscicenter.jetbrains.octave.psi.OctaveElementImpl;
import ru.compscicenter.jetbrains.octave.psi.api.OctaveReferenceExpression;
import ru.compscicenter.jetbrains.octave.psi.reference.OctaveReferenceImpl;

/**
 * Created by Markina Margarita on 18.11.14.
 */
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
    final String text = getText();
    return text;
  }

}