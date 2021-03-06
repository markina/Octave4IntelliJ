package com.jetbrains.octave.parser;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.IElementType;
import com.jetbrains.octave.OctaveFileType;
import com.jetbrains.octave.OctaveLanguage;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Constructor;

public class OctaveElementType extends IElementType {
  protected Class<? extends PsiElement> myPsiElementClass;
  private Constructor<? extends PsiElement> myConstructor;
  private static final Class[] PARAMETER_TYPES = new Class[]{ASTNode.class};

  public OctaveElementType(@NotNull @NonNls String debugName) {
    super(debugName, OctaveLanguage.INSTANCE);
  }

  public OctaveElementType(@NotNull @NonNls final String debugName, @NotNull final Class<? extends PsiElement> psiElementClass) {
    super(debugName, OctaveFileType.INSTANCE.getLanguage());
    myPsiElementClass = psiElementClass;
  }

  @Override
  public String toString() {
    return "Octave:" + super.toString();
  }

  public PsiElement createElement(@NotNull final ASTNode node) {

    if (myPsiElementClass == null) {
      throw new IllegalStateException("Cannot create an element for " + node.getElementType() + " without element class");
    }
    try {
      if (myConstructor == null) {
        myConstructor = myPsiElementClass.getConstructor(PARAMETER_TYPES);
      }
      return myConstructor.newInstance(node);
    }
    catch (Exception e) {
      throw new IllegalStateException("No necessary constr  uctor for " + node.getElementType(), e);
    }
  }
}
