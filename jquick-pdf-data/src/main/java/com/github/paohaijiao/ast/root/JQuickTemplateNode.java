package com.github.paohaijiao.ast.root;

/**
 * Template reference: {@code <template>&name</template>}.
 */
public class JQuickTemplateNode extends JQuickAstNode {

    public JQuickTemplateNode() {
        super("template");
    }

    /**
     * Constructor that adds the {@code &} prefix.
     */
    public JQuickTemplateNode(String identifier) {
        super("template");
        if (identifier != null) {
            addChild(JQuickTextNode.raw("&" + identifier));
        }
    }
}
