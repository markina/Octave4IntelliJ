package ru.compscicenter.jetbrains.octave.psi.api.impl;

import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;
import ru.compscicenter.jetbrains.octave.psi.OctaveElementImpl;
import ru.compscicenter.jetbrains.octave.psi.api.OctaveBooleanLiteral;

/**
 * Created by Markina Margarita on 18.11.14.
 */
public class OctaveBooleanLiteralImpl extends OctaveElementImpl implements OctaveBooleanLiteral {
  public OctaveBooleanLiteralImpl(@NotNull final ASTNode astNode) {
    super(astNode);
  }
}
