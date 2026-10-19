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
 * 单据 143：银行承兑汇票 / 商业承兑汇票（bank 领域第 4 项）。
 *
 * <p>本类为独立可运行 Demo：自带 {@code main} 方法、不依赖任何公共工具类或实体类，
 * 数据以 key-value 形式通过 {@link JQuickPdfFactory#bindAll(Map)} 注入，
 * 版式全部落在模板文件 {@code demo/biz/bank/143_acceptance_bill.txt} 中。</p>
 *
 * <p>视觉主题「宝蓝·密排·竖栏」：主色 #3B53A0、辅色 #914627、浅底 #F1F2F6；
 * 版式为 side 抬头 + kv 信息区 + compact 明细表 + right 签章区 + right 落款，
 * 与同领域其它单据互不重样。</p>
 *
 * <p>银行承兑汇票 / 商业承兑汇票：汇票号码与类型、出票与到期日期、</p>
 * <p>出票人收款人账户、大小写金额、背书转让连续记录与承兑行签章栏。</p>
 *
 * <p>运行后输出：{@code D:/test/pdf/143_acceptance_bill.pdf}。</p>
 *
 * @author Martin
 * @version 1.0.0
 */
public class JQuickBiz143AcceptanceBill {

    private static final String PDF_DIR = "D:/test/pdf/";
    private static final String OUT_FILE = PDF_DIR + "143_acceptance_bill.pdf";
    private static final String TEMPLATE = "demo/biz/bank/143_acceptance_bill.txt";

    /** 模板固定明细行数，不足时补空行，避免占位符原样输出。 */
    private static final int ITEM_ROWS = 6;

    public static void main(String[] args) throws IOException {
        new File(PDF_DIR).mkdirs();

        // ---------- 1. 数据（无实体类，直接 bind）----------
        String[][] items = {
                {"1", "出票", "××国际贸易有限公司 签发并交付", "2026-10-19", "已签章", "有效"},
                {"2", "承兑", "中国工商银行××支行 到期无条件付款", "2026-10-19", "已承兑", "有效"},
                {"3", "背书转让（第一手）", "背书给 ××物流有限公司", "2026-10-25", "已签章", "有效"},
                {"4", "背书转让（第二手）", "背书给 ××材料科技有限公司", "2026-11-08", "已签章", "有效"},
                {"5", "贴现", "持票人向 ××银行申请贴现", "2026-12-02", "待办理", "未贴现"},
                {"6", "提示付款", "到期日起 10 日内提示付款", "—", "—", "待到期"}
        };

        Map<String, Object> data = new LinkedHashMap<String, Object>();
        data.put("billNo", "10400052 20261019 00382116");
        data.put("billType", "银行承兑汇票");
        data.put("issueDate", "2026年10月19日");
        data.put("dueDate", "2027年04月19日");
        data.put("drawerName", "××国际贸易有限公司");
        data.put("drawerAccount", "4301 0123 4567 8901 / 中国工商银行××支行");
        data.put("payeeName", "××精密机械制造有限公司");
        data.put("payeeAccount", "1259 8877 6655 4433 / 招商银行××分行");
        data.put("amountInWords", "人民币伍拾万元整");
        data.put("amount", "￥500,000.00");
        data.put("acceptor", "中国工商银行××支行");
        data.put("acceptNo", "AC-2026-1019-0033");
        data.put("paymentNote", "银行承兑汇票由承兑银行到期无条件付款；商业承兑汇票由承兑人付款，到期不付持票人可行使追索权。");
        data.put("remark", "汇票背书应当连续，背书不得附有条件；票据金额、日期、收款人名称不得更改，更改的票据无效。");
        data.put("drawerSign", "××国际贸易有限公司");
        data.put("acceptorSign", "中国工商银行××支行");
        data.put("reviewer", "柳文渊");

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
        config.getHeaderConfig().setText("中国工商银行××支行　银行承兑汇票　10400052 20261019 00382116");
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
