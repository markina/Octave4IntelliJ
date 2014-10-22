package ru.compscicenter.jetbrains.octave.psi;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import ru.compscicenter.jetbrains.octave.psi.api.OctaveIdentifierStatement;
import ru.compscicenter.jetbrains.octave.psi.api.OctaveIfStatement;

/**
 * Created by Markina Margarita on 23.10.14.
 */
public class OctaveIdentifierStatementImpl extends OctaveElementImpl implements OctaveIdentifierStatement {
  public OctaveIdentifierStatementImpl(@NotNull final ASTNode astNode) {
    super(astNode);
  }
}
