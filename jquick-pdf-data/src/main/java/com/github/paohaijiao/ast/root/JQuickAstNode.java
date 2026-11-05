package com.github.paohaijiao.ast.root;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Base class for all template tag nodes.
 * <p>
 * Holds the tag name and the inline style. Raw attributes are rare, so tags that need
 * them (image src/alt, list listType, checkbox checked) keep their own fields and
 * override {@link #appendAttrsBeforeStyle(StringBuilder)} /
 * {@link #appendAttrsAfterStyle(StringBuilder)} to keep the output order right.
 * <p>
 * {@link #toString()} prints indented template source, not rendered output.
 */
public abstract class JQuickAstNode {

    /**
     * Indent per level: two spaces.
     */
    private static final String INDENT_UNIT = "  ";

    /**
     * Inline tags, printed on the same line as their text.
     */
    private static final String[] INLINE_TAGS = {"span", "br", "b", "strong", "i", "em"};
    /**
     * Child nodes, printed in order.
     */
    protected final List<JQuickAstNode> children = new ArrayList<JQuickAstNode>();
    /**
     * Tag name, e.g. div, p, table.
     */
    private final String tagName;
    /**
     * Inline style, null when empty.
     */
    private final JQuickStyleAstNode styleMap;
    /**
     * Comment printed above this tag.
     */
    private final List<JQuickCommentNode> comments = new ArrayList<JQuickCommentNode>();

    protected JQuickAstNode(String tagName) {
        this(tagName, null);
    }

    protected JQuickAstNode(String tagName, JQuickStyleAstNode styleMap) {
        this.tagName = tagName;
        this.styleMap = styleMap;
    }

    protected JQuickAstNode(String tagName, JQuickStyleAstNode styleMap, JQuickAstNode... children) {
        this(tagName, styleMap);
        addChildren(children);
    }

    /**
     * Appends a child and returns this for chaining.
     */
    public JQuickAstNode addChild(JQuickAstNode child) {
        if (child != null) {
            children.add(child);
        }
        return this;
    }

    /**
     * Appends children in the given order.
     */
    public JQuickAstNode addChildren(JQuickAstNode... nodes) {
        if (nodes != null) {
            for (JQuickAstNode node : nodes) {
                addChild(node);
            }
        }
        return this;
    }

    public List<JQuickAstNode> getChildren() {
        return Collections.unmodifiableList(children);
    }

    /**
     * Attaches comment lines, printed above the tag. The parser skips them; they only
     * mark sections of the template.
     */
    public JQuickAstNode comment(String... lines) {
        comments.add(new JQuickCommentNode(lines));
        return this;
    }

    public List<JQuickCommentNode> getComments() {
        return Collections.unmodifiableList(comments);
    }

    public String getTagName() {
        return tagName;
    }

    public JQuickStyleAstNode getStyleMap() {
        return styleMap;
    }

    /**
     * True for self-closing tags such as br.
     */
    protected boolean isSelfClosing() {
        return false;
    }

    /**
     * Attributes printed before style, only image src today.
     */
    protected void appendAttrsBeforeStyle(StringBuilder sb) {
        // none
    }

    /**
     * Attributes printed after style: listType, image alt, checkbox checked.
     */
    protected void appendAttrsAfterStyle(StringBuilder sb) {
        // none
    }

    /**
     * Indented multi-line output.
     */
    @Override
    public final String toString() {
        StringBuilder sb = new StringBuilder();
        render(sb, 0);
        return sb.toString();
    }

    /**
     * Single-line output, same content.
     */
    public final String toCompactString() {
        StringBuilder sb = new StringBuilder();
        renderCompact(sb);
        return sb.toString();
    }

    /**
     * Child indent level. The pdf wrapper passes its own level through.
     */
    protected int childIndent(int indent) {
        return indent + 1;
    }

    /**
     * Blank line between children, used by body to separate blocks.
     */
    protected boolean blankLineBetweenChildren() {
        return false;
    }

    /**
     * Recursive output. One line per tag, inline children stay on the tag line so no
     * stray text lands in cells.
     */
    protected void render(StringBuilder sb, int indent) {
        for (JQuickCommentNode comment : comments) {
            comment.render(sb, indent);
        }
        if (sb.length() > 0) {
            sb.append('\n');
        }
        appendIndent(sb, indent);
        appendOpenTag(sb);
        if (children.isEmpty()) {
            appendEmptyBody(sb);
            return;
        }
        sb.append('>');
        if (isInlineContent()) {
            for (JQuickAstNode child : children) {
                child.renderInline(sb);
            }
            appendCloseTag(sb);
            return;
        }
        for (int i = 0; i < children.size(); i++) {
            if (i > 0 && blankLineBetweenChildren()) {
                sb.append('\n');
            }
            children.get(i).render(sb, childIndent(indent));
        }
        sb.append('\n');
        appendIndent(sb, indent);
        appendCloseTag(sb);
    }

    /**
     * Same-line output, used when children are all inline.
     */
    protected void renderInline(StringBuilder sb) {
        renderCompact(sb);
    }

    /**
     * Open tag, children and close tag on one line.
     */
    protected void renderCompact(StringBuilder sb) {
        appendOpenTag(sb);
        if (children.isEmpty()) {
            appendEmptyBody(sb);
            return;
        }
        sb.append('>');
        for (JQuickAstNode child : children) {
            child.renderCompact(sb);
        }
        appendCloseTag(sb);
    }

    /**
     * Open tag: leading attrs, style, trailing attrs.
     */
    protected void appendOpenTag(StringBuilder sb) {
        sb.append('<').append(tagName);
        appendAttrsBeforeStyle(sb);
        if (styleMap != null && !styleMap.isEmpty()) {
            // style string is built by JQuickStyleAstNode, e.g. background:#0F4C81; padding:12px
            sb.append(" style=\"").append(styleMap).append('"');
        }
        appendAttrsAfterStyle(sb);
    }

    /**
     * Closing form for a childless tag: {@code <br />} or an empty pair.
     */
    private void appendEmptyBody(StringBuilder sb) {
        if (isSelfClosing()) {
            sb.append(" />");
        } else {
            sb.append('>');
            appendCloseTag(sb);
        }
    }

    private void appendCloseTag(StringBuilder sb) {
        sb.append("</").append(tagName).append('>');
    }

    protected void appendIndent(StringBuilder sb, int indent) {
        for (int i = 0; i < indent; i++) {
            sb.append(INDENT_UNIT);
        }
    }

    /**
     * Whether this tag is inline.
     */
    protected boolean isInline() {
        for (String tag : INLINE_TAGS) {
            if (tag.equals(tagName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Whether all children are text or inline tags.
     */
    protected boolean isInlineContent() {
        for (JQuickAstNode child : children) {
            if (child instanceof JQuickTextNode) {
                continue;
            }
            if (child.isInline()) {
                continue;
            }
            return false;
        }
        return true;
    }
}
