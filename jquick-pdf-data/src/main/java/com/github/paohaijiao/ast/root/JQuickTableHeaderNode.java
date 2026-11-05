package com.github.paohaijiao.ast.root;

/**
 * Header cell. Content stays on the tag line so no stray text lands in the cell.
 */
public class JQuickTableHeaderNode extends JQuickAstNode {

    public JQuickTableHeaderNode() {
        super("th");
    }

    public JQuickTableHeaderNode(JQuickStyleAstNode styleMap) {
        super("th", styleMap);
    }

    public JQuickTableHeaderNode(JQuickStyleAstNode styleMap, JQuickAstNode... children) {
        super("th", styleMap, children);
    }

    /**
     * Header content is always written inline.
     */
    @Override
    protected boolean isInlineContent() {
        return true;
    }
}
