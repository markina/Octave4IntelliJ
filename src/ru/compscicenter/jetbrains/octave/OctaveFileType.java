package ru.compscicenter.jetbrains.octave;

import com.intellij.openapi.fileTypes.LanguageFileType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * Created by Markina Margarita on 01.10.14.
 */
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
