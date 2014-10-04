package ru.compscicenter.jetbrains.octave.psi;

import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFileFactory;
import ru.compscicenter.jetbrains.octave.OctaveFileType;

/**
 * Created by Markina Margarita on 02.10.14.
 */
public class OctaveElementFactory {
  public static OctaveProperty createProperty(Project project, String name, String value) {
    final OctaveFile file = createFile(project, name + " = " + value);
    return (OctaveProperty) file.getFirstChild();
  }

  public static OctaveProperty createProperty(Project project, String name) {
    final OctaveFile file = createFile(project, name);
    return (OctaveProperty) file.getFirstChild();
  }

  public static PsiElement createCRLF(Project project) {
    final OctaveFile file = createFile(project, "\n");
    return file.getFirstChild();
  }

  public static OctaveFile createFile(Project project, String text) {
    String name = "dummy.Octave";
    return (OctaveFile) PsiFileFactory.getInstance(project).
      createFileFromText(name, OctaveFileType.INSTANCE, text);
  }
}
