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
 * 单据 135：出口退税申报单 / 退税回执单（customs 领域第 11 项）。
 *
 * <p>本类为独立可运行 Demo：自带 {@code main} 方法、不依赖任何公共工具类或实体类，
 * 数据以 key-value 形式通过 {@link JQuickPdfFactory#bindAll(Map)} 注入，
 * 版式全部落在模板文件 {@code demo/biz/customs/135_export_tax_refund.txt} 中。</p>
 *
 * <p>视觉主题「宝蓝·斑马·通栏」：主色 #133FC2、辅色 #914527、浅底 #EFF1F8；
 * 版式为 banner 抬头 + kv 信息区 + striped 明细表 + card 签章区 + table 落款，
 * 与同领域其它单据互不重样。</p>
 *
 * <p>出口退税申报单：退税所属期与报关单号、逐项出口销售额与折人民币金额、</p>
 * <p>征退税率与应退税额合计、退税机关受理栏。</p>
 *
 * <p>运行后输出：{@code D:/test/pdf/135_export_tax_refund.pdf}。</p>
 *
 * @author Martin
 * @version 1.0.0
 */
public class JQuickBiz135ExportTaxRefund {

    private static final String PDF_DIR = "D:/test/pdf/";
    private static final String OUT_FILE = PDF_DIR + "135_export_tax_refund.pdf";
    private static final String TEMPLATE = "demo/biz/customs/135_export_tax_refund.txt";

    /** 模板固定明细行数，不足时补空行，避免占位符原样输出。 */
    private static final int ITEM_ROWS = 6;

    public static void main(String[] args) throws IOException {
        new File(PDF_DIR).mkdirs();

        // ---------- 1. 数据（无实体类，直接 bind）----------
        String[][] items = {
                {"1", "伺服电机 SM-500W", "1796.80", "12793.22", "13%", "13%", "1663.12"},
                {"2", "精密齿轮箱 GB-120", "4055.20", "28872.90", "13%", "13%", "3753.48"},
                {"3", "运动控制卡 MC-8020", "1952.40", "13900.61", "13%", "13%", "1807.08"},
                {"4", "三坐标测量仪 CMM-1210", "29800.00", "212176.00", "13%", "13%", "27582.88"},
                {"5", "工业阀门组件 IV-50", "2076.00", "14780.16", "13%", "13%", "1921.42"},
                {"6", "技术资料及软件", "1200.00", "8544.00", "13%", "13%", "1110.72"}
        };

        Map<String, Object> data = new LinkedHashMap<String, Object>();
        data.put("declareNo", "TS-20261013-0221");
        data.put("declareDate", "2026年10月13日");
        data.put("taxOrg", "国家税务总局××市××区税务局");
        data.put("taxNo", "91330100MA2H5T71XB");
        data.put("companyName", "××国际贸易有限公司");
        data.put("period", "2026年09月");
        data.put("invoiceNo", "EXP-INV-2026-0918");
        data.put("declarationNo", "220120261012003388");
        data.put("refundRate", "13%");
        data.put("bankAccount", "中国银行××支行　6217 ×××× ×××× 8890");
        data.put("remark", "出口退税申报须在出口之日次年 4 月 30 日前的各申报期内完成；单证收齐且信息齐全后方可办理退税。");
        data.put("declarer", "崔行知");
        data.put("reviewer", "柳文渊");
        data.put("taxOfficer", "××区税务局");

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
            itemTotal = itemTotal.add(new BigDecimal(items[i][3]));
            data.put("itemQty" + n, items[i][3]);
            data.put("itemPrice" + n, items[i][4]);
            data.put("itemAmount" + n, items[i][5]);
            data.put("itemTax" + n, items[i][6]);
        }

        data.put("totalAmount", itemTotal.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString());
        data.put("totalAmountInWords", toChineseAmount(itemTotal));

        data.put("refundTotal", new BigDecimal("37838.70").setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString());

        data.put("totalLine", "出口销售额合计（大写）" + toChineseAmount(itemTotal) + "　　（小写）￥" + itemTotal.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString());

        data.put("refundLine", "应退税额合计（大写）" + toChineseAmount(new BigDecimal("37838.70")) + "　　（小写）￥37838.70");

        // ---------- 2. 渲染 ----------
        JPdfConfig config = new JPdfConfig();
        config.getLayoutConfig().setFlexLayout(true);
        config.getDoc().setMargins(Arrays.asList(48, 48, 48, 48));
        config.getFontConfig().setDefaultFontName("Noto Sans CJK SC");
        config.getHeaderConfig().setEnabled(true);
        config.getHeaderConfig().setText("××国际贸易有限公司　出口退税申报单　TS-20261013-0221");
        config.getHeaderConfig().setFontSize(7.5f);
        config.getHeaderConfig().setHeight(34);
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
