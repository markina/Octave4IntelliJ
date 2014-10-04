// This is a generated file. Not intended for manual editing.
package ru.compscicenter.jetbrains.octave.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import ru.compscicenter.jetbrains.octave.psi.impl.*;

public interface OctaveTypes {

  IElementType PROPERTY = new OctaveElementType("PROPERTY");

  IElementType COMMENT = new OctaveTokenType("COMMENT");
  IElementType CRLF = new OctaveTokenType("CRLF");
  IElementType KEY = new OctaveTokenType("KEY");
  IElementType SEPARATOR = new OctaveTokenType("SEPARATOR");
  IElementType VALUE = new OctaveTokenType("VALUE");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
      if (type == PROPERTY) {
        return new OctavePropertyImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
