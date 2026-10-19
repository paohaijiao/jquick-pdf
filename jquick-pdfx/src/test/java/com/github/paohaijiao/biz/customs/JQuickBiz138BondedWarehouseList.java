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
 * 单据 138：保税仓出入库清单 / 中转货物清单（customs 领域第 14 项）。
 *
 * <p>本类为独立可运行 Demo：自带 {@code main} 方法、不依赖任何公共工具类或实体类，
 * 数据以 key-value 形式通过 {@link JQuickPdfFactory#bindAll(Map)} 注入，
 * 版式全部落在模板文件 {@code demo/biz/customs/138_bonded_warehouse_list.txt} 中。</p>
 *
 * <p>视觉主题「紫棠·密排·票式」：主色 #2D0F7F、辅色 #917C27、浅底 #F2EFF8；
 * 版式为 ticket 抬头 + strip 信息区 + compact 明细表 + three 签章区 + right 落款，
 * 与同领域其它单据互不重样。</p>
 *
 * <p>保税仓出入库清单 / 中转货物清单：E 账册编号与库位区间、作业类型，</p>
 * <p>逐项商品数量金额与在库状态、库存金额合计与三方签字栏。</p>
 *
 * <p>运行后输出：{@code D:/test/pdf/138_bonded_warehouse_list.pdf}。</p>
 *
 * @author Martin
 * @version 1.0.0
 */
public class JQuickBiz138BondedWarehouseList {

    private static final String PDF_DIR = "D:/test/pdf/";
    private static final String OUT_FILE = PDF_DIR + "138_bonded_warehouse_list.pdf";
    private static final String TEMPLATE = "demo/biz/customs/138_bonded_warehouse_list.txt";

    /** 模板固定明细行数，不足时补空行，避免占位符原样输出。 */
    private static final int ITEM_ROWS = 6;

    public static void main(String[] args) throws IOException {
        new File(PDF_DIR).mkdirs();

        // ---------- 1. 数据（无实体类，直接 bind）----------
        String[][] items = {
                {"1", "进口婴幼儿配方奶粉 800g", "入库", "1200 罐", "A-01 ~ A-06", "321600.00", "在库"},
                {"2", "进口保健食品 60 粒装", "入库", "860 瓶", "A-07 ~ A-12", "159960.00", "在库"},
                {"3", "进口化妆品套装", "入库", "540 套", "A-13 ~ A-18", "177120.00", "在库"},
                {"4", "进口纸尿裤 L 码", "中转出库", "2000 包", "T-中转区", "192000.00", "已出库"},
                {"5", "进口宠物食品 5kg", "入库", "680 袋", "A-19 ~ A-24", "107440.00", "在库"},
                {"6", "进口小家电配件", "中转出库", "320 件", "T-中转区", "27520.00", "已出库"}
        };

        Map<String, Object> data = new LinkedHashMap<String, Object>();
        data.put("listNo", "BW-20261016-0112");
        data.put("operateDate", "2026年10月16日");
        data.put("warehouseName", "××综合保税区 B 区 2 号库");
        data.put("accountNo", "E2026000088");
        data.put("operatorName", "××保税物流有限公司");
        data.put("operateType", "入库 + 中转出库");
        data.put("transport", "苏A·D72K18 / 集装箱 COSU 3312880");
        data.put("refRecordNo", "BS-20261012-0166");
        data.put("locationRange", "A-01 ~ A-24 / T-中转区");
        data.put("keeper", "路承志");
        data.put("remark", "保税仓储货物须账册平衡，出入库须实时核注；中转货物在监管区内流转，不得擅自运离监管区。");
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
            itemTotal = itemTotal.add(new BigDecimal(items[i][5]));
            data.put("itemAmount" + n, items[i][5]);
            data.put("itemTax" + n, items[i][6]);
        }

        data.put("totalAmount", itemTotal.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString());
        data.put("totalAmountInWords", toChineseAmount(itemTotal));

        data.put("totalLine", "库存金额合计（大写）" + toChineseAmount(itemTotal) + "　　（小写）￥" + itemTotal.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString());

        // ---------- 2. 渲染 ----------
        JPdfConfig config = new JPdfConfig();
        config.getLayoutConfig().setFlexLayout(true);
        config.getDoc().setMargins(Arrays.asList(44, 44, 44, 44));
        config.getFontConfig().setDefaultFontName("Noto Sans CJK SC");
        config.getHeaderConfig().setEnabled(true);
        config.getHeaderConfig().setText("××综合保税区　保税仓出入库清单　BW-20261016-0112");
        config.getHeaderConfig().setFontSize(7.5f);
        config.getHeaderConfig().setHeight(30);
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
