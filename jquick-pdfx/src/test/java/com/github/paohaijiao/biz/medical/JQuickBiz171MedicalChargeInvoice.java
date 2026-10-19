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
package com.github.paohaijiao.biz.medical;

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
 * 单据 171：医疗门诊收费票据 / 住院收费发票（medical 领域第 2 项）。
 *
 * <p>本类为独立可运行 Demo：自带 {@code main} 方法、不依赖任何公共工具类或实体类，
 * 数据以 key-value 形式通过 {@link JQuickPdfFactory#bindAll(Map)} 注入，
 * 版式全部落在模板文件 {@code demo/biz/medical/171_medical_charge_invoice.txt} 中。</p>
 *
 * <p>视觉主题「青瓷·素雅·通栏」：主色 #16B36B、辅色 #912789、浅底 #EFF8F4；
 * 版式为 banner 抬头 + kv 信息区 + light 明细表 + three 签章区 + table 落款，
 * 与同领域其它单据互不重样。</p>
 *
 * <p>医疗门诊 / 住院收费票据：票据代码号码与医疗机构、患者与医保信息，</p>
 * <p>逐项收费明细（规格、数量、单价、金额、医保类别）、总费用与医保结算、签章栏。</p>
 *
 * <p>运行后输出：{@code D:/test/pdf/171_medical_charge_invoice.pdf}。</p>
 *
 * @author Martin
 * @version 1.0.0
 */
public class JQuickBiz171MedicalChargeInvoice {

    private static final String PDF_DIR = "D:/test/pdf/";
    private static final String OUT_FILE = PDF_DIR + "171_medical_charge_invoice.pdf";
    private static final String TEMPLATE = "demo/biz/medical/171_medical_charge_invoice.txt";

    /** 模板固定明细行数，不足时补空行，避免占位符原样输出。 */
    private static final int ITEM_ROWS = 8;

    public static void main(String[] args) throws IOException {
        new File(PDF_DIR).mkdirs();

        // ---------- 1. 数据（无实体类，直接 bind）----------
        String[][] items = {
                {"1", "床位费（三人间）", "日", "9", "60.00", "540.00", "甲类"},
                {"2", "护理费（二级护理）", "日", "9", "32.00", "288.00", "甲类"},
                {"3", "冠状动脉造影术", "次", "1", "3800.00", "3800.00", "乙类"},
                {"4", "冠脉支架（药物洗脱）", "个", "1", "8600.00", "8600.00", "乙类"},
                {"5", "西药费（抗血小板等）", "—", "1", "2680.50", "2680.50", "甲类"},
                {"6", "化验费（心肌酶谱等）", "项", "8", "86.00", "688.00", "甲类"},
                {"7", "检查费（心电图 / CT）", "次", "3", "320.00", "960.00", "甲类"},
                {"8", "诊查费 / 治疗费", "—", "1", "486.00", "486.00", "甲类"}
        };

        Map<String, Object> data = new LinkedHashMap<String, Object>();
        data.put("invoiceCode", "32010026");
        data.put("invoiceNo", "0012887766");
        data.put("issueDate", "2026年10月27日");
        data.put("hospitalName", "××市第一人民医院");
        data.put("invoiceType", "住院医疗收费电子票据（财政部监制）");
        data.put("patientInfo", "崔行知 / 城镇职工基本医疗保险");
        data.put("idCard", "3201×××××××××××××× / 3201××××××××");
        data.put("admissionInfo", "ZY-20261018-008812 / 心血管内科");
        data.put("settleWay", "医保即时结算 + 个人自付");
        data.put("cashierInfo", "沈亦舟 / 柳文渊");
        data.put("remark", "本票据为财政部监制的医疗收费电子票据，与纸质票据具有同等法律效力，可作为医保报销与商业保险理赔凭证。");
        data.put("cashier", "沈亦舟");
        data.put("reviewer", "柳文渊");
        data.put("hospitalSign", "××市第一人民医院");

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

        data.put("totalLine", "医疗总费用（大写）" + toChineseAmount(itemTotal) + "　　（小写）￥" + itemTotal.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString());

        data.put("settleLine", "医保统筹支付 ￥" + itemTotal.multiply(new BigDecimal("0.72")).setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "　　个人自付 ￥" + itemTotal.multiply(new BigDecimal("0.28")).setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString());

        // ---------- 2. 渲染 ----------
        JPdfConfig config = new JPdfConfig();
        config.getLayoutConfig().setFlexLayout(true);
        config.getDoc().setMargins(Arrays.asList(48, 48, 48, 48));
        config.getFontConfig().setDefaultFontName("Noto Sans CJK SC");
        config.getHeaderConfig().setEnabled(true);
        config.getHeaderConfig().setText("××市第一人民医院　医疗住院收费票据　0012887766");
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
