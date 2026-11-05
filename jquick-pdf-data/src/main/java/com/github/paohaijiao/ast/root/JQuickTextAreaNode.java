package com.github.paohaijiao.ast.root;

/**
 * Multi-line text area, drawn as a bordered block.
 */
public class JQuickTextAreaNode extends JQuickAstNode {

    public JQuickTextAreaNode() {
        super("textArea");
    }

    public JQuickTextAreaNode(JQuickStyleAstNode styleMap) {
        super("textArea", styleMap);
    }

    public JQuickTextAreaNode(JQuickStyleAstNode styleMap, JQuickAstNode... children) {
        super("textArea", styleMap, children);
    }
}
