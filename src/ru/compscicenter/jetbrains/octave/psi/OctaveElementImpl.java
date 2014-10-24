package ru.compscicenter.jetbrains.octave.psi;

import com.intellij.lang.ASTNode;
import com.intellij.psi.stubs.StubElement;
import ru.compscicenter.jetbrains.octave.psi.api.impl.OctaveBaseElementImpl;

/**
 * Created by Markina Margarita on 22.10.14.
 */
public class OctaveElementImpl extends OctaveBaseElementImpl<StubElement> {
  public OctaveElementImpl(ASTNode astNode) {
    super(astNode);
  }
}
