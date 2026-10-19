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
 * 场景 71：商务报价单 PDF（明细表格、页码、电子公章、金额加粗，模板文件 + bind 数据注入版）。
 *
 * <p>本类不定义任何实体类，全部数据通过 {@link JQuickPdfFactory#bindAll(Map)}
 * 以 key-value 形式注入；版式全部落在模板文件 {@code report/biz/71_quotation.txt} 中。</p>
 *
 * <p>产物目录约定：</p>
 * <ul>
 *   <li>PDF：{@code D:/test/pdf/71_quotation.pdf}</li>
 *   <li>logo / 公章：{@code D:/test/image/71_logo.png}、{@code D:/test/image/71_seal.png}</li>
 * </ul>
 *
 * @author Martin
 * @version 2.0.0
 */
public class JQuickBiz71Quotation {

    private static final String PDF_DIR = "D:/test/pdf/";
    private static final String IMG_DIR = "D:/test/image/";
    private static final String OUT_FILE = PDF_DIR + "71_quotation.pdf";
    private static final String TEMPLATE = "report/biz/71_quotation.txt";

    public static void main(String[] args) throws IOException {
        new File(PDF_DIR).mkdirs();
        new File(IMG_DIR).mkdirs();

        // ---------- 1. 图片：logo + 椭圆合同专用章 ----------
        generateLogo(IMG_DIR + "71_logo.png");
        generateSeal(IMG_DIR + "71_seal.png", "××数字科技有限公司");

        // ---------- 2. 数据（普通 Map，无实体类）----------
        Map<String, Object> data = new LinkedHashMap<String, Object>();
        data.put("quoteNo", "BJ-2026-1019-0032");
        data.put("customer", "××城市商业银行股份有限公司");
        data.put("contact", "周敏");
        data.put("phone", "021-6123-4567");
        data.put("quoteDate", "2026年10月19日");
        data.put("validUntil", "2026年11月30日");

        // 明细逐行 bind 为 itemNameN / itemSpecN / itemQtyN / itemPriceN / itemAmountN
        String[][] items = {
                {"企业级 PDF 报表引擎（标准版）", "JQ-PDF-STD", "2", "68000.00"},
                {"报表模板定制开发", "CUSTOM", "12", "2600.00"},
                {"SVG 图表组件包", "JQ-CHART", "1", "15800.00"},
                {"历史报表迁移服务", "MIGRATE", "1", "32000.00"},
                {"现场部署与培训", "TRAINING", "4", "4800.00"}
        };
        BigDecimal total = BigDecimal.ZERO;
        for (int i = 0; i < items.length; i++) {
            BigDecimal unitPrice = new BigDecimal(items[i][3]);
            BigDecimal amount = unitPrice.multiply(new BigDecimal(items[i][2])).setScale(2, RoundingMode.HALF_UP);
            total = total.add(amount);
            String suffix = String.valueOf(i + 1);
            data.put("itemName" + suffix, items[i][0]);
            data.put("itemSpec" + suffix, items[i][1]);
            data.put("itemQty" + suffix, items[i][2]);
            data.put("itemPrice" + suffix, unitPrice.toPlainString());
            data.put("itemAmount" + suffix, amount.toPlainString());
        }

        data.put("totalAmount", total.setScale(2, RoundingMode.HALF_UP).toPlainString());
        data.put("totalWords", amountInWords(total));
        data.put("remark", "1. 以上报价含 13% 增值税专用发票；2. 报价含一年远程技术支持，现场服务另计差旅；"
                + "3. 付款方式：合同签订后 30%，上线验收后 60%，质保期满后 10%。");

        // ---------- 3. 渲染 ----------
        JPdfConfig config = new JPdfConfig();
        config.getLayoutConfig().setFlexLayout(true);
        config.getDoc().setMargins(Arrays.asList(45, 45, 55, 45));
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
     * 金额转人民币大写，例如 42070.00 → 肆万贰仟零柒拾元整。
     */
    public static String amountInWords(BigDecimal amount) {
        String[] digits = {"零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖"};
        String[] units = {"", "拾", "佰", "仟"};
        String[] groups = {"", "万", "亿", "万亿"};
        long value = amount.setScale(0, RoundingMode.HALF_UP).longValue();
        String yuan = String.valueOf(value);
        StringBuilder result = new StringBuilder();
        int groupIndex = 0;
        boolean needZero = false;
        while (yuan.length() > 0) {
            int end = Math.max(0, yuan.length() - 4);
            String group = yuan.substring(end);
            yuan = yuan.substring(0, end);
            StringBuilder part = new StringBuilder();
            int groupValue = Integer.parseInt(group);
            if (groupValue == 0) {
                if (result.length() > 0) {
                    needZero = true;
                }
                groupIndex++;
                continue;
            }
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
            if (needZero && result.length() > 0) {
                result.insert(0, "零");
            }
            result.insert(0, text + groups[groupIndex]);
            needZero = false;
            groupIndex++;
        }
        return (result.length() == 0 ? "零" : result.toString()) + "元整";
    }

    private static void generateLogo(String path) throws IOException {
        BufferedImage image = new BufferedImage(104, 104, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = image.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(new Color(0x1F, 0x4E, 0x79));
        g.fillRoundRect(2, 2, 100, 100, 18, 18);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Microsoft YaHei", Font.BOLD, 42));
        g.drawString("JQ", 20, 68);
        g.dispose();
        ImageIO.write(image, "png", new File(path));
    }

    /** 生成椭圆形合同专用章。 */
    private static void generateSeal(String path, String orgName) throws IOException {
        int w = 260;
        int h = 180;
        BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = image.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Color red = new Color(0xC6, 0x28, 0x28);
        g.setColor(red);
        g.setStroke(new BasicStroke(6f));
        g.drawOval(10, 10, w - 20, h - 20);
        g.setFont(new Font("Microsoft YaHei", Font.BOLD, 20));
        g.drawString(orgName, 26, 58);
        g.setFont(new Font("Microsoft YaHei", Font.PLAIN, 15));
        g.drawString("合 同 专 用 章", 72, 92);
        g.setFont(new Font("Microsoft YaHei", Font.PLAIN, 11));
        g.drawString("(1)", w / 2 - 12, 118);
        g.dispose();
        ImageIO.write(image, "png", new File(path));
    }
}
