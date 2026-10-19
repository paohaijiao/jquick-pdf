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
 * 单据 144：汇票贴现凭证 / 托收凭证（bank 领域第 5 项）。
 *
 * <p>本类为独立可运行 Demo：自带 {@code main} 方法、不依赖任何公共工具类或实体类，
 * 数据以 key-value 形式通过 {@link JQuickPdfFactory#bindAll(Map)} 注入，
 * 版式全部落在模板文件 {@code demo/biz/bank/144_bill_discount_voucher.txt} 中。</p>
 *
 * <p>视觉主题「宝蓝·厚框·票式」：主色 #183590、辅色 #914527、浅底 #F0F2F7；
 * 版式为 ticket 抬头 + grid2 信息区 + solid 明细表 + split 签章区 + table 落款，
 * 与同领域其它单据互不重样。</p>
 *
 * <p>汇票贴现凭证 / 托收凭证：贴现银行与申请人、贴现利率、</p>
 * <p>逐笔票面金额与贴现利息实付金额、合计与银行签章栏。</p>
 *
 * <p>运行后输出：{@code D:/test/pdf/144_bill_discount_voucher.pdf}。</p>
 *
 * @author Martin
 * @version 1.0.0
 */
public class JQuickBiz144BillDiscountVoucher {

    private static final String PDF_DIR = "D:/test/pdf/";
    private static final String OUT_FILE = PDF_DIR + "144_bill_discount_voucher.pdf";
    private static final String TEMPLATE = "demo/biz/bank/144_bill_discount_voucher.txt";

    /** 模板固定明细行数，不足时补空行，避免占位符原样输出。 */
    private static final int ITEM_ROWS = 6;

    public static void main(String[] args) throws IOException {
        new File(PDF_DIR).mkdirs();

        // ---------- 1. 数据（无实体类，直接 bind）----------
        String[][] items = {
                {"1", "10400052 20261019 00382116", "500000.00", "2027-04-19", "182", "9125.00", "490875.00"},
                {"2", "10400052 20260912 00112774", "300000.00", "2027-03-12", "144", "4320.00", "295680.00"},
                {"3", "10400052 20260825 00990218", "180000.00", "2027-02-25", "129", "2322.00", "177678.00"},
                {"4", "10400052 20260718 00664330", "120000.00", "2027-01-18", "91", "1092.00", "118908.00"},
                {"5", "10400052 20260630 00221887", "86000.00", "2026-12-30", "72", "618.00", "85382.00"},
                {"6", "—", "0.00", "—", "0", "0.00", "0.00"}
        };

        Map<String, Object> data = new LinkedHashMap<String, Object>();
        data.put("voucherNo", "DSC-20261020-0055");
        data.put("handleDate", "2026年10月20日");
        data.put("bankName", "招商银行××分行");
        data.put("applicant", "××国际贸易有限公司");
        data.put("applicantAccount", "1259 8877 6655 4433");
        data.put("billNo", "10400052 20261019 00382116");
        data.put("billType", "银行承兑汇票");
        data.put("acceptor", "中国工商银行××支行");
        data.put("dueDate", "2027年04月19日");
        data.put("discountRate", "3.65%");
        data.put("remark", "贴现利息 = 票面金额 × 贴现利率 × 贴现天数 ÷ 360；托收凭证适用于委托银行收款，款项收妥后方可入账。");
        data.put("applicantSign", "××国际贸易有限公司");
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

        data.put("totalFace", new BigDecimal("1186000.00").setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString());

        data.put("totalInterest", new BigDecimal("17477.00").setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString());

        data.put("totalLine", "实付金额合计（大写）" + toChineseAmount(itemTotal) + "　　（小写）￥" + itemTotal.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString());

        // ---------- 2. 渲染 ----------
        JPdfConfig config = new JPdfConfig();
        config.getLayoutConfig().setFlexLayout(true);
        config.getDoc().setMargins(Arrays.asList(36, 36, 36, 36));
        config.getFontConfig().setDefaultFontName("Noto Sans CJK SC");
        config.getHeaderConfig().setEnabled(true);
        config.getHeaderConfig().setText("招商银行××分行　汇票贴现凭证　DSC-20261020-0055");
        config.getHeaderConfig().setFontSize(7.5f);
        config.getHeaderConfig().setHeight(28);
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
