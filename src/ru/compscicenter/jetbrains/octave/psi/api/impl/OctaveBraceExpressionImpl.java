package ru.compscicenter.jetbrains.octave.psi.api.impl;

import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;
import ru.compscicenter.jetbrains.octave.psi.OctaveElementImpl;
import ru.compscicenter.jetbrains.octave.psi.api.OctaveBraceStatement;

/**
 * Created by Markina Margarita on 10.11.14.
 */
public class OctaveBraceExpressionImpl extends OctaveElementImpl implements OctaveBraceStatement {
  public OctaveBraceExpressionImpl(@NotNull final ASTNode astNode) {
    super(astNode);
  }
}
