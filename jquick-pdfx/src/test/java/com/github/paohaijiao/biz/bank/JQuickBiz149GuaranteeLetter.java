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
package com.github.paohaijiao.biz.bank;

import com.github.paohaijiao.config.JPdfConfig;
import com.github.paohaijiao.executor.JQuickPdfFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 单据 149：保函开立凭证 / 履约保函回执（bank 领域第 10 项）。
 *
 * <p>本类为独立可运行 Demo：自带 {@code main} 方法、不依赖任何公共工具类或实体类，
 * 数据以 key-value 形式通过 {@link JQuickPdfFactory#bindAll(Map)} 注入，
 * 版式全部落在模板文件 {@code demo/biz/bank/149_guarantee_letter.txt} 中。</p>
 *
 * <p>视觉主题「紫棠·厚框·竖栏」：主色 #3F1190、辅色 #918627、浅底 #F2EFF8；
 * 版式为 side 抬头 + chip 信息区 + solid 明细表 + two 签章区 + table 落款，
 * 与同领域其它单据互不重样。</p>
 *
 * <p>保函开立凭证 / 履约保函回执：保函种类、申请人与受益人、基础合同、</p>
 * <p>担保金额与有效期、索赔与减额条款、开立行签章栏。</p>
 *
 * <p>运行后输出：{@code D:/test/pdf/149_guarantee_letter.pdf}。</p>
 *
 * @author Martin
 * @version 1.0.0
 */
public class JQuickBiz149GuaranteeLetter {

    private static final String PDF_DIR = "D:/test/pdf/";
    private static final String OUT_FILE = PDF_DIR + "149_guarantee_letter.pdf";
    private static final String TEMPLATE = "demo/biz/bank/149_guarantee_letter.txt";

    /** 模板固定明细行数，不足时补空行，避免占位符原样输出。 */
    private static final int ITEM_ROWS = 6;

    public static void main(String[] args) throws IOException {
        new File(PDF_DIR).mkdirs();

        // ---------- 1. 数据（无实体类，直接 bind）----------
        String[][] items = {
                {"1", "担保范围", "承包人履行合同义务的履约担保", "合同价 10%", "有效"},
                {"2", "担保金额", "最高不超过人民币壹佰捌拾陆万元整", "1860000.00", "有效"},
                {"3", "有效期", "自开立之日起 12 个月", "—", "有效"},
                {"4", "索赔方式", "受益人提交书面索赔及违约证明", "—", "有效"},
                {"5", "减额条款", "工程形象进度达 50% 后减额 50%", "930000.00", "有效"},
                {"6", "失效条件", "工程竣工验收合格或保函到期", "—", "有效"}
        };

        Map<String, Object> data = new LinkedHashMap<String, Object>();
        data.put("guaranteeNo", "LG-20261025-0033");
        data.put("issueDate", "2026年10月25日");
        data.put("bankName", "中国银行××分行");
        data.put("guaranteeType", "履约保函（PERFORMANCE GUARANTEE）");
        data.put("applicant", "××建设工程有限公司");
        data.put("beneficiary", "××城市投资开发有限公司");
        data.put("baseContract", "××产业园 3 号厂房施工总承包合同");
        data.put("contractInfo", "GC-2026-0713 / ￥18,600,000.00");
        data.put("amountInWords", "人民币壹佰捌拾陆万元整");
        data.put("amount", "￥1,860,000.00");
        data.put("validPeriod", "自开立之日起至 2027 年 10 月 24 日");
        data.put("claimCondition", "受益人书面索赔声明 + 违约证明材料");
        data.put("legalNote", "本保函为独立性保函，开立行承担第一性付款责任；保函一经开立，未经受益人同意不得撤销或修改。");
        data.put("remark", "保函到期后受益人须将保函正本退还开立行；申请人须提供不低于担保金额 20% 的保证金或授信额度。");
        data.put("applicantSign", "××建设工程有限公司");
        data.put("bankSign", "中国银行××分行");
        data.put("reviewer", "柳文渊");

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
        config.getDoc().setMargins(Arrays.asList(40, 40, 40, 40));
        config.getFontConfig().setDefaultFontName("Noto Sans CJK SC");
        config.getHeaderConfig().setEnabled(true);
        config.getHeaderConfig().setText("中国银行××分行　履约保函　LG-20261025-0033");
        config.getHeaderConfig().setFontSize(8.5f);
        config.getHeaderConfig().setHeight(32);
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
