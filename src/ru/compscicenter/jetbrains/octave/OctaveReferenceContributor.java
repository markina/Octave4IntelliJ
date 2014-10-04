package ru.compscicenter.jetbrains.octave;

import com.intellij.openapi.util.TextRange;
import com.intellij.patterns.PlatformPatterns;
import com.intellij.psi.*;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;

/**
 * Created by Markina Margarita on 02.10.14.
 */
public class OctaveReferenceContributor extends PsiReferenceContributor{
  @Override
  public void registerReferenceProviders(PsiReferenceRegistrar registrar) {
    registrar.registerReferenceProvider(PlatformPatterns.psiElement(PsiLiteralExpression.class),
                                        new PsiReferenceProvider() {
                                          @NotNull
                                          @Override
                                          public PsiReference[] getReferencesByElement(@NotNull PsiElement element, @NotNull ProcessingContext context) {
                                            PsiLiteralExpression literalExpression = (PsiLiteralExpression) element;
                                            String text = (String) literalExpression.getValue();
                                            if (text != null && text.startsWith("octave:")) {
                                              return new PsiReference[]{new OctaveReference(element, new TextRange(8, text.length() + 1))};
                                            }
                                            return new PsiReference[0];
                                          }
                                        });
  }
}
