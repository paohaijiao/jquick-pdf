package com.github.paohaijiao.ast.root;

/**
 * Table. Children must be {@link JQuickTableRowNode}.
 */
public class JQuickTableNode extends JQuickAstNode {

    public JQuickTableNode() {
        super("table");
    }

    public JQuickTableNode(JQuickStyleAstNode styleMap) {
        super("table", styleMap);
    }

    public JQuickTableNode(JQuickStyleAstNode styleMap, JQuickAstNode... children) {
        super("table", styleMap, children);
    }
}
