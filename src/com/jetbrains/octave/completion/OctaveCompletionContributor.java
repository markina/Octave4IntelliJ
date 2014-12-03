package com.jetbrains.octave.completion;

import com.intellij.codeInsight.completion.*;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.patterns.PlatformPatterns;
import com.intellij.util.ProcessingContext;
import com.jetbrains.octave.OctaveLanguage;
import com.jetbrains.octave.lexer.OctaveTokenTypes;
import org.jetbrains.annotations.NotNull;

public class OctaveCompletionContributor extends CompletionContributor{
  public OctaveCompletionContributor() {
    extend(CompletionType.BASIC,
           PlatformPatterns.psiElement(OctaveTokenTypes.IDENTIFIER).withLanguage(OctaveLanguage.INSTANCE),
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
