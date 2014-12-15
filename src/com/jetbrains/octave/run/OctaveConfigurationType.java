package com.jetbrains.octave.run;

import com.intellij.execution.configurations.ConfigurationFactory;
import com.intellij.execution.configurations.ConfigurationType;
import com.intellij.execution.configurations.ConfigurationTypeUtil;
import com.intellij.openapi.util.IconLoader;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class OctaveConfigurationType implements ConfigurationType {
  private final OctaveConfigurationFactory myFactory = new OctaveConfigurationFactory(this);

  public static OctaveConfigurationType getInstance() {
    return ConfigurationTypeUtil.findConfigurationType(OctaveConfigurationType.class);
  }

  @Override
  public String getDisplayName() {
    return "Octave script";
  }

  @Override
  public String getConfigurationTypeDescription() {
    return "The Octave run configuration";
  }

  @Override
  public Icon getIcon() {
    return IconLoader.getIcon("/logo.png");
  }

  @Override
  public ConfigurationFactory[] getConfigurationFactories() {
    return new ConfigurationFactory[]{myFactory};
  }

  @Override
  @NotNull
  @NonNls
  public String getId() {
    return "OctaveConfigurationType";
  }
}
