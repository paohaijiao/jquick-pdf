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

import com.paohaijiao.javelin.enums.JDirect;
import com.paohaijiao.javelin.enums.JUnit;
import com.paohaijiao.javelin.model.*;
import com.paohaijiao.javelin.model.style.JStyleDimensionModel;
import com.paohaijiao.javelin.parser.JQuickPDFParser;

import java.math.BigDecimal;


/**
 * packageName com.paohaijiao.javelin.visitor
 *
 * @author Martin
 * @version 1.0.0
 * @className JPdfXCommonVisitor
 * @date 2025/6/14
 * @description
 */
public class JPdfXValueVisitor extends JPdfXCoreVisitor {

    @Override
    public String visitString(JQuickPDFParser.StringContext ctx) {
        return ctx.getText();
    }
    @Override
    public JUnit visitUnit(JQuickPDFParser.UnitContext ctx) {
        return JUnit.codeOf(ctx.getText());
    }
    @Override
    public JDirect visitFloat(JQuickPDFParser.FloatContext ctx) {
        String direct=visitFloatDirect(ctx.floatDirect());
        return JDirect.codeOf(direct);
    }

    @Override
    public String visitFloatDirect(JQuickPDFParser.FloatDirectContext ctx) {
        return ctx.getText();
    }
    @Override
    public JScaleModel visitScale(JQuickPDFParser.ScaleContext ctx) {
        BigDecimal number=visitNumber(ctx.number());
        JScaleModel jScaleModel=new JScaleModel();
        jScaleModel.setScale(Integer.valueOf(number.intValue()));
        return jScaleModel;
    }
    @Override
    public JOpacityModel visitOpacity(JQuickPDFParser.OpacityContext ctx) {
        BigDecimal number=visitNumber(ctx.number());
        JOpacityModel jOpacityModel=new JOpacityModel();
        jOpacityModel.setOpacity(Integer.valueOf(number.intValue()));
        return jOpacityModel;
    }
    @Override
    public BigDecimal visitNumber(JQuickPDFParser.NumberContext ctx) {
        return new BigDecimal(ctx.getText());
    }
    @Override
    public String visitColor(JQuickPDFParser.ColorContext ctx) {
        return ctx.getText();
    }
    @Override
    public JBorderRoundedModel visitBorderRounded(JQuickPDFParser.BorderRoundedContext ctx) {
        JBorderRoundedModel jBorderRoundedModel=new JBorderRoundedModel();
        BigDecimal number=visitNumber(ctx.number());
        jBorderRoundedModel.setNumber(number.intValue());
        JUnit unit=visitUnit(ctx.unit());
        jBorderRoundedModel.setUnit(unit);
        return jBorderRoundedModel;
    }
    @Override
    public JBorderModel visitBorder(JQuickPDFParser.BorderContext ctx) {
        JBorderModel jBorderModel=new JBorderModel();
        BigDecimal number=visitNumber(ctx.number());
        jBorderModel.setNumber(number.intValue());
        JUnit unit=visitUnit(ctx.unit());
        jBorderModel.setUnit(unit);
        if(null!=ctx.borderRounded()){
            JBorderRoundedModel borderRoundedModel=visitBorderRounded(ctx.borderRounded());
            jBorderModel.setBorderRounded(borderRoundedModel);
        }
        if(null!=ctx.color()){
            jBorderModel.setColor(visitColor(ctx.color()));
        }
        return jBorderModel;
    }
    @Override
    public JBackGroundModel visitBackground(JQuickPDFParser.BackgroundContext ctx) {
        JBackGroundModel model=new JBackGroundModel();
        if(ctx.color()!=null){
            String color=visitColor(ctx.color());
            model.setColor(color);
        }
        return model;
    }

    @Override
    public JNumberUnitModel visitDimensionWidth(JQuickPDFParser.DimensionWidthContext ctx) {
        BigDecimal number=visitNumber(ctx.number());
        JUnit unit=visitUnit(ctx.unit());
        JNumberUnitModel jNumberUnitModel=new JNumberUnitModel();
        jNumberUnitModel.setNumber(number.intValue());
        jNumberUnitModel.setUnit(unit);
        this.dimension.setWidth(jNumberUnitModel);
        return jNumberUnitModel;
    }
    @Override
    public JNumberUnitModel visitDimensionHeight(JQuickPDFParser.DimensionHeightContext ctx) {
        BigDecimal number=visitNumber(ctx.number());
        JUnit unit=visitUnit(ctx.unit());
        JNumberUnitModel jNumberUnitModel=new JNumberUnitModel();
        jNumberUnitModel.setNumber(number.intValue());
        jNumberUnitModel.setUnit(unit);
        this.dimension.setHeight(jNumberUnitModel);
        return jNumberUnitModel;
    }
    @Override
    public JStyleDimensionModel visitDimensionSize(JQuickPDFParser.DimensionSizeContext ctx) {
        BigDecimal leftNumber=visitNumber(ctx.number(0));
        JUnit leftUnit=visitUnit(ctx.unit(0));
        JNumberUnitModel left=new JNumberUnitModel();
        left.setNumber(leftNumber.intValue());
        left.setUnit(leftUnit);
        this.dimension.setLeftSize(left);
        BigDecimal rightNumber=visitNumber(ctx.number(0));
        JUnit rightUnit=visitUnit(ctx.unit(0));
        JNumberUnitModel right=new JNumberUnitModel();
        right.setNumber(rightNumber.intValue());
        right.setUnit(rightUnit);
        this.dimension.setRightSize(left);
        return dimension;
    }











}
