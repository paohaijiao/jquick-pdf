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
package com.github.paohaijiao.biz.gov;

import com.github.paohaijiao.config.JPdfConfig;
import com.github.paohaijiao.executor.JQuickPdfFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 单据 157：许可证年度审查申报表 / 年审回执（gov 领域第 3 项）。
 *
 * <p>本类为独立可运行 Demo：自带 {@code main} 方法、不依赖任何公共工具类或实体类，
 * 数据以 key-value 形式通过 {@link JQuickPdfFactory#bindAll(Map)} 注入，
 * 版式全部落在模板文件 {@code demo/biz/gov/157_license_annual_review.txt} 中。</p>
 *
 * <p>视觉主题「天青·疏朗·下线」：主色 #0F9987、辅色 #912766、浅底 #EFF8F7；
 * 版式为 underline 抬头 + chip 信息区 + airy 明细表 + split 签章区 + plain 落款，
 * 与同领域其它单据互不重样。</p>
 *
 * <p>许可证年度审查申报表 / 年审回执：许可证编号与持证单位、年审年度，</p>
 * <p>逐项审验项目（上年度 / 本年度情况与结论）、年审结论与双方签章栏。</p>
 *
 * <p>运行后输出：{@code D:/test/pdf/157_license_annual_review.pdf}。</p>
 *
 * @author Martin
 * @version 1.0.0
 */
public class JQuickBiz157LicenseAnnualReview {

    private static final String PDF_DIR = "D:/test/pdf/";
    private static final String OUT_FILE = PDF_DIR + "157_license_annual_review.pdf";
    private static final String TEMPLATE = "demo/biz/gov/157_license_annual_review.txt";

    /** 模板固定明细行数，不足时补空行，避免占位符原样输出。 */
    private static final int ITEM_ROWS = 6;

    public static void main(String[] args) throws IOException {
        new File(PDF_DIR).mkdirs();

        // ---------- 1. 数据（无实体类，直接 bind）----------
        String[][] items = {
                {"1", "经营资质符合性", "经营范围与许可一致", "经营范围与许可一致", "合格", "—"},
                {"2", "车辆技术状况", "营运车辆 46 辆，年检合格", "营运车辆 52 辆，年检合格", "合格", "—"},
                {"3", "安全生产管理制度", "制度健全并执行", "制度健全并执行", "合格", "—"},
                {"4", "从业人员资格", "从业资格证持证率 100%", "持证率 100%，新增 6 人", "合格", "—"},
                {"5", "违法违规记录", "无重大违法记录", "一般违规 1 起，已整改", "合格", "限期整改"},
                {"6", "税费缴纳情况", "无欠税欠费", "无欠税欠费", "合格", "—"}
        };

        Map<String, Object> data = new LinkedHashMap<String, Object>();
        data.put("applyNo", "AR-20261020-0071");
        data.put("applyDate", "2026年10月20日");
        data.put("orgName", "××市交通运输局");
        data.put("licenseName", "道路运输经营许可证");
        data.put("licenseNo", "交运管许可 ××字 320100088812 号");
        data.put("holderName", "××物流有限公司");
        data.put("creditCode", "91320500MA1M8T3X7Y");
        data.put("reviewYear", "2026 年度");
        data.put("lastResult", "合格");
        data.put("contactInfo", "路承志 / 0512-66××××××");
        data.put("reviewConclusion", "经审验，该单位 2026 年度道路运输经营许可年度审查结论为合格，许可继续有效，有效期至 2027 年 10 月 19 日。");
        data.put("remark", "年审不合格或逾期未年审的，许可证自动失效；被责令限期整改的，整改合格后方可通过年审。");
        data.put("operator", "苏见宁");
        data.put("reviewer", "柳文渊");
        data.put("holderSign", "××物流有限公司");

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
        config.getHeaderConfig().setText("××市交通运输局　许可证年度审查回执　AR-20261020-0071");
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
