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
 * 单据 179：药品处方单 / 处方收费核对单（medical 领域第 10 项）。
 *
 * <p>本类为独立可运行 Demo：自带 {@code main} 方法、不依赖任何公共工具类或实体类，
 * 数据以 key-value 形式通过 {@link JQuickPdfFactory#bindAll(Map)} 注入，
 * 版式全部落在模板文件 {@code demo/biz/medical/179_prescription_charge_check.txt} 中。</p>
 *
 * <p>视觉主题「宝蓝·厚框·竖栏」：主色 #1351BC、辅色 #913827、浅底 #EFF2F8；
 * 版式为 side 抬头 + kv 信息区 + solid 明细表 + three 签章区 + right 落款，
 * 与同领域其它单据互不重样。</p>
 *
 * <p>药品处方单 / 处方收费核对单：处方编号与类型、临床诊断、医师与药师，</p>
 * <p>逐项处方药品（规格、数量、用法用量、金额）与四查十对核对结果、三方签字栏。</p>
 *
 * <p>运行后输出：{@code D:/test/pdf/179_prescription_charge_check.pdf}。</p>
 *
 * @author Martin
 * @version 1.0.0
 */
public class JQuickBiz179PrescriptionChargeCheck {

    private static final String PDF_DIR = "D:/test/pdf/";
    private static final String OUT_FILE = PDF_DIR + "179_prescription_charge_check.pdf";
    private static final String TEMPLATE = "demo/biz/medical/179_prescription_charge_check.txt";

    /** 模板固定明细行数，不足时补空行，避免占位符原样输出。 */
    private static final int ITEM_ROWS = 6;

    public static void main(String[] args) throws IOException {
        new File(PDF_DIR).mkdirs();

        // ---------- 1. 数据（无实体类，直接 bind）----------
        String[][] items = {
                {"1", "艾司奥美拉唑镁肠溶片", "20mg × 7 片", "4 盒", "每日一次，早餐前 20mg", "268.80", "乙类"},
                {"2", "阿莫西林胶囊", "0.5g × 24 粒", "2 盒", "每日两次，每次 1.0g", "86.40", "甲类"},
                {"3", "克拉霉素片", "0.25g × 12 片", "2 盒", "每日两次，每次 0.5g", "128.60", "乙类"},
                {"4", "枸橼酸铋钾颗粒", "110mg × 28 袋", "1 盒", "每日两次，每次 220mg", "68.50", "甲类"},
                {"5", "复方消化酶胶囊", "— × 20 粒", "1 盒", "每日三次，每次 2 粒", "96.00", "乙类"},
                {"6", "铝碳酸镁咀嚼片", "0.5g × 24 片", "1 盒", "餐后 1 小时嚼服 1 片", "32.80", "甲类"}
        };

        Map<String, Object> data = new LinkedHashMap<String, Object>();
        data.put("prescNo", "RX-20261031-009912");
        data.put("prescDate", "2026年10月31日");
        data.put("hospitalName", "××市第一人民医院 / 消化内科门诊");
        data.put("prescType", "普通处方（西药处方，白色）");
        data.put("patientInfo", "路承志 / 男 / 38 岁");
        data.put("clinicNo", "MZ-20261031-005518 / ZY-20261018-008812");
        data.put("diagnosis", "慢性胃炎（HP 阳性）、功能性消化不良");
        data.put("doctorInfo", "柳文渊（主治医师）/ 邵怀信（主管药师）");
        data.put("validDays", "开具当日有效，最多可延长至 3 天");
        data.put("checker", "沈亦舟");
        data.put("remark", "本处方为四联抗幽门螺杆菌疗法，疗程 14 天，用药期间禁酒并避免与牛奶同服；治疗结束 4 周后复查呼气试验。");
        data.put("doctor", "柳文渊");
        data.put("pharmacist", "邵怀信");

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

        data.put("totalLine", "处方金额合计（大写）" + toChineseAmount(itemTotal) + "　　（小写）￥" + itemTotal.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString());

        data.put("checkLine", "四查十对核对结果：处方合法性与用药适宜性均通过；其中医保统筹支付 ￥" + itemTotal.multiply(new BigDecimal("0.70")).setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "，个人自付 ￥" + itemTotal.multiply(new BigDecimal("0.30")).setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString());

        // ---------- 2. 渲染 ----------
        JPdfConfig config = new JPdfConfig();
        config.getLayoutConfig().setFlexLayout(true);
        config.getDoc().setMargins(Arrays.asList(48, 48, 48, 48));
        config.getFontConfig().setDefaultFontName("Noto Sans CJK SC");
        config.getHeaderConfig().setEnabled(true);
        config.getHeaderConfig().setText("××市第一人民医院　药品处方单 / 收费核对单　RX-20261031-009912");
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
