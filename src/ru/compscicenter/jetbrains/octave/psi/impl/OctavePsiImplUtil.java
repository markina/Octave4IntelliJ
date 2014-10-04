package ru.compscicenter.jetbrains.octave.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.navigation.ItemPresentation;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.Nullable;
import ru.compscicenter.jetbrains.octave.OctaveIcons;
import ru.compscicenter.jetbrains.octave.psi.OctaveElementFactory;
import ru.compscicenter.jetbrains.octave.psi.OctaveProperty;
import ru.compscicenter.jetbrains.octave.psi.OctaveTypes;

import javax.swing.*;

/**
 * Created by Markina Margarita on 02.10.14.
 */
public class OctavePsiImplUtil {
  public static String getKey(OctaveProperty element) {
    ASTNode keyNode = element.getNode().findChildByType(OctaveTypes.KEY);
    if (keyNode != null) {
      return keyNode.getText();
    } else {
      return null;
    }
  }

  public static String getValue(OctaveProperty element) {
    ASTNode valueNode = element.getNode().findChildByType(OctaveTypes.VALUE);
    if (valueNode != null) {
      return valueNode.getText();
    } else {
      return null;
    }
  }

  public static String getName(OctaveProperty element) {
    return getKey(element);
  }

  public static PsiElement setName(OctaveProperty element, String newName) {
    ASTNode keyNode = element.getNode().findChildByType(OctaveTypes.KEY);
    if (keyNode != null) {

      OctaveProperty property = OctaveElementFactory.createProperty(element.getProject(), newName);
      ASTNode newKeyNode = property.getFirstChild().getNode();
      element.getNode().replaceChild(keyNode, newKeyNode);
    }
    return element;
  }

  public static PsiElement getNameIdentifier(OctaveProperty element) {
    ASTNode keyNode = element.getNode().findChildByType(OctaveTypes.KEY);
    if (keyNode != null) {
      return keyNode.getPsi();
    } else {
      return null;
    }
  }

  public static ItemPresentation getPresentation(final OctaveProperty element) {
    return new ItemPresentation() {
      @Nullable
      @Override
      public String getPresentableText() {
        return element.getKey();
      }

      @Nullable
      @Override
      public String getLocationString() {
        return element.getContainingFile().getName();
      }

      @Nullable
      @Override
      public Icon getIcon(boolean unused) {
        return OctaveIcons.FILE;
      }
    };
  }
}
