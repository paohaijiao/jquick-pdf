package com.github.paohaijiao.ast.root;

/**
 * Page break. Content {@code next_page} starts a new page.
 */
public class JQuickPageBreakNode extends JQuickAstNode {

    public JQuickPageBreakNode() {
        super("htmlPageBreak");
    }

    public JQuickPageBreakNode(JQuickStyleAstNode styleMap) {
        super("htmlPageBreak", styleMap);
    }

    public JQuickPageBreakNode(JQuickStyleAstNode styleMap, JQuickAstNode... children) {
        super("htmlPageBreak", styleMap, children);
    }
}
