package com.github.paohaijiao.ast.root;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Comment node, printed as {@code // text} and skipped by the parser.
 * Can sit in body as a block marker or on any tag.
 */
public class JQuickCommentNode extends JQuickAstNode {

    /**
     * Comment lines, one per entry.
     */
    private final List<String> lines = new ArrayList<String>();

    public JQuickCommentNode(String... lines) {
        super("#comment");
        if (lines != null) {
            Collections.addAll(this.lines, lines);
        }
    }

    public List<String> getLines() {
        return Collections.unmodifiableList(lines);
    }

    /**
     * A comment is not a tag, so it never self-closes.
     */
    @Override
    protected boolean isSelfClosing() {
        return false;
    }

    /**
     * One line per comment, indented like its siblings.
     */
    @Override
    protected void render(StringBuilder sb, int indent) {
        for (String line : lines) {
            if (sb.length() > 0) {
                sb.append('\n');
            }
            appendIndent(sb, indent);
            sb.append("//");
            if (line != null && !line.isEmpty()) {
                sb.append(' ').append(line);
            }
        }
    }

    /**
     * Comments are always printed as whole lines.
     */
    @Override
    protected void renderInline(StringBuilder sb) {
        render(sb, 0);
    }

    /**
     * Single-line output for logs and diffs.
     */
    @Override
    protected void renderCompact(StringBuilder sb) {
        for (int i = 0; i < lines.size(); i++) {
            if (i > 0) {
                sb.append(' ');
            }
            sb.append("//");
            if (lines.get(i) != null && !lines.get(i).isEmpty()) {
                sb.append(' ').append(lines.get(i));
            }
        }
    }
}
