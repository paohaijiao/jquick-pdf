package com.github.paohaijiao.ast.root;

/**
 * Paragraph: plain text, or text mixed with spans.
 */
public class JQuickParagraphNode extends JQuickAstNode {

    public JQuickParagraphNode() {
        super("p");
    }

    public JQuickParagraphNode(JQuickStyleAstNode styleMap) {
        super("p", styleMap);
    }

    public JQuickParagraphNode(JQuickStyleAstNode styleMap, JQuickAstNode... children) {
        super("p", styleMap, children);
    }
}
