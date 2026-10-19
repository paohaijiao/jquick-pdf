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
 * 单据 146：贷款借据 / 借款合同回执（bank 领域第 7 项）。
 *
 * <p>本类为独立可运行 Demo：自带 {@code main} 方法、不依赖任何公共工具类或实体类，
 * 数据以 key-value 形式通过 {@link JQuickPdfFactory#bindAll(Map)} 注入，
 * 版式全部落在模板文件 {@code demo/biz/bank/146_loan_debit_note.txt} 中。</p>
 *
 * <p>视觉主题「群青·素雅·公文」：主色 #0D1988、辅色 #915527、浅底 #EFF0F8；
 * 版式为 doc 抬头 + strip 信息区 + light 明细表 + three 签章区 + plain 落款，
 * 与同领域其它单据互不重样。</p>
 *
 * <p>贷款借据 / 借款合同回执：借款合同编号、贷款种类与金额、期限与执行利率、</p>
 * <p>担保方式、分期还款计划（本金利息剩余本金）与银企签章栏。</p>
 *
 * <p>运行后输出：{@code D:/test/pdf/146_loan_debit_note.pdf}。</p>
 *
 * @author Martin
 * @version 1.0.0
 */
public class JQuickBiz146LoanDebitNote {

    private static final String PDF_DIR = "D:/test/pdf/";
    private static final String OUT_FILE = PDF_DIR + "146_loan_debit_note.pdf";
    private static final String TEMPLATE = "demo/biz/bank/146_loan_debit_note.txt";

    /** 模板固定明细行数，不足时补空行，避免占位符原样输出。 */
    private static final int ITEM_ROWS = 6;

    public static void main(String[] args) throws IOException {
        new File(PDF_DIR).mkdirs();

        // ---------- 1. 数据（无实体类，直接 bind）----------
        String[][] items = {
                {"1", "2026-11-21", "0.00", "9625.00", "3000000.00", "待还款", "付息"},
                {"2", "2026-12-21", "0.00", "9625.00", "3000000.00", "待还款", "付息"},
                {"3", "2027-01-21", "0.00", "9625.00", "3000000.00", "待还款", "付息"},
                {"4", "2027-02-21", "0.00", "9625.00", "3000000.00", "待还款", "付息"},
                {"5", "2027-03-21", "0.00", "9625.00", "3000000.00", "待还款", "付息"},
                {"6", "2027-10-21", "3000000.00", "9625.00", "0.00", "待还款", "到期还本"}
        };

        Map<String, Object> data = new LinkedHashMap<String, Object>();
        data.put("debitNo", "LN-20261022-0016");
        data.put("contractNo", "LOAN-2026-1016");
        data.put("loanDate", "2026年10月22日");
        data.put("bankName", "中国建设银行××支行");
        data.put("borrowerName", "××智能装备制造有限公司");
        data.put("borrowerAccount", "3201 8877 6655 0012");
        data.put("loanType", "流动资金贷款（一般授信）");
        data.put("amountInWords", "人民币叁佰万元整");
        data.put("amount", "￥3,000,000.00");
        data.put("loanTerm", "12 个月（2026-10-22 至 2027-10-21）");
        data.put("interestRate", "3.85%（LPR + 20BP）");
        data.put("repayMethod", "按月付息，到期还本");
        data.put("guaranteeType", "保证担保 + 机器设备抵押");
        data.put("remark", "本借据为借款合同的从合同，与合同具有同等法律效力；逾期贷款按合同约定计收罚息（上浮 50%）。");
        data.put("borrowerSign", "××智能装备制造有限公司");
        data.put("legalSign", "闻仲平");
        data.put("bankSign", "中国建设银行××支行");

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

        data.put("totalPrincipal", new BigDecimal("3000000.00").setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString());

        data.put("totalInterest", itemTotal.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString());

        data.put("totalLine", "利息合计（大写）" + toChineseAmount(itemTotal) + "　　（小写）￥" + itemTotal.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString());

        // ---------- 2. 渲染 ----------
        JPdfConfig config = new JPdfConfig();
        config.getLayoutConfig().setFlexLayout(true);
        config.getDoc().setMargins(Arrays.asList(44, 44, 44, 44));
        config.getFontConfig().setDefaultFontName("Noto Sans CJK SC");
        config.getHeaderConfig().setEnabled(true);
        config.getHeaderConfig().setText("中国建设银行××支行　贷款借据　LN-20261022-0016");
        config.getHeaderConfig().setFontSize(8.5f);
        config.getHeaderConfig().setHeight(30);
        config.getFooterConfig().setEnabled(true);
        config.getFooterConfig().setFontSize(8);
        config.getFooterConfig().setPageNumberFormat("· %d ·");

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
