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
package com.github.paohaijiao.visitor;

import com.github.paohaijiao.handler.JStyleHandler;
import com.github.paohaijiao.model.JStyleAttributes;
import com.github.paohaijiao.model.style.JStyleAlignModel;
import com.github.paohaijiao.model.style.JStyleSpacingModel;
import com.github.paohaijiao.param.JContext;
import com.github.paohaijiao.parser.JQuickPDFBaseVisitor;
import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Div;
import com.itextpdf.layout.element.IElement;
import com.itextpdf.layout.font.FontProvider;

import java.io.IOException;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

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
    protected PdfDocument pdf;
    protected JStyleAlignModel align = new JStyleAlignModel();
    protected JStyleSpacingModel spacingModel = new JStyleSpacingModel();
    //protected Map<String, Template> templates = new HashMap<>();
    protected PageSize currentPageSize = PageSize.A4;
    protected float[] currentMargins = new float[]{72, 72, 72, 72}; // default 1 inch margins // top, right, bottom, left
    protected JContext context = new JContext();
    protected PdfFont font;
    protected Document doc;
    protected Properties properties = new Properties();
    protected Set<Integer> pageSet = new HashSet<>();
    protected ConverterProperties proper=new ConverterProperties();


    protected void buildStyle(IElement ele, JStyleAttributes style) {
        Div div = new Div();
        JStyleHandler.applyStyles(doc,ele, style);
    }

    protected FontProvider getFontProvider(){
        FontProvider fontProvider = new FontProvider();
        try {
            String fontPath = "fonts/simhei.ttf";
            fontProvider.addFont(fontPath, PdfEncodings.IDENTITY_H);
            if (font == null) {
                font = PdfFontFactory.createFont(fontPath, PdfEncodings.IDENTITY_H);
            }
        } catch (IOException e) {
            e.printStackTrace();
            try {
                fontProvider.addFont("Helvetica");
                if (font == null) {
                    font = PdfFontFactory.createFont("Helvetica");
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return fontProvider;
    }

    protected void initPdf(String fileName)  {
        try{
            PdfWriter writer = new PdfWriter(fileName);
            PdfDocument pdf = new PdfDocument(writer);
            pdf = new PdfDocument(writer);
            pdf.setDefaultPageSize(PageSize.A4);
            pdf.getDefaultPageSize().applyMargins(0, 0, 0, 0, true);
            FontProvider fontProvider = getFontProvider();
            doc = new Document(pdf);
            doc.setMargins(50, 60, 50, 60);
            doc.setFontProvider(fontProvider);
            doc.setFont(font);
            doc.setFontSize(10.5f);
            doc.setCharacterSpacing(0.1f);
            proper = new ConverterProperties();
            proper.setFontProvider(fontProvider);
            this.pdf = pdf;
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
