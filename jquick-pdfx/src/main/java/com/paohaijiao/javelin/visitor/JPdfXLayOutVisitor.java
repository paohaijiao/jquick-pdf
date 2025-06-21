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
import com.itextpdf.kernel.geom.PageSize;
import com.paohaijiao.javelin.enums.JUnit;
import com.paohaijiao.javelin.model.JBorderRoundedModel;
import com.paohaijiao.javelin.model.JNumberUnitModel;
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
public class JPdfXLayOutVisitor extends JPdfXValueVisitor {
    @Override
    public PageSize visitLayoutOption(JQuickPDFParser.LayoutOptionContext ctx) {
        if (ctx.A0() != null) return PageSize.A0;
        if (ctx.A1() != null) return PageSize.A1;
        if (ctx.A2() != null) return PageSize.A2;
        if (ctx.A3() != null) return PageSize.A3;
        if (ctx.A4() != null) return PageSize.A4;
        if (ctx.A5() != null) return PageSize.A5;
        if (ctx.A6() != null) return PageSize.A6;
        if (ctx.A7() != null) return PageSize.A7;
        if (ctx.A8() != null) return PageSize.A8;
        if (ctx.A9() != null) return PageSize.A9;
        if (ctx.A10() != null) return PageSize.A10;
        if (ctx.B0() != null) return PageSize.B0;
        if (ctx.B1() != null) return PageSize.B1;
        if (ctx.B2() != null) return PageSize.B2;
        if (ctx.B3() != null) return PageSize.B3;
        if (ctx.B4() != null) return PageSize.B4;
        if (ctx.B5() != null) return PageSize.B5;
        if (ctx.B6() != null) return PageSize.B6;
        if (ctx.B7() != null) return PageSize.B7;
        if (ctx.B8() != null) return PageSize.B8;
        if (ctx.B9() != null) return PageSize.B9;
        if (ctx.B10() != null) return PageSize.B10;
        if (ctx.DEFAULT() != null) return PageSize.A4;
        if (ctx.EXECUTIVE() != null) return PageSize.EXECUTIVE;
        if (ctx.LEDGER() != null) return PageSize.LEDGER;
        if (ctx.LEGAL() != null) return PageSize.LEGAL;
        if (ctx.LETTER() != null) return PageSize.LETTER;
        if (ctx.TABLOID() != null) return PageSize.TABLOID;
        return PageSize.A4;
    }
    @Override
    public PageSize visitCustomOption(JQuickPDFParser.CustomOptionContext ctx) {
        BigDecimal numberLeft=visitNumber(ctx.number(0));
        BigDecimal numberRight=visitNumber(ctx.number(1));
        float width = Float.parseFloat(numberLeft.toPlainString());
        float height = Float.parseFloat(numberRight.toPlainString());
        String widthUnit = ctx.unit(0).getText();
        String heightUnit = ctx.unit(1).getText();
        width = convertToPoints(width, widthUnit);
        height = convertToPoints(height, heightUnit);
        return new PageSize(width, height);
    }






}
