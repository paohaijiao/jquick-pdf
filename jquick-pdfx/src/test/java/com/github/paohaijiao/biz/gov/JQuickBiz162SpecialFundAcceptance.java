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
 * 单据 162：专项资金验收单 / 绩效评价回执（gov 领域第 8 项）。
 *
 * <p>本类为独立可运行 Demo：自带 {@code main} 方法、不依赖任何公共工具类或实体类，
 * 数据以 key-value 形式通过 {@link JQuickPdfFactory#bindAll(Map)} 注入，
 * 版式全部落在模板文件 {@code demo/biz/gov/162_special_fund_acceptance.txt} 中。</p>
 *
 * <p>视觉主题「宝蓝·疏朗·票式」：主色 #293979、辅色 #914A27、浅底 #F1F2F6；
 * 版式为 ticket 抬头 + strip 信息区 + airy 明细表 + two 签章区 + table 落款，
 * 与同领域其它单据互不重样。</p>
 *
 * <p>专项资金验收单 / 绩效评价回执：专项资金与项目信息、验收方式与绩效等级，</p>
 * <p>逐项绩效指标（目标值、完成值、得分、等次）与验收结论、组织单位签章栏。</p>
 *
 * <p>运行后输出：{@code D:/test/pdf/162_special_fund_acceptance.pdf}。</p>
 *
 * @author Martin
 * @version 1.0.0
 */
public class JQuickBiz162SpecialFundAcceptance {

    private static final String PDF_DIR = "D:/test/pdf/";
    private static final String OUT_FILE = PDF_DIR + "162_special_fund_acceptance.pdf";
    private static final String TEMPLATE = "demo/biz/gov/162_special_fund_acceptance.txt";

    /** 模板固定明细行数，不足时补空行，避免占位符原样输出。 */
    private static final int ITEM_ROWS = 6;

    public static void main(String[] args) throws IOException {
        new File(PDF_DIR).mkdirs();

        // ---------- 1. 数据（无实体类，直接 bind）----------
        String[][] items = {
                {"1", "技术指标完成情况", "定位精度 ±0.005mm", "定位精度 ±0.0038mm", "20 / 19", "优"},
                {"2", "知识产权产出", "申请专利 6 件", "申请专利 9 件（授权 4 件）", "15 / 15", "优"},
                {"3", "成果转化与产业化", "形成销售收入 3000 万元", "实现销售收入 3860 万元", "20 / 18", "优"},
                {"4", "专项资金使用合规性", "专款专用、单独核算", "专款专用，核算规范", "20 / 20", "优"},
                {"5", "财务管理制度执行", "制度健全、票据合规", "制度健全、票据合规", "15 / 14", "优"},
                {"6", "社会效益与可持续性", "带动就业 20 人", "新增就业 26 人", "10 / 9.5", "优"}
        };

        Map<String, Object> data = new LinkedHashMap<String, Object>();
        data.put("acceptNo", "SFA-20261025-0033");
        data.put("acceptDate", "2026年10月25日");
        data.put("orgName", "××市科学技术局 ××市财政局");
        data.put("fundName", "市级科技计划项目专项资金");
        data.put("projectName", "高精度运动控制关键技术研发及产业化");
        data.put("companyName", "××智能装备制造有限公司");
        data.put("projectNo", "××科计〔2025〕118 号 / KJ2025-0812");
        data.put("fundAmount", "￥3,000,000.00");
        data.put("acceptWay", "会议验收（专家评审 + 现场核查）");
        data.put("perfLevel", "优秀（90.5 分）");
        data.put("conclusion", "通过验收，绩效评级优秀");
        data.put("perfConclusion", "该项目绩效综合评价得分 90.5 分，评价等次为优秀；专项资金使用合规，建议按合同约定拨付尾款。");
        data.put("remark", "验收未通过的项目须限期整改，整改后仍不合格的，收回已拨付的专项资金。");
        data.put("groupLeader", "闻仲平");
        data.put("companySign", "××智能装备制造有限公司");
        data.put("orgSign", "××市科学技术局");

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
        config.getHeaderConfig().setText("××市科学技术局　专项资金验收单　SFA-20261025-0033");
        config.getHeaderConfig().setFontSize(7.5f);
        config.getHeaderConfig().setHeight(30);
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
