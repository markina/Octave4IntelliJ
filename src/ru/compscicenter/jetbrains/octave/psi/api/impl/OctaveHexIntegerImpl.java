package ru.compscicenter.jetbrains.octave.psi.api.impl;

import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;
import ru.compscicenter.jetbrains.octave.psi.OctaveElementImpl;
import ru.compscicenter.jetbrains.octave.psi.api.OctaveHexInteger;

/**
 * Created by Markina Margarita on 10.11.14.
 */
public class OctaveHexIntegerImpl extends OctaveElementImpl implements OctaveHexInteger {
  public OctaveHexIntegerImpl(@NotNull final ASTNode astNode) {
    super(astNode);
  }
}

