package ru.compscicenter.jetbrains.octave.psi.api.impl;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import ru.compscicenter.jetbrains.octave.psi.OctaveElementImpl;
import ru.compscicenter.jetbrains.octave.psi.api.OctaveFunctionStatement;
import ru.compscicenter.jetbrains.octave.psi.api.OctaveTryStatement;

/**
 * Created by Markina Margarita on 24.10.14.
 */
public class OctaveFunctionStatementImpl extends OctaveElementImpl implements OctaveFunctionStatement {
  public OctaveFunctionStatementImpl(@NotNull final ASTNode astNode) {
    super(astNode);
  }
}
