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
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 场景 75：人事-离职证明 PDF（标准制式公文，单页）。
 *
 * <p>本类不定义任何实体类，全部数据通过 {@link JQuickPdfFactory#bindAll(Map)}
 * 以 key-value 形式注入；版式全部落在模板文件
 * {@code report/biz/75_resignation_cert.txt} 中。</p>
 *
 * <p>产物目录约定：PDF 输出 {@code D:/test/pdf/}，印章等图片输出 {@code D:/test/image/}。</p>
 *
 * @author Martin
 * @version 2.0.0
 */
public class JQuickBiz75ResignationCert {

    private static final String PDF_DIR = "D:/test/pdf/";
    private static final String IMG_DIR = "D:/test/image/";
    private static final String OUT_FILE = PDF_DIR + "75_resignation_cert.pdf";
    private static final String TEMPLATE = "report/biz/75_resignation_cert.txt";

    public static void main(String[] args) throws IOException {
        new File(PDF_DIR).mkdirs();
        new File(IMG_DIR).mkdirs();

        // ---------- 1. 生成公章图片（模板按字面量路径引用）----------
        generateSeal(IMG_DIR + "75_seal.png", "××数字科技有限公司");

        // ---------- 2. 组织数据（普通 Map，无实体类）----------
        String employeeName = "周子墨";
        String idCard = "3101**********5621";
        String entryDate = "2022年03月01日";
        String leaveDate = "2026年10月15日";
        String position = "高级前端开发工程师";
        String companyName = "××数字科技有限公司";

        Map<String, Object> data = new LinkedHashMap<String, Object>();
        data.put("certNo", "离职证明〔2026〕第 0412 号");
        data.put("employeeName", employeeName);
        data.put("idCard", idCard);
        data.put("entryDate", entryDate);
        data.put("leaveDate", leaveDate);
        data.put("position", position);
        data.put("terminateReason", "协商一致解除");
        data.put("companyName", companyName);
        data.put("issueDate", "2026年10月19日");
        // 长句在 Java 侧组装为单条纯文本（模板中变量只能承载纯文本）
        data.put("line1", employeeName + "，身份证号 " + idCard + "，");
        data.put("line2", "于 " + entryDate + " 入职我司，任 " + position + " 一职；");
        data.put("line3", "双方劳动关系已于 " + leaveDate + " 解除。");
        data.put("line4", "经双方协商一致，自 " + leaveDate
                + " 起解除劳动合同，我司已依法为其办理社会保险及住房公积金停缴手续，");

        // ---------- 3. 渲染 ----------
        JPdfConfig config = new JPdfConfig();
        config.getLayoutConfig().setFlexLayout(true);
        config.getDoc().setMargins(Arrays.asList(60, 72, 60, 72));
        config.getFooterConfig().setEnabled(false);

        JQuickPdfFactory factory = new JQuickPdfFactory(config);
        factory.bindAll(data);
        byte[] pdf = factory.executeResource(TEMPLATE);
        java.nio.file.Files.write(new File(OUT_FILE).toPath(), pdf);
        System.out.println("已生成：" + OUT_FILE);
    }

    /** 生成单位公章（圆环 + 单位名 + 「公章」字样）。 */
    private static void generateSeal(String path, String orgName) throws IOException {
        int size = 200;
        BufferedImage image = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = image.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Color red = new Color(0xC6, 0x28, 0x28);
        g.setColor(red);
        g.setStroke(new BasicStroke(6f));
        g.drawOval(10, 10, size - 20, size - 20);
        g.setFont(new Font("Microsoft YaHei", Font.BOLD, 13));
        g.drawString(orgName, 14, size / 2 + 50);
        g.setFont(new Font("Microsoft YaHei", Font.BOLD, 26));
        g.drawString("公章", size / 2 - 26, size / 2 + 9);
        g.dispose();
        ImageIO.write(image, "png", new File(path));
    }
}
