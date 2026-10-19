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
 * 单据 131：场站收据（DOCK RECEIPT）（customs 领域第 7 项）。
 *
 * <p>本类为独立可运行 Demo：自带 {@code main} 方法、不依赖任何公共工具类或实体类，
 * 数据以 key-value 形式通过 {@link JQuickPdfFactory#bindAll(Map)} 注入，
 * 版式全部落在模板文件 {@code demo/biz/customs/131_terminal_receipt.txt} 中。</p>
 *
 * <p>视觉主题「靛蓝·素雅·竖栏」：主色 #215E87、辅色 #91272E、浅底 #F0F4F7；
 * 版式为 side 抬头 + kv 信息区 + light 明细表 + card 签章区 + table 落款，
 * 与同领域其它单据互不重样。</p>
 *
 * <p>场站收据：场站名称与集装箱号、装箱方式、逐项收货明细（重量体积外包装状况），</p>
 * <p>场站经办人与承运人代理、托运人三方签字栏。</p>
 *
 * <p>运行后输出：{@code D:/test/pdf/131_terminal_receipt.pdf}。</p>
 *
 * @author Martin
 * @version 1.0.0
 */
public class JQuickBiz131TerminalReceipt {

    private static final String PDF_DIR = "D:/test/pdf/";
    private static final String OUT_FILE = PDF_DIR + "131_terminal_receipt.pdf";
    private static final String TEMPLATE = "demo/biz/customs/131_terminal_receipt.txt";

    /** 模板固定明细行数，不足时补空行，避免占位符原样输出。 */
    private static final int ITEM_ROWS = 6;

    public static void main(String[] args) throws IOException {
        new File(PDF_DIR).mkdirs();

        // ---------- 1. 数据（无实体类，直接 bind）----------
        String[][] items = {
                {"1", "伺服电机 SM-500W", "8 CTNS", "368.00", "0.86", "完好"},
                {"2", "精密齿轮箱 GB-120", "8 CTNS", "1960.00", "2.44", "完好"},
                {"3", "运动控制卡 MC-8020", "6 CTNS", "66.00", "0.42", "完好"},
                {"4", "三坐标测量仪 CMM-1210", "8 CTNS", "9200.00", "8.60", "完好"},
                {"5", "工业阀门组件 IV-50", "14 CTNS", "1218.00", "3.12", "完好"},
                {"6", "技术资料", "2 CTNS", "48.00", "0.18", "完好"}
        };

        Map<String, Object> data = new LinkedHashMap<String, Object>();
        data.put("receiptNo", "DR-20260919-0077");
        data.put("receiveDate", "2026年09月19日");
        data.put("terminalName", "××港区集装箱场站");
        data.put("shipper", "××国际贸易有限公司");
        data.put("consignee", "×× PRECISION MACHINERY PTE. LTD.");
        data.put("vesselNo", "××轮 / V.2608");
        data.put("blNo", "COSU6123456780");
        data.put("loadType", "整箱 FCL / 场站装箱");
        data.put("containerNo", "COSU 3312880 / 20GP");
        data.put("operator", "邵怀信");
        data.put("remark", "场站收据（D/R）是承运人或其代理收到托运货物的凭证，换取提单时须交回本收据；货物交接时外包装状况须现场注明。");
        data.put("agentSign", "崔行知");
        data.put("shipperSign", "路承志");

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
                continue;
            }
            data.put("itemName" + n, items[i][0]);
            data.put("itemSpec" + n, items[i][1]);
            data.put("itemUnit" + n, items[i][2]);
            itemTotal = itemTotal.add(new BigDecimal(items[i][3]));
            data.put("itemQty" + n, items[i][3]);
            data.put("itemPrice" + n, items[i][4]);
            data.put("itemAmount" + n, items[i][5]);
        }

        data.put("totalAmount", itemTotal.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString());

        data.put("grossWeight", itemTotal.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString());

        data.put("measure", new BigDecimal("15.62").setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString());

        // ---------- 2. 渲染 ----------
        JPdfConfig config = new JPdfConfig();
        config.getLayoutConfig().setFlexLayout(true);
        config.getDoc().setMargins(Arrays.asList(48, 48, 48, 48));
        config.getFontConfig().setDefaultFontName("Noto Sans CJK SC");
        config.getHeaderConfig().setEnabled(true);
        config.getHeaderConfig().setText("××港区集装箱场站　场站收据　DR-20260919-0077");
        config.getHeaderConfig().setFontSize(8.5f);
        config.getHeaderConfig().setHeight(30);
        config.getFooterConfig().setEnabled(true);
        config.getFooterConfig().setFontSize(8);
        config.getFooterConfig().setPageNumberFormat("· %d ·");

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
