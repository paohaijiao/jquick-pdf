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

import com.paohaijiao.javelin.enums.JAlign;
import com.paohaijiao.javelin.enums.JUnit;
import com.paohaijiao.javelin.model.JNumberUnitModel;
import com.paohaijiao.javelin.model.style.JStyleAlignModel;
import com.paohaijiao.javelin.model.style.JStyleModel;
import com.paohaijiao.javelin.model.style.JStyleSpacingModel;
import com.paohaijiao.javelin.parser.JQuickPDFParser;

import java.math.BigDecimal;

/**
 * packageName com.paohaijiao.javelin.visitor
 *
 * @author Martin
 * @version 1.0.0
 * @className JTextStyleAndAlignmentAndSpacingVisitor
 * @date 2025/6/14
 * @description
 */
public class JTextStyleAndAlignmentAndSpacingVisitor extends JPdfXLayOutVisitor{
    @Override
    public JStyleModel visitTextStylefont(JQuickPDFParser.TextStylefontContext ctx) {
        String font=visitString(ctx.string());
        style.setFont(font);
        return style;
    }
    @Override
    public JStyleModel visitTextStyleSize(JQuickPDFParser.TextStyleSizeContext ctx) {
        BigDecimal number=visitNumber(ctx.number());
        JUnit unit=visitUnit(ctx.unit());
        JNumberUnitModel numberUnitModel=new JNumberUnitModel();
        numberUnitModel.setNumber(number.intValue());
        numberUnitModel.setUnit(unit);
        style.setNumberUnit(numberUnitModel);
        return style;
    }
    @Override
    public JStyleModel visitTextStyleColor(JQuickPDFParser.TextStyleColorContext ctx) {
        String color=visitColor(ctx.color());
        style.setColor(color);
        return style;
    }
    @Override
    public JStyleModel visitTextStyleBold(JQuickPDFParser.TextStyleBoldContext ctx) {
        style.setBold(Boolean.TRUE);
        return style;
    }
    @Override
    public JStyleModel visitTextStyleItalic(JQuickPDFParser.TextStyleItalicContext ctx) {
        style.setItalic(Boolean.TRUE);
        return style;
    }
    @Override
    public JStyleModel visitTextStyleUnderline(JQuickPDFParser.TextStyleUnderlineContext ctx) {
        style.setUnderline(Boolean.TRUE);
        return style;
    }
    @Override
    public JStyleModel visitTextStyleAlign(JQuickPDFParser.TextStyleAlignContext ctx) {
        style.setAlign(ctx.ID().getText());
        return style;
    }
    @Override
    public JAlign visitAlignment(JQuickPDFParser.AlignmentContext ctx) {
        JAlign align= visitAlignmentLocation(ctx.alignmentLocation());
        JStyleAlignModel styleAlignModel=new JStyleAlignModel();
        styleAlignModel.setDirection(align.getCode());
        this.align=styleAlignModel;
        return align;
    }

    @Override
    public JAlign visitAlignmentLocation(JQuickPDFParser.AlignmentLocationContext ctx) {
        String text=ctx.getText();
        return JAlign.codeOf(text);
    }
    @Override
    public JStyleSpacingModel visitSpacing(JQuickPDFParser.SpacingContext ctx) {
        JStyleSpacingModel thisSpacingModel=new JStyleSpacingModel();
        if(ctx.marginOrPadding()!=null){
            String type=visitMarginOrPadding(ctx.marginOrPadding());
            thisSpacingModel.setType(type);
        }
        JNumberUnitModel left=new JNumberUnitModel();
        if(ctx.number(0)!=null){
            left.setNumber(visitNumber(ctx.number(0)).intValue());
        }
        if(ctx.unit(0)!=null){
            left.setUnit(visitUnit(ctx.unit(0)));
        }
        JNumberUnitModel right=new JNumberUnitModel();
        if(ctx.number(1)!=null){
            right.setNumber(visitNumber(ctx.number(1)).intValue());
        }
        if(ctx.unit(1)!=null){
            right.setUnit(visitUnit(ctx.unit(1)));
        }
        thisSpacingModel.setLeftSpacing(left);
        thisSpacingModel.setRightSpacing(right);
        this.spacingModel=thisSpacingModel;
        return spacingModel;
    }
    @Override
    public String visitMarginOrPadding(JQuickPDFParser.MarginOrPaddingContext ctx) {
        return ctx.getText();
    }









}
