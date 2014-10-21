package ru.compscicenter.jetbrains.octave.parser;

import com.intellij.psi.tree.IElementType;
import ru.compscicenter.jetbrains.octave.psi.OctaveIfStatementImpl;

/**
 * Created by Markina Margarita on 17.10.14.
 */
public interface OctaveElementTypes {

  OctaveElementType IF_STATEMENT = new OctaveElementType("IF_STATEMENT", OctaveIfStatementImpl.class);
}
