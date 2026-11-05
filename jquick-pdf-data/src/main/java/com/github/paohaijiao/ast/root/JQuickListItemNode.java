package com.github.paohaijiao.ast.root;

/**
 * List item, child of {@link JQuickListNode}.
 */
public class JQuickListItemNode extends JQuickAstNode {

    public JQuickListItemNode() {
        super("li");
    }

    public JQuickListItemNode(JQuickStyleAstNode styleMap) {
        super("li", styleMap);
    }

    public JQuickListItemNode(JQuickStyleAstNode styleMap, JQuickAstNode... children) {
        super("li", styleMap, children);
    }
}
