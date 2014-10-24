package ru.compscicenter.jetbrains.octave.psi.api.impl;

import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;
import ru.compscicenter.jetbrains.octave.psi.OctaveElementImpl;
import ru.compscicenter.jetbrains.octave.psi.api.OctaveIfStatement;

/**
 * Created by Markina Margarita on 22.10.14.
 */
public class OctaveIfStatementImpl extends OctaveElementImpl implements OctaveIfStatement {
  public OctaveIfStatementImpl(@NotNull final ASTNode astNode) {
    super(astNode);
  }
}
