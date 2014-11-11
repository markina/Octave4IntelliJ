package ru.compscicenter.jetbrains.octave.psi.api.impl;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiReference;
import org.jetbrains.annotations.NotNull;
import ru.compscicenter.jetbrains.octave.psi.OctaveElementImpl;
import ru.compscicenter.jetbrains.octave.psi.api.OctaveIdentifier;
import ru.compscicenter.jetbrains.octave.reference.OctaveReferenceImpl;

/**
 * Created by Markina Margarita on 08.11.14.
 */
public class OctaveIdentifierImpl extends OctaveElementImpl implements OctaveIdentifier {
  public OctaveIdentifierImpl(@NotNull final ASTNode astNode) {
    super(astNode);
  }

  @Override
  public PsiReference getReference() {
    return new OctaveReferenceImpl(this);
  }
}