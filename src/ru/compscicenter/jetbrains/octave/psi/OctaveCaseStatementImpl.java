package ru.compscicenter.jetbrains.octave.psi;

import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;
import ru.compscicenter.jetbrains.octave.psi.api.OctaveCaseStatement;

/**
 * Created by Markina Margarita on 24.10.14.
 */
public class OctaveCaseStatementImpl extends OctaveElementImpl implements OctaveCaseStatement {
  public OctaveCaseStatementImpl(@NotNull final ASTNode astNode) {
    super(astNode);
  }
}
