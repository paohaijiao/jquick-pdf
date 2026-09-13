package com.github.paohaijiao.visitor.element;

import com.github.paohaijiao.model.JStyleAttributes;
import com.github.paohaijiao.util.JSvgUtil;
import com.github.paohaijiao.visitor.context.JQuickRenderContext;
import com.github.paohaijiao.visitor.render.PdfBoxLayoutEngine;
import com.github.paohaijiao.visitor.render.PdfBoxRenderAdapter;
import com.github.paohaijiao.visitor.render.PdfBoxStyleModel;
import lombok.Data;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.PNGTranscoder;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.pdmodel.graphics.state.PDExtendedGraphicsState;
import org.apache.pdfbox.util.Matrix;
import org.w3c.dom.svg.SVGDocument;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Data
public class JQuickSvgElementRender implements JQuickElementRender {

    private static final float DEFAULT_DPI = 72f;

    private static final float RENDER_DPI = 300f;

    private static final float PNG_SCALE = RENDER_DPI / DEFAULT_DPI;

    private static final float BLOCK_GAP = 8f;

    private String svgContent;

    private JStyleAttributes style;

    private float width = -1f;

    private float height = -1f;

    public JQuickSvgElementRender(String svgContent, JStyleAttributes style) {
        this.svgContent = svgContent;
        this.style = style;
        applyStyle();
    }

    @Override
    public void draw(PDPageContentStream stream, JQuickRenderContext context) throws IOException {
        if (stream == null || context == null || svgContent == null || svgContent.isEmpty()) {
            return;
        }
        PDDocument document = context.getDocument();
        if (document == null) {
            return;
        }
        PdfBoxStyleModel styleModel = PdfBoxStyleModel.from(style);
        SVGDocument svgDocument = JSvgUtil.parse(svgContent);
        float[] dimensions = JSvgUtil.parseSvgDimensions(svgDocument);
        float availableWidth = resolveAvailableWidth(context, styleModel);
        float finalWidth = resolveWidth(dimensions[0], styleModel, availableWidth);
        float finalHeight = resolveHeight(dimensions[0], dimensions[1], finalWidth, styleModel);
        float outerHeight = styleModel.getMarginTop() + styleModel.getPaddingTop() + finalHeight + styleModel.getPaddingBottom() + styleModel.getMarginBottom() + BLOCK_GAP;
        PdfBoxLayoutEngine layout = context.getLayoutEngine();
        if (layout != null) {
            layout.ensureSpace(outerHeight, styleModel.isKeepTogether());
            stream = layout.getStream();
        }
        float x = context.getCursorX() + styleModel.getMarginLeft();
        float top = context.getCursorY() - styleModel.getMarginTop();
        float boxWidth = finalWidth + styleModel.getPaddingLeft() + styleModel.getPaddingRight();
        float boxHeight = finalHeight + styleModel.getPaddingTop() + styleModel.getPaddingBottom();
        PdfBoxRenderAdapter.drawBox(document, stream, styleModel, x, top, boxWidth, boxHeight);
        PdfBoxRenderAdapter.drawBackgroundImage(document, stream, styleModel, x, top - boxHeight, boxWidth, boxHeight);

        byte[] pngBytes = rasterize(svgDocument, finalWidth, finalHeight);
        PDImageXObject image = PDImageXObject.createFromByteArray(document, pngBytes, "svg");
        float imageX = x + styleModel.getPaddingLeft() + styleModel.getRelativeLeft() - styleModel.getRelativeRight();
        float imageY = top - styleModel.getPaddingTop() - finalHeight
                - styleModel.getRelativeTop() + styleModel.getRelativeBottom();
        stream.saveGraphicsState();
        applyOpacity(stream, styleModel.getOpacity());
        if (styleModel.getAngleInRadians() != 0f) {
            stream.transform(Matrix.getRotateInstance(styleModel.getAngleInRadians(),
                    imageX + finalWidth / 2f, imageY + finalHeight / 2f));
        }
        stream.drawImage(image, imageX, imageY, finalWidth, finalHeight);
        stream.restoreGraphicsState();
        context.setCursorY(top - boxHeight - styleModel.getMarginBottom() - BLOCK_GAP);
    }

