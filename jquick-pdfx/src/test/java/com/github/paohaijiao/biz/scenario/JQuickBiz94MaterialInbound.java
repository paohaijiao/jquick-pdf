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
package com.github.paohaijiao.biz.scenario;

import com.github.paohaijiao.config.JPdfConfig;
import com.github.paohaijiao.executor.JQuickPdfFactory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 场景 94：物资入库单（模板文件 + bind 数据注入版）。
 *
 * <p>本类不定义任何实体类，全部数据通过 {@link JQuickPdfFactory#bindAll(Map)} 注入；
 * 版式全部落在模板文件 {@code report/biz/94_material_inbound.txt} 中。</p>
 *
 * <p>明细行逐行 bind 为 iCode1~20 / iName1~20 / iSpec1~20 / iUnit1~20 / iQty1~20 /
 * iPrice1~20 / iAmount1~20；合计行逐列占位（引擎不支持 colspan）。</p>
 *
 * <p>产物：PDF {@code D:/test/pdf/94_material_inbound.pdf}；
 * 入库专用章 {@code D:/test/image/94_seal.png}。</p>
 *
 * @author Martin
 * @version 2.0.0
 */
public class JQuickBiz94MaterialInbound {

    private static final String PDF_DIR = "D:/test/pdf/";
    private static final String IMG_DIR = "D:/test/image/";
    private static final String OUT_FILE = PDF_DIR + "94_material_inbound.pdf";
    private static final String TEMPLATE = "report/biz/94_material_inbound.txt";

    /** 明细行原始数据：编码 / 名称 / 规格 / 单位 / 数量 / 单价。 */
    private static final String[][] ITEMS = {
            {"WL-1001", "热轧钢板", "Q235B 6mm×1500×6000", "张", "120", "386.00"},
            {"WL-1002", "镀锌钢管", "DN50 × 3.5mm", "m", "860", "28.60"},
            {"WL-1003", "角钢", "L50×50×5", "m", "1200", "18.40"},
            {"WL-1004", "六角螺栓", "M12×60 8.8 级", "套", "8000", "1.35"},
            {"WL-1005", "平垫圈", "M12 镀锌", "个", "16000", "0.18"},
            {"WL-1006", "电焊条", "J422 Φ3.2", "kg", "500", "9.80"},
            {"WL-1007", "二氧化碳气体", "40L / 瓶", "瓶", "60", "85.00"},
            {"WL-1008", "砂纸", "240 目", "张", "2000", "0.65"},
            {"WL-1009", "防锈底漆", "环氧富锌 20kg/桶", "桶", "40", "268.00"},
            {"WL-1010", "面漆", "丙烯酸聚氨酯 18kg/桶", "桶", "36", "312.00"},
            {"WL-1011", "稀释剂", "X-7 15kg/桶", "桶", "20", "146.00"},
            {"WL-1012", "安全帽", "ABS 透气型 白色", "顶", "80", "32.00"},
            {"WL-1013", "反光背心", "涤纶 荧光黄", "件", "100", "24.50"},
            {"WL-1014", "劳保手套", "丁腈涂层", "副", "400", "6.80"},
            {"WL-1015", "防护面罩", "焊接专用", "个", "40", "58.00"},
            {"WL-1016", "电缆", "YJV22 4×25", "m", "620", "96.50"},
            {"WL-1017", "配电箱", "XL-21 户外型", "台", "6", "1860.00"},
            {"WL-1018", "LED 投光灯", "200W IP66", "套", "48", "268.00"},
            {"WL-1019", "镀锌桥架", "200×100 含盖板", "m", "380", "62.00"},
            {"WL-1020", "接线端子", "UK-2.5", "个", "3000", "0.95"}};

    public static void main(String[] args) throws IOException {
        mkdirs();
        generateSeal(IMG_DIR + "94_seal.png", "中央仓库");

        Map<String, Object> data = new LinkedHashMap<String, Object>();
        data.put("orderNo", "RK-2026-03-0215");
        data.put("supplier", "××钢铁物资有限公司");
        data.put("warehouse", "××建工集团　中央仓库（A 区）");
        data.put("inboundDate", "2026年3月18日");
        data.put("purchaseNo", "CG-2026-0087");
        data.put("operator", "刘承");
        data.put("keeper", "孙慧");

        BigDecimal totalAmount = BigDecimal.ZERO;
        int totalQty = 0;
        for (int i = 0; i < ITEMS.length; i++) {
            String[] row = ITEMS[i];
            int qty = Integer.parseInt(row[4]);
            BigDecimal amount = new BigDecimal(row[5]).multiply(new BigDecimal(qty))
                    .setScale(2, RoundingMode.HALF_UP);
            totalQty += qty;
            totalAmount = totalAmount.add(amount);
            data.put("iCode" + (i + 1), row[0]);
            data.put("iName" + (i + 1), row[1]);
            data.put("iSpec" + (i + 1), row[2]);
            data.put("iUnit" + (i + 1), row[3]);
            data.put("iQty" + (i + 1), row[4]);
            data.put("iPrice" + (i + 1), row[5]);
            data.put("iAmount" + (i + 1), amount.toPlainString());
        }
        data.put("totalQty", totalQty);
        data.put("totalAmount", totalAmount.toPlainString());
        data.put("amountWords", amountInWords(totalAmount));
        data.put("acceptResult", "经清点数量、核对规格型号与质保资料，全部符合采购订单要求，其中钢材已抽样送检，"
                + "质保书、合格证、材质单齐全，验收合格，准予入库。");

        JPdfConfig config = new JPdfConfig();
        config.getLayoutConfig().setFlexLayout(true);
        config.getDoc().setMargins(Arrays.asList(52, 58, 52, 58));
        config.getHeaderConfig().setEnabled(true);
        config.getHeaderConfig().setText("物资入库单　RK-2026-03-0215");
        config.getHeaderConfig().setFontSize(8);
        config.getHeaderConfig().setHeight(32);
        config.getFooterConfig().setEnabled(true);
        config.getFooterConfig().setFontSize(8);
        config.getFooterConfig().setPageNumberFormat("第 %d 页");

        JQuickPdfFactory factory = new JQuickPdfFactory(config);
        factory.bindAll(data);
        byte[] pdf = factory.executeResource(TEMPLATE);

        FileOutputStream out = new FileOutputStream(OUT_FILE);
        out.write(pdf);
        out.close();
        System.out.println("已生成：" + OUT_FILE);
    }

    private static void mkdirs() {
        new File(PDF_DIR).mkdirs();
        new File(IMG_DIR).mkdirs();
    }

    /**
     * 人民币金额大写（到分）。示例：1234.50 → 壹仟贰佰叁拾肆元伍角整。
     */
    public static String amountInWords(BigDecimal amount) {
        String[] digits = {"零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖"};
        String[] units = {"", "拾", "佰", "仟"};
        String[] groups = {"", "万", "亿"};
        BigDecimal scaled = amount.setScale(2, RoundingMode.HALF_UP);
        long integer = scaled.longValue();
        int fraction = scaled.subtract(new BigDecimal(integer)).movePointRight(2).intValue();

        StringBuilder result = new StringBuilder();
        if (integer == 0) {
            result.append("零");
        } else {
            String number = String.valueOf(integer);
            int groupIndex = 0;
            boolean pendingZero = false;
            while (number.length() > 0) {
                int end = Math.max(0, number.length() - 4);
                String group = number.substring(end);
                number = number.substring(0, end);
                int groupValue = Integer.parseInt(group);
                if (groupValue == 0) {
                    pendingZero = result.length() > 0;
                    groupIndex++;
                    continue;
                }
                if (pendingZero) {
                    result.insert(0, "零");
                    pendingZero = false;
                }
                StringBuilder groupText = new StringBuilder();
                char[] cs = group.toCharArray();
                boolean zeroFlag = false;
                for (int i = 0; i < cs.length; i++) {
                    int digit = cs[i] - '0';
                    int power = cs.length - 1 - i;
                    if (digit == 0) {
                        zeroFlag = true;
                    } else {
                        if (zeroFlag && groupText.length() > 0) {
                            groupText.append("零");
                        }
                        zeroFlag = false;
                        groupText.append(digits[digit]).append(units[power]);
                    }
                }
                result.insert(0, groupText.append(groups[groupIndex]).toString());
                groupIndex++;
            }
        }
        result.append("元");
        int jiao = fraction / 10;
        int fen = fraction % 10;
        if (jiao == 0 && fen == 0) {
            result.append("整");
        } else {
            if (jiao > 0) {
                result.append(digits[jiao]).append("角");
            } else {
                result.append("零");
            }
            if (fen > 0) {
                result.append(digits[fen]).append("分");
            } else {
                result.append("整");
            }
        }
        return result.toString();
    }

    /** 生成入库专用章（圆章 + 外圈文字）。 */
    private static void generateSeal(String path, String warehouse) throws IOException {
        int size = 200;
        BufferedImage image = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = image.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Color red = new Color(0xC6, 0x28, 0x28);
        g.setColor(red);
        g.setStroke(new BasicStroke(6f));
        g.drawOval(8, 8, size - 16, size - 16);
        g.setFont(new Font("Microsoft YaHei", Font.BOLD, 15));
        g.drawString(warehouse, 14, 56);
        g.setFont(new Font("Microsoft YaHei", Font.BOLD, 14));
        g.drawString("入库专用", 24, size / 2 + 16);
        g.setFont(new Font("Microsoft YaHei", Font.PLAIN, 11));
        g.drawString("2026-03-18", 44, size / 2 + 34);
        g.dispose();
        ImageIO.write(image, "png", new File(path));
    }
}
