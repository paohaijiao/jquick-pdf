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

import com.github.paohaijiao.util.JSvgUtil;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.exceptions.PdfException;
import com.itextpdf.kernel.pdf.canvas.wmf.WmfImageData;
import com.itextpdf.kernel.pdf.tagutils.AccessibilityProperties;
import com.itextpdf.kernel.pdf.xobject.PdfImageXObject;
import com.itextpdf.kernel.pdf.xobject.PdfXObject;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.properties.ObjectFit;
import com.itextpdf.layout.properties.UnitValue;
import com.itextpdf.layout.renderer.IRenderer;
import com.itextpdf.layout.renderer.ImageRenderer;
import org.apache.batik.anim.dom.SAXSVGDocumentFactory;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.PNGTranscoder;
import org.apache.batik.util.XMLResourceDescriptor;
import org.w3c.dom.svg.SVGDocument;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.net.URL;


/**
 * packageName com.github.paohaijiao.extension.svg
 *
 * @author Martin
 * @version 1.0.0
 * @since 2025/7/20
 */
public class SvgImage extends Image {
    private static final float DEFAULT_DPI = 72f;
    private String svgContent;
    private float explicitWidth = -1;
    private float explicitHeight = -1;

    /**
     * Creates a new SvgImage from SVG string content.
     *
     * @param svgContent the SVG content as a string
     */
    public SvgImage(String svgContent) {
        super(convertSvgToXObject(svgContent, -1, -1));
        this.svgContent = svgContent;
        float[] diemonsions=JSvgUtil.parseSvgDimensions(svgContent);
        this.explicitWidth = diemonsions[0];
        this.explicitHeight = diemonsions[1];
        this.setProperty(19, true); // Mark as reusable
    }

    /**
     * Creates a new SvgImage with explicit dimensions.
     *
     * @param svgContent the SVG content as a string
     * @param width      the desired width in points
     * @param height     the desired height in points
     */
    public SvgImage(String svgContent, float width, float height) {
        super(convertSvgToXObject(svgContent, width, height));
        this.svgContent = svgContent;
        this.explicitWidth = width;
        this.explicitHeight = height;
        this.setProperty(19, true);
    }

    /**
     * Creates a new SvgImage from an InputStream.
     *
     * @param svgStream the SVG content as an InputStream
     * @throws IOException if reading the stream fails
     */
    public SvgImage(InputStream svgStream) throws IOException {
        this(streamToString(svgStream));
    }

    /**
     * Creates a new SvgImage from an InputStream with explicit dimensions.
     *
     * @param svgStream the SVG content as an InputStream
     * @param width     the desired width in points
     * @param height    the desired height in points
     * @throws IOException if reading the stream fails
     */
    public SvgImage(InputStream svgStream, float width, float height) throws IOException {
        this(streamToString(svgStream), width, height);
    }

    /**
     * Creates a new SvgImage from a URL.
     *
     * @param svgUrl the URL of the SVG file
     * @throws IOException if reading the URL fails
     */
    public SvgImage(URL svgUrl) throws IOException {
        this(svgUrl.openStream());
    }

    /**
     * Creates a new SvgImage from a URL with explicit dimensions.
     *
     * @param svgUrl  the URL of the SVG file
     * @param width   the desired width in points
     * @param height  the desired height in points
     * @throws IOException if reading the URL fails
     */
    public SvgImage(URL svgUrl, float width, float height) throws IOException {
        this(svgUrl.openStream(), width, height);
    }



    /**
     * Helper method to convert InputStream to String.
     */
    private static String streamToString(InputStream is) throws IOException {
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length;
        while ((length = is.read(buffer)) != -1) {
            result.write(buffer, 0, length);
        }
        return result.toString("UTF-8");
    }


    @Override
    public PdfXObject getXObject() {
        return this.xObject;
    }

    @Override
    public Image setRotationAngle(double radAngle) {
        super.setRotationAngle(radAngle);
        return this;
    }

    @Override
    public UnitValue getMarginLeft() {
        return super.getMarginLeft();
    }

    @Override
    public Image setMarginLeft(float value) {
        super.setMarginLeft(value);
        return this;
    }

    @Override
    public UnitValue getMarginRight() {
        return super.getMarginRight();
    }

