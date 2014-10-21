package ru.compscicenter.jetbrains.octave.parser;

import com.intellij.lang.ASTNode;
import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiParser;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;

public class OctaveParser implements PsiParser {
  @Override
  @NotNull
  public ASTNode parse(@NotNull final IElementType root, @NotNull final PsiBuilder builder) {
    final PsiBuilder.Marker rootMarker = builder.mark();

    final OctaveParserContext context = createParsingContext(builder);
    final OctaveExpressionParsing statementParser = context.getExpressionParser();

    while (!builder.eof()) {
      statementParser.parseExpressionStatement();
    }
    rootMarker.done(root);
    final ASTNode ast = builder.getTreeBuilt();
    return ast;
  }

  @NotNull
  private OctaveParserContext createParsingContext(PsiBuilder builder) {
    return new OctaveParserContext(builder);
  }
}
