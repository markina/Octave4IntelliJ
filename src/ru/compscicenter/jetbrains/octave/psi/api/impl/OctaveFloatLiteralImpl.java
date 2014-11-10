package ru.compscicenter.jetbrains.octave.psi.api.impl;

import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;
import ru.compscicenter.jetbrains.octave.psi.OctaveElementImpl;
import ru.compscicenter.jetbrains.octave.psi.api.OctaveFloatLiteral;

/**
 * Created by Markina Margarita on 10.11.14.
 */
public class OctaveFloatLiteralImpl extends OctaveElementImpl implements OctaveFloatLiteral {
  public OctaveFloatLiteralImpl(@NotNull final ASTNode astNode) {
    super(astNode);
  }
}
