package ru.compscicenter.jetbrains.octave.psi.api.impl;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.compscicenter.jetbrains.octave.lexer.OctaveTokenTypes;
import ru.compscicenter.jetbrains.octave.parser.OctaveElementTypes;
import ru.compscicenter.jetbrains.octave.psi.api.OctaveAssignmentStatement;
import ru.compscicenter.jetbrains.octave.psi.api.OctaveElement;
import ru.compscicenter.jetbrains.octave.psi.stubs.OctaveAssignmentStub;

/**
 * Created by Markina Margarita on 11.11.14.
 */
public class OctaveAssignmentStatementImpl extends OctaveBaseElementImpl<OctaveAssignmentStub> implements OctaveAssignmentStatement {
  public OctaveAssignmentStatementImpl(@NotNull final ASTNode astNode) {
    super(astNode);
  }

  public OctaveAssignmentStatementImpl(@NotNull final OctaveAssignmentStub stub) {
    super(stub, OctaveElementTypes.ASSIGNMENT_STATEMENT);
  }

  @Nullable
  @Override
  public String getName() {
    final OctaveAssignmentStub stub = getStub();
    if (stub != null) {
      return stub.getName();
    }

    return null;
  }

  private boolean isAssgnment() {
    final ASTNode operator = getNode().findChildByType(OctaveTokenTypes.EQ);
    return operator != null;
  }

  @Nullable
  public ASTNode getNameNode() {
    final ASTNode node = getNode();
    if (isAssgnment()) {
      return node.findChildByType(OctaveElementTypes.REFERENCE_EXPRESSION);
    }
    for(ASTNode element = node.getLastChildNode(); element != null; element = element.getTreePrev()){
      if (element.getElementType() == OctaveElementTypes.REFERENCE_EXPRESSION) return element;
    }
    return null;
  }

  @Nullable
  @Override
  public PsiElement getAssignee() {
    final ASTNode nameNode = getNameNode();
    return nameNode != null ? nameNode.getPsi() : null;
  }

  @Nullable
  @Override
  public OctaveElement getAssignedValue() {
    return null;
  }

  @Override
  public PsiElement setName(@NotNull String name) throws IncorrectOperationException {
    return null;
  }
}