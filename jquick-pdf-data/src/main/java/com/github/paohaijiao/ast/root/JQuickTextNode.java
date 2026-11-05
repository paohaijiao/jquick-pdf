package com.github.paohaijiao.ast.root;

/**
 * Text node. Quotes the content by default, bare Chinese text does not match the
 * identifier rule. Use {@link #raw(String)} for {@code ${var}} placeholders.
 */
public class JQuickTextNode extends JQuickAstNode {

    /**
     * Plain text content.
     */
    private final String text;
    /**
     * True to quote the output (default), false to print raw.
     */
    private final boolean quoted;

    public JQuickTextNode(String text) {
        this(text, true);
    }

    public JQuickTextNode(String text, boolean quoted) {
        super("#text");
        this.text = text;
        this.quoted = quoted;
    }

    /**
     * Raw constructor for placeholders like {@code ${var}} or {@code &name}.
     */
    public static JQuickTextNode raw(String text) {
        return new JQuickTextNode(text, false);
    }

    public String getText() {
        return text;
    }

    public boolean isQuoted() {
        return quoted;
    }

    /**
     * Text has no children: one indented line.
     */
    @Override
    protected void render(StringBuilder sb, int indent) {
        if (sb.length() > 0) {
            sb.append('\n');
        }
        appendIndent(sb, indent);
        sb.append(formatted());
    }

    /**
     * Same-line output for inline content.
     */
    @Override
    protected void renderInline(StringBuilder sb) {
        sb.append(formatted());
    }

    @Override
    protected void renderCompact(StringBuilder sb) {
        sb.append(formatted());
    }

    /**
     * Prints the text quoted or raw.
     */
    private String formatted() {
        if (text == null || text.isEmpty()) {
            return "";
        }
        return quoted ? "'" + text + "'" : text;
    }
}
