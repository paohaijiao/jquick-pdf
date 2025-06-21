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

import com.paohaijiao.javelin.parser.JQuickPDFParser;

/**
 * packageName com.paohaijiao.javelin.visitor
 *
 * @author Martin
 * @version 1.0.0
 * @className JPdfXCommonVisitor
 * @date 2025/6/14
 * @description
 */
public class JPdfXSvgVisitor extends JPdfXListVisitor {


//    @Override
//    public T visitSvg(JQuickPDFParser.SvgContext ctx) {
//        String path = ctx.PATH().getText();
//        SvgConverter.convertToPdf(new File("input.svg"), new PdfWriter("output.pdf"));
//        String svg = "<svg xmlns='http://www.w3.org/2000/svg' width='100' height='100'>"
//                + "<circle cx='50' cy='50' r='40' stroke='black' stroke-width='3' fill='red'/>"
//                + "</svg>";
//        SvgConverter.convertToPdf(svg, new PdfWriter("output.pdf"));
//        ISvgConverterProperties properties = new SvgConverterProperties()
//                .setBaseUri("") // 设置基础 URI（如果需要引用外部资源）
//                .setCharset("UTF-8"); // 设置字符集
//
//// 从文件添加 SVG
//        SvgConverter.drawOnDocument(new FileInputStream("input.svg"), document, properties);
//
//// 从字符串添加 SVG
//        String svgString = "<svg>...</svg>";
//        SvgConverter.drawOnDocument(svgString, document, properties);
//
//    }
//
//    public static Image buildImage(){
//        Image image = null;
//        try {
//            image = new Image(ImageDataFactory.create("path/to/image.jpg"));
//        } catch (MalformedURLException e) {
//            throw new RuntimeException(e);
//        }
//        image.setWidth(200); // 宽度
//        image.setHeight(100); // 高度
//        image.setAutoScale(true); // 自动缩放
//        image.setRotationAngle(Math.PI / 4); // 旋转45度
//        image.setOpacity(0.8f); // 透明度
//       // image.setBorder(new SolidBorder(Color.BLUE, 2)); // 边框
//        image.setHorizontalAlignment(HorizontalAlignment.CENTER); // 对齐方式
//        image.setMargins(10, 0, 10, 0); // 边距
//        return image;
//    }

}
