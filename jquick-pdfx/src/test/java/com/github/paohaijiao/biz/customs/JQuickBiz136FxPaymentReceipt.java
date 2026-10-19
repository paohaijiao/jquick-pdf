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
import java.math.BigDecimal;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 单据 136：外汇付汇凭证 / 收汇水单（customs 领域第 12 项）。
 *
 * <p>本类为独立可运行 Demo：自带 {@code main} 方法、不依赖任何公共工具类或实体类，
 * 数据以 key-value 形式通过 {@link JQuickPdfFactory#bindAll(Map)} 注入，
 * 版式全部落在模板文件 {@code demo/biz/customs/136_fx_payment_receipt.txt} 中。</p>
 *
 * <p>视觉主题「群青·素雅·典式」：主色 #1A1A88、辅色 #915F27、浅底 #F0F0F7；
 * 版式为 classic 抬头 + grid2 信息区 + light 明细表 + split 签章区 + right 落款，
 * 与同领域其它单据互不重样。</p>
 *
 * <p>外汇付汇凭证 / 收汇水单：汇款人与收款人及开户行、SWIFT、折算汇率，</p>
 * <p>逐笔付汇明细与折人民币金额、银行业务章占位。</p>
 *
 * <p>运行后输出：{@code D:/test/pdf/136_fx_payment_receipt.pdf}。</p>
 *
 * @author Martin
 * @version 1.0.0
 */
public class JQuickBiz136FxPaymentReceipt {

    private static final String PDF_DIR = "D:/test/pdf/";
    private static final String OUT_FILE = PDF_DIR + "136_fx_payment_receipt.pdf";
    private static final String TEMPLATE = "demo/biz/customs/136_fx_payment_receipt.txt";

    /** 模板固定明细行数，不足时补空行，避免占位符原样输出。 */
    private static final int ITEM_ROWS = 6;

    public static void main(String[] args) throws IOException {
        new File(PDF_DIR).mkdirs();

        // ---------- 1. 数据（无实体类，直接 bind）----------
        String[][] items = {
                {"1", "一般贸易进口付汇", "SC-2026-0713 / INV-2026-0918", "40980.40", "291780.45", "186.00", "已汇出"},
                {"2", "预付货款 30%", "SC-2026-0713 / PI-2026-0811", "12294.12", "87534.14", "96.00", "已汇出"},
                {"3", "运保费付汇", "BL COSU6123456780", "2400.00", "17088.00", "48.00", "已汇出"},
                {"4", "技术服务费付汇", "TSA-2026-0902", "5400.00", "38448.00", "72.00", "已汇出"},
                {"5", "退汇 / 退款", "—", "0.00", "0.00", "0.00", "无"},
                {"6", "其他经常项目", "—", "0.00", "0.00", "0.00", "无"}
        };

        Map<String, Object> data = new LinkedHashMap<String, Object>();
        data.put("bizNo", "FX-20261014-0093");
        data.put("tradeDate", "2026年10月14日");
        data.put("currency", "USD / 美元");
        data.put("remitter", "××国际贸易有限公司");
        data.put("beneficiary", "×× PRECISION MACHINERY PTE. LTD.");
        data.put("beneficiaryBank", "×× BANK, SINGAPORE BRANCH");
        data.put("beneficiaryAccount", "SG88 ×××× ×××× 1201 / ××××SG2X");
        data.put("remitBank", "中国银行××分行");
        data.put("exchangeRate", "7.1200");
        data.put("declareNo", "33010026101400931288");
        data.put("remark", "外汇收支须通过货物贸易外汇监测系统名录登记，单笔 5 万美元以上付汇须提交合同与发票等背景材料。");
        data.put("operator", "沈亦舟");
        data.put("reviewer", "柳文渊");
        data.put("bankSign", "中国银行××分行");

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
            itemTotal = itemTotal.add(new BigDecimal(items[i][3]));
            data.put("itemQty" + n, items[i][3]);
            data.put("itemPrice" + n, items[i][4]);
            data.put("itemAmount" + n, items[i][5]);
            data.put("itemTax" + n, items[i][6]);
        }

        data.put("totalAmount", itemTotal.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString());

        data.put("cnyTotal", itemTotal.multiply(new BigDecimal("7.1200")).setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString());

        data.put("totalLine", "付汇合计（大写）" + toChineseAmount(itemTotal.multiply(new BigDecimal("7.1200"))) + "　　（小写）USD " + itemTotal.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString());

        // ---------- 2. 渲染 ----------
        JPdfConfig config = new JPdfConfig();
        config.getLayoutConfig().setFlexLayout(true);
        config.getDoc().setMargins(Arrays.asList(36, 36, 36, 36));
        config.getFontConfig().setDefaultFontName("Noto Sans CJK SC");
        config.getHeaderConfig().setEnabled(true);
        config.getHeaderConfig().setText("中国银行××分行　外汇付汇凭证　FX-20261014-0093");
        config.getHeaderConfig().setFontSize(8f);
        config.getHeaderConfig().setHeight(28);
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
