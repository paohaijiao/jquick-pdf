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
package com.github.paohaijiao.biz.customs;

import com.github.paohaijiao.config.JPdfConfig;
import com.github.paohaijiao.executor.JQuickPdfFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 单据 133：进出口商品检验 / 检疫检验报告（customs 领域第 9 项）。
 *
 * <p>本类为独立可运行 Demo：自带 {@code main} 方法、不依赖任何公共工具类或实体类，
 * 数据以 key-value 形式通过 {@link JQuickPdfFactory#bindAll(Map)} 注入，
 * 版式全部落在模板文件 {@code demo/biz/customs/133_commodity_inspection_report.txt} 中。</p>
 *
 * <p>视觉主题「宝蓝·密排·下线」：主色 #153580、辅色 #913F27、浅底 #F0F2F7；
 * 版式为 underline 抬头 + chip 信息区 + compact 明细表 + two 签章区 + table 落款，
 * 与同领域其它单据互不重样。</p>
 *
 * <p>进出口商检 / 检疫检验报告：报检单位与货物信息、检验依据、</p>
 * <p>逐项检验项目与判定结果、检验结论与检验机构签章栏。</p>
 *
 * <p>运行后输出：{@code D:/test/pdf/133_commodity_inspection_report.pdf}。</p>
 *
 * @author Martin
 * @version 1.0.0
 */
public class JQuickBiz133CommodityInspectionReport {

    private static final String PDF_DIR = "D:/test/pdf/";
    private static final String OUT_FILE = PDF_DIR + "133_commodity_inspection_report.pdf";
    private static final String TEMPLATE = "demo/biz/customs/133_commodity_inspection_report.txt";

    /** 模板固定明细行数，不足时补空行，避免占位符原样输出。 */
    private static final int ITEM_ROWS = 6;

    public static void main(String[] args) throws IOException {
        new File(PDF_DIR).mkdirs();

        // ---------- 1. 数据（无实体类，直接 bind）----------
        String[][] items = {
                {"1", "外观与包装检验", "包装完好、标识清晰", "包装完好，唛头清晰", "合格", "—"},
                {"2", "数量重量检验", "与装箱单一致", "46 件 / 12140 kg", "合格", "—"},
                {"3", "安全性能检验", "GB/T 14711 耐压 1500V", "通过，无击穿", "合格", "—"},
                {"4", "电磁兼容检验", "GB 4343.1 限值内", "符合 A 级限值", "合格", "—"},
                {"5", "木质包装检疫", "IPPC 标识有效", "标识清晰有效", "合格", "—"},
                {"6", "动植物检疫", "无土壤、无活体", "未检出", "合格", "—"}
        };

        Map<String, Object> data = new LinkedHashMap<String, Object>();
        data.put("reportNo", "CIQ-20261011-0091");
        data.put("checkDate", "2026年10月11日");
        data.put("orgName", "××海关技术中心 ××出入境检验检疫局");
        data.put("applicant", "××国际贸易有限公司");
        data.put("regNo", "3301-CIQ-008812");
        data.put("goodsName", "伺服电机、精密齿轮箱等机电产品");
        data.put("quantityInfo", "46 件 / 12140 kg");
        data.put("countryInfo", "SINGAPORE → CHINA");
        data.put("standard", "GB/T 14711-2013 及合同技术条款");
        data.put("conclusion", "所检项目符合标准要求");
        data.put("remark", "本报告仅对来样负责；未经检验机构书面批准，不得部分复制本报告。");
        data.put("inspector", "路承志");
        data.put("reviewer", "闻仲平");
        data.put("issuer", "××海关技术中心");

        // 明细逐格展开
        for (int i = 0; i < ITEM_ROWS; i++) {
            int n = i + 1;
            if (i >= items.length) {
                data.put("itemName" + n, "—");
                data.put("itemSpec" + n, "—");
                data.put("itemUnit" + n, "—");
                data.put("itemQty" + n, "—");
                data.put("itemPrice" + n, "—");
                data.put("itemAmount" + n, "—");
                continue;
            }
            data.put("itemName" + n, items[i][0]);
            data.put("itemSpec" + n, items[i][1]);
            data.put("itemUnit" + n, items[i][2]);
            data.put("itemQty" + n, items[i][3]);
            data.put("itemPrice" + n, items[i][4]);
            data.put("itemAmount" + n, items[i][5]);
        }

        // ---------- 2. 渲染 ----------
        JPdfConfig config = new JPdfConfig();
        config.getLayoutConfig().setFlexLayout(true);
        config.getDoc().setMargins(Arrays.asList(40, 40, 40, 40));
        config.getFontConfig().setDefaultFontName("Noto Sans CJK SC");
        config.getHeaderConfig().setEnabled(true);
        config.getHeaderConfig().setText("××海关技术中心　进出口商品检验报告　CIQ-20261011-0091");
        config.getHeaderConfig().setFontSize(8f);
        config.getHeaderConfig().setHeight(32);
        config.getFooterConfig().setEnabled(true);
        config.getFooterConfig().setFontSize(8);
        config.getFooterConfig().setPageNumberFormat("— %d —");

        JQuickPdfFactory factory = new JQuickPdfFactory(config);
        factory.bindAll(data);
        byte[] pdf = factory.executeResource(TEMPLATE);
        Files.write(new File(OUT_FILE).toPath(), pdf);
        System.out.println("已生成：" + OUT_FILE + "（" + pdf.length + " 字节）");
    }
}
