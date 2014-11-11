package ru.compscicenter.jetbrains.octave.psi.api;

import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.Nullable;

/**
 * Created by Markina Margarita on 11.11.14.
 */
public interface OctaveAssignmentExpression extends OctaveElement{

  @Nullable
  public PsiElement getAssignee();
}
