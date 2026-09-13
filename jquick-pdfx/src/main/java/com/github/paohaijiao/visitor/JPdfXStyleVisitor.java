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

import com.github.paohaijiao.visitor.render.PdfBoxRenderAdapter;
import com.github.paohaijiao.visitor.render.PdfBoxUnitConverter;
import com.github.paohaijiao.enums.JBorder;
import com.github.paohaijiao.exception.JAssert;
import com.github.paohaijiao.executor.JQuickPdfUnitExecutor;
import com.github.paohaijiao.model.JMarginModel;
import com.github.paohaijiao.model.JStyleAttributes;
import com.github.paohaijiao.parser.JQuickPDFParser;
import org.apache.pdfbox.pdmodel.graphics.color.PDColor;
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
        if (null == ctx) {
            return new JStyleAttributes();
        }
        // 直接按分隔符拆分原始声明串，而不是复用语法树/样式执行器。
        // 词法未定义十六进制颜色 token，形如 #3498db 的值会被截断
        // （如 backgroundColor:#3498db 退化为 backgroundColor=3498），
        // 导致背景、边框、字体颜色等样式静默丢失。
        String raw = null != ctx.STRING() ? ctx.STRING().getText() : rawText(ctx.style());
        return parseDeclarations(raw);
    }

    /** 返回语法树节点对应源码片段，保留词法阶段可能被丢弃的字符（如 #）。 */
    private String rawText(org.antlr.v4.runtime.ParserRuleContext context) {
        if (null == context || null == context.getStart() || null == context.getStop()) {
            return null;
        }
        org.antlr.v4.runtime.Token start = context.getStart();
        org.antlr.v4.runtime.CharStream input = start.getInputStream();
        if (null == input) {
            return context.getText();
        }
        return input.getText(org.antlr.v4.runtime.misc.Interval.of(
                start.getStartIndex(), context.getStop().getStopIndex()));
    }

    /**
     * 解析 CSS 声明串，形如 {@code color:#fff; padding:5px}。
     *
     * @param raw 原始声明串（可带引号）
     * @return 声明键值集合，无法识别的片段会被忽略
     */
    private JStyleAttributes parseDeclarations(String raw) {
        JStyleAttributes attributes = new JStyleAttributes();
        if (null == raw) {
            return attributes;
        }
        String text = stripQuotes(raw.trim());
        for (String declaration : text.split(";")) {
            String item = declaration.trim();
            if (item.isEmpty()) {
                continue;
            }
            int separator = item.indexOf(':');
            if (separator <= 0) {
                continue;
            }
            String key = item.substring(0, separator).trim();
            String value = stripQuotes(item.substring(separator + 1).trim());
            if (key.isEmpty() || value.isEmpty()) {
                continue;
            }
            attributes.put(key, value);
        }
        return attributes;
    }

    private String stripQuotes(String text) {
        if (null == text || text.length() < 2) {
            return text;
        }
        char first = text.charAt(0);
        char last = text.charAt(text.length() - 1);
        if ((first == '\'' && last == '\'') || (first == '"' && last == '"')) {
            return text.substring(1, text.length() - 1).trim();
        }
        return text;
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
        // JAssert.notNull(value, "value is null");
        JStyleAttributes attr = new JStyleAttributes();
        attr.put(key, null == value ? "" : value.toString());
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
    public PDColor visitColor(JQuickPDFParser.ColorContext ctx) {
        if (ctx == null) {
            return null; // or return a default color
        }
        if (ctx.getText().startsWith("#")) {
            PDColor rgb = PdfBoxRenderAdapter.color(ctx.getText(), null);
            return rgb;
        } else if (null != ctx.RGB_COLOR()) {
            String[] numbers = ctx.RGB_COLOR().getText().trim().replace("rgb(", "").replace(")", "").split(",");
            int r = Integer.parseInt(numbers[0].trim());
            int g = Integer.parseInt(numbers[1].trim());
            int b = Integer.parseInt(numbers[2].trim());
            PDColor rgb = PdfBoxRenderAdapter.color("rgb(" + r + "," + g + "," + b + ")", null);
            return rgb;
        } else if (null != ctx.CMYK_COLOR()) {
            String[] numbers = ctx.CMYK_COLOR().getText().trim().replace("cmyk(", "").replace(")", "").split(",");
            BigDecimal c = new BigDecimal(numbers[0]);
            BigDecimal m = new BigDecimal(numbers[1]);
            BigDecimal y = new BigDecimal(numbers[2]);
            BigDecimal k = new BigDecimal(numbers[3]);
            PDColor rgb = PdfBoxRenderAdapter.color("cmyk(" + c + "," + m + "," + y + "," + k + ")", null);
            return rgb;
        } else if (null != ctx.CMYK_PERCENT()) {
            String[] numbers = ctx.CMYK_PERCENT().getText().trim().replace("cmyk(", "").replace(")", "").replace("%", "").split(",");
            BigDecimal c = new BigDecimal(numbers[0]);
            BigDecimal m = new BigDecimal(numbers[1]);
            BigDecimal y = new BigDecimal(numbers[2]);
            BigDecimal k = new BigDecimal(numbers[3]);
            PDColor rgb = PdfBoxRenderAdapter.color("cmyk(" + c + "," + m + "," + y + "," + k + ")", null);
            return rgb;
        } else if (null != ctx.COLORENUM()) {
            String color = ctx.COLORENUM().getText().trim();
            return PdfBoxRenderAdapter.color(color, null);
        }
        return null;
    }

    @Override
    public Float visitUnit(JQuickPDFParser.UnitContext ctx) {
        if (null != ctx.NUMBERUNIT()) {
            String unit = ctx.NUMBERUNIT().getText();
            Pattern pattern = Pattern.compile("\\d+\\.?\\d*");
            Matcher matcher = pattern.matcher(ctx.NUMBERUNIT().getText());
            Float f = 0F;
            if (matcher.find()) {
                f = Float.parseFloat(matcher.group());
            }
            String code = unit.replaceAll("[0-9.]", "").trim();
            float unitValue = PdfBoxUnitConverter.toPoint(f + code, f);
            return unitValue;
        }
        return null;
    }

    @Override
    public JBorder visitBorderType(JQuickPDFParser.BorderTypeContext ctx) {
        if (null != ctx.BORDERTYPE()) {
            String border = ctx.BORDERTYPE().getText();
            return JBorder.codeOf(border);
        }
        return null;
    }

    @Override
    public JMarginModel visitMarginValue(JQuickPDFParser.MarginValueContext ctx) {
        if (ctx.NUMBERUNIT() != null && ctx.NUMBERUNIT().size() == 4) {
            JQuickPdfUnitExecutor executor = new JQuickPdfUnitExecutor();
            String txt = ctx.NUMBERUNIT().get(0).getText();
            float first = executor.execute(ctx.NUMBERUNIT().get(0).getText());
            float second = executor.execute(ctx.NUMBERUNIT().get(1).getText());
            float third = executor.execute(ctx.NUMBERUNIT().get(2).getText());
            float four = executor.execute(ctx.NUMBERUNIT().get(3).getText());
            JMarginModel m = new JMarginModel();
            m.setFirst(first);
            m.setSecond(second);
            m.setThird(third);
            m.setFourth(four);
            return m;
        }
        return null;
    }

}
