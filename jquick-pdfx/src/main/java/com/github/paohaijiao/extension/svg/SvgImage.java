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
import com.itextpdf.kernel.exceptions.PdfException;
import com.itextpdf.kernel.pdf.canvas.wmf.WmfImageData;
import com.itextpdf.kernel.pdf.tagutils.AccessibilityProperties;
import com.itextpdf.kernel.pdf.tagutils.DefaultAccessibilityProperties;
import com.itextpdf.kernel.pdf.xobject.PdfXObject;
import com.itextpdf.layout.element.AbstractElement;
import com.itextpdf.layout.element.BlockElement;
import com.itextpdf.layout.element.ILeafElement;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.properties.ObjectFit;
import com.itextpdf.layout.properties.UnitValue;
import com.itextpdf.layout.renderer.IRenderer;
import com.itextpdf.layout.tagging.IAccessibleElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * packageName com.github.paohaijiao.extension.svg
 *
 * @author Martin
 * @version 1.0.0
 * @since 2025/7/20
 */
public class SvgImage extends BlockElement<SvgImage> implements ILeafElement, IAccessibleElement {
    protected String svg;
    protected DefaultAccessibilityProperties tagProperties;
    protected float width;
    protected float height;
    protected Integer pageNum;


    public SvgImage(String svg) {
        this.svg = svg;
        this.width = JSvgUtil.parseSvgDimensions(svg )[0];
        this.height = JSvgUtil.parseSvgDimensions(svg )[1];
    }

    public SvgImage(String svg, float width) {
        this.svg = svg;
        this.width = JSvgUtil.parseSvgDimensions(svg )[0];
        this.height = JSvgUtil.parseSvgDimensions(svg )[1];
        this.setWidth(width);
    }

    public SvgImage(String svg, float left, float bottom, float width) {
        this.svg = svg;
        this.width = JSvgUtil.parseSvgDimensions(svg )[0];
        this.height = JSvgUtil.parseSvgDimensions(svg )[1];
        this.setProperty(34, left);
        this.setProperty(14, bottom);
        this.setWidth(width);
        this.setProperty(52, 4);
    }

    public SvgImage(String svg, float left, float bottom) {
        this.svg = svg;
        this.width = JSvgUtil.parseSvgDimensions(svg )[0];
        this.height = JSvgUtil.parseSvgDimensions(svg )[1];
        this.setProperty(34, left);
        this.setProperty(14, bottom);
        this.setProperty(52, 4);
    }





    public String getSvg() {
        return this.svg;
    }

    public SvgImage setRotationAngle(double radAngle) {
        this.setProperty(55, radAngle);
        return this;
    }

    public UnitValue getMarginLeft() {
        return (UnitValue)this.getProperty(44);
    }

    public SvgImage setMarginLeft(float value) {
        UnitValue marginUV = UnitValue.createPointValue(value);
        this.setProperty(44, marginUV);
        return this;
    }

    public UnitValue getMarginRight() {
        return (UnitValue)this.getProperty(45);
    }

    public SvgImage setMarginRight(float value) {
        UnitValue marginUV = UnitValue.createPointValue(value);
        this.setProperty(45, marginUV);
        return this;
    }

    public UnitValue getMarginTop() {
        return (UnitValue)this.getProperty(46);
    }

    public SvgImage setMarginTop(float value) {
        UnitValue marginUV = UnitValue.createPointValue(value);
        this.setProperty(46, marginUV);
        return this;
    }

    public UnitValue getMarginBottom() {
        return (UnitValue)this.getProperty(43);
    }

    public SvgImage setMarginBottom(float value) {
        UnitValue marginUV = UnitValue.createPointValue(value);
        this.setProperty(43, marginUV);
        return this;
    }

    public SvgImage setMargins(float marginTop, float marginRight, float marginBottom, float marginLeft) {
        return this.setMarginTop(marginTop).setMarginRight(marginRight).setMarginBottom(marginBottom).setMarginLeft(marginLeft);
    }

    public UnitValue getPaddingLeft() {
        return (UnitValue)this.getProperty(48);
    }

    public SvgImage setPaddingLeft(float value) {
        UnitValue paddingUV = UnitValue.createPointValue(value);
        this.setProperty(48, paddingUV);
        return this;
    }

    public UnitValue getPaddingRight() {
        return (UnitValue)this.getProperty(49);
    }

    public SvgImage setPaddingRight(float value) {
        UnitValue paddingUV = UnitValue.createPointValue(value);
        this.setProperty(49, paddingUV);
        return this;
    }

    public UnitValue getPaddingTop() {
        return (UnitValue)this.getProperty(50);
    }

    public SvgImage setPaddingTop(float value) {
        UnitValue paddingUV = UnitValue.createPointValue(value);
        this.setProperty(50, paddingUV);
        return this;
    }

    public UnitValue getPaddingBottom() {
        return (UnitValue)this.getProperty(47);
    }

    public SvgImage setPaddingBottom(float value) {
        UnitValue paddingUV = UnitValue.createPointValue(value);
        this.setProperty(47, paddingUV);
        return this;
    }

    public SvgImage setPadding(float commonPadding) {
        return this.setPaddings(commonPadding, commonPadding, commonPadding, commonPadding);
    }

    public SvgImage setPaddings(float paddingTop, float paddingRight, float paddingBottom, float paddingLeft) {
        this.setPaddingTop(paddingTop);
        this.setPaddingRight(paddingRight);
        this.setPaddingBottom(paddingBottom);
        this.setPaddingLeft(paddingLeft);
        return this;
    }

