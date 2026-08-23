package com.github.paohaijiao.visitor.element;

import com.github.paohaijiao.visitor.context.JQuickRenderContext;
import lombok.Data;
import org.apache.pdfbox.pdmodel.PDPageContentStream;

import java.io.IOException;

@Data
public class JQuickAreaBreakElementRender implements JQuickElementRender {

    private String breakType;

    private String pageSize;

    public JQuickAreaBreakElementRender() {
    }

    public JQuickAreaBreakElementRender(String breakType, String pageSize) {
        this.breakType = breakType;
        this.pageSize = pageSize;
    }

    @Override
    public void draw(PDPageContentStream stream, JQuickRenderContext context) throws IOException {
        if (stream == null || context == null) {
            return;
        }
        context.setNewPage(true);
        context.setCursorX(context.getMargins()[3]);
        context.setCursorY(context.getPageHeight() - context.getMargins()[0]);
    }
}
