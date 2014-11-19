package ru.compscicenter.jetbrains.octave.psi.reference;

import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.*;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.compscicenter.jetbrains.octave.psi.api.OctaveAssignmentStatement;
import ru.compscicenter.jetbrains.octave.psi.api.OctaveReferenceExpression;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Markina Margarita on 11.11.14.
 */
public class OctaveReferenceImpl extends PsiReferenceBase {
  private static final Logger LOG = Logger.getInstance(OctaveReferenceImpl.class.getName());

  public OctaveReferenceImpl(OctaveReferenceExpression expression) {
    super(expression);
  }

  @NotNull
  public ResolveResult[] multiResolve(boolean incompleteCode) {
    final List<ResolveResult> result = new ArrayList<ResolveResult>();
    final String name = myElement.getText();
    if (name == null) return ResolveResult.EMPTY_ARRAY;

    final PsiFile file = myElement.getContainingFile();
    final OctaveAssignmentStatement[] statements = PsiTreeUtil.getChildrenOfType(file, OctaveAssignmentStatement.class);
    if (statements != null) {
      for (OctaveAssignmentStatement statement : statements) {
        final PsiElement assignee = statement.getAssignee();
        if (assignee != null && assignee.getText().equals(name)) {
          result.add(new PsiElementResolveResult(assignee));
        }
      }
    }

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
    return results.length >= 1 ? results[0].getElement() : null;
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
