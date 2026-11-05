package com.github.paohaijiao.ast.root;

/**
 * Tab, moves to the next tab stop. No children.
 */
public class JQuickTabNode extends JQuickAstNode {

    public JQuickTabNode() {
        super("tab");
    }

    public JQuickTabNode(JQuickStyleAstNode styleMap) {
        super("tab", styleMap);
    }
}
