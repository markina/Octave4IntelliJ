package ru.compscicenter.jetbrains.octave;

import com.intellij.codeInsight.intention.impl.BaseIntentionAction;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.fileChooser.FileChooser;
import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.pom.Navigatable;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;
import ru.compscicenter.jetbrains.octave.psi.OctaveElementFactory;
import ru.compscicenter.jetbrains.octave.psi.OctaveFile;
import ru.compscicenter.jetbrains.octave.psi.OctaveProperty;
import ru.compscicenter.jetbrains.octave.psi.OctaveTypes;

/**
 * Created by Markina Margarita on 03.10.14.
 */
public class CreatePropertyQuickFix extends BaseIntentionAction {
  private String key;

  CreatePropertyQuickFix(String key) {
    this.key = key;
  }

  @NotNull
  @Override
  public String getText() {
    return "Create property";
  }

  @NotNull
  @Override
  public String getFamilyName() {
    return "Octave properties";
  }

  @Override
  public boolean isAvailable(@NotNull Project project, Editor editor, PsiFile file) {
    return true;
  }

  @Override
  public void invoke(@NotNull final Project project, final Editor editor, PsiFile file) throws IncorrectOperationException {
    ApplicationManager.getApplication().invokeLater(new Runnable() {
      @Override
      public void run() {
        final FileChooserDescriptor descriptor = FileChooserDescriptorFactory.createSingleFileDescriptor(OctaveFileType.INSTANCE);
        descriptor.setRoots(project.getBaseDir());
        final VirtualFile file = FileChooser.chooseFile(descriptor, project, null);
        if (file != null) {
          ApplicationManager.getApplication().runWriteAction(new Runnable() {
            @Override
            public void run() {
              OctaveFile OctaveFile = (OctaveFile)PsiManager.getInstance(project).findFile(file);
              ASTNode lastChildNode = OctaveFile.getNode().getLastChildNode();
              if (lastChildNode != null && !lastChildNode.getElementType().equals(OctaveTypes.CRLF)) {
                OctaveFile.getNode().addChild(OctaveElementFactory.createCRLF(project).getNode());
              }
              OctaveProperty property = OctaveElementFactory.createProperty(project, key, "");
              OctaveFile.getNode().addChild(property.getNode());
              ((Navigatable)property.getLastChild().getNavigationElement()).navigate(true);
              FileEditorManager.getInstance(project).getSelectedTextEditor().getCaretModel().
                moveCaretRelatively(2, 0, false, false, false);
            }
          });
        }
      }
    });
  }
}
