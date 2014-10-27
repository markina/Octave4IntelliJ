package ru.compscicenter.jetbrains.octave.psi.api.impl;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import ru.compscicenter.jetbrains.octave.psi.OctaveElementImpl;
import ru.compscicenter.jetbrains.octave.psi.api.OctaveFunctionNameStatement;
import ru.compscicenter.jetbrains.octave.psi.api.OctaveParforStatement;

/**
 * Created by Markina Margarita on 27.10.14.
 */
public class OctaveParforStatementImpl  extends OctaveElementImpl implements OctaveParforStatement {
  public OctaveParforStatementImpl(@NotNull final ASTNode astNode) {
    super(astNode);
  }
}