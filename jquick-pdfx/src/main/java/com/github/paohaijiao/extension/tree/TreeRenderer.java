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
package com.github.paohaijiao.extension.tree;

import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.kernel.pdf.canvas.PdfCanvasConstants;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.UnitValue;
import com.itextpdf.layout.properties.VerticalAlignment;
import com.itextpdf.layout.renderer.DrawContext;
import com.itextpdf.layout.renderer.IRenderer;

/**
 * packageName com.github.paohaijiao.extension.tree
 *
 * @author Martin
 * @version 1.0.0
 * @since 2025/7/20
 */
public class TreeRenderer extends com.itextpdf.layout.renderer.DivRenderer
{
    public TreeRenderer(TreeElement modelElement) {
        super(modelElement);
    }

    @Override
    public IRenderer getNextRenderer() {
        return new TreeRenderer((TreeElement) modelElement);
    }

    @Override
    public void draw(DrawContext drawContext) {
        super.draw(drawContext);
        TreeElement treeElement = (TreeElement) modelElement;
        TreeNode root = treeElement.getRoot();
        float indentSize = treeElement.getIndentSize();
        float nodeHeight = treeElement.getNodeHeight();

        Rectangle area = getOccupiedAreaBBox();
        float startX = area.getX() + 15;
        float startY = area.getY() + area.getHeight() - nodeHeight;

        drawTree(drawContext, root, startX, startY, 0, indentSize, nodeHeight);
    }

    private void drawTree(DrawContext drawContext, TreeNode node, float x, float y,
                          int level, float indentSize, float nodeHeight) {
        drawCorrectedCheckboxNode(drawContext, node, x + level * indentSize, y, nodeHeight);

        if (node.isExpanded() && !node.isLeaf()) {
            float childY = y - nodeHeight;
            for (TreeNode child : node.getChildren()) {
                drawTree(drawContext, child, x, childY, level + 1, indentSize, nodeHeight);
                childY -= nodeHeight;
            }
        }
    }

    private void drawCorrectedCheckboxNode(DrawContext drawContext, TreeNode node,
                                           float x, float y, float nodeHeight) {
        TreeElement treeElement = (TreeElement) modelElement;
        PdfCanvas canvas = drawContext.getCanvas();
        canvas.saveState();
        canvas.setFillColor(treeElement.getHoverColor());
        canvas.roundRectangle(x, y - nodeHeight, getOccupiedAreaBBox().getWidth() - x,
                nodeHeight, 2);
        canvas.fill();
        canvas.restoreState();
        if (!node.isRoot()) {
            canvas.saveState();
            canvas.setStrokeColor(new DeviceRgb(230, 230, 230));
            canvas.setLineWidth(0.3f);
            float connectorX = x - treeElement.getIndentSize() / 2;
            canvas.moveTo(connectorX, y);
            canvas.lineTo(connectorX, y - nodeHeight / 2);
            canvas.lineTo(x, y - nodeHeight / 2);
            canvas.stroke();
            canvas.restoreState();
        }
        float checkboxSize = 16;
        float checkboxPadding = (nodeHeight - checkboxSize) / 2;
        drawCorrectedCheckbox(drawContext, node.isSelected(),
                x + checkboxPadding, y - nodeHeight + checkboxPadding,
                checkboxSize, checkboxSize);
        Paragraph p = new Paragraph(node.getText())
                .setFontSize(11)
                .setFontColor(treeElement.getTextColor())
                .setMargin(0)
                .setPadding(0)
                .setMarginLeft(checkboxSize + checkboxPadding * 2 + 5)
                .setWidth(UnitValue.createPercentValue(100))
                .setHeight(nodeHeight)
                .setVerticalAlignment(VerticalAlignment.MIDDLE);

        Canvas nodeCanvas = new Canvas(drawContext.getCanvas(),
                new Rectangle(x, y - nodeHeight,
                        getOccupiedAreaBBox().getWidth() - x, nodeHeight));
        nodeCanvas.add(p);
        nodeCanvas.close();
        if (!node.isLeaf()) {
            float arrowSize = 8;
            float arrowX = x + checkboxPadding + checkboxSize + 12;
            float arrowY = y - nodeHeight / 2;
            canvas.saveState();
            canvas.setStrokeColor(new DeviceRgb(120, 120, 120));
            canvas.setLineWidth(0.8f);
            canvas.setLineCapStyle(PdfCanvasConstants.LineCapStyle.ROUND);
            canvas.setLineJoinStyle(PdfCanvasConstants.LineJoinStyle.ROUND);
            if (node.isExpanded()) {
                canvas.moveTo(arrowX - arrowSize/2, arrowY - arrowSize/3);
                canvas.lineTo(arrowX, arrowY + arrowSize/3);
                canvas.lineTo(arrowX + arrowSize/2, arrowY - arrowSize/3);
            } else {
                canvas.moveTo(arrowX - arrowSize/3, arrowY - arrowSize/2);
                canvas.lineTo(arrowX + arrowSize/3, arrowY);
                canvas.lineTo(arrowX - arrowSize/3, arrowY + arrowSize/2);
            }
            canvas.stroke();
            canvas.restoreState();
        }
    }

    private void drawCorrectedCheckbox(DrawContext drawContext, boolean isSelected,
                                       float x, float y, float width, float height) {
        TreeElement treeElement = (TreeElement) modelElement;
        PdfCanvas canvas = drawContext.getCanvas();
        canvas.saveState();
        canvas.setStrokeColor(treeElement.getCheckboxBorderColor());
        canvas.setLineWidth(0.8f);
        canvas.roundRectangle(x, y, width, height, treeElement.getCheckboxCornerRadius());
        canvas.stroke();
        if (isSelected) {
            DeviceRgb color=new DeviceRgb(
                    treeElement.getCheckboxSelectedColor().getColorValue()[0],
                    treeElement.getCheckboxSelectedColor().getColorValue()[1],
                    treeElement.getCheckboxSelectedColor().getColorValue()[2]
            );
            canvas.setFillColor(color);
            canvas.roundRectangle(x + 0.5f, y + 0.5f, width - 1, height - 1,
                    treeElement.getCheckboxCornerRadius());
            canvas.fill();
            canvas.setStrokeColor(treeElement.getCheckboxSelectedColor());
            canvas.setLineWidth(1.5f);
            canvas.setLineCapStyle(PdfCanvasConstants.LineCapStyle.ROUND);
            float padding = width * 0.15f;
            canvas.moveTo(x + padding, y + height/2);
            canvas.lineTo(x + width/2, y + height - padding);
            canvas.lineTo(x + width - padding, y + padding);
            canvas.stroke();
        }

        canvas.restoreState();
    }
}