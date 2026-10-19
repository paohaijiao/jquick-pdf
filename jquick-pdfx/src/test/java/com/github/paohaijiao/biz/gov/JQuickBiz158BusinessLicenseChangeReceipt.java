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
 * 单据 158：营业执照变更 / 备案回执（gov 领域第 4 项）。
 *
 * <p>本类为独立可运行 Demo：自带 {@code main} 方法、不依赖任何公共工具类或实体类，
 * 数据以 key-value 形式通过 {@link JQuickPdfFactory#bindAll(Map)} 注入，
 * 版式全部落在模板文件 {@code demo/biz/gov/158_business_license_change_receipt.txt} 中。</p>
 *
 * <p>视觉主题「靛蓝·密排·公文」：主色 #2098A8、辅色 #91274C、浅底 #F0F6F7；
 * 版式为 doc 抬头 + strip 信息区 + compact 明细表 + two 签章区 + table 落款，
 * 与同领域其它单据互不重样。</p>
 *
 * <p>营业执照变更 / 备案回执：企业名称与统一社会信用代码、变更备案类型，</p>
 * <p>逐项变更项目（变更前 / 变更后）、办理结果与登记机关签章栏。</p>
 *
 * <p>运行后输出：{@code D:/test/pdf/158_business_license_change_receipt.pdf}。</p>
 *
 * @author Martin
 * @version 1.0.0
 */
public class JQuickBiz158BusinessLicenseChangeReceipt {

    private static final String PDF_DIR = "D:/test/pdf/";
    private static final String OUT_FILE = PDF_DIR + "158_business_license_change_receipt.pdf";
    private static final String TEMPLATE = "demo/biz/gov/158_business_license_change_receipt.txt";

    /** 模板固定明细行数，不足时补空行，避免占位符原样输出。 */
    private static final int ITEM_ROWS = 6;

    public static void main(String[] args) throws IOException {
        new File(PDF_DIR).mkdirs();

        // ---------- 1. 数据（无实体类，直接 bind）----------
        String[][] items = {
                {"1", "企业名称", "××智能装备有限公司", "××智能装备制造有限公司", "2026-10-21"},
                {"2", "住所", "××市××区智造大道 88 号", "××市××区智造大道 168 号 A 座", "2026-10-21"},
                {"3", "注册资本", "人民币 2000 万元", "人民币 5000 万元", "2026-10-21"},
                {"4", "法定代表人", "闻仲平", "闻仲平（不变）", "—"},
                {"5", "经营范围", "智能装备研发、生产、销售", "增加：机器人系统集成、技术服务", "2026-10-21"},
                {"6", "章程备案", "原章程（2024 版）", "新章程（2026 修订版）", "2026-10-21"}
        };

        Map<String, Object> data = new LinkedHashMap<String, Object>();
        data.put("receiptNo", "BL-20261021-0055");
        data.put("approveDate", "2026年10月21日");
        data.put("orgName", "××市市场监督管理局");
        data.put("companyName", "××智能装备制造有限公司");
        data.put("creditCode", "91320100MA1X7K38XG");
        data.put("changeType", "变更登记（名称、住所、注册资本）+ 章程备案");
        data.put("oldRegNo", "320100000088123");
        data.put("newRegNo", "320100000088456");
        data.put("acceptInfo", "2026-10-15 / GS-20261015-03188");
        data.put("result", "准予变更登记，已换发营业执照");
        data.put("remark", "营业执照变更后，税务、银行、社保、公积金等登记信息须在 30 日内同步变更；营业执照正副本须同时换发。");
        data.put("operator", "苏见宁");
        data.put("approver", "闻仲平");
        data.put("companySign", "崔行知");

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
        config.getHeaderConfig().setText("××市市场监督管理局　营业执照变更 / 备案回执　BL-20261021-0055");
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
