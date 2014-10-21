package ru.compscicenter.jetbrains.octave.parser;

import com.intellij.lang.PsiBuilder;
import org.jetbrains.annotations.NotNull;

/**
 * Created by Markina Margarita on 21.10.14.
 */
public class Parsing {
  private final OctaveParserContext myContext;
  protected final PsiBuilder myPsiBuilder;


  public Parsing(@NotNull final OctaveParserContext context) {
    myContext = context;
    myPsiBuilder = context.getBuilder();
  }
}