    public SvgImage scale(float horizontalScaling, float verticalScaling) {
        this.setProperty(29, horizontalScaling);
        this.setProperty(76, verticalScaling);
        return this;
    }

    public SvgImage scaleToFit(float fitWidth, float fitHeight) {
        float horizontalScaling = fitWidth / this.width;
        float verticalScaling = fitHeight / this.height;
        return this.scale(Math.min(horizontalScaling, verticalScaling), Math.min(horizontalScaling, verticalScaling));
    }

    public SvgImage scaleAbsolute(float fitWidth, float fitHeight) {
        float horizontalScaling = fitWidth / this.width;
        float verticalScaling = fitHeight / this.height;
        return this.scale(horizontalScaling, verticalScaling);
    }

    public SvgImage setAutoScale(boolean autoScale) {
        if (this.hasProperty(5) && this.hasProperty(4) && autoScale && ((Boolean)this.getProperty(5) || (Boolean)this.getProperty(4))) {
            Logger logger = LoggerFactory.getLogger(Image.class);
            logger.warn("The image cannot be auto scaled and scaled by a certain parameter simultaneously");
        }

        this.setProperty(3, autoScale);
        return this;
    }

    public SvgImage setAutoScaleHeight(boolean autoScale) {
        if (this.hasProperty(5) && autoScale && (Boolean)this.getProperty(5)) {
            this.setProperty(5, false);
            this.setProperty(4, false);
            this.setProperty(3, true);
        } else {
            this.setProperty(5, autoScale);
        }

        return this;
    }

    public SvgImage setAutoScaleWidth(boolean autoScale) {
        if (this.hasProperty(4) && autoScale && (Boolean)this.getProperty(4)) {
            this.setProperty(5, false);
            this.setProperty(4, false);
            this.setProperty(3, true);
        } else {
            this.setProperty(5, autoScale);
        }

        return this;
    }

    public SvgImage setFixedPosition(float left, float bottom) {
        this.setFixedPosition(left, bottom, this.getWidth());
        return this;
    }

    public SvgImage setFixedPosition(int pageNumber, float left, float bottom) {
        this.setFixedPosition(pageNumber, left, bottom, this.getWidth());
        return this;
    }

    public float getImageWidth() {
        return width;
    }

    public float getImageHeight() {
        return height;
    }

    public SvgImage setHeight(float height) {
        UnitValue heightAsUV = UnitValue.createPointValue(height);
        this.setProperty(27, heightAsUV);
        return this;
    }

    public SvgImage setHeight(UnitValue height) {
        this.setProperty(27, height);
        return this;
    }

    public SvgImage setMaxHeight(float maxHeight) {
        UnitValue maxHeightAsUv = UnitValue.createPointValue(maxHeight);
        this.setProperty(84, maxHeightAsUv);
        return this;
    }

    public SvgImage setMaxHeight(UnitValue maxHeight) {
        this.setProperty(84, maxHeight);
        return this;
    }

    public SvgImage setMinHeight(float minHeight) {
        UnitValue minHeightAsUv = UnitValue.createPointValue(minHeight);
        this.setProperty(85, minHeightAsUv);
        return this;
    }

    public SvgImage setMinHeight(UnitValue minHeight) {
        this.setProperty(85, minHeight);
        return this;
    }

    public SvgImage setMaxWidth(float maxWidth) {
        UnitValue minHeightAsUv = UnitValue.createPointValue(maxWidth);
        this.setProperty(79, minHeightAsUv);
        return this;
    }

    public SvgImage setMaxWidth(UnitValue maxWidth) {
        this.setProperty(79, maxWidth);
        return this;
    }

    public SvgImage setMinWidth(float minWidth) {
        UnitValue minHeightAsUv = UnitValue.createPointValue(minWidth);
        this.setProperty(80, minHeightAsUv);
        return this;
    }

    public SvgImage setMinWidth(UnitValue minWidth) {
        this.setProperty(80, minWidth);
        return this;
    }

    public SvgImage setWidth(float width) {
        this.setProperty(77, UnitValue.createPointValue(width));
        return this;
    }

    public SvgImage setWidth(UnitValue width) {
        this.setProperty(77, width);
        return this;
    }

    public UnitValue getWidth() {
        return (UnitValue)this.getProperty(77);
    }

    public float getImageScaledWidth() {
        return null == this.getProperty(29) ? width : width * (Float)this.getProperty(29);
    }

    public float getImageScaledHeight() {
        return null == this.getProperty(76) ? height : height * (Float)this.getProperty(76);
    }

    public SvgImage setObjectFit(ObjectFit objectFit) {
        this.setProperty(125, objectFit);
        return this;
    }

    public ObjectFit getObjectFit() {
        return this.hasProperty(125) ? (ObjectFit)this.getProperty(125) : ObjectFit.FILL;
    }

    public AccessibilityProperties getAccessibilityProperties() {
        if (this.tagProperties == null) {
            this.tagProperties = new DefaultAccessibilityProperties("Figure");
        }

        return this.tagProperties;
    }

    public SvgImage setNeutralRole() {
        this.getAccessibilityProperties().setRole((String)null);
        return this;
    }

    protected IRenderer makeNewRenderer() {
        return new JSvgImageRenderer(this);
    }

    public  Integer getPageNum(){
        return this.pageNum;
    }
    public  void setPageNum(Integer pageNum){
         this.pageNum=pageNum;
    }
}
