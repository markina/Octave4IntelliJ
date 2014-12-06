package com.jetbrains.octave.reference;

import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.*;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.octave.OctaveIcons;
import com.jetbrains.octave.psi.api.OctaveAssignmentStatement;
import com.jetbrains.octave.psi.api.OctaveReferenceExpression;
import com.jetbrains.octave.psi.api.impl.OctaveAssignmentStatementImpl;
import com.jetbrains.octave.psi.api.impl.OctaveReferenceExpressionImpl;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    PsiTreeUtil.processElements(file, element -> {
      if (element instanceof OctaveAssignmentStatementImpl) {
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
    for (; i >= 0; i--) {
      if (results[i].getElement().getTextOffset() < myElement.getTextOffset()) {
        break;
      }
    }
    return i >= 0 ? results[i].getElement() : null;
  }

  @NotNull
  @Override
  public Object[] getVariants() {
    List<LookupElement> variants = new ArrayList<>();

    final String name = myElement.getText();
    if (name == null) return LookupElement.EMPTY_ARRAY;
    final PsiFile file = myElement.getContainingFile();

    Set<String> setNameIdentifier = new HashSet<>();

    PsiTreeUtil.processElements(file, element -> {
      if (element instanceof OctaveReferenceExpressionImpl) {
        OctaveReferenceExpression statement = (OctaveReferenceExpressionImpl)element;
        String nameIdentifier = statement.getText();

        setNameIdentifier.add(nameIdentifier);
      }
      return true;
    });


    for (String nameIdentifier : setNameIdentifier) {
      variants.add(LookupElementBuilder.create(nameIdentifier).
                     withIcon(OctaveIcons.VARIABLE).
                     withTypeText(nameIdentifier)
      );
    }

    return variants.toArray();
  }

  @Override
  public boolean isSoft() {
    return false;
  }
}
