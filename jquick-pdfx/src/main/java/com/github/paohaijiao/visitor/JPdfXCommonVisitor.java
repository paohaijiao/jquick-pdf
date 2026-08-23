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
import com.github.paohaijiao.model.JHtmlRenderModel;
import com.github.paohaijiao.param.JContext;
import com.github.paohaijiao.parser.JQuickPDFParser;
import com.github.paohaijiao.sample.CataLog;
import com.github.paohaijiao.sample.CatalogType;
import com.github.paohaijiao.sample.ReportComponent;
import com.github.paohaijiao.sample.ReportStyle;
import com.github.paohaijiao.sample.event.CatalogMoveEvent;
import com.github.paohaijiao.visitor.context.JQuickRenderContext;
import com.github.paohaijiao.visitor.render.JTemplatePdfBoxRenderer;
import com.github.paohaijiao.visitor.element.JQuickAreaBreakElementRender;
import com.github.paohaijiao.visitor.element.JQuickButtonElementRender;
import com.github.paohaijiao.visitor.element.JQuickCheckBoxElementRender;
import com.github.paohaijiao.visitor.element.JQuickComboBoxElementRender;
import com.github.paohaijiao.visitor.element.JQuickElementRender;
import com.github.paohaijiao.visitor.element.JQuickImageElementRender;
import com.github.paohaijiao.visitor.element.JQuickLineSeparatorElementRender;
import com.github.paohaijiao.visitor.element.JQuickListElementRender;
import com.github.paohaijiao.visitor.element.JQuickTabElementRender;
import com.github.paohaijiao.visitor.element.JQuickTextAreaElementRender;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.properties.AreaBreakType;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
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

    protected boolean pdfBoxEnabled = false;

    public JPdfXCommonVisitor() throws FileNotFoundException {
        this.context = new JContext();
        this.config = new JPdfConfig();
    }

    public JPdfXCommonVisitor(JContext context) throws FileNotFoundException {
        this.context = context;
        this.config = new JPdfConfig();
    }

    public JPdfXCommonVisitor(JPdfConfig config) throws FileNotFoundException {
        this.context = new JContext();
        this.config = config;
    }

    public JPdfXCommonVisitor(JContext context, JPdfConfig config) throws FileNotFoundException {
        this.context = context;
        this.config = config;
    }

    public JPdfXCommonVisitor enablePdfBox() {
        this.pdfBoxEnabled = true;
        return this;
    }

    public boolean isPdfBoxEnabled() {
        return pdfBoxEnabled;
    }

    @Override
    public OutputStream visitDocument(JQuickPDFParser.DocumentContext ctx) {
        baos = new ByteArrayOutputStream();
        if (null != ctx.doc()) {
            visitDoc(ctx.doc());
        }
        return getOutputStream();
    }

    @Override
    public Void visitDoc(JQuickPDFParser.DocContext ctx) {
        if (null != ctx.html()) {
            return visitHtml(ctx.html());
        }
        return null;
    }

    @Override
    public Void visitHtml(JQuickPDFParser.HtmlContext ctx) {
        configure(config);
        if (pdfBoxEnabled) {
            return renderWithPdfBox(ctx);
        }
        if (null != ctx.body()) {
            visitBody(ctx.body());
        }
        if (pdf != null) {
            pdf.close();
        }
        return null;
    }

    protected Void renderWithPdfBox(JQuickPDFParser.HtmlContext ctx) {
        try {
            if (getPdfBoxDocument() == null) {
                return null;
            }
            PDPage page = createPdfBoxPage();
            getPdfBoxDocument().addPage(page);
            try (PDPageContentStream contentStream = new PDPageContentStream(getPdfBoxDocument(), page)) {
                JQuickRenderContext renderContext = createRenderContext(page.getMediaBox());
                if (ctx != null && ctx.body() != null) {
                    renderPdfBoxBody(ctx.body(), contentStream, renderContext);
                }
            }
            getPdfBoxDocument().save(baos);
            getPdfBoxDocument().close();
        } catch (Exception e) {
            throw new RuntimeException("render pdf with pdfbox failed", e);
        }
        return null;
    }

    protected PDPage createPdfBoxPage() {
        PDRectangle rectangle = PDRectangle.A4;
        if (config != null && config.getDefaultPageSize() != null) {
            float width = config.getDefaultPageSize().getWidth();
            float height = config.getDefaultPageSize().getHeight();
            rectangle = new PDRectangle(width, height);
        }
        return new PDPage(rectangle);
    }

    protected JQuickRenderContext createRenderContext(PDRectangle mediaBox) {
        float[] margins = new float[]{36f, 36f, 36f, 36f};
        return JQuickRenderContext.builder()
                .pageWidth(mediaBox.getWidth())
                .pageHeight(mediaBox.getHeight())
                .document(getPdfBoxDocument())
                .width(mediaBox.getWidth() - margins[1] - margins[3])
                .height(mediaBox.getHeight() - margins[0] - margins[2])
                .margins(margins)
                .cursorX(margins[3])
                .cursorY(mediaBox.getHeight() - margins[0])
                .font(new PDType1Font(Standard14Fonts.FontName.HELVETICA))
                .fontSize(12f)
                .lineHeight(16f)
                .build();
    }

    protected void renderPdfBoxBody(JQuickPDFParser.BodyContext ctx, PDPageContentStream contentStream, JQuickRenderContext renderContext) throws IOException {
        if (ctx == null || ctx.element() == null || ctx.element().isEmpty()) {
            return;
        }
        for (JQuickPDFParser.ElementContext elementContext : ctx.element()) {
            Object object = visitElement(elementContext);
            if (object instanceof JQuickElementRender) {
                ((JQuickElementRender) object).draw(contentStream, renderContext);
                if (renderContext.isNewPage()) {
                    renderContext.setNewPage(false);
                }
            }
        }
    }

    @Override
    public Void visitBody(JQuickPDFParser.BodyContext ctx) {
        if (ctx.element() != null && !ctx.element().isEmpty()) {
            for (JQuickPDFParser.ElementContext elementContext : ctx.element()) {
                Object object = visitElement(elementContext);
                if (object instanceof Image) {
                    Image image = (Image) object;
                    doc.add(image);
                }
                if (object instanceof IBlockElement) {
                    IBlockElement blockElement = (IBlockElement) object;
                    doc.add(blockElement);
                }
                if (object instanceof JQuickAreaBreakElementRender) {
                    continue;
                }
                if (object instanceof JQuickTextAreaElementRender) {
                    continue;
                }
                if (object instanceof JQuickListElementRender) {
                    continue;
                }
                if (object instanceof JQuickSvgElementRender) {
                    continue;
                }
                if (object instanceof JQuickTableElementRender) {
                    continue;
                }
                if (object instanceof JQuickTemplateRenderModel) {
                    continue;
                }
                if (object instanceof JQuickElementRender) {
                    continue;
                }
                if (object instanceof JHtmlRenderModel) {
                    JHtmlRenderModel renderModel = (JHtmlRenderModel) object;
                    if (renderModel.getList() != null) {
                        renderModel.getList().forEach(this::saveSub);
                    }
                }
            }
        }
        return null;
    }

    private void saveSub(Object object) {
        if (doc == null || object == null) {
            return;
        }
        if (object instanceof Image) {
            Image image = (Image) object;
            doc.add(image);
        }
        if (object instanceof IBlockElement) {
            IBlockElement blockElement = (IBlockElement) object;
            doc.add(blockElement);
        }
        if (object instanceof JQuickAreaBreakElementRender) {
            return;
        }
        if (object instanceof JQuickButtonElementRender) {
            return;
        }
        if (object instanceof JQuickCheckBoxElementRender) {
            return;
        }
        if (object instanceof JQuickComboBoxElementRender) {
            return;
        }
        if (object instanceof JQuickTextAreaElementRender) {
            return;
        }
        if (object instanceof JQuickImageElementRender) {
            return;
        }
        if (object instanceof JQuickLineSeparatorElementRender) {
            return;
        }
        if (object instanceof JQuickTabElementRender) {
            return;
        }
        if (object instanceof JQuickListElementRender) {
            return;
        }
        if (object instanceof JQuickSvgElementRender) {
            return;
        }
        if (object instanceof JQuickTableElementRender) {
            return;
        }
        if (object instanceof JQuickTemplateRenderModel) {
            return;
        }
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
            tableCatalog.addCell(ReportComponent.getCatelogCell().add(new com.itextpdf.layout.element.List().add(new ListItem(cataLog.getLabel())
                    .setListSymbol(new Image(ImageDataFactory.create(JPdfXCommonVisitor.class.getClassLoader().getResource("image/dark-green-point.png")))
                            .addStyle(ReportStyle.getDefaultPoint())))));
            tableCatalog.addCell(ReportComponent.getCatelogCell().add(new Paragraph((cataLog.getPageNumber() + offPage) + "")));
            tableCatalog.startNewRow();
        }
    }
}
