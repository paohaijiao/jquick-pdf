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

import com.github.paohaijiao.config.JGraphConfig;
import com.github.paohaijiao.exception.JAssert;
import com.github.paohaijiao.extension.svg.SvgImage;
import com.github.paohaijiao.model.JStyleAttributes;
import com.github.paohaijiao.parser.JQuickPDFParser;
import com.itextpdf.layout.element.IElement;

/**
 * packageName com.paohaijiao.javelin.visitor
 *
 * @author Martin
 * @version 1.0.0
 * @className JPdfXCommonVisitor
 * @date 2025/6/14
 * @description
 */
public class JPdfXSvgVisitor extends JPdfXTreeVisitor {


    @Override
    public IElement visitSvg(JQuickPDFParser.SvgContext ctx) {
        JStyleAttributes style = new JStyleAttributes();
        if (null != ctx.styleEle()) {
            style = visitStyleEle(ctx.styleEle());
        } else {
            style = new JStyleAttributes();
        }
        SvgImage svgImage=null;
        if(ctx.variable()!=null){
            Object var=  visitVariable(ctx.variable());
            JAssert.notNull(var,"the variable not  null");
            svgImage=new SvgImage(var.toString());
        }else if(ctx.IDENTIFIER()!=null) {
            String identify = ctx.IDENTIFIER().getText();
            JGraphConfig graphConfig = this.config.getGraphConfig();
            JAssert.notNull(graphConfig, "the graph config not  null");
            String content = graphConfig.drawGraph(identify);
            svgImage = new SvgImage(content);
        }

        super.buildStyle(svgImage, style);
        return svgImage;
    }

}
