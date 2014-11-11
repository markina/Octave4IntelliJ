package ru.compscicenter.jetbrains.octave.psi.api.impl;

import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;
import ru.compscicenter.jetbrains.octave.psi.OctaveElementImpl;
import ru.compscicenter.jetbrains.octave.psi.api.OctaveStringLiteral;

/**
 * Created by Markina Margarita on 11.11.14.
 */
public class OctaveStringLiteralImpl extends OctaveElementImpl implements OctaveStringLiteral {
  public OctaveStringLiteralImpl(@NotNull final ASTNode astNode) {
    super(astNode);
  }
}