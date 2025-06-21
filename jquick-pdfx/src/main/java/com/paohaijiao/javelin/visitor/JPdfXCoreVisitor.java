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
package com.paohaijiao.javelin.visitor;

import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.Document;
import com.paohaijiao.javelin.model.style.JStyleAlignModel;
import com.paohaijiao.javelin.model.style.JStyleDimensionModel;
import com.paohaijiao.javelin.model.style.JStyleModel;
import com.paohaijiao.javelin.model.style.JStyleSpacingModel;
import com.paohaijiao.javelin.parser.JQuickPDFBaseVisitor;
import com.sun.org.apache.xalan.internal.xsltc.compiler.Template;

import java.util.HashMap;
import java.util.Map;

/**
 * packageName com.paohaijiao.javelin.visitor
 *
 * @author Martin
 * @version 1.0.0
 * @className JPdfXCoreVisitor
 * @date 2025/6/14
 * @description
 */
public class JPdfXCoreVisitor extends JQuickPDFBaseVisitor {
    protected PdfDocument pdfDoc;
    protected Document document;
    protected JStyleModel style=new JStyleModel();
    protected JStyleAlignModel align=new JStyleAlignModel();
    protected JStyleSpacingModel spacingModel=new JStyleSpacingModel();
    protected JStyleDimensionModel dimension=new JStyleDimensionModel();
    protected Map<String, Template> templates = new HashMap<>();
    protected PageSize currentPageSize = PageSize.A4;
    protected float[] currentMargins = new float[]{72, 72, 72, 72}; // default 1 inch margins // top, right, bottom, left
    protected float convertToPoints(float value, String unit) {
        switch (unit) {
            case "px":
                return value * 72f / 96f;
            case "pt":
                return value;
            case "mm":
                return value * 72f / 25.4f;
            case "cm":
                return value * 72f / 2.54f;
            case "in":
                return value * 72f;
            case "%":
                return PageSize.A4.getWidth() * value / 100f;
            default:
                return value;
        }
    }
    protected Color parseColor(String colorStr) {
        if (colorStr.startsWith("#")) {
            return new DeviceRgb(
                    Integer.valueOf(colorStr.substring(1, 3), 16),
                    Integer.valueOf(colorStr.substring(3, 5), 16),
                    Integer.valueOf(colorStr.substring(5, 7), 16)
            );
        } else if (colorStr.startsWith("rgb(")) {
            String[] parts = colorStr.substring(4, colorStr.length() - 1).split(",");
            return new DeviceRgb(
                    Integer.parseInt(parts[0].trim()),
                    Integer.parseInt(parts[1].trim()),
                    Integer.parseInt(parts[2].trim())
            );
        } else {
            // Named colors
            return new DeviceRgb(0, 0, 0); // default black
        }
    }
    protected void cleanTemp(){
        this.style=new JStyleModel();
        this.align=new JStyleAlignModel();
        this.spacingModel=new JStyleSpacingModel();
        this.dimension=new JStyleDimensionModel();
    }
}
