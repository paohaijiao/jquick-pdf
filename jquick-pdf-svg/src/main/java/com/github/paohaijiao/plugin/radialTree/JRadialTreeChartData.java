/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Copyright (c) [2025-2099] Martin (goudingcheng@gmail.com)
 */
package com.github.paohaijiao.plugin.radialTree;

import com.github.paohaijiao.exception.JAssert;
import com.github.paohaijiao.graph.JGraphData;
import lombok.Data;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * packageName com.github.paohaijiao.plugin.radialTree
 *
 * @author Martin
 * @version 1.0.0
 * @classname JRadialTreeChartData
 * @date 2026/11/8
 * @description Data model of the radial tree chart.
 * <p>
 * The renderer reads it from {@code JOption.setData(...)}, the same way
 * {@code JCircleChartData} is passed to the ring chart. A node tree is built with
 * {@link RadialNode}, and {@link #validateAndCalculate()} fills the leaf count of every
 * node, the total node count, the real depth and the default footer text.
 * <p>
 * Angular width is always shared by leaf count, never by node value, so the outermost
 * ring stays evenly filled and closed.
 */
@Data
public class JRadialTreeChartData implements JGraphData {

    /**
     * Canvas size. Zero means the renderer picks the size by node count.
     */
    private int width = 0;

    private int height = 0;

    /**
     * Start angle in degrees, -90 starts at twelve o'clock.
     */
    private float startAngle = -90f;

    /**
     * Number of rings to draw. Clamped to the real depth of the tree.
     */
    private int maxDepth = 4;

    private String titleText;

    private String subtitleText;

    private String footerText;

    private Color backgroundColor = Color.WHITE;

    private Color branchLabelColor = new Color(0x37474F);

    private Color leafLabelColor = new Color(0x546E7A);

    private Color noteColor = new Color(0x90A4AE);

    /**
     * Palette of the first level branches, children inherit the colour of their branch.
     */
    private List<Color> branchColors = new ArrayList<>(Arrays.asList(
            new Color(0x2F6F8F), new Color(0x3E8E7E), new Color(0x8A6FB0), new Color(0xC97B4A),
            new Color(0x4A6FA5), new Color(0x7F9A3C), new Color(0xA85B78), new Color(0x5C7A8A)));

    private RadialNode root;

    /**
     * Node count of the whole tree, filled by {@link #validateAndCalculate()}.
     */
    private int nodeCount;

    /**
     * Real depth of the tree, filled by {@link #validateAndCalculate()}.
     */
    private int depth;

    /**
     * Validates the tree and fills the derived fields: leaf count per node, node count,
     * real depth and the footer text when none was given.
     */
    public void validateAndCalculate() {
        JAssert.notNull(root, "radial tree root require not null");
        JAssert.isTrue(!root.getChildren().isEmpty(), "radial tree root require at least one child");
        JAssert.isTrue(branchColors != null && !branchColors.isEmpty(), "branch colors require not empty");
        int[] counter = new int[1];
        int measuredDepth = measure(root, 0, counter);
        this.nodeCount = counter[0];
        this.depth = measuredDepth;
        if (maxDepth <= 0 || maxDepth > measuredDepth) {
            maxDepth = measuredDepth;
        }
        if (footerText == null) {
            footerText = buildFooterText();
        }
    }

    /**
     * Returns the colour of a first level branch, cycling through the palette.
     *
     * @param index branch index
     * @return branch colour
     */
    public Color branchColor(int index) {
        return branchColors.get(Math.floorMod(index, branchColors.size()));
    }

    private String buildFooterText() {
        String scope = nodeCount > 120 ? "分支标注完整，叶子标签按空间取舍" : "空间不足的标签自动省略";
        return "共 " + nodeCount + " 个节点 · 展示 " + maxDepth + " 层 · " + scope;
    }

    private static int measure(RadialNode node, int depth, int[] counter) {
        counter[0]++;
        if (node.getChildren().isEmpty()) {
            node.setLeafCount(1);
            return depth;
        }
        int leaves = 0;
        int max = depth;
        for (RadialNode child : node.getChildren()) {
            max = Math.max(max, measure(child, depth + 1, counter));
            leaves += child.getLeafCount();
        }
        node.setLeafCount(leaves);
        return max;
    }

    /**
     * One node of the tree. Build it with the chained {@link #addChild(RadialNode)} method:
     * <pre>
     * RadialNode root = new RadialNode("root")
     *         .addChild(new RadialNode("analytics")
     *                 .addChild(new RadialNode("cluster", 3938)));
     * </pre>
     */
    @Data
    public static class RadialNode {

        private String name;

        private double value;

        private Color color;

        private List<RadialNode> children = new ArrayList<>();

        /**
         * Number of leaves under this node, filled by {@link #validateAndCalculate()}.
         */
        private int leafCount;

        public RadialNode(String name) {
            this.name = name;
        }

        public RadialNode(String name, double value) {
            this.name = name;
            this.value = value;
        }

        public RadialNode(String name, double value, Color color) {
            this.name = name;
            this.value = value;
            this.color = color;
        }

        public RadialNode addChild(RadialNode child) {
            this.children.add(child);
            return this;
        }
    }
}
