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
 * 单据 127：报关小票 / 现场查验回执（customs 领域第 3 项）。
 *
 * <p>本类为独立可运行 Demo：自带 {@code main} 方法、不依赖任何公共工具类或实体类，
 * 数据以 key-value 形式通过 {@link JQuickPdfFactory#bindAll(Map)} 注入，
 * 版式全部落在模板文件 {@code demo/biz/customs/127_declaration_receipt_inspection.txt} 中。</p>
 *
 * <p>视觉主题「靛蓝·疏朗·下线」：主色 #169DB8、辅色 #912747、浅底 #EFF6F8；
 * 版式为 underline 抬头 + kv 信息区 + airy 明细表 + card 签章区 + right 落款，
 * 与同领域其它单据互不重样。</p>
 *
 * <p>报关小票 / 现场查验回执：查验现场与方式、逐项查验内容与结果，</p>
 * <p>查验结论与查验关员、收发货人签收栏。</p>
 *
 * <p>运行后输出：{@code D:/test/pdf/127_declaration_receipt_inspection.pdf}。</p>
 *
 * @author Martin
 * @version 1.0.0
 */
public class JQuickBiz127DeclarationReceiptInspection {

    private static final String PDF_DIR = "D:/test/pdf/";
    private static final String OUT_FILE = PDF_DIR + "127_declaration_receipt_inspection.pdf";
    private static final String TEMPLATE = "demo/biz/customs/127_declaration_receipt_inspection.txt";

    /** 模板固定明细行数，不足时补空行，避免占位符原样输出。 */
    private static final int ITEM_ROWS = 6;

    public static void main(String[] args) throws IOException {
        new File(PDF_DIR).mkdirs();

        // ---------- 1. 数据（无实体类，直接 bind）----------
        String[][] items = {
                {"品名规格核对", "伺服电机 / 精密齿轮箱等 6 项", "相符", "无", "通过"},
                {"数量重量核对", "申报 46 件 / 12140 kg", "相符", "无", "通过"},
                {"原产地核对", "新加坡，随附原产地证书", "相符", "无", "通过"},
                {"木质包装检疫", "IPPC 标识核查", "相符", "无", "通过"},
                {"知识产权核查", "商标 / 专利备案比对", "相符", "无", "通过"},
                {"单证完整性", "合同发票装箱单提单齐全", "相符", "无", "通过"}
        };

        Map<String, Object> data = new LinkedHashMap<String, Object>();
        data.put("receiptNo", "CHK-20261010-0331");
        data.put("checkDate", "2026年10月10日");
        data.put("checkPlace", "××海关××查验中心 3 号台");
        data.put("declarationNo", "220120261012003388");
        data.put("consignee", "××国际贸易有限公司");
        data.put("checkMethod", "机检 + 人工开箱");
        data.put("officer", "闻仲平 / 路承志");
        data.put("conclusion", "单货相符，予以放行");
        data.put("customsOffice", "××海关");
        data.put("remark", "本回执一式两联，一联交收发货人留存，一联由海关归档；如需复验请在 3 个工作日内提出书面申请。");
        data.put("consigneeSign", "崔行知");
        data.put("inputer", "苏见宁");

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
        config.getDoc().setMargins(Arrays.asList(48, 48, 48, 48));
        config.getFontConfig().setDefaultFontName("Noto Sans CJK SC");
        config.getHeaderConfig().setEnabled(true);
        config.getHeaderConfig().setText("××海关　现场查验回执　CHK-20261010-0331");
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
