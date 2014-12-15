package com.jetbrains.octave.interpreter;

import com.intellij.openapi.components.*;
import com.intellij.util.xmlb.XmlSerializerUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@State(
  name = "OctaveInterpreterService",
  storages = {
    @Storage(
      file = StoragePathMacros.APP_CONFIG + "/octaveInterpreterSettings.xml"
    )}
)
public class OctaveInterpreterService implements PersistentStateComponent<OctaveInterpreterService>{
  public String INTERPRETER_PATH = "";

  @Nullable
  @Override
  public OctaveInterpreterService getState() {
    return this;
  }

  @Override
  public void loadState(OctaveInterpreterService state) {
    XmlSerializerUtil.copyBean(state, this);
  }

  public String getInterpreterPath() {
    return INTERPRETER_PATH;
  }


  public static OctaveInterpreterService getInstance() {
    return ServiceManager.getService(OctaveInterpreterService.class);
  }

  public void setInterpreterPath(@NotNull final String path) {
    INTERPRETER_PATH = path;
  }
}
