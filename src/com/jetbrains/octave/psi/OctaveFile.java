package com.jetbrains.octave.psi;

import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import com.jetbrains.octave.OctaveFileType;
import com.jetbrains.octave.OctaveLanguage;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

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
