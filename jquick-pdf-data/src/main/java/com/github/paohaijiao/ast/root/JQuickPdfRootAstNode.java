package com.github.paohaijiao.ast.root;

/**
 * Document root: {@code <pdf><body>...</body></pdf>}. Adds no indentation level.
 */
public class JQuickPdfRootAstNode extends JQuickAstNode {

    public JQuickPdfRootAstNode() {
        super("pdf");
    }

    /**
     * Convenience factory.
     */
    public static JQuickPdfRootAstNode of(JQuickAstNode... children) {
        JQuickPdfRootAstNode root = new JQuickPdfRootAstNode();
        root.addChildren(children);
        return root;
    }

    /**
     * Covariant override, keeps the root type when chaining.
     */
    @Override
    public JQuickPdfRootAstNode addChild(JQuickAstNode child) {
        return (JQuickPdfRootAstNode) super.addChild(child);
    }

    /**
     * body lines up with pdf, its children take one level.
     */
    @Override
    protected int childIndent(int indent) {
        return indent;
    }

    /**
     * Covariant override, keeps the root type when chaining.
     */
    @Override
    public JQuickPdfRootAstNode comment(String... lines) {
        super.comment(lines);
        return this;
    }
}
