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
 * 单据 172：医疗费用明细清单 / 药品耗材清单（medical 领域第 3 项）。
 *
 * <p>本类为独立可运行 Demo：自带 {@code main} 方法、不依赖任何公共工具类或实体类，
 * 数据以 key-value 形式通过 {@link JQuickPdfFactory#bindAll(Map)} 注入，
 * 版式全部落在模板文件 {@code demo/biz/medical/172_medical_expense_detail.txt} 中。</p>
 *
 * <p>视觉主题「天青·疏朗·典式」：主色 #1B7A6E、辅色 #912766、浅底 #F0F7F6；
 * 版式为 classic 抬头 + grid2 信息区 + airy 明细表 + right 签章区 + right 落款，
 * 与同领域其它单据互不重样。</p>
 *
 * <p>医疗费用明细清单 / 药品耗材清单：结算期间与患者病案号、费用总额与统筹支付，</p>
 * <p>逐项药品耗材（规格、数量、单价、金额、医保类别）与合计、药剂科与医保办核对栏。</p>
 *
 * <p>运行后输出：{@code D:/test/pdf/172_medical_expense_detail.pdf}。</p>
 *
 * @author Martin
 * @version 1.0.0
 */
public class JQuickBiz172MedicalExpenseDetail {

    private static final String PDF_DIR = "D:/test/pdf/";
    private static final String OUT_FILE = PDF_DIR + "172_medical_expense_detail.pdf";
    private static final String TEMPLATE = "demo/biz/medical/172_medical_expense_detail.txt";

    /** 模板固定明细行数，不足时补空行，避免占位符原样输出。 */
    private static final int ITEM_ROWS = 8;

    public static void main(String[] args) throws IOException {
        new File(PDF_DIR).mkdirs();

        // ---------- 1. 数据（无实体类，直接 bind）----------
        String[][] items = {
                {"1", "阿司匹林肠溶片", "100mg × 30 片", "2 盒", "18.60", "37.20", "甲类"},
                {"2", "硫酸氢氯吡格雷片", "75mg × 7 片", "4 盒", "86.50", "346.00", "乙类"},
                {"3", "阿托伐他汀钙片", "20mg × 7 片", "4 盒", "58.80", "235.20", "乙类"},
                {"4", "硝酸甘油注射液", "5mg / 支", "3 支", "26.40", "79.20", "甲类"},
                {"5", "药物洗脱冠脉支架", "2.75 × 18mm", "1 个", "8600.00", "8600.00", "乙类"},
                {"6", "造影剂（碘克沙醇）", "100ml / 瓶", "1 瓶", "386.00", "386.00", "乙类"},
                {"7", "一次性使用导管鞘", "6F", "1 套", "1280.00", "1280.00", "乙类"},
                {"8", "医用敷料及耗材", "—", "1 批", "486.00", "486.00", "甲类"}
        };

        Map<String, Object> data = new LinkedHashMap<String, Object>();
        data.put("listNo", "MED-20261027-0331");
        data.put("period", "2026年10月18日 — 2026年10月27日");
        data.put("patientName", "崔行知 / ZY-20261018-008812");
        data.put("hospitalName", "××市第一人民医院 / 心血管内科");
        data.put("insuranceType", "城镇职工基本医疗保险");
        data.put("totalFee", "￥17,042.50");
        data.put("fundPay", "￥12,270.60");
        data.put("selfPay", "￥4,771.90");
        data.put("pageInfo", "共 2 页，第 1 页");
        data.put("printInfo", "2026年10月27日 / 沈亦舟");
        data.put("remark", "本清单为住院费用的组成部分，与医疗收费票据、医保结算单金额勾稽一致；乙类药品须先自付一定比例后纳入统筹。");
        data.put("preparer", "沈亦舟");
        data.put("pharmacist", "邵怀信");
        data.put("insuranceOfficer", "柳文渊");

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

        data.put("totalLine", "药品耗材金额合计（大写）" + toChineseAmount(itemTotal) + "　　（小写）￥" + itemTotal.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString());

        // ---------- 2. 渲染 ----------
        JPdfConfig config = new JPdfConfig();
        config.getLayoutConfig().setFlexLayout(true);
        config.getDoc().setMargins(Arrays.asList(36, 36, 36, 36));
        config.getFontConfig().setDefaultFontName("Noto Sans CJK SC");
        config.getHeaderConfig().setEnabled(true);
        config.getHeaderConfig().setText("××市第一人民医院　医疗费用明细清单　MED-20261027-0331");
        config.getHeaderConfig().setFontSize(8f);
        config.getHeaderConfig().setHeight(32);
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
