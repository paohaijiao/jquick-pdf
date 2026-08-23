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
import com.github.paohaijiao.console.JConsole;
import com.github.paohaijiao.handler.JStyleHandler;
import com.github.paohaijiao.model.JStyleAttributes;
import com.github.paohaijiao.model.style.JStyleAlignModel;
import com.github.paohaijiao.model.style.JStyleSpacingModel;
import com.github.paohaijiao.param.JContext;
import com.github.paohaijiao.parser.JQuickPDFBaseVisitor;
import com.github.paohaijiao.sample.CataLog;
import com.github.paohaijiao.sample.CatalogType;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;
import org.apache.pdfbox.pdmodel.interactive.documentnavigation.outline.PDDocumentOutline;
import org.apache.pdfbox.pdmodel.interactive.documentnavigation.outline.PDOutlineItem;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

/**
 * PDFBox实现的Visitor
 */
public class JPdfXCoreVisitor extends JQuickPDFBaseVisitor {

    protected PDDocument pdfBoxDocument;

    protected PDPage currentPage;

    protected PDPageContentStream contentStream;

    protected JPdfConfig config = new JPdfConfig();

    protected JConsole console = JConsole.initConsoleEnvironment();

    protected JStyleAlignModel align = new JStyleAlignModel();

    protected JStyleSpacingModel spacingModel = new JStyleSpacingModel();

    protected Stack<PDOutlineItem> outlineStack = new Stack<>();


    protected Map<CatalogType, List<CataLog>> cataLogsMap = new LinkedHashMap<>();

    protected PageSize currentPageSize = PageSize.A4;

    protected float[] currentMargins = new float[]{72, 72, 72, 72};

    protected JContext context = new JContext();

    protected PDFont font;

    protected PDDocumentOutline outline;

    protected ByteArrayOutputStream baos = null;

    protected Properties properties = new Properties();

    protected Set<Integer> pageSet = new HashSet<>();

    protected int pageNumber = 0;

    protected float currentY;

    public static String trim(String str) {
        if (null == str || "".equals(str)) {
            return str;
        }
        String newStr = str.replaceAll("^['\"]|['\"]$", "");
        newStr = newStr.replaceAll("'", "");
        return newStr;
    }

