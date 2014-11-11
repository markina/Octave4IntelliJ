package ru.compscicenter.jetbrains.octave.psi.api.impl;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.compscicenter.jetbrains.octave.psi.OctaveElementImpl;
import ru.compscicenter.jetbrains.octave.psi.api.OctaveAssignmentExpression;

/**
 * Created by Markina Margarita on 11.11.14.
 */
public class OctaveAssignmentExpressionImpl extends OctaveElementImpl implements OctaveAssignmentExpression {
  public OctaveAssignmentExpressionImpl(@NotNull final ASTNode astNode) {
    super(astNode);
  }

  @Nullable
  @Override
  public PsiElement getAssignee() {

  }

  public ASTNode getNameNode() {
    
  }
}