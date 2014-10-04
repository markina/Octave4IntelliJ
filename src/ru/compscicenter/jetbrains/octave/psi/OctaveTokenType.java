package ru.compscicenter.jetbrains.octave.psi;

import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import ru.compscicenter.jetbrains.octave.OctaveLanguage;

/**
 * Created by Markina Margarita on 01.10.14.
 */
public class OctaveTokenType extends IElementType {
  public OctaveTokenType(@NotNull @NonNls String debugName) {
    super(debugName, OctaveLanguage.INSTANCE);
  }

  @Override
  public String toString() {
    return "OctaveTokenType." + super.toString();
  }
}