    /**
     * 获取字体
     */
    protected PDFont getFont() {
        try {
            if (font == null) {
                String fontPath = "fonts/simhei.ttf";
                File fontFile = new File(fontPath);
                if (fontFile.exists()) {
                    font = PDType0Font.load(pdfBoxDocument, fontFile);
                } else {
                    font = new PDType1Font(Standard14Fonts.FontName.HELVETICA);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            font = new PDType1Font(Standard14Fonts.FontName.HELVETICA);
        }
        return font;
    }

    /**
     * 创建新页面
     */
    protected PDPage createPage() {
        PDRectangle pageSize = getPageSize(currentPageSize);
        PDPage page = new PDPage(pageSize);
        pdfBoxDocument.addPage(page);
        currentPage = page;
        pageNumber++;
        currentY = pageSize.getHeight() - currentMargins[0];
        return page;
    }


    protected PDRectangle getPageSize(PageSize size) {
        switch (size) {
            case A4:
                return PDRectangle.A4;
            case A3:
                return PDRectangle.A3;
            case A5:
                return PDRectangle.A5;
            case LETTER:
                return PDRectangle.LETTER;
            case LEGAL:
                return PDRectangle.LEGAL;
            default:
                return PDRectangle.A4;
        }
    }

    /**
     * 开始页面内容流
     */
    protected void beginPageContent() throws IOException {
        if (contentStream != null) {
            contentStream.close();
        }
        contentStream = new PDPageContentStream(pdfBoxDocument, currentPage);
    }

    /**
     * endPageContent
     */
    protected void endPageContent() throws IOException {
        if (contentStream != null) {
            contentStream.close();
            contentStream = null;
        }
    }

    /**
     * 绘制文本
     */
    protected void drawText(String text, float x, float y, float fontSize) throws IOException {
        if (contentStream == null) {
            beginPageContent();
        }
        contentStream.beginText();
        contentStream.setFont(getFont(), fontSize);
        contentStream.newLineAtOffset(x, y);
        contentStream.showText(text);
        contentStream.endText();
    }

    protected void addOutlineItem(String title, int pageNum, int level) {
        try {
            if (outline == null) {
                outline = new PDDocumentOutline();
                pdfBoxDocument.getDocumentCatalog().setDocumentOutline(outline);
                outlineStack.clear();
            }

            PDOutlineItem item = new PDOutlineItem();
            item.setTitle(title);
            if (pageNum > 0 && pageNum <= pdfBoxDocument.getNumberOfPages()) {
                PDPage page = pdfBoxDocument.getPage(pageNum - 1);
                item.setDestination(page);
            }

            if (level <= 1) {
                outline.addLast(item);
                outlineStack.clear();
                outlineStack.push(item);
            } else {
                while (outlineStack.size() >= level) {
                    outlineStack.pop();
                }

                if (!outlineStack.isEmpty()) {
                    PDOutlineItem parent = outlineStack.peek();
                    parent.addLast(item);
                } else {
                    outline.addLast(item);
                }
                outlineStack.push(item);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    protected void addOutlineItem(String title, int pageNum) {
        addOutlineItem(title, pageNum, 1);
    }
    /**
     * 开始一个新的目录层级
     * 用于在添加子目录前重置栈状态
     */
    protected void startOutlineLevel(int level) {
        // 清空栈到指定层级
        while (outlineStack.size() > level) {
            outlineStack.pop();
        }
    }
    /**
     * 重置目录栈
     */
    protected void resetOutlineStack() {
        outlineStack.clear();
    }


    /**
     * 构建样式（PDFBox简化实现）
     */
    protected void buildStyle(Object ele, JStyleAttributes style) {
        if (style != null) {

        }
    }

    /**
     * 配置文档
     */
    protected void configure(JPdfConfig config) {
        try {
            this.pdfBoxDocument = new PDDocument();
            this.baos = new ByteArrayOutputStream();
            currentPageSize = config.getDefaultPageSize() != null ? convertPageSize(config.getDefaultPageSize()) : PageSize.A4;
            List<Float> margins = config.getMargins();
            if (margins != null && margins.size() >= 4) {
                currentMargins = new float[]{margins.get(0), margins.get(1), margins.get(2), margins.get(3)
                };
            }
            getFont();
            createPage();
            beginPageContent();
            if (config.getAuthor() != null) {
                pdfBoxDocument.getDocumentInformation().setAuthor(config.getAuthor());
            }
            if (config.getTitle() != null) {
                pdfBoxDocument.getDocumentInformation().setTitle(config.getTitle());
            }

            this.config = config;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 转换页面大小
     */
    protected PageSize convertPageSize(PageSize size) {
        return PageSize.A4;
    }

    /**
     * 保存文档
     */
    protected void save(JPdfConfig config) {
        try {
            if (contentStream != null) {
                contentStream.close();
                contentStream = null;
            }
            if (pdfBoxDocument != null && baos != null) {
                pdfBoxDocument.save(baos);
                pdfBoxDocument.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 判断是否为PDFBox模式
     */
    protected boolean isPdfBoxMode() {
        return pdfBoxDocument != null;
    }

    /**
     * 获取PDFBox文档
     */
    protected PDDocument getPdfBoxDocument() {
        return pdfBoxDocument;
    }

    /**
     * 获取输出流
     */
    public OutputStream getOutputStream() {
        return baos;
    }

    /**
     * 获取当前页宽
     */
    protected float getPageWidth() {
        return currentPage.getMediaBox().getWidth();
    }

    /**
     * 获取当前页高
     */
    protected float getPageHeight() {
        return currentPage.getMediaBox().getHeight();
    }

    /**
     * 获取可用宽度（扣除边距）
     */
    protected float getAvailableWidth() {
        return getPageWidth() - currentMargins[1] - currentMargins[3];
    }

    /**
     * 获取可用高度（扣除边距）
     */
    protected float getAvailableHeight() {
        return getPageHeight() - currentMargins[0] - currentMargins[2];
    }

    /**
     * 换页
     */
    protected void newPage() throws IOException {
        endPageContent();
        createPage();
        beginPageContent();
    }

    // 页面大小枚举（需要根据您的实际定义调整）
    public enum PageSize {
        A4, A3, A5, LETTER, LEGAL
    }
}