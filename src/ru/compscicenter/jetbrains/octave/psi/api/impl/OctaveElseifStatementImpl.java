package ru.compscicenter.jetbrains.octave.psi.api.impl;

import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;
import ru.compscicenter.jetbrains.octave.psi.OctaveElementImpl;
import ru.compscicenter.jetbrains.octave.psi.api.OctaveElseifStatement;

/**
 * Created by Markina Margarita on 06.11.14.
 */
public class OctaveElseifStatementImpl extends OctaveElementImpl implements OctaveElseifStatement {
  public OctaveElseifStatementImpl(@NotNull final ASTNode astNode) {
    super(astNode);
  }
}
