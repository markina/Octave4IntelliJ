package com.jetbrains.octave.run;

import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SettingsEditor;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class OctaveRunConfigurationEditor extends SettingsEditor<OctaveRunConfiguration> {
  private OctaveRunConfigurationForm myForm;

  public OctaveRunConfigurationEditor(final OctaveRunConfiguration configuration) {
    myForm = new OctaveRunConfigurationForm(configuration);
  }

  @Override
  protected void resetEditorFrom(final OctaveRunConfiguration config) {
    OctaveRunConfiguration.copyParams(config, myForm);
  }

  @Override
  protected void applyEditorTo(final OctaveRunConfiguration config) throws ConfigurationException {
    OctaveRunConfiguration.copyParams(myForm, config);
  }

  @Override
  @NotNull
  protected JComponent createEditor() {
    return myForm.getPanel();
  }

  @Override
  protected void disposeEditor() {
    myForm = null;
  }
}