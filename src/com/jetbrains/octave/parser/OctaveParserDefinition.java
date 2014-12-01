package com.jetbrains.octave.parser;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.lang.ParserDefinition;
import com.intellij.lang.PsiParser;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.project.Project;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.IFileElementType;
import com.intellij.psi.tree.TokenSet;
import com.jetbrains.octave.OctaveLanguage;
import com.jetbrains.octave.lexer.OctaveLexerAdapter;
import com.jetbrains.octave.lexer.OctaveTokenTypes;
import com.jetbrains.octave.psi.OctaveFile;
import com.jetbrains.octave.psi.stubs.OctaveStubElementType;
import org.jetbrains.annotations.NotNull;

public class OctaveParserDefinition implements ParserDefinition {
  public static final TokenSet myCommentTokens = TokenSet.create(OctaveTokenTypes.COMMENT);
  public static final TokenSet myStringLiteralTokens = TokenSet.create(OctaveTokenTypes.STRING);
  public static final TokenSet myWhitespaceTokens =
    TokenSet.create(OctaveTokenTypes.SPACE, OctaveTokenTypes.TAB, OctaveTokenTypes.FORMFEED);


  public static final IFileElementType FILE = new IFileElementType(OctaveLanguage.INSTANCE);

  @NotNull
  @Override
  public Lexer createLexer(Project project) {
    return new OctaveLexerAdapter();
  }

  @Override
  public PsiParser createParser(Project project) {
    return new OctaveParser();
  }

  @Override
  public IFileElementType getFileNodeType() {
    return FILE;
  }

  @NotNull
  @Override
  public TokenSet getWhitespaceTokens() {
    return myWhitespaceTokens;
  }

  @NotNull
  @Override
  public TokenSet getCommentTokens() {
    return myCommentTokens;
  }

  @NotNull
  @Override
  public TokenSet getStringLiteralElements() {
    return myStringLiteralTokens;
  }

  @NotNull
  @Override
  public PsiElement createElement(@NotNull ASTNode node) {
    final IElementType type = node.getElementType();
    if (type instanceof OctaveStubElementType) {
      OctaveStubElementType elementType = (OctaveStubElementType)type;
      return elementType.createElement(node);
    }
    else if (type instanceof OctaveElementType) {
      OctaveElementType elementType = (OctaveElementType)type;
      return elementType.createElement(node);
    }

    return new ASTWrapperPsiElement(node);
  }

  @Override
  public PsiFile createFile(FileViewProvider provider) {
    return new OctaveFile(provider);
  }

  @Override
  public SpaceRequirements spaceExistanceTypeBetweenTokens(ASTNode node, ASTNode node1) {
    return SpaceRequirements.MAY;
  }
}
