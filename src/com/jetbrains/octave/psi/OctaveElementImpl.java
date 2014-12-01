package com.jetbrains.octave.psi;

import com.intellij.lang.ASTNode;
import com.intellij.psi.stubs.StubElement;
import com.jetbrains.octave.psi.api.impl.OctaveBaseElementImpl;

public class OctaveElementImpl extends OctaveBaseElementImpl<StubElement> {
  public OctaveElementImpl(ASTNode astNode) {
    super(astNode);
  }
}
