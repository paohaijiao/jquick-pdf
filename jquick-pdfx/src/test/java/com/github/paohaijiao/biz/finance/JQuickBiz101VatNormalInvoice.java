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
 * 单据 101：增值税普通发票（finance 领域第 2 项）。
 *
 * <p>本类为独立可运行 Demo：自带 {@code main} 方法、不依赖任何公共工具类或实体类，
 * 数据以 key-value 形式通过 {@link JQuickPdfFactory#bindAll(Map)} 注入，
 * 版式全部落在模板文件 {@code demo/biz/finance/101_vat_normal_invoice.txt} 中。</p>
 *
 * <p>视觉主题「绯红·素雅·竖栏」：主色 #A22D34、辅色 #279158、浅底 #F6F1F1；
 * 版式为 side 抬头 + chip 信息区 + light 明细表 + right 签章区 + plain 落款，
 * 与同领域其它单据互不重样。</p>
 *
 * <p>票面要素：发票代码 / 号码、开票日期、校验码、购销双方名称与纳税人识别号、</p>
 * <p>货物或应税劳务明细（规格、单位、数量、单价、金额、税率、税额）、合计与价税合计。</p>
 *
 * <p>运行后输出：{@code D:/test/pdf/101_vat_normal_invoice.pdf}。</p>
 *
 * @author Martin
 * @version 1.0.0
 */
public class JQuickBiz101VatNormalInvoice {

    private static final String PDF_DIR = "D:/test/pdf/";
    private static final String OUT_FILE = PDF_DIR + "101_vat_normal_invoice.pdf";
    private static final String TEMPLATE = "demo/biz/finance/101_vat_normal_invoice.txt";

    /** 模板固定明细行数，不足时补空行，避免占位符原样输出。 */
    private static final int ITEM_ROWS = 5;

    public static void main(String[] args) throws IOException {
        new File(PDF_DIR).mkdirs();

        // ---------- 1. 数据（无实体类，直接 bind）----------
        String[][] items = {
                {"办公一体机", "MFP-3300", "台", "4", "4280.00", "—", "13%", "—"},
                {"笔记本电脑", "TP-X390", "台", "2", "6980.00", "—", "13%", "—"},
                {"碎纸机", "SD-950", "台", "3", "1180.00", "—", "13%", "—"},
                {"耗材配送服务", "—", "次", "1", "1500.00", "—", "6%", "—"},
                {"设备延保服务", "—", "项", "1", "2600.00", "—", "6%", "—"}
        };

        Map<String, Object> data = new LinkedHashMap<String, Object>();
        data.put("invoiceCode", "011002100411");
        data.put("invoiceNo", "30218776");
        data.put("issueDate", "2026年09月20日");
        data.put("checkCode", "1523 88471 20963 54118");
        data.put("buyerName", "××商贸有限公司");
        data.put("buyerTaxNo", "91330100MA2H5T71XB");
        data.put("sellerName", "××智能科技有限公司");
        data.put("sellerTaxNo", "91330100MA2G8N32WF");
        data.put("payee", "沈亦舟");
        data.put("reviewer", "柳文渊");
        data.put("drawer", "苏见宁");

        // 明细逐格展开，并累计金额
        BigDecimal itemTotal = BigDecimal.ZERO;
        BigDecimal taxTotal = BigDecimal.ZERO;
        for (int i = 0; i < ITEM_ROWS; i++) {
            int n = i + 1;
            if (i >= items.length) {
                data.put("itemName" + n, "—");
                data.put("itemSpec" + n, "—");
                data.put("itemUnit" + n, "—");
                data.put("itemQty" + n, "—");
                data.put("itemPrice" + n, "—");
                data.put("itemAmount" + n, "—");
                data.put("itemRate" + n, "—");
                data.put("itemTax" + n, "—");
                continue;
            }
            BigDecimal qty = new BigDecimal(items[i][3]);
            BigDecimal price = new BigDecimal(items[i][4]);
            BigDecimal amount = qty.multiply(price).setScale(2, BigDecimal.ROUND_HALF_UP);
            BigDecimal rate = new BigDecimal(items[i][6].replace("%", ""));
            BigDecimal tax = amount.multiply(rate)
                    .divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP);
            itemTotal = itemTotal.add(amount);
            taxTotal = taxTotal.add(tax);
            data.put("itemName" + n, items[i][0]);
            data.put("itemSpec" + n, items[i][1]);
            data.put("itemUnit" + n, items[i][2]);
            data.put("itemQty" + n, items[i][3]);
            data.put("itemPrice" + n, price.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString());
            data.put("itemAmount" + n, amount.toPlainString());
            data.put("itemRate" + n, items[i][6]);
            data.put("itemTax" + n, tax.toPlainString());
        }

        data.put("totalAmount", itemTotal.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString());
        data.put("totalTax", taxTotal.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString());
        BigDecimal withTax = itemTotal.add(taxTotal);
        data.put("totalWithTax", withTax.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString());
        data.put("totalWithTaxInWords", toChineseAmount(withTax));

        data.put("totalLine", "价税合计（大写）" + toChineseAmount(withTax) + "　　（小写）￥" + withTax.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString());

        // ---------- 2. 渲染 ----------
        JPdfConfig config = new JPdfConfig();
        config.getLayoutConfig().setFlexLayout(true);
        config.getDoc().setMargins(Arrays.asList(40, 40, 40, 40));
        config.getFontConfig().setDefaultFontName("Noto Sans CJK SC");
        config.getHeaderConfig().setEnabled(true);
        config.getHeaderConfig().setText("××智能科技有限公司　增值税普通发票　30218776");
        config.getHeaderConfig().setFontSize(8.5f);
        config.getHeaderConfig().setHeight(32);
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
