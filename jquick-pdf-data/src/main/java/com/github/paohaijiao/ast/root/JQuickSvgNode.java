package com.github.paohaijiao.ast.root;

import java.util.List;

/**
 * SVG placeholder: {@code <svg style="width:76pt">${chart}</svg>}.
 * The drawing itself is bound as a variable and rendered by Batik.
 */
public class JQuickSvgNode extends JQuickAstNode {

    public JQuickSvgNode() {
        super("svg");
    }

    public JQuickSvgNode(JQuickStyleAstNode styleMap) {
        super("svg", styleMap);
    }

    public JQuickSvgNode(JQuickStyleAstNode styleMap, JQuickAstNode... children) {
        super("svg", styleMap, children);
    }

    public JQuickSvgNode(JQuickStyleAstNode styleMap, List<JQuickAstNode> children) {
        super("svg", styleMap);
        if (children != null) {
            for (JQuickAstNode child : children) {
                addChild(child);
            }
        }
    }
}
