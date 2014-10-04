package ru.compscicenter.jetbrains.octave;

import com.intellij.lang.HelpID;
import com.intellij.lang.cacheBuilder.DefaultWordsScanner;
import com.intellij.lang.cacheBuilder.WordsScanner;
import com.intellij.lang.findUsages.FindUsagesProvider;
import com.intellij.lexer.FlexAdapter;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiNamedElement;
import com.intellij.psi.tree.TokenSet;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.compscicenter.jetbrains.octave.psi.OctaveProperty;
import ru.compscicenter.jetbrains.octave.psi.OctaveTypes;

import java.io.Reader;

/**
 * Created by Markina Margarita on 02.10.14.
 */
public class OctaveFindUsagesProvider implements FindUsagesProvider {
  private static final DefaultWordsScanner WORDS_SCANNER =
    new DefaultWordsScanner(new FlexAdapter(new OctaveLexer((Reader) null)),
                            TokenSet.create(OctaveTypes.KEY), TokenSet.create(OctaveTypes.COMMENT), TokenSet.EMPTY);

  @Nullable
  @Override
  public WordsScanner getWordsScanner() {
    return WORDS_SCANNER;
  }

  @Override
  public boolean canFindUsagesFor(@NotNull PsiElement psiElement) {
    return psiElement instanceof PsiNamedElement;
  }

  @Nullable
  @Override
  public String getHelpId(@NotNull PsiElement psiElement) {
    return HelpID.FIND_OTHER_USAGES;
  }

  @NotNull
  @Override
  public String getType(@NotNull PsiElement element) {
    if (element instanceof OctaveProperty) {
      return "octave property";
    } else {
      return "";
    }
  }

  @NotNull
  @Override
  public String getDescriptiveName(@NotNull PsiElement element) {
    if (element instanceof OctaveProperty) {
      return ((OctaveProperty) element).getKey();
    } else {
      return "";
    }
  }

  @NotNull
  @Override
  public String getNodeText(@NotNull PsiElement element, boolean useFullName) {
    if (element instanceof OctaveProperty) {
      return ((OctaveProperty) element).getKey() + ":" + ((OctaveProperty) element).getValue();
    } else {
      return "";
    }
  }
}
