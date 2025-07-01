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
package com.github.paohaijiao.model.provider;

import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.List;


/**
 * packageName com.github.paohaijiao.model.provider
 *
 * @author Martin
 * @version 1.0.0
 * @className JCSSPropertiesAProvider
 * @date 2025/7/1
 * @description
 */
public class JCSSPropertiesBaseProvider {

       protected static Color parseColor(String color) {
//           Color.makeColor()
//           return Color.convertCmykToRgb(DeviceRgb.BLACK);
           return    null;
       }
      protected static Border parseBorder(String borderValue) {
        Border solidBorder = new SolidBorder(ColorConstants.BLACK, 1f);
        return solidBorder;
    }

        protected static void addElement(BlockElement<?> container, IElement element) {
            if (container == null || element == null) {
                throw new IllegalArgumentException("containers and elements cannot be null");
            }

            if (container instanceof Div) {
                addToDiv((Div) container, element);
                return;
            }
            if (container instanceof Paragraph) {
                addToParagraph((Paragraph) container, element);
                return;
            }
            if (container instanceof List) {
                addToList((List) container, element);
                return;
            }
            if (container instanceof Cell) {
                addToCell((Cell) container, element);
                return;
            }

            throw new IllegalArgumentException("unsupported container type: " + container.getClass().getSimpleName());
        }
        private static void addToDiv(Div div, IElement element) {
            if (element instanceof IBlockElement) {
                div.add((IBlockElement) element);
            } else if (element instanceof Image) {
                div.add((Image) element);
            } else {
                throw new IllegalArgumentException("div does not support adding" + element.getClass().getSimpleName() + "类型元素");
            }
        }

        private static void addToParagraph(Paragraph paragraph, IElement element) {
            if (element instanceof Text) {
                paragraph.add((Text) element);
            } else if (element instanceof Image) {
                paragraph.add((Image) element);
            } else if (element instanceof Tab) {
                paragraph.add((Tab) element);
            } else {
                throw new IllegalArgumentException("paragraph can only add text, image, or tab type elements");
            }
        }

        private static void addToList(List list, IElement element) {
            if (element instanceof ListItem) {
                list.add((ListItem) element);
            } else if (element instanceof IBlockElement) {
                if (element instanceof Div) {
                    Div div = (Div) element;
                    ListItem listItem = new ListItem();
                    for (IElement child : div.getChildren()) {
                        if (child instanceof IBlockElement) {
                            listItem.add((IBlockElement) child);
                        }
                    }
                    list.add(listItem);
                }
            } else {
                throw new IllegalArgumentException("list can only add listItem or BlockElement type elements");
            }
        }

        private static void addToCell(Cell cell, IElement element) {
            if (element instanceof IBlockElement) {
                cell.add((IBlockElement) element);
            } else if (element instanceof Image) {
                cell.add((Image) element);
            } else {
                throw new IllegalArgumentException("cell does not support adding" + element.getClass().getSimpleName() + "类型元素");
            }
        }

        public static void addStyledParagraph(BlockElement<?> container, String text,
                                              float fontSize, DeviceRgb color, float marginLeft) {
            Paragraph p = new Paragraph(text)
                    .setFontSize(fontSize)
                    .setFontColor(color)
                    .setMarginLeft(marginLeft);
            addElement(container, p);
        }
}
