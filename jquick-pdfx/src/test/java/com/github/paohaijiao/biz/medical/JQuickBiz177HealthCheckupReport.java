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
 * 单据 177：健康体检筛查报告单 / 体检项目清单（medical 领域第 8 项）。
 *
 * <p>本类为独立可运行 Demo：自带 {@code main} 方法、不依赖任何公共工具类或实体类，
 * 数据以 key-value 形式通过 {@link JQuickPdfFactory#bindAll(Map)} 注入，
 * 版式全部落在模板文件 {@code demo/biz/medical/177_health_checkup_report.txt} 中。</p>
 *
 * <p>视觉主题「靛蓝·疏朗·通栏」：主色 #15577A、辅色 #912734、浅底 #F0F5F7；
 * 版式为 banner 抬头 + chip 信息区 + airy 明细表 + card 签章区 + plain 落款，
 * 与同领域其它单据互不重样。</p>
 *
 * <p>健康体检筛查报告单 / 体检项目清单：体检编号与机构、体检人信息，</p>
 * <p>逐项体检结果（结果、单位、参考范围、提示、科室）与健康指导建议、主检医师签字栏。</p>
 *
 * <p>运行后输出：{@code D:/test/pdf/177_health_checkup_report.pdf}。</p>
 *
 * @author Martin
 * @version 1.0.0
 */
public class JQuickBiz177HealthCheckupReport {

    private static final String PDF_DIR = "D:/test/pdf/";
    private static final String OUT_FILE = PDF_DIR + "177_health_checkup_report.pdf";
    private static final String TEMPLATE = "demo/biz/medical/177_health_checkup_report.txt";

    /** 模板固定明细行数，不足时补空行，避免占位符原样输出。 */
    private static final int ITEM_ROWS = 10;

    public static void main(String[] args) throws IOException {
        new File(PDF_DIR).mkdirs();

        // ---------- 1. 数据（无实体类，直接 bind）----------
        String[][] items = {
                {"1", "身高 / 体重", "172 / 82", "cm / kg", "BMI 18.5 — 23.9", "超重", "一般检查"},
                {"2", "体质指数（BMI）", "27.7", "kg/m²", "18.5 — 23.9", "偏高", "一般检查"},
                {"3", "血压", "138/88", "mmHg", "90-139 / 60-89", "正常高值", "一般检查"},
                {"4", "腹部超声", "肝区回声增强，肝内管道欠清", "—", "—", "脂肪肝", "超声科"},
                {"5", "总胆固醇（TC）", "6.12", "mmol/L", "< 5.18", "偏高", "检验科"},
                {"6", "甘油三酯（TG）", "2.86", "mmol/L", "< 1.70", "偏高", "检验科"},
                {"7", "空腹血糖（FPG）", "5.62", "mmol/L", "3.90 — 6.10", "正常", "检验科"},
                {"8", "尿酸（UA）", "458.00", "μmol/L", "208 — 428", "偏高", "检验科"},
                {"9", "胸部 DR 正位", "双肺纹理清晰，心影大小正常", "—", "—", "正常", "放射科"},
                {"10", "心电图", "窦性心律，大致正常心电图", "—", "—", "正常", "功能科"}
        };

        Map<String, Object> data = new LinkedHashMap<String, Object>();
        data.put("checkupNo", "PE-20261029-003316");
        data.put("checkupDate", "2026年10月29日");
        data.put("orgName", "××健康管理中心 / 单位团体体检（套餐 C）");
        data.put("personInfo", "路承志 / 男 / 38 岁");
        data.put("idCard", "3201×××××××××××××× / EMP-2077");
        data.put("companyInfo", "××智能装备制造有限公司 / 生产制造部");
        data.put("itemCount", "46 项");
        data.put("chiefDoctor", "闻仲平 / 柳文渊");
        data.put("conclusion", "体重超重、脂肪肝（轻度）、血脂异常，建议生活方式干预");
        data.put("issueDate", "2026年11月02日");
        data.put("advice", "1. 控制总热量摄入，减少精制碳水与油炸食品，BMI 目标降至 24 以下；2. 每周中等强度有氧运动不少于 150 分钟；3. 限制嘌呤摄入，戒酒，3 个月后复查尿酸与血脂；4. 血压处于正常高值，建议家庭自测血压并记录。");
        data.put("remark", "体检结果受饮食、运动、作息等因素影响，异常项目建议在专科门诊复查确认。");
        data.put("auditor", "柳文渊");
        data.put("orgSign", "××健康管理中心");

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
        config.getDoc().setMargins(Arrays.asList(40, 40, 40, 40));
        config.getFontConfig().setDefaultFontName("Noto Sans CJK SC");
        config.getHeaderConfig().setEnabled(true);
        config.getHeaderConfig().setText("××健康管理中心　健康体检报告　PE-20261029-003316");
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
}
