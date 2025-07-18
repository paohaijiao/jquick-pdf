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

import com.github.paohaijiao.model.JStyleAttributes;
import com.github.paohaijiao.param.JContext;
import com.github.paohaijiao.parser.JQuickPDFParser;
import com.github.paohaijiao.sample.*;
import com.github.paohaijiao.sample.event.CatalogMoveEvent;
import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.font.FontProvider;
import com.itextpdf.layout.properties.AreaBreakType;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.VerticalAlignment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * packageName com.paohaijiao.javelin.visitor
 *
 * @author Martin
 * @version 1.0.0
 * @className JPdfXCommonVisitor
 * @date 2025/6/14
 * @description
 */
public class JPdfXCommonVisitor extends JPdfXElementVisitor {

    private String fileName = "d://test//DivBasedHeadings.pdf";

    public JPdfXCommonVisitor() throws FileNotFoundException {
        this.context = new JContext();
        initPdf(fileName);
    }

    public JPdfXCommonVisitor(JContext context) throws FileNotFoundException {
        this.context = context;
        initPdf(fileName);
    }

    public JPdfXCommonVisitor(String outputPath) throws IOException {
        initPdf(fileName);
    }

    @Override
    public Void visitDocument(JQuickPDFParser.DocumentContext ctx) {
        if (null != ctx.doc()) {
            visitDoc(ctx.doc());
        }
        return null;
    }

    @Override
    public Void visitDoc(JQuickPDFParser.DocContext ctx) {
        if (null != ctx.html()) {
            visitHtml(ctx.html());
        }
        return null;
    }

    @Override
    public Void visitHtml(JQuickPDFParser.HtmlContext ctx) {
        if (null != ctx.head()) {
            visitHead(ctx.head());
        }
        if (null != ctx.body()) {
            visitBody(ctx.body());
        }
//        this.addCatalog();
//        this.addPageNumber();
        pdf.close();
        return null;
    }

    @Override
    public Void visitHead(JQuickPDFParser.HeadContext ctx) {
        if (null != ctx.headStyle()) {
            visitHeadStyle(ctx.headStyle());
        }
        return null;
    }

    @Override
    public Void visitHeadStyle(JQuickPDFParser.HeadStyleContext ctx) {
        if (null != ctx.headStyleOption()) {
            visitHeadStyleOption(ctx.headStyleOption());
        }
        if (null != ctx.bodyStyleOption()) {
            visitBodyStyleOption(ctx.bodyStyleOption());
        }
        return null;
    }

    @Override
    public Void visitHeadStyleOption(JQuickPDFParser.HeadStyleOptionContext ctx) {
        if (ctx.style() != null && !ctx.style().isEmpty()) {
            for (JQuickPDFParser.StyleContext styleContext : ctx.style()) {
                JStyleAttributes style = visitStyle(styleContext);
            }
        }
        return null;
    }

    @Override
    public Void visitBodyStyleOption(JQuickPDFParser.BodyStyleOptionContext ctx) {
        if (ctx.style() != null && !ctx.style().isEmpty()) {
            for (JQuickPDFParser.StyleContext styleContext : ctx.style()) {
                JStyleAttributes style = visitStyle(styleContext);
            }
        }
        return null;
    }

