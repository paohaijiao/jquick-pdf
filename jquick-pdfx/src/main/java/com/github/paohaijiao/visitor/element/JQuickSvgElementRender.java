package com.github.paohaijiao.visitor.element;

import com.github.paohaijiao.model.JStyleAttributes;
import com.github.paohaijiao.util.JSvgUtil;
import com.github.paohaijiao.visitor.context.JQuickRenderContext;
import lombok.Data;
import org.apache.batik.anim.dom.SAXSVGDocumentFactory;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.PNGTranscoder;
import org.apache.batik.util.XMLResourceDescriptor;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.w3c.dom.svg.SVGDocument;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringReader;

@Data
public class JQuickSvgElementRender implements JQuickElementRender {

    private static final float DEFAULT_DPI = 72f;

    private static final float RENDER_DPI = 300f;

    private static final float PNG_SCALE = RENDER_DPI / DEFAULT_DPI;

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
        float[] dimensions = JSvgUtil.parseSvgDimensions(svgContent);
        float svgWidth = dimensions[0];
        float svgHeight = dimensions[1];
        float finalWidth = width > 0 ? width : svgWidth;
        float finalHeight = height > 0 ? height : svgHeight;
        if (finalWidth <= 0) {
            finalWidth = 120f;
        }
        if (finalHeight <= 0) {
            finalHeight = 80f;
        }
        if (width > 0 && height <= 0 && svgWidth > 0) {
            finalHeight = svgHeight * (width / svgWidth);
        } else if (height > 0 && width <= 0 && svgHeight > 0) {
            finalWidth = svgWidth * (height / svgHeight);
        }
        byte[] pngBytes = rasterize(finalWidth, finalHeight);
        PDImageXObject image = PDImageXObject.createFromByteArray(document, pngBytes, "svg");
        float x = context.getCursorX();
        float y = context.getCursorY() - finalHeight;
        stream.drawImage(image, x, y, finalWidth, finalHeight);
        context.setCursorY(y - 8f);
    }

    private byte[] rasterize(float finalWidth, float finalHeight) throws IOException {
        try {
            String parser = XMLResourceDescriptor.getXMLParserClassName();
            SAXSVGDocumentFactory factory = new SAXSVGDocumentFactory(parser);
            SVGDocument svgDocument = factory.createSVGDocument(null, new StringReader(svgContent));
            PNGTranscoder transcoder = new PNGTranscoder();
            transcoder.addTranscodingHint(PNGTranscoder.KEY_WIDTH, Math.max(1f, finalWidth * PNG_SCALE));
            transcoder.addTranscodingHint(PNGTranscoder.KEY_HEIGHT, Math.max(1f, finalHeight * PNG_SCALE));
            ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
            TranscoderOutput output = new TranscoderOutput(pngOutputStream);
            transcoder.transcode(new TranscoderInput(svgDocument), output);
            return pngOutputStream.toByteArray();
        } catch (Exception e) {
            throw new IOException("Failed to rasterize svg", e);
        }
    }

    private void applyStyle() {
        if (style == null) {
            return;
        }
        width = parseFloat(style.get("width"), width);
        height = parseFloat(style.get("height"), height);
    }

    private float parseFloat(Object value, float defaultValue) {
        if (value == null) {
            return defaultValue;
        }
        try {
            return Float.parseFloat(value.toString().replace("px", "").trim());
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }
}
