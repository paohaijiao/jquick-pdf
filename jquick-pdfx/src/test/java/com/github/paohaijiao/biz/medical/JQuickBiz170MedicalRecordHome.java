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
 * 单据 170：门诊病历 / 住院病案首页（medical 领域第 1 项）。
 *
 * <p>本类为独立可运行 Demo：自带 {@code main} 方法、不依赖任何公共工具类或实体类，
 * 数据以 key-value 形式通过 {@link JQuickPdfFactory#bindAll(Map)} 注入，
 * 版式全部落在模板文件 {@code demo/biz/medical/170_medical_record_home.txt} 中。</p>
 *
 * <p>视觉主题「青瓷·斑马·公文」：主色 #278159、辅色 #912788、浅底 #F1F6F4；
 * 版式为 doc 抬头 + strip 信息区 + striped 明细表 + two 签章区 + right 落款，
 * 与同领域其它单据互不重样。</p>
 *
 * <p>门诊病历 / 住院病案首页：病案号与医疗机构、患者与医保信息、入出院诊断，</p>
 * <p>逐日诊疗经过记录与医嘱处置、病案质控与科主任签字栏。</p>
 *
 * <p>运行后输出：{@code D:/test/pdf/170_medical_record_home.pdf}。</p>
 *
 * @author Martin
 * @version 1.0.0
 */
public class JQuickBiz170MedicalRecordHome {

    private static final String PDF_DIR = "D:/test/pdf/";
    private static final String OUT_FILE = PDF_DIR + "170_medical_record_home.pdf";
    private static final String TEMPLATE = "demo/biz/medical/170_medical_record_home.txt";

    /** 模板固定明细行数，不足时补空行，避免占位符原样输出。 */
    private static final int ITEM_ROWS = 6;

    public static void main(String[] args) throws IOException {
        new File(PDF_DIR).mkdirs();

        // ---------- 1. 数据（无实体类，直接 bind）----------
        String[][] items = {
                {"2026-10-18", "入院记录", "反复胸痛 3 天，活动后加重，伴胸闷", "完善心电图、心肌酶谱、冠脉 CTA", "闻仲平"},
                {"2026-10-19", "病程记录", "心肌酶谱正常，心电图示 ST-T 改变", "给予抗血小板、扩冠、降压治疗", "闻仲平"},
                {"2026-10-21", "会诊记录", "心内科会诊：建议行冠状动脉造影", "术前评估，签署知情同意书", "柳文渊"},
                {"2026-10-23", "手术记录", "行冠状动脉造影 + 支架植入术（前降支）", "术后安返病房，心电监护", "闻仲平"},
                {"2026-10-25", "病程记录", "术后恢复良好，胸痛症状缓解", "继续双抗治疗，康复指导", "闻仲平"},
                {"2026-10-27", "出院记录", "病情稳定，切口愈合良好，予出院", "出院带药，1 个月后门诊复查", "闻仲平"}
        };

        Map<String, Object> data = new LinkedHashMap<String, Object>();
        data.put("recordNo", "ZY-20261018-008812");
        data.put("visitDate", "2026年10月18日");
        data.put("hospitalName", "××市第一人民医院");
        data.put("hospitalLevel", "三级甲等 / H32010088");
        data.put("patientInfo", "崔行知 / 男 / 46 岁");
        data.put("idCard", "3201×××××××××××××× / 138××××8877");
        data.put("insuranceInfo", "城镇职工基本医疗保险 / 3201××××××××");
        data.put("deptInfo", "心血管内科 / 12 床");
        data.put("admissionDiagnosis", "冠状动脉粥样硬化性心脏病、不稳定型心绞痛");
        data.put("dischargeDiagnosis", "冠状动脉粥样硬化性心脏病、不稳定型心绞痛、高血压 2 级");
        data.put("stayDays", "9 天 / 病案共 6 页");
        data.put("doctorInfo", "闻仲平（主任医师）/ 邵怀信");
        data.put("qualityNote", "本病案首页信息完整，诊断依据充分，手术记录与医嘱一致，符合《病历书写基本规范》要求。");
        data.put("remark", "病案首页是医疗统计与医保结算的原始依据，诊断编码须使用 ICD-10，手术操作编码使用 ICD-9-CM-3。");
        data.put("doctor", "闻仲平");
        data.put("qcOfficer", "柳文渊");
        data.put("deptHead", "路承志");

        // 明细逐格展开
        for (int i = 0; i < ITEM_ROWS; i++) {
            int n = i + 1;
            if (i >= items.length) {
                data.put("itemName" + n, "—");
                data.put("itemSpec" + n, "—");
                data.put("itemUnit" + n, "—");
                data.put("itemQty" + n, "—");
                data.put("itemAmount" + n, "—");
                continue;
            }
            data.put("itemName" + n, items[i][0]);
            data.put("itemSpec" + n, items[i][1]);
            data.put("itemUnit" + n, items[i][2]);
            data.put("itemQty" + n, items[i][3]);
            data.put("itemAmount" + n, items[i][4]);
        }

        // ---------- 2. 渲染 ----------
        JPdfConfig config = new JPdfConfig();
        config.getLayoutConfig().setFlexLayout(true);
        config.getDoc().setMargins(Arrays.asList(44, 44, 44, 44));
        config.getFontConfig().setDefaultFontName("Noto Sans CJK SC");
        config.getHeaderConfig().setEnabled(true);
        config.getHeaderConfig().setText("××市第一人民医院　住院病案首页　ZY-20261018-008812");
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
}
