/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 */
package com.github.paohaijiao.extension.tree;

import com.github.paohaijiao.visitor.context.JQuickRenderContext;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;
import org.apache.pdfbox.pdmodel.graphics.color.PDColor;
import org.apache.pdfbox.pdmodel.graphics.color.PDDeviceRGB;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 在当前 PDFBox 页面流中绘制树形节点。
 */
public class TreeRenderer {

    private static final float HORIZONTAL_PADDING = 8f;
    private static final float CHECKBOX_SIZE = 16f;
    private static final float TEXT_GAP = 5f;

    private final TreeElement element;

    public TreeRenderer(TreeElement element) {
        this.element = element;
    }

    public void draw(PDPageContentStream stream, JQuickRenderContext context) throws IOException {
        if (element.getRoot() == null || stream == null || context == null) {
            return;
        }
        List<NodeRow> rows = new ArrayList<NodeRow>();
        collectRows(element.getRoot(), 0, rows);
        for (NodeRow row : rows) {
            if (context.getLayoutEngine() != null) {
                context.getLayoutEngine().ensureSpace(element.getNodeHeight(), false);
                stream = context.getLayoutEngine().getStream();
            }
            drawRow(stream, context, row);
        }
    }

    private void collectRows(TreeNode node, int level, List<NodeRow> rows) {
        rows.add(new NodeRow(node, level));
        if (node.isExpanded()) {
            for (TreeNode child : node.getChildren()) {
                collectRows(child, level + 1, rows);
            }
        }
    }

    private void drawRow(PDPageContentStream stream, JQuickRenderContext context, NodeRow row)
            throws IOException {
        float rowHeight = element.getNodeHeight();
        float x = context.getCursorX() + row.level * element.getIndentSize();
        float top = context.getCursorY();
        float y = top - rowHeight;
        float rightMargin = context.getMargins() == null ? 0f : context.getMargins()[1];
        float rowWidth = Math.max(0f, context.getPageWidth() - x - rightMargin);

        stream.setNonStrokingColor(element.getBackgroundColor());
        stream.addRect(x, y, rowWidth, rowHeight);
        stream.fill();

        if (!row.node.isRoot()) {
            float connectorX = x - element.getIndentSize() / 2f;
            stream.setStrokingColor(rgb(230, 230, 230));
            stream.setLineWidth(0.3f);
            stream.moveTo(connectorX, top);
            stream.lineTo(connectorX, y + rowHeight / 2f);
            stream.lineTo(x, y + rowHeight / 2f);
            stream.stroke();
        }

        float checkboxY = y + (rowHeight - CHECKBOX_SIZE) / 2f;
        drawCheckbox(stream, row.node.isSelected(), x + HORIZONTAL_PADDING, checkboxY);
        float textX = x + HORIZONTAL_PADDING * 2f + CHECKBOX_SIZE + TEXT_GAP;
        if (!row.node.isLeaf()) {
            drawArrow(stream, x + HORIZONTAL_PADDING + CHECKBOX_SIZE + 7f,
                    y + rowHeight / 2f, row.node.isExpanded());
            textX += 12f;
        }
        drawText(stream, context, row.node.getText(), textX, y + (rowHeight - fontSize(context)) / 2f);
        context.setCursorY(y);
    }

    private void drawCheckbox(PDPageContentStream stream, boolean selected, float x, float y)
            throws IOException {
        stream.setStrokingColor(element.getCheckboxBorderColor());
        stream.setLineWidth(0.8f);
        stream.addRect(x, y, CHECKBOX_SIZE, CHECKBOX_SIZE);
        stream.stroke();
        if (!selected) {
            return;
        }
        stream.setNonStrokingColor(element.getCheckboxSelectedColor());
        stream.addRect(x + 0.5f, y + 0.5f, CHECKBOX_SIZE - 1f, CHECKBOX_SIZE - 1f);
        stream.fill();
        stream.setStrokingColor(element.getCheckboxSelectedColor());
        stream.setLineWidth(1.5f);
        float padding = CHECKBOX_SIZE * 0.2f;
        stream.moveTo(x + padding, y + CHECKBOX_SIZE / 2f);
        stream.lineTo(x + CHECKBOX_SIZE / 2f, y + CHECKBOX_SIZE - padding);
        stream.lineTo(x + CHECKBOX_SIZE - padding, y + padding);
        stream.stroke();
    }

    private void drawArrow(PDPageContentStream stream, float x, float y, boolean expanded)
            throws IOException {
        float size = 6f;
        stream.setStrokingColor(rgb(120, 120, 120));
        stream.setLineWidth(0.8f);
        if (expanded) {
            stream.moveTo(x - size / 2f, y + size / 3f);
            stream.lineTo(x, y - size / 3f);
            stream.lineTo(x + size / 2f, y + size / 3f);
        } else {
            stream.moveTo(x - size / 3f, y + size / 2f);
            stream.lineTo(x + size / 3f, y);
            stream.lineTo(x - size / 3f, y - size / 2f);
        }
        stream.stroke();
    }

    private void drawText(PDPageContentStream stream, JQuickRenderContext context,
                          String text, float x, float y) throws IOException {
        PDFont font = context.getFont() == null
                ? new PDType1Font(Standard14Fonts.FontName.HELVETICA) : context.getFont();
        stream.beginText();
        stream.setFont(font, fontSize(context));
        stream.setNonStrokingColor(element.getTextColor());
        stream.newLineAtOffset(x, y);
        stream.showText(text == null ? "" : text);
        stream.endText();
    }

    private float fontSize(JQuickRenderContext context) {
        return context.getFontSize() > 0f ? context.getFontSize() : 11f;
    }

    /**
     * PDFBox 3.x 只提供 0..1 分量的 {@code setStrokingColor(float,float,float)} 重载，
     * 这里把 0..255 的 RGB 分量换算成 {@link PDColor} 后再写入，避免整棵树因参数越界而渲染失败。
     */
    private static PDColor rgb(int red, int green, int blue) {
        return new PDColor(new float[]{red / 255f, green / 255f, blue / 255f}, PDDeviceRGB.INSTANCE);
    }

    private static final class NodeRow {
        private final TreeNode node;
        private final int level;

        private NodeRow(TreeNode node, int level) {
            this.node = node;
            this.level = level;
        }
    }
}
