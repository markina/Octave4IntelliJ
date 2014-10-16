package ru.compscicenter.jetbrains.octave.parser;

import com.intellij.lang.ASTNode;
import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiParser;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;

public class OctaveParser implements PsiParser {
  @Override
  @NotNull
  public ASTNode parse(@NotNull final IElementType root, @NotNull final PsiBuilder builder) {
    final PsiBuilder.Marker rootMarker = builder.mark();

    while (!builder.eof()) {
      builder.advanceLexer();
    }
    rootMarker.done(root);
    return builder.getTreeBuilt();
  }
}
