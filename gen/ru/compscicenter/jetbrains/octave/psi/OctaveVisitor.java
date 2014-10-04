// This is a generated file. Not intended for manual editing.
package ru.compscicenter.jetbrains.octave.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.PsiElement;

public class OctaveVisitor extends PsiElementVisitor {

  public void visitProperty(@NotNull OctaveProperty o) {
    visitNamedElement(o);
  }

  public void visitNamedElement(@NotNull OctaveNamedElement o) {
    visitPsiElement(o);
  }

  public void visitPsiElement(@NotNull PsiElement o) {
    visitElement(o);
  }

}
