package com.jetbrains.octave.psi.api;

import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiNamedElement;
import com.intellij.psi.StubBasedPsiElement;
import com.jetbrains.octave.psi.stubs.OctaveAssignmentStub;
import org.jetbrains.annotations.Nullable;

public interface OctaveAssignmentStatement extends OctaveElement, PsiNamedElement, StubBasedPsiElement<OctaveAssignmentStub> {

  @Nullable
  public PsiElement getAssignee();

  @Nullable
  public OctaveElement getAssignedValue();
}
