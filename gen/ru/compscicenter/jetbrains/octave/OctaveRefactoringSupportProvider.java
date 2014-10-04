package ru.compscicenter.jetbrains.octave;

import com.intellij.lang.refactoring.RefactoringSupportProvider;
import com.intellij.psi.PsiElement;
import ru.compscicenter.jetbrains.octave.psi.OctaveProperty;

/**
 * Created by Markina Margarita on 02.10.14.
 */
public class OctaveRefactoringSupportProvider extends RefactoringSupportProvider{
  @Override
  public boolean isMemberInplaceRenameAvailable(PsiElement element, PsiElement context) {
    return element instanceof OctaveProperty;
  }
}
