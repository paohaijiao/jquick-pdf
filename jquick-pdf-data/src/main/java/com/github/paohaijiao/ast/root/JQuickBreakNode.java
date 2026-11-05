package com.github.paohaijiao.ast.root;

/**
 * Line break, printed as {@code <br/>}.
 */
public class JQuickBreakNode extends JQuickAstNode {

    public JQuickBreakNode() {
        super("br");
    }

    public JQuickBreakNode(JQuickStyleAstNode styleMap) {
        super("br", styleMap);
    }

    @Override
    protected boolean isSelfClosing() {
        return true;
    }
}
