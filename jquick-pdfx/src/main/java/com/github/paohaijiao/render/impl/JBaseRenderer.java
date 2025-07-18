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

import com.github.paohaijiao.enums.*;
import com.github.paohaijiao.executor.*;
import com.github.paohaijiao.model.*;
import com.github.paohaijiao.render.JStyleRenderer;
import com.github.paohaijiao.sample.ReportColor;
import com.github.paohaijiao.unit.JUnitConverter;
import com.itextpdf.kernel.colors.Color;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.ElementPropertyContainer;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.AreaBreak;
import com.itextpdf.layout.element.BlockElement;
import com.itextpdf.layout.element.IElement;
import com.itextpdf.layout.properties.AreaBreakType;
import com.itextpdf.layout.properties.BorderRadius;
import com.itextpdf.layout.properties.UnitValue;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.List;

/**
 * packageName com.github.paohaijiao.render.impl
 *
 * @author Martin
 * @version 1.0.0
 * @className JBaseRenderer
 * @date 2025/6/27
 * @description
 */
public abstract class JBaseRenderer implements JStyleRenderer {

    public abstract void applyStyles(Document doc, IElement element, JStyleAttributes styles);


    public void  applyBlockElement(Document doc, IElement element, JStyleAttributes styles){
        JStyleBlockAttributes divStyles = new JStyleBlockAttributes();
        divStyles.putAll(styles);
        BlockElement<?> block = (BlockElement<?>) element;
        if(divStyles.getMarginLeft() != null){//marginLeft:1px
            JQuickPdfUnitExecutor executor = new JQuickPdfUnitExecutor();
            UnitValue unitValue= executor.execute(divStyles.getMarginLeft());
            if(unitValue != null){
                float f=JUnitConverter.toFloat(unitValue);
                block.setMarginLeft(f);
            }
        }
        if(divStyles.getMarginRight() != null){//marginRight:500px
            JQuickPdfUnitExecutor executor = new JQuickPdfUnitExecutor();
            UnitValue unitValue= executor.execute(divStyles.getMarginRight());
            if(unitValue != null){
                float f=JUnitConverter.toFloat(unitValue);
                block.setMarginRight(f);
            }
        }
        if(divStyles.getMarginTop() != null){//marginTop:500px
            JQuickPdfUnitExecutor executor = new JQuickPdfUnitExecutor();
            UnitValue unitValue= executor.execute(divStyles.getMarginTop());
            if(unitValue != null){
                float f=JUnitConverter.toFloat(unitValue);
                block.setMarginTop(f);
            }
        }
        if(divStyles.getMarginBottom() != null){//marginBottom:500px
            JQuickPdfUnitExecutor executor = new JQuickPdfUnitExecutor();
            UnitValue unitValue= executor.execute(divStyles.getMarginBottom());
            if(unitValue != null){
                float f=JUnitConverter.toFloat(unitValue);
                block.setMarginBottom(f);
            }
        }
        if(divStyles.getCommonMargin() != null){//commonMargin:100px
            JQuickPdfUnitExecutor executor = new JQuickPdfUnitExecutor();
            UnitValue unitValue= executor.execute(divStyles.getCommonMargin());
            if(unitValue != null){
                float f=JUnitConverter.toFloat(unitValue);
                block.setMargin(f);
            }

        }
        if(divStyles.getMargins() != null){//margins:20px 30px 40px 50px
            JQuickPdfMarginExecutor executor = new JQuickPdfMarginExecutor();
            JMarginModel marginModel= executor.execute(divStyles.getMargins());
            if(null!=marginModel){
                block.setMargins(JUnitConverter.toFloat(marginModel.getFirst()),JUnitConverter.toFloat(marginModel.getSecond()),JUnitConverter.toFloat(marginModel.getThird()),JUnitConverter.toFloat(marginModel.getFourth()));
            }
        }
        if(divStyles.getPaddingLeft() != null){
            JQuickPdfUnitExecutor executor = new JQuickPdfUnitExecutor();
            UnitValue unitValue= executor.execute(divStyles.getPaddingLeft());
            float f=JUnitConverter.toFloat(unitValue);
            block.setPaddingLeft(f);
        }

        if(divStyles.getPaddingRight() != null){
            JQuickPdfUnitExecutor executor = new JQuickPdfUnitExecutor();
            UnitValue unitValue= executor.execute(divStyles.getPaddingRight());
            float f=JUnitConverter.toFloat(unitValue);
            block.setPaddingRight(f);
        }
        if(divStyles.getPaddingTop() != null){
            JQuickPdfUnitExecutor executor = new JQuickPdfUnitExecutor();
            UnitValue unitValue= executor.execute(divStyles.getPaddingTop());
            float f=JUnitConverter.toFloat(unitValue);
            block.setPaddingTop(f);
        }
        if(divStyles.getPaddingBottom() != null){
            JQuickPdfUnitExecutor executor = new JQuickPdfUnitExecutor();
            UnitValue unitValue= executor.execute(divStyles.getPaddingBottom());
            float f=JUnitConverter.toFloat(unitValue);
            block.setPaddingBottom(f);
        }
        if(divStyles.getCommonPadding() != null){
            JQuickPdfUnitExecutor executor = new JQuickPdfUnitExecutor();
            UnitValue unitValue= executor.execute(divStyles.getCommonPadding());
            float f=JUnitConverter.toFloat(unitValue);
            block.setPadding(f);
        }
        if(divStyles.getPaddings() != null){
            JQuickPdfMarginExecutor executor = new JQuickPdfMarginExecutor();
            JMarginModel marginModel= executor.execute(divStyles.getPaddings());
            block.setPaddings(JUnitConverter.toFloat(marginModel.getFirst()),JUnitConverter.toFloat(marginModel.getSecond()),JUnitConverter.toFloat(marginModel.getThird()),JUnitConverter.toFloat(marginModel.getFourth()));
        }
        if(divStyles.getVerticalAlignment() != null){
            JVerticalAlignment verticalAlignment=JVerticalAlignment.codeOf(divStyles.getVerticalAlignment());
            block.setVerticalAlignment(verticalAlignment.getType());
        }
        if(divStyles.getSpacingRatio() != null){
            block.setSpacingRatio(Float.parseFloat(divStyles.getSpacingRatio()));
        }
        if(divStyles.getKeepTogether() != null){
            block.setKeepTogether(Boolean.parseBoolean(divStyles.getKeepTogether()));
        }
        if(divStyles.getKeepWithNext() != null){
            block.setKeepWithNext(Boolean.parseBoolean(divStyles.getKeepWithNext()));
        }
        if(divStyles.getAngleInRadians() != null){
            block.setRotationAngle(Float.parseFloat(divStyles.getAngleInRadians()));
        }
        if(divStyles.getWidth() != null){
            JQuickPdfUnitExecutor executor = new JQuickPdfUnitExecutor();
            UnitValue unitValue= executor.execute(divStyles.getWidth());
            float f=JUnitConverter.toFloat(unitValue);
            block.setWidth(f);
        }
        if(divStyles.getHeight() != null){
            JQuickPdfUnitExecutor executor = new JQuickPdfUnitExecutor();
            UnitValue unitValue= executor.execute(divStyles.getHeight());
            float f=JUnitConverter.toFloat(unitValue);
            block.setHeight(f);
        }
        if(divStyles.getMaxHeight() != null){
            JQuickPdfUnitExecutor executor = new JQuickPdfUnitExecutor();
            UnitValue unitValue= executor.execute(divStyles.getMaxHeight());
            float f=JUnitConverter.toFloat(unitValue);
            block.setMaxHeight(f);
        }
        if(divStyles.getMinHeight() != null){
            JQuickPdfUnitExecutor executor = new JQuickPdfUnitExecutor();
            UnitValue unitValue= executor.execute(divStyles.getMinHeight());
            float f=JUnitConverter.toFloat(unitValue);
            block.setMinHeight(f);
        }
        if(divStyles.getMinWidth() != null){
            JQuickPdfUnitExecutor executor = new JQuickPdfUnitExecutor();
            UnitValue unitValue= executor.execute(divStyles.getMinWidth());
            float f=JUnitConverter.toFloat(unitValue);
            block.setMinWidth(f);
        }
        if(divStyles.getMaxWidth() != null){
            JQuickPdfUnitExecutor executor = new JQuickPdfUnitExecutor();
            UnitValue unitValue= executor.execute(divStyles.getMaxWidth());
            float f=JUnitConverter.toFloat(unitValue);
            block.setMaxWidth(f);
        }
        this.applyElementProperty(doc, element, styles);
    }

