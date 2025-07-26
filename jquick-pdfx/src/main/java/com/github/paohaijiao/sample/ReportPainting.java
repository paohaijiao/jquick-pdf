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
package com.github.paohaijiao.sample;

import com.github.paohaijiao.sample.data.Categories;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.element.Image;
import lombok.Data;

import java.io.IOException;
import java.net.URL;


@Data
public class ReportPainting {
    private PdfCanvas pdfCanvas;
    private PageSize pageSize;
    private PdfDocument pdf;
    private PdfFont font;

    public ReportPainting(PdfDocument pdf, PdfFont font) {
        this.pdf = pdf;
        this.font = font;
        PdfPage page = pdf.getPage(pdf.getNumberOfPages());
        pageSize = pdf.getDefaultPageSize();
        pdfCanvas = new PdfCanvas(page);
    }

    public void drawHeader() {
        pdfCanvas.setStrokeColor(ReportColor.getThemeColor());
        pdfCanvas.setLineWidth(0.7f);
        // 画头线（在开头画会使整个页面画不出来）
        float y = pageSize.getTop() - 40;
        pdfCanvas.moveTo(60, y)
                .lineTo(pageSize.getWidth() / 2 - 60, y).stroke();
        pdfCanvas.moveTo(pageSize.getWidth() / 2 + 60, y)
                .lineTo(pageSize.getWidth() - 60, y).stroke();
    }

