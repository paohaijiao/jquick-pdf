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

import com.github.paohaijiao.enums.JAreaBreakEnums;
import com.github.paohaijiao.enums.JPageSize;
import com.github.paohaijiao.factory.JFontProviderFactory;
import com.github.paohaijiao.model.JStyleAreaBreakAttributes;
import com.github.paohaijiao.model.JStyleAttributes;
import com.github.paohaijiao.parser.JQuickPDFParser;
import com.itextpdf.layout.element.AreaBreak;


/**
 * packageName com.paohaijiao.javelin.visitor
 *
 * @author Martin
 * @version 1.0.0
 * @className JPdfXCommonVisitor
 * @date 2025/6/14
 * @description
 */
public class JPdfXAreaBreakVisitor extends JPdfXDivVisitor {
    @Override
    public AreaBreak visitAreaBreak(JQuickPDFParser.AreaBreakContext ctx) {
        JStyleAttributes style = new JStyleAttributes();
        String breakType = null;
        if (null != ctx.styleEle()) {
            style = visitStyleEle(ctx.styleEle());
        } else {
            style = new JStyleAttributes();
        }
        if (ctx.IDENTIFIER() != null) {
            breakType = ctx.IDENTIFIER().getText();
        }
        AreaBreak areaBreak = new AreaBreak();
        areaBreak.setFont(JFontProviderFactory.defualtFont());
        if (null != breakType) {
            JAreaBreakEnums breakEnums = JAreaBreakEnums.codeOf(breakType);
            if (breakEnums != null) {
                areaBreak = new AreaBreak(breakEnums.getType());
            }
        }
        super.buildStyle(areaBreak, style);
        this.buildStyle(areaBreak, style);
        return areaBreak;
    }

    private void buildStyle(AreaBreak areaBreak, JStyleAttributes style) {
        JStyleAreaBreakAttributes attr = new JStyleAreaBreakAttributes();
        attr.putAll(style);
        if (null != attr.getPageSize()) {
            JPageSize pageSize = JPageSize.codeOf(attr.getPageSize());
            if (null != pageSize) {
                areaBreak.setPageSize(pageSize.getPageSize());
            }

        }
    }

}
