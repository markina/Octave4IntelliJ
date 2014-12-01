package com.jetbrains.octave.psi.stubs;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.stubs.IStubElementType;
import com.intellij.psi.stubs.IndexSink;
import com.intellij.psi.stubs.StubElement;
import com.jetbrains.octave.OctaveFileType;
import com.jetbrains.octave.psi.api.OctaveElement;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public abstract class OctaveStubElementType<StubT extends StubElement, PsiT extends OctaveElement> extends IStubElementType<StubT, PsiT> {
  public OctaveStubElementType(@NonNls final String debugName) {
    super(debugName, OctaveFileType.INSTANCE.getLanguage());
  }

  @Override
  public String toString() {
    return "Octave:" + super.toString();
  }

  public abstract PsiElement createElement(@NotNull final ASTNode node);

  @Override
  public void indexStub(@NotNull final StubT stub, @NotNull final IndexSink sink) {
  }

  @Override
  @NotNull
  public String getExternalId() {
    return "octave." + super.toString();
  }
}
