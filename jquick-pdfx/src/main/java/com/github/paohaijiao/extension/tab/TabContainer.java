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
package com.github.paohaijiao.extension.tab;

import com.github.paohaijiao.visitor.context.JQuickRenderContext;
import com.github.paohaijiao.visitor.element.JQuickElementRender;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.color.PDColor;
import org.apache.pdfbox.pdmodel.graphics.color.PDDeviceRGB;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * packageName com.github.paohaijiao.extension.tab
 *
 * @author Martin
 * @version 1.0.0
 * @since 2025/7/20
 */
public class TabContainer implements JQuickElementRender {

    private final List<TabPage> tabs;

    private int activeTabIndex;

    private PDColor tabBackgroundColor = rgb(240, 240, 240);

    private PDColor activeTabBackgroundColor = rgb(255, 255, 255);

    private PDColor tabTextColor = rgb(0, 0, 0);

    private PDColor tabBorderColor = rgb(200, 200, 200);

    private float tabHeight = 30f;

    private float tabPadding = 10f;

    private float width = -1f;

    private float contentHeight = 160f;

    private float marginBottom = 10f;

    public TabContainer() {
        this.tabs = new ArrayList<>();
        this.activeTabIndex = 0;
    }

    public void addTab(TabPage tab) {
        if (tab == null) {
            return;
        }
        tabs.add(tab);
        if (tabs.size() == 1) {
            tab.setActive(true);
        }
    }

    public void setActiveTab(int index) {
        if (index >= 0 && index < tabs.size()) {
            tabs.get(activeTabIndex).setActive(false);
            activeTabIndex = index;
            tabs.get(activeTabIndex).setActive(true);
        }
    }

    public TabContainerRenderer getRenderer() {
        return new TabContainerRenderer(this);
    }

    @Override
    public void draw(PDPageContentStream stream,
                     JQuickRenderContext context) throws IOException {
        getRenderer().draw(stream, context);
    }

    public List<TabPage> getTabs() {
        return tabs;
    }

    public int getActiveTabIndex() {
        return activeTabIndex;
    }

    public PDColor getTabBackgroundColor() {
        return tabBackgroundColor;
    }

    public void setTabBackgroundColor(PDColor tabBackgroundColor) {
        this.tabBackgroundColor = tabBackgroundColor;
    }

    public void setTabBackgroundColor(String tabBackgroundColor) {
        this.tabBackgroundColor = color(tabBackgroundColor, this.tabBackgroundColor);
    }

    public PDColor getActiveTabBackgroundColor() {
        return activeTabBackgroundColor;
    }

    public void setActiveTabBackgroundColor(PDColor activeTabBackgroundColor) {
        this.activeTabBackgroundColor = activeTabBackgroundColor;
    }

    public void setActiveTabBackgroundColor(String activeTabBackgroundColor) {
        this.activeTabBackgroundColor = color(activeTabBackgroundColor, this.activeTabBackgroundColor);
    }

    public PDColor getTabTextColor() {
        return tabTextColor;
    }

    public void setTabTextColor(PDColor tabTextColor) {
        this.tabTextColor = tabTextColor;
    }

    public void setTabTextColor(String tabTextColor) {
        this.tabTextColor = color(tabTextColor, this.tabTextColor);
    }

    public PDColor getTabBorderColor() {
        return tabBorderColor;
    }

    public void setTabBorderColor(PDColor tabBorderColor) {
        this.tabBorderColor = tabBorderColor;
    }

    public void setTabBorderColor(String tabBorderColor) {
        this.tabBorderColor = color(tabBorderColor, this.tabBorderColor);
    }

    public float getTabHeight() {
        return tabHeight;
    }

    public void setTabHeight(float tabHeight) {
        this.tabHeight = tabHeight;
    }

    public float getTabPadding() {
        return tabPadding;
    }

    public void setTabPadding(float tabPadding) {
        this.tabPadding = tabPadding;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getContentHeight() {
        return contentHeight;
    }

    public void setContentHeight(float contentHeight) {
        this.contentHeight = contentHeight;
    }

    public float getMarginBottom() {
        return marginBottom;
    }

    public void setMarginBottom(float marginBottom) {
        this.marginBottom = marginBottom;
    }

    static PDColor color(String value, PDColor fallback) {
        if (value == null || value.trim().isEmpty()) {
            return fallback;
        }
        String normalized = value.trim().toLowerCase(Locale.ROOT);
        try {
            if (normalized.startsWith("#")) {
                return hexColor(normalized);
            }
            if ("black".equals(normalized)) return rgb(0, 0, 0);
            if ("white".equals(normalized)) return rgb(255, 255, 255);
            if ("red".equals(normalized)) return rgb(255, 0, 0);
            if ("green".equals(normalized) || "lime".equals(normalized)) return rgb(0, 255, 0);
            if ("blue".equals(normalized)) return rgb(0, 0, 255);
            if ("gray".equals(normalized) || "grey".equals(normalized)) return rgb(128, 128, 128);
            if ("light_gray".equals(normalized) || "lightgray".equals(normalized)) return rgb(192, 192, 192);
            if ("dark_gray".equals(normalized) || "darkgray".equals(normalized)) return rgb(64, 64, 64);
        } catch (RuntimeException ignored) {
            return fallback;
        }
        return fallback;
    }

    static PDColor rgb(int red, int green, int blue) {
        return new PDColor(new float[]{red / 255f, green / 255f, blue / 255f}, PDDeviceRGB.INSTANCE);
    }

    private static PDColor hexColor(String value) {
        String hex = value.startsWith("#") ? value.substring(1) : value;
        if (hex.length() == 3) {
            int red = Integer.parseInt(hex.substring(0, 1), 16) * 17;
            int green = Integer.parseInt(hex.substring(1, 2), 16) * 17;
            int blue = Integer.parseInt(hex.substring(2, 3), 16) * 17;
            return rgb(red, green, blue);
        }
        if (hex.length() == 6) {
            int red = Integer.parseInt(hex.substring(0, 2), 16);
            int green = Integer.parseInt(hex.substring(2, 4), 16);
            int blue = Integer.parseInt(hex.substring(4, 6), 16);
            return rgb(red, green, blue);
        }
        return fallbackBlack();
    }

    private static PDColor fallbackBlack() {
        return rgb(0, 0, 0);
    }
}
