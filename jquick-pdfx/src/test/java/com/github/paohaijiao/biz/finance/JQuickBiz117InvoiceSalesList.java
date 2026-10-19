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
 * 单据 117：发票销货清单 / 开票明细清单（finance 领域第 18 项）。
 *
 * <p>本类为独立可运行 Demo：自带 {@code main} 方法、不依赖任何公共工具类或实体类，
 * 数据以 key-value 形式通过 {@link JQuickPdfFactory#bindAll(Map)} 注入，
 * 版式全部落在模板文件 {@code demo/biz/finance/117_invoice_sales_list.txt} 中。</p>
 *
 * <p>视觉主题「橄榄·疏朗·通栏」：主色 #6DB427、辅色 #2B2791、浅底 #F3F7F0；
 * 版式为 banner 抬头 + chip 信息区 + airy 明细表 + three 签章区 + table 落款，
 * 与同领域其它单据互不重样。</p>
 *
 * <p>发票销货清单：对应发票代码号码、购销双方信息、逐行货物明细与金额合计，</p>
 * <p>含其中税额拆分与销货单位签章占位。</p>
 *
 * <p>运行后输出：{@code D:/test/pdf/117_invoice_sales_list.pdf}。</p>
 *
 * @author Martin
 * @version 1.0.0
 */
public class JQuickBiz117InvoiceSalesList {

    private static final String PDF_DIR = "D:/test/pdf/";
    private static final String OUT_FILE = PDF_DIR + "117_invoice_sales_list.pdf";
    private static final String TEMPLATE = "demo/biz/finance/117_invoice_sales_list.txt";

    /** 模板固定明细行数，不足时补空行，避免占位符原样输出。 */
    private static final int ITEM_ROWS = 8;

    public static void main(String[] args) throws IOException {
        new File(PDF_DIR).mkdirs();

        // ---------- 1. 数据（无实体类，直接 bind）----------
        String[][] items = {
                {"1", "工业级伺服电机", "SVM-750W", "台", "6", "1860.00", "11160.00"},
                {"2", "工业级伺服电机", "SVM-1500W", "台", "6", "2480.00", "14880.00"},
                {"3", "精密减速机", "RV-40E", "台", "12", "1240.00", "14880.00"},
                {"4", "运动控制卡", "MC-8020", "块", "6", "2350.00", "14100.00"},
                {"5", "编码器线缆", "ENC-5M", "根", "24", "86.00", "2064.00"},
                {"6", "安装调试服务", "—", "项", "1", "8600.00", "8600.00"},
                {"7", "技术服务费", "—", "项", "1", "4200.00", "4200.00"},
                {"8", "包装运输费", "—", "次", "1", "1216.00", "1216.00"}
        };

        Map<String, Object> data = new LinkedHashMap<String, Object>();
        data.put("listNo", "SDL-20261017-0512");
        data.put("invoiceNo", "08876325");
        data.put("invoiceCode", "011002100311");
        data.put("issueDate", "2026年10月17日");
        data.put("sellerName", "××自动化科技有限公司");
        data.put("sellerTaxNo", "91320100MA1Y2P94QD");
        data.put("buyerName", "××智能装备制造有限公司");
        data.put("buyerTaxNo", "91320100MA1X7K38XG");
        data.put("remark", "本销货清单为增值税发票的附件，当货物品名较多、发票票面无法全部列示时随发票一并出具。");
        data.put("payee", "苏见宁");
        data.put("reviewer", "柳文渊");
        data.put("drawer", "××自动化科技有限公司");

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
            data.put("itemAmount" + n, items[i][5]);
            itemTotal = itemTotal.add(new BigDecimal(items[i][6]));
            data.put("itemTax" + n, items[i][6]);
        }

        data.put("totalAmount", itemTotal.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString());
        data.put("totalAmountInWords", toChineseAmount(itemTotal));

        data.put("totalLine", "销货金额合计（大写）" + toChineseAmount(itemTotal) + "　　（小写）￥" + itemTotal.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString());

        data.put("taxLine", "其中税额（13%）：￥" + itemTotal.multiply(new BigDecimal("0.13")).setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString());

        // ---------- 2. 渲染 ----------
        JPdfConfig config = new JPdfConfig();
        config.getLayoutConfig().setFlexLayout(true);
        config.getDoc().setMargins(Arrays.asList(40, 40, 40, 40));
        config.getFontConfig().setDefaultFontName("Noto Sans CJK SC");
        config.getHeaderConfig().setEnabled(true);
        config.getHeaderConfig().setText("××自动化科技有限公司　发票销货清单　SDL-20261017-0512");
        config.getHeaderConfig().setFontSize(7.5f);
        config.getHeaderConfig().setHeight(32);
        config.getFooterConfig().setEnabled(true);
        config.getFooterConfig().setFontSize(8);
        config.getFooterConfig().setPageNumberFormat("第 %d 页");

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
