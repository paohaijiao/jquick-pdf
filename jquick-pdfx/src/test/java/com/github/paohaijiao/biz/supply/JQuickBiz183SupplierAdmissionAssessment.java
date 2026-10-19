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
package com.github.paohaijiao.biz.supply;

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
 * 单据 183：供应商准入资料 / 履约考核清单（supply 领域第 4 项）。
 *
 * <p>本类为独立可运行 Demo：自带 {@code main} 方法、不依赖任何公共工具类或实体类，
 * 数据以 key-value 形式通过 {@link JQuickPdfFactory#bindAll(Map)} 注入，
 * 版式全部落在模板文件 {@code demo/biz/supply/183_supplier_admission_assessment.txt} 中。</p>
 *
 * <p>视觉主题「秋香·密排·通栏」：主色 #5F8A1E、辅色 #272E91、浅底 #F4F7F0；
 * 版式为 banner 抬头 + kv 信息区 + compact 明细表 + two 签章区 + right 落款，
 * 与同领域其它单据互不重样。</p>
 *
 * <p>供应商准入资料 / 履约考核清单：准入类别与供应品类、考核方式与期间，</p>
 * <p>六维考核指标（目标值、实际值、得分、等级）与合计得分、考核结论与三方签字栏。</p>
 *
 * <p>运行后输出：{@code D:/test/pdf/183_supplier_admission_assessment.pdf}。</p>
 *
 * @author Martin
 * @version 1.0.0
 */
public class JQuickBiz183SupplierAdmissionAssessment {

    private static final String PDF_DIR = "D:/test/pdf/";
    private static final String OUT_FILE = PDF_DIR + "183_supplier_admission_assessment.pdf";
    private static final String TEMPLATE = "demo/biz/supply/183_supplier_admission_assessment.txt";

    /** 模板固定明细行数，不足时补空行，避免占位符原样输出。 */
    private static final int ITEM_ROWS = 6;

    public static void main(String[] args) throws IOException {
        new File(PDF_DIR).mkdirs();

        // ---------- 1. 数据（无实体类，直接 bind）----------
        String[][] items = {
                {"1", "质量表现", "来料检验批次合格率", "≥ 98%", "99.10%", "23.0", "A"},
                {"2", "交付表现", "准时交货率", "≥ 95%", "96.80%", "22.5", "A"},
                {"3", "成本控制", "年度降价达成率", "≥ 3%", "3.60%", "13.0", "A"},
                {"4", "服务能力", "异常响应及时率（24h）", "≥ 90%", "92.30%", "12.0", "A"},
                {"5", "技术能力", "技术支持与协同开发", "良好", "良好，参与 2 项联合开发", "10.5", "A"},
                {"6", "合规与信用", "无重大违约、无失信记录", "无", "无违约、无失信记录", "7.6", "A"}
        };

        Map<String, Object> data = new LinkedHashMap<String, Object>();
        data.put("admissionNo", "SUP-20261015-00218");
        data.put("period", "2026年01月01日 — 2026年09月30日");
        data.put("buyerName", "××智能装备制造有限公司 / 采购部、质量部");
        data.put("supplierName", "××精密机械制造有限公司");
        data.put("creditCode", "91330100MA2G8N32WF");
        data.put("admissionType", "战略供应商 / A 级");
        data.put("supplyCategory", "伺服电机、精密减速机、运动控制卡");
        data.put("assessWay", "季度评分 + 年度综合评定（百分制）");
        data.put("scoreInfo", "88.6 分 / A 级（优秀）");
        data.put("conclusion", "继续合作，建议纳入战略供应商名录");
        data.put("assessorInfo", "路承志 / 闻仲平");
        data.put("remark", "考核等级为 C 级的供应商须限期整改，连续两次 C 级启动退出程序；A 级供应商可享受优先询价与缩短账期激励。");
        data.put("assessor", "路承志");
        data.put("purchaseHead", "闻仲平");
        data.put("qcHead", "柳文渊");

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

        data.put("grade", "A");

        data.put("totalLine", "供应商履约考核合计得分：" + itemTotal.setScale(1, BigDecimal.ROUND_HALF_UP).toPlainString() + " 分（满分 100 分），综合评定等级：A 级（优秀）");

        // ---------- 2. 渲染 ----------
        JPdfConfig config = new JPdfConfig();
        config.getLayoutConfig().setFlexLayout(true);
        config.getDoc().setMargins(Arrays.asList(48, 48, 48, 48));
        config.getFontConfig().setDefaultFontName("Noto Sans CJK SC");
        config.getHeaderConfig().setEnabled(true);
        config.getHeaderConfig().setText("××智能装备制造有限公司　供应商准入 / 履约考核清单　SUP-20261015-00218");
        config.getHeaderConfig().setFontSize(7.5f);
        config.getHeaderConfig().setHeight(34);
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
