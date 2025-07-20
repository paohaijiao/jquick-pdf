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

import com.github.paohaijiao.factory.JFontProviderFactory;
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.element.IBlockElement;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.font.FontProvider;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.VerticalAlignment;
import com.itextpdf.layout.renderer.DrawContext;
import com.itextpdf.layout.renderer.IRenderer;

import java.io.IOException;
import java.util.List;

/**
 * packageName com.github.paohaijiao.extension.tab
 *
 * @author Martin
 * @version 1.0.0
 * @since 2025/7/20
 */
public class TabContainerRenderer extends com.itextpdf.layout.renderer.DivRenderer
{
    public TabContainerRenderer(TabContainer modelElement) {
        super(modelElement);
    }

    @Override
    public IRenderer getNextRenderer() {
        return new TabContainerRenderer((TabContainer) modelElement);
    }

    @Override
    public void draw(DrawContext drawContext) {
        TabContainer tabContainer = (TabContainer) modelElement;
        List<TabPage> tabs = tabContainer.getTabs();
        super.draw(drawContext);

        Rectangle area = getOccupiedAreaBBox();
        float startX = area.getX();
        float startY = area.getY() + area.getHeight();

        PdfCanvas canvas = drawContext.getCanvas();
        canvas.saveState();
        drawTabBar(drawContext, tabs, startX, startY, tabContainer);
        canvas.setStrokeColor(tabContainer.getTabBorderColor());
        canvas.setLineWidth(1);
        canvas.rectangle(startX, startY - tabContainer.getTabHeight() - 1,
                area.getWidth(), area.getHeight() -  tabContainer.getTabHeight());
        canvas.stroke();
        canvas.restoreState();
        if (!tabs.isEmpty()) {
            TabPage activeTab = tabs.get(tabContainer.getActiveTabIndex());
            float contentY = startY - tabContainer.getTabHeight() - 10;
            for (IBlockElement content : activeTab.getContents()) {
                Canvas contentCanvas = new Canvas(drawContext.getCanvas(),
                        new Rectangle(startX + 10, contentY - 20,
                                area.getWidth() - 20, 20));
                contentCanvas.add(content);
                contentCanvas.close();
                contentY -= 30;
            }
        }
    }

    private void drawTabBar(DrawContext drawContext, List<TabPage> tabs,
                            float startX, float startY, TabContainer tabContainer) {
        PdfCanvas canvas = drawContext.getCanvas();
        PdfFont font=JFontProviderFactory.getFont(JFontProviderFactory.DEFAULT_FONT);
        canvas.setFontAndSize(font,32);
        float tabWidth = (getOccupiedAreaBBox().getWidth() - 2) / tabs.size();
        float tabX = startX + 1;
        for (int i = 0; i < tabs.size(); i++) {
            TabPage tab = tabs.get(i);
            canvas.saveState();
            if (tab.isActive()) {
                canvas.setFillColor(tabContainer.getActiveTabBackgroundColor());
            } else {
                canvas.setFillColor(tabContainer.getTabBackgroundColor());
            }
            canvas.moveTo(tabX, startY);
            canvas.lineTo(tabX, startY - tabContainer.getTabHeight() + 5);
            canvas.curveTo(tabX, startY - tabContainer.getTabHeight(),
                    tabX + 5, startY - tabContainer.getTabHeight(),
                    tabX + 5, startY - tabContainer.getTabHeight());
            canvas.lineTo(tabX + tabWidth - 5, startY - tabContainer.getTabHeight());
            canvas.curveTo(tabX + tabWidth, startY - tabContainer.getTabHeight(),
                    tabX + tabWidth, startY - tabContainer.getTabHeight() + 5,
                    tabX + tabWidth, startY - tabContainer.getTabHeight() + 5);
            canvas.lineTo(tabX + tabWidth, startY);
            canvas.closePath();
            canvas.fill();
            canvas.setStrokeColor(tabContainer.getTabBorderColor());
            canvas.setLineWidth(1);
            if (i == 0 || !tab.isActive()) {
                canvas.moveTo(tabX, startY);
                canvas.lineTo(tabX, startY - tabContainer.getTabHeight() + 5);
                canvas.curveTo(tabX, startY - tabContainer.getTabHeight(),
                        tabX + 5, startY - tabContainer.getTabHeight(),
                        tabX + 5, startY - tabContainer.getTabHeight());
            }

            canvas.moveTo(tabX + 5, startY - tabContainer.getTabHeight());
            canvas.lineTo(tabX + tabWidth - 5, startY - tabContainer.getTabHeight());

            if (i == tabs.size() - 1 || !tab.isActive()) {
                canvas.curveTo(tabX + tabWidth, startY - tabContainer.getTabHeight(),
                        tabX + tabWidth, startY - tabContainer.getTabHeight() + 5,
                        tabX + tabWidth, startY - tabContainer.getTabHeight() + 5);
                canvas.lineTo(tabX + tabWidth, startY);
            }

            if (!tab.isActive()) {
                canvas.moveTo(tabX, startY);
                canvas.lineTo(tabX + tabWidth, startY);
            }

            canvas.stroke();
            canvas.restoreState();
            Paragraph tabText = new Paragraph(tab.getTitle())
                    .setFontSize(12)
                    .setFont(font)
                    .setFontColor(tabContainer.getTabTextColor())
                    .setTextAlignment(TextAlignment.CENTER)
                    .setWidth(tabWidth)
                    .setHeight(tabContainer.getTabHeight())
                    .setVerticalAlignment(VerticalAlignment.MIDDLE);

            Canvas tabCanvas = new Canvas(canvas,
                    new Rectangle(tabX, startY - tabContainer.getTabHeight(),
                            tabWidth, tabContainer.getTabHeight()));
            tabCanvas.add(tabText);
            tabCanvas.close();

            tabX += tabWidth;
        }
    }
}
