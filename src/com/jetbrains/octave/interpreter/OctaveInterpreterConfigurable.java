package com.jetbrains.octave.interpreter;

import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory;
import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SearchableConfigurable;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.TextFieldWithBrowseButton;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;

public class OctaveInterpreterConfigurable implements SearchableConfigurable, Configurable.NoScroll{
  private JPanel myMainPanel;
  private final Project myProject;
  private final TextFieldWithBrowseButton myInterpreterField;
  public static final String OCTAVE_SKELETONS = "Octave Skeletons";

  OctaveInterpreterConfigurable(Project project) {
    myProject = project;

    final GridBagLayout layout = new GridBagLayout();
    myMainPanel = new JPanel(layout);
    final JLabel interpreterLabel = new JLabel("Interpreter:");
    myInterpreterField = new TextFieldWithBrowseButton();
    final FileChooserDescriptor interpreterDescriptor = FileChooserDescriptorFactory.createSingleLocalFileDescriptor();
    myInterpreterField.addBrowseFolderListener("Choose interpreter path", "Choose interpreter path", myProject, interpreterDescriptor);

    final GridBagConstraints c = new GridBagConstraints();
    c.fill = GridBagConstraints.HORIZONTAL;
    c.insets = new Insets(2,2,2,2);
    c.anchor = GridBagConstraints.NORTH;

    c.gridx = 0;
    c.gridy = 1;
    c.weightx = 0.;
    c.weighty = 1.;
    final JPanel interpreterPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    interpreterPanel.add(interpreterLabel);
    myMainPanel.add(interpreterPanel, c);

    c.gridx = 1;
    c.gridy = 1;
    c.weightx = 1.;
    myMainPanel.add(myInterpreterField, c);
  }

  @NotNull
  @Override
  public String getId() {
    return getClass().getName();
  }

  @Nullable
  @Override
  public Runnable enableSearch(String option) {
    return null;
  }

  @Nls
  @Override
  public String getDisplayName() {
    return "Octave Interpreter";
  }

  @Nullable
  @Override
  public String getHelpTopic() {
    return null;
  }

  @Nullable
  @Override
  public JComponent createComponent() {
    return myMainPanel;
  }

  @Override
  public boolean isModified() {
    final OctaveInterpreterService interpreterService = OctaveInterpreterService.getInstance();
    return !interpreterService.getInterpreterPath().equals(myInterpreterField.getText());
  }

  @Override
  public void apply() throws ConfigurationException {
    final OctaveInterpreterService interpreterService = OctaveInterpreterService.getInstance();
    final String interpreterPath = myInterpreterField.getText();
    interpreterService.setInterpreterPath(interpreterPath);
  }

  @Override
  public void reset() {
    final OctaveInterpreterService interpreterService = OctaveInterpreterService.getInstance();
    final String interpreterPath = interpreterService.getInterpreterPath();
    myInterpreterField.setText(interpreterPath != null ? interpreterPath : "");
  }

  @Override
  public void disposeUIResources() {
  }

}