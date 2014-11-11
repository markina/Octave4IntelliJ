package ru.compscicenter.jetbrains.octave.reference;

import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.*;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.compscicenter.jetbrains.octave.psi.api.OctaveAssignmentExpression;
import ru.compscicenter.jetbrains.octave.psi.api.OctaveIdentifier;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Markina Margarita on 11.11.14.
 */
public class OctaveReferenceImpl implements PsiReference, PsiPolyVariantReference {
  private static final Logger LOG = Logger.getInstance(OctaveReferenceImpl.class.getName());
  protected final OctaveIdentifier myElement;

  public OctaveReferenceImpl(OctaveIdentifier identifier) {
    myElement = identifier;
  }

  @NotNull
  @Override
  public ResolveResult[] multiResolve(boolean incompleteCode) {
    final List<ResolveResult> result = new ArrayList<ResolveResult>();
    final String name = myElement.getName();
    if (name == null) return ResolveResult.EMPTY_ARRAY;

    final PsiFile file = myElement.getContainingFile();
    final OctaveAssignmentExpression[] statements = PsiTreeUtil.getChildrenOfType(file, OctaveAssignmentExpression.class);
    if (statements != null) {
      for (OctaveAssignmentExpression statement : statements) {
        final PsiElement assignee = statement.getAssignee();
        if (assignee != null && assignee.getText().equals(name)) {
          result.add(new PsiElementResolveResult(assignee));
        }
      }
    }

    return result.toArray(new ResolveResult[result.size()]);
  }

  @Override
  public PsiElement getElement() {
    return null;
  }

  @Override
  public TextRange getRangeInElement() {
    return null;
  }

  @Nullable
  @Override
  public PsiElement resolve() {
    return null;
  }

  @NotNull
  @Override
  public String getCanonicalText() {
    return null;
  }

  @Override
  public PsiElement handleElementRename(String newElementName) throws IncorrectOperationException {
    return null;
  }

  @Override
  public PsiElement bindToElement(@NotNull PsiElement element) throws IncorrectOperationException {
    return null;
  }

  @Override
  public boolean isReferenceTo(PsiElement element) {
    return false;
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
