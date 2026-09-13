/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 */
package com.github.paohaijiao.extension.tree;

import com.github.paohaijiao.visitor.context.JQuickRenderContext;
import com.github.paohaijiao.visitor.element.JQuickElementRender;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.color.PDColor;
import org.apache.pdfbox.pdmodel.graphics.color.PDDeviceRGB;

import java.io.IOException;

/**
 * 基于 PDFBox 的树形元素。
 */
public class TreeElement implements JQuickElementRender {

    private final TreeNode root;
    private float indentSize = 20f;
    private float nodeHeight = 28f;
    private PDColor backgroundColor = color(255, 255, 255);
    private PDColor hoverColor = color(245, 245, 245);
    private PDColor textColor = color(51, 51, 51);
    private PDColor checkboxBorderColor = color(204, 204, 204);
    private PDColor checkboxSelectedColor = color(70, 130, 180);
    private float checkboxCornerRadius = 3f;

    public TreeElement(TreeNode root) {
        this.root = root;
    }

    @Override
    public void draw(PDPageContentStream stream, JQuickRenderContext context) throws IOException {
        new TreeRenderer(this).draw(stream, context);
    }

    public TreeNode getRoot() {
        return root;
    }

    public float getIndentSize() {
        return indentSize;
    }

    public float getNodeHeight() {
        return nodeHeight;
    }

    public PDColor getBackgroundColor() {
        return backgroundColor;
    }

    public PDColor getHoverColor() {
        return hoverColor;
    }

    public PDColor getTextColor() {
        return textColor;
    }

    public PDColor getCheckboxBorderColor() {
        return checkboxBorderColor;
    }

    public PDColor getCheckboxSelectedColor() {
        return checkboxSelectedColor;
    }

    public float getCheckboxCornerRadius() {
        return checkboxCornerRadius;
    }

    private static PDColor color(int red, int green, int blue) {
        return new PDColor(new float[]{red / 255f, green / 255f, blue / 255f},
                PDDeviceRGB.INSTANCE);
    }
}
