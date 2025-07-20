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
package com.github.paohaijiao.extension.svg;


import com.github.paohaijiao.console.JConsole;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.kernel.pdf.xobject.PdfFormXObject;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.layout.LayoutArea;
import com.itextpdf.layout.layout.LayoutContext;
import com.itextpdf.layout.layout.LayoutResult;
import com.itextpdf.layout.properties.ObjectFit;
import com.itextpdf.layout.properties.Property;
import com.itextpdf.layout.properties.UnitValue;
import com.itextpdf.layout.renderer.AbstractRenderer;
import com.itextpdf.layout.renderer.DrawContext;
import com.itextpdf.layout.renderer.IRenderer;
import com.itextpdf.layout.renderer.ILeafElementRenderer;
import org.apache.batik.anim.dom.SAXSVGDocumentFactory;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.PNGTranscoder;
import org.apache.batik.util.XMLResourceDescriptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.svg.SVGDocument;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.util.Map;

/**
 * packageName com.github.paohaijiao.extension.svg
 *
 * @author Martin
 * @version 1.0.0
 * @since 2025/7/20
 */
public class JSvgImageRenderer extends AbstractRenderer implements ILeafElementRenderer {
    private static final Logger logger = LoggerFactory.getLogger(JSvgImageRenderer.class);

    private final SvgImage modelElement;
    private Rectangle occupiedArea;

    public JSvgImageRenderer(SvgImage modelElement) {
        this.modelElement = modelElement;
    }

    @Override
    public LayoutResult layout(LayoutContext layoutContext) {
        Rectangle parentBBox = layoutContext.getArea().getBBox();
        float parentWidth = parentBBox.getWidth();
        float parentHeight = parentBBox.getHeight();
        float width = modelElement.getImageWidth();
        float height = modelElement.getImageHeight();
        UnitValue widthUV = modelElement.<UnitValue>getProperty(Property.WIDTH);
        UnitValue heightUV = modelElement.<UnitValue>getProperty(Property.HEIGHT);
        if (widthUV != null && widthUV.isPointValue()) {
            width = widthUV.getValue();
        }

        if (heightUV != null && heightUV.isPointValue()) {
            height = heightUV.getValue();
        }
        Float scaleX = modelElement.<Float>getProperty(Property.HORIZONTAL_SCALING);
        Float scaleY = modelElement.<Float>getProperty(Property.VERTICAL_SCALING);
        if (scaleX != null) {
            width *= scaleX;
        }
        if (scaleY != null) {
            height *= scaleY;
        }
        this.occupiedArea = new Rectangle(
                parentBBox.getX(),
                parentBBox.getY() + parentHeight - height, // 从顶部开始布局
                width,
                height
        );
        LayoutArea layoutArea=new LayoutArea(modelElement.getPageNum(),this.occupiedArea);
        return new LayoutResult(
                LayoutResult.FULL,
                layoutArea,
                null,
                null
        );
    }

    @Override
    public IRenderer getNextRenderer() {
        return new JSvgImageRenderer(modelElement);
    }

    @Override
    public void draw(DrawContext drawContext) {
        try {
            // 1. 获取绘制区域
            Rectangle area = this.occupiedArea;
            if (area == null) {
                logger.warn("No occupied area set for SVG image");
                return;
            }
            PdfCanvas canvas = drawContext.getCanvas();
            canvas.saveState();
            applyTransformation(canvas, area);
            JConsole console = new JConsole();
            console.info(modelElement.getSvg());
            PdfFormXObject xObject = convertSvgToXObject(modelElement.getSvg(), drawContext);

            ObjectFit objectFit = modelElement.<ObjectFit>getProperty(Property.OBJECT_FIT);
            if (objectFit == null) {
                objectFit = ObjectFit.FILL;
            }
            Rectangle fitRect = calculateObjectFitRect(xObject, area, objectFit);
            canvas.addXObject(xObject, fitRect.getWidth(),fitRect.getHeight());
            drawBorders(drawContext);

            canvas.restoreState();

        } catch (Exception e) {
            logger.error("Failed to draw SVG image", e);
            throw new RuntimeException("Failed to draw SVG image", e);
        }
    }

    @Override
    public LayoutArea getOccupiedArea() {
        LayoutArea layoutArea=new LayoutArea(modelElement.getPageNum(),this.occupiedArea);
        return layoutArea;
    }

