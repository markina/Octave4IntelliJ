package ru.compscicenter.jetbrains.octave.parser;

import com.intellij.lang.ASTNode;
import com.intellij.lang.Language;
import com.intellij.lang.ParserDefinition;
import com.intellij.lang.PsiParser;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.project.Project;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IFileElementType;
import com.intellij.psi.tree.TokenSet;
import org.jetbrains.annotations.NotNull;
import ru.compscicenter.jetbrains.octave.OctaveLanguage;
import ru.compscicenter.jetbrains.octave.lexer.OctaveLexerAdapter;
import ru.compscicenter.jetbrains.octave.parser.OctaveParser;
import ru.compscicenter.jetbrains.octave.psi.OctaveFile;
import ru.compscicenter.jetbrains.octave.psi.OctaveTypes;

/**
 * Created by Markina Margarita on 02.10.14.
 */
public class OctaveParserDefinition implements ParserDefinition{
  public static final TokenSet COMMENTS = TokenSet.create(OctaveTypes.COMMENT);

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
    return TokenSet.create(TokenType.WHITE_SPACE);
  }

  @NotNull
  @Override
  public TokenSet getCommentTokens() {
    return COMMENTS;
  }

  @NotNull
  @Override
  public TokenSet getStringLiteralElements() {
    return TokenSet.EMPTY;
  }

  @NotNull
  @Override
  public PsiElement createElement(ASTNode node) {
    return OctaveTypes.Factory.createElement(node);
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
