package ru.compscicenter.jetbrains.octave.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;
import ru.compscicenter.jetbrains.octave.psi.OctaveNamedElement;

/**
 * Created by Markina Margarita on 02.10.14.
 */
public abstract class OctaveNamedElementImpl extends ASTWrapperPsiElement implements OctaveNamedElement{
  public OctaveNamedElementImpl(@NotNull ASTNode node) {
    super(node);
  }
}
