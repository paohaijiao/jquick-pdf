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
 * 单据 125：中华人民共和国海关进口货物报关单（customs 领域第 1 项）。
 *
 * <p>本类为独立可运行 Demo：自带 {@code main} 方法、不依赖任何公共工具类或实体类，
 * 数据以 key-value 形式通过 {@link JQuickPdfFactory#bindAll(Map)} 注入，
 * 版式全部落在模板文件 {@code demo/biz/customs/125_customs_declaration.txt} 中。</p>
 *
 * <p>视觉主题「天青·斑马·竖栏」：主色 #17786C、辅色 #912765、浅底 #F0F7F6；
 * 版式为 side 抬头 + chip 信息区 + striped 明细表 + three 签章区 + right 落款，
 * 与同领域其它单据互不重样。</p>
 *
 * <p>对标海关进出口货物报关单票样：海关编号、境内收货人 / 境外发货人、</p>
 * <p>运输方式与贸易方式、商品 HS 编码与成交总价、申报单位签章与海关验讫栏。</p>
 *
 * <p>运行后输出：{@code D:/test/pdf/125_customs_declaration.pdf}。</p>
 *
 * @author Martin
 * @version 1.0.0
 */
public class JQuickBiz125CustomsDeclaration {

    private static final String PDF_DIR = "D:/test/pdf/";
    private static final String OUT_FILE = PDF_DIR + "125_customs_declaration.pdf";
    private static final String TEMPLATE = "demo/biz/customs/125_customs_declaration.txt";

    /** 模板固定明细行数，不足时补空行，避免占位符原样输出。 */
    private static final int ITEM_ROWS = 6;

    public static void main(String[] args) throws IOException {
        new File(PDF_DIR).mkdirs();

        // ---------- 1. 数据（无实体类，直接 bind）----------
        String[][] items = {
                {"1", "8501.5100", "伺服电机 SM-500W", "8 台", "新加坡", "224.60", "1796.80"},
                {"2", "8483.4090", "精密齿轮箱 GB-120", "8 台", "新加坡", "506.90", "4055.20"},
                {"3", "8537.1090", "运动控制卡 MC-8020", "6 块", "新加坡", "325.40", "1952.40"},
                {"4", "9031.8090", "三坐标测量仪 CMM-1210", "1 台", "新加坡", "29800.00", "29800.00"},
                {"5", "8481.8040", "工业阀门组件 IV-50", "24 套", "新加坡", "86.50", "2076.00"},
                {"6", "—", "技术资料及软件", "1 套", "新加坡", "1200.00", "1200.00"}
        };

        Map<String, Object> data = new LinkedHashMap<String, Object>();
        data.put("declarationNo", "220120261012003388");
        data.put("declareDate", "2026年10月08日");
        data.put("portName", "××海关××口岸");
        data.put("consignee", "××国际贸易有限公司　91330100MA2H5T71XB");
        data.put("shipper", "×× PRECISION MACHINERY PTE. LTD.");
        data.put("endUser", "××国际贸易有限公司");
        data.put("transport", "水路运输 / ××轮 V.2608");
        data.put("tradeMode", "一般贸易 / 照章征税");
        data.put("contractNo", "SC-2026-0713");
        data.put("packageInfo", "46 件 / 12860 kg / 12140 kg");
        data.put("remark", "随附单证：合同、发票、装箱单、提（运）单、原产地证书；报关单编号为海关接受申报的唯一编号， modify 须经海关核准。");
        data.put("declarer", "崔行知");
        data.put("reviewer", "柳文渊");
        data.put("customsOfficer", "××海关");

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
            data.put("itemPrice" + n, items[i][4]);
            data.put("itemAmount" + n, items[i][5]);
            itemTotal = itemTotal.add(new BigDecimal(items[i][6]));
            data.put("itemTax" + n, items[i][6]);
        }

        data.put("totalAmount", itemTotal.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString());

        data.put("totalLine", "成交总价（大写）" + toChineseAmount(itemTotal) + " 美元　　（小写）USD " + itemTotal.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString());

        // ---------- 2. 渲染 ----------
        JPdfConfig config = new JPdfConfig();
        config.getLayoutConfig().setFlexLayout(true);
        config.getDoc().setMargins(Arrays.asList(40, 40, 40, 40));
        config.getFontConfig().setDefaultFontName("Noto Sans CJK SC");
        config.getHeaderConfig().setEnabled(true);
        config.getHeaderConfig().setText("××海关　进口货物报关单　220120261012003388");
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
