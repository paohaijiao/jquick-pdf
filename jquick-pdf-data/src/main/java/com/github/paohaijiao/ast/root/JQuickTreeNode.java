package com.github.paohaijiao.ast.root;

/**
 * Tree control, fed from a variable or an {@code &{id}} address.
 */
public class JQuickTreeNode extends JQuickAstNode {

    public JQuickTreeNode() {
        super("tree");
    }

    public JQuickTreeNode(JQuickStyleAstNode styleMap) {
        super("tree", styleMap);
    }

    public JQuickTreeNode(JQuickStyleAstNode styleMap, JQuickAstNode... children) {
        super("tree", styleMap, children);
    }
}
