package com.jetbrains.octave.run;

import com.intellij.execution.configurations.ConfigurationFactory;
import com.intellij.execution.configurations.ConfigurationType;
import com.intellij.execution.configurations.RunConfiguration;
import com.intellij.openapi.project.Project;

public class OctaveConfigurationFactory extends ConfigurationFactory {
  protected OctaveConfigurationFactory(ConfigurationType configurationType) {
    super(configurationType);
  }

  @Override
  public RunConfiguration createTemplateConfiguration(Project project) {
    return new OctaveRunConfiguration(project, this);
  }
}