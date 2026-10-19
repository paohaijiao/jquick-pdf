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
 * 单据 147：信贷放款凭证 / 还款结清回执（bank 领域第 8 项）。
 *
 * <p>本类为独立可运行 Demo：自带 {@code main} 方法、不依赖任何公共工具类或实体类，
 * 数据以 key-value 形式通过 {@link JQuickPdfFactory#bindAll(Map)} 注入，
 * 版式全部落在模板文件 {@code demo/biz/bank/147_credit_release_voucher.txt} 中。</p>
 *
 * <p>视觉主题「群青·疏朗·通栏」：主色 #2A1D98、辅色 #916A27、浅底 #F1F0F7；
 * 版式为 banner 抬头 + kv 信息区 + airy 明细表 + right 签章区 + right 落款，
 * 与同领域其它单据互不重样。</p>
 *
 * <p>信贷放款凭证 / 还款结清回执：授信合同与业务类型、放款与逐期还款明细、</p>
 * <p>累计归还本金与利息、结清说明与银企签章栏。</p>
 *
 * <p>运行后输出：{@code D:/test/pdf/147_credit_release_voucher.pdf}。</p>
 *
 * @author Martin
 * @version 1.0.0
 */
public class JQuickBiz147CreditReleaseVoucher {

    private static final String PDF_DIR = "D:/test/pdf/";
    private static final String OUT_FILE = PDF_DIR + "147_credit_release_voucher.pdf";
    private static final String TEMPLATE = "demo/biz/bank/147_credit_release_voucher.txt";

    /** 模板固定明细行数，不足时补空行，避免占位符原样输出。 */
    private static final int ITEM_ROWS = 6;

    public static void main(String[] args) throws IOException {
        new File(PDF_DIR).mkdirs();

        // ---------- 1. 数据（无实体类，直接 bind）----------
        String[][] items = {
                {"1", "贷款放款", "2026-10-22", "3000000.00", "0.00", "3000000.00"},
                {"2", "按期付息", "2026-11-21", "0.00", "9625.00", "3000000.00"},
                {"3", "按期付息", "2026-12-21", "0.00", "9625.00", "3000000.00"},
                {"4", "部分还本", "2027-06-21", "500000.00", "8020.83", "2500000.00"},
                {"5", "提前还本", "2027-09-21", "1200000.00", "6250.00", "1300000.00"},
                {"6", "到期结清", "2027-10-21", "1300000.00", "80979.17", "0.00"}
        };

        Map<String, Object> data = new LinkedHashMap<String, Object>();
        data.put("voucherNo", "CR-20261023-0022");
        data.put("handleDate", "2026年10月23日");
        data.put("bankName", "中国建设银行××支行");
        data.put("customerName", "××智能装备制造有限公司");
        data.put("creditNo", "CREDIT-2026-0881");
        data.put("bizType", "流动资金贷款放款 / 到期结清");
        data.put("releaseAmount", "￥3,000,000.00");
        data.put("repaidPrincipal", "￥3,000,000.00");
        data.put("repaidInterest", "￥115,500.00");
        data.put("settleStatus", "已结清（本息全部归还）");
        data.put("settleNo", "SETTLE-20261023-0022");
        data.put("settleNote", "截至 2027 年 10 月 21 日，该笔授信项下贷款本息已全部清偿，贷款合同项下权利义务终止，可凭本回执办理抵押注销手续。");
        data.put("customerSign", "××智能装备制造有限公司");
        data.put("operator", "崔行知");
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
        data.put("totalAmountInWords", toChineseAmount(itemTotal));

        data.put("interestTotal", new BigDecimal("114500.00").setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString());

        data.put("totalLine", "累计归还本金合计（大写）" + toChineseAmount(itemTotal) + "　　（小写）￥" + itemTotal.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString());

        // ---------- 2. 渲染 ----------
        JPdfConfig config = new JPdfConfig();
        config.getLayoutConfig().setFlexLayout(true);
        config.getDoc().setMargins(Arrays.asList(48, 48, 48, 48));
        config.getFontConfig().setDefaultFontName("Noto Sans CJK SC");
        config.getHeaderConfig().setEnabled(true);
        config.getHeaderConfig().setText("中国建设银行××支行　信贷放款 / 结清回执　CR-20261023-0022");
        config.getHeaderConfig().setFontSize(7.5f);
        config.getHeaderConfig().setHeight(30);
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
