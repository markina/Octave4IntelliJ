package ru.compscicenter.jetbrains.octave.psi.api.impl;

import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;
import ru.compscicenter.jetbrains.octave.psi.OctaveElementImpl;
import ru.compscicenter.jetbrains.octave.psi.api.OctaveSwitchParameter;

/**
 * Created by Markina Margarita on 24.10.14.
 */
public class OctaveSwitchParameterImpl extends OctaveElementImpl implements OctaveSwitchParameter {
  public OctaveSwitchParameterImpl(@NotNull final ASTNode astNode) {
    super(astNode);
  }
}