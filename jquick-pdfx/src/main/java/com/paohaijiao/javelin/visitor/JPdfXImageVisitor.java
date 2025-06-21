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
package com.paohaijiao.javelin.visitor;

import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.List;
import com.itextpdf.layout.element.ListItem;
import com.itextpdf.layout.properties.HorizontalAlignment;
import com.paohaijiao.javelin.model.list.JListModel;
import com.paohaijiao.javelin.parser.JQuickPDFParser;

import java.net.MalformedURLException;
import java.util.ArrayList;


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
        String path = ctx.PATH().getText();
        if (ctx.imageStyle() != null) {
          visitImageStyle(ctx.imageStyle());
        }
        return null;
    }
    @Override
    public Void visitImageStyle(JQuickPDFParser.ImageStyleContext ctx) {
        for (JQuickPDFParser.ImageStyleItemContext item : ctx.imageStyleItem()) {
            visitImageStyleItem(item);
        }
        return null;
    }
    @Override
    public Void visitImageStyleItem(JQuickPDFParser.ImageStyleItemContext ctx) {
        if(ctx.dimension()!=null) {
            visit(ctx.dimension());
        }
        if(ctx.border()!=null) {
            visit(ctx.border());
        }
        if(ctx.opacity()!=null) {
            visit(ctx.opacity());
        }
        return null;
    }



    public static Image buildImage(){
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
