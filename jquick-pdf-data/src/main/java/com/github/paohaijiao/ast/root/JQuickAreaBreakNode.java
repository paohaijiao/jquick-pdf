package com.github.paohaijiao.ast.root;

/**
 * Area break. Following content moves to the next region.
 */
public class JQuickAreaBreakNode extends JQuickAstNode {

    public JQuickAreaBreakNode() {
        super("areaBreak");
    }

    public JQuickAreaBreakNode(JQuickStyleAstNode styleMap) {
        super("areaBreak", styleMap);
    }

    public JQuickAreaBreakNode(JQuickStyleAstNode styleMap, JQuickAstNode... children) {
        super("areaBreak", styleMap, children);
    }
}
