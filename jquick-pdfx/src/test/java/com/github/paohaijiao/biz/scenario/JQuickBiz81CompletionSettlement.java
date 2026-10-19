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
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 场景 81：工程竣工结算书 PDF（多页长文档、骑缝章、页眉项目名、明细跨页）。
 *
 * <p>本类不定义任何实体类，全部数据通过 {@link JQuickPdfFactory#bindAll(Map)}
 * 以 key-value 形式注入；版式全部落在模板文件
 * {@code report/biz/81_completion_settlement.txt} 中（明细 17 行、变更 4 行逐格占位）。</p>
 *
 * <p>产物目录约定：PDF 输出 {@code D:/test/pdf/}，印章 / 骑缝章图片输出 {@code D:/test/image/}。</p>
 *
 * @author Martin
 * @version 2.0.0
 */
public class JQuickBiz81CompletionSettlement {

    private static final String PDF_DIR = "D:/test/pdf/";
    private static final String IMG_DIR = "D:/test/image/";
    private static final String OUT_FILE = PDF_DIR + "81_completion_settlement.pdf";
    private static final String TEMPLATE = "report/biz/81_completion_settlement.txt";

    public static void main(String[] args) throws IOException {
        new File(PDF_DIR).mkdirs();
        new File(IMG_DIR).mkdirs();

        // ---------- 1. 生成图片（双方公章 + 骑缝章条），模板按字面量路径引用 ----------
        String sealOwner = IMG_DIR + "81_seal_owner.png";
        String sealBuilder = IMG_DIR + "81_seal_builder.png";
        generateSeal(sealOwner, "建设单位", "公章");
        generateSeal(sealBuilder, "施工单位", "公章");
        splitSealForRidge(sealBuilder, 4, IMG_DIR + "81_ridge_");

        // ---------- 2. 组织数据（普通 Map，无实体类）----------
        // 明细行：名称 / 单位 / 工程量 / 综合单价（合价由程序计算）
        String[][] subs = {
                {"土方开挖及回填", "m³", "12860", "38.50"},
                {"C30 钢筋混凝土基础", "m³", "2380", "860.00"},
                {"框架柱、梁混凝土浇筑", "m³", "4120", "920.00"},
                {"钢筋制安（HRB400）", "t", "1860", "5280.00"},
                {"砌体工程（加气混凝土砌块）", "m³", "1560", "420.00"},
                {"屋面防水（SBS 卷材）", "m²", "6800", "96.00"},
                {"外墙保温及真石漆", "m²", "9200", "168.00"},
                {"铝合金门窗安装", "m²", "3400", "520.00"},
                {"给排水管道安装", "m", "5600", "78.00"},
                {"电气桥架及配管", "m", "12800", "46.00"},
                {"消防喷淋系统", "m²", "18600", "62.00"},
                {"通风空调设备安装", "台", "86", "8600.00"},
                {"室内精装修（办公区）", "m²", "7200", "680.00"},
                {"室外道路及绿化", "m²", "5400", "158.00"},
                {"电梯采购与安装", "台", "4", "386000.00"},
                {"弱电智能化系统", "项", "1", "1260000.00"},
                {"临时设施及安全文明施工", "项", "1", "480000.00"}
        };
        String[][] changes = {
                {"QZ-001", "地下室增加抗浮锚杆 126 根", "386000.00"},
                {"QZ-002", "首层大堂石材由国产改为进口", "428000.00"},
                {"QZ-003", "因设计变更空调主机由 3 台增至 4 台", "268000.00"},
                {"QZ-004", "取消原设计中庭采光顶", "-196000.00"}
        };

        Map<String, Object> data = new LinkedHashMap<String, Object>();
        data.put("settlementNo", "JS-2026-0086");
        data.put("projectName", "××市科创中心一期建设工程");
        data.put("ownerUnit", "××市城市建设投资集团有限公司");
        data.put("builderUnit", "××建工集团第三工程有限公司");
        data.put("settlementDate", "2026年10月19日");
        data.put("contractAmount", "￥ 46,800,000.00（肆仟陆佰捌拾万元整）");

        BigDecimal subTotal = BigDecimal.ZERO;
        for (int i = 0; i < subs.length; i++) {
            BigDecimal qty = new BigDecimal(subs[i][2]);
            BigDecimal price = new BigDecimal(subs[i][3]);
            BigDecimal amount = qty.multiply(price).setScale(2, BigDecimal.ROUND_HALF_UP);
            subTotal = subTotal.add(amount);
            data.put("spName" + (i + 1), subs[i][0]);
            data.put("spUnit" + (i + 1), subs[i][1]);
            data.put("spQty" + (i + 1), qty.toPlainString());
            data.put("spPrice" + (i + 1), price.toPlainString());
            data.put("spAmount" + (i + 1), amount.toPlainString());
        }
        BigDecimal changeTotal = BigDecimal.ZERO;
        for (int i = 0; i < changes.length; i++) {
            BigDecimal amount = new BigDecimal(changes[i][2]);
            changeTotal = changeTotal.add(amount);
            data.put("chNo" + (i + 1), changes[i][0]);
            data.put("chContent" + (i + 1), changes[i][1]);
            data.put("chAmount" + (i + 1), amount.toPlainString());
        }
        data.put("chTotal", changeTotal.toPlainString());
        BigDecimal total = subTotal.add(changeTotal).setScale(2, BigDecimal.ROUND_HALF_UP);
        data.put("totalWords", amountInWords(total));
        data.put("totalPlain", total.toPlainString());
        data.put("compiler", "张国强");
        data.put("reviewer", "李慧");
        data.put("approver", "王建平");

        // ---------- 3. 渲染 ----------
        JPdfConfig config = new JPdfConfig();
        config.getLayoutConfig().setFlexLayout(true);
        // 右侧留 26pt 供骑缝章细条使用
        config.getDoc().setMargins(Arrays.asList(58, 76, 55, 50));
        config.getHeaderConfig().setEnabled(true);
        config.getHeaderConfig().setText(data.get("projectName") + "　竣工结算书");
        config.getHeaderConfig().setFontSize(8);
        config.getHeaderConfig().setHeight(34);
        config.getFooterConfig().setEnabled(true);
        config.getFooterConfig().setFontSize(8);
        config.getFooterConfig().setPageNumberFormat("第 %d 页");

        JQuickPdfFactory factory = new JQuickPdfFactory(config);
        factory.bindAll(data);
        byte[] pdf = factory.executeResource(TEMPLATE);
        java.nio.file.Files.write(new File(OUT_FILE).toPath(), pdf);
        System.out.println("已生成：" + OUT_FILE);
    }

    /**
     * 人民币大写（到分），与发票场景保持同一套换算规则。
     */
    public static String amountInWords(BigDecimal amount) {
        String[] digits = {"零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖"};
        String[] units = {"", "拾", "佰", "仟"};
        String[] groups = {"", "万", "亿"};
        BigDecimal scaled = amount.setScale(2, BigDecimal.ROUND_HALF_UP);
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

    /** 生成简化公章（圆环 + 上方角色名 + 下方「公章」字样）。 */
    private static void generateSeal(String path, String top, String bottom) throws IOException {
        int size = 200;
        BufferedImage image = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = image.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Color red = new Color(0xC6, 0x28, 0x28);
        g.setColor(red);
        g.setStroke(new BasicStroke(6f));
        g.drawOval(8, 8, size - 16, size - 16);
        g.setFont(new Font("Microsoft YaHei", Font.BOLD, 17));
        g.drawString(top, size / 2 - 34, 66);
        g.setFont(new Font("Microsoft YaHei", Font.BOLD, 16));
        g.drawString(bottom, size / 2 - 16, 132);
        g.dispose();
        ImageIO.write(image, "png", new File(path));
    }

    /**
     * 骑缝章：把公章图按纵向切成 strips 条，输出到固定前缀路径。
     * 打印后沿装订线裁切，各页边缘的细条拼起来就是一枚完整的章。
     */
    public static List<String> splitSealForRidge(String sealPath, int strips, String prefix) throws IOException {
        BufferedImage source = ImageIO.read(new File(sealPath));
        int stripWidth = Math.max(1, source.getWidth() / strips);
        List<String> paths = new ArrayList<String>();
        for (int i = 0; i < strips; i++) {
            int x = i * stripWidth;
            int w = Math.min(stripWidth, source.getWidth() - x);
            BufferedImage part = source.getSubimage(x, 0, w, source.getHeight());
            String path = prefix + i + ".png";
            ImageIO.write(part, "png", new File(path));
            paths.add(path);
        }
        return paths;
    }
}
