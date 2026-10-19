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
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 场景 74：人事-入职通知 PDF（红头文件样式，单页，模板文件 + bind 数据注入版）。
 *
 * <p>本类不定义任何实体类，全部数据通过 {@link JQuickPdfFactory#bindAll(Map)}
 * 以 key-value 形式注入；版式全部落在模板文件 {@code report/biz/74_onboard_notice.txt} 中。
 * 报到材料逐条 bind 为 material1~6。</p>
 *
 * <p>产物目录约定：</p>
 * <ul>
 *   <li>PDF：{@code D:/test/pdf/74_onboard_notice.pdf}</li>
 *   <li>logo：{@code D:/test/image/74_logo.png}</li>
 * </ul>
 *
 * @author Martin
 * @version 2.0.0
 */
public class JQuickBiz74OnboardNotice {

    private static final String PDF_DIR = "D:/test/pdf/";
    private static final String IMG_DIR = "D:/test/image/";
    private static final String OUT_FILE = PDF_DIR + "74_onboard_notice.pdf";
    private static final String TEMPLATE = "report/biz/74_onboard_notice.txt";

    public static void main(String[] args) throws IOException {
        new File(PDF_DIR).mkdirs();
        new File(IMG_DIR).mkdirs();

        // ---------- 1. 图片：红头 logo ----------
        generateLogo(IMG_DIR + "74_logo.png");

        // ---------- 2. 数据（普通 Map，无实体类）----------
        Map<String, Object> data = new LinkedHashMap<String, Object>();
        data.put("companyName", "××数字科技有限公司");
        data.put("noticeNo", "人事〔2026〕入职第 086 号");
        data.put("employeeName", "许安");
        data.put("position", "资深 Java 后端工程师（P6）");
        data.put("checkInTime", "2026年11月02日（星期一）上午 9:30");
        data.put("checkInAddress", "××市浦东新区××路 88 号 JQ 大厦 12 层 人力资源部");
        data.put("salaryInfo", "月基本工资 28,000 元，绩效奖金按季度考核发放；试用期 3 个月，试用期工资为转正工资的 80%；"
                + "按上海市规定缴纳五险一金，另享补充商业医疗保险与年度体检。");

        // 报到材料：逐条 bind 为 material1~6
        String[] materials = {
                "身份证原件及复印件 2 份",
                "最高学历、学位证书原件及复印件各 1 份（境外学历需附教育部认证报告）",
                "原单位离职证明原件",
                "一寸白底免冠照片 4 张（含电子版）",
                "本人名下银行卡复印件 1 份（用于工资发放）",
                "近三个月体检报告（三级甲等医院）"
        };
        for (int i = 0; i < materials.length; i++) {
            data.put("material" + (i + 1), materials[i]);
        }

        data.put("issueDate", "2026年10月19日");

        // ---------- 3. 渲染 ----------
        JPdfConfig config = new JPdfConfig();
        config.getLayoutConfig().setFlexLayout(true);
        // 公文版心：上 60、下 55，左右 70（约 2.5cm 标准公文页边距）
        config.getDoc().setMargins(Arrays.asList(60, 70, 55, 70));
        config.getFooterConfig().setEnabled(false);

        JQuickPdfFactory factory = new JQuickPdfFactory(config);
        factory.bindAll(data);
        byte[] pdf = factory.executeResource(TEMPLATE);

        FileOutputStream out = new FileOutputStream(OUT_FILE);
        out.write(pdf);
        out.close();
        System.out.println("已生成：" + OUT_FILE);
    }

    private static void generateLogo(String path) throws IOException {
        BufferedImage image = new BufferedImage(80, 80, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = image.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(new Color(0xB7, 0x1C, 0x1C));
        g.fillOval(2, 2, 76, 76);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Microsoft YaHei", Font.BOLD, 30));
        g.drawString("JQ", 17, 52);
        g.dispose();
        ImageIO.write(image, "png", new File(path));
    }
}
