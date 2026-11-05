package com.github.paohaijiao.ast.root;

/**
 * Heading. Level 1-6 maps to h1-h6. Only h1 is collected into the TOC.
 */
public class JQuickHeadingNode extends JQuickAstNode {

    public JQuickHeadingNode(int level) {
        super(tagOf(level));
    }

    public JQuickHeadingNode(int level, JQuickStyleAstNode styleMap) {
        super(tagOf(level), styleMap);
    }

    public JQuickHeadingNode(int level, JQuickStyleAstNode styleMap, JQuickAstNode... children) {
        super(tagOf(level), styleMap, children);
    }

    private static String tagOf(int level) {
        if (level < 1 || level > 6) {
            throw new IllegalArgumentException("heading level must be 1~6, but got: " + level);
        }
        return "h" + level;
    }
}
