package com.github.paohaijiao.ast.root;

/**
 * Image tag: {@code <image src="..." style="..." alt="...">}.
 * src comes before style and alt after it, that order is fixed by the parser.
 */
public class JQuickImageNode extends JQuickAstNode {

    /**
     * Image source.
     */
    private final String src;
    /**
     * Alt text.
     */
    private final String alt;

    public JQuickImageNode() {
        this(null, null, null);
    }

    public JQuickImageNode(JQuickStyleAstNode styleMap) {
        this(null, null, styleMap);
    }

    public JQuickImageNode(String src, String alt, JQuickStyleAstNode styleMap) {
        super("image", styleMap);
        this.src = src;
        this.alt = alt;
    }

    public JQuickImageNode(String src, String alt, JQuickStyleAstNode styleMap, JQuickAstNode... children) {
        this(src, alt, styleMap);
        addChildren(children);
    }

    public String getSrc() {
        return src;
    }

    public String getAlt() {
        return alt;
    }

    /**
     * src is printed before style.
     */
    @Override
    protected void appendAttrsBeforeStyle(StringBuilder sb) {
        if (src != null && !src.isEmpty()) {
            sb.append(" src=\"").append(src).append('"');
        }
    }

    /**
     * alt is printed after style.
     */
    @Override
    protected void appendAttrsAfterStyle(StringBuilder sb) {
        if (alt != null && !alt.isEmpty()) {
            sb.append(" alt=\"").append(alt).append('"');
        }
    }
}
