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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 场景 79：交付收货回执 PDF（模板文件 + bind 数据注入版，表格跨页、底部签收栏）。
 *
 * <p>本类不定义任何实体类，全部数据通过 {@link JQuickPdfFactory#bindAll(Map)}
 * 以 key-value 形式注入；版式全部落在模板文件
 * {@code report/biz/79_delivery_receipt.txt} 中。</p>
 *
 * <p>运行后输出：{@code D:/test/pdf/79_delivery_receipt.pdf}。</p>
 *
 * @author Martin
 * @version 2.0.0
 */
public class JQuickBiz79DeliveryReceipt {

    private static final String PDF_DIR = "D:/test/pdf/";
    private static final String IMG_DIR = "D:/test/image/";
    private static final String OUT_FILE = PDF_DIR + "79_delivery_receipt.pdf";
    private static final String TEMPLATE = "report/biz/79_delivery_receipt.txt";

    public static void main(String[] args) throws Exception {
        new File(PDF_DIR).mkdirs();
        new File(IMG_DIR).mkdirs();

        // ---------- 1. 生成签名图片（模板 image src 按字面量引用固定路径） ----------
        generateSignature(IMG_DIR + "79_sign.png", "赵一鸣");

        // ---------- 2. 组织数据（普通 Map，无实体类）----------
        Map<String, Object> data = new LinkedHashMap<String, Object>();
        data.put("receiptNo", "HZ-2026-1019-0043");
        data.put("receiverUnit", "××城市商业银行 信息科技部");
        data.put("contact", "赵一鸣　021-6123-4567");
        data.put("address", "××市浦东新区××路 88 号 JQ 大厦 12 层");
        data.put("deliveryDate", "2026年10月19日");

        List<String[]> items = new ArrayList<String[]>();
        items.add(new String[]{"PDF 报表引擎安装介质（U 盘）", "V4.2.0", "个", "1", "含许可文件"});
        items.add(new String[]{"企业版许可证书", "5 节点", "份", "1", "已激活"});
        items.add(new String[]{"部署手册", "V4.2", "册", "2", ""});
        items.add(new String[]{"运维手册", "V4.2", "册", "2", ""});
        items.add(new String[]{"接口开发文档", "V4.2", "册", "2", "含示例代码"});
        items.add(new String[]{"报表模板包（58 套）", "STD-2026", "套", "1", "压缩包"});
        data.put("itemCount", items.size() + " 项");
        String[] keys = {"name", "spec", "unit", "qty", "remark"};
        for (int i = 0; i < items.size(); i++) {
            for (int j = 0; j < keys.length; j++) {
                data.put("it" + (i + 1) + "_" + keys[j], items.get(i)[j]);
            }
        }

        // ---------- 3. 渲染 ----------
        JPdfConfig config = new JPdfConfig();
        config.getLayoutConfig().setFlexLayout(true);
        config.getDoc().setMargins(Arrays.asList(48, 48, 55, 48));
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

    /** 生成手写签名图片（楷体 + 轻微旋转），与 93 样板同一套绘图逻辑。 */
    private static void generateSignature(String path, String name) throws IOException {
        BufferedImage image = new BufferedImage(180, 88, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = image.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.rotate(Math.toRadians(-3), 90, 44);
        g.setColor(new Color(0x1B, 0x2A, 0x3A));
        g.setFont(new Font("KaiTi", Font.PLAIN, 40));
        g.drawString(name, 26, 60);
        g.dispose();
        ImageIO.write(image, "png", new File(path));
    }
}
