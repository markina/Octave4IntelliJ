package ru.compscicenter.jetbrains.octave;

import com.intellij.lang.annotation.Annotation;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.openapi.editor.SyntaxHighlighterColors;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiLiteralExpression;
import org.jetbrains.annotations.NotNull;
import ru.compscicenter.jetbrains.octave.psi.OctaveProperty;

import java.util.List;

/**
 * Created by Markina Margarita on 02.10.14.
 */
public class OctaveAnnotator implements Annotator {
  @Override
  public void annotate(@NotNull final PsiElement element, @NotNull AnnotationHolder holder) {
    if (element instanceof PsiLiteralExpression) {
      PsiLiteralExpression literalExpression = (PsiLiteralExpression)element;
      String value = (String)literalExpression.getValue();
      if (value != null && value.startsWith("octave:")) {
        Project project = element.getProject();
        String key = value.substring(7);
        List<OctaveProperty> properties = OctaveUtil.findProperties(project, key);
        if (properties.size() == 1) {
          TextRange range = new TextRange(element.getTextRange().getStartOffset() + 7,
                                          element.getTextRange().getStartOffset() + 7);
          Annotation annotation = holder.createInfoAnnotation(range, null);
          annotation.setTextAttributes(SyntaxHighlighterColors.LINE_COMMENT);
        }
        else if (properties.size() == 0) {
          TextRange range = new TextRange(element.getTextRange().getStartOffset() + 8,
                                          element.getTextRange().getEndOffset());
          holder.createErrorAnnotation(range, "Unresolved property").
            registerFix(new CreatePropertyQuickFix(key));
        }
      }
    }
  }
}

