package ru.compscicenter.jetbrains.octave.psi.api.impl;

import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;
import ru.compscicenter.jetbrains.octave.psi.OctaveElementImpl;
import ru.compscicenter.jetbrains.octave.psi.api.OctaveBinaryExpression;

/**
 * Created by Markina Margarita on 08.11.14.
 */
public class OctaveBinaryExpressionImpl extends OctaveElementImpl implements OctaveBinaryExpression {
  public OctaveBinaryExpressionImpl(@NotNull final ASTNode astNode) {
    super(astNode);
  }
}