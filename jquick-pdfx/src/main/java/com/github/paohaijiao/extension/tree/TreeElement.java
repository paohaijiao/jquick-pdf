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

import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.layout.element.Div;
import com.itextpdf.layout.renderer.IRenderer;

/**
 * packageName com.github.paohaijiao.extension.tree
 *
 * @author Martin
 * @version 1.0.0
 * @since 2025/7/20
 */
public class TreeElement extends Div {
    private TreeNode root;
    private float indentSize = 20;
    private float nodeHeight = 28;
    private Color backgroundColor = ColorConstants.WHITE;
    private Color hoverColor = new DeviceRgb(245, 245, 245);
    private Color textColor = new DeviceRgb(51, 51, 51);
    private Color checkboxBorderColor = new DeviceRgb(204, 204, 204);
    private Color checkboxSelectedColor = new DeviceRgb(70, 130, 180);
    private float checkboxCornerRadius = 3;

    public TreeElement(TreeNode root) {
        this.root = root;
    }

    @Override
    public IRenderer getRenderer() {
        return new TreeRenderer(this);
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

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public Color getHoverColor() {
        return hoverColor;
    }

    public Color getTextColor() {
        return textColor;
    }

    public Color getCheckboxBorderColor() {
        return checkboxBorderColor;
    }

    public Color getCheckboxSelectedColor() {
        return checkboxSelectedColor;
    }

    public float getCheckboxCornerRadius() {
        return checkboxCornerRadius;
    }
}