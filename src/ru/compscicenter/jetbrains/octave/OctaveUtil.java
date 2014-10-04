package ru.compscicenter.jetbrains.octave;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiManager;
import com.intellij.psi.search.FileTypeIndex;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.indexing.FileBasedIndex;
import ru.compscicenter.jetbrains.octave.psi.OctaveFile;
import ru.compscicenter.jetbrains.octave.psi.OctaveProperty;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by Markina Margarita on 02.10.14.
 */
public class OctaveUtil {
  public static List<OctaveProperty> findProperties(Project project, String key) {
    List<OctaveProperty> result = null;
    Collection<VirtualFile> virtualFiles = FileBasedIndex.getInstance().getContainingFiles(FileTypeIndex.NAME, OctaveFileType.INSTANCE,
                                                                                           GlobalSearchScope.allScope(project));
    for (VirtualFile virtualFile : virtualFiles) {
      OctaveFile octaveFile = (OctaveFile) PsiManager.getInstance(project).findFile(virtualFile);
      if (octaveFile != null) {
        OctaveProperty[] properties = PsiTreeUtil.getChildrenOfType(octaveFile, OctaveProperty.class);
        if (properties != null) {
          for (OctaveProperty property : properties) {
            if (key.equals(property.getKey())) {
              if (result == null) {
                result = new ArrayList<OctaveProperty>();
              }
              result.add(property);
            }
          }
        }
      }
    }
    return result != null ? result : Collections.<OctaveProperty>emptyList();
  }

  public static List<OctaveProperty> findProperties(Project project) {
    List<OctaveProperty> result = new ArrayList<OctaveProperty>();
    Collection<VirtualFile> virtualFiles = FileBasedIndex.getInstance().getContainingFiles(FileTypeIndex.NAME, OctaveFileType.INSTANCE,
                                                                                           GlobalSearchScope.allScope(project));
    for (VirtualFile virtualFile : virtualFiles) {
      OctaveFile octaveFile = (OctaveFile) PsiManager.getInstance(project).findFile(virtualFile);
      if (octaveFile != null) {
        OctaveProperty[] properties = PsiTreeUtil.getChildrenOfType(octaveFile, OctaveProperty.class);
        if (properties != null) {
          Collections.addAll(result, properties);
        }
      }
    }
    return result;
  }
}

