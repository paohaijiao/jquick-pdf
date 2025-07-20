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

import com.itextpdf.forms.PdfAcroForm;
import com.itextpdf.forms.fields.PdfButtonFormField;
import com.itextpdf.forms.fields.PdfFormField;
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfName;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.action.PdfAction;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.kernel.pdf.layer.PdfLayer;
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
        PdfDocument pdfDoc = new PdfDocument(new PdfWriter("output.pdf"));
        Document document = new Document(pdfDoc);

        // 创建 3 个图层（对应 3 个选项卡）
        PdfLayer tab1Layer = new PdfLayer("Tab1", pdfDoc);
        PdfLayer tab2Layer = new PdfLayer("Tab2", pdfDoc);
        PdfLayer tab3Layer = new PdfLayer("Tab3", pdfDoc);

        // 默认显示第一个选项卡
        tab1Layer.setOn(true);
        tab2Layer.setOn(false);
        tab3Layer.setOn(false);

        // 获取表单
        PdfAcroForm form = PdfAcroForm.getAcroForm(pdfDoc, true);

        // 添加选项卡按钮
        addTabButton(form, pdfDoc, "基本信息", 50, 700, tab1Layer, tab2Layer, tab3Layer);
        addTabButton(form, pdfDoc, "教育背景", 150, 700, tab2Layer, tab1Layer, tab3Layer);
        addTabButton(form, pdfDoc, "工作经历", 250, 700, tab3Layer, tab1Layer, tab2Layer);

        // 添加选项卡内容
        addTabContent(pdfDoc, tab1Layer, "这是基本信息内容");
        addTabContent(pdfDoc, tab2Layer, "这是教育背景内容");
        addTabContent(pdfDoc, tab3Layer, "这是工作经历内容");

        document.close();
    }

    private static void addTabButton(PdfAcroForm form, PdfDocument pdfDoc, String title, float x, float y,
                                     PdfLayer showLayer, PdfLayer... hideLayers) {
        // 创建按钮
        PdfButtonFormField button = PdfFormField.createPushButton(
                pdfDoc,
                new Rectangle(x, y, 100, 30), // 按钮位置和大小
                title.replace(" ", "_") + "_Btn", // 按钮名称（不能有空格）
                title // 按钮显示文本
        );

        // 设置按钮样式（可选）
        button.setBackgroundColor(new DeviceRgb(200, 200, 200));
        button.setBorderColor(new DeviceRgb(0, 0, 0));
        button.setFontSize(12);

        // 获取图层名称（修正点）
        String showLayerName = showLayer.getPdfObject().get(PdfName.Name).toString();
        StringBuilder js = new StringBuilder();
        for (PdfLayer hideLayer : hideLayers) {
            String hideLayerName = hideLayer.getPdfObject().get(PdfName.Name).toString();
            js.append("this.getOCG('").append(hideLayerName).append("').state = false;");
        }
        js.append("this.getOCG('").append(showLayerName).append("').state = true;");
        button.setAction(PdfAction.createJavaScript(js.toString()));

        // 将按钮添加到表单
        form.addField(button);
    }

    private static void addTabContent(PdfDocument pdfDoc, PdfLayer layer, String content) throws IOException {
        PdfCanvas canvas = new PdfCanvas(pdfDoc.getFirstPage());
        canvas.beginLayer(layer);
        canvas.beginText()
                .setFontAndSize(PdfFontFactory.createFont(), 12)
                .moveText(50, 650)
                .showText(content)
                .endText();
        canvas.endLayer();
    }
}
