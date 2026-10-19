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
 * 单据 154：保理业务应收账款清单 / 债权转让凭证（bank 领域第 15 项）。
 *
 * <p>本类为独立可运行 Demo：自带 {@code main} 方法、不依赖任何公共工具类或实体类，
 * 数据以 key-value 形式通过 {@link JQuickPdfFactory#bindAll(Map)} 注入，
 * 版式全部落在模板文件 {@code demo/biz/bank/154_factoring_receivable_list.txt} 中。</p>
 *
 * <p>视觉主题「绛紫·厚框·典式」：主色 #5C2A72、辅色 #789127、浅底 #F4F1F6；
 * 版式为 classic 抬头 + strip 信息区 + solid 明细表 + three 签章区 + right 落款，
 * 与同领域其它单据互不重样。</p>
 *
 * <p>保理业务应收账款清单 / 债权转让凭证：保理类型与基础合同、</p>
 * <p>逐笔应收账款（发票号、债务人、金额、到期日、融资金额）、融资款计算与三方签章栏。</p>
 *
 * <p>运行后输出：{@code D:/test/pdf/154_factoring_receivable_list.pdf}。</p>
 *
 * @author Martin
 * @version 1.0.0
 */
public class JQuickBiz154FactoringReceivableList {

    private static final String PDF_DIR = "D:/test/pdf/";
    private static final String OUT_FILE = PDF_DIR + "154_factoring_receivable_list.pdf";
    private static final String TEMPLATE = "demo/biz/bank/154_factoring_receivable_list.txt";

    /** 模板固定明细行数，不足时补空行，避免占位符原样输出。 */
    private static final int ITEM_ROWS = 6;

    public static void main(String[] args) throws IOException {
        new File(PDF_DIR).mkdirs();

        // ---------- 1. 数据（无实体类，直接 bind）----------
        String[][] items = {
                {"1", "08876325", "××智能装备制造有限公司", "1433518.00", "2027-01-31", "1146814.40", "已转让"},
                {"2", "08877012", "××智能装备制造有限公司", "1080000.00", "2027-02-28", "864000.00", "已转让"},
                {"3", "08877013", "××智能装备制造有限公司", "936000.00", "2027-03-31", "748800.00", "已转让"},
                {"4", "08878821", "××智能装备制造有限公司", "864000.00", "2027-04-30", "691200.00", "已转让"},
                {"5", "08878822", "××智能装备制造有限公司", "543482.00", "2027-04-30", "434785.60", "已转让"},
                {"6", "—", "—", "0.00", "—", "0.00", "—"}
        };

        Map<String, Object> data = new LinkedHashMap<String, Object>();
        data.put("factoringNo", "FCT-20261030-0044");
        data.put("transferDate", "2026年10月30日");
        data.put("factorName", "××商业保理有限公司");
        data.put("factoringType", "有追索权明保理（应收账款转让）");
        data.put("sellerName", "××精密机械制造有限公司");
        data.put("buyerName", "××智能装备制造有限公司");
        data.put("baseContract", "设备供货合同 SC-2026-0713");
        data.put("receivableTotal", "￥4,860,000.00");
        data.put("financeRatio", "80%");
        data.put("financeAmount", "￥3,888,000.00");
        data.put("feeRate", "5.60%");
        data.put("dueDate", "2027年04月30日");
        data.put("registerOrg", "中国人民银行征信中心动产融资统一登记系统");
        data.put("registerNo", "REG-20261030-3301-0088");
        data.put("remark", "应收账款转让须在动产融资统一登记系统办理登记；有追索权保理项下，债务人到期未付款时保理商可向卖方追索。");
        data.put("sellerSign", "××精密机械制造有限公司");
        data.put("buyerSign", "××智能装备制造有限公司");
        data.put("factorSign", "××商业保理有限公司");

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
        data.put("totalAmountInWords", toChineseAmount(itemTotal));

        data.put("financeTotal", itemTotal.multiply(new BigDecimal("0.80")).setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString());

        data.put("totalLine", "应收账款转让合计（大写）" + toChineseAmount(itemTotal) + "　　（小写）￥" + itemTotal.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString());

        data.put("financeLine", "按 80% 融资比例，本次发放保理融资款（大写）" + toChineseAmount(itemTotal.multiply(new BigDecimal("0.80"))) + "　　（小写）￥" + itemTotal.multiply(new BigDecimal("0.80")).setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString());

        // ---------- 2. 渲染 ----------
        JPdfConfig config = new JPdfConfig();
        config.getLayoutConfig().setFlexLayout(true);
        config.getDoc().setMargins(Arrays.asList(44, 44, 44, 44));
        config.getFontConfig().setDefaultFontName("Noto Sans CJK SC");
        config.getHeaderConfig().setEnabled(true);
        config.getHeaderConfig().setText("××商业保理有限公司　应收账款转让清单　FCT-20261030-0044");
        config.getHeaderConfig().setFontSize(8f);
        config.getHeaderConfig().setHeight(30);
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
