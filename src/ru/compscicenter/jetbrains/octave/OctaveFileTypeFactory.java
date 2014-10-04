package ru.compscicenter.jetbrains.octave;

import com.intellij.openapi.fileTypes.FileTypeConsumer;
import com.intellij.openapi.fileTypes.FileTypeFactory;
import org.jetbrains.annotations.NotNull;

/**
 * Created by Markina Margarita on 01.10.14.
 */
public class OctaveFileTypeFactory extends FileTypeFactory{

  @Override
  public void createFileTypes(@NotNull FileTypeConsumer fileTypeConsumer) {
    fileTypeConsumer.consume(OctaveFileType.INSTANCE, "m");
  }
}
