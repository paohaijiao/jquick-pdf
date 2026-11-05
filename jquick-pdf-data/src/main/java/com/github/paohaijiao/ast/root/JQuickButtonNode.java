package com.github.paohaijiao.ast.root;

/**
 * Button, for form templates only. Drawn as a bordered area, no interaction.
 */
public class JQuickButtonNode extends JQuickAstNode {

    public JQuickButtonNode() {
        super("button");
    }

    public JQuickButtonNode(JQuickStyleAstNode styleMap) {
        super("button", styleMap);
    }

    public JQuickButtonNode(JQuickStyleAstNode styleMap, JQuickAstNode... children) {
        super("button", styleMap, children);
    }
}
