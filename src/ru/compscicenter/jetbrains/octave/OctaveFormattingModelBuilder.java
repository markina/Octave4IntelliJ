package ru.compscicenter.jetbrains.octave;

import com.intellij.formatting.*;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.codeStyle.CodeStyleSettings;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.compscicenter.jetbrains.octave.psi.OctaveTypes;

/**
 * Created by Markina Margarita on 03.10.14.
 */
public class OctaveFormattingModelBuilder implements FormattingModelBuilder{
  @NotNull
  @Override
  public FormattingModel createModel(PsiElement element, CodeStyleSettings settings) {
    return FormattingModelProvider.createFormattingModelForPsiFile(element.getContainingFile(),
                                                                   new OctaveBlock(element.getNode(), Wrap.createWrap(WrapType.NONE, false),
                                                                                   Alignment.createAlignment(), createSpaceBuilder(settings)), settings);
  }

  private static SpacingBuilder createSpaceBuilder(CodeStyleSettings settings) {
    return new SpacingBuilder(settings).
      around(OctaveTypes.SEPARATOR).spaceIf(settings.SPACE_AROUND_ASSIGNMENT_OPERATORS).
      before(OctaveTypes.PROPERTY).none();
  }

  @Nullable
  @Override
  public TextRange getRangeAffectingIndent(PsiFile file, int offset, ASTNode elementAtOffset) {
    return null;
  }
}
