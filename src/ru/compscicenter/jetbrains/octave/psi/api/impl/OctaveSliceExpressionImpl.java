package ru.compscicenter.jetbrains.octave.psi.api.impl;

import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;
import ru.compscicenter.jetbrains.octave.psi.OctaveElementImpl;
import ru.compscicenter.jetbrains.octave.psi.api.OctaveSliceStatement;

/**
 * Created by Markina Margarita on 09.11.14.
 */
public class OctaveSliceExpressionImpl extends OctaveElementImpl implements OctaveSliceStatement {
  public OctaveSliceExpressionImpl(@NotNull final ASTNode astNode) {
    super(astNode);
  }
}