    @Override
    public void move(float deltaX, float deltaY) {
        if (this.occupiedArea != null) {
            this.occupiedArea.setX(this.occupiedArea.getX() + deltaX);
            this.occupiedArea.setY(this.occupiedArea.getY() + deltaY);
        }
    }
    private PdfFormXObject convertSvgToXObject(String svg, DrawContext drawContext) {
        try {
            String parser = XMLResourceDescriptor.getXMLParserClassName();
            SAXSVGDocumentFactory factory = new SAXSVGDocumentFactory(parser);
            SVGDocument svgDocument = factory.createSVGDocument(null, new StringReader(svg));
            PNGTranscoder transcoder = new PNGTranscoder();
            transcoder.addTranscodingHint(PNGTranscoder.KEY_WIDTH, (float)modelElement.getImageWidth());
            transcoder.addTranscodingHint(PNGTranscoder.KEY_HEIGHT, (float)modelElement.getImageHeight());

            ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
            TranscoderOutput output = new TranscoderOutput(pngOutputStream);
            transcoder.transcode(new TranscoderInput(svgDocument), output);
            byte[] pngBytes = pngOutputStream.toByteArray();
            String filePath = "D:/test/image.png";
            try (FileOutputStream fos = new FileOutputStream(filePath)) {
                fos.write(pngBytes); // 写入字节数据
                System.out.println("PNG 图片已保存至：" + filePath);
            } catch (IOException e) {
                System.err.println("保存失败：" + e.getMessage());
            }
            ImageData imageData = ImageDataFactory.create(pngBytes);
            PdfFormXObject xObject = new PdfFormXObject(
                    new Rectangle(modelElement.getImageWidth(), modelElement.getImageHeight()));
            Canvas canvas = new Canvas(xObject, drawContext.getDocument());
            canvas.add(new Image(imageData)
                    .setWidth(modelElement.getImageWidth())
                    .setHeight(modelElement.getImageHeight()));
            canvas.close();
            return xObject;
        } catch (Exception e) {
            throw new RuntimeException("Failed to convert SVG to PDF XObject", e);
        }
    }

    private Rectangle calculateObjectFitRect(PdfFormXObject xObject, Rectangle area, ObjectFit objectFit) {
        float imgWidth = xObject.getWidth();
        float imgHeight = xObject.getHeight();
        float areaWidth = area.getWidth();
        float areaHeight = area.getHeight();

        switch (objectFit) {
            case CONTAIN:
                float scale = Math.min(areaWidth / imgWidth, areaHeight / imgHeight);
                float scaledWidth = imgWidth * scale;
                float scaledHeight = imgHeight * scale;
                float x = (areaWidth - scaledWidth) / 2;
                float y = (areaHeight - scaledHeight) / 2;
                return new Rectangle(x, y, scaledWidth, scaledHeight);

            case COVER:
                float coverScale = Math.max(areaWidth / imgWidth, areaHeight / imgHeight);
                return new Rectangle(0, 0, imgWidth * coverScale, imgHeight * coverScale);

            case FILL:
                return new Rectangle(0, 0, areaWidth, areaHeight);

            case SCALE_DOWN:
                float scaleDown = Math.min(1, Math.min(areaWidth / imgWidth, areaHeight / imgHeight));
                return new Rectangle(0, 0, imgWidth * scaleDown, imgHeight * scaleDown);

            case NONE:
            default:
                float noneX = (areaWidth - imgWidth) / 2;
                float noneY = (areaHeight - imgHeight) / 2;
                return new Rectangle(noneX, noneY, imgWidth, imgHeight);
        }
    }

    private void applyTransformation(PdfCanvas canvas, Rectangle area) {
        // 应用位置变换
        canvas.concatMatrix(1, 0, 0, 1, area.getX(), area.getY());
        Double rotation = modelElement.<Double>getProperty(Property.ROTATION_ANGLE);
        if (rotation != null && rotation != 0) {
            float centerX = area.getWidth() / 2;
            float centerY = area.getHeight() / 2;
            canvas.concatMatrix(
                    (float) Math.cos(rotation), (float) Math.sin(rotation),
                    -(float) Math.sin(rotation), (float) Math.cos(rotation),
                    centerX, centerY
            );
            canvas.concatMatrix(1, 0, 0, 1, -centerX, -centerY);
        }

        // 应用缩放
        Float scaleX = modelElement.<Float>getProperty(Property.HORIZONTAL_SCALING);
        Float scaleY = modelElement.<Float>getProperty(Property.VERTICAL_SCALING);
        if (scaleX != null && scaleY != null) {
            canvas.concatMatrix(scaleX, 0, 0, scaleY, 0, 0);
        }
    }

    private void drawBorders(DrawContext drawContext) {
        Border[] borders = getBorders();
        if (borders != null && occupiedArea != null) {
            Rectangle borderArea = occupiedArea.clone();
            for (Border border : borders) {
                if (border != null) {
                    border.draw(drawContext.getCanvas(), borderArea);
                }
            }
        }
    }

    public Border[] getBorders() {
        return modelElement.<Border[]>getProperty(Property.BORDER);
    }

    @Override
    public float getAscent() {
        return getOccupiedArea() != null ? getOccupiedArea().getBBox().getHeight() : 0f;
    }

    @Override
    public float getDescent() {
        return 0;
    }
}