    @Override
    public Image setMarginRight(float value) {
        super.setMarginRight(value);
        return this;
    }

    @Override
    public UnitValue getMarginTop() {
        return super.getMarginTop();
    }

    @Override
    public Image setMarginTop(float value) {
        super.setMarginTop(value);
        return this;
    }

    @Override
    public UnitValue getMarginBottom() {
        return super.getMarginBottom();
    }

    @Override
    public Image setMarginBottom(float value) {
        super.setMarginBottom(value);
        return this;
    }

    @Override
    public Image setMargins(float marginTop, float marginRight, float marginBottom, float marginLeft) {
        super.setMargins(marginTop, marginRight, marginBottom, marginLeft);
        return this;
    }

    @Override
    public UnitValue getPaddingLeft() {
        return super.getPaddingLeft();
    }

    @Override
    public Image setPaddingLeft(float value) {
        super.setPaddingLeft(value);
        return this;
    }

    @Override
    public UnitValue getPaddingRight() {
        return super.getPaddingRight();
    }

    @Override
    public Image setPaddingRight(float value) {
        super.setPaddingRight(value);
        return this;
    }

    @Override
    public UnitValue getPaddingTop() {
        return super.getPaddingTop();
    }

    @Override
    public Image setPaddingTop(float value) {
        super.setPaddingTop(value);
        return this;
    }

    @Override
    public UnitValue getPaddingBottom() {
        return super.getPaddingBottom();
    }

    @Override
    public Image setPaddingBottom(float value) {
        super.setPaddingBottom(value);
        return this;
    }

    @Override
    public Image setPadding(float commonPadding) {
        super.setPadding(commonPadding);
        return this;
    }

    @Override
    public Image setPaddings(float paddingTop, float paddingRight, float paddingBottom, float paddingLeft) {
        super.setPaddings(paddingTop, paddingRight, paddingBottom, paddingLeft);
        return this;
    }

    @Override
    public Image scale(float horizontalScaling, float verticalScaling) {
        super.scale(horizontalScaling, verticalScaling);
        return this;
    }

    @Override
    public Image scaleToFit(float fitWidth, float fitHeight) {
        super.scaleToFit(fitWidth, fitHeight);
        return this;
    }

    @Override
    public Image scaleAbsolute(float fitWidth, float fitHeight) {
        super.scaleAbsolute(fitWidth, fitHeight);
        return this;
    }

    @Override
    public Image setAutoScale(boolean autoScale) {
        super.setAutoScale(autoScale);
        return this;
    }

    @Override
    public Image setAutoScaleHeight(boolean autoScale) {
        super.setAutoScaleHeight(autoScale);
        return this;
    }

    @Override
    public Image setAutoScaleWidth(boolean autoScale) {
        super.setAutoScaleWidth(autoScale);
        return this;
    }

    @Override
    public Image setFixedPosition(float left, float bottom) {
        super.setFixedPosition(left, bottom);
        return this;
    }

    @Override
    public Image setFixedPosition(int pageNumber, float left, float bottom) {
        super.setFixedPosition(pageNumber, left, bottom);
        return this;
    }

    @Override
    public float getImageWidth() {
        return super.getImageWidth();
    }

    @Override
    public float getImageHeight() {
        return super.getImageHeight();
    }

    @Override
    public Image setHeight(float height) {
        super.setHeight(height);
        return this;
    }

    @Override
    public Image setHeight(UnitValue height) {
        super.setHeight(height);
        return this;
    }

    @Override
    public Image setMaxHeight(float maxHeight) {
        super.setMaxHeight(maxHeight);
        return this;
    }

    @Override
    public Image setMaxHeight(UnitValue maxHeight) {
        super.setMaxHeight(maxHeight);
        return this;
    }

    @Override
    public Image setMinHeight(float minHeight) {
        super.setMinHeight(minHeight);
        return this;
    }

    @Override
    public Image setMinHeight(UnitValue minHeight) {
        super.setMinHeight(minHeight);
        return this;
    }

    @Override
    public Image setMaxWidth(float maxWidth) {
        super.setMaxWidth(maxWidth);
        return this;
    }

    @Override
    public Image setMaxWidth(UnitValue maxWidth) {
        super.setMaxWidth(maxWidth);
        return this;
    }

