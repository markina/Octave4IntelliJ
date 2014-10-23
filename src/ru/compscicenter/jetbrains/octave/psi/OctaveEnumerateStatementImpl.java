package ru.compscicenter.jetbrains.octave.psi;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import ru.compscicenter.jetbrains.octave.psi.api.OctaveConditionStatement;
import ru.compscicenter.jetbrains.octave.psi.api.OctaveEnumerateStatement;

/**
 * Created by Markina Margarita on 24.10.14.
 */
public class OctaveEnumerateStatementImpl extends OctaveElementImpl implements OctaveEnumerateStatement {
  public OctaveEnumerateStatementImpl(@NotNull final ASTNode astNode) {
    super(astNode);
  }
}
