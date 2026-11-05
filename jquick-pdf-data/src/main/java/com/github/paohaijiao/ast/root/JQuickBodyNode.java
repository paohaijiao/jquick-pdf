package com.github.paohaijiao.ast.root;

/**
 * Body container, holds everything visible.
 * Prints a blank line between blocks so long templates stay scannable.
 */
public class JQuickBodyNode extends JQuickAstNode {

    public JQuickBodyNode() {
        super("body");
    }

    public JQuickBodyNode(JQuickStyleAstNode styleMap) {
        super("body", styleMap);
    }

    public JQuickBodyNode(JQuickStyleAstNode styleMap, JQuickAstNode... children) {
        super("body", styleMap, children);
    }

    /**
     * Blank line between blocks.
     */
    @Override
    protected boolean blankLineBetweenChildren() {
        return true;
    }
}
