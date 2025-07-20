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
import com.github.paohaijiao.enums.JBorder;
import com.github.paohaijiao.exception.JAssert;
import com.github.paohaijiao.executor.JQuickPdfStyleExecutor;
import com.github.paohaijiao.executor.JQuickPdfUnitExecutor;
import com.github.paohaijiao.model.JMarginModel;
import com.github.paohaijiao.model.JStyleAttributes;
import com.github.paohaijiao.parser.JQuickPDFParser;
import com.github.paohaijiao.unit.JUnitConverter;
import com.github.paohaijiao.util.JStringUtils;
import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.layout.properties.UnitValue;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


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
    public Color visitColor(JQuickPDFParser.ColorContext ctx) {
        if (ctx == null) {
            return null; // or return a default color
        }
        if (ctx.getText().startsWith("#")) {
            DeviceRgb  rgb=JColorEnums.convertHexToRgb(ctx.getText());
            return rgb;
        } else if (null!= ctx.RGB_COLOR()) {
            String[] numbers = ctx.RGB_COLOR().getText().trim().replace("rgb(", "").replace(")", "").split(",");
            int r = Integer.parseInt(numbers[0].trim());
            int g = Integer.parseInt(numbers[1].trim());
            int b = Integer.parseInt(numbers[2].trim());
            Color  rgb=JColorEnums.colorOf(r,g,b);
            return rgb;
        }else if (null!= ctx.CMYK_COLOR()) {
            String[] numbers = ctx.CMYK_COLOR().getText().trim().replace("cmyk(", "").replace(")", "").split(",");
            BigDecimal c=new BigDecimal(numbers[0]);
            BigDecimal m=new BigDecimal(numbers[1]);
            BigDecimal y=new BigDecimal(numbers[2]);
            BigDecimal k=new BigDecimal(numbers[3]);
            Color rgb=JColorEnums.colorOfPercent(c.floatValue(),m.floatValue(),y.floatValue(), k.floatValue());
            return rgb;
        }else if (null!= ctx.CMYK_PERCENT()) {
            String[] numbers = ctx.CMYK_PERCENT().getText().trim().replace("cmyk(", "").replace(")", "").replace("%","").split(",");
            BigDecimal c=new BigDecimal(numbers[0]);
            BigDecimal m=new BigDecimal(numbers[1]);
            BigDecimal y=new BigDecimal(numbers[2]);
            BigDecimal k=new BigDecimal(numbers[3]);
            Color rgb=JColorEnums.colorOfPercent(c.floatValue(),m.floatValue(),y.floatValue(), k.floatValue());
            return rgb;
        }else if (null!= ctx.COLORENUM()) {
            String color = ctx.COLORENUM().getText().trim();
            return JColorEnums.colorOf(color);
        }
        return null;
    }

    @Override
    public UnitValue visitUnit(JQuickPDFParser.UnitContext ctx) {
        if(null!=ctx.NUMBERUNIT()){
            String unit=ctx.NUMBERUNIT().getText();
            Pattern pattern = Pattern.compile("\\d+\\.?\\d*");
            Matcher matcher = pattern.matcher(ctx.NUMBERUNIT().getText());
            Float f=0F;
            if (matcher.find()) {
                f= Float.parseFloat(matcher.group());
            }
            String code=unit.replaceAll("[0-9.]","").trim();
            UnitValue unitValue= JUnitConverter.create(f,code);
            return unitValue;
        }
        return null;
    }
    @Override
    public JBorder visitBorderType(JQuickPDFParser.BorderTypeContext ctx) {
        if(null!=ctx.BORDERTYPE()){
            String border=ctx.BORDERTYPE().getText();
            return JBorder.codeOf(border);
        }
        return null;
    }
    @Override
    public JMarginModel visitMarginValue(JQuickPDFParser.MarginValueContext ctx) {
        if(ctx.NUMBERUNIT() != null&&ctx.NUMBERUNIT().size()==4) {
            JQuickPdfUnitExecutor executor=new JQuickPdfUnitExecutor();
            String txt=ctx.NUMBERUNIT().get(0).getText();
            UnitValue first=executor.execute(ctx.NUMBERUNIT().get(0).getText());
            UnitValue second=executor.execute(ctx.NUMBERUNIT().get(1).getText());
            UnitValue third=executor.execute(ctx.NUMBERUNIT().get(2).getText());
            UnitValue four=executor.execute(ctx.NUMBERUNIT().get(3).getText());
            JMarginModel m=new JMarginModel();
            m.setFirst(first);
            m.setSecond(second);
            m.setThird(third);
            m.setFourth(four);
            return m;
        }
        return null;
    }






}
