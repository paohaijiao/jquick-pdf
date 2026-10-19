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
package com.github.paohaijiao.biz.gov;

import com.github.paohaijiao.config.JPdfConfig;
import com.github.paohaijiao.executor.JQuickPdfFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 单据 168：环境影响评价备案回执 / 安全评价验收单据（gov 领域第 14 项）。
 *
 * <p>本类为独立可运行 Demo：自带 {@code main} 方法、不依赖任何公共工具类或实体类，
 * 数据以 key-value 形式通过 {@link JQuickPdfFactory#bindAll(Map)} 注入，
 * 版式全部落在模板文件 {@code demo/biz/gov/168_environment_assessment_receipt.txt} 中。</p>
 *
 * <p>视觉主题「绛紫·密排·票式」：主色 #8711AB、辅色 #719127、浅底 #F6EFF8；
 * 版式为 ticket 抬头 + grid2 信息区 + compact 明细表 + right 签章区 + plain 落款，
 * 与同领域其它单据互不重样。</p>
 *
 * <p>环评备案回执 / 安评验收单据：项目名称与建设地点、环评与安评文件类型，</p>
 * <p>逐项评价验收项目（标准要求、建设情况、结论）与备案结论、备案机关签章栏。</p>
 *
 * <p>运行后输出：{@code D:/test/pdf/168_environment_assessment_receipt.pdf}。</p>
 *
 * @author Martin
 * @version 1.0.0
 */
public class JQuickBiz168EnvironmentAssessmentReceipt {

    private static final String PDF_DIR = "D:/test/pdf/";
    private static final String OUT_FILE = PDF_DIR + "168_environment_assessment_receipt.pdf";
    private static final String TEMPLATE = "demo/biz/gov/168_environment_assessment_receipt.txt";

    /** 模板固定明细行数，不足时补空行，避免占位符原样输出。 */
    private static final int ITEM_ROWS = 6;

    public static void main(String[] args) throws IOException {
        new File(PDF_DIR).mkdirs();

        // ---------- 1. 数据（无实体类，直接 bind）----------
        String[][] items = {
                {"1", "废气治理设施", "非甲烷总烃排放浓度 ≤ 60mg/m³", "活性炭吸附 + 催化燃烧，达标排放", "通过", "—"},
                {"2", "废水治理设施", "pH 6-9，COD ≤ 500mg/L", "隔油 + 气浮 + 生化处理，达标", "通过", "—"},
                {"3", "噪声防治措施", "厂界噪声昼间 ≤ 65dB(A)", "设备减振 + 隔声罩，达标", "通过", "—"},
                {"4", "固体废物处置", "危废规范化管理、转移联单", "危废暂存间已建，联单齐全", "通过", "—"},
                {"5", "安全设施竣工验收", "消防、防雷、防静电验收合格", "均已取得验收合格文件", "通过", "—"},
                {"6", "应急预案与演练", "应急预案备案并定期演练", "已备案，年内演练 2 次", "通过", "—"}
        };

        Map<String, Object> data = new LinkedHashMap<String, Object>();
        data.put("recordNo", "EIA-20261031-0055");
        data.put("recordDate", "2026年10月31日");
        data.put("orgName", "××市生态环境局 ××市应急管理局");
        data.put("projectName", "年产 200 台智能装备产业化项目");
        data.put("companyName", "××智能装备制造有限公司");
        data.put("location", "××市××区智造大道 168 号 A 座");
        data.put("eiaType", "环境影响报告表（编制单位：××环境科技有限公司）");
        data.put("eiaConclusion", "在落实各项环保措施前提下，从环保角度项目建设可行");
        data.put("siaType", "安全预评价报告 + 安全设施竣工验收");
        data.put("siaConclusion", "安全风险可控，安全设施验收通过");
        data.put("investInfo", "18600 万元 / 环保投资 620 万元");
        data.put("validUntil", "自备案之日起 5 年");
        data.put("recordConclusion", "该项目环境影响报告表及安全评价报告材料齐全，符合备案条件，予以备案（环境影响登记表备案号 EIA-20261031-0055）。");
        data.put("remark", "建设项目的性质、规模、地点或采用的生产工艺发生重大变动的，须重新报批环评文件；备案后 5 年内未开工的须重新备案。");
        data.put("companySign", "××智能装备制造有限公司");
        data.put("operator", "崔行知");
        data.put("orgSign", "××市生态环境局");

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
        config.getDoc().setMargins(Arrays.asList(36, 36, 36, 36));
        config.getFontConfig().setDefaultFontName("Noto Sans CJK SC");
        config.getHeaderConfig().setEnabled(true);
        config.getHeaderConfig().setText("××市生态环境局　环评备案回执　EIA-20261031-0055");
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
