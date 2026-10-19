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
 * 单据 142：现金支票 / 转账支票（bank 领域第 3 项）。
 *
 * <p>本类为独立可运行 Demo：自带 {@code main} 方法、不依赖任何公共工具类或实体类，
 * 数据以 key-value 形式通过 {@link JQuickPdfFactory#bindAll(Map)} 注入，
 * 版式全部落在模板文件 {@code demo/biz/bank/142_bank_check.txt} 中。</p>
 *
 * <p>视觉主题「靛蓝·疏朗·典式」：主色 #256496、辅色 #912729、浅底 #F0F4F7；
 * 版式为 classic 抬头 + strip 信息区 + airy 明细表 + three 签章区 + table 落款，
 * 与同领域其它单据互不重样。</p>
 *
 * <p>现金支票 / 转账支票：支票号码与类型、付款银行与出票人账号、</p>
 * <p>收款人与大小写金额、用途与背书记录、出票人预留印鉴栏。</p>
 *
 * <p>运行后输出：{@code D:/test/pdf/142_bank_check.pdf}。</p>
 *
 * @author Martin
 * @version 1.0.0
 */
public class JQuickBiz142BankCheck {

    private static final String PDF_DIR = "D:/test/pdf/";
    private static final String OUT_FILE = PDF_DIR + "142_bank_check.pdf";
    private static final String TEMPLATE = "demo/biz/bank/142_bank_check.txt";

    /** 模板固定明细行数，不足时补空行，避免占位符原样输出。 */
    private static final int ITEM_ROWS = 6;

    public static void main(String[] args) throws IOException {
        new File(PDF_DIR).mkdirs();

        // ---------- 1. 数据（无实体类，直接 bind）----------
        String[][] items = {
                {"1", "出票日期", "2026年10月19日（大写：贰零贰陆年壹拾月壹拾玖日）", "必填", "通过", "—"},
                {"2", "收款人名称", "××精密机械制造有限公司", "必填", "通过", "不得涂改"},
                {"3", "金额大小写", "贰万叁仟元整 / ￥23,000.00", "必填", "通过", "大小写一致"},
                {"4", "用途", "模具开发费", "必填", "通过", "—"},
                {"5", "出票人签章", "预留印鉴（财务专用章 + 法人章）", "必填", "通过", "印鉴核验"},
                {"6", "背书栏", "空白背书 / 委托收款背书", "选填", "通过", "不得附有条件"}
        };

        Map<String, Object> data = new LinkedHashMap<String, Object>();
        data.put("checkNo", "10203360 88127712");
        data.put("checkType", "转账支票（划线支票）");
        data.put("issueDate", "2026年10月19日");
        data.put("payerBank", "中国工商银行××支行");
        data.put("payerAccount", "4301 0123 4567 8901");
        data.put("payerName", "××国际贸易有限公司");
        data.put("payeeName", "××精密机械制造有限公司");
        data.put("amountInWords", "人民币贰万叁仟元整");
        data.put("amount", "￥23,000.00");
        data.put("usage", "模具开发费");
        data.put("validNote", "支票的提示付款期限为自出票日起 10 日，异地使用的支票，其提示付款期限由中国人民银行另行规定。");
        data.put("remark", "转账支票只能用于转账，不得支取现金；划线支票仅可通过银行转账收款。");
        data.put("payerSign", "××国际贸易有限公司 财务专用章");
        data.put("legalSign", "闻仲平");
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
        config.getDoc().setMargins(Arrays.asList(44, 44, 44, 44));
        config.getFontConfig().setDefaultFontName("Noto Sans CJK SC");
        config.getHeaderConfig().setEnabled(true);
        config.getHeaderConfig().setText("中国工商银行××支行　转账支票　10203360 88127712");
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
