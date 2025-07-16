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

import com.github.paohaijiao.color.JColorEnums;
import com.github.paohaijiao.enums.JUnit;
import com.github.paohaijiao.exception.JAssert;
import com.github.paohaijiao.executor.JQuickPdfStyleExecutor;
import com.github.paohaijiao.executor.JQuickPdfXExecutor;
import com.github.paohaijiao.model.JStyleAttributes;
import com.github.paohaijiao.parser.JQuickPDFLexer;
import com.github.paohaijiao.parser.JQuickPDFParser;
import com.github.paohaijiao.unit.JUnitConverter;
import com.github.paohaijiao.util.JStringUtils;
import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.layout.properties.UnitValue;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

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
public class JPdfXStyleVisitor extends JPdfXValueVisitor {
    @Override
    public JStyleAttributes visitStyleEle(JQuickPDFParser.StyleEleContext ctx) {
        if (null != ctx.style()) {
            return visitStyle(ctx.style());
        } else if (null != ctx.STRING()) {
            String string = ctx.STRING().getText();
            String value = JStringUtils.trim(string);
            JQuickPdfStyleExecutor executor=new JQuickPdfStyleExecutor();
            JStyleAttributes styleAttributes=executor.execute(value);
            return styleAttributes;
        }
        return new JStyleAttributes();
    }


    @Override
    public JStyleAttributes visitAttr(JQuickPDFParser.AttrContext ctx) {
        String key = null;
        Object value = null;
        if (ctx.key() != null) {
            key = visitKey(ctx.key());
        }
        JAssert.notNull(key, "key is null");
        if (ctx.value() != null) {
            String str = ctx.getText();
            value = visitValue(ctx.value());
        }
        JAssert.notNull(value, "value is null");
        JStyleAttributes attr = new JStyleAttributes();
        attr.put(key, value.toString());
        return attr;
    }

    @Override
    public JStyleAttributes visitStyle(JQuickPDFParser.StyleContext ctx) {
        JStyleAttributes data = new JStyleAttributes();
        for (JQuickPDFParser.AttrContext attrContext : ctx.attr()) {
            JStyleAttributes attr = visitAttr(attrContext);
            for (String key : attr.keySet()) {
                data.put(key, attr.get(key));
            }
        }
        return data;
    }

    @Override
    public Color visitHex(JQuickPDFParser.HexContext ctx) {
        if(null!=ctx){
            String value=ctx.getText();
            DeviceRgb  rgb=JColorEnums.convertHexToRgb(value);
            return rgb;
        }
        JAssert.throwNewException("invalidate the color format ");
        return null;
    }
    @Override
    public Color visitRgb(JQuickPDFParser.RgbContext ctx) {
         String text=ctx.RGB_COLOR().getText();
        String[] numbers = text.replace("rgb(", "").replace(")", "").split(",");
        int r = Integer.parseInt(numbers[0].trim());
        int g = Integer.parseInt(numbers[1].trim());
        int b = Integer.parseInt(numbers[2].trim());
        Color  rgb=JColorEnums.colorOf(r,g,b);
        return rgb;
    }
    @Override
    public Color visitCmykPecent(JQuickPDFParser.CmykPecentContext ctx) {
        if(null!=ctx&&null!=ctx.NUMBER()&&4==ctx.NUMBER().size()){
            BigDecimal c=new BigDecimal(ctx.NUMBER(0).getText());
            BigDecimal m=new BigDecimal(ctx.NUMBER(1).getText());
            BigDecimal y=new BigDecimal(ctx.NUMBER(2).getText());
            BigDecimal k=new BigDecimal(ctx.NUMBER(3).getText());
            Color rgb=JColorEnums.colorOfPercent(c.floatValue(),m.floatValue(),y.floatValue(), k.floatValue());
            return rgb;
        }
        JAssert.throwNewException("invalidate the color format ");
        return null;
    }
    @Override
    public Color visitCmykNumber(JQuickPDFParser.CmykNumberContext ctx) {
        String text=ctx.CMYK_COLOR().getText();
        String[] numbers = text.replace("cmyk(", "").replace(")", "").split(",");
        Float c=Float.parseFloat(numbers[0].trim());
        Float m=Float.parseFloat(numbers[1].trim());
        Float y=Float.parseFloat(numbers[2].trim());
        Float k=Float.parseFloat(numbers[3].trim());
        Color rgb=JColorEnums.colorOf(c.floatValue(),m.floatValue(),y.floatValue(), k.floatValue());
        return rgb;
    }
    @Override
    public Color visitColorVal(JQuickPDFParser.ColorValContext ctx) {
        if(null!=ctx&&null!=ctx.colorValue()){
            String color=ctx.colorValue().getText();
            Color rgb=JColorEnums.colorOf(color);
            return rgb;
        }
        JAssert.throwNewException("invalidate the color format ");
        return null;
    }

    @Override
    public UnitValue visitUnit(JQuickPDFParser.UnitContext ctx) {
        if(null==ctx.UNIT()){
            BigDecimal bigDecimal= new BigDecimal(ctx.number().getText());
            UnitValue unitValue= JUnitConverter.create(bigDecimal.floatValue(),JUnit.px.getCode());
            return unitValue;
        }
        if(null!=ctx.number()&&null!=ctx.UNIT()){
            String unit= ctx.UNIT().getText();
            JUnit un=JUnit.codeOf(unit);
            BigDecimal bigDecimal= new BigDecimal(ctx.number().getText());
            UnitValue unitValue= JUnitConverter.create(bigDecimal.floatValue(),un.getCode());
            return unitValue;
        }
        JAssert.throwNewException("invalidate the unit format ");
        return null;
    }




}
