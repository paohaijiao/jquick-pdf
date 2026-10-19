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
 * 单据 126：中华人民共和国海关进出境货物备案清单（customs 领域第 2 项）。
 *
 * <p>本类为独立可运行 Demo：自带 {@code main} 方法、不依赖任何公共工具类或实体类，
 * 数据以 key-value 形式通过 {@link JQuickPdfFactory#bindAll(Map)} 注入，
 * 版式全部落在模板文件 {@code demo/biz/customs/126_entry_exit_record_list.txt} 中。</p>
 *
 * <p>视觉主题「靛蓝·素雅·票式」：主色 #297586、辅色 #912745、浅底 #F1F5F6；
 * 版式为 ticket 抬头 + strip 信息区 + light 明细表 + right 签章区 + table 落款，
 * 与同领域其它单据互不重样。</p>
 *
 * <p>对标海关进出境货物备案清单票样：备案清单编号、经营单位、备案性质、</p>
 * <p>货物 HS 编码与备案总价、录入单位与海关签章栏。</p>
 *
 * <p>运行后输出：{@code D:/test/pdf/126_entry_exit_record_list.pdf}。</p>
 *
 * @author Martin
 * @version 1.0.0
 */
public class JQuickBiz126EntryExitRecordList {

    private static final String PDF_DIR = "D:/test/pdf/";
    private static final String OUT_FILE = PDF_DIR + "126_entry_exit_record_list.pdf";
    private static final String TEMPLATE = "demo/biz/customs/126_entry_exit_record_list.txt";

    /** 模板固定明细行数，不足时补空行，避免占位符原样输出。 */
    private static final int ITEM_ROWS = 6;

    public static void main(String[] args) throws IOException {
        new File(PDF_DIR).mkdirs();

        // ---------- 1. 数据（无实体类，直接 bind）----------
        String[][] items = {
                {"1", "8542.3100", "集成电路 IC-8800", "12000 个", "中国台湾", "1.86", "22320.00"},
                {"2", "8534.0000", "印刷电路板 PCB-6L", "3600 片", "中国台湾", "4.20", "15120.00"},
                {"3", "8541.1000", "二极管 DIO-40V", "50000 个", "韩国", "0.03", "1500.00"},
                {"4", "8533.2100", "贴片电阻 RES-1K", "80000 个", "韩国", "0.01", "800.00"},
                {"5", "—", "电子元器件辅料", "1 批", "日本", "860.00", "860.00"},
                {"6", "—", "备用包装材料", "1 批", "中国", "320.00", "320.00"}
        };

        Map<String, Object> data = new LinkedHashMap<String, Object>();
        data.put("recordNo", "220120261012004411");
        data.put("recordDate", "2026年10月09日");
        data.put("portName", "××海关××口岸");
        data.put("operatorName", "××保税物流有限公司　91330100MA2H5T71XB");
        data.put("counterpart", "××保税物流有限公司");
        data.put("recordType", "保税仓储 / 进境备案");
        data.put("transport", "公路运输 / 苏A·D72K18");
        data.put("inputUnit", "××报关服务有限公司");
        data.put("refDeclarationNo", "220120261012003388");
        data.put("validUntil", "2026年12月31日");
        data.put("remark", "本备案清单适用于保税区、出口加工区等特殊监管区域进出境货物，不作正式报关单使用，货物实际离境时另行申报。");
        data.put("declarer", "崔行知");
        data.put("inputer", "苏见宁");
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

        data.put("totalLine", "备案总价（大写）" + toChineseAmount(itemTotal) + " 美元　　（小写）USD " + itemTotal.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString());

        // ---------- 2. 渲染 ----------
        JPdfConfig config = new JPdfConfig();
        config.getLayoutConfig().setFlexLayout(true);
        config.getDoc().setMargins(Arrays.asList(44, 44, 44, 44));
        config.getFontConfig().setDefaultFontName("Noto Sans CJK SC");
        config.getHeaderConfig().setEnabled(true);
        config.getHeaderConfig().setText("××海关　进出境货物备案清单　220120261012004411");
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
