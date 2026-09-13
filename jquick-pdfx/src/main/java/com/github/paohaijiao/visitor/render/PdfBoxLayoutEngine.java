package com.github.paohaijiao.visitor.render;

import com.github.paohaijiao.visitor.context.JQuickRenderContext;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;

import java.io.IOException;

/**
 * Owns the active PDFBox page and content stream so pagination changes are
 * real page changes instead of cursor-only state changes.
 */
public class PdfBoxLayoutEngine implements AutoCloseable {

    private final PDDocument document;

    private final PDRectangle pageSize;

    private final JQuickRenderContext context;

    private PDPage page;

    private PDPageContentStream stream;

    /** 测量（预排版）期间关闭分页，避免试排版在文档中留下多余页面。 */
    private boolean paginationEnabled = true;

    public boolean isPaginationEnabled() {
        return paginationEnabled;
    }

    public void setPaginationEnabled(boolean paginationEnabled) {
        this.paginationEnabled = paginationEnabled;
    }

    public PdfBoxLayoutEngine(PDDocument document, PDRectangle pageSize, JQuickRenderContext context) throws IOException {
        this.document = document;
        this.pageSize = pageSize == null ? PDRectangle.A4 : pageSize;
        this.context = context;
        newPage();
    }

    public PdfBoxLayoutEngine(PDDocument document, PDRectangle pageSize, JQuickRenderContext context, PDPage page, PDPageContentStream stream) {
        this.document = document;
        this.pageSize = pageSize == null ? PDRectangle.A4 : pageSize;
        this.context = context;
        this.page = page;
        this.stream = stream;
    }

    public PDPageContentStream getStream() {
        return stream;
    }

    public void breakPage() throws IOException {
        if (!paginationEnabled) {
            return;
        }
        newPage();
    }

    public PDPage getPage() {
        return page;
    }

    public void ensureSpace(float requiredHeight, boolean keepTogether) throws IOException {
        if (!paginationEnabled) {
            return;
        }
        float bottom = context.getMargins() == null ? 0f : context.getMargins()[2];
        if (context.getCursorY() - requiredHeight < bottom && (keepTogether || requiredHeight > 0f)) {
            newPage();
        }
    }

    public void ensureSpace(float currentHeight, float nextHeight, boolean keepWithNext) throws IOException {
        ensureSpace(currentHeight + (keepWithNext ? nextHeight : 0f), keepWithNext);
    }

    public void newPage() throws IOException {
        if (stream != null) stream.close();
        page = new PDPage(pageSize);
        document.addPage(page);
        stream = new PDPageContentStream(document, page);
        float[] margins = context.getMargins() == null ? new float[]{0, 0, 0, 0} : context.getMargins();
        context.setCursorX(margins[3]);
        context.setCursorY(pageSize.getHeight() - margins[0]);
        context.setPageWidth(pageSize.getWidth());
        context.setPageHeight(pageSize.getHeight());
        context.setPageNumber(document.getNumberOfPages());
        context.setNewPage(true);
    }

    @Override
    public void close() throws IOException {
        if (stream != null) {
            stream.close();
            stream = null;
        }
    }
}
