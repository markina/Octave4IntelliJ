package ru.compscicenter.jetbrains.octave.parser;

import com.intellij.psi.tree.IElementType;
import ru.compscicenter.jetbrains.octave.psi.api.impl.OctaveFunctionStatementImpl;
import ru.compscicenter.jetbrains.octave.psi.api.impl.*;

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
  OctaveElementType DO_STATEMENT = new OctaveElementType("DO_STATEMENT", OctaveDoStatementImpl.class);
  OctaveElementType UNWIND_STATEMENT = new OctaveElementType("UNWIND_STATEMENT", OctaveUnwindStatementImpl.class);
  OctaveElementType TRY_STATEMENT = new OctaveElementType("TRY_STATEMENT", OctaveTryStatementImpl.class);
  OctaveElementType FUNCTION_STATEMENT = new OctaveElementType("FUNCTION_STATEMENT", OctaveFunctionStatementImpl.class);
  OctaveElementType FUNCTION_NAME_STATEMENT = new OctaveElementType("FUNCTION_NAME_STATEMENT", OctaveFunctionNameStatementImpl.class);
  OctaveElementType PARFOR_STATEMENT = new OctaveElementType("PARFOR_STATEMENT", OctaveParforStatementImpl.class);
}
