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
import com.github.paohaijiao.font.JFontSpec;
import com.github.paohaijiao.param.JContext;
import com.github.paohaijiao.parser.JQuickPDFParser;
import com.github.paohaijiao.visitor.context.JQuickRenderContext;
import com.github.paohaijiao.visitor.render.JTemplatePdfBoxRenderer;
import com.github.paohaijiao.visitor.render.PdfBoxLayoutEngine;
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
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

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
        return renderWithPdfBox(ctx);
    }

    protected Void renderWithPdfBox(JQuickPDFParser.HtmlContext ctx) {
        try {
            if (getPdfBoxDocument() == null) {
                return null;
            }
            PDPage page = currentPage == null ? createPdfBoxPage() : currentPage;
            if (currentPage == null) {
                getPdfBoxDocument().addPage(page);
            }
            JQuickRenderContext renderContext = createRenderContext(page.getMediaBox());
            PdfBoxLayoutEngine layoutEngine = new PdfBoxLayoutEngine(getPdfBoxDocument(), page.getMediaBox(),
                    renderContext, page, contentStream == null ? new PDPageContentStream(getPdfBoxDocument(), page) : contentStream);
            renderContext.setLayoutEngine(layoutEngine);
            try {
                if (ctx != null && ctx.body() != null) {
                    renderPdfBoxBody(ctx.body(), layoutEngine.getStream(), renderContext);
                }
            } finally {
                layoutEngine.close();
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
                .font(resolveDocumentFont())
                .fontSize(12f)
                .lineHeight(16f)
                .flexLayout(config != null && config.getLayoutConfig() != null
                        && config.getLayoutConfig().isFlexLayout())
                .build();
    }

    /**
     * 解析文档默认字体：优先使用配置中的字体（默认从 classpath 的 fonts/simhei.ttf 加载，
     * 支持中文等 CJK 字符），加载失败时回退到内置 Helvetica。
     */
    protected PDFont resolveDocumentFont() {
        try {
            JFontSpec spec = config == null || config.getFontConfig() == null
                    ? null : config.getFontConfig().getDefaultFont();
            if (spec != null) {
                return spec.resolve(getPdfBoxDocument());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new PDType1Font(Standard14Fonts.FontName.HELVETICA);
    }

    protected void renderPdfBoxBody(JQuickPDFParser.BodyContext ctx, PDPageContentStream contentStream, JQuickRenderContext renderContext) throws IOException {
        if (ctx == null || ctx.element() == null || ctx.element().isEmpty()) {
            return;
        }
        for (JQuickPDFParser.ElementContext elementContext : ctx.element()) {
            Object object = visitElement(elementContext);
            if (object instanceof JQuickElementRender) {
                PDPageContentStream activeStream = renderContext.getLayoutEngine() == null
                        ? contentStream : renderContext.getLayoutEngine().getStream();
                ((JQuickElementRender) object).draw(activeStream, renderContext);
                if (renderContext.isNewPage()) {
                    renderContext.setNewPage(false);
                }
            }
        }
    }

    @Override
    public Void visitBody(JQuickPDFParser.BodyContext ctx) {
        return null;
    }
}
