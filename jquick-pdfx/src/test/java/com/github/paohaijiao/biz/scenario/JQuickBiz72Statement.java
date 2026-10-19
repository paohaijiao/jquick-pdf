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
package com.github.paohaijiao.biz.scenario;

import com.github.paohaijiao.config.JPdfConfig;
import com.github.paohaijiao.executor.JQuickPdfFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 场景 72：客户月度对账单 PDF（明细自动分页、底部汇总行、页码，模板文件 + bind 数据注入版）。
 *
 * <p>本类不定义任何实体类，全部数据通过 {@link JQuickPdfFactory#bindAll(Map)}
 * 以 key-value 形式注入；版式全部落在模板文件 {@code report/biz/72_statement.txt} 中。
 * 明细逐行 bind 为 tN / sN / aN / dN（时间 / 项目 / 金额 / 方向），模板逐行引用。</p>
 *
 * <p>数据校验：期末余额由程序按「期初 + 增加 - 减少」重算，避免人工录入不一致。</p>
 *
 * <p>产物目录约定：PDF 输出 {@code D:/test/pdf/72_statement.pdf}。</p>
 *
 * @author Martin
 * @version 2.0.0
 */
public class JQuickBiz72Statement {

    private static final String PDF_DIR = "D:/test/pdf/";
    private static final String OUT_FILE = PDF_DIR + "72_statement.pdf";
    private static final String TEMPLATE = "report/biz/72_statement.txt";

    public static void main(String[] args) throws IOException {
        new File(PDF_DIR).mkdirs();

        // ---------- 1. 数据（普通 Map，无实体类）----------
        String[][] raw = {
                {"2026-09-01", "8 月平台服务费", "12800.00"},
                {"2026-09-03", "接口调用超量计费（8 月）", "3260.00"},
                {"2026-09-05", "银行转账还款", "-20000.00"},
                {"2026-09-08", "报表模板定制（合同 BJ-2026-0032）", "26000.00"},
                {"2026-09-12", "SVG 图表组件扩容", "4500.00"},
                {"2026-09-15", "技术服务费（9 月上半月）", "6400.00"},
                {"2026-09-18", "票据贴现利息", "-860.00"},
                {"2026-09-20", "银行转账还款", "-30000.00"},
                {"2026-09-22", "数据迁移服务", "32000.00"},
                {"2026-09-25", "账户管理费", "-120.00"},
                {"2026-09-26", "现场部署服务费", "19200.00"},
                {"2026-09-28", "培训服务（4 场）", "19200.00"},
                {"2026-09-30", "结算尾差调整", "-38.50"}
        };
        BigDecimal increase = BigDecimal.ZERO;
        BigDecimal decrease = BigDecimal.ZERO;
        Map<String, Object> data = new LinkedHashMap<String, Object>();
        for (int i = 0; i < raw.length; i++) {
            BigDecimal amount = new BigDecimal(raw[i][2]);
            boolean isIncrease = amount.signum() >= 0;
            if (isIncrease) {
                increase = increase.add(amount);
            } else {
                decrease = decrease.add(amount.abs());
            }
            String suffix = String.valueOf(i + 1);
            data.put("t" + suffix, raw[i][0]);
            data.put("s" + suffix, raw[i][1]);
            data.put("a" + suffix, amount.abs().setScale(2, RoundingMode.HALF_UP).toPlainString());
            data.put("d" + suffix, isIncrease ? "增加" : "减少");
        }
        BigDecimal opening = new BigDecimal("186420.00");
        BigDecimal closing = opening.add(increase).subtract(decrease);

        data.put("customer", "××城市商业银行股份有限公司");
        data.put("month", "2026 年 09 月");
        data.put("monthShort", "09");
        data.put("billNo", "DZ-2026-09-0871");
        data.put("openingBalance", opening.setScale(2, RoundingMode.HALF_UP).toPlainString());
        data.put("totalIncrease", increase.setScale(2, RoundingMode.HALF_UP).toPlainString());
        data.put("totalDecrease", decrease.setScale(2, RoundingMode.HALF_UP).toPlainString());
        data.put("closingBalance", closing.setScale(2, RoundingMode.HALF_UP).toPlainString());

        // ---------- 2. 渲染 ----------
        JPdfConfig config = new JPdfConfig();
        config.getLayoutConfig().setFlexLayout(true);
        config.getDoc().setMargins(Arrays.asList(45, 45, 55, 45));
        config.getFooterConfig().setEnabled(true);
        config.getFooterConfig().setFontSize(8);
        config.getFooterConfig().setPageNumberFormat("第 %d 页");

        JQuickPdfFactory factory = new JQuickPdfFactory(config);
        factory.bindAll(data);
        byte[] pdf = factory.executeResource(TEMPLATE);

        FileOutputStream out = new FileOutputStream(OUT_FILE);
        out.write(pdf);
        out.close();
        System.out.println("已生成：" + OUT_FILE);
    }
}
