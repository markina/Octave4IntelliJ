// This is a generated file. Not intended for manual editing.
package ru.compscicenter.jetbrains.octave.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static ru.compscicenter.jetbrains.octave.psi.OctaveTypes.*;
import ru.compscicenter.jetbrains.octave.psi.*;
import com.intellij.navigation.ItemPresentation;

public class OctavePropertyImpl extends OctaveNamedElementImpl implements OctaveProperty {

  public OctavePropertyImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof OctaveVisitor) ((OctaveVisitor)visitor).visitProperty(this);
    else super.accept(visitor);
  }

  public String getKey() {
    return OctavePsiImplUtil.getKey(this);
  }

  public String getValue() {
    return OctavePsiImplUtil.getValue(this);
  }

  public String getName() {
    return OctavePsiImplUtil.getName(this);
  }

  public PsiElement setName(String newName) {
    return OctavePsiImplUtil.setName(this, newName);
  }

  public PsiElement getNameIdentifier() {
    return OctavePsiImplUtil.getNameIdentifier(this);
  }

  public ItemPresentation getPresentation() {
    return OctavePsiImplUtil.getPresentation(this);
  }

}
