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

import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Div;
import com.itextpdf.layout.element.IBlockElement;
import com.itextpdf.layout.element.IElement;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.font.FontProvider;
import com.itextpdf.layout.properties.Property;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import com.itextpdf.layout.properties.VerticalAlignment;
import com.itextpdf.layout.renderer.DrawContext;
import com.itextpdf.layout.renderer.IRenderer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * packageName com.github.paohaijiao.extension.tab
 *
 * @author Martin
 * @version 1.0.0
 * @since 2025/7/20
 */
public class PdfTabComponent {

    public static void main(String[] args) throws Exception {
        FontProvider fontProvider = new FontProvider();
        String fontPath = "fonts/simhei.ttf";
        fontProvider.addFont(fontPath, PdfEncodings.IDENTITY_H);
        PdfFont font = PdfFontFactory.createFont(fontPath, PdfEncodings.IDENTITY_H);
        PdfDocument pdfDoc = new PdfDocument(new com.itextpdf.kernel.pdf.PdfWriter("d://test//tab_component.pdf"));
        Document document = new Document(pdfDoc);
        TabContainer tabContainer = new TabContainer();
        TabPage tab1 = new TabPage("基本信息");
        tab1.addContent(new Paragraph("姓名: 张三").setFont(font));
        tab1.addContent(new Paragraph("年龄: 30").setFont(font));
        tab1.addContent(new Paragraph("职业: 软件工程师").setFont(font));
        tabContainer.addTab(tab1);
        TabPage tab2 = new TabPage("教育背景");
        tab2.addContent(new Paragraph("WWWW: XXX 计算机科学与技术"));
        tab2.addContent(new Paragraph("XXX: XXXX 人工智能"));
        tabContainer.addTab(tab2);
        TabPage tab3 = new TabPage("工作经历");
        tab3.addContent(new Paragraph("2015-2018: Google 高级软件工程师"));
        tab3.addContent(new Paragraph("2018-2022: Amazon 技术专家"));
        tab3.addContent(new Paragraph("2022-至今: 自由职业者"));
        tabContainer.addTab(tab3);
        tabContainer.setActiveTab(2);
        document.add(new Paragraph("个人简历").setFont(font).setBold().setFontSize(16).setTextAlignment(TextAlignment.CENTER));
        document.add(tabContainer);

        document.close();
    }
}