    /**
     * 计算 SVG 可用的内容宽度（已扣除外边距和内边距），使其不会超出 PDF 页面。
     * 无法确定可用宽度时返回 -1，表示不做限制。
     */
    private float resolveAvailableWidth(JQuickRenderContext context, PdfBoxStyleModel styleModel) {
        float availableWidth = context.getWidth();
        if (availableWidth <= 0f) {
            float[] margins = context.getMargins();
            if (margins != null && context.getPageWidth() > 0f) {
                availableWidth = context.getPageWidth() - margins[1] - margins[3];
            }
        }
        if (availableWidth <= 0f) {
            return -1f;
        }
        availableWidth -= styleModel.getMarginLeft() + styleModel.getMarginRight()
                + styleModel.getPaddingLeft() + styleModel.getPaddingRight();
        return availableWidth > 0f ? availableWidth : -1f;
    }

    private float resolveWidth(float svgWidth, PdfBoxStyleModel styleModel, float availableWidth) {
        float result = width > 0f ? width : svgWidth;
        if (styleModel.getWidth() > 0f) {
            result = styleModel.getWidth();
        }
        if (result <= 0f) {
            result = 120f;
        }
        if (styleModel.getMinWidth() > 0f) {
            result = Math.max(result, styleModel.getMinWidth());
        }
        if (styleModel.getMaxWidth() > 0f) {
            result = Math.min(result, styleModel.getMaxWidth());
        }
        if (availableWidth > 0f) {
            result = Math.min(result, availableWidth);
        }
        return result;
    }

    private float resolveHeight(float svgWidth, float svgHeight, float finalWidth, PdfBoxStyleModel styleModel) {
        float result;
        if (height > 0f) {
            result = height;
        } else if (styleModel.getHeight() > 0f) {
            result = styleModel.getHeight();
        } else if (svgWidth > 0f && svgHeight > 0f && finalWidth > 0f) {
            // 宽度被缩放到页面内时，高度按原始比例同步缩放，避免图片变形
            result = svgHeight * finalWidth / svgWidth;
        } else {
            result = svgHeight;
        }
        if (result <= 0f) {
            result = 80f;
        }
        if (styleModel.getMinHeight() > 0f) {
            result = Math.max(result, styleModel.getMinHeight());
        }
        if (styleModel.getMaxHeight() > 0f) {
            result = Math.min(result, styleModel.getMaxHeight());
        }
        return result;
    }

    private void applyOpacity(PDPageContentStream stream, float opacity) throws IOException {
        PDExtendedGraphicsState state = new PDExtendedGraphicsState();
        state.setNonStrokingAlphaConstant(opacity);
        state.setStrokingAlphaConstant(opacity);
        stream.setGraphicsStateParameters(state);
    }

    private byte[] rasterize(SVGDocument svgDocument, float finalWidth, float finalHeight) throws IOException {
        if (svgDocument == null) {
            throw new IOException("Failed to parse svg document");
        }
        try {
            PNGTranscoder transcoder = new PNGTranscoder();
            transcoder.addTranscodingHint(PNGTranscoder.KEY_WIDTH, Math.max(1f, finalWidth * PNG_SCALE));
            transcoder.addTranscodingHint(PNGTranscoder.KEY_HEIGHT, Math.max(1f, finalHeight * PNG_SCALE));
            ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
            transcoder.transcode(new TranscoderInput(svgDocument), new TranscoderOutput(pngOutputStream));
            return pngOutputStream.toByteArray();
        } catch (Exception e) {
            throw new IOException("Failed to rasterize svg", e);
        }
    }

    private void applyStyle() {
        if (style == null) {
            return;
        }
        width = com.github.paohaijiao.visitor.render.PdfBoxUnitConverter.toPoint(style.get("width"), width);
        height = com.github.paohaijiao.visitor.render.PdfBoxUnitConverter.toPoint(style.get("height"), height);
    }
}
