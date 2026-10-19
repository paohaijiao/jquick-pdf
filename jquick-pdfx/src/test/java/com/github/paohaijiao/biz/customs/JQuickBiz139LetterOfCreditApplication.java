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
package com.github.paohaijiao.biz.customs;

import com.github.paohaijiao.config.JPdfConfig;
import com.github.paohaijiao.executor.JQuickPdfFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 单据 139：信用证 / 开证申请书 / 报关代理委托书（customs 领域第 15 项）。
 *
 * <p>本类为独立可运行 Demo：自带 {@code main} 方法、不依赖任何公共工具类或实体类，
 * 数据以 key-value 形式通过 {@link JQuickPdfFactory#bindAll(Map)} 注入，
 * 版式全部落在模板文件 {@code demo/biz/customs/139_letter_of_credit_application.txt} 中。</p>
 *
 * <p>视觉主题「群青·厚框·下线」：主色 #2B1F8F、辅色 #916A27、浅底 #F1F0F7；
 * 版式为 underline 抬头 + kv 信息区 + solid 明细表 + card 签章区 + plain 落款，
 * 与同领域其它单据互不重样。</p>
 *
 * <p>信用证开证申请书：信用证号与类型、开证申请人与受益人、金额与有效期，</p>
 * <p>单据要求清单一览、特别条款与开证行签章栏。</p>
 *
 * <p>运行后输出：{@code D:/test/pdf/139_letter_of_credit_application.pdf}。</p>
 *
 * @author Martin
 * @version 1.0.0
 */
public class JQuickBiz139LetterOfCreditApplication {

    private static final String PDF_DIR = "D:/test/pdf/";
    private static final String OUT_FILE = PDF_DIR + "139_letter_of_credit_application.pdf";
    private static final String TEMPLATE = "demo/biz/customs/139_letter_of_credit_application.txt";

    /** 模板固定明细行数，不足时补空行，避免占位符原样输出。 */
    private static final int ITEM_ROWS = 6;

    public static void main(String[] args) throws IOException {
        new File(PDF_DIR).mkdirs();

        // ---------- 1. 数据（无实体类，直接 bind）----------
        String[][] items = {
                {"1", "商业发票 / COMMERCIAL INVOICE", "注明信用证号与合同号", "3", "提单日后 15 天", "待提交"},
                {"2", "提单 / BILL OF LADING", "全套清洁已装船提单，TO ORDER", "3/3", "提单日后 21 天", "待提交"},
                {"3", "装箱单 / PACKING LIST", "注明件数毛重尺码", "3", "同发票", "待提交"},
                {"4", "原产地证书 / C/O FORM A", "由商会签发并签章", "1", "同发票", "待提交"},
                {"5", "保险单 / INSURANCE POLICY", "按发票金额 110% 投保一切险", "2", "同发票", "待提交"},
                {"6", "报关代理委托书", "明确代理权限与责任期限", "1", "开证前", "已签署"}
        };

        Map<String, Object> data = new LinkedHashMap<String, Object>();
        data.put("lcNo", "LC-20261017-SG-08812");
        data.put("issueDate", "2026年10月17日");
        data.put("issuingBank", "中国银行××分行");
        data.put("applicant", "××国际贸易有限公司");
        data.put("beneficiary", "×× PRECISION MACHINERY PTE. LTD.");
        data.put("lcType", "不可撤销即期信用证（IRREVOCABLE SIGHT L/C）");
        data.put("lcAmount", "USD 46,280.40");
        data.put("expiryInfo", "2026年12月16日 / SINGAPORE");
        data.put("negotiatingBank", "×× BANK, SINGAPORE BRANCH");
        data.put("agencyName", "××报关服务有限公司");
        data.put("specialClause", "允许分批装运，允许转运；货物须于 2026 年 11 月 30 日前装船，不符点单据每次扣 USD 80.00。");
        data.put("remark", "开证申请书须加盖申请人预留印鉴，报关代理委托书须明确代理报关、报检的权限与期限。");
        data.put("applicantSign", "××国际贸易有限公司");
        data.put("bankSign", "中国银行××分行");
        data.put("inputer", "苏见宁");

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
        config.getHeaderConfig().setText("中国银行××分行　信用证开证申请书　LC-20261017-SG-08812");
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
