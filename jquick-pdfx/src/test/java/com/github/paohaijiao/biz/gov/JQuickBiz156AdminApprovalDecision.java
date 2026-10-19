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
 * 单据 156：行政审批申请表 / 准予行政许可决定书（gov 领域第 2 项）。
 *
 * <p>本类为独立可运行 Demo：自带 {@code main} 方法、不依赖任何公共工具类或实体类，
 * 数据以 key-value 形式通过 {@link JQuickPdfFactory#bindAll(Map)} 注入，
 * 版式全部落在模板文件 {@code demo/biz/gov/156_admin_approval_decision.txt} 中。</p>
 *
 * <p>视觉主题「青瓷·素雅·票式」：主色 #2FAC76、辅色 #912786、浅底 #F1F6F4；
 * 版式为 ticket 抬头 + grid2 信息区 + light 明细表 + card 签章区 + right 落款，
 * 与同领域其它单据互不重样。</p>
 *
 * <p>行政审批申请表 / 准予行政许可决定书：决定书文号与许可机关、申请事项与审查结论，</p>
 * <p>逐项许可事项与有效期、决定内容与救济途径、许可机关签章栏。</p>
 *
 * <p>运行后输出：{@code D:/test/pdf/156_admin_approval_decision.pdf}。</p>
 *
 * @author Martin
 * @version 1.0.0
 */
public class JQuickBiz156AdminApprovalDecision {

    private static final String PDF_DIR = "D:/test/pdf/";
    private static final String OUT_FILE = PDF_DIR + "156_admin_approval_decision.pdf";
    private static final String TEMPLATE = "demo/biz/gov/156_admin_approval_decision.txt";

    /** 模板固定明细行数，不足时补空行，避免占位符原样输出。 */
    private static final int ITEM_ROWS = 6;

    public static void main(String[] args) throws IOException {
        new File(PDF_DIR).mkdirs();

        // ---------- 1. 数据（无实体类，直接 bind）----------
        String[][] items = {
                {"1", "预包装食品销售（含冷藏冷冻食品）", "食品销售", "××区中山路 168 号 1 层", "2031-10-18", "—"},
                {"2", "散装食品销售", "食品销售", "××区中山路 168 号 1 层", "2031-10-18", "—"},
                {"3", "热食类食品制售", "食品制售", "××区中山路 168 号 1 层", "2031-10-18", "—"},
                {"4", "自制饮品制售", "食品制售", "××区中山路 168 号 1 层", "2031-10-18", "—"},
                {"5", "网络经营（第三方平台）", "食品销售", "线上经营", "2031-10-18", "—"},
                {"6", "—", "—", "—", "—", "—"}
        };

        Map<String, Object> data = new LinkedHashMap<String, Object>();
        data.put("decisionNo", "××许准字〔2026〕第 0881 号");
        data.put("decisionDate", "2026年10月19日");
        data.put("orgName", "××市市场监督管理局");
        data.put("applyItem", "食品经营许可证核发（新办）");
        data.put("licenseType", "普通许可（食品销售）");
        data.put("applicant", "××餐饮管理有限公司");
        data.put("creditCode", "91320100MA1X7K38XG");
        data.put("businessPlace", "××市××区中山路 168 号 1 层");
        data.put("acceptInfo", "GS-20261012-02991 / 2026-10-12");
        data.put("reviewResult", "材料齐全、符合法定形式，现场核查通过");
        data.put("decision", "准予行政许可");
        data.put("licenseInfo", "JY13201000088812 / 至 2031-10-18");
        data.put("decisionContent", "经审查，你单位提出的食品经营许可申请符合《中华人民共和国食品安全法》及其实施条例规定的条件，本机关决定准予行政许可。");
        data.put("remedyNote", "如对本决定不服，可自收到本决定书之日起 60 日内申请行政复议，或 6 个月内向人民法院提起行政诉讼。");
        data.put("orgHead", "闻仲平");
        data.put("reviewer", "柳文渊");
        data.put("applicantSign", "崔行知");

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
        config.getHeaderConfig().setText("××市市场监督管理局　准予行政许可决定书　××许准字〔2026〕第 0881 号");
        config.getHeaderConfig().setFontSize(7.5f);
        config.getHeaderConfig().setHeight(32);
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
