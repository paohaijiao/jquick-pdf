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
package com.github.paohaijiao.biz.finance;

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
 * 单据 103：机动车销售统一发票（finance 领域第 4 项）。
 *
 * <p>本类为独立可运行 Demo：自带 {@code main} 方法、不依赖任何公共工具类或实体类，
 * 数据以 key-value 形式通过 {@link JQuickPdfFactory#bindAll(Map)} 注入，
 * 版式全部落在模板文件 {@code demo/biz/finance/103_motor_vehicle_sales_invoice.txt} 中。</p>
 *
 * <p>视觉主题「绯红·密排·下线」：主色 #9D321E、辅色 #279170、浅底 #F7F1F0；
 * 版式为 underline 抬头 + kv 信息区 + compact 明细表 + split 签章区 + plain 落款，
 * 与同领域其它单据互不重样。</p>
 *
 * <p>对标机动车销售统一发票票样：车辆识别代号、合格证号、购车人身份信息，以及</p>
 * <p>不含税价、增值税税额、价税合计与发票专用章占位。</p>
 *
 * <p>运行后输出：{@code D:/test/pdf/103_motor_vehicle_sales_invoice.pdf}。</p>
 *
 * @author Martin
 * @version 1.0.0
 */
public class JQuickBiz103MotorVehicleSalesInvoice {

    private static final String PDF_DIR = "D:/test/pdf/";
    private static final String OUT_FILE = PDF_DIR + "103_motor_vehicle_sales_invoice.pdf";
    private static final String TEMPLATE = "demo/biz/finance/103_motor_vehicle_sales_invoice.txt";

    /** 模板固定明细行数，不足时补空行，避免占位符原样输出。 */
    private static final int ITEM_ROWS = 5;

    public static void main(String[] args) throws IOException {
        new File(PDF_DIR).mkdirs();

        // ---------- 1. 数据（无实体类，直接 bind）----------
        String[][] items = {
                {"轻型厢式货车", "××牌 NJ5045XXY", "中国", "WAD2309188", "LSVAA1234N2088765", "128318.58"},
                {"车辆装饰", "整车贴膜 + 脚垫", "中国", "—", "—", "2654.87"},
                {"代办保险服务", "交强险 + 商业险", "—", "—", "—", "5660.38"},
                {"上牌服务费", "代办上牌", "—", "—", "—", "943.40"},
                {"延保服务", "三年延保", "—", "—", "—", "3539.82"}
        };

        Map<String, Object> data = new LinkedHashMap<String, Object>();
        data.put("invoiceCode", "121002200411");
        data.put("invoiceNo", "00663721");
        data.put("issueDate", "2026年09月25日");
        data.put("machineNo", "339012088765");
        data.put("buyerName", "××物流有限公司 / 91320500MA1M8T3X7Y");
        data.put("sellerName", "××汽车销售服务有限公司");
        data.put("sellerTaxNo", "91320500MA1N5P82WD");
        data.put("sellerAddressPhone", "××市××区汽车大道 168 号　0512-66××××××");
        data.put("remark", "本发票为机动车销售统一发票，一车一票；车辆识别代号与合格证号须与实车一致。");
        data.put("payee", "崔行知");
        data.put("reviewer", "柳文渊");
        data.put("drawer", "苏见宁");

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
            data.put("itemQty" + n, items[i][3]);
            data.put("itemPrice" + n, items[i][4]);
            itemTotal = itemTotal.add(new BigDecimal(items[i][5]));
            data.put("itemAmount" + n, items[i][5]);
        }

        data.put("totalAmount", itemTotal.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString());
        data.put("totalAmountInWords", toChineseAmount(itemTotal));

        data.put("totalLine", "合计金额（大写）" + toChineseAmount(itemTotal) + "　　（小写）￥" + itemTotal.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString());

        data.put("taxLine", "增值税税额：￥" + itemTotal.multiply(new BigDecimal("0.13")).setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "　　征收率 13%");

        // ---------- 2. 渲染 ----------
        JPdfConfig config = new JPdfConfig();
        config.getLayoutConfig().setFlexLayout(true);
        config.getDoc().setMargins(Arrays.asList(48, 48, 48, 48));
        config.getFontConfig().setDefaultFontName("Noto Sans CJK SC");
        config.getHeaderConfig().setEnabled(true);
        config.getHeaderConfig().setText("××汽车销售服务有限公司　机动车销售统一发票　00663721");
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
