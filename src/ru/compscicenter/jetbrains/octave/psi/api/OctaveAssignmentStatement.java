package ru.compscicenter.jetbrains.octave.psi.api;

import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiNamedElement;
import com.intellij.psi.StubBasedPsiElement;
import org.jetbrains.annotations.Nullable;
import ru.compscicenter.jetbrains.octave.psi.stubs.OctaveAssignmentStub;

/**
 * Created by Markina Margarita on 11.11.14.
 */
public interface OctaveAssignmentStatement extends OctaveElement, PsiNamedElement, StubBasedPsiElement<OctaveAssignmentStub> {

  @Nullable
  public PsiElement getAssignee();

  @Nullable
  public OctaveElement getAssignedValue();
}
