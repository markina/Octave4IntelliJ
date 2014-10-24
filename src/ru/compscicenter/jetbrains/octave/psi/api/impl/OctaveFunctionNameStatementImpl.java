package ru.compscicenter.jetbrains.octave.psi.api.impl;

import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;
import ru.compscicenter.jetbrains.octave.psi.OctaveElementImpl;
import ru.compscicenter.jetbrains.octave.psi.api.OctaveFunctionNameStatement;

/**
 * Created by Markina Margarita on 24.10.14.
 */
public class OctaveFunctionNameStatementImpl extends OctaveElementImpl implements OctaveFunctionNameStatement {
  public OctaveFunctionNameStatementImpl(@NotNull final ASTNode astNode) {
    super(astNode);
  }
}
