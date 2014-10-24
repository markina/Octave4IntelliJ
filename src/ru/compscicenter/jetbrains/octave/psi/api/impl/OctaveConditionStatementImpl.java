package ru.compscicenter.jetbrains.octave.psi.api.impl;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import ru.compscicenter.jetbrains.octave.psi.OctaveElementImpl;
import ru.compscicenter.jetbrains.octave.psi.api.OctaveConditionStatement;
import ru.compscicenter.jetbrains.octave.psi.api.OctaveIfStatement;

/**
 * Created by Markina Margarita on 23.10.14.
 */
public class OctaveConditionStatementImpl extends OctaveElementImpl implements OctaveConditionStatement {
  public OctaveConditionStatementImpl(@NotNull final ASTNode astNode) {
    super(astNode);
  }
}
