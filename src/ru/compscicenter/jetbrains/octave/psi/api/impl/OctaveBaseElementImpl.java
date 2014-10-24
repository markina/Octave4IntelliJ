package ru.compscicenter.jetbrains.octave.psi.api.impl;

import com.intellij.extapi.psi.StubBasedPsiElementBase;
import com.intellij.lang.ASTNode;
import com.intellij.psi.stubs.IStubElementType;
import com.intellij.psi.stubs.StubElement;
import ru.compscicenter.jetbrains.octave.psi.api.OctaveElement;

/**
 * Created by Markina Margarita on 22.10.14.
 */
public class OctaveBaseElementImpl<T extends StubElement> extends StubBasedPsiElementBase<T> implements OctaveElement {
  public OctaveBaseElementImpl(T stub, IStubElementType nodeType) {
    super(stub, nodeType);
  }

  public OctaveBaseElementImpl(ASTNode node) {
    super(node);
  }

  @Override
  public String toString() {
    return getNode().getElementType().toString();
  }
}
