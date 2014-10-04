package ru.compscicenter.jetbrains.octave;

import com.intellij.codeInsight.daemon.RelatedItemLineMarkerInfo;
import com.intellij.codeInsight.daemon.RelatedItemLineMarkerProvider;
import com.intellij.codeInsight.navigation.NavigationGutterIconBuilder;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiLiteralExpression;
import org.jetbrains.annotations.NotNull;
import ru.compscicenter.jetbrains.octave.psi.OctaveProperty;

import java.util.Collection;
import java.util.List;

/**
 * Created by Markina Margarita on 02.10.14.
 */
public class OctaveLineMarkerProvider extends RelatedItemLineMarkerProvider{
  @Override
  protected void collectNavigationMarkers(@NotNull PsiElement element, Collection<? super RelatedItemLineMarkerInfo> result) {
    if (element instanceof PsiLiteralExpression) {
      PsiLiteralExpression literalExpression = (PsiLiteralExpression) element;
      String value = (String) literalExpression.getValue();
      if (value != null && value.startsWith("octave:")) {
        Project project = element.getProject();
        final List<OctaveProperty> properties = OctaveUtil.findProperties(project, value.substring(7));
        if (properties.size() > 0) {
          NavigationGutterIconBuilder<PsiElement> builder =
            NavigationGutterIconBuilder.create(OctaveIcons.FILE).
              setTargets(properties).
              setTooltipText("Navigate to a octave property");
          result.add(builder.createLineMarkerInfo(element));
        }
      }
    }
  }
}
