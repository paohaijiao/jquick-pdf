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
import com.github.paohaijiao.executor.JQuickPdfBorderExecutor;
import com.github.paohaijiao.executor.JQuickPdfColorExecutor;
import com.github.paohaijiao.executor.JQuickPdfMarginExecutor;
import com.github.paohaijiao.executor.JQuickPdfUnitExecutor;
import com.github.paohaijiao.model.*;
import com.github.paohaijiao.render.JStyleRenderer;
import com.github.paohaijiao.unit.JUnitConverter;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.xobject.PdfImageXObject;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.ElementPropertyContainer;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.BlockElement;
import com.itextpdf.layout.element.IElement;
import com.itextpdf.layout.properties.BackgroundImage;
import com.itextpdf.layout.properties.BorderRadius;
import com.itextpdf.layout.properties.UnitValue;

import java.lang.reflect.Constructor;
import java.util.Arrays;

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
        if(divStyles.getMargins() != null){//margins:'20px 30px 40px 50px'
            JQuickPdfMarginExecutor executor = new JQuickPdfMarginExecutor();
            JMarginModel marginModel= executor.execute(divStyles.getMargins());
            if(null!=marginModel){
                block.setMargins(JUnitConverter.toFloat(marginModel.getFirst()),JUnitConverter.toFloat(marginModel.getSecond()),JUnitConverter.toFloat(marginModel.getThird()),JUnitConverter.toFloat(marginModel.getFourth()));
            }
        }
        if(divStyles.getPaddingLeft() != null){//paddingLeft:50px
            JQuickPdfUnitExecutor executor = new JQuickPdfUnitExecutor();
            UnitValue unitValue= executor.execute(divStyles.getPaddingLeft());
            if(null!=unitValue){
                float f=JUnitConverter.toFloat(unitValue);
                block.setPaddingLeft(f);
            }
        }

        if(divStyles.getPaddingRight() != null){//paddingRight:50px
            JQuickPdfUnitExecutor executor = new JQuickPdfUnitExecutor();
            UnitValue unitValue= executor.execute(divStyles.getPaddingRight());
            if(null!=unitValue){
                float f=JUnitConverter.toFloat(unitValue);
                block.setPaddingRight(f);
            }

        }
        if(divStyles.getPaddingTop() != null){//paddingTop:50px
            JQuickPdfUnitExecutor executor = new JQuickPdfUnitExecutor();
            UnitValue unitValue= executor.execute(divStyles.getPaddingTop());
            if(null!=unitValue){
                float f=JUnitConverter.toFloat(unitValue);
                block.setPaddingTop(f);
            }
        }
        if(divStyles.getPaddingBottom() != null){//paddingBottom:50px
            JQuickPdfUnitExecutor executor = new JQuickPdfUnitExecutor();
            UnitValue unitValue= executor.execute(divStyles.getPaddingBottom());
            if(null!=unitValue){
                float f=JUnitConverter.toFloat(unitValue);
                block.setPaddingBottom(f);
            }

        }
        if(divStyles.getCommonPadding() != null){//commonPadding:50px
            JQuickPdfUnitExecutor executor = new JQuickPdfUnitExecutor();
            UnitValue unitValue= executor.execute(divStyles.getCommonPadding());
            if(null!=unitValue){
                float f=JUnitConverter.toFloat(unitValue);
                block.setPadding(f);
            }

        }
        if(divStyles.getPaddings() != null){//paddings:'50px 50px 60px 70px'
            JQuickPdfMarginExecutor executor = new JQuickPdfMarginExecutor();
            JMarginModel marginModel= executor.execute(divStyles.getPaddings());
            if(null!=marginModel){
                block.setPaddings(JUnitConverter.toFloat(marginModel.getFirst()),JUnitConverter.toFloat(marginModel.getSecond()),JUnitConverter.toFloat(marginModel.getThird()),JUnitConverter.toFloat(marginModel.getFourth()));
            }
        }
        if(divStyles.getVerticalAlignment() != null){//verticalAlignment:top
            JVerticalAlignment verticalAlignment=JVerticalAlignment.codeOf(divStyles.getVerticalAlignment());
            if(null!=verticalAlignment){
                block.setVerticalAlignment(verticalAlignment.getType());
            }
        }
        if(divStyles.getSpacingRatio() != null){//spacingRatio:30
            block.setSpacingRatio(Float.parseFloat(divStyles.getSpacingRatio()));
        }
        if(divStyles.getKeepTogether() != null){//keepTogether:true
            block.setKeepTogether(Boolean.parseBoolean(divStyles.getKeepTogether()));
        }
        if(divStyles.getKeepWithNext() != null){//keepWithNext:true
            block.setKeepWithNext(Boolean.parseBoolean(divStyles.getKeepWithNext()));
        }
        if(divStyles.getAngleInRadians() != null){//angleInRadians:30
            block.setRotationAngle(Float.parseFloat(divStyles.getAngleInRadians()));
        }
        if(divStyles.getWidth() != null){//width:300px
            JQuickPdfUnitExecutor executor = new JQuickPdfUnitExecutor();
            UnitValue unitValue= executor.execute(divStyles.getWidth());
            if(null!=unitValue){
                float f=JUnitConverter.toFloat(unitValue);
                block.setWidth(f);
            }
        }
        if(divStyles.getHeight() != null){//height:300px
            JQuickPdfUnitExecutor executor = new JQuickPdfUnitExecutor();
            UnitValue unitValue= executor.execute(divStyles.getHeight());
            if(null!=unitValue){
                float f=JUnitConverter.toFloat(unitValue);
                block.setHeight(f);
            }

        }
        if(divStyles.getMaxHeight() != null){//maxHeight:300px
            JQuickPdfUnitExecutor executor = new JQuickPdfUnitExecutor();
            UnitValue unitValue= executor.execute(divStyles.getMaxHeight());
            if(null!=unitValue){
                float f=JUnitConverter.toFloat(unitValue);
                block.setMaxHeight(f);
            }

        }
        if(divStyles.getMinHeight() != null){//minHeight:300px
            JQuickPdfUnitExecutor executor = new JQuickPdfUnitExecutor();
            UnitValue unitValue= executor.execute(divStyles.getMinHeight());
            if(null!=unitValue){
                float f=JUnitConverter.toFloat(unitValue);
                block.setMinHeight(f);
            }

        }
        if(divStyles.getMinWidth() != null){//minWidth:300px
            JQuickPdfUnitExecutor executor = new JQuickPdfUnitExecutor();
            UnitValue unitValue= executor.execute(divStyles.getMinWidth());
            if(null!=unitValue){
                float f=JUnitConverter.toFloat(unitValue);
                block.setMinWidth(f);
            }

        }
        if(divStyles.getMaxWidth() != null){//maxWidth:300px
            JQuickPdfUnitExecutor executor = new JQuickPdfUnitExecutor();
            UnitValue unitValue= executor.execute(divStyles.getMaxWidth());
            if(null!=unitValue){
                float f=JUnitConverter.toFloat(unitValue);
                block.setMaxWidth(f);
            }

        }
        this.applyElementProperty(doc, element, styles);
    }

    public void  applyElementProperty(Document doc, IElement element, JStyleAttributes styles){
        JStyleElementPropertyAttributes elementPropertyAttributes = new JStyleElementPropertyAttributes();
        elementPropertyAttributes.putAll(styles);
        ElementPropertyContainer<?> block = (ElementPropertyContainer<?>) element;
        if(null!=elementPropertyAttributes.getRelativePosition()){//relativePosition:'30px 30px 30px 30px'
            JQuickPdfMarginExecutor executor = new JQuickPdfMarginExecutor();
            JMarginModel marginModel= executor.execute(elementPropertyAttributes.getRelativePosition());
            if(null!=marginModel){
                block.setRelativePosition(JUnitConverter.toFloat(marginModel.getFirst()),JUnitConverter.toFloat(marginModel.getSecond()),JUnitConverter.toFloat(marginModel.getThird()),JUnitConverter.toFloat(marginModel.getFourth()));
            }
        }
        if(null!=elementPropertyAttributes.getFixedPosition()){
//            JQuickPdfMarginExecutor executor = new JQuickPdfMarginExecutor();
//            JMarginModel marginModel= executor.execute(elementPropertyAttributes.getFixedPosition());
//            block.setFixedPosition(0,0,0,0);
        }
        if(null!=elementPropertyAttributes.getFont()){//font:HELVETICA
            try {
                JFontEnum jFontEnum=JFontEnum.codeOf(elementPropertyAttributes.getFont());
                if(null!=jFontEnum){
                    PdfFont font = PdfFontFactory.createFont(jFontEnum.getFontName());
                    block.setFont(font);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        if(null!=elementPropertyAttributes.getFontFamilyNames()){//fontFamilyNames:Helvetica
            String[] array=elementPropertyAttributes.getFontFamilyNames().split(",");
            if(null!=array&&array.length>0){
                block.setFontFamily(Arrays.asList(array));
            }
        }
        if(null!=elementPropertyAttributes.getFontColor()){//fontColor:red
            JQuickPdfColorExecutor executor=new JQuickPdfColorExecutor();
            Color color=executor.execute(elementPropertyAttributes.getFontColor());
            if(null!=color){
                block.setFontColor(color);
            }
        }
        if(null!=elementPropertyAttributes.getOpacity()){//opacity:0.5
            block.setOpacity(Float.parseFloat(elementPropertyAttributes.getOpacity()));
        }
        if(null!=elementPropertyAttributes.getFontSize()){//fontSize:34
            block.setFontSize(Float.parseFloat(elementPropertyAttributes.getFontSize()));
        }
        if(null!=elementPropertyAttributes.getTextAlignment()){//textAlignment:left
            JTextAlignment jTextAlignme= JTextAlignment.codeOf(elementPropertyAttributes.getTextAlignment());
            if(null!=jTextAlignme){
                block.setTextAlignment(jTextAlignme.getType());
            }
        }
        if(null!=elementPropertyAttributes.getCharacterSpacing()){//characterSpacing:30
            block.setCharacterSpacing(Float.parseFloat(elementPropertyAttributes.getCharacterSpacing()));
        }
        if(null!=elementPropertyAttributes.getWordSpacing()){//wordSpacing:30
            block.setWordSpacing(Float.parseFloat(elementPropertyAttributes.getWordSpacing()));
        }
        if(null!=elementPropertyAttributes.getFontKerning()){//fontKerning:yes
            JFontKerning fontKerning= JFontKerning.codeOf(elementPropertyAttributes.getFontKerning());
            if(null!=fontKerning){
                block.setFontKerning(fontKerning.getType());
            }
        }
        if(null!=elementPropertyAttributes.getBackgroundColor()){//backgroundColor:red
            JQuickPdfColorExecutor executor=new JQuickPdfColorExecutor();
            Color color=executor.execute(elementPropertyAttributes.getBackgroundColor());
            if(color!=null){
                block.setBackgroundColor(color);
            }
        }
        if(null!=elementPropertyAttributes.getBackgroundImage()){//backgroundImage:'D:/pdf/image/封底-02.png' no effect
            try{
                ImageData imageData = ImageDataFactory.create(elementPropertyAttributes.getBackgroundImage());
                if(null!=imageData){
                    PdfImageXObject xObject = new PdfImageXObject(imageData);
                    BackgroundImage background = new BackgroundImage.Builder()
                            .setImage(xObject)
                            .build();
                    block.setBackgroundImage(background);
                }
            }catch (Exception e){
                e.printStackTrace();
            }



        }
        if(null!=elementPropertyAttributes.getBorder()){//border:'solid 32px red'
            Border border=buildBorder(elementPropertyAttributes.getBorder());
            if(null!=border){
                block.setBorder(border);
            }
        }

        if(null!=elementPropertyAttributes.getBorderTop()){//borderTop:'solid 32px red'
            Border border=buildBorder(elementPropertyAttributes.getBorderTop());
            if(null!=border){
                block.setBorderTop(border);
            }

        }
        if(null!=elementPropertyAttributes.getBorderRight()){//borderRight:'solid 32px red'
            Border border= buildBorder(elementPropertyAttributes.getBorderRight());
            if(null!=border){
                block.setBorderRight(border);
            }

        }
        if(null!=elementPropertyAttributes.getBorderLeft()){//borderLeft:'solid 32px red'
            Border border=buildBorder(elementPropertyAttributes.getBorderLeft());
            if(null!=border){
                block.setBorderLeft(border);
            }

        }
        if(null!=elementPropertyAttributes.getBorderBottom()){//borderBottom:'solid 32px red'
            Border border=buildBorder(elementPropertyAttributes.getBorderBottom());
            if(null!=border){
                block.setBorderBottom(border);
            }

        }
        if(null!=elementPropertyAttributes.getBorderRadius()){//borderRadius:'32px 24px'
            String[] array=elementPropertyAttributes.getBorderRadius().split(" ");
            if(null!=array&&2==array.length){
                JQuickPdfUnitExecutor executor=new JQuickPdfUnitExecutor();
                UnitValue first=executor.execute(array[0]);
                UnitValue second=executor.execute(array[1]);
                BorderRadius radius=new BorderRadius(first,second);
                block.setBorderRadius(radius);
            }
        }
        if(null!=elementPropertyAttributes.getBorderBottomLeftRadius()){//borderBottomLeftRadius:'32px 24px'
            String[] array=elementPropertyAttributes.getBorderBottomLeftRadius().split(" ");
            if(null!=array&&2==array.length){
                JQuickPdfUnitExecutor executor=new JQuickPdfUnitExecutor();
                UnitValue first=executor.execute(array[0]);
                UnitValue second=executor.execute(array[1]);
                BorderRadius radius=new BorderRadius(first,second);
                block.setBorderBottomLeftRadius(radius);
            }
        }
        if(null!=elementPropertyAttributes.getBorderBottomRightRadius()){//borderBottomRightRadius:'32px 24px'
            String[] array=elementPropertyAttributes.getBorderBottomRightRadius().split(" ");
            if(null!=array&&2==array.length){
                JQuickPdfUnitExecutor executor=new JQuickPdfUnitExecutor();
                UnitValue first=executor.execute(array[0]);
                UnitValue second=executor.execute(array[1]);
                BorderRadius radius=new BorderRadius(first,second);
                block.setBorderBottomRightRadius(radius);
            }

        }
        if(null!=elementPropertyAttributes.getBorderTopRightRadius()){//borderTopRightRadius:'32px 24px'
            String[] array=elementPropertyAttributes.getBorderTopRightRadius().split(" ");
            if(null!=array&&2==array.length){
                JQuickPdfUnitExecutor executor=new JQuickPdfUnitExecutor();
                UnitValue first=executor.execute(array[0]);
                UnitValue second=executor.execute(array[1]);
                BorderRadius radius=new BorderRadius(first,second);
                block.setBorderTopRightRadius(radius);
            }
        }
        if(null!=elementPropertyAttributes.getBorderTopLeftRadius()){//borderTopLeftRadius:'32px 24px'
            String[] array=elementPropertyAttributes.getBorderTopLeftRadius().split(" ");
            if(null!=array&&2==array.length){
                JQuickPdfUnitExecutor executor=new JQuickPdfUnitExecutor();
                UnitValue first=executor.execute(array[0]);
                UnitValue second=executor.execute(array[1]);
                BorderRadius radius=new BorderRadius(first,second);
                block.setBorderTopLeftRadius(radius);
            }
        }
        if(null!=elementPropertyAttributes.getSplitCharacters()){//splitCharacters:24
            block.setCharacterSpacing(Float.parseFloat(elementPropertyAttributes.getSplitCharacters()));
        }
        if(null!=elementPropertyAttributes.getTextRenderingMode()){//textRenderingMode:24textRenderingMode:24
            block.setTextRenderingMode(Integer.parseInt(elementPropertyAttributes.getTextRenderingMode()));
        }
        if(null!=elementPropertyAttributes.getStrokeColor()){//strokeColor:red
            JQuickPdfColorExecutor executor=new JQuickPdfColorExecutor();
            Color color=executor.execute(elementPropertyAttributes.getStrokeColor());
            block.setStrokeColor(color);
        }

        if(null!=elementPropertyAttributes.getStrokeWidth()){//strokeWidth:24
            block.setStrokeWidth(Float.parseFloat(elementPropertyAttributes.getStrokeWidth()));
        }
        if(null!=elementPropertyAttributes.getBold()){//bold:true
            if(Boolean.parseBoolean(elementPropertyAttributes.getBold())){
                block.setBold();
            }
        }
        if(null!=elementPropertyAttributes.getItalic()){//italic:true
            if(Boolean.parseBoolean(elementPropertyAttributes.getItalic())){
                block.setItalic();
            }
        }

        if(null!=elementPropertyAttributes.getLineThrough()){//lineThrough:true
            if(Boolean.parseBoolean(elementPropertyAttributes.getLineThrough())){
                block.setLineThrough();
            }
        }
        if(null!=elementPropertyAttributes.getUnderline()){//underline:true
            if(Boolean.parseBoolean(elementPropertyAttributes.getUnderline())){
                block.setUnderline();
            }
        }
        if(null!=elementPropertyAttributes.getBaseDirection()){//baseDirection:'no_bidi'
             JBaseDirection jBaseDirection= JBaseDirection.codeOf(elementPropertyAttributes.getBaseDirection());
                if(null!=jBaseDirection){
                    block.setBaseDirection(jBaseDirection.getType());
                }

        }
        if(null!=elementPropertyAttributes.getHyphenation()){
//            block.setHyphenation()
        }
        if(null!=elementPropertyAttributes.getFontScript()){//fontScript:common
            JUnicodeScript script=JUnicodeScript.codeOf(elementPropertyAttributes.getFontScript());
            if(null!=script){
                block.setFontScript(script.getType());
            }

        }
        if(null!=elementPropertyAttributes.getDestination()){//destination:hello
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
                    constructor = borderClass.getConstructor( Color.class,float.class);
                    borderInstance = constructor.newInstance(color,width);
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
