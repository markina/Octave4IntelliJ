package ru.compscicenter.jetbrains.octave.psi.api.impl;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import ru.compscicenter.jetbrains.octave.psi.OctaveElementImpl;
import ru.compscicenter.jetbrains.octave.psi.api.OctaveSwitchStatement;
import ru.compscicenter.jetbrains.octave.psi.api.OctaveWhileStatement;

/**
 * Created by Markina Margarita on 24.10.14.
 */
public class OctaveSwitchStatementImpl extends OctaveElementImpl implements OctaveSwitchStatement {
  public OctaveSwitchStatementImpl(@NotNull final ASTNode astNode) {
    super(astNode);
  }
}