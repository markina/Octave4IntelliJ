package ru.compscicenter.jetbrains.octave;

import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import com.intellij.openapi.fileTypes.SyntaxHighlighterFactory;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;

/**
 * Created by Markina Margarita on 02.10.14.
 */
public class OctaveSyntaxHighlighterFactory extends SyntaxHighlighterFactory{

  @NotNull
  @Override
  public SyntaxHighlighter getSyntaxHighlighter(Project project, VirtualFile virtualFile) {
    return new OctaveSyntaxHighlighter();
  }
}
