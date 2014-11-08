package ru.compscicenter.jetbrains.octave.psi.api.impl;

import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;
import ru.compscicenter.jetbrains.octave.psi.OctaveElementImpl;
import ru.compscicenter.jetbrains.octave.psi.api.OctaveIdentifier;

/**
 * Created by Markina Margarita on 08.11.14.
 */
public class OctaveIdentifierImpl extends OctaveElementImpl implements OctaveIdentifier {
  public OctaveIdentifierImpl(@NotNull final ASTNode astNode) {
    super(astNode);
  }
}