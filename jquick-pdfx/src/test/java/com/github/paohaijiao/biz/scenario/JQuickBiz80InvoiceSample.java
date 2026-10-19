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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 场景 80：增值税普通发票票样 PDF（模板文件 + bind 数据注入版，模拟票样，非真实发票）。
 *
 * <p>本类不定义任何实体类，全部数据通过 {@link JQuickPdfFactory#bindAll(Map)}
 * 以 key-value 形式注入；版式全部落在模板文件
 * {@code report/biz/80_invoice_sample.txt} 中。</p>
 *
 * <p>明细行逐条 bind 为 dN_xxx；金额（不含税 / 税额 / 价税合计 / 大写）由 Java 侧用
 * BigDecimal 计算后 bind。模板不支持 colspan，合计行空位逐列 '—' 占位。</p>
 *
 * <p><b>合规声明：本模板仅用于系统开发调试与打印样式验证，不是真实发票，不得用于报销、抵扣或任何税务用途。</b></p>
 *
 * <p>运行后输出：{@code D:/test/pdf/80_invoice_sample.pdf}。</p>
 *
 * @author Martin
 * @version 2.0.0
 */
public class JQuickBiz80InvoiceSample {

    private static final String PDF_DIR = "D:/test/pdf/";
    private static final String IMG_DIR = "D:/test/image/";
    private static final String OUT_FILE = PDF_DIR + "80_invoice_sample.pdf";
    private static final String TEMPLATE = "report/biz/80_invoice_sample.txt";

    public static void main(String[] args) throws Exception {
        new File(PDF_DIR).mkdirs();
        new File(IMG_DIR).mkdirs();

        // ---------- 1. 生成 logo 与发票专用章（模板 image src 按字面量引用固定路径） ----------
        generateLogo(IMG_DIR + "80_logo.png");
        generateInvoiceSeal(IMG_DIR + "80_seal.png");

        // ---------- 2. 组织数据（普通 Map，无实体类）----------
        Map<String, Object> data = new LinkedHashMap<String, Object>();
        data.put("invoiceNo", "00000000");
        data.put("invoiceDate", "2026年10月19日");
        data.put("buyerName", "××城市商业银行股份有限公司");
        data.put("buyerTaxNo", "91310000MA1FL8XXXX");
        data.put("buyerAddressPhone", "××市浦东新区××路 88 号　021-6123-4567");
        data.put("buyerBankAccount", "工商银行××支行 1001 2345 6789 0123");
        data.put("sellerName", "××数字科技有限公司");
        data.put("sellerTaxNo", "91310115MA1K9XXXX");
        data.put("sellerAddressPhone", "××市××区科技园 8 号楼　021-6666-5678");
        data.put("sellerBankAccount", "建设银行××支行 3100 1234 5678 9012");

        // 明细行：名称 / 规格 / 单位 / 数量 / 不含税单价
        List<String[]> items = new ArrayList<String[]>();
        items.add(new String[]{"＊软件＊PDF 报表引擎企业版", "JQ-ENT-5N", "套", "5", "10619.47"});
        items.add(new String[]{"＊服务＊模板迁移服务", "MIGRATE", "项", "1", "28318.58"});
        items.add(new String[]{"＊服务＊现场培训服务", "TRAINING", "场", "2", "3773.58"});

        BigDecimal taxRate = new BigDecimal("0.06");
        BigDecimal sum = BigDecimal.ZERO;
        String[] keys = {"name", "spec", "unit", "qty", "price"};
        for (int i = 0; i < items.size(); i++) {
            String[] it = items.get(i);
            for (int j = 0; j < keys.length; j++) {
                data.put("d" + (i + 1) + "_" + keys[j], it[j]);
            }
            BigDecimal amount = new BigDecimal(it[4]).multiply(new BigDecimal(it[3])).setScale(2, RoundingMode.HALF_UP);
            data.put("d" + (i + 1) + "_amount", amount.toPlainString());
            sum = sum.add(amount);
        }
        BigDecimal taxAmount = sum.multiply(taxRate).setScale(2, RoundingMode.HALF_UP);
        BigDecimal total = sum.add(taxAmount).setScale(2, RoundingMode.HALF_UP);

        data.put("taxRateText", "6%");
        data.put("amountWithoutTax", sum.toPlainString());
        data.put("taxAmount", taxAmount.toPlainString());
        data.put("total", total.toPlainString());
        data.put("totalInWords", amountInWords(total));
        data.put("remark", "合同编号 HT-2026-0918，票样仅供样式验证");

        // ---------- 3. 渲染 ----------
        JPdfConfig config = new JPdfConfig();
        config.getLayoutConfig().setFlexLayout(true);
        config.getDoc().setMargins(Arrays.asList(46, 46, 50, 46));
        // 票样水印：避免被误用
        config.getWatermarkConfig().setEnabled(true);
        config.getWatermarkConfig().setWatermarkText("票 样　非 报 销 凭 证");
        config.getWatermarkConfig().setFillOpacity(0.12f);
        config.getFooterConfig().setEnabled(true);
        config.getFooterConfig().setFontSize(8);
        config.getFooterConfig().setPageNumberFormat("第 %d 页");

        JQuickPdfFactory factory = new JQuickPdfFactory(config);
        factory.bindAll(data);
        byte[] pdf = factory.executeResource(TEMPLATE);

        FileOutputStream out = new FileOutputStream(OUT_FILE);
        out.write(pdf);
        out.close();
        System.out.println("已生成（票样）：" + OUT_FILE);
    }

    /**
     * 人民币金额大写。仅处理到「亿」，覆盖发票场景；小数部分按角分输出。
     * 例：11010.00 → 壹万壹仟零壹拾元整。
     */
    public static String amountInWords(BigDecimal amount) {
        String[] digits = {"零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖"};
        String[] units = {"", "拾", "佰", "仟"};
        String[] groups = {"", "万", "亿"};
        BigDecimal scaled = amount.setScale(2, RoundingMode.HALF_UP);
        long integer = scaled.longValue();
        int fraction = scaled.subtract(new BigDecimal(integer)).movePointRight(2).intValue();

        String number = String.valueOf(integer);
        StringBuilder result = new StringBuilder();
        int groupIndex = 0;
        boolean pendingZero = false;
        while (number.length() > 0) {
            int end = Math.max(0, number.length() - 4);
            String group = number.substring(end);
            number = number.substring(0, end);
            if (Integer.parseInt(group) == 0) {
                pendingZero = result.length() > 0;
                groupIndex++;
                continue;
            }
            StringBuilder part = new StringBuilder();
            for (int i = 0; i < group.length(); i++) {
                int d = group.charAt(i) - '0';
                int pos = group.length() - 1 - i;
                if (d == 0) {
                    if (part.length() > 0 && !part.toString().endsWith("零")) {
                        part.append("零");
                    }
                } else {
                    part.append(digits[d]).append(units[pos]);
                }
            }
            String text = part.toString();
            if (text.endsWith("零")) {
                text = text.substring(0, text.length() - 1);
            }
            if (pendingZero) {
                result.insert(0, "零");
                pendingZero = false;
            }
            result.insert(0, text + groups[groupIndex]);
            groupIndex++;
        }
        if (result.length() == 0) {
            result.append("零");
        }
        StringBuilder words = new StringBuilder(result).append("元");
        if (fraction == 0) {
            words.append("整");
        } else {
            int jiao = fraction / 10;
            int fen = fraction % 10;
            if (jiao > 0) {
                words.append(digits[jiao]).append("角");
            } else if (fen > 0) {
                words.append("零");
            }
            if (fen > 0) {
                words.append(digits[fen]).append("分");
            }
        }
        return words.toString();
    }

    private static void generateLogo(String path) throws IOException {
        BufferedImage image = new BufferedImage(88, 88, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = image.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(new Color(0x8C, 0x3B, 0x3B));
        g.fillOval(2, 2, 84, 84);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Microsoft YaHei", Font.BOLD, 26));
        g.drawString("JQ", 24, 56);
        g.dispose();
        ImageIO.write(image, "png", new File(path));
    }

    private static void generateInvoiceSeal(String path) throws IOException {
        int size = 200;
        BufferedImage image = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = image.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Color red = new Color(0xC6, 0x28, 0x28);
        g.setColor(red);
        g.setStroke(new BasicStroke(6f));
        g.drawOval(8, 8, size - 16, size - 16);
        g.setFont(new Font("Microsoft YaHei", Font.BOLD, 15));
        g.drawString("发票专用章", size / 2 - 40, 70);
        g.setFont(new Font("Microsoft YaHei", Font.PLAIN, 11));
        g.drawString("××数字科技有限公司", 22, 100);
        g.setFont(new Font("Microsoft YaHei", Font.PLAIN, 10));
        g.drawString("(票样)", size / 2 - 16, 150);
        g.dispose();
        ImageIO.write(image, "png", new File(path));
    }
}
