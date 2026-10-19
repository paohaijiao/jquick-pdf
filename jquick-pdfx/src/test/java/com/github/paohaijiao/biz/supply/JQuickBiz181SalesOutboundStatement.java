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
package com.github.paohaijiao.biz.supply;

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
 * 单据 181：销售出库单 / 往来对账单 / 结算单（supply 领域第 2 项）。
 *
 * <p>本类为独立可运行 Demo：自带 {@code main} 方法、不依赖任何公共工具类或实体类，
 * 数据以 key-value 形式通过 {@link JQuickPdfFactory#bindAll(Map)} 注入，
 * 版式全部落在模板文件 {@code demo/biz/supply/181_sales_outbound_statement.txt} 中。</p>
 *
 * <p>视觉主题「朱砂·素雅·下线」：主色 #906B2C、辅色 #277F91、浅底 #F6F4F1；
 * 版式为 underline 抬头 + chip 信息区 + light 明细表 + card 签章区 + right 落款，
 * 与同领域其它单据互不重样。</p>
 *
 * <p>销售出库单 / 往来对账单 / 结算单：对账期间与双方信息、结算方式与账期，</p>
 * <p>逐笔出库明细与结算金额合计、往来结算与双方法务 / 财务签字栏。</p>
 *
 * <p>运行后输出：{@code D:/test/pdf/181_sales_outbound_statement.pdf}。</p>
 *
 * @author Martin
 * @version 1.0.0
 */
public class JQuickBiz181SalesOutboundStatement {

    private static final String PDF_DIR = "D:/test/pdf/";
    private static final String OUT_FILE = PDF_DIR + "181_sales_outbound_statement.pdf";
    private static final String TEMPLATE = "demo/biz/supply/181_sales_outbound_statement.txt";

    /** 模板固定明细行数，不足时补空行，避免占位符原样输出。 */
    private static final int ITEM_ROWS = 8;

    public static void main(String[] args) throws IOException {
        new File(PDF_DIR).mkdirs();

        // ---------- 1. 数据（无实体类，直接 bind）----------
        String[][] items = {
                {"1", "工业级伺服电机 SVM-750W", "SO-260903-11", "6", "2101.80", "12610.80", "已核对"},
                {"2", "精密减速机 RV-40E", "SO-260906-18", "12", "1401.20", "16814.40", "已核对"},
                {"3", "运动控制卡 MC-8020", "SO-260912-22", "6", "2655.50", "15933.00", "已核对"},
                {"4", "编码器线缆 ENC-5M", "SO-260915-31", "24", "97.18", "2332.32", "已核对"},
                {"5", "安装调试服务", "SO-260918-40", "1", "9116.00", "9116.00", "已核对"},
                {"6", "技术服务费", "SO-260925-52", "1", "4452.00", "4452.00", "已核对"},
                {"7", "包装运输费", "SO-260925-53", "1", "1374.08", "1374.08", "已核对"},
                {"8", "备件销售", "SO-260928-61", "8", "368.00", "2944.00", "已核对"}
        };

        Map<String, Object> data = new LinkedHashMap<String, Object>();
        data.put("outNo", "SO-20261010-0077");
        data.put("period", "2026年09月01日 — 2026年09月30日");
        data.put("sellerName", "××自动化科技有限公司");
        data.put("customerName", "××智能装备制造有限公司");
        data.put("customerInfo", "CUS-00312 / 崔行知 025-88××××××");
        data.put("payTerm", "月结 30 天 / 电汇");
        data.put("deliveryInfo", "成品库 / ××物流有限公司");
        data.put("currencyInfo", "CNY / 13%");
        data.put("reconcileResult", "双方账面一致，无差异");
        data.put("reconcileInfo", "2026年10月10日 / 柳文渊");
        data.put("remark", "对账单须由双方财务负责人签字并加盖财务专用章后生效；如有差异请在收到对账单后 5 个工作日内书面反馈。");
        data.put("sellerFinance", "柳文渊");
        data.put("customerFinance", "沈亦舟");
        data.put("salesHead", "闻仲平");

        // 明细逐格展开，并累计金额
        BigDecimal itemTotal = BigDecimal.ZERO;
        for (int i = 0; i < ITEM_ROWS; i++) {
            int n = i + 1;
            if (i >= items.length) {
                data.put("itemName" + n, "—");
                data.put("itemSpec" + n, "—");
                data.put("itemUnit" + n, "—");
                data.put("itemQty" + n, "—");
                data.put("itemPrice" + n, "—");
                data.put("itemAmount" + n, "—");
                data.put("itemTax" + n, "—");
                continue;
            }
            data.put("itemName" + n, items[i][0]);
            data.put("itemSpec" + n, items[i][1]);
            data.put("itemUnit" + n, items[i][2]);
            data.put("itemQty" + n, items[i][3]);
            data.put("itemPrice" + n, items[i][4]);
            itemTotal = itemTotal.add(new BigDecimal(items[i][5]));
            data.put("itemAmount" + n, items[i][5]);
            data.put("itemTax" + n, items[i][6]);
        }

        data.put("totalAmount", itemTotal.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString());
        data.put("totalAmountInWords", toChineseAmount(itemTotal));

        data.put("totalLine", "本期结算金额合计（大写）" + toChineseAmount(itemTotal) + "　　（小写）￥" + itemTotal.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString());

        data.put("settleLine", "本期应收 ￥" + itemTotal.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "　　已收预收款 ￥12000.00　　本期应补收（大写）" + toChineseAmount(itemTotal.subtract(new BigDecimal("12000.00"))) + "　　（小写）￥" + itemTotal.subtract(new BigDecimal("12000.00")).setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString());

        // ---------- 2. 渲染 ----------
        JPdfConfig config = new JPdfConfig();
        config.getLayoutConfig().setFlexLayout(true);
        config.getDoc().setMargins(Arrays.asList(40, 40, 40, 40));
        config.getFontConfig().setDefaultFontName("Noto Sans CJK SC");
        config.getHeaderConfig().setEnabled(true);
        config.getHeaderConfig().setText("××自动化科技有限公司　销售出库 / 往来对账单　SO-20261010-0077");
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
