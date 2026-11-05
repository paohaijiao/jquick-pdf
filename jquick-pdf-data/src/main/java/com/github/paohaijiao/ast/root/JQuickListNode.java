package com.github.paohaijiao.ast.root;

/**
 * List. Children are li nodes. listType is optional and printed after style.
 */
public class JQuickListNode extends JQuickAstNode {

    /**
     * List type, omitted when null.
     */
    private final String listType;

    public JQuickListNode() {
        this(null, null);
    }

    public JQuickListNode(JQuickStyleAstNode styleMap) {
        this(styleMap, null);
    }

    public JQuickListNode(JQuickStyleAstNode styleMap, String listType) {
        super("list", styleMap);
        this.listType = listType;
    }

    public JQuickListNode(JQuickStyleAstNode styleMap, String listType, JQuickAstNode... children) {
        this(styleMap, listType);
        addChildren(children);
    }

    /**
     * Printed after style.
     */
    @Override
    protected void appendAttrsAfterStyle(StringBuilder sb) {
        if (listType != null && !listType.isEmpty()) {
            sb.append(" listType=").append(listType);
        }
    }
}
