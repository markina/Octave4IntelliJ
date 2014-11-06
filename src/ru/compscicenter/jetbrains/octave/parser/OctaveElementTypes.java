package ru.compscicenter.jetbrains.octave.parser;

import ru.compscicenter.jetbrains.octave.psi.api.impl.OctaveFunctionStatementImpl;
import ru.compscicenter.jetbrains.octave.psi.api.impl.*;

/**
 * Created by Markina Margarita on 17.10.14.
 */
public interface OctaveElementTypes {

  OctaveElementType IF_STATEMENT = new OctaveElementType("IF_STATEMENT", OctaveIfStatementImpl.class);
  OctaveElementType ELSEIF_STATEMENT = new OctaveElementType("ELSEIF_STATEMENT", OctaveElseifStatementImpl.class);
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
  OctaveElementType CLASSDEF_STATEMENT = new OctaveElementType("CLASSDEF_STATEMENT", OctaveClassDefStatementImpl.class);
  OctaveElementType CLASS_NAME_STATEMENT = new OctaveElementType("CLASS_NAME_STATEMENT", OctaveClassNameStatementImpl.class);
  OctaveElementType METHODS_NAME_STATEMENT = new OctaveElementType("METHODS_NAME_STATEMENT", OctaveMethodsNameStatementImpl.class);
  OctaveElementType METHODS_STATEMENT = new OctaveElementType("METHODS_STATEMENT", OctaveMethodsStatementImpl.class);
  OctaveElementType ENUMERATION_STATEMENT = new OctaveElementType("ENUMERATION_STATEMENT", OctaveEnumerationStatementImpl.class);
  OctaveElementType PROPERTIES_STATEMENT = new OctaveElementType("PROPERTIES_STATEMENT", OctavePropertiesStatementImpl.class);
  OctaveElementType EVENTS_STATEMENT = new OctaveElementType("EVENTS_STATEMENT", OctaveEventsStatementImpl.class);
  OctaveElementType RETURN_STATEMENT = new OctaveElementType("RETURN_STATEMENT", OctaveReturnStatementImpl.class);
}
