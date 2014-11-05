package ru.compscicenter.jetbrains.octave.psi.api.impl;

import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;
import ru.compscicenter.jetbrains.octave.psi.OctaveElementImpl;
import ru.compscicenter.jetbrains.octave.psi.api.OctaveMethodNameStatement;

/**
 * Created by Markina Margarita on 05.11.14.
 */
public class OctaveMethodsNameStatementImpl extends OctaveElementImpl implements OctaveMethodNameStatement {
  public OctaveMethodsNameStatementImpl(@NotNull final ASTNode astNode) {
    super(astNode);
  }
}