    public void drawSegment(float score) {
        Color color = null;
        pdfCanvas.saveState();
        pdfCanvas.setStrokeColor(ColorConstants.BLACK);
        pdfCanvas.setLineWidth(1);
        String[] segmenets = ReportConstant.SEGMENETS;
        if (score >= Float.parseFloat(segmenets[0]) && score < Float.parseFloat(segmenets[2])) {
            color = ReportColor.getLightBlueColor();
        } else if (score >= Float.parseFloat(segmenets[2]) && score < Float.parseFloat(segmenets[3])) {
            color = ReportColor.getThemeColor();
        } else if (score >= Float.parseFloat(segmenets[3]) && score < Float.parseFloat(segmenets[4])) {
            color = ReportColor.getOrangeColor();
        } else if (score >= Float.parseFloat(segmenets[4]) && score <= Float.parseFloat(segmenets[5])) {
            color = ReportColor.getRedColor();
        }
        // 位置标示偏移量
        float posXOffset = -100;
        float yOffset = pageSize.getTop() + 20;
        // 画线段
        for (int i = 0; i < 6; i++) {
            pdfCanvas.saveState().moveTo(pageSize.getWidth() / 2 - 100 + i * 40, yOffset - 203)
                    .lineTo(pageSize.getWidth() / 2 - 100 + i * 40, yOffset - 208)
                    .stroke().restoreState();
            try {
                int left = 103;
                if (i > 0) {
                    left = 107;
                }
                pdfCanvas.beginText()
                        .setFontAndSize(PdfFontFactory.createFont(StandardFonts.SYMBOL), 12)
                        .moveText(pageSize.getWidth() / 2 - left + i * 40, yOffset - 218);
                pdfCanvas.newlineShowText(segmenets[i]);
                pdfCanvas.endText();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (i < segmenets.length - 1) {
                float v1 = Float.parseFloat(segmenets[i]);
                float v2 = Float.parseFloat(segmenets[i + 1]);
                if (score >= v1 && score < v2) {
                    posXOffset = posXOffset + 40 / (v2 - v1) * (score - v1) + 40 * i;
                }
            }
        }
        // 画三角形
        pdfCanvas.setFillColor(color);
        pdfCanvas.moveTo(pageSize.getWidth() / 2 + posXOffset, yOffset - 192)
                .lineTo(pageSize.getWidth() / 2 - 3.6 + posXOffset, yOffset - 185)
                .lineTo(pageSize.getWidth() / 2 + 3.6 + posXOffset, yOffset - 185)
                .fill();
        // 画圆
        pdfCanvas.setLineWidth(2.4f);
        pdfCanvas.setStrokeColor(color);
        pdfCanvas.roundRectangle(pageSize.getWidth() / 2 - 3.5 + posXOffset, yOffset - 186, 7, 7, 3.5)
                .stroke();
        // 画进度条
        pdfCanvas
                .roundRectangle(pageSize.getWidth() / 2 - 100, yOffset - 200, 200, 5, 3)
                .stroke();
        pdfCanvas
                .roundRectangle(pageSize.getWidth() / 2 - 100, yOffset - 200, posXOffset + 100, 5, 3)
                .fill()
                .restoreState();
    }

    public void close() {
        pdfCanvas.release();
    }

    public void drawHeaderText(String text) {
        float yOffset = pageSize.getTop();
        pdfCanvas.beginText()
                .setFontAndSize(font, 12)
                .moveText(pageSize.getWidth() / 2 - text.length() * 12 / 2, yOffset - 45);
        pdfCanvas.showText(text);
        pdfCanvas.endText();
    }

    public void drawHello(String imagePath, float offset) {
        URL resource = ReportPainting.class.getClassLoader().getResource(imagePath);
        Image backImage = new Image(ImageDataFactory.create(resource));
        PdfPage page = pdf.getPage(pdf.getNumberOfPages());
        Rectangle pageSize = page.getPageSize();
        int leftDist = 0;
        Rectangle rectangle = new Rectangle(
                pageSize.getX() + leftDist,
                pageSize.getTop() + offset,
                pageSize.getWidth() - leftDist * 2,
                40);

        Canvas canvas = new Canvas(page, rectangle);
        canvas.add(backImage).flush();
        canvas.close();
    }

    public void drawHello(String imagePath) {
        drawHello(imagePath, 0);
    }

    public void drawWss(int level, Categories.ItemsBean itemsBean) {
        float yOffset = pageSize.getTop() + 20;
        // 画线条
        pdfCanvas.saveState();
        pdfCanvas.setStrokeColor(ReportColor.getThemeColor());
        pdfCanvas.setFillColor(ReportColor.getThemeColor());
        pdfCanvas.roundRectangle(pageSize.getWidth() / 2 - 108, yOffset - 200, 5, 6, 3);
        pdfCanvas.rectangle(pageSize.getWidth() / 2 - 105, yOffset - 200, 70, 6).fill().stroke();
        pdfCanvas.setStrokeColor(ReportColor.getOrangeColor());
        pdfCanvas.setFillColor(ReportColor.getOrangeColor());
        pdfCanvas.rectangle(pageSize.getWidth() / 2 - 105 + 70, yOffset - 200, 70, 6).fill().stroke();
        pdfCanvas.setStrokeColor(ReportColor.getRedColor());
        pdfCanvas.setFillColor(ReportColor.getRedColor());
        pdfCanvas.roundRectangle(pageSize.getWidth() / 2 - 105 + 208, yOffset - 200, 5, 6, 3);
        pdfCanvas.rectangle(pageSize.getWidth() / 2 - 105 + 140, yOffset - 200, 70, 6).fill().stroke();

        Color color = null;
        float posXOffset = 0;
        switch (level) {
            case 2:
                color = ReportColor.getThemeColor();
                posXOffset = -70;
                break;
            case 3:
                color = ReportColor.getOrangeColor();
                posXOffset = 0;
                break;
            case 4:
                color = ReportColor.getRedColor();
                posXOffset = 70;
                break;
            default:
                break;
        }
        // 画三角形
        pdfCanvas.setFillColor(color);
        pdfCanvas.moveTo(pageSize.getWidth() / 2 + posXOffset, yOffset - 193)
                .lineTo(pageSize.getWidth() / 2 - 6 + posXOffset, yOffset - 184)
                .lineTo(pageSize.getWidth() / 2 + 6 + posXOffset, yOffset - 184)
                .fill();
        // 画圆
        pdfCanvas.setLineWidth(4);
        pdfCanvas.setStrokeColor(color);
        pdfCanvas.roundRectangle(pageSize.getWidth() / 2 - 4.5 + posXOffset, yOffset - 186, 9, 9, 4.5)
                .stroke();

        float textHeightOff = -215;
        pdfCanvas.beginText().setFontAndSize(font, 11);
        if (level == 2) {
            pdfCanvas.setFillColor(ReportColor.getThemeColor());
        } else {
            pdfCanvas.setFillColor(new DeviceRgb(159, 159, 159));
        }
        pdfCanvas.moveText(pageSize.getWidth() / 2 - 70 - 12, yOffset + textHeightOff);
        pdfCanvas.showText("正常").stroke();
        pdfCanvas.endText();

        pdfCanvas.beginText().setFontAndSize(font, 11);
        if (level == 3) {
            pdfCanvas.setFillColor(ReportColor.getOrangeColor());
        } else {
            pdfCanvas.setFillColor(new DeviceRgb(159, 159, 159));
        }
        pdfCanvas.moveText(pageSize.getWidth() / 2 + 0 - 12, yOffset + textHeightOff);
        pdfCanvas.showText("稍高");
        pdfCanvas.endText();

        pdfCanvas.beginText().setFontAndSize(font, 11);
        if (level == 4) {
            pdfCanvas.setFillColor(ReportColor.getRedColor());
        } else {
            pdfCanvas.setFillColor(new DeviceRgb(159, 159, 159));
        }
        pdfCanvas.moveText(pageSize.getWidth() / 2 + 70 - 6, yOffset + textHeightOff);
        pdfCanvas.showText("高");
        pdfCanvas.endText().stroke();
        switch (level) {
            case 2:
                pdfCanvas.beginText().setFontAndSize(font, 11);
                pdfCanvas.setFillColor(ColorConstants.BLACK);
                pdfCanvas.moveText(pageSize.getWidth() / 2 - 48, yOffset - 5);
                pdfCanvas.showText(itemsBean.getName() + "需求量").stroke();
                pdfCanvas.setFillColor(ReportColor.getThemeColor());
                pdfCanvas.showText("正常");
                pdfCanvas.endText();
                break;
            case 3:
                pdfCanvas.beginText().setFontAndSize(font, 11);
                pdfCanvas.setFillColor(ColorConstants.BLACK);
                pdfCanvas.moveText(pageSize.getWidth() / 2 - 48, yOffset - 235);
                pdfCanvas.showText(itemsBean.getName() + "需求量").stroke();
                pdfCanvas.setFillColor(ReportColor.getOrangeColor());
                pdfCanvas.showText("稍高");
                pdfCanvas.endText();
                break;
            case 4:
                pdfCanvas.beginText().setFontAndSize(font, 11);
                pdfCanvas.moveText(pageSize.getWidth() / 2 - 48, yOffset - 235);
                pdfCanvas.setFillColor(ColorConstants.BLACK);
                pdfCanvas.showText(itemsBean.getName() + "需求量").stroke();
                pdfCanvas.setFillColor(ReportColor.getRedColor());
                pdfCanvas.showText("高");
                pdfCanvas.endText();
                break;
            default:
                break;
        }
        pdfCanvas.restoreState();
    }

    public void drawWss(int level) {
        float yOffset = pageSize.getTop() + 20;
        // 画线条
        pdfCanvas.saveState();
        pdfCanvas.setStrokeColor(ReportColor.getThemeColor());
        pdfCanvas.setFillColor(ReportColor.getThemeColor());
        pdfCanvas.roundRectangle(pageSize.getWidth() / 2 - 108, yOffset - 200, 5, 6, 3);
        pdfCanvas.rectangle(pageSize.getWidth() / 2 - 105, yOffset - 200, 70, 6).fill().stroke();
        pdfCanvas.setStrokeColor(ReportColor.getOrangeColor());
        pdfCanvas.setFillColor(ReportColor.getOrangeColor());
        pdfCanvas.rectangle(pageSize.getWidth() / 2 - 105 + 70, yOffset - 200, 70, 6).fill().stroke();
        pdfCanvas.setStrokeColor(ReportColor.getRedColor());
        pdfCanvas.setFillColor(ReportColor.getRedColor());
        pdfCanvas.roundRectangle(pageSize.getWidth() / 2 - 105 + 208, yOffset - 200, 5, 6, 3);
        pdfCanvas.rectangle(pageSize.getWidth() / 2 - 105 + 140, yOffset - 200, 70, 6).fill().stroke();

        Color color = null;
        float posXOffset = 0;
        switch (level) {
            case 2:
                color = ReportColor.getThemeColor();
                posXOffset = -70;
                break;
            case 3:
                color = ReportColor.getOrangeColor();
                posXOffset = 0;
                break;
            case 4:
                color = ReportColor.getRedColor();
                posXOffset = 70;
                break;
            default:
                break;
        }
        // 画三角形
        pdfCanvas.setFillColor(color);
        pdfCanvas.moveTo(pageSize.getWidth() / 2 + posXOffset, yOffset - 193)
                .lineTo(pageSize.getWidth() / 2 - 6 + posXOffset, yOffset - 184)
                .lineTo(pageSize.getWidth() / 2 + 6 + posXOffset, yOffset - 184)
                .fill();
        // 画圆
        pdfCanvas.setLineWidth(4);
        pdfCanvas.setStrokeColor(color);
        pdfCanvas.roundRectangle(pageSize.getWidth() / 2 - 4.5 + posXOffset, yOffset - 186, 9, 9, 4.5)
                .stroke();

        float textHeightOff = -215;
        pdfCanvas.beginText().setFontAndSize(font, 11);
        if (level == 2) {
            pdfCanvas.setFillColor(ReportColor.getThemeColor());
        } else {
            pdfCanvas.setFillColor(new DeviceRgb(159, 159, 159));
        }
        pdfCanvas.moveText(pageSize.getWidth() / 2 - 70 - 12, yOffset + textHeightOff);
        pdfCanvas.showText("正常").stroke();
        pdfCanvas.endText();

        pdfCanvas.beginText().setFontAndSize(font, 11);
        if (level == 3) {
            pdfCanvas.setFillColor(ReportColor.getOrangeColor());
        } else {
            pdfCanvas.setFillColor(new DeviceRgb(159, 159, 159));
        }
        pdfCanvas.moveText(pageSize.getWidth() / 2 + 0 - 12, yOffset + textHeightOff);
        pdfCanvas.showText("稍高");
        pdfCanvas.endText();

        pdfCanvas.beginText().setFontAndSize(font, 11);
        if (level == 4) {
            pdfCanvas.setFillColor(ReportColor.getRedColor());
        } else {
            pdfCanvas.setFillColor(new DeviceRgb(159, 159, 159));
        }
        pdfCanvas.moveText(pageSize.getWidth() / 2 + 70 - 6, yOffset + textHeightOff);
        pdfCanvas.showText("高");
        pdfCanvas.endText().stroke();
        switch (level) {
            case 2:
                pdfCanvas.beginText().setFontAndSize(font, 11);
                pdfCanvas.setFillColor(ColorConstants.BLACK);
                pdfCanvas.moveText(pageSize.getWidth() / 2 - 48, yOffset - 5);
                pdfCanvas.showText( "需求量").stroke();
                pdfCanvas.setFillColor(ReportColor.getThemeColor());
                pdfCanvas.showText("正常");
                pdfCanvas.endText();
                break;
            case 3:
                pdfCanvas.beginText().setFontAndSize(font, 11);
                pdfCanvas.setFillColor(ColorConstants.BLACK);
                pdfCanvas.moveText(pageSize.getWidth() / 2 - 48, yOffset - 235);
                pdfCanvas.showText(  "需求量").stroke();
                pdfCanvas.setFillColor(ReportColor.getOrangeColor());
                pdfCanvas.showText("稍高");
                pdfCanvas.endText();
                break;
            case 4:
                pdfCanvas.beginText().setFontAndSize(font, 11);
                pdfCanvas.moveText(pageSize.getWidth() / 2 - 48, yOffset - 235);
                pdfCanvas.setFillColor(ColorConstants.BLACK);
                pdfCanvas.showText("需求量").stroke();
                pdfCanvas.setFillColor(ReportColor.getRedColor());
                pdfCanvas.showText("高");
                pdfCanvas.endText();
                break;
            default:
                break;
        }
        pdfCanvas.restoreState();
    }
}