    @Override
    public Image setMinWidth(float minWidth) {
        super.setMinWidth(minWidth);
        return this;
    }

    @Override
    public Image setMinWidth(UnitValue minWidth) {
        super.setMinWidth(minWidth);
        return this;
    }

    @Override
    public Image setWidth(float width) {
        super.setWidth(width);
        return this;
    }

    @Override
    public Image setWidth(UnitValue width) {
        super.setWidth(width);
        return this;
    }

    @Override
    public UnitValue getWidth() {
        return super.getWidth();
    }

    @Override
    public float getImageScaledWidth() {
        return super.getImageScaledWidth();
    }

    @Override
    public float getImageScaledHeight() {
        return super.getImageScaledHeight();
    }

    @Override
    public Image setObjectFit(ObjectFit objectFit) {
        super.setObjectFit(objectFit);
        return this;
    }

    @Override
    public ObjectFit getObjectFit() {
        return super.getObjectFit();
    }

    @Override
    public AccessibilityProperties getAccessibilityProperties() {
        return super.getAccessibilityProperties();
    }

    @Override
    public Image setNeutralRole() {
        super.setNeutralRole();
        return this;
    }

    @Override
    protected IRenderer makeNewRenderer() {
        return new ImageRenderer(this);
    }

    private static ImageData checkImageType(ImageData image) {
        if (image instanceof WmfImageData) {
            throw new PdfException("Cannot create layout image by WmfImage instance. First convert the image into FormXObject and then use the corresponding layout image constructor.");
        } else {
            return image;
        }
    }

    /**
     * Gets the original SVG content.
     *
     * @return the SVG content as a string
     */
    public String getSvgContent() {
        return svgContent;
    }

    /**
     * Gets the explicit width if set, or -1 if using native SVG width.
     *
     * @return the explicit width in points, or -1
     */
    public float getExplicitWidth() {
        return explicitWidth;
    }

    /**
     * Gets the explicit height if set, or -1 if using native SVG height.
     *
     * @return the explicit height in points, or -1
     */
    public float getExplicitHeight() {
        return explicitHeight;
    }
    private static ImageData createImageDataFromSvg(String svgContent, float width, float height) {
        PdfImageXObject xObject = convertSvgToXObject(svgContent, width, height);
        return ImageDataFactory.create(xObject.getImageBytes());
    }
    /**
     * Converts SVG content to a PdfXObject.
     *
     * @param svgContent the SVG content
     * @param width      desired width (or -1 to use SVG's native width)
     * @param height     desired height (or -1 to use SVG's native height)
     * @return the created PdfXObject
     */
    private static PdfImageXObject  convertSvgToXObject(String svgContent, float width, float height) {
        try {
            String parser = XMLResourceDescriptor.getXMLParserClassName();
            SAXSVGDocumentFactory factory = new SAXSVGDocumentFactory(parser);
            SVGDocument svgDocument = factory.createSVGDocument(null, new StringReader(svgContent));

            PNGTranscoder transcoder = new PNGTranscoder();

            // Calculate dimensions
            float[] dimensions = JSvgUtil.parseSvgDimensions(svgContent);
            float svgWidth = dimensions[0];
            float svgHeight = dimensions[1];

            // Apply explicit dimensions if provided
            float finalWidth = width > 0 ? width : svgWidth;
            float finalHeight = height > 0 ? height : svgHeight;

            // Maintain aspect ratio if only one dimension is specified
            if (width > 0 && height <= 0) {
                finalHeight = svgHeight * (width / svgWidth);
            } else if (height > 0 && width <= 0) {
                finalWidth = svgWidth * (height / svgHeight);
            }

            transcoder.addTranscodingHint(PNGTranscoder.KEY_WIDTH, finalWidth);
            transcoder.addTranscodingHint(PNGTranscoder.KEY_HEIGHT, finalHeight);

            ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
            TranscoderOutput output = new TranscoderOutput(pngOutputStream);
            transcoder.transcode(new TranscoderInput(svgDocument), output);

            byte[] pngBytes = pngOutputStream.toByteArray();
            ImageData imageData = ImageDataFactory.create(pngBytes);
            return new PdfImageXObject(imageData);
        } catch (Exception e) {
            throw new RuntimeException("Failed to convert SVG to Image", e);
        }
    }
}
