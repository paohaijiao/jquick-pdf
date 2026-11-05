package com.github.paohaijiao.ast.root;

/**
 * Data cell. Content stays on the tag line, same as header cells.
 * An svg inside a cell is not rendered, draw progress bars in a div instead.
 */
public class JQuickTableCellNode extends JQuickAstNode {

    public JQuickTableCellNode() {
        super("td");
    }

    public JQuickTableCellNode(JQuickStyleAstNode styleMap) {
        super("td", styleMap);
    }

    public JQuickTableCellNode(JQuickStyleAstNode styleMap, JQuickAstNode... children) {
        super("td", styleMap, children);
    }

    /**
     * Cell content is always written inline.
     */
    @Override
    protected boolean isInlineContent() {
        return true;
    }
}
