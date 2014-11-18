package ru.compscicenter.jetbrains.octave.psi.stubs;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.stubs.*;
import com.intellij.util.io.StringRef;
import org.jetbrains.annotations.NotNull;
import ru.compscicenter.jetbrains.octave.parser.OctaveElementTypes;
import ru.compscicenter.jetbrains.octave.psi.api.OctaveAssignmentStatement;
import ru.compscicenter.jetbrains.octave.psi.api.OctaveElement;
import ru.compscicenter.jetbrains.octave.psi.api.impl.OctaveAssignmentStatementImpl;

import java.io.IOException;

/**
 * Created by Markina Margarita on 18.11.14.
 */
public class OctaveAssignmentElementType extends OctaveStubElementType<OctaveAssignmentStub, OctaveAssignmentStatement> {
  public OctaveAssignmentElementType() {
    this("ASSIGNMENT_STATEMENT");
  }

  public OctaveAssignmentElementType(@NotNull final String debugName) {
    super(debugName);
  }

  @Override
  public PsiElement createElement(@NotNull final ASTNode node) {
    return new OctaveAssignmentStatementImpl(node);
  }

  @Override
  public OctaveAssignmentStatement createPsi(@NotNull final OctaveAssignmentStub stub) {
    return new OctaveAssignmentStatementImpl(stub);
  }

  @Override
  public OctaveAssignmentStub createStub(@NotNull OctaveAssignmentStatement psi, StubElement parentStub) {
    final String name = psi.getName();
    final OctaveElement value = psi.getAssignedValue();
    return new OctaveAssignmentStubImpl(name, parentStub, getStubElementType(), false);
  }

  @Override
  public void serialize(@NotNull final OctaveAssignmentStub stub, @NotNull final StubOutputStream dataStream)
    throws IOException {
    dataStream.writeName(stub.getName());
    //dataStream.writeBoolean(stub.isFunctionDeclaration());
  }

  @Override
  @NotNull
  public OctaveAssignmentStub deserialize(@NotNull final StubInputStream dataStream, final StubElement parentStub) throws IOException {
    String name = StringRef.toString(dataStream.readName());
    final boolean isFunctionDefinition = dataStream.readBoolean();
    return new OctaveAssignmentStubImpl(name, parentStub, getStubElementType(), isFunctionDefinition);
  }

  @Override
  public void indexStub(@NotNull final OctaveAssignmentStub stub, @NotNull final IndexSink sink) {
    final String name = stub.getName();
  }

  protected IStubElementType getStubElementType() {
    return OctaveElementTypes.ASSIGNMENT_STATEMENT;
  }
}
