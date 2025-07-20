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

import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.layout.element.Div;
import com.itextpdf.layout.renderer.IRenderer;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * packageName com.github.paohaijiao.extension.tab
 *
 * @author Martin
 * @version 1.0.0
 * @since 2025/7/20
 */
@Getter
public class TabContainer  extends Div {
    private List<TabPage> tabs;
    private int activeTabIndex;
    private Color tabBackgroundColor = new DeviceRgb(240, 240, 240);
    private Color activeTabBackgroundColor = ColorConstants.WHITE;
    private Color tabTextColor = ColorConstants.BLACK;
    private Color tabBorderColor = new DeviceRgb(200, 200, 200);
    private float tabHeight = 30;
    private float tabPadding = 10;

    public TabContainer() {
        this.tabs = new ArrayList<>();
        this.activeTabIndex = 0;
    }

    public void addTab(TabPage tab) {
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

    @Override
    public IRenderer getRenderer() {
        return new TabContainerRenderer(this);
    }
}