    public void  applyElementProperty(Document doc, IElement element, JStyleAttributes styles){
        JStyleElementPropertyAttributes elementPropertyAttributes = new JStyleElementPropertyAttributes();
        elementPropertyAttributes.putAll(styles);
        ElementPropertyContainer<?> block = (ElementPropertyContainer<?>) element;
        if(null!=elementPropertyAttributes.getRelativePosition()){
            JQuickPdfMarginExecutor executor = new JQuickPdfMarginExecutor();
            JMarginModel marginModel= executor.execute(elementPropertyAttributes.getRelativePosition());
            block.setRelativePosition(JUnitConverter.toFloat(marginModel.getFirst()),JUnitConverter.toFloat(marginModel.getSecond()),JUnitConverter.toFloat(marginModel.getThird()),JUnitConverter.toFloat(marginModel.getFourth()));
        }
        if(null!=elementPropertyAttributes.getFixedPosition()){
//            JQuickPdfMarginExecutor executor = new JQuickPdfMarginExecutor();
//            JMarginModel marginModel= executor.execute(elementPropertyAttributes.getFixedPosition());
//            block.setFixedPosition(JUnitConverter.toFloat(marginModel.getFirst()),JUnitConverter.toFloat(marginModel.getSecond()),JUnitConverter.toFloat(marginModel.getThird()),JUnitConverter.toFloat(marginModel.getFourth()));
//            block.setFixedPosition(0,0,0,0);
        }
        if(null!=elementPropertyAttributes.getFont()){
            //block.setFont(null);
        }
        if(null!=elementPropertyAttributes.getFontFamilyNames()){
            String[] array=elementPropertyAttributes.getFontFamilyNames().split(",");
            block.setFontFamily(Arrays.asList(array));
        }
        if(null!=elementPropertyAttributes.getFontColor()){
            JQuickPdfColorExecutor executor=new JQuickPdfColorExecutor();
            Color color=executor.execute(elementPropertyAttributes.getFontColor());
            block.setFontColor(color);
        }
        if(null!=elementPropertyAttributes.getOpacity()){
            block.setOpacity(Float.parseFloat(elementPropertyAttributes.getOpacity()));
        }
        if(null!=elementPropertyAttributes.getFontSize()){
            block.setFontSize(Float.parseFloat(elementPropertyAttributes.getFontSize()));
        }
        if(null!=elementPropertyAttributes.getTextAlignment()){
            JTextAlignment jTextAlignme= JTextAlignment.codeOf(elementPropertyAttributes.getTextAlignment());
            block.setTextAlignment(jTextAlignme.getType());
        }
        if(null!=elementPropertyAttributes.getCharacterSpacing()){
            block.setCharacterSpacing(Float.parseFloat(elementPropertyAttributes.getCharacterSpacing()));
        }
        if(null!=elementPropertyAttributes.getWordSpacing()){
            block.setWordSpacing(Float.parseFloat(elementPropertyAttributes.getWordSpacing()));
        }
        if(null!=elementPropertyAttributes.getWordSpacing()){
            block.setWordSpacing(Float.parseFloat(elementPropertyAttributes.getWordSpacing()));
        }
        if(null!=elementPropertyAttributes.getFontKerning()){
            JFontKerning fontKerning= JFontKerning.codeOf(elementPropertyAttributes.getFontKerning());
            block.setFontKerning(fontKerning.getType());
        }
        if(null!=elementPropertyAttributes.getBackgroundColor()){
            JQuickPdfColorExecutor executor=new JQuickPdfColorExecutor();
            Color color=executor.execute(elementPropertyAttributes.getBackgroundColor());
            block.setBackgroundColor(color);
        }
        if(null!=elementPropertyAttributes.getBackgroundImage()){
            //block.setBackgroundImage(null);
        }
        if(null!=elementPropertyAttributes.getBorder()){
            block.setBorder(buildBorder(elementPropertyAttributes.getBorder()));
        }

        if(null!=elementPropertyAttributes.getBorderTop()){
            block.setBorderTop(buildBorder(elementPropertyAttributes.getBorderTop()));
        }
        if(null!=elementPropertyAttributes.getBorderRight()){
            block.setBorderRight(buildBorder(elementPropertyAttributes.getBorderRight()));
        }
        if(null!=elementPropertyAttributes.getBorderLeft()){
            block.setBorderLeft(buildBorder(elementPropertyAttributes.getBorderLeft()));
        }
        if(null!=elementPropertyAttributes.getBorderBottom()){
            block.setBorderBottom(buildBorder(elementPropertyAttributes.getBorderBottom()));
        }
        if(null!=elementPropertyAttributes.getBorderRadius()){
            String[] array=elementPropertyAttributes.getBorderRadius().split(" ");
            if(null!=array&&2==array.length){
                JQuickPdfUnitExecutor executor=new JQuickPdfUnitExecutor();
                UnitValue first=executor.execute(array[0]);
                UnitValue second=executor.execute(array[1]);
                BorderRadius radius=new BorderRadius(first,second);
                block.setBorderRadius(radius);
            }
        }
        if(null!=elementPropertyAttributes.getBorderBottomLeftRadius()){
            String[] array=elementPropertyAttributes.getBorderBottomLeftRadius().split(" ");
            if(null!=array&&2==array.length){
                JQuickPdfUnitExecutor executor=new JQuickPdfUnitExecutor();
                UnitValue first=executor.execute(array[0]);
                UnitValue second=executor.execute(array[1]);
                BorderRadius radius=new BorderRadius(first,second);
                block.setBorderBottomLeftRadius(radius);
            }
        }
        if(null!=elementPropertyAttributes.getBorderBottomRightRadius()){
            String[] array=elementPropertyAttributes.getBorderBottomRightRadius().split(" ");
            if(null!=array&&2==array.length){
                JQuickPdfUnitExecutor executor=new JQuickPdfUnitExecutor();
                UnitValue first=executor.execute(array[0]);
                UnitValue second=executor.execute(array[1]);
                BorderRadius radius=new BorderRadius(first,second);
                block.setBorderBottomRightRadius(radius);
            }

        }
        if(null!=elementPropertyAttributes.getBorderTopRightRadius()){
            String[] array=elementPropertyAttributes.getBorderTopRightRadius().split(" ");
            if(null!=array&&2==array.length){
                JQuickPdfUnitExecutor executor=new JQuickPdfUnitExecutor();
                UnitValue first=executor.execute(array[0]);
                UnitValue second=executor.execute(array[1]);
                BorderRadius radius=new BorderRadius(first,second);
                block.setBorderTopRightRadius(radius);
            }
        }
        if(null!=elementPropertyAttributes.getBorderTopLeftRadius()){
            String[] array=elementPropertyAttributes.getBorderTopLeftRadius().split(" ");
            if(null!=array&&2==array.length){
                JQuickPdfUnitExecutor executor=new JQuickPdfUnitExecutor();
                UnitValue first=executor.execute(array[0]);
                UnitValue second=executor.execute(array[1]);
                BorderRadius radius=new BorderRadius(first,second);
                block.setBorderTopLeftRadius(radius);
            }
        }
        if(null!=elementPropertyAttributes.getSplitCharacters()){
         block.setCharacterSpacing(Float.parseFloat(elementPropertyAttributes.getSplitCharacters()));
        }
        if(null!=elementPropertyAttributes.getTextRenderingMode()){
            block.setTextRenderingMode(Integer.parseInt(elementPropertyAttributes.getTextRenderingMode()));
        }
        if(null!=elementPropertyAttributes.getStrokeColor()){
            JQuickPdfColorExecutor executor=new JQuickPdfColorExecutor();
            Color color=executor.execute(elementPropertyAttributes.getStrokeColor());
            block.setStrokeColor(color);
        }

        if(null!=elementPropertyAttributes.getStrokeWidth()){
            block.setStrokeWidth(Float.parseFloat(elementPropertyAttributes.getStrokeWidth()));
        }
        if(null!=elementPropertyAttributes.getBold()){
            if(Boolean.parseBoolean(elementPropertyAttributes.getBold())){
                block.setBold();
            }
        }
        if(null!=elementPropertyAttributes.getItalic()){
            if(Boolean.parseBoolean(elementPropertyAttributes.getItalic())){
                block.setItalic();
            }
        }

        if(null!=elementPropertyAttributes.getLineThrough()){
            if(Boolean.parseBoolean(elementPropertyAttributes.getLineThrough())){
                block.setLineThrough();
            }
        }
        if(null!=elementPropertyAttributes.getUnderline()){
            if(Boolean.parseBoolean(elementPropertyAttributes.getUnderline())){
                block.setUnderline();
            }
        }
        if(null!=elementPropertyAttributes.getBaseDirection()){
            if(Boolean.parseBoolean(elementPropertyAttributes.getUnderline())){
                JBaseDirection jBaseDirection= JBaseDirection.codeOf(elementPropertyAttributes.getBaseDirection());
                if(null!=jBaseDirection){
                    block.setBaseDirection(jBaseDirection.getType());
                }
            }
        }
        if(null!=elementPropertyAttributes.getHyphenation()){
//            block.setHyphenation()
        }
        if(null!=elementPropertyAttributes.getFontScript()){
            JUnicodeScript script=JUnicodeScript.codeOf(elementPropertyAttributes.getFontScript());
            if(null!=script){
                block.setFontScript(script.getType());
            }

        }
        if(null!=elementPropertyAttributes.getDestination()){
            block.setDestination(elementPropertyAttributes.getDestination());
        }
    }
    private Border buildBorder(String border){
        JBorderModel borderModel = getBorder(border);
        if(null!=borderModel&&null!=borderModel.getBorder()){
            JBorder jBorder = borderModel.getBorder();
            Float width = borderModel.getWidth();
            Color color = borderModel.getColor();
            try {
                Class<?> borderClass = jBorder.getClazz();
                Constructor<?> constructor;
                Object borderInstance;
                if (width != null && color != null) {
                    constructor = borderClass.getConstructor(float.class, Color.class);
                    borderInstance = constructor.newInstance(width, color);
                } else if (width != null) {
                    constructor = borderClass.getConstructor(float.class);
                    borderInstance = constructor.newInstance(width);
                } else {
                    constructor = borderClass.getConstructor();
                    borderInstance = constructor.newInstance();
                }
                return (Border) borderInstance;
            } catch (Exception e) {
                throw new RuntimeException("Failed to create border instance for: " + jBorder.name(), e);
            }
        }

        return null;

    }
    private JBorderModel getBorder(String border){
        String[] array=border.split(" ");
        JBorderModel model=new JBorderModel();
        if (null!=array&&array.length>0){
            for (int i = 0; i < array.length; i++) {
                String content=array[i];
                JQuickPdfBorderExecutor borderExecutor=new JQuickPdfBorderExecutor();
                JBorder jBorder=borderExecutor.execute(content);
                if(null!=jBorder){
                    model.setBorder(jBorder);
                }
                JQuickPdfColorExecutor colorExecutor=new JQuickPdfColorExecutor();
                Color color=colorExecutor.execute(content);
                if(null!=color){
                    model.setColor(color);
                }
                JQuickPdfUnitExecutor unitExecutor=new JQuickPdfUnitExecutor();
                UnitValue unit=unitExecutor.execute(content);
                if(null!=unit){
                    model.setWidth(unit.getValue());
                }
            }

        }
        return model;
    }






}
