package ru.compscicenter.jetbrains.octave.parser;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import ru.compscicenter.jetbrains.octave.OctaveLanguage;
import ru.compscicenter.jetbrains.octave.psi.OctaveIfStatementImpl;

/**
 * Created by Markina Margarita on 01.10.14.
 */
public class OctaveElementType extends IElementType {
  public OctaveElementType(@NotNull @NonNls String debugName) {
    super(debugName, OctaveLanguage.INSTANCE);
  }

  public OctaveElementType(String statement, Class<OctaveIfStatementImpl> aClass) {
    super(statement, aClass);
  }

  public PsiElement createElement(ASTNode node) {
    return null;
  }
}
