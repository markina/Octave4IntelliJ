package com.jetbrains.octave.run;

import org.jetbrains.annotations.NotNull;

public interface OctaveRunConfigurationParams {
  String getScriptName();

  void setScriptName(@NotNull final String scriptName);

  String getScriptParameters();

  void setScriptParameters(@NotNull final String scriptParameters);

}
