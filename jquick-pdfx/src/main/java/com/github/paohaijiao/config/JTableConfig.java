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
package com.github.paohaijiao.config;

import lombok.Data;

/**
 * 表格渲染配置：对齐 HTML UA 样式的默认单元格排版行为，均可被单元格样式覆盖。
 *
 * @author Martin
 * @version 1.0.0
 */
@Data
public class JTableConfig {

    /**
     * 数字/金额列自动右对齐：整列数据单元格均为数值/金额时按右对齐绘制，
     * 对齐 HTML 数字列的排版习惯。默认开启。
     */
    private Boolean numericRightAlign = true;

    /**
     * 表头（th）默认居中，对齐 HTML UA 样式的 {@code th { text-align: center }}。默认开启。
     */
    private Boolean headerCenterAlign = true;

    /**
     * 单元格默认内边距（pt）：td/th 未声明 padding 样式时生效，
     * 优先级为 单元格样式 &gt; 表格样式 padding &gt; 这里的默认值。
     */
    private Float cellPaddingLeft = 6f;

    private Float cellPaddingRight = 6f;

    private Float cellPaddingTop = 3f;

    private Float cellPaddingBottom = 3f;
}
