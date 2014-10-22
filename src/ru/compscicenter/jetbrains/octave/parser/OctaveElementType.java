package ru.compscicenter.jetbrains.octave.parser;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import ru.compscicenter.jetbrains.octave.OctaveFileType;
import ru.compscicenter.jetbrains.octave.OctaveLanguage;
import ru.compscicenter.jetbrains.octave.psi.OctaveIfStatementImpl;

/**
 * Created by Markina Margarita on 01.10.14.
 */
public class OctaveElementType extends IElementType {
  protected Class<? extends PsiElement> myPsiElementClass;

  public OctaveElementType(@NotNull @NonNls String debugName) {
    super(debugName, OctaveLanguage.INSTANCE);
  }

  public OctaveElementType(@NotNull @NonNls final String debugName, @NotNull final Class<? extends PsiElement> psiElementClass) {
    super(debugName, OctaveFileType.INSTANCE.getLanguage());
    myPsiElementClass = psiElementClass;
  }

  public PsiElement createElement(ASTNode node) {
    return null;
  }
}
