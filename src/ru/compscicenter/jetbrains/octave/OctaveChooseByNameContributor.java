package ru.compscicenter.jetbrains.octave;

import com.intellij.navigation.ChooseByNameContributor;
import com.intellij.navigation.NavigationItem;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import ru.compscicenter.jetbrains.octave.psi.OctaveProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Markina Margarita on 03.10.14.
 */
public class OctaveChooseByNameContributor implements ChooseByNameContributor {
  @NotNull
  @Override
  public String[] getNames(Project project, boolean includeNonProjectItems) {
    List<OctaveProperty> properties = OctaveUtil.findProperties(project);
    List<String> names = new ArrayList<String>(properties.size());
    for (OctaveProperty property : properties) {
      if (property.getKey() != null && property.getKey().length() > 0) {
        names.add(property.getKey());
      }
    }
    return names.toArray(new String[names.size()]);
  }

  @NotNull
  @Override
  public NavigationItem[] getItemsByName(String name, String pattern, Project project, boolean includeNonProjectItems) {
    // todo include non project items
    List<OctaveProperty> properties = OctaveUtil.findProperties(project, name);
    return properties.toArray(new NavigationItem[properties.size()]);
  }
}
