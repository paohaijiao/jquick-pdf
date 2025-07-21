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

import com.github.paohaijiao.factory.JImageFactory;
import com.github.paohaijiao.image.JBaseImageProvider;
import com.github.paohaijiao.model.JStyleAttributes;
import com.github.paohaijiao.parser.JQuickPDFParser;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.properties.HorizontalAlignment;

import java.net.MalformedURLException;


/**
 * packageName com.paohaijiao.javelin.visitor
 *
 * @author Martin
 * @version 1.0.0
 * @className JPdfXCommonVisitor
 * @date 2025/6/14
 * @description
 */
public class JPdfXImageVisitor extends JPdfXListVisitor {

    @Override
    public Image visitImage(JQuickPDFParser.ImageContext ctx) {
        try {
            String src = null;
            if (ctx.src() != null) {
                src = visitSrc(ctx.src());
            }
            String alt = null;
            if (ctx.alt() != null) {
                alt = visitAlt(ctx.alt());
            }
            String value = null;
            if (ctx.value() != null) {
                value = visitValue(ctx.value()).toString();
            }
            JStyleAttributes style = new JStyleAttributes();
            if (null != ctx.styleEle()) {
                style = visitStyleEle(ctx.styleEle());
            } else {
                style = new JStyleAttributes();
            }
            JBaseImageProvider imageProvider = JImageFactory.createProvider(src);
            byte[] bytes = imageProvider.loadImage();
            ImageData imageData = ImageDataFactory.create(bytes);
            Image image = new Image(imageData);
            if (null != alt) {
                image.getAccessibilityProperties().setAlternateDescription(alt);
            }
            if (null != value) {
                image.getAccessibilityProperties().setActualText(value);
            }
           // image.setMargins(-50, -60, -60, -60);
            super.buildStyle(image, style);
            return image;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static Image buildImage() {
        Image image = null;
        try {
            image = new Image(ImageDataFactory.create("path/to/image.jpg"));
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        image.setWidth(200); // 宽度
        image.setHeight(100); // 高度
        image.setAutoScale(true); // 自动缩放
        image.setRotationAngle(Math.PI / 4); // 旋转45度
        image.setOpacity(0.8f); // 透明度
        // image.setBorder(new SolidBorder(Color.BLUE, 2)); // 边框
        image.setHorizontalAlignment(HorizontalAlignment.CENTER); // 对齐方式
        image.setMargins(10, 0, 10, 0); // 边距
        return image;
    }

}
