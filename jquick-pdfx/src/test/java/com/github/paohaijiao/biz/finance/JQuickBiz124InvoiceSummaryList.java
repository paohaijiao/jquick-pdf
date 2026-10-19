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
package com.github.paohaijiao.biz.finance;

import com.github.paohaijiao.config.JPdfConfig;
import com.github.paohaijiao.executor.JQuickPdfFactory;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 单据 124：开票汇总清单（finance 领域第 25 项）。
 *
 * <p>本类为独立可运行 Demo：自带 {@code main} 方法、不依赖任何公共工具类或实体类，
 * 数据以 key-value 形式通过 {@link JQuickPdfFactory#bindAll(Map)} 注入，
 * 版式全部落在模板文件 {@code demo/biz/finance/124_invoice_summary_list.txt} 中。</p>
 *
 * <p>视觉主题「松绿·厚框·典式」：主色 #11B024、辅色 #6C2791、浅底 #EFF8F0；
 * 版式为 classic 抬头 + grid2 信息区 + solid 明细表 + two 签章区 + table 落款，
 * 与同领域其它单据互不重样。</p>
 *
 * <p>开票汇总清单：汇总期间与开票单位、按票种分组的开票份数与金额税额、</p>
 * <p>本期不含税金额合计大小写与填报 / 复核 / 负责人签字栏。</p>
 *
 * <p>运行后输出：{@code D:/test/pdf/124_invoice_summary_list.pdf}。</p>
 *
 * @author Martin
 * @version 1.0.0
 */
public class JQuickBiz124InvoiceSummaryList {

    private static final String PDF_DIR = "D:/test/pdf/";
    private static final String OUT_FILE = PDF_DIR + "124_invoice_summary_list.pdf";
    private static final String TEMPLATE = "demo/biz/finance/124_invoice_summary_list.txt";

    /** 模板固定明细行数，不足时补空行，避免占位符原样输出。 */
    private static final int ITEM_ROWS = 5;

    public static void main(String[] args) throws IOException {
        new File(PDF_DIR).mkdirs();

        // ---------- 1. 数据（无实体类，直接 bind）----------
        String[][] items = {
                {"增值税专用发票", "18", "1268600.00", "164918.00", "1433518.00"},
                {"增值税普通发票", "16", "386420.00", "50234.60", "436654.60"},
                {"电子普通发票", "9", "128350.00", "12835.00", "141185.00"},
                {"机动车销售统一发票", "2", "256637.16", "33362.83", "289999.99"},
                {"通行费电子发票", "1", "1029.13", "30.87", "1060.00"}
        };

        Map<String, Object> data = new LinkedHashMap<String, Object>();
        data.put("summaryNo", "SUM-202610-0001");
        data.put("period", "2026年10月01日 — 2026年10月31日");
        data.put("sellerName", "××自动化科技有限公司");
        data.put("sellerTaxNo", "91320100MA1Y2P94QD");
        data.put("invoiceCount", "46");
        data.put("voidCount", "2");
        data.put("redCount", "1");
        data.put("preparer", "苏见宁");
        data.put("remark", "本清单按月汇总增值税发票开具情况，与增值税纳税申报表附表一「开具增值税专用发票」栏次核对一致。");
        data.put("reviewer", "柳文渊");
        data.put("drawer", "闻仲平");

        // 明细逐格展开，并累计金额
        BigDecimal itemTotal = BigDecimal.ZERO;
        for (int i = 0; i < ITEM_ROWS; i++) {
            int n = i + 1;
            if (i >= items.length) {
                data.put("itemName" + n, "—");
                data.put("itemSpec" + n, "—");
                data.put("itemUnit" + n, "—");
                data.put("itemQty" + n, "—");
                data.put("itemAmount" + n, "—");
                continue;
            }
            data.put("itemName" + n, items[i][0]);
            data.put("itemSpec" + n, items[i][1]);
            itemTotal = itemTotal.add(new BigDecimal(items[i][2]));
            data.put("itemUnit" + n, items[i][2]);
            data.put("itemQty" + n, items[i][3]);
            data.put("itemAmount" + n, items[i][4]);
        }

        data.put("totalAmount", itemTotal.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString());
        data.put("totalAmountInWords", toChineseAmount(itemTotal));

        data.put("totalLine", "本期不含税金额合计（大写）" + toChineseAmount(itemTotal) + "　　（小写）￥" + itemTotal.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString());

        data.put("totalTax", new BigDecimal("261381.30").setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString());

        data.put("totalWithTax", itemTotal.add(new BigDecimal("261381.30")).setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString());

        // ---------- 2. 渲染 ----------
        JPdfConfig config = new JPdfConfig();
        config.getLayoutConfig().setFlexLayout(true);
        config.getDoc().setMargins(Arrays.asList(36, 36, 36, 36));
        config.getFontConfig().setDefaultFontName("Noto Sans CJK SC");
        config.getHeaderConfig().setEnabled(true);
        config.getHeaderConfig().setText("××自动化科技有限公司　开票汇总清单　SUM-202610-0001");
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

    /** 人民币金额转票据大写（元角分，末尾补「整」）。 */
    private static String toChineseAmount(BigDecimal amount) {
        String[] digits = {"零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖"};
        String[] units = {"", "拾", "佰", "仟"};
        String[] sections = {"", "万", "亿", "万亿"};
        long cents = amount.setScale(2, BigDecimal.ROUND_HALF_UP)
                .movePointRight(2).longValue();
        if (cents == 0) {
            return "零元整";
        }
        long yuan = cents / 100;
        int jiao = (int) (cents % 100 / 10);
        int fen = (int) (cents % 10);
        StringBuilder sb = new StringBuilder();
        if (yuan > 0) {
            sb.append(toChineseSection(yuan, digits, units, sections)).append("元");
        }
        if (jiao == 0 && fen == 0) {
            sb.append("整");
        } else {
            if (jiao > 0) {
                sb.append(digits[jiao]).append("角");
            } else if (yuan > 0) {
                sb.append("零");
            }
            if (fen > 0) {
                sb.append(digits[fen]).append("分");
            }
        }
        return sb.toString();
    }

    /** 整数部分按四位分节转大写。 */
    private static String toChineseSection(long value, String[] digits, String[] units, String[] sections) {
        StringBuilder sb = new StringBuilder();
        int sectionIndex = 0;
        boolean needZero = false;
        while (value > 0) {
            int section = (int) (value % 10000);
            StringBuilder part = new StringBuilder();
            int unitIndex = 0;
            boolean zeroInSection = false;
            int temp = section;
            while (temp > 0) {
                int digit = temp % 10;
                if (digit == 0) {
                    zeroInSection = true;
                } else {
                    if (zeroInSection || (needZero && part.length() == 0 && sb.length() > 0)) {
                        part.insert(0, digits[0]);
                    }
                    part.insert(0, units[unitIndex]);
                    part.insert(0, digits[digit]);
                    zeroInSection = false;
                    needZero = false;
                }
                unitIndex++;
                temp /= 10;
            }
            if (part.length() > 0) {
                part.append(sections[sectionIndex]);
            }
            sb.insert(0, part);
            needZero = section < 1000 && section > 0;
            value /= 10000;
            sectionIndex++;
        }
        return sb.toString();
    }
}
