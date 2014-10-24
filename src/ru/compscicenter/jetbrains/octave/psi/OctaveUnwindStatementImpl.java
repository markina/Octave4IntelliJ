package ru.compscicenter.jetbrains.octave.psi;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import ru.compscicenter.jetbrains.octave.psi.api.OctaveUnwindStatement;

/**
 * Created by Markina Margarita on 24.10.14.
 */
public class OctaveUnwindStatementImpl extends OctaveElementImpl implements OctaveUnwindStatement {
  public OctaveUnwindStatementImpl(@NotNull final ASTNode astNode) {
    super(astNode);
  }
}