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
package com.github.paohaijiao.biz.i18n;

import java.awt.Color;

/**
 * 单据 156（系列）· 单个语种的语言包（元数据 + KPI + 三张图表文案与配色）。
 *
 * <p>每个 i18n Demo 类（序号 185-209）持有一份自己的语言包实例，互不掺杂外语：
 * 语种 {@code xx} 的报告就只用 {@code xx} 的语言与字形。</p>
 *
 * @author Martin
 * @version 1.0.0
 */
class I18nLanguagePack {

    String lang;
    String defaultFont;
    // 报告元数据
    String reportNo;
    String reportDate;
    String orgName;
    String dataScope;
    // KPI
    String totalAssets;
    String crossBorderSettlement;
    String globalBranches;
    String countries;
    String revenue;
    String netProfit;
    String employees;
    String customers;
    // 落款
    String preparedBy;
    String reviewedBy;
    String issuedBy;
    // 图表 1（柱状图）
    String barTitle;
    String barSub;
    String barSeriesName;
    String[] barRegions;
    // 图表 2（环形图）
    String donutTitle;
    String donutSub;
    String donutCenterTitle;
    String donutCenterUnit;
    String donutFooter;
    String[] donutNames;
    // 图表 3（多重折线图）
    String lineTitle;
    String lineSub;
    String lineYAxis;
    String lineFooter;
    String[] lineMonths;
    String[] lineNames;
    // 每种语言的独立视觉主题（图表配色随模板主题走）
    String barColor;
    Color[] donutPalette;
    Color[] lineColors;
    Color chartArea;
}
