package com.github.paohaijiao.visitor.element;

import com.github.paohaijiao.visitor.context.JQuickRenderContext;
import org.apache.pdfbox.pdmodel.PDPageContentStream;

import java.io.IOException;

public interface JQuickElementRender {

    void draw(PDPageContentStream stream, JQuickRenderContext context) throws IOException;

}
