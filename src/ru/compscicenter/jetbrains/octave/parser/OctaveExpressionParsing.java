package ru.compscicenter.jetbrains.octave.parser;

import com.intellij.lang.PsiBuilder;
import com.intellij.psi.tree.IElementType;
import ru.compscicenter.jetbrains.octave.lexer.OctaveTokenTypes;

/**
 * Created by Markina Margarita on 21.10.14.
 */
public class OctaveExpressionParsing extends Parsing{
  public OctaveExpressionParsing(OctaveParserContext context) {
    super(context);
  }

  public void parseExpressionStatement() {
    final IElementType firstToken = myPsiBuilder.getTokenType();

    if(firstToken == null) return;

    if(firstToken == OctaveTokenTypes.IF_KEYWORD) {
      parseIfExpression();
    }


  }

  private void parseIfExpression() {
    final PsiBuilder.Marker ifExpression = myPsiBuilder.mark();
    parseConditionExpression();
    parseExpressionStatement();
    if(myPsiBuilder.getTokenType() == OctaveTokenTypes.ELSE_KEYWORD) {
      myPsiBuilder.advanceLexer();
      parseExpressionStatement();
    }
    ifExpression.done(OctaveElementTypes.IF_STATEMENT);
  }

  private void parseConditionExpression() {
    parseExpressionStatement();
  }
}
