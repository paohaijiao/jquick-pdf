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
package com.github.paohaijiao.render.impl;

import com.github.paohaijiao.enums.JFrontStyle;
import com.github.paohaijiao.enums.JFrontWeight;
import com.github.paohaijiao.factory.JFontProviderFactory;
import com.github.paohaijiao.font.JFontProvider;
import com.github.paohaijiao.model.JStyleAttributes;
import com.github.paohaijiao.string.JStringUtils;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.layout.element.BlockElement;
import com.itextpdf.layout.element.IElement;
import com.itextpdf.layout.element.Text;

import java.io.IOException;

/**
 * packageName com.github.paohaijiao.render.impl
 *
 * @author Martin
 * @version 1.0.0
 * @className JFontRenderer
 * @date 2025/6/29
 * @description
 */
public class JFontRenderer {
    protected PdfFont getFont(String fontFamily) {
        JFontProviderFactory factory = new JFontProviderFactory();
        factory.registerResourceFont("fonts/simhei.ttf", JFontProviderFactory.DEFAULT_FONT);
        try {
            JFontProvider fontProvider = factory.getFontProvider(fontFamily);
            PdfFont font = fontProvider.fontProvider();
            return font;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    protected void applyFontStyles(IElement element, JStyleAttributes styles) {
        if (styles.getFontFamily() != null) {
            PdfFont font = this.getFont(styles.getFontFamily());
            if (null != font) {
                ((BlockElement<?>) element).setFont(font);
            }
        } else {
            PdfFont font = null;
            try {//process chinese charater
                font = PdfFontFactory.createFont("STSong-Light", "UniGB-UCS2-H", PdfFontFactory.EmbeddingStrategy.PREFER_EMBEDDED);
                if (null != font) {
                    ((BlockElement<?>) element).setFont(font);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        if (styles.getFontStyle() != null && JFrontStyle.Italic.getCode().equals(styles.getFontStyle())) {
            ((BlockElement<?>) element).setItalic();
        }
        if (styles.getFontStyle() != null && JFrontStyle.Underline.getCode().equals(styles.getFontStyle())) {
            ((BlockElement<?>) element).setUnderline();
        }
        if (styles.getFontWeight() != null && JFrontWeight.Bold.getCode().equals(styles.getFontWeight())) {
            ((BlockElement<?>) element).setBold();
        }
        if (styles.getFontSize() != null) {
            String fontSize=styles.getFontSize();
            ((BlockElement<?>) element).setFontSize(Float.parseFloat(fontSize));
        }
        if (styles.getLetterSpacing() != null) {
            try {
                float spacing = Float.parseFloat(styles.getLetterSpacing());
                ((BlockElement<?>) element).setCharacterSpacing(spacing);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        if (styles.getLetterSpacing() != null) {
            ((BlockElement<?>) element).setCharacterSpacing(Float.parseFloat(styles.getLetterSpacing()));
        }
//        if (styles.getTextDecoration() != null) {
//            if ("underline".equalsIgnoreCase(styles.getTextDecoration())) {
//                ((BlockElement<?>) element).setUnderline();
//            } else if ("line-through".equalsIgnoreCase(styles.getTextDecoration())) {
//                ((BlockElement<?>) element).setLineThrough();
//            }
//        }
//        if (styles.getTextTransform() != null) {
//            setTransformedText(element, styles);
//        }
        if (styles.getColor() != null) {
            try {
                com.itextpdf.kernel.colors.Color color = com.itextpdf.kernel.colors.Color.convertRgbToCmyk(
                        new com.itextpdf.kernel.colors.DeviceRgb(
                                Integer.parseInt(styles.getColor().substring(1, 3), 16),
                                Integer.parseInt(styles.getColor().substring(3, 5), 16),
                                Integer.parseInt(styles.getColor().substring(5, 7), 16)
                        )
                );
                ((BlockElement<?>) element).setFontColor(color);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (styles.getTextAlign() != null) {
            switch (styles.getTextAlign().toLowerCase()) {
                case "left":
                    ((BlockElement<?>) element).setTextAlignment(com.itextpdf.layout.properties.TextAlignment.LEFT);
                    break;
                case "right":
                    ((BlockElement<?>) element).setTextAlignment(com.itextpdf.layout.properties.TextAlignment.RIGHT);
                    break;
                case "center":
                    ((BlockElement<?>) element).setTextAlignment(com.itextpdf.layout.properties.TextAlignment.CENTER);
                    break;
                case "justify":
                    ((BlockElement<?>) element).setTextAlignment(com.itextpdf.layout.properties.TextAlignment.JUSTIFIED);
                    break;
            }
        }
//        if (styles.getTextShadow() != null) {
//            // iText doesn't directly support text shadow, you might need to implement this with custom logic
//            // For example, by drawing the text multiple times with offsets
//        }
    }

    protected String transform(String text, String textTransform) {
        if (text == null || textTransform == null) {
            return text;
        }
        switch (textTransform.toLowerCase()) {
            case "uppercase":
                return text.toUpperCase();
            case "lowercase":
                return text.toLowerCase();
            case "capitalize":
                return JStringUtils.capitalize(text);
            default: // "none"
                return text;
        }
    }

    protected void setTransformedText(IElement element, JStyleAttributes styles) {
        if (element == null) {
            return;
        }
        if (element instanceof Text) {
            Text textEle = ((Text) element);
            String text = textEle.getText();
            //String transformedText = transform(text, styles.getTextTransform());
         //   textEle.setText(transformedText);
        }
//        else if (element instanceof Paragraph) {
//            Paragraph p = (Paragraph) element;
//            p.geT
//            p.getChildren().clear();
//            p.add(transformedText);
//        } else if (element instanceof Div) {
//            Div div = (Div) element;
//            div.getChildren().clear();
//            div.add(new Paragraph(transformedText));
//        }
    }
}
