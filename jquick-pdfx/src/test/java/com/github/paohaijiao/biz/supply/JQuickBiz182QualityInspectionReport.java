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
import java.nio.file.Files;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 单据 182：来料质检单 / 成品质量检验报告单（supply 领域第 3 项）。
 *
 * <p>本类为独立可运行 Demo：自带 {@code main} 方法、不依赖任何公共工具类或实体类，
 * 数据以 key-value 形式通过 {@link JQuickPdfFactory#bindAll(Map)} 注入，
 * 版式全部落在模板文件 {@code demo/biz/supply/182_quality_inspection_report.txt} 中。</p>
 *
 * <p>视觉主题「金橙·疏朗·公文」：主色 #C4A718、辅色 #276A91、浅底 #F8F6EF；
 * 版式为 doc 抬头 + strip 信息区 + airy 明细表 + split 签章区 + plain 落款，
 * 与同领域其它单据互不重样。</p>
 *
 * <p>来料质检单 / 成品质量检验报告单：检验类型与抽检方案、检验依据与测量设备，</p>
 * <p>逐项检验项目（标准要求、实测结果、判定）与检验结论、处置意见与三方签字栏。</p>
 *
 * <p>运行后输出：{@code D:/test/pdf/182_quality_inspection_report.pdf}。</p>
 *
 * @author Martin
 * @version 1.0.0
 */
public class JQuickBiz182QualityInspectionReport {

    private static final String PDF_DIR = "D:/test/pdf/";
    private static final String OUT_FILE = PDF_DIR + "182_quality_inspection_report.pdf";
    private static final String TEMPLATE = "demo/biz/supply/182_quality_inspection_report.txt";

    /** 模板固定明细行数，不足时补空行，避免占位符原样输出。 */
    private static final int ITEM_ROWS = 8;

    public static void main(String[] args) throws IOException {
        new File(PDF_DIR).mkdirs();

        // ---------- 1. 数据（无实体类，直接 bind）----------
        String[][] items = {
                {"1", "外观与标识检查", "无明显磕碰划伤，铭牌清晰", "5 台均无缺陷，标识完整", "合格", "—"},
                {"2", "外形尺寸检测", "安装孔距 120±0.10mm", "119.96 ~ 120.05 mm", "合格", "—"},
                {"3", "额定转速测试", "3000 rpm ±2%", "2991 ~ 3008 rpm", "合格", "—"},
                {"4", "额定扭矩测试", "≥ 2.39 N·m", "2.42 ~ 2.51 N·m", "合格", "—"},
                {"5", "绝缘电阻测试", "≥ 100 MΩ（500V）", "＞ 500 MΩ", "合格", "—"},
                {"6", "耐压测试", "1500V / 60s 无击穿", "通过，无击穿闪络", "合格", "—"},
                {"7", "成品功能联调", "整机连续运行 8 小时无故障", "运行 8 小时，故障 0 次", "合格", "—"},
                {"8", "包装与防护检查", "防潮防震包装，标识齐全", "包装完好，标识齐全", "合格", "—"}
        };

        Map<String, Object> data = new LinkedHashMap<String, Object>();
        data.put("inspectNo", "QC-20261012-0044");
        data.put("inspectDate", "2026年10月12日");
        data.put("deptName", "质量管理部 / 来料检验 IQC + 成品检验 OQC");
        data.put("supplierName", "××精密机械制造有限公司 / LOT-260908-03");
        data.put("materialName", "伺服电机 SVM-750W / 智能装配单元 ZN-2026");
        data.put("qtyInfo", "12 台 / 按 GB/T 2828.1 一般检验水平 II，抽检 5 台");
        data.put("standard", "技术图纸 SVM-750W-02、检验规范 WI-QC-0118");
        data.put("deviceInfo", "三坐标测量仪、耐压测试仪 / 温度 22℃ 湿度 55%");
        data.put("inspectorInfo", "路承志 / 闻仲平");
        data.put("conclusion", "来料合格，准予入库；成品一次交验合格率 96.3%");
        data.put("handleAdvice", "来料批次判定合格，办理入库；成品一次交验合格率 96.3%，高于目标值 95%，同意放行发货。");
        data.put("remark", "检验不合格品须隔离标识并执行《不合格品控制程序》，让步接收须经技术、质量、生产三方会签批准。");
        data.put("inspector", "路承志");
        data.put("auditor", "闻仲平");
        data.put("supplierSign", "崔行知");

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
        config.getHeaderConfig().setText("××智能装备制造有限公司　来料质检 / 成品检验报告　QC-20261012-0044");
        config.getHeaderConfig().setFontSize(8.5f);
        config.getHeaderConfig().setHeight(34);
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
