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
import java.math.BigDecimal;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 单据 129：装箱单 / 舱单（PACKING LIST & MANIFEST）（customs 领域第 5 项）。
 *
 * <p>本类为独立可运行 Demo：自带 {@code main} 方法、不依赖任何公共工具类或实体类，
 * 数据以 key-value 形式通过 {@link JQuickPdfFactory#bindAll(Map)} 注入，
 * 版式全部落在模板文件 {@code demo/biz/customs/129_packing_list_manifest.txt} 中。</p>
 *
 * <p>视觉主题「靛蓝·厚框·通栏」：主色 #2F728C、辅色 #91273A、浅底 #F1F5F6；
 * 版式为 banner 抬头 + chip 信息区 + solid 明细表 + three 签章区 + right 落款，
 * 与同领域其它单据互不重样。</p>
 *
 * <p>装箱单 / 舱单：船名航次与提单号、发货人与收货人、逐箱明细（净重 / 毛重 / 尺码），</p>
 * <p>合计件数与体积、发货人签章栏。</p>
 *
 * <p>运行后输出：{@code D:/test/pdf/129_packing_list_manifest.pdf}。</p>
 *
 * @author Martin
 * @version 1.0.0
 */
public class JQuickBiz129PackingListManifest {

    private static final String PDF_DIR = "D:/test/pdf/";
    private static final String OUT_FILE = PDF_DIR + "129_packing_list_manifest.pdf";
    private static final String TEMPLATE = "demo/biz/customs/129_packing_list_manifest.txt";

    /** 模板固定明细行数，不足时补空行，避免占位符原样输出。 */
    private static final int ITEM_ROWS = 6;

    public static void main(String[] args) throws IOException {
        new File(PDF_DIR).mkdirs();

        // ---------- 1. 数据（无实体类，直接 bind）----------
        String[][] items = {
                {"CTN-01 ~ 08", "伺服电机 / SERVO MOTOR", "SM-500W", "8 PCS", "320.00", "368.00", "0.86 CBM"},
                {"CTN-09 ~ 16", "精密齿轮箱 / GEAR BOX", "GB-120", "8 PCS", "1840.00", "1960.00", "2.44 CBM"},
                {"CTN-17 ~ 22", "运动控制卡 / CONTROL CARD", "MC-8020", "6 PCS", "48.00", "66.00", "0.42 CBM"},
                {"CTN-23 ~ 30", "三坐标测量仪 / CMM", "CMM-1210", "1 SET", "8600.00", "9200.00", "8.60 CBM"},
                {"CTN-31 ~ 44", "工业阀门组件 / VALVE", "IV-50", "24 SETS", "1104.00", "1218.00", "3.12 CBM"},
                {"CTN-45 ~ 46", "技术资料 / DOCUMENT", "—", "1 SET", "8.00", "48.00", "0.18 CBM"}
        };

        Map<String, Object> data = new LinkedHashMap<String, Object>();
        data.put("packingNo", "PL-2026-0918");
        data.put("packingDate", "2026年09月18日");
        data.put("vesselNo", "××轮 / V.2608");
        data.put("blNo", "COSU6123456780");
        data.put("shipper", "×× PRECISION MACHINERY PTE. LTD.");
        data.put("consignee", "××国际贸易有限公司");
        data.put("portInfo", "SINGAPORE / SHANGHAI");
        data.put("totalInfo", "46 CTNS / 12860 KGS");
        data.put("remark", "木质包装已加施 IPPC 标识；货物分 46 纸箱装载，箱号连续，唛头见箱体刷唛。");
        data.put("preparer", "苏见宁");
        data.put("reviewer", "柳文渊");
        data.put("signer", "×× PRECISION MACHINERY PTE. LTD.");

        // 明细逐格展开，并累计金额
        BigDecimal itemTotal = BigDecimal.ZERO;
        for (int i = 0; i < ITEM_ROWS; i++) {
            int n = i + 1;
            if (i >= items.length) {
                data.put("itemName" + n, "—");
                data.put("itemSpec" + n, "—");
                data.put("itemUnit" + n, "—");
                data.put("itemQty" + n, "—");
                data.put("itemPrice" + n, "—");
                data.put("itemAmount" + n, "—");
                data.put("itemTax" + n, "—");
                continue;
            }
            data.put("itemName" + n, items[i][0]);
            data.put("itemSpec" + n, items[i][1]);
            data.put("itemUnit" + n, items[i][2]);
            data.put("itemQty" + n, items[i][3]);
            itemTotal = itemTotal.add(new BigDecimal(items[i][4]));
            data.put("itemPrice" + n, items[i][4]);
            data.put("itemAmount" + n, items[i][5]);
            data.put("itemTax" + n, items[i][6]);
        }

        data.put("totalAmount", itemTotal.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString());

        data.put("netWeight", itemTotal.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString());

        data.put("grossWeight", new BigDecimal("12860.00").setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString());

        data.put("measure", new BigDecimal("15.62").setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString());

        // ---------- 2. 渲染 ----------
        JPdfConfig config = new JPdfConfig();
        config.getLayoutConfig().setFlexLayout(true);
        config.getDoc().setMargins(Arrays.asList(40, 40, 40, 40));
        config.getFontConfig().setDefaultFontName("Noto Sans CJK SC");
        config.getHeaderConfig().setEnabled(true);
        config.getHeaderConfig().setText("×× PRECISION MACHINERY PTE. LTD.　PACKING LIST　PL-2026-0918");
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

    /** 人民币金额转票据大写（元角分，末尾补「整」）。 */
    private static String toChineseAmount(BigDecimal amount) {
        String[] digits = {"零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖"};
        String[] units = {"", "拾", "佰", "仟"};
        String[] sections = {"", "万", "亿", "万亿"};
        long cents = amount.setScale(2, BigDecimal.ROUND_HALF_UP)
                .movePointRight(2).longValue();
        if (cents == 0) {
            return "零元整";
        }
        long yuan = cents / 100;
        int jiao = (int) (cents % 100 / 10);
        int fen = (int) (cents % 10);
        StringBuilder sb = new StringBuilder();
        if (yuan > 0) {
            sb.append(toChineseSection(yuan, digits, units, sections)).append("元");
        }
        if (jiao == 0 && fen == 0) {
            sb.append("整");
        } else {
            if (jiao > 0) {
                sb.append(digits[jiao]).append("角");
            } else if (yuan > 0) {
                sb.append("零");
            }
            if (fen > 0) {
                sb.append(digits[fen]).append("分");
            }
        }
        return sb.toString();
    }

    /** 整数部分按四位分节转大写。 */
    private static String toChineseSection(long value, String[] digits, String[] units, String[] sections) {
        StringBuilder sb = new StringBuilder();
        int sectionIndex = 0;
        boolean needZero = false;
        while (value > 0) {
            int section = (int) (value % 10000);
            StringBuilder part = new StringBuilder();
            int unitIndex = 0;
            boolean zeroInSection = false;
            int temp = section;
            while (temp > 0) {
                int digit = temp % 10;
                if (digit == 0) {
                    zeroInSection = true;
                } else {
                    if (zeroInSection || (needZero && part.length() == 0 && sb.length() > 0)) {
                        part.insert(0, digits[0]);
                    }
                    part.insert(0, units[unitIndex]);
                    part.insert(0, digits[digit]);
                    zeroInSection = false;
                    needZero = false;
                }
                unitIndex++;
                temp /= 10;
            }
            if (part.length() > 0) {
                part.append(sections[sectionIndex]);
            }
            sb.insert(0, part);
            needZero = section < 1000 && section > 0;
            value /= 10000;
            sectionIndex++;
        }
        return sb.toString();
    }
}
