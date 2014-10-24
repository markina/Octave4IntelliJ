package ru.compscicenter.jetbrains.octave.parser;

import com.intellij.psi.tree.IElementType;
import ru.compscicenter.jetbrains.octave.psi.*;

/**
 * Created by Markina Margarita on 17.10.14.
 */
public interface OctaveElementTypes {

  OctaveElementType IF_STATEMENT = new OctaveElementType("IF_STATEMENT", OctaveIfStatementImpl.class);
  OctaveElementType CONDITION_STATEMENT = new OctaveElementType("CONDITION_STATEMENT", OctaveConditionStatementImpl.class);
  OctaveElementType FOR_STATEMENT = new OctaveElementType("FOR_STATEMENT", OctaveForStatementImpl.class);
  OctaveElementType ENUMERATE_STATEMENT = new OctaveElementType("ENUMERATE_STATEMENT", OctaveEnumerateStatementImpl.class);
  OctaveElementType WHILE_STATEMENT = new OctaveElementType("WHILE_STATEMENT", OctaveWhileStatementImpl.class);
  OctaveElementType SWITCH_STATEMENT = new OctaveElementType("SWITCH_STATEMENT", OctaveSwitchStatementImpl.class);
  OctaveElementType SWITCH_PARAMETER_STATEMENT = new OctaveElementType("SWITCH_PARAMETER_STATEMENT", OctaveSwitchParameterImpl.class);
  OctaveElementType CASE_STATEMENT = new OctaveElementType("CASE_STATEMENT", OctaveCaseStatementImpl.class);
}
