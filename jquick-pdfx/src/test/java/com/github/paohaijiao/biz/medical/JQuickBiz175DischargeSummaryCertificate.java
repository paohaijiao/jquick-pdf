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
 * 单据 175：出院小结 / 疾病诊断证明书（medical 领域第 6 项）。
 *
 * <p>本类为独立可运行 Demo：自带 {@code main} 方法、不依赖任何公共工具类或实体类，
 * 数据以 key-value 形式通过 {@link JQuickPdfFactory#bindAll(Map)} 注入，
 * 版式全部落在模板文件 {@code demo/biz/medical/175_discharge_summary_certificate.txt} 中。</p>
 *
 * <p>视觉主题「靛蓝·斑马·下线」：主色 #207182、辅色 #912746、浅底 #F0F6F7；
 * 版式为 underline 抬头 + kv 信息区 + striped 明细表 + three 签章区 + plain 落款，
 * 与同领域其它单据互不重样。</p>
 *
 * <p>出院小结 / 疾病诊断证明书：入出院日期与诊断、手术操作名称、出院时情况，</p>
 * <p>住院期间主要诊疗与用药明细、出院医嘱与主治医师 / 科主任签字栏。</p>
 *
 * <p>运行后输出：{@code D:/test/pdf/175_discharge_summary_certificate.pdf}。</p>
 *
 * @author Martin
 * @version 1.0.0
 */
public class JQuickBiz175DischargeSummaryCertificate {

    private static final String PDF_DIR = "D:/test/pdf/";
    private static final String OUT_FILE = PDF_DIR + "175_discharge_summary_certificate.pdf";
    private static final String TEMPLATE = "demo/biz/medical/175_discharge_summary_certificate.txt";

    /** 模板固定明细行数，不足时补空行，避免占位符原样输出。 */
    private static final int ITEM_ROWS = 6;

    public static void main(String[] args) throws IOException {
        new File(PDF_DIR).mkdirs();

        // ---------- 1. 数据（无实体类，直接 bind）----------
        String[][] items = {
                {"1", "冠状动脉造影术", "2026-10-23", "经桡动脉穿刺", "前降支狭窄 85%", "明确病变"},
                {"2", "支架植入术", "2026-10-23", "2.75 × 18mm 药物洗脱支架", "狭窄解除，血流恢复", "成功"},
                {"3", "阿司匹林肠溶片", "2026-10-23 起", "100mg 每日一次，长期", "抗血小板治疗有效", "出院带药"},
                {"4", "硫酸氢氯吡格雷片", "2026-10-23 起", "75mg 每日一次，服用 12 个月", "双抗治疗中", "出院带药"},
                {"5", "阿托伐他汀钙片", "2026-10-19 起", "20mg 每晚一次", "血脂下降，LDL-C 2.86", "出院带药"},
                {"6", "苯磺酸氨氯地平片", "2026-10-19 起", "5mg 每日一次", "血压控制于 128/82 mmHg", "出院带药"}
        };

        Map<String, Object> data = new LinkedHashMap<String, Object>();
        data.put("certNo", "DIS-20261027-008812");
        data.put("admitDischarge", "2026年10月18日 / 2026年10月27日");
        data.put("stayDays", "9 天");
        data.put("hospitalName", "××市第一人民医院 / 心血管内科");
        data.put("patientInfo", "崔行知 / 男 / 46 岁");
        data.put("idInfo", "3201×××××××××××××× / ZY-20261018-008812");
        data.put("admissionDiagnosis", "冠状动脉粥样硬化性心脏病、不稳定型心绞痛");
        data.put("dischargeDiagnosis", "冠状动脉粥样硬化性心脏病、不稳定型心绞痛、高血压 2 级（中危）、血脂异常");
        data.put("operationName", "冠状动脉造影术 + 前降支药物洗脱支架植入术");
        data.put("dischargeStatus", "病情稳定，胸痛症状消失，切口愈合良好");
        data.put("doctorInfo", "闻仲平 / 路承志");
        data.put("dischargeDate", "2026年10月27日");
        data.put("dischargeAdvice", "1. 低盐低脂饮食，戒烟限酒，规律作息；2. 双联抗血小板治疗至少 12 个月，切勿自行停药；3. 术后 1、3、6 个月门诊复查心电图、血脂与肝肾功能；4. 如再发胸痛、胸闷立即急诊就医。");
        data.put("remark", "本证明书仅证明患者在本院的诊疗情况，不作伤残鉴定、司法鉴定及保险理赔的唯一依据。");
        data.put("doctor", "闻仲平");
        data.put("deptHead", "路承志");
        data.put("hospitalSign", "××市第一人民医院");

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
                continue;
            }
            data.put("itemName" + n, items[i][0]);
            data.put("itemSpec" + n, items[i][1]);
            data.put("itemUnit" + n, items[i][2]);
            data.put("itemQty" + n, items[i][3]);
            data.put("itemPrice" + n, items[i][4]);
            data.put("itemAmount" + n, items[i][5]);
        }

        // ---------- 2. 渲染 ----------
        JPdfConfig config = new JPdfConfig();
        config.getLayoutConfig().setFlexLayout(true);
        config.getDoc().setMargins(Arrays.asList(48, 48, 48, 48));
        config.getFontConfig().setDefaultFontName("Noto Sans CJK SC");
        config.getHeaderConfig().setEnabled(true);
        config.getHeaderConfig().setText("××市第一人民医院　出院小结 / 诊断证明书　DIS-20261027-008812");
        config.getHeaderConfig().setFontSize(8f);
        config.getHeaderConfig().setHeight(34);
        config.getFooterConfig().setEnabled(true);
        config.getFooterConfig().setFontSize(8);
        config.getFooterConfig().setPageNumberFormat("— %d —");

        JQuickPdfFactory factory = new JQuickPdfFactory(config);
        factory.bindAll(data);
        byte[] pdf = factory.executeResource(TEMPLATE);
        Files.write(new File(OUT_FILE).toPath(), pdf);
        System.out.println("已生成：" + OUT_FILE + "（" + pdf.length + " 字节）");
    }
}
