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
 * 单据 132：原产地证书 / 普惠制原产地证（C/O & FORM A）（customs 领域第 8 项）。
 *
 * <p>本类为独立可运行 Demo：自带 {@code main} 方法、不依赖任何公共工具类或实体类，
 * 数据以 key-value 形式通过 {@link JQuickPdfFactory#bindAll(Map)} 注入，
 * 版式全部落在模板文件 {@code demo/biz/customs/132_certificate_of_origin.txt} 中。</p>
 *
 * <p>视觉主题「宝蓝·疏朗·票式」：主色 #365292、辅色 #913F27、浅底 #F1F3F6；
 * 版式为 ticket 抬头 + grid2 信息区 + airy 明细表 + split 签章区 + plain 落款，
 * 与同领域其它单据互不重样。</p>
 *
 * <p>原产地证书 / 普惠制原产地证：证书编号与类型、出口商与进口商、原产国与目的国，</p>
 * <p>逐项 HS 编码与原产地标准、官方声明与签证机构签章栏。</p>
 *
 * <p>运行后输出：{@code D:/test/pdf/132_certificate_of_origin.pdf}。</p>
 *
 * @author Martin
 * @version 1.0.0
 */
public class JQuickBiz132CertificateOfOrigin {

    private static final String PDF_DIR = "D:/test/pdf/";
    private static final String OUT_FILE = PDF_DIR + "132_certificate_of_origin.pdf";
    private static final String TEMPLATE = "demo/biz/customs/132_certificate_of_origin.txt";

    /** 模板固定明细行数，不足时补空行，避免占位符原样输出。 */
    private static final int ITEM_ROWS = 6;

    public static void main(String[] args) throws IOException {
        new File(PDF_DIR).mkdirs();

        // ---------- 1. 数据（无实体类，直接 bind）----------
        String[][] items = {
                {"1", "CTN-01 ~ 08", "伺服电机 SM-500W", "8501.5100", "8 PCS", " wholly obtained"},
                {"2", "CTN-09 ~ 16", "精密齿轮箱 GB-120", "8483.4090", "8 PCS", " wholly obtained"},
                {"3", "CTN-17 ~ 22", "运动控制卡 MC-8020", "8537.1090", "6 PCS", " wholly obtained"},
                {"4", "CTN-23 ~ 30", "三坐标测量仪 CMM-1210", "9031.8090", "1 SET", " wholly obtained"},
                {"5", "CTN-31 ~ 44", "工业阀门组件 IV-50", "8481.8040", "24 SETS", " wholly obtained"},
                {"6", "CTN-45 ~ 46", "技术资料", "—", "1 SET", " wholly obtained"}
        };

        Map<String, Object> data = new LinkedHashMap<String, Object>();
        data.put("certNo", "CO-SG-2026-0881234");
        data.put("certType", "普惠制原产地证 FORM A");
        data.put("issueDate", "2026年09月18日");
        data.put("issueOrg", "××商会（××海关代签）");
        data.put("exporter", "×× PRECISION MACHINERY PTE. LTD.");
        data.put("importer", "××国际贸易有限公司");
        data.put("originCountry", "SINGAPORE");
        data.put("transportRoute", "BY SEA　FROM SINGAPORE TO SHANGHAI");
        data.put("invoiceInfo", "INV-2026-0918 / 2026-09-18");
        data.put("destCountry", "CHINA");
        data.put("declaration", "兹证明上述货物原产于新加坡，符合普惠制原产地规则及给惠国普惠制方案项下的原产地标准。");
        data.put("remark", "FORM A 证书须在货物出运前或出运后合理期限内签发，逾期补发须注明「ISSUED RETROSPECTIVELY」。");
        data.put("declarer", "崔行知");
        data.put("issuerSign", "××商会");
        data.put("issuePlace", "SINGAPORE　2026-09-18");

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
        config.getHeaderConfig().setText("××商会　CERTIFICATE OF ORIGIN FORM A　CO-SG-2026-0881234");
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
