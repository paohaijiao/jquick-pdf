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
package com.github.paohaijiao.treemap;


import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 树形图配置选项类
 */
public class TreeMapOption {

    private int marginLeft = 50;

    private int marginRight = 50;

    private int marginTop = 70;

    private int marginBottom = 80;

    private Color backgroundColor = new Color(250, 250, 250);

    private Color borderColor = new Color(220, 220, 220);

    private float borderWidth = 0.5f;

    private String fontFamily = "Microsoft YaHei";

    private int fontSize = 11;

    private int maxLabelLength = 8;

    private int minLabelWidth = 50;

    private int minLabelHeight = 25;

    private int minValueLabelWidth = 80;

    private int minValueLabelHeight = 35;

    private boolean showLegend = true;

    private int legendBoxSize = 15;

    private int legendFontSize = 12;

    private int legendItemWidth = 120;

    private String legendNote = "注：同色系矩形为同一部门子分类";

    private Color legendNoteColor = new Color(80, 80, 80);

    private int legendNoteSpacing = 15;

    private int colorDeltaRange = 40;

    private Map<String, Color> departmentColors = new HashMap<>();

    private Map<String, Color> categoryColors = new HashMap<>();

    private List<TreeMapMapping> departmentRules = new ArrayList<>();

    private JTreeMapNode root;

    public int getMarginLeft() {
        return marginLeft;
    }

    public void setMarginLeft(int marginLeft) {
        this.marginLeft = marginLeft;
    }

    public int getMarginRight() {
        return marginRight;
    }

    public void setMarginRight(int marginRight) {
        this.marginRight = marginRight;
    }

    public int getMarginTop() {
        return marginTop;
    }

    public void setMarginTop(int marginTop) {
        this.marginTop = marginTop;
    }

    public int getMarginBottom() {
        return marginBottom;
    }

    public void setMarginBottom(int marginBottom) {
        this.marginBottom = marginBottom;
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public Color getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
    }

    public float getBorderWidth() {
        return borderWidth;
    }

    public void setBorderWidth(float borderWidth) {
        this.borderWidth = borderWidth;
    }

    public String getFontFamily() {
        return fontFamily;
    }

    public void setFontFamily(String fontFamily) {
        this.fontFamily = fontFamily;
    }

    public int getFontSize() {
        return fontSize;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

    public int getMaxLabelLength() {
        return maxLabelLength;
    }

    public void setMaxLabelLength(int maxLabelLength) {
        this.maxLabelLength = maxLabelLength;
    }

    public int getMinLabelWidth() {
        return minLabelWidth;
    }

    public void setMinLabelWidth(int minLabelWidth) {
        this.minLabelWidth = minLabelWidth;
    }

    public int getMinLabelHeight() {
        return minLabelHeight;
    }

    public void setMinLabelHeight(int minLabelHeight) {
        this.minLabelHeight = minLabelHeight;
    }

    public int getMinValueLabelWidth() {
        return minValueLabelWidth;
    }

    public void setMinValueLabelWidth(int minValueLabelWidth) {
        this.minValueLabelWidth = minValueLabelWidth;
    }

    public int getMinValueLabelHeight() {
        return minValueLabelHeight;
    }

    public void setMinValueLabelHeight(int minValueLabelHeight) {
        this.minValueLabelHeight = minValueLabelHeight;
    }

    public boolean isShowLegend() {
        return showLegend;
    }

    public void setShowLegend(boolean showLegend) {
        this.showLegend = showLegend;
    }

    public int getLegendBoxSize() {
        return legendBoxSize;
    }

    public void setLegendBoxSize(int legendBoxSize) {
        this.legendBoxSize = legendBoxSize;
    }

    public int getLegendFontSize() {
        return legendFontSize;
    }

    public void setLegendFontSize(int legendFontSize) {
        this.legendFontSize = legendFontSize;
    }

    public int getLegendItemWidth() {
        return legendItemWidth;
    }

    public void setLegendItemWidth(int legendItemWidth) {
        this.legendItemWidth = legendItemWidth;
    }

    public String getLegendNote() {
        return legendNote;
    }

    public void setLegendNote(String legendNote) {
        this.legendNote = legendNote;
    }

    public Color getLegendNoteColor() {
        return legendNoteColor;
    }

    public void setLegendNoteColor(Color legendNoteColor) {
        this.legendNoteColor = legendNoteColor;
    }

    public int getLegendNoteSpacing() {
        return legendNoteSpacing;
    }

    public void setLegendNoteSpacing(int legendNoteSpacing) {
        this.legendNoteSpacing = legendNoteSpacing;
    }

    public int getColorDeltaRange() {
        return colorDeltaRange;
    }

    public void setColorDeltaRange(int colorDeltaRange) {
        this.colorDeltaRange = colorDeltaRange;
    }

    public Map<String, Color> getDepartmentColors() {
        return departmentColors;
    }

    public void setDepartmentColors(Map<String, Color> departmentColors) {
        this.departmentColors = departmentColors;
    }

    public Map<String, Color> getCategoryColors() {
        return categoryColors;
    }

    public void setCategoryColors(Map<String, Color> categoryColors) {
        this.categoryColors = categoryColors;
    }

    public List<TreeMapMapping> getDepartmentRules() {
        return departmentRules;
    }

    public void setDepartmentRules(List<TreeMapMapping> departmentRules) {
        this.departmentRules = departmentRules;
    }

    public JTreeMapNode getRoot() {
        return root;
    }

    public void setRoot(JTreeMapNode root) {
        this.root = root;
    }
}