    @Override
    public Void visitBody(JQuickPDFParser.BodyContext ctx) {
        String text=ctx.getText();
        if (ctx.element() != null && !ctx.element().isEmpty()) {
            for (JQuickPDFParser.ElementContext elementContext : ctx.element()) {
                String elementTxt=elementContext.getText();
                Object object=visitElement(elementContext);
                if (object instanceof Image) {
                    Image image=(Image)object;
                    doc.add(image);
                }
                if (object instanceof IBlockElement) {
                    IBlockElement blockElement=(IBlockElement)object;
                    doc.add(blockElement);
                }
                if (object instanceof AreaBreak) {
                    AreaBreak areaBreak=(AreaBreak)object;
                    doc.add(areaBreak);
                }
            }
        }
//        ReportPainting painting = new ReportPainting(pdf, font);
//        painting.drawHello("image/纸质报告-03.png");
//        painting.close();
        return null;
    }
    public void addCatalog() {
        CatalogMoveEvent catalogMoveEvent = new CatalogMoveEvent(properties);
        pdf.addEventHandler(PdfDocumentEvent.END_PAGE, catalogMoveEvent);
        doc.add(new AreaBreak(AreaBreakType.NEXT_PAGE));
        int startNum = pdf.getNumberOfPages();
        Div div1 = getCataLogDiv(0);
        doc.add(div1);
        pdf.removeEventHandler(PdfDocumentEvent.END_PAGE, catalogMoveEvent);
        int pageSize = catalogMoveEvent.getPageSize();
        doc.add(new AreaBreak(AreaBreakType.NEXT_PAGE));
        Div cataLogDiv = getCataLogDiv(pageSize);
        doc.add(cataLogDiv);

        for (int i = startNum; i < startNum + pageSize; i++) {
            pdf.removePage(startNum);
        }
        return ;
    }
    private Div getCataLogDiv(int offPage) {
        Div div1 = new Div();
        Table tableCatalog = new Table(4).useAllAvailableWidth();
        tableCatalog.addCell(ReportComponent.getCatelogCell().add(new Paragraph("检测结果概况").addStyle(ReportStyle.getSecondTitleStyle())));
        tableCatalog.addCell(ReportComponent.getCatelogCell(2).add(ReportComponent.getCatelogDottedLine(1)));
        tableCatalog.addCell(ReportComponent.getCatelogCell().add(new Paragraph("8")));
        tableCatalog.startNewRow();
        Paragraph p1 = new Paragraph();
        p1.add(new Text("目录").addStyle(ReportStyle.getTitleStyle()).setFontSize(32));
        java.util.List<CataLog> cataLogs = cataLogsMap.getOrDefault(CatalogType.ATTENTION, new LinkedList<>());
        tableCatalog.addCell(ReportComponent.getCatelogCell().add(new Paragraph("需要注意").addStyle(ReportStyle.getSecondTitleStyle())));
        tableCatalog.startNewRow();
        this.addCatalogDetail(offPage, tableCatalog, cataLogs);

        cataLogs = cataLogsMap.getOrDefault(CatalogType.NORMAL, new LinkedList<>());
        tableCatalog.addCell(ReportComponent.getCatelogCell().add(new Paragraph("正常项目").addStyle(ReportStyle.getSecondTitleStyle())));
        tableCatalog.startNewRow();
        Map<String, List<CataLog>> cataLogMap = cataLogs.stream().collect(Collectors.groupingBy(CataLog::getCategoryName, LinkedHashMap::new, Collectors.toList()));
        Set<Map.Entry<String, List<CataLog>>> entries1 = cataLogMap.entrySet();
        for (Map.Entry<String, java.util.List<CataLog>> cataLogEntry : entries1) {
            String categoryName = cataLogEntry.getKey();
            tableCatalog.addCell(ReportComponent.getCatelogCell().add(new Paragraph(categoryName).addStyle(ReportStyle.getSecondTitleStyle().setFontSize(13))));
            tableCatalog.startNewRow();
            java.util.List<CataLog> values = cataLogEntry.getValue();
            this.addCatalogDetail(offPage, tableCatalog, values);
        }
        tableCatalog.addCell(ReportComponent.getCatelogCell().add(new Paragraph("结束语").addStyle(ReportStyle.getSecondTitleStyle())));
        div1.add(p1);
        div1.add(tableCatalog);
        return div1;
    }
    private void addCatalogDetail(int offPage, Table tableCatalog, java.util.List<CataLog> values) {
        for (CataLog cataLog : values) {
            tableCatalog.addCell(ReportComponent.getCatelogCell().add(new Paragraph(cataLog.getName())));
            tableCatalog.addCell(ReportComponent.getCatelogCell().add(ReportComponent.getCatelogDottedLine(2)));
            System.out.println(selectColor(cataLog));
            tableCatalog.addCell(ReportComponent.getCatelogCell().add(new com.itextpdf.layout.element.List().add(new ListItem(cataLog.getLabel())
                    .setListSymbol(new Image(ImageDataFactory.create(JPdfXCommonVisitor.class.getClassLoader().getResource("image/dark-green-point.png")))
                            .addStyle(ReportStyle.getDefaultPoint())))));
            tableCatalog.addCell(ReportComponent.getCatelogCell().add(new Paragraph((cataLog.getPageNumber() + offPage) + "")));
            tableCatalog.startNewRow();
        }
    }
    private String selectColor(CataLog cataLog) {
        switch (cataLog.getIndex()) {
            case 0:
                return "green";
            case 1:
                return "dark-green";
            case 2:
                return "blue";
            case 3:
                return "orange";
            case 4:
                return "red";
            default:
                break;
        }
        return "blue";
    }
    protected String getInPath() {
        String outPath= "d://test//";
        int index = outPath.lastIndexOf("/");
        int index2 = outPath.lastIndexOf("/", index-1);
        String prefix = outPath.substring(0, index2);
        String fileName = outPath.substring(index);
        String name = fileName.split("\\.")[0];
        String pre = prefix + "/temp";
        if (!Files.exists(Paths.get(pre))) {
            try {
                Files.createDirectories(Paths.get(pre));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return pre + name + "_temp.pdf";
    }
    public void addPageNumber() {
        Integer catalogSize = Integer.parseInt(properties.getProperty(ReportConstant.CATALOG_SIZE));
        pdf.close();
        PdfReader reader = null;
        PdfWriter writer = null;
        String inPath = getInPath();
        try {
            reader = new PdfReader(new File(inPath));
            writer = new PdfWriter(new File("d://test//hello.pdf"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        PdfDocument pdf = new PdfDocument(reader, writer);
        Document doc = new Document(pdf);
        int startPage = 7;
        int numberOfPages = pdf.getNumberOfPages();
        for (int i = 0; i < catalogSize; i++) {
            pdf.movePage(numberOfPages, startPage);
        }
        String forbidPage = properties.getProperty(ReportConstant.FORBIDDE);
        for (int pageNumber = 1; pageNumber < numberOfPages + 1; pageNumber++) {

            if (pageNumber > 6 + catalogSize && pageNumber != 8 + catalogSize) {
                if (forbidPage != null && (pageNumber - catalogSize) >= Integer.parseInt(forbidPage)) {
                    continue;
                }
                if (pageSet.contains(pageNumber - catalogSize)) {
                    continue;
                }
                PageSize pageSize = pdf.getDefaultPageSize();
                doc.showTextAligned(new Paragraph(String.format("- %d -", pageNumber)), pageSize.getWidth() / 2, 30, pageNumber, TextAlignment.CENTER, VerticalAlignment.MIDDLE, 0);
            }
        }
        pdf.close();
        // 删除临时文件
        try {
            Files.delete(Paths.get(inPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ;
    }
}
