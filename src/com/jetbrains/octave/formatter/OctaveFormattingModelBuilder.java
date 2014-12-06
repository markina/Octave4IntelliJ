package com.jetbrains.octave.formatter;

import com.intellij.formatting.*;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.codeStyle.CodeStyleSettings;
import com.jetbrains.octave.lexer.OctaveTokenTypes;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class OctaveFormattingModelBuilder implements FormattingModelBuilder {
  @NotNull
  @Override
  public FormattingModel createModel(PsiElement element, CodeStyleSettings settings) {
    return FormattingModelProvider.createFormattingModelForPsiFile(element.getContainingFile(),
                                                                   new OctaveBlock(element.getNode(), Wrap.createWrap(WrapType.NONE, false),
                                                                                   Alignment.createAlignment(),
                                                                                   createSpaceBuilder(settings)), settings);
  }

  private static SpacingBuilder createSpaceBuilder(CodeStyleSettings settings) {
    return new SpacingBuilder(settings).
      around(OctaveTokenTypes.OPERATORS_WITH_SPACE).spaceIf(settings.SPACE_AROUND_ASSIGNMENT_OPERATORS).
      after(OctaveTokenTypes.COMMA).spaceIf(settings.SPACE_AROUND_ASSIGNMENT_OPERATORS).
      before(OctaveTokenTypes.APOSTROPHE).none().
      around(OctaveTokenTypes.COLON).none();
  }

  //todo -5

  @Nullable
  @Override
  public TextRange getRangeAffectingIndent(PsiFile file, int i, ASTNode node) {
    return null;
  }
}
