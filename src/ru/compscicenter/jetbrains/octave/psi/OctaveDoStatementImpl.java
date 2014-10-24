package ru.compscicenter.jetbrains.octave.psi;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import ru.compscicenter.jetbrains.octave.psi.api.OctaveDoStatement;

/**
 * Created by Markina Margarita on 24.10.14.
 */
public class OctaveDoStatementImpl extends OctaveElementImpl implements OctaveDoStatement {
  public OctaveDoStatementImpl(@NotNull final ASTNode astNode) {
    super(astNode);
  }
}
