package ru.compscicenter.jetbrains.octave.parser;

import com.intellij.psi.tree.IElementType;
import ru.compscicenter.jetbrains.octave.psi.OctaveConditionStatementImpl;
import ru.compscicenter.jetbrains.octave.psi.OctaveIdentifierStatementImpl;
import ru.compscicenter.jetbrains.octave.psi.OctaveIfStatementImpl;
import ru.compscicenter.jetbrains.octave.psi.api.OctaveIfStatement;

/**
 * Created by Markina Margarita on 17.10.14.
 */
public interface OctaveElementTypes {

  OctaveElementType IF_STATEMENT = new OctaveElementType("IF_STATEMENT", OctaveIfStatementImpl.class);
  OctaveElementType CONDITION_STATEMENT = new OctaveElementType("CONDITION_STATEMENT", OctaveConditionStatementImpl.class);
  OctaveElementType IDENTIFIER_STATEMENT = new OctaveElementType("IDENTIFIER_STATEMENT", OctaveIdentifierStatementImpl.class); //for example
}
