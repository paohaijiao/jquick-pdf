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
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 场景 82：银行资信证明 PDF（标准银行制式公文，单页，右下角银行公章）。
 *
 * <p>本类不定义任何实体类，全部数据通过 {@link JQuickPdfFactory#bindAll(Map)}
 * 以 key-value 形式注入；版式全部落在模板文件
 * {@code report/biz/82_credit_certificate.txt} 中。</p>
 *
 * <p>产物目录约定：PDF 输出 {@code D:/test/pdf/}，印章图片输出 {@code D:/test/image/}。</p>
 *
 * @author Martin
 * @version 2.0.0
 */
public class JQuickBiz82CreditCertificate {

    private static final String PDF_DIR = "D:/test/pdf/";
    private static final String IMG_DIR = "D:/test/image/";
    private static final String OUT_FILE = PDF_DIR + "82_credit_certificate.pdf";
    private static final String TEMPLATE = "report/biz/82_credit_certificate.txt";

    public static void main(String[] args) throws IOException {
        new File(PDF_DIR).mkdirs();
        new File(IMG_DIR).mkdirs();

        // ---------- 1. 生成业务专用章图片（模板按字面量路径引用）----------
        generateSeal(IMG_DIR + "82_seal.png");

        // ---------- 2. 组织数据（普通 Map，无实体类）----------
        Map<String, Object> data = new LinkedHashMap<String, Object>();
        data.put("certNo", "资信证明〔2026〕第 20261019-086 号");
        data.put("customerName", "××数字科技有限公司");
        data.put("idNo", "91310115MA1K9XXXX");
        data.put("accountNo", "3100 1234 5678 9012 345");
        data.put("accountType", "基本存款账户");
        data.put("balance", "人民币 ￥ 8,642,300.00");
        data.put("openDate", "2018年05月16日");
        data.put("validUntil", "90 日");
        data.put("bankName", "××银行××支行");
        data.put("issueDate", "2026年10月19日");

        // ---------- 3. 渲染 ----------
        JPdfConfig config = new JPdfConfig();
        config.getLayoutConfig().setFlexLayout(true);
        config.getDoc().setMargins(Arrays.asList(58, 66, 58, 66));
        config.getFooterConfig().setEnabled(false);

        JQuickPdfFactory factory = new JQuickPdfFactory(config);
        factory.bindAll(data);
        byte[] pdf = factory.executeResource(TEMPLATE);
        java.nio.file.Files.write(new File(OUT_FILE).toPath(), pdf);
        System.out.println("已生成：" + OUT_FILE);
    }

    /** 生成银行业务专用章（圆环 + 行名 + 「业务专用章」字样）。 */
    private static void generateSeal(String path) throws IOException {
        int size = 210;
        BufferedImage image = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = image.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Color red = new Color(0xC6, 0x28, 0x28);
        g.setColor(red);
        g.setStroke(new BasicStroke(6f));
        g.drawOval(8, 8, size - 16, size - 16);
        g.setFont(new Font("Microsoft YaHei", Font.BOLD, 15));
        g.drawString("××银行", size / 2 - 30, 62);
        g.drawString("××支行", size / 2 - 30, 84);
        g.setFont(new Font("Microsoft YaHei", Font.BOLD, 17));
        g.drawString("业务专用章", size / 2 - 45, size / 2 + 32);
        g.dispose();
        ImageIO.write(image, "png", new File(path));
    }

    /** 预留：余额格式化，业务中建议用 BigDecimal 处理后再传入字符串。 */
    @SuppressWarnings("unused")
    private static String format(BigDecimal amount) {
        return String.format("%,.2f", amount);
    }
}
