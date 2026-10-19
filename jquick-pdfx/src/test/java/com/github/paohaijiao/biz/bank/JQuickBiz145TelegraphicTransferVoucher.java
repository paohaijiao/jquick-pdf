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
 * 单据 145：电汇 / 信汇凭证、付款委托书（bank 领域第 6 项）。
 *
 * <p>本类为独立可运行 Demo：自带 {@code main} 方法、不依赖任何公共工具类或实体类，
 * 数据以 key-value 形式通过 {@link JQuickPdfFactory#bindAll(Map)} 注入，
 * 版式全部落在模板文件 {@code demo/biz/bank/145_telegraphic_transfer_voucher.txt} 中。</p>
 *
 * <p>视觉主题「群青·斑马·下线」：主色 #2E2B9D、辅色 #916227、浅底 #F1F1F6；
 * 版式为 underline 抬头 + chip 信息区 + striped 明细表 + two 签章区 + right 落款，
 * 与同领域其它单据互不重样。</p>
 *
 * <p>电汇 / 信汇凭证、付款委托书：汇出行与汇入行、汇款人与收款人账号，</p>
 * <p>币种金额与汇率、逐项费用明细与承担方、汇款人预留印鉴栏。</p>
 *
 * <p>运行后输出：{@code D:/test/pdf/145_telegraphic_transfer_voucher.pdf}。</p>
 *
 * @author Martin
 * @version 1.0.0
 */
public class JQuickBiz145TelegraphicTransferVoucher {

    private static final String PDF_DIR = "D:/test/pdf/";
    private static final String OUT_FILE = PDF_DIR + "145_telegraphic_transfer_voucher.pdf";
    private static final String TEMPLATE = "demo/biz/bank/145_telegraphic_transfer_voucher.txt";

    /** 模板固定明细行数，不足时补空行，避免占位符原样输出。 */
    private static final int ITEM_ROWS = 6;

    public static void main(String[] args) throws IOException {
        new File(PDF_DIR).mkdirs();

        // ---------- 1. 数据（无实体类，直接 bind）----------
        String[][] items = {
                {"1", "电汇手续费", "汇款金额 0.1%，最低 50 元", "0.10%", "291.78", "汇款人", "291.78"},
                {"2", "电报费", "每笔固定", "固定", "150.00", "汇款人", "150.00"},
                {"3", "中间行费用", "代理行扣收", "—", "186.00", "收款人", "186.00"},
                {"4", "汇出汇款手续费", "加急处理费", "固定", "80.00", "汇款人", "80.00"},
                {"5", "币种折算差价", "买入价与中间价差额", "—", "0.00", "—", "0.00"},
                {"6", "其他费用", "—", "—", "0.00", "—", "0.00"}
        };

        Map<String, Object> data = new LinkedHashMap<String, Object>();
        data.put("voucherNo", "TT-20261021-0088");
        data.put("entrustDate", "2026年10月21日");
        data.put("transferType", "电汇 T/T（加急）");
        data.put("bankName", "中国银行××分行");
        data.put("remitter", "××国际贸易有限公司");
        data.put("remitterAccount", "6217 ×××× ×××× 8890");
        data.put("payeeBank", "×× BANK, SINGAPORE BRANCH / ××××SG2X");
        data.put("payeeName", "×× PRECISION MACHINERY PTE. LTD.");
        data.put("payeeAccount", "SG88 ×××× ×××× 1201");
        data.put("amountInfo", "USD 40,980.40 / 折 ￥291,780.45");
        data.put("exchangeRate", "7.1200");
        data.put("usage", "进口设备货款（合同 SC-2026-0713）");
        data.put("remark", "电汇（T/T）为顺汇方式，信汇（M/T）以信函通知解付；付款委托书须加盖预留印鉴方为有效。");
        data.put("remitterSign", "××国际贸易有限公司");
        data.put("operator", "沈亦舟");
        data.put("reviewer", "柳文渊");

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

        data.put("totalLine", "费用合计（大写）" + toChineseAmount(itemTotal) + "　　（小写）￥" + itemTotal.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString());

        // ---------- 2. 渲染 ----------
        JPdfConfig config = new JPdfConfig();
        config.getLayoutConfig().setFlexLayout(true);
        config.getDoc().setMargins(Arrays.asList(40, 40, 40, 40));
        config.getFontConfig().setDefaultFontName("Noto Sans CJK SC");
        config.getHeaderConfig().setEnabled(true);
        config.getHeaderConfig().setText("中国银行××分行　电汇凭证　TT-20261021-0088");
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
