package ru.compscicenter.jetbrains.octave.psi.stubs;

import com.intellij.psi.stubs.IStubElementType;
import com.intellij.psi.stubs.StubBase;
import com.intellij.psi.stubs.StubElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.compscicenter.jetbrains.octave.psi.api.OctaveAssignmentStatement;

/**
 * Created by Markina Margarita on 18.11.14.
 */
public class OctaveAssignmentStubImpl extends StubBase<OctaveAssignmentStatement> implements OctaveAssignmentStub {
  private final String myName;
  //private final boolean isFunction;

  public OctaveAssignmentStubImpl(@Nullable final String name,
                                  @NotNull final StubElement parent,
                                  @NotNull IStubElementType stubElementType,
                                  boolean isFunctionDefinition) {
    super(parent, stubElementType);
    myName = name;
    //isFunction = isFunctionDefinition;
  }

  @Override
  public String getName() {
    return myName;
  }

  @Override
  public String toString() {
    return "OctaveAssignmentStub(" + myName + ")";
  }

  //@Override
  //public boolean isFunctionDeclaration() {
  //  return isFunction;
  //}
}
