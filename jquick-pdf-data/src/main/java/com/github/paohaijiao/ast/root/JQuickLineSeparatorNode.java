package com.github.paohaijiao.ast.root;

/**
 * Horizontal separator line.
 */
public class JQuickLineSeparatorNode extends JQuickAstNode {

    public JQuickLineSeparatorNode() {
        super("lineSeparator");
    }

    public JQuickLineSeparatorNode(JQuickStyleAstNode styleMap) {
        super("lineSeparator", styleMap);
    }

    public JQuickLineSeparatorNode(JQuickStyleAstNode styleMap, JQuickAstNode... children) {
        super("lineSeparator", styleMap, children);
    }
}
