package ru.compscicenter.jetbrains.octave.psi;

import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;
import ru.compscicenter.jetbrains.octave.psi.api.OctaveTryStatement;

/**
 * Created by Markina Margarita on 24.10.14.
 */
public class OctaveTryStatementImpl extends OctaveElementImpl implements OctaveTryStatement {
  public OctaveTryStatementImpl(@NotNull final ASTNode astNode) {
    super(astNode);
  }
}