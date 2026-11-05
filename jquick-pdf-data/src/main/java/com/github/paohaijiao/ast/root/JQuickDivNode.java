package com.github.paohaijiao.ast.root;

/**
 * Container. Holds paragraphs, tables, images or nested divs.
 */
public class JQuickDivNode extends JQuickAstNode {

    public JQuickDivNode() {
        super("div");
    }

    public JQuickDivNode(JQuickStyleAstNode styleMap) {
        super("div", styleMap);
    }

    public JQuickDivNode(JQuickStyleAstNode styleMap, JQuickAstNode... children) {
        super("div", styleMap, children);
    }
}
