package ru.compscicenter.jetbrains.octave.psi.api.impl;

import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;
import ru.compscicenter.jetbrains.octave.psi.OctaveElementImpl;
import ru.compscicenter.jetbrains.octave.psi.api.OctavePrefixExpression;

/**
 * Created by Markina Margarita on 09.11.14.
 */
public class OctavePrefixExpressionImpl extends OctaveElementImpl implements OctavePrefixExpression {
  public OctavePrefixExpressionImpl(@NotNull final ASTNode astNode) {
    super(astNode);
  }
}
