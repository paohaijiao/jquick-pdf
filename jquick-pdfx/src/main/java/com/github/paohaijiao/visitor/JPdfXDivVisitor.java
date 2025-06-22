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

import com.github.paohaijiao.enums.JDirect;
import com.github.paohaijiao.model.JBackGroundModel;
import com.github.paohaijiao.model.JBorderModel;
import com.github.paohaijiao.model.style.JStyleDimensionModel;
import com.github.paohaijiao.model.style.JDivStyleItemModel;
import com.github.paohaijiao.model.style.JStyleSpacingModel;
import com.github.paohaijiao.parser.JQuickPDFParser;


/**
 * packageName com.paohaijiao.javelin.visitor
 *
 * @author Martin
 * @version 1.0.0
 * @className JPdfXDivVisitor
 * @date 2025/6/15
 * @description
 */
public class JPdfXDivVisitor extends JPdfXSvgVisitor {
    @Override
    public Void visitDiv(JQuickPDFParser.DivContext ctx) {
        if (ctx.divStyle() != null) {
            JDivStyleItemModel style= visitDivStyle(ctx.divStyle());
        }
        for (JQuickPDFParser.ElementContext elementContext : ctx.element()) {
            visitElement(elementContext);
        }
        return null;
    }

    @Override
    public JDivStyleItemModel visitDivStyle(JQuickPDFParser.DivStyleContext ctx) {
        JDivStyleItemModel jStyleItemModel=new JDivStyleItemModel();
        if (null != ctx.dimension()&&!ctx.dimension().isEmpty()) {
            JStyleDimensionModel dimensionModel=new JStyleDimensionModel();
            for(JQuickPDFParser.DimensionContext dimensionContext:ctx.dimension()) {
                JStyleDimensionModel dimension=(JStyleDimensionModel) visit(dimensionContext);
                if(null!=dimension) {
                    if(null!=dimension.getDimensionWidth()){
                        dimensionModel.setDimensionWidth(dimension.getDimensionWidth());
                    }
                    if(null!=dimension.getDimensionHeight()){
                        dimensionModel.setDimensionHeight(dimension.getDimensionHeight());
                    }
                    if(null!=dimension.getDimensionSize()){
                        dimensionModel.setDimensionSize(dimension.getDimensionSize());
                    }
                }
            }
            jStyleItemModel.setDimension(dimensionModel.getData());
        }
        if (null != ctx.background()) {
            JBackGroundModel backGround=visitBackground(ctx.background());
            jStyleItemModel.setBackground(backGround);
        }
        if (null != ctx.border()) {
            JBorderModel border= visitBorder(ctx.border());
            jStyleItemModel.setBorder(border);
        }
        if (null != ctx.spacing()) {
            JStyleSpacingModel spacing=visitSpacing(ctx.spacing());
            jStyleItemModel.setSpacing(spacing);
        }
        if (null != ctx.float_()) {
            JDirect direct=visitFloat(ctx.float_());
            jStyleItemModel.setDirect(direct);
        }
        return jStyleItemModel;
    }





}
