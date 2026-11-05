package com.github.paohaijiao.ast.root;

/**
 * Checkbox. Prints {@code checked} after style when set.
 */
public class JQuickCheckboxNode extends JQuickAstNode {

    /**
     * Checked state.
     */
    private final boolean checked;

    public JQuickCheckboxNode() {
        this(false, null);
    }

    public JQuickCheckboxNode(JQuickStyleAstNode styleMap) {
        this(false, styleMap);
    }

    public JQuickCheckboxNode(boolean checked, JQuickStyleAstNode styleMap) {
        super("checkbox", styleMap);
        this.checked = checked;
    }

    public JQuickCheckboxNode(boolean checked, JQuickStyleAstNode styleMap, JQuickAstNode... children) {
        this(checked, styleMap);
        addChildren(children);
    }

    public boolean isChecked() {
        return checked;
    }

    /**
     * Prints {@code checked} after style.
     */
    @Override
    protected void appendAttrsAfterStyle(StringBuilder sb) {
        if (checked) {
            sb.append(" checked");
        }
    }
}
