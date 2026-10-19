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
import java.nio.file.Files;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 单据 174：检查检验报告单 / 化验回执单（medical 领域第 5 项）。
 *
 * <p>本类为独立可运行 Demo：自带 {@code main} 方法、不依赖任何公共工具类或实体类，
 * 数据以 key-value 形式通过 {@link JQuickPdfFactory#bindAll(Map)} 注入，
 * 版式全部落在模板文件 {@code demo/biz/medical/174_lab_test_report.txt} 中。</p>
 *
 * <p>视觉主题「天青·厚框·票式」：主色 #1BBAB7、辅色 #91275B、浅底 #F0F7F7；
 * 版式为 ticket 抬头 + strip 信息区 + solid 明细表 + two 签章区 + right 落款，
 * 与同领域其它单据互不重样。</p>
 *
 * <p>检查检验报告单 / 化验回执单：报告编号与标本信息、检验项目组，</p>
 * <p>逐项检验结果（结果、单位、参考范围、提示、方法）与检验提示、检验 / 审核签字栏。</p>
 *
 * <p>运行后输出：{@code D:/test/pdf/174_lab_test_report.pdf}。</p>
 *
 * @author Martin
 * @version 1.0.0
 */
public class JQuickBiz174LabTestReport {

    private static final String PDF_DIR = "D:/test/pdf/";
    private static final String OUT_FILE = PDF_DIR + "174_lab_test_report.pdf";
    private static final String TEMPLATE = "demo/biz/medical/174_lab_test_report.txt";

    /** 模板固定明细行数，不足时补空行，避免占位符原样输出。 */
    private static final int ITEM_ROWS = 8;

    public static void main(String[] args) throws IOException {
        new File(PDF_DIR).mkdirs();

        // ---------- 1. 数据（无实体类，直接 bind）----------
        String[][] items = {
                {"1", "肌钙蛋白 I（cTnI）", "0.02", "ng/mL", "< 0.04", "正常", "化学发光法"},
                {"2", "肌酸激酶同工酶（CK-MB）", "18.60", "U/L", "0 — 25", "正常", "速率法"},
                {"3", "乳酸脱氢酶（LDH）", "186.00", "U/L", "120 — 250", "正常", "速率法"},
                {"4", "总胆固醇（TC）", "5.86", "mmol/L", "< 5.18", "偏高", "酶法"},
                {"5", "低密度脂蛋白（LDL-C）", "3.92", "mmol/L", "< 3.37", "偏高", "酶法"},
                {"6", "甘油三酯（TG）", "2.38", "mmol/L", "< 1.70", "偏高", "酶法"},
                {"7", "空腹血糖（FPG）", "6.12", "mmol/L", "3.90 — 6.10", "偏高", "己糖激酶法"},
                {"8", "血红蛋白（Hb）", "148.00", "g/L", "130 — 175", "正常", "电阻抗法"}
        };

        Map<String, Object> data = new LinkedHashMap<String, Object>();
        data.put("reportNo", "LAB-20261019-007712");
        data.put("sampleDate", "2026年10月19日 / 2026年10月19日");
        data.put("labDept", "医学检验科 / 邵怀信");
        data.put("patientInfo", "崔行知 / 男 / 46 岁");
        data.put("recordInfo", "ZY-20261018-008812 / 12 床");
        data.put("sampleType", "静脉血清 / 合格");
        data.put("itemGroup", "心肌酶谱 + 血脂 + 血常规");
        data.put("applyInfo", "闻仲平 / 心血管内科");
        data.put("auditInfo", "柳文渊 / 2026-10-19 15:20");
        data.put("criticalNote", "无危急值");
        data.put("hospitalName", "××市第一人民医院");
        data.put("interpretation", "血脂四项中 TC、LDL-C、TG 及空腹血糖轻度升高，建议低脂饮食、规律运动，1 个月后复查血脂与血糖。");
        data.put("remark", "本报告仅对所检标本负责，检验结果须结合临床综合判断；如与临床不符请及时与检验科联系复检。");
        data.put("tester", "邵怀信");
        data.put("auditor", "柳文渊");
        data.put("issuer", "沈亦舟");

        // 明细逐格展开
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
            data.put("itemTax" + n, items[i][6]);
        }

        // ---------- 2. 渲染 ----------
        JPdfConfig config = new JPdfConfig();
        config.getLayoutConfig().setFlexLayout(true);
        config.getDoc().setMargins(Arrays.asList(44, 44, 44, 44));
        config.getFontConfig().setDefaultFontName("Noto Sans CJK SC");
        config.getHeaderConfig().setEnabled(true);
        config.getHeaderConfig().setText("××市第一人民医院　检验报告单　LAB-20261019-007712");
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
}
