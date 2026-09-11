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

import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.PDResources;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;
import org.apache.pdfbox.pdmodel.graphics.color.PDColor;
import org.apache.pdfbox.pdmodel.graphics.optionalcontent.PDOptionalContentGroup;
import org.apache.pdfbox.pdmodel.graphics.optionalcontent.PDOptionalContentProperties;
import org.apache.pdfbox.pdmodel.interactive.action.PDActionJavaScript;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotationWidget;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDAppearanceCharacteristicsDictionary;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDBorderStyleDictionary;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDPushButton;
import org.apache.pdfbox.util.Matrix;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;

/**
 * packageName com.github.paohaijiao.extension.tab
 *
 * @author Martin
 * @version 1.0.0
 * @since 2025/7/20
 */
public class PdfTabComponent {

    public static void main(String[] args) throws Exception {
        PDDocument document = new PDDocument();
        try {
            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);

            PDOptionalContentGroup tab1Layer = createLayer(document, "Tab1", true);
            PDOptionalContentGroup tab2Layer = createLayer(document, "Tab2", false);
            PDOptionalContentGroup tab3Layer = createLayer(document, "Tab3", false);

            PDAcroForm form = getOrCreateForm(document);
            addTabButton(form, page, "基本信息", 50, 780, 100, 30, tab1Layer, tab2Layer, tab3Layer);
            addTabButton(form, page, "教育背景", 150, 780, 100, 30, tab2Layer, tab1Layer, tab3Layer);
            addTabButton(form, page, "工作经历", 250, 780, 100, 30, tab3Layer, tab1Layer, tab2Layer);

            PDPageContentStream stream = new PDPageContentStream(document, page);
            try {
                addTabContent(document, stream, tab1Layer, "这是基本信息内容", 50, 730);
                addTabContent(document, stream, tab2Layer, "这是教育背景内容", 50, 730);
                addTabContent(document, stream, tab3Layer, "这是工作经历内容", 50, 730);
            } finally {
                stream.close();
            }

            File output = new File("d://test//output.pdf");
            File parent = output.getParentFile();
            if (parent != null && !parent.exists()) {
                parent.mkdirs();
            }
            document.save(output);
        } finally {
            document.close();
        }
    }

    private static PDOptionalContentGroup createLayer(PDDocument document,
                                                      String name,
                                                      boolean visible) {
        PDOptionalContentProperties properties = document.getDocumentCatalog().getOCProperties();
        if (properties == null) {
            properties = new PDOptionalContentProperties();
            document.getDocumentCatalog().setOCProperties(properties);
        }
        PDOptionalContentGroup layer = new PDOptionalContentGroup(name);
        properties.addGroup(layer);
        properties.setGroupEnabled(layer, visible);
        return layer;
    }

    private static PDAcroForm getOrCreateForm(PDDocument document) {
        PDAcroForm form = document.getDocumentCatalog().getAcroForm();
        if (form == null) {
            form = new PDAcroForm(document);
            document.getDocumentCatalog().setAcroForm(form);
        }
        if (form.getDefaultResources() == null) {
            form.setDefaultResources(new PDResources());
        }
        form.setNeedAppearances(true);
        return form;
    }

    private static void addTabButton(PDAcroForm form,
                                     PDPage page,
                                     String title,
                                     float x,
                                     float y,
                                     float width,
                                     float height,
                                     PDOptionalContentGroup showLayer,
                                     PDOptionalContentGroup... hideLayers) throws IOException {
        PDPushButton button = new PDPushButton(form);
        button.setPartialName(title.replaceAll("\\s+", "_") + "_Btn");

        PDAnnotationWidget widget = new PDAnnotationWidget();
        widget.setRectangle(new PDRectangle(x, y, width, height));
        widget.setPage(page);
        widget.setPrinted(true);
        widget.setParent(button);

        PDAppearanceCharacteristicsDictionary appearance =
                new PDAppearanceCharacteristicsDictionary(new COSDictionary());
        appearance.setBackground(TabContainer.rgb(200, 200, 200));
        appearance.setBorderColour(TabContainer.rgb(0, 0, 0));
        appearance.setNormalCaption(title);
        widget.setAppearanceCharacteristics(appearance);

        PDBorderStyleDictionary border = new PDBorderStyleDictionary();
        border.setWidth(1f);
        border.setStyle(PDBorderStyleDictionary.STYLE_SOLID);
        widget.setBorderStyle(border);
        widget.setAction(new PDActionJavaScript(layerSwitchScript(showLayer, hideLayers)));

        button.setWidgets(Collections.singletonList(widget));
        form.getFields().add(button);
        page.getAnnotations().add(widget);
    }

    private static String layerSwitchScript(PDOptionalContentGroup showLayer,
                                            PDOptionalContentGroup... hideLayers) {
        StringBuilder js = new StringBuilder();
        for (PDOptionalContentGroup hideLayer : hideLayers) {
            js.append("this.getOCG('")
                    .append(escapeJavaScript(hideLayer.getName()))
                    .append("').state = false;");
        }
        js.append("this.getOCG('")
                .append(escapeJavaScript(showLayer.getName()))
                .append("').state = true;");
        return js.toString();
    }

    private static void addTabContent(PDDocument document,
                                      PDPageContentStream stream,
                                      PDOptionalContentGroup layer,
                                      String content,
                                      float x,
                                      float baseline) throws IOException {
        PDFont font = loadFont(document);
        stream.beginMarkedContent(COSName.OC, layer);
        stream.beginText();
        stream.setFont(font, 12f);
        stream.setNonStrokingColor(TabContainer.rgb(0, 0, 0));
        stream.setTextMatrix(Matrix.getTranslateInstance(x, baseline));
        stream.showText(safeText(font, content));
        stream.endText();
        stream.endMarkedContent();
    }

    private static PDFont loadFont(PDDocument document) throws IOException {
        InputStream inputStream = PdfTabComponent.class.getClassLoader().getResourceAsStream("fonts/simhei.ttf");
        if (inputStream != null) {
            try {
                return PDType0Font.load(document, inputStream);
            } finally {
                inputStream.close();
            }
        }
        String[] candidates = new String[]{
                "fonts/simhei.ttf",
                "jquick-pdf-font/src/main/resources/fonts/simhei.ttf",
                "../jquick-pdf-font/src/main/resources/fonts/simhei.ttf"
        };
        for (String candidate : candidates) {
            File fontFile = new File(candidate);
            if (fontFile.exists()) {
                return PDType0Font.load(document, fontFile);
            }
        }
        return new PDType1Font(Standard14Fonts.FontName.HELVETICA);
    }

    private static String safeText(PDFont font, String text) {
        if (!(font instanceof PDType1Font) || text == null) {
            return text;
        }
        StringBuilder builder = new StringBuilder(text.length());
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            builder.append(ch <= 255 ? ch : '?');
        }
        return builder.toString();
    }

    private static String escapeJavaScript(String value) {
        if (value == null) {
            return "";
        }
        return value.replace("\\", "\\\\").replace("'", "\\'");
    }
}
