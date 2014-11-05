package ru.compscicenter.jetbrains.octave.psi.api.impl;

import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;
import ru.compscicenter.jetbrains.octave.psi.OctaveElementImpl;
import ru.compscicenter.jetbrains.octave.psi.api.OctaveConditionStatement;

/**
 * Created by Markina Margarita on 23.10.14.
 */
public class OctaveConditionStatementImpl extends OctaveElementImpl implements OctaveConditionStatement {
  public OctaveConditionStatementImpl(@NotNull final ASTNode astNode) {
    super(astNode);
  }
}
