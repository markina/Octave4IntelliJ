package com.jetbrains.octave.psi.api.impl;

import com.intellij.extapi.psi.StubBasedPsiElementBase;
import com.intellij.lang.ASTNode;
import com.intellij.psi.stubs.IStubElementType;
import com.intellij.psi.stubs.StubElement;
import com.jetbrains.octave.psi.api.OctaveElement;

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
