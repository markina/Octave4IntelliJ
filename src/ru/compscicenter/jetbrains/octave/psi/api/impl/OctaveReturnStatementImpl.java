package ru.compscicenter.jetbrains.octave.psi.api.impl;

import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;
import ru.compscicenter.jetbrains.octave.psi.OctaveElementImpl;
import ru.compscicenter.jetbrains.octave.psi.api.OctaveReturnStatement;

/**
 * Created by Markina Margarita on 05.11.14.
 */
public class OctaveReturnStatementImpl  extends OctaveElementImpl implements OctaveReturnStatement {
  public OctaveReturnStatementImpl(@NotNull final ASTNode astNode) {
    super(astNode);
  }
}