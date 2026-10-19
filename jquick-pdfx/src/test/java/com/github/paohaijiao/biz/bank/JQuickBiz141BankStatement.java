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
package com.github.paohaijiao.biz.bank;

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
 * 单据 141：单位银行账户对账单 / 流水明细单（bank 领域第 2 项）。
 *
 * <p>本类为独立可运行 Demo：自带 {@code main} 方法、不依赖任何公共工具类或实体类，
 * 数据以 key-value 形式通过 {@link JQuickPdfFactory#bindAll(Map)} 注入，
 * 版式全部落在模板文件 {@code demo/biz/bank/141_bank_statement.txt} 中。</p>
 *
 * <p>视觉主题「宝蓝·素雅·通栏」：主色 #144088、辅色 #913627、浅底 #F0F3F7；
 * 版式为 banner 抬头 + chip 信息区 + light 明细表 + two 签章区 + plain 落款，
 * 与同领域其它单据互不重样。</p>
 *
 * <p>单位银行账户对账单：账户信息与对账期间、期初 / 期末余额、</p>
 * <p>逐笔流水（凭证号、摘要、收入支出、余额）与本月合计、银企双签栏。</p>
 *
 * <p>运行后输出：{@code D:/test/pdf/141_bank_statement.pdf}。</p>
 *
 * @author Martin
 * @version 1.0.0
 */
public class JQuickBiz141BankStatement {

    private static final String PDF_DIR = "D:/test/pdf/";
    private static final String OUT_FILE = PDF_DIR + "141_bank_statement.pdf";
    private static final String TEMPLATE = "demo/biz/bank/141_bank_statement.txt";

    /** 模板固定明细行数，不足时补空行，避免占位符原样输出。 */
    private static final int ITEM_ROWS = 8;

    public static void main(String[] args) throws IOException {
        new File(PDF_DIR).mkdirs();

        // ---------- 1. 数据（无实体类，直接 bind）----------
        String[][] items = {
                {"2026-09-02", "转 8812", "货款回收（××智能装备）", "286400.00", "0.00", "1572740.55"},
                {"2026-09-06", "转 8831", "支付设备采购款", "0.00", "43000.00", "1529740.55"},
                {"2026-09-11", "现 0122", "营业款现金存入", "26800.00", "0.00", "1556540.55"},
                {"2026-09-15", "转 8866", "支付模具开发费", "0.00", "18000.00", "1538540.55"},
                {"2026-09-18", "转 8890", "支付厂房租金", "0.00", "86000.00", "1452540.55"},
                {"2026-09-22", "转 8917", "出口退税到账", "37838.70", "0.00", "1490379.25"},
                {"2026-09-25", "转 8940", "支付职工薪酬", "0.00", "156000.00", "1334379.25"},
                {"2026-09-28", "转 8971", "货款回收（××物流）", "318539.05", "0.00", "1652918.30"}
        };

        Map<String, Object> data = new LinkedHashMap<String, Object>();
        data.put("accountName", "××国际贸易有限公司");
        data.put("accountNo", "4301 0123 4567 8901");
        data.put("bankName", "中国工商银行××支行");
        data.put("period", "2026年09月01日 — 2026年09月30日");
        data.put("accountType", "基本存款账户");
        data.put("currency", "CNY / 人民币");
        data.put("openBalance", "￥1,286,340.55");
        data.put("closeBalance", "￥1,652,918.30");
        data.put("statementNo", "ST-202609-33010088");
        data.put("printDate", "2026年10月01日");
        data.put("remark", "本对账单按月出具，请于收到后 15 日内核对，如有不符请向开户行出具书面查询。");
        data.put("bankSign", "中国工商银行××支行");
        data.put("reviewer", "柳文渊");
        data.put("preparer", "苏见宁");

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
                continue;
            }
            data.put("itemName" + n, items[i][0]);
            data.put("itemSpec" + n, items[i][1]);
            data.put("itemUnit" + n, items[i][2]);
            itemTotal = itemTotal.add(new BigDecimal(items[i][3]));
            data.put("itemQty" + n, items[i][3]);
            data.put("itemPrice" + n, items[i][4]);
            data.put("itemAmount" + n, items[i][5]);
        }

        data.put("totalAmount", itemTotal.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString());

        data.put("totalIn", itemTotal.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString());

        data.put("totalOut", new BigDecimal("303000.00").setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString());

        data.put("totalLine", "本月收入合计（大写）" + toChineseAmount(itemTotal) + "　　（小写）￥" + itemTotal.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString());

        data.put("balanceLine", "本月支出合计 ￥303000.00　　期末余额 ￥1652918.30　　借贷双方核对一致");

        // ---------- 2. 渲染 ----------
        JPdfConfig config = new JPdfConfig();
        config.getLayoutConfig().setFlexLayout(true);
        config.getDoc().setMargins(Arrays.asList(40, 40, 40, 40));
        config.getFontConfig().setDefaultFontName("Noto Sans CJK SC");
        config.getHeaderConfig().setEnabled(true);
        config.getHeaderConfig().setText("中国工商银行××支行　单位账户对账单　ST-202609-33010088");
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
