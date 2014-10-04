package ru.compscicenter.jetbrains.octave.psi;

import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import org.jetbrains.annotations.NotNull;
import ru.compscicenter.jetbrains.octave.OctaveFileType;
import ru.compscicenter.jetbrains.octave.OctaveLanguage;

import javax.swing.*;

/**
 * Created by Markina Margarita on 02.10.14.
 */
public class OctaveFile extends PsiFileBase {
  public OctaveFile(@NotNull FileViewProvider viewProvider) {
    super(viewProvider, OctaveLanguage.INSTANCE);
  }

  @NotNull
  @Override
  public FileType getFileType() {
    return OctaveFileType.INSTANCE;
  }

  @Override
  public String toString() {
    return "Octave File";
  }

  @Override
  public Icon getIcon(int flags) {
    return super.getIcon(flags);
  }
}
