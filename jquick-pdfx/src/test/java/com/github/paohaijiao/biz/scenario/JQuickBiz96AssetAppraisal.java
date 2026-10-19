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
import java.nio.file.Files;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 场景 96：资产评估摘要报告 PDF（封面 + 正文多页 + 评估机构公章 + 注册评估师签章）。
 *
 * <p>本类不定义任何实体类，全部数据通过 {@link JQuickPdfFactory#bindAll(Map)}
 * 以 key-value 形式注入；版式全部落在模板文件
 * {@code report/biz/96_asset_appraisal.txt} 中（资产分类汇总 9 行逐格占位）。</p>
 *
 * <p>产物目录约定：PDF 输出 {@code D:/test/pdf/}，印章 / 签名图片输出 {@code D:/test/image/}。</p>
 *
 * @author Martin
 * @version 2.0.0
 */
public class JQuickBiz96AssetAppraisal {

    private static final String PDF_DIR = "D:/test/pdf/";
    private static final String IMG_DIR = "D:/test/image/";
    private static final String OUT_FILE = PDF_DIR + "96_asset_appraisal.pdf";
    private static final String TEMPLATE = "report/biz/96_asset_appraisal.txt";

    public static void main(String[] args) throws IOException {
        new File(PDF_DIR).mkdirs();
        new File(IMG_DIR).mkdirs();

        // ---------- 1. 生成图片（机构章 + 双评估师签名），模板按字面量路径引用 ----------
        generateSeal(IMG_DIR + "96_seal.png", "中和正信资产评估有限公司");
        generateSignature(IMG_DIR + "96_sign_a.png", "顾言之");
        generateSignature(IMG_DIR + "96_sign_b.png", "柳明");

        // ---------- 2. 组织数据（普通 Map，无实体类）----------
        // 资产分类汇总：类别 / 评估方法 / 账面价值 / 评估价值（增值额与增值率程序计算）
        String[][] assets = {
                {"流动资产（货币资金、应收款项、存货）", "资产基础法", "86420000.00", "87150000.00"},
                {"固定资产—房屋建筑物", "重置成本法", "126800000.00", "148260000.00"},
                {"固定资产—机器设备", "重置成本法", "46350000.00", "41820000.00"},
                {"固定资产—车辆", "市场法", "4820000.00", "3960000.00"},
                {"在建工程", "资产基础法", "18640000.00", "18640000.00"},
                {"无形资产—土地使用权", "基准地价系数修正法", "32400000.00", "56800000.00"},
                {"无形资产—专利及软件著作权", "收益法", "8600000.00", "12400000.00"},
                {"长期待摊费用及其他", "资产基础法", "2460000.00", "2310000.00"},
                {"负债（短期借款、应付款项等）", "资产基础法", "58260000.00", "58260000.00"}
        };
        String[] specialNotes = {
                "1．本次评估范围内房屋建筑物共 6 项，均已取得不动产权证书，其中 2 项已设定抵押，抵押权人为××商业银行，"
                        + "评估时未考虑抵押事项对价值的影响。",
                "2．存货中的在产品按完工进度折算为约当产量后评估，若后续订单发生变化，评估结论可能相应调整。",
                "3．无形资产—专利及软件著作权采用收益法评估，其未来收益预测由委托人提供，我们对其合理性进行了必要分析，"
                        + "但不构成对未来收益实现的保证。",
                "4．截至评估基准日，产权持有人存在未决诉讼 1 起，涉诉金额约 320 万元，评估时未计提预计负债。",
                "5．本报告未考虑评估对象在交易过程中可能产生的交易税费、过户费用及相关法律费用。"
        };

        Map<String, Object> data = new LinkedHashMap<String, Object>();
        data.put("reportNo", "中正评报字[2026]第 0417 号");
        data.put("client", "××科技股份有限公司");
        data.put("owner", "××科技股份有限公司");
        data.put("purpose", "为委托人拟进行的股权收购事宜提供价值参考");
        data.put("baseDate", "2025年12月31日");
        data.put("scope", "委托人申报的全部资产及相关负债，包括流动资产、房屋建筑物、机器设备、在建工程、"
                + "土地使用权、专利及软件著作权等无形资产（详见评估明细表）");
        data.put("valueType", "市场价值");
        data.put("reportDate", "2026年3月26日");
        data.put("validPeriod", "2025年12月31日 至 2026年12月30日");
        data.put("institution", "中和正信资产评估有限公司");
        data.put("address", "××市××区金融街 18 号 中和大厦 12 层");
        data.put("appraiserA", "顾言之");
        data.put("appraiserB", "柳明");
        data.put("certNoA", "（资产评估师）编号 31000052");
        data.put("certNoB", "（资产评估师）编号 31000187");

        BigDecimal totalBook = BigDecimal.ZERO;
        BigDecimal totalEval = BigDecimal.ZERO;
        for (int i = 0; i < assets.length; i++) {
            BigDecimal book = new BigDecimal(assets[i][2]);
            BigDecimal eval = new BigDecimal(assets[i][3]);
            BigDecimal increment = eval.subtract(book);
            String rate;
            if (book.compareTo(BigDecimal.ZERO) == 0) {
                rate = "0%";
            } else {
                rate = increment.multiply(new BigDecimal(100))
                        .divide(book, 2, BigDecimal.ROUND_HALF_UP).toPlainString() + "%";
            }
            totalBook = totalBook.add(book);
            totalEval = totalEval.add(eval);
            data.put("aCat" + (i + 1), assets[i][0]);
            data.put("aMethod" + (i + 1), assets[i][1]);
            data.put("aBook" + (i + 1), money(book));
            data.put("aEval" + (i + 1), money(eval));
            data.put("aRate" + (i + 1), rate);
        }
        data.put("totalBook", money(totalBook));
        data.put("totalEval", money(totalEval));
        data.put("totalIncrement", money(totalEval.subtract(totalBook)));
        data.put("totalRate", totalEval.subtract(totalBook).multiply(new BigDecimal(100))
                .divide(totalBook, 2, BigDecimal.ROUND_HALF_UP).toPlainString() + "%");
        for (int i = 0; i < specialNotes.length; i++) {
            data.put("note" + (i + 1), specialNotes[i]);
        }

        // ---------- 3. 渲染 ----------
        JPdfConfig config = new JPdfConfig();
        config.getLayoutConfig().setFlexLayout(true);
        config.getDoc().setMargins(Arrays.asList(60, 64, 58, 64));
        config.getHeaderConfig().setEnabled(true);
        config.getHeaderConfig().setText("资产评估报告摘要　" + data.get("reportNo"));
        config.getHeaderConfig().setFontSize(8);
        config.getHeaderConfig().setHeight(34);
        config.getFooterConfig().setEnabled(true);
        config.getFooterConfig().setFontSize(8);
        config.getFooterConfig().setPageNumberFormat("第 %d 页");

        JQuickPdfFactory factory = new JQuickPdfFactory(config);
        factory.bindAll(data);
        byte[] pdf = factory.executeResource(TEMPLATE);
        Files.write(new File(OUT_FILE).toPath(), pdf);
        System.out.println("已生成：" + OUT_FILE);
    }

    /** 千分位格式化，保留两位小数。 */
    private static String money(BigDecimal v) {
        return String.format("%,.2f", v.doubleValue());
    }

    /** 生成评估机构公章（双圈 + 五角星 + 环形机构名）。 */
    private static void generateSeal(String path, String orgName) throws IOException {
        int size = 220;
        BufferedImage image = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = image.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Color red = new Color(0xC6, 0x28, 0x28);
        g.setColor(red);
        g.setStroke(new BasicStroke(7f));
        g.drawOval(7, 7, size - 14, size - 14);
        g.setStroke(new BasicStroke(2f));
        g.drawOval(18, 18, size - 36, size - 36);
        g.fill(star(size / 2, size / 2 - 10, 20));
        // 环形排列单位名称
        String name = orgName;
        double start = -168;
        double step = 216.0 / Math.max(1, name.length());
        for (int i = 0; i < name.length(); i++) {
            double angle = Math.toRadians(start + step * i);
            int r = 74;
            int x = (int) (size / 2 + r * Math.cos(angle));
            int y = (int) (size / 2 + r * Math.sin(angle));
            Graphics2D g2 = (Graphics2D) g.create();
            g2.rotate(angle + Math.PI / 2, x, y);
            g2.setFont(new Font("Microsoft YaHei", Font.BOLD, 12));
            g2.drawString(String.valueOf(name.charAt(i)), x - 6, y + 5);
            g2.dispose();
        }
        g.setFont(new Font("Microsoft YaHei", Font.BOLD, 13));
        g.drawString("资产评估专用章", size / 2 - 46, size - 40);
        g.dispose();
        ImageIO.write(image, "png", new File(path));
    }

    private static Shape star(int cx, int cy, int radius) {
        java.awt.geom.Path2D.Double path = new java.awt.geom.Path2D.Double();
        for (int i = 0; i < 10; i++) {
            double r = (i % 2 == 0) ? radius : radius * 0.42;
            double angle = Math.toRadians(-90 + i * 36);
            double x = cx + r * Math.cos(angle);
            double y = cy + r * Math.sin(angle);
            if (i == 0) {
                path.moveTo(x, y);
            } else {
                path.lineTo(x, y);
            }
        }
        path.closePath();
        return path;
    }

    /** 生成签名图片（楷体 + 轻微旋转）。 */
    private static void generateSignature(String path, String name) throws IOException {
        BufferedImage image = new BufferedImage(200, 96, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = image.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.rotate(Math.toRadians(-3), 100, 48);
        g.setColor(new Color(0x1B, 0x2A, 0x3A));
        g.setFont(new Font("KaiTi", Font.PLAIN, 40));
        g.drawString(name, 36, 64);
        g.dispose();
        ImageIO.write(image, "png", new File(path));
    }
}
