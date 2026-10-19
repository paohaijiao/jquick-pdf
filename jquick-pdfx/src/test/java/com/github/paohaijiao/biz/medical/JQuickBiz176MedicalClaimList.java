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
 * 单据 176：医疗费用报销申请单 / 保险理赔核算清单（medical 领域第 7 项）。
 *
 * <p>本类为独立可运行 Demo：自带 {@code main} 方法、不依赖任何公共工具类或实体类，
 * 数据以 key-value 形式通过 {@link JQuickPdfFactory#bindAll(Map)} 注入，
 * 版式全部落在模板文件 {@code demo/biz/medical/176_medical_claim_list.txt} 中。</p>
 *
 * <p>视觉主题「天青·素雅·公文」：主色 #348C8D、辅色 #912756、浅底 #F1F6F6；
 * 版式为 doc 抬头 + grid2 信息区 + light 明细表 + right 签章区 + table 落款，
 * 与同领域其它单据互不重样。</p>
 *
 * <p>医疗费用报销申请单 / 理赔核算清单：保单与险种、被保险人信息、出险诊断，</p>
 * <p>逐类费用核算（申请额、医保已付、免赔额、核定额）与核算结论、核赔签字栏。</p>
 *
 * <p>运行后输出：{@code D:/test/pdf/176_medical_claim_list.pdf}。</p>
 *
 * @author Martin
 * @version 1.0.0
 */
public class JQuickBiz176MedicalClaimList {

    private static final String PDF_DIR = "D:/test/pdf/";
    private static final String OUT_FILE = PDF_DIR + "176_medical_claim_list.pdf";
    private static final String TEMPLATE = "demo/biz/medical/176_medical_claim_list.txt";

    /** 模板固定明细行数，不足时补空行，避免占位符原样输出。 */
    private static final int ITEM_ROWS = 6;

    public static void main(String[] args) throws IOException {
        new File(PDF_DIR).mkdirs();

        // ---------- 1. 数据（无实体类，直接 bind）----------
        String[][] items = {
                {"1", "床位与护理费", "774.00", "774.00", "0.00", "0.00", "医保已付"},
                {"2", "手术与治疗费", "4286.00", "3086.00", "10000.00", "0.00", "未达免赔额"},
                {"3", "药品费", "3257.70", "2345.60", "912.10", "0.00", "计入免赔额"},
                {"4", "检查化验费", "1648.00", "1648.00", "0.00", "0.00", "医保已付"},
                {"5", "医用耗材费", "8636.80", "3876.00", "4760.80", "0.00", "计入免赔额"},
                {"6", "合计", "18602.50", "11729.60", "6872.90", "0.00", "待审核"}
        };

        Map<String, Object> data = new LinkedHashMap<String, Object>();
        data.put("claimNo", "CLM-20261028-007712");
        data.put("applyDate", "2026年10月28日");
        data.put("insurerName", "××人寿保险股份有限公司××分公司");
        data.put("policyNo", "POL-2025101000881234 / 百万医疗保险");
        data.put("insuredInfo", "崔行知 / 崔行知（本人）");
        data.put("idCard", "3201×××××××××××××× / 138××××8877");
        data.put("hospitalInfo", "××市第一人民医院 / 心血管内科");
        data.put("accidentInfo", "冠状动脉粥样硬化性心脏病（住院治疗）");
        data.put("claimAmount", "￥6,872.90");
        data.put("bankInfo", "中国工商银行××支行　6222 ×××× ×××× 3312");
        data.put("remark", "百万医疗保险通常设 1 万元年度免赔额，社保统筹支付部分不计入免赔额，个人自付部分可累计。");
        data.put("applicantSign", "崔行知");
        data.put("operator", "邵怀信");
        data.put("claimOfficer", "柳文渊");

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

        data.put("totalFee", new BigDecimal("18602.50").setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString());

        data.put("fundPaid", new BigDecimal("11729.60").setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString());

        data.put("deductible", new BigDecimal("6872.90").setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString());

        data.put("totalLine", "本次核定赔付金额合计 ￥0.00（未达年度免赔额 10000.00 元，已计入金额 6872.90 元可累计至本年度后续理赔）");

        // ---------- 2. 渲染 ----------
        JPdfConfig config = new JPdfConfig();
        config.getLayoutConfig().setFlexLayout(true);
        config.getDoc().setMargins(Arrays.asList(36, 36, 36, 36));
        config.getFontConfig().setDefaultFontName("Noto Sans CJK SC");
        config.getHeaderConfig().setEnabled(true);
        config.getHeaderConfig().setText("××人寿保险××分公司　医疗费用理赔核算清单　CLM-20261028-007712");
        config.getHeaderConfig().setFontSize(8.5f);
        config.getHeaderConfig().setHeight(28);
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
