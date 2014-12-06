package com.jetbrains.octave.completion;

import com.intellij.codeInsight.completion.*;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.patterns.PlatformPatterns;
import com.intellij.util.ProcessingContext;
import com.jetbrains.octave.OctaveLanguage;
import com.jetbrains.octave.lexer.OctaveTokenTypes;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class OctaveCompletionContributor extends CompletionContributor {
  String[] keywords = new String[]{
    "end_try_catch",
    "end_unwind_protect",
    "endclassdef",
    "endenumeration",
    "endfor",
    "endfunction",
    "endif",
    "endmethods",
    "endparfor",
    "endswitch",
    "endwhile",
    "enumeration",
    "for",
    "function",
    "if",
    "methods",
    "try",
    "until",
    "unwind_protect",
    "unwind_protect_cleanup",
    "while",
    "endevents",
    "events",
    "endproperties",
    "properties",
    "otherwise",
    "parfor",
    "switch",
    "persistent",
    "static",
    "global",
    "return",
    "break",
    "case",
    "catch",
    "classdef",
    "continue",
    "do",
    "else",
    "elseif",
    "end",
    "NA",
    "inf",
    "Inf",
    "true",
    "false",
    "NaN",
    "nan",
    "e",
    "pi",
    "eps",
    "realmax",
    "realmin"
  };

  public OctaveCompletionContributor() {
    extend(CompletionType.BASIC,
           PlatformPatterns.psiElement(OctaveTokenTypes.IDENTIFIER).withLanguage(OctaveLanguage.INSTANCE),
           new CompletionProvider<CompletionParameters>() {
             public void addCompletions(@NotNull CompletionParameters parameters,
                                        ProcessingContext context,
                                        @NotNull CompletionResultSet resultSet) {
               List<LookupElementBuilder> array = new ArrayList<>();
               for (int i = 0; i < keywords.length; i++) {
                 array.add(LookupElementBuilder.create(keywords[i]));
               }
               resultSet.addAllElements(array);
             }
           }
    );
  }
}
