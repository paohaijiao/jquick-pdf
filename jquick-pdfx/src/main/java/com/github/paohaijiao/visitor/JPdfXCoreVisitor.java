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

import com.github.paohaijiao.config.JPdfConfig;
import com.github.paohaijiao.event.JFooterHandler;
import com.github.paohaijiao.event.JHeaderHandler;
import com.github.paohaijiao.event.JPdfXWatermarkEventHandler;
import com.github.paohaijiao.event.JTocEventHandler;
import com.github.paohaijiao.handler.JStyleHandler;
import com.github.paohaijiao.model.JStyleAttributes;
import com.github.paohaijiao.model.style.JStyleAlignModel;
import com.github.paohaijiao.model.style.JStyleSpacingModel;
import com.github.paohaijiao.param.JContext;
import com.github.paohaijiao.parser.JQuickPDFBaseVisitor;
import com.github.paohaijiao.sample.CataLog;
import com.github.paohaijiao.sample.CatalogType;
import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.IElement;
import com.itextpdf.layout.font.FontProvider;

import java.io.IOException;
import java.util.*;

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

    protected JPdfConfig config=new JPdfConfig();

    protected JStyleAlignModel align = new JStyleAlignModel();

    protected JStyleSpacingModel spacingModel = new JStyleSpacingModel();
    //protected Map<String, Template> templates = new HashMap<>();
    protected Map<CatalogType, java.util.List<CataLog>> cataLogsMap = new LinkedHashMap<>();

    protected PageSize currentPageSize = PageSize.A4;
    protected float[] currentMargins = new float[]{72, 72, 72, 72}; // default 1 inch margins // top, right, bottom, left
    protected JContext context = new JContext();
    protected PdfFont font;
    protected Document doc;
    protected Properties properties = new Properties();
    protected Set<Integer> pageSet = new HashSet<>();
    protected ConverterProperties proper=new ConverterProperties();


    protected void buildStyle(IElement ele, JStyleAttributes style) {
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
    protected void save(JPdfConfig config)  {

    }
    protected void configure(JPdfConfig config)  {
        try{
            PdfWriter writer = new PdfWriter(config.getOutputFilePath());
            PdfDocument pdf = new PdfDocument(writer);
            pdf = new PdfDocument(writer);
            pdf.setDefaultPageSize(config.getDefaultPageSize());
            pdf.getDefaultPageSize().applyMargins(config.getMargins().get(0),
                    config.getMargins().get(1), config.getMargins().get(2),
                    config.getMargins().get(3), config.getReverse());
            FontProvider fontProvider = getFontProvider();
            doc = new Document(pdf);
            doc.setMargins(config.getDoc().getMargins().get(0), config.getDoc().getMargins().get(1),
                    config.getDoc().getMargins().get(2), config.getDoc().getMargins().get(3));
            doc.setFontProvider(fontProvider);
            doc.setFont(config.getDoc().getFont());
            doc.setFontSize(config.getDoc().getFontSize());
            doc.setCharacterSpacing(config.getDoc().getCharacterSpacing());
            proper = new ConverterProperties();
            proper.setFontProvider(fontProvider);
            if (config.getFontConfig().getDefaultFont() != null) {
                doc.setFont(config.getFontConfig().getDefaultFont());
            }
            if (null!=config.getHeaderConfig()&&config.getHeaderConfig().isEnabled() ) {
                pdf.addEventHandler(PdfDocumentEvent.END_PAGE,
                        new JHeaderHandler(config.getHeaderConfig()));
            }
            if (null!=config.getFooterConfig()&&config.getFooterConfig().isEnabled() ) {
                pdf.addEventHandler(PdfDocumentEvent.END_PAGE,
                        new JFooterHandler( config.getFooterConfig()));
            }
            if (config.getCatalogConfig() != null&&config.getCatalogConfig().isEnabled()) {
                pdf.addEventHandler(PdfDocumentEvent.START_PAGE, new JTocEventHandler(pdf,config.getCatalogConfig()));
            }
            if (config.getWatermarkConfig() != null&&config.getWatermarkConfig().getEnabled()) {
                pdf.addEventHandler(PdfDocumentEvent.START_PAGE, new JPdfXWatermarkEventHandler(config.getWatermarkConfig()));
            }


//            if (config.getWatermarkConfig().isEnabled()) {
//                pdf.addEventHandler(PdfDocumentEvent.START_PAGE,
//                        new JPdfXWatermarkEventHandler(config.getWatermarkConfig()));
//            }
            this.pdf = pdf;

        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public static String trim(String str) {
        if(null==str || "".equals(str)) {
            return str;
        }
        String newStr = str.replaceAll("^['\"]|['\"]$", "");
        newStr = newStr.replaceAll("'", "");
        return newStr;
    }


}
