package com.jetbrains.octave;

import com.intellij.openapi.fileTypes.LanguageFileType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class OctaveFileType extends LanguageFileType {
  public static final OctaveFileType INSTANCE = new OctaveFileType();

  private OctaveFileType() {
    super(OctaveLanguage.INSTANCE);
  }

  @NotNull
  @Override
  public String getName() {
    return "Octave file";
  }

  @NotNull
  @Override
  public String getDescription() {
    return "Octave language file";
  }

  @NotNull
  @Override
  public String getDefaultExtension() {
    return "m";
  }

  @Nullable
  @Override
  public Icon getIcon() {
    return OctaveIcons.FILE;
  }
}
