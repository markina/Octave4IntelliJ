package ru.compscicenter.jetbrains.octave;

import com.intellij.formatting.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.TokenType;
import com.intellij.psi.formatter.common.AbstractBlock;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.compscicenter.jetbrains.octave.psi.OctaveTypes;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Markina Margarita on 03.10.14.
 */
public class OctaveBlock extends AbstractBlock{
  private SpacingBuilder spacingBuilder;

  protected OctaveBlock(@NotNull ASTNode node, @Nullable Wrap wrap, @Nullable Alignment alignment,
                        SpacingBuilder spacingBuilder) {
    super(node, wrap, alignment);
    this.spacingBuilder = spacingBuilder;
  }

  @Override
  protected List<Block> buildChildren() {
    List<Block> blocks = new ArrayList<Block>();
    ASTNode child = myNode.getFirstChildNode();
    ASTNode previousChild = null;
    while (child != null) {
      if (child.getElementType() != TokenType.WHITE_SPACE &&
          (previousChild == null || previousChild.getElementType() != OctaveTypes.CRLF ||
           child.getElementType() != OctaveTypes.CRLF)) {
        Block block = new OctaveBlock(child, Wrap.createWrap(WrapType.NONE, false), Alignment.createAlignment(),
                                      spacingBuilder);
        blocks.add(block);
      }
      previousChild = child;
      child = child.getTreeNext();
    }
    return blocks;
  }

  @Override
  public Indent getIndent() {
    return Indent.getNoneIndent();
  }

  @Nullable
  @Override
  public Spacing getSpacing(@Nullable Block child1, @NotNull Block child2) {
    return spacingBuilder.getSpacing(this, child1, child2);
  }

  @Override
  public boolean isLeaf() {
    return myNode.getFirstChildNode() == null;
  }
}
