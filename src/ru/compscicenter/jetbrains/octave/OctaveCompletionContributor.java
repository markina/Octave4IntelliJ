package ru.compscicenter.jetbrains.octave;

import com.intellij.codeInsight.completion.*;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.patterns.PlatformPatterns;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;
import ru.compscicenter.jetbrains.octave.psi.OctaveTypes;

/**
 * Created by Markina Margarita on 02.10.14.
 */
public class OctaveCompletionContributor extends CompletionContributor{
  public OctaveCompletionContributor() {
    extend(CompletionType.BASIC,
           PlatformPatterns.psiElement(OctaveTypes.VALUE).withLanguage(OctaveLanguage.INSTANCE),
           new CompletionProvider<CompletionParameters>() {
             public void addCompletions(@NotNull CompletionParameters parameters,
                                        ProcessingContext context,
                                        @NotNull CompletionResultSet resultSet) {
               resultSet.addElement(LookupElementBuilder.create("Hello"));
             }
           }
    );
  }
}
