package ru.compscicenter.jetbrains.octave.psi.api.impl;

import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;
import ru.compscicenter.jetbrains.octave.psi.OctaveElementImpl;
import ru.compscicenter.jetbrains.octave.psi.api.OctaveClassNameStatement;

/**
 * Created by Markina Margarita on 05.11.14.
 */
public class OctaveClassNameStatementImpl extends OctaveElementImpl implements OctaveClassNameStatement {
  public OctaveClassNameStatementImpl(@NotNull final ASTNode astNode) {
    super(astNode);
  }
}
