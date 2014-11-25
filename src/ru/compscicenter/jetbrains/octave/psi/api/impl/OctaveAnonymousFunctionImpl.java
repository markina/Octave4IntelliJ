package ru.compscicenter.jetbrains.octave.psi.api.impl;

import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;
import ru.compscicenter.jetbrains.octave.psi.OctaveElementImpl;
import ru.compscicenter.jetbrains.octave.psi.api.OctaveAnonymousFunction;
import ru.compscicenter.jetbrains.octave.psi.api.OctaveBinaryExpression;

/**
 * Created by Markina Margarita on 25.11.14.
 */
public class OctaveAnonymousFunctionImpl extends OctaveElementImpl implements OctaveAnonymousFunction {
  public OctaveAnonymousFunctionImpl(@NotNull final ASTNode astNode) {
    super(astNode);
  }
}
