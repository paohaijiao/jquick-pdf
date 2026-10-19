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
 * 单据 178：门诊分诊单 / 住院床位预约回执（medical 领域第 9 项）。
 *
 * <p>本类为独立可运行 Demo：自带 {@code main} 方法、不依赖任何公共工具类或实体类，
 * 数据以 key-value 形式通过 {@link JQuickPdfFactory#bindAll(Map)} 注入，
 * 版式全部落在模板文件 {@code demo/biz/medical/178_triage_bed_reservation.txt} 中。</p>
 *
 * <p>视觉主题「靛蓝·密排·典式」：主色 #266689、辅色 #912732、浅底 #F1F4F6；
 * 版式为 classic 抬头 + strip 信息区 + compact 明细表 + split 签章区 + table 落款，
 * 与同领域其它单据互不重样。</p>
 *
 * <p>门诊分诊单 / 住院床位预约回执：主诉症状与生命体征、分诊科室与分级，</p>
 * <p>分诊与候诊候床逐项明细、分诊结论与护士 / 医师 / 患者签收栏。</p>
 *
 * <p>运行后输出：{@code D:/test/pdf/178_triage_bed_reservation.pdf}。</p>
 *
 * @author Martin
 * @version 1.0.0
 */
public class JQuickBiz178TriageBedReservation {

    private static final String PDF_DIR = "D:/test/pdf/";
    private static final String OUT_FILE = PDF_DIR + "178_triage_bed_reservation.pdf";
    private static final String TEMPLATE = "demo/biz/medical/178_triage_bed_reservation.txt";

    /** 模板固定明细行数，不足时补空行，避免占位符原样输出。 */
    private static final int ITEM_ROWS = 6;

    public static void main(String[] args) throws IOException {
        new File(PDF_DIR).mkdirs();

        // ---------- 1. 数据（无实体类，直接 bind）----------
        String[][] items = {
                {"1", "体温与生命体征测量", "T 36.7℃，血压正常高值", "5 分钟", "已完成", "监测血压"},
                {"2", "症状问诊与分级评估", "上腹痛伴反酸，分级为三级（普通）", "8 分钟", "已完成", "内科接诊"},
                {"3", "消化内科门诊候诊", "排队号 A-032，预计 30 分钟内", "30 分钟", "候诊中", "按叫号就诊"},
                {"4", "胃镜检查预约", "预约 2026-11-04 上午，空腹", "5 个工作日", "已预约", "检查前禁食"},
                {"5", "住院床位预约登记", "普通双人病房，候床第 3 位", "6 天", "候床中", "电话通知"},
                {"6", "术前 / 入院宣教", "入院流程与注意事项告知", "即时", "已宣教", "签署知情同意"}
        };

        Map<String, Object> data = new LinkedHashMap<String, Object>();
        data.put("triageNo", "TRI-20261030-005518");
        data.put("registerDate", "2026年10月30日");
        data.put("hospitalName", "××市第一人民医院（总院）");
        data.put("patientInfo", "路承志 / 男 / 38 岁");
        data.put("idCard", "3201×××××××××××××× / 139××××5566");
        data.put("insuranceInfo", "城镇职工基本医疗保险 / 3201××××××××");
        data.put("chiefComplaint", "上腹隐痛伴反酸 2 周，餐后加重");
        data.put("triageDept", "消化内科 / 三级（普通，30 分钟内接诊）");
        data.put("vitalSigns", "T 36.7℃　P 82 次/分　R 18 次/分　BP 138/88 mmHg");
        data.put("nurseInfo", "邵怀信 / 柳文渊");
        data.put("bedInfo", "消化内科 / 普通双人病房");
        data.put("reserveDate", "2026年11月05日（候床第 3 位）");
        data.put("triageConclusion", "患者病情属普通级别，无急危重症征象，按三级分诊流程安排消化内科门诊就诊，同步预约胃镜与床位。");
        data.put("remark", "候床顺序按病情紧急程度动态调整，急危重症患者优先；请保持电话畅通，逾期未办理视为自动放弃。");
        data.put("nurse", "邵怀信");
        data.put("doctor", "柳文渊");
        data.put("patientSign", "路承志");

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
        config.getDoc().setMargins(Arrays.asList(44, 44, 44, 44));
        config.getFontConfig().setDefaultFontName("Noto Sans CJK SC");
        config.getHeaderConfig().setEnabled(true);
        config.getHeaderConfig().setText("××市第一人民医院　门诊分诊 / 床位预约回执　TRI-20261030-005518");
        config.getHeaderConfig().setFontSize(8f);
        config.getHeaderConfig().setHeight(30);
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
