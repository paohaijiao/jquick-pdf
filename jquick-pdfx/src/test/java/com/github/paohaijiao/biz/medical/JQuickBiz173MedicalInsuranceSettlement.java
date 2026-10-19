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
 * 单据 173：医疗保险结算单 / 异地就医结算回执（medical 领域第 4 项）。
 *
 * <p>本类为独立可运行 Demo：自带 {@code main} 方法、不依赖任何公共工具类或实体类，
 * 数据以 key-value 形式通过 {@link JQuickPdfFactory#bindAll(Map)} 注入，
 * 版式全部落在模板文件 {@code demo/biz/medical/173_medical_insurance_settlement.txt} 中。</p>
 *
 * <p>视觉主题「天青·密排·竖栏」：主色 #2E876F、辅色 #912775、浅底 #F1F6F5；
 * 版式为 side 抬头 + chip 信息区 + compact 明细表 + card 签章区 + plain 落款，
 * 与同领域其它单据互不重样。</p>
 *
 * <p>医疗保险结算单 / 异地就医结算回执：参保地与就医地、就医类型与备案信息，</p>
 * <p>逐类费用（发生额、统筹支付、账户支付、自付）与结算结果、医保经办与参保人签收栏。</p>
 *
 * <p>运行后输出：{@code D:/test/pdf/173_medical_insurance_settlement.pdf}。</p>
 *
 * @author Martin
 * @version 1.0.0
 */
public class JQuickBiz173MedicalInsuranceSettlement {

    private static final String PDF_DIR = "D:/test/pdf/";
    private static final String OUT_FILE = PDF_DIR + "173_medical_insurance_settlement.pdf";
    private static final String TEMPLATE = "demo/biz/medical/173_medical_insurance_settlement.txt";

    /** 模板固定明细行数，不足时补空行，避免占位符原样输出。 */
    private static final int ITEM_ROWS = 6;

    public static void main(String[] args) throws IOException {
        new File(PDF_DIR).mkdirs();

        // ---------- 1. 数据（无实体类，直接 bind）----------
        String[][] items = {
                {"1", "床位费", "540.00", "540.00", "0.00", "0.00", "全额统筹"},
                {"2", "护理与诊查费", "774.00", "774.00", "0.00", "0.00", "全额统筹"},
                {"3", "手术与治疗费", "4286.00", "3086.00", "0.00", "1200.00", "乙类自付"},
                {"4", "药品费", "3257.70", "2345.60", "0.00", "912.10", "乙类自付"},
                {"5", "检查化验费", "1648.00", "1648.00", "0.00", "0.00", "全额统筹"},
                {"6", "医用耗材费", "8636.80", "3876.00", "0.00", "4760.80", "乙类自付"}
        };

        Map<String, Object> data = new LinkedHashMap<String, Object>();
        data.put("settleNo", "MIS-20261027-009311");
        data.put("settleDate", "2026年10月27日");
        data.put("insuredPlace", "××市 / 城镇职工基本医疗保险");
        data.put("treatPlace", "××市 / ××市第一人民医院（三级甲等）");
        data.put("patientInfo", "崔行知 / 3201××××××××××××××");
        data.put("cardInfo", "3201×××××××× / 正常参保");
        data.put("treatType", "异地转诊住院（已办理备案）");
        data.put("recordInfo", "BA-20261015-0071 / 2026-10-15");
        data.put("dateInfo", "2026年10月18日 / 2026年10月27日");
        data.put("settleWay", "异地就医直接结算（一站式）");
        data.put("insuranceOrg", "××市医疗保障局××分局");
        data.put("remark", "异地就医直接结算执行「就医地目录、参保地政策」；未办理备案的，报销比例按规定降低。");
        data.put("operator", "柳文渊");
        data.put("reviewer", "闻仲平");
        data.put("patientSign", "崔行知");

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
            itemTotal = itemTotal.add(new BigDecimal(items[i][2]));
            data.put("itemUnit" + n, items[i][2]);
            data.put("itemQty" + n, items[i][3]);
            data.put("itemPrice" + n, items[i][4]);
            data.put("itemAmount" + n, items[i][5]);
            data.put("itemTax" + n, items[i][6]);
        }

        data.put("totalAmount", itemTotal.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString());

        data.put("totalFee", itemTotal.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString());

        data.put("fundPay", new BigDecimal("12269.60").setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString());

        data.put("selfPay", new BigDecimal("6872.90").setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString());

        data.put("totalLine", "医疗费用总额 ￥" + itemTotal.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "　　统筹支付 ￥12269.60　　个人自付 ￥6872.90　　（其中个人账户支付 ￥0.00）");

        // ---------- 2. 渲染 ----------
        JPdfConfig config = new JPdfConfig();
        config.getLayoutConfig().setFlexLayout(true);
        config.getDoc().setMargins(Arrays.asList(40, 40, 40, 40));
        config.getFontConfig().setDefaultFontName("Noto Sans CJK SC");
        config.getHeaderConfig().setEnabled(true);
        config.getHeaderConfig().setText("××市医疗保障局　异地就医医保结算单　MIS-20261027-009311");
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
