package com.github.paohaijiao.ast.root;

/**
 * Inline fragment inside a paragraph, applies local style without breaking the line.
 */
public class JQuickSpanNode extends JQuickAstNode {

    public JQuickSpanNode() {
        super("span");
    }

    public JQuickSpanNode(JQuickStyleAstNode styleMap) {
        super("span", styleMap);
    }

    public JQuickSpanNode(JQuickStyleAstNode styleMap, JQuickAstNode... children) {
        super("span", styleMap, children);
    }
}
