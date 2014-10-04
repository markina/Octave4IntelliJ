package ru.compscicenter.jetbrains.octave;

import com.intellij.ide.structureView.StructureViewTreeElement;
import com.intellij.ide.util.treeView.smartTree.SortableTreeElement;
import com.intellij.ide.util.treeView.smartTree.TreeElement;
import com.intellij.navigation.ItemPresentation;
import com.intellij.navigation.NavigationItem;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiNamedElement;
import com.intellij.psi.util.PsiTreeUtil;
import ru.compscicenter.jetbrains.octave.psi.OctaveFile;
import ru.compscicenter.jetbrains.octave.psi.OctaveProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Markina Margarita on 03.10.14.
 */
public class OctaveStructureViewElement implements StructureViewTreeElement, SortableTreeElement {
  private PsiElement element;

  public OctaveStructureViewElement(PsiElement element) {
    this.element = element;
  }

  @Override
  public Object getValue() {
    return element;
  }

  @Override
  public void navigate(boolean requestFocus) {
    if (element instanceof NavigationItem) {
      ((NavigationItem) element).navigate(requestFocus);
    }
  }

  @Override
  public boolean canNavigate() {
    return element instanceof NavigationItem &&
           ((NavigationItem)element).canNavigate();
  }

  @Override
  public boolean canNavigateToSource() {
    return element instanceof NavigationItem &&
           ((NavigationItem)element).canNavigateToSource();
  }

  @Override
  public String getAlphaSortKey() {
    return element instanceof PsiNamedElement ? ((PsiNamedElement) element).getName() : null;
  }

  @Override
  public ItemPresentation getPresentation() {
    return element instanceof NavigationItem ?
           ((NavigationItem) element).getPresentation() : null;
  }

  @Override
  public TreeElement[] getChildren() {
    if (element instanceof OctaveFile) {
      OctaveProperty[] properties = PsiTreeUtil.getChildrenOfType(element, OctaveProperty.class);
      List<TreeElement> treeElements = new ArrayList<TreeElement>(properties.length);
      for (OctaveProperty property : properties) {
        treeElements.add(new OctaveStructureViewElement(property));
      }
      return treeElements.toArray(new TreeElement[treeElements.size()]);
    } else {
      return EMPTY_ARRAY;
    }
  }
}
