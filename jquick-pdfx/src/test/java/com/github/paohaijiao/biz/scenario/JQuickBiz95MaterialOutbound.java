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
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 场景 95：物资出库单（模板文件 + bind 数据注入版）。
 *
 * <p>本类不定义任何实体类，全部数据通过 {@link JQuickPdfFactory#bindAll(Map)} 注入；
 * 版式全部落在模板文件 {@code report/biz/95_material_outbound.txt} 中。</p>
 *
 * <p>明细 30 行按每页 16 行切块（模板两个表格块 + {@code <areaBreak/>} 分页），
 * 每块末尾「本页小计」行；行数据 bind 为 item{row}_1 ~ item{row}_9。</p>
 *
 * <p>产物：PDF {@code D:/test/pdf/95_material_outbound.pdf}；
 * 出库专用章 {@code D:/test/image/95_seal.png}。</p>
 *
 * @author Martin
 * @version 2.0.0
 */
public class JQuickBiz95MaterialOutbound {

    private static final String PDF_DIR = "D:/test/pdf/";
    private static final String IMG_DIR = "D:/test/image/";
    private static final String OUT_FILE = PDF_DIR + "95_material_outbound.pdf";
    private static final String TEMPLATE = "report/biz/95_material_outbound.txt";
    private static final String SEAL = IMG_DIR + "95_seal.png";

    /** 明细数据：编码 / 名称 / 规格 / 单位 / 申请量 / 实发量 / 单价。 */
    private static final String[][] ITEMS = {
            {"WL-1001", "热轧钢板", "Q235B 6mm×1500×6000", "张", "40", "40", "386.00"},
            {"WL-1002", "镀锌钢管", "DN50 × 3.5mm", "m", "260", "260", "28.60"},
            {"WL-1003", "角钢", "L50×50×5", "m", "400", "400", "18.40"},
            {"WL-1004", "六角螺栓", "M12×60 8.8 级", "套", "2400", "2400", "1.35"},
            {"WL-1005", "平垫圈", "M12 镀锌", "个", "5000", "4800", "0.18"},
            {"WL-1006", "电焊条", "J422 Φ3.2", "kg", "120", "120", "9.80"},
            {"WL-1007", "二氧化碳气体", "40L / 瓶", "瓶", "20", "20", "85.00"},
            {"WL-1008", "砂纸", "240 目", "张", "600", "600", "0.65"},
            {"WL-1009", "防锈底漆", "环氧富锌 20kg/桶", "桶", "12", "12", "268.00"},
            {"WL-1010", "面漆", "丙烯酸聚氨酯 18kg/桶", "桶", "10", "8", "312.00"},
            {"WL-1011", "稀释剂", "X-7 15kg/桶", "桶", "6", "6", "146.00"},
            {"WL-1012", "安全帽", "ABS 透气型 白色", "顶", "20", "20", "32.00"},
            {"WL-1013", "反光背心", "涤纶 荧光黄", "件", "30", "30", "24.50"},
            {"WL-1014", "劳保手套", "丁腈涂层", "副", "120", "120", "6.80"},
            {"WL-1015", "防护面罩", "焊接专用", "个", "12", "12", "58.00"},
            {"WL-1016", "电缆", "YJV22 4×25", "m", "200", "200", "96.50"},
            {"WL-1017", "配电箱", "XL-21 户外型", "台", "2", "2", "1860.00"},
            {"WL-1018", "LED 投光灯", "200W IP66", "套", "16", "16", "268.00"},
            {"WL-1019", "镀锌桥架", "200×100 含盖板", "m", "120", "100", "62.00"},
            {"WL-1020", "接线端子", "UK-2.5", "个", "900", "900", "0.95"},
            {"WL-1021", "穿线管", "PVC Φ25", "m", "800", "800", "3.60"},
            {"WL-1022", "防水胶带", "自粘型 100mm", "卷", "60", "60", "12.80"},
            {"WL-1023", "膨胀螺栓", "M10×80", "套", "1200", "1200", "2.10"},
            {"WL-1024", "密封胶", "中性硅酮 300ml", "支", "80", "80", "18.50"},
            {"WL-1025", "不锈钢焊丝", "ER304 Φ1.2", "kg", "30", "25", "58.00"},
            {"WL-1026", "切割片", "Φ100 树脂", "片", "200", "200", "3.20"},
            {"WL-1027", "打磨片", "Φ100 百叶", "片", "150", "150", "6.40"},
            {"WL-1028", "钢卷尺", "5m 带锁", "把", "10", "10", "22.00"},
            {"WL-1029", "水平尺", "600mm 铝合金", "把", "6", "6", "56.00"},
            {"WL-1030", "警示带", "红白 50m", "卷", "24", "24", "16.80"}
    };

    /** 每页（每个分块）明细行数。 */
    private static final int ROWS_PER_PAGE = 16;

    public static void main(String[] args) throws IOException {
        new File(PDF_DIR).mkdirs();
        new File(IMG_DIR).mkdirs();
        generateSeal(SEAL, "中央仓库");

        Map<String, Object> data = new LinkedHashMap<String, Object>();
        data.put("orderNo", "CK-2026-03-0417");
        data.put("department", "××建工集团　第三工程分公司 机电安装班组");
        data.put("project", "××产业园二期 3# 厂房钢结构与机电安装工程");
        data.put("warehouse", "××建工集团　中央仓库（A 区）");
        data.put("outDate", "2026年3月21日");
        data.put("receiver", "钱立");
        data.put("issuer", "孙慧");
        data.put("approver", "郑守义");
        data.put("remark", "部分物资（面漆、平垫圈、桥架、焊丝）库存不足，按实发出，欠发部分已于当日提交补货申请 CG-2026-0091。");

        // 明细行 + 分块小计
        BigDecimal grandTotal = BigDecimal.ZERO;
        BigDecimal blockTotal = BigDecimal.ZERO;
        int block = 1;
        for (int i = 0; i < ITEMS.length; i++) {
            if (i > 0 && i % ROWS_PER_PAGE == 0) {
                data.put("subTotal" + block, blockTotal.toPlainString());
                grandTotal = grandTotal.add(blockTotal);
                blockTotal = BigDecimal.ZERO;
                block++;
            }
            int row = i + 1;
            BigDecimal unitPrice = new BigDecimal(ITEMS[i][6]);
            int actualQty = Integer.parseInt(ITEMS[i][5]);
            int requestQty = Integer.parseInt(ITEMS[i][4]);
            BigDecimal amount = unitPrice.multiply(new BigDecimal(actualQty))
                    .setScale(2, BigDecimal.ROUND_HALF_UP);
            blockTotal = blockTotal.add(amount);
            boolean shorted = actualQty < requestQty;
            String p = "item" + row + "_";
            data.put(p + "1", String.valueOf(row));
            data.put(p + "2", ITEMS[i][0]);
            data.put(p + "3", ITEMS[i][1]);
            data.put(p + "4", ITEMS[i][2]);
            data.put(p + "5", ITEMS[i][3]);
            data.put(p + "6", ITEMS[i][4]);
            data.put(p + "6c", shorted ? "#B71C1C" : "#333333");
            data.put(p + "6b", shorted ? "true" : "false");
            data.put(p + "7", ITEMS[i][5]);
            data.put(p + "8", unitPrice.toPlainString());
            data.put(p + "9", amount.toPlainString());
        }
        data.put("subTotal" + block, blockTotal.toPlainString());
        grandTotal = grandTotal.add(blockTotal);

        data.put("grandTotal", grandTotal.toPlainString());
        data.put("amountWords", amountInWords(grandTotal));

        JPdfConfig config = new JPdfConfig();
        config.getLayoutConfig().setFlexLayout(true);
        config.getDoc().setMargins(Arrays.asList(56, 58, 54, 58));
        config.getHeaderConfig().setEnabled(true);
        config.getHeaderConfig().setText("物资出库单　" + data.get("orderNo"));
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

    /**
     * 人民币金额大写（到分）。
     */
    public static String amountInWords(BigDecimal amount) {
        String[] digits = {"零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖"};
        String[] units = {"", "拾", "佰", "仟"};
        String[] groups = {"", "万", "亿"};
        BigDecimal scaled = amount.setScale(2, BigDecimal.ROUND_HALF_UP);
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

    /** 生成出库专用章。 */
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
        g.drawString("出库专用", 24, size / 2 + 16);
        g.setFont(new Font("Microsoft YaHei", Font.PLAIN, 11));
        g.drawString("2026-03-21", 44, size / 2 + 34);
        g.dispose();
        ImageIO.write(image, "png", new File(path));
    }
}
