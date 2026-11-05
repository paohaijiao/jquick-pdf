package com.github.paohaijiao.ast.root;

/**
 * Combo box. PDFs are static, so it draws a box holding the current value.
 */
public class JQuickComboBoxNode extends JQuickAstNode {

    public JQuickComboBoxNode() {
        super("comboBoxField");
    }

    public JQuickComboBoxNode(JQuickStyleAstNode styleMap) {
        super("comboBoxField", styleMap);
    }

    public JQuickComboBoxNode(JQuickStyleAstNode styleMap, JQuickAstNode... children) {
        super("comboBoxField", styleMap, children);
    }
}
