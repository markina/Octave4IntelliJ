package com.jetbrains.octave.psi.api.impl;

import com.intellij.lang.ASTNode;
import com.jetbrains.octave.psi.OctaveElementImpl;
import com.jetbrains.octave.psi.api.OctaveSwitchParameter;
import org.jetbrains.annotations.NotNull;

public class OctaveSwitchParameterImpl extends OctaveElementImpl implements OctaveSwitchParameter {
  public OctaveSwitchParameterImpl(@NotNull final ASTNode astNode) {
    super(astNode);
  }
}
