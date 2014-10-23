package ru.compscicenter.jetbrains.octave.psi;

import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;
import ru.compscicenter.jetbrains.octave.psi.api.OctaveForStatement;

/**
 * Created by Markina Margarita on 24.10.14.
 */
public class OctaveForStatementImpl extends OctaveElementImpl implements OctaveForStatement {
  public OctaveForStatementImpl(@NotNull final ASTNode astNode) {
    super(astNode);
  }
}
