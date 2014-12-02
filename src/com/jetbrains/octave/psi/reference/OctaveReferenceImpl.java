package com.jetbrains.octave.psi.reference;

import com.intellij.openapi.util.TextRange;
import com.intellij.psi.*;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.octave.psi.api.OctaveAssignmentStatement;
import com.jetbrains.octave.psi.api.OctaveReferenceExpression;
import com.jetbrains.octave.psi.api.impl.OctaveAssignmentStatementImpl;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class OctaveReferenceImpl extends PsiReferenceBase {

  public OctaveReferenceImpl(OctaveReferenceExpression expression) {
    super(expression);
  }

  @NotNull
  public ResolveResult[] multiResolve(boolean incompleteCode) {
    /// todo dot reference

    final List<ResolveResult> result = new ArrayList<>();
    final String name = myElement.getText();
    if (name == null) return ResolveResult.EMPTY_ARRAY;
    final PsiFile file = myElement.getContainingFile();

    int myPlace = myElement.getTextOffset();

    PsiTreeUtil.processElements(file, element -> {
      if(element instanceof OctaveAssignmentStatementImpl) {
        OctaveAssignmentStatement statement = (OctaveAssignmentStatement)element;
        final PsiElement assignee = statement.getAssignee();
        if (assignee != null && assignee.getText().equals(myElement.getText())) {
          result.add(new PsiElementResolveResult(assignee));
        }
      }
      return true;
    });

    return result.toArray(new ResolveResult[result.size()]);
  }


  @Override
  public TextRange getRangeInElement() {
    final TextRange range = myElement.getNode().getTextRange();
    return range.shiftRight(-myElement.getNode().getStartOffset());
  }

  @Nullable
  @Override
  public PsiElement resolve() {
    final ResolveResult[] results = multiResolve(false);
    int i = results.length - 1;
    for(; i >= 0; i--) {
      if(results[i].getElement().getTextOffset() < myElement.getTextOffset()) {
        break;
      }
    }
    return i >= 0 ? results[i].getElement() : null;
  }

  @NotNull
  @Override
  public Object[] getVariants() {
    return new Object[0];
  }

  @Override
  public boolean isSoft() {
    return false;
  }
}
