package com.github.paohaijiao.ast.root;

/**
 * Table row. Children are th or td cells.
 */
public class JQuickTableRowNode extends JQuickAstNode {

    public JQuickTableRowNode() {
        super("tr");
    }

    public JQuickTableRowNode(JQuickStyleAstNode styleMap) {
        super("tr", styleMap);
    }

    public JQuickTableRowNode(JQuickStyleAstNode styleMap, JQuickAstNode... children) {
        super("tr", styleMap, children);
    }
}
