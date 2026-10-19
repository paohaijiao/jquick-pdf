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
 * 场景 90：车辆检测报告（模板文件 + bind 数据注入版）。
 *
 * <p>本类不定义任何实体类，全部数据通过 {@link JQuickPdfFactory#bindAll(Map)} 注入；
 * 版式全部落在模板文件 {@code report/biz/90_vehicle_inspection.txt} 中。</p>
 *
 * <p>产物：PDF {@code D:/test/pdf/90_vehicle_inspection.pdf}；
 * 检测专用章 {@code D:/test/image/90_seal.png}。</p>
 *
 * @author Martin
 * @version 2.0.0
 */
public class JQuickBiz90VehicleInspection {

    private static final String PDF_DIR = "D:/test/pdf/";
    private static final String IMG_DIR = "D:/test/image/";
    private static final String OUT_FILE = PDF_DIR + "90_vehicle_inspection.pdf";
    private static final String TEMPLATE = "report/biz/90_vehicle_inspection.txt";
    private static final String SEAL = IMG_DIR + "90_seal.png";

    /** 检测项目：名称、标准要求、实测值、判定。 */
    private static final String[][] ITEMS = {
            {"唯一性认定", "VIN 与登记信息一致", "一致", "合格"},
            {"外观检查", "车身无明显变形、无非法改装", "符合", "合格"},
            {"外部照明和信号装置", "各灯具点亮正常", "正常", "合格"},
            {"制动性能（行车）", "制动力 ≥ 60% 轴荷", "68%", "合格"},
            {"制动性能（驻车）", "驻车制动力 ≥ 20%", "26%", "合格"},
            {"制动踏板自由行程", "10-30mm", "18mm", "合格"},
            {"转向轮侧滑量", "≤ 5m/km", "2.1m/km", "合格"},
            {"前照灯发光强度", "≥ 15000cd（近光）", "18600cd", "合格"},
            {"前照灯照射位置", "符合 GB 7258 要求", "符合", "合格"},
            {"车速表指示误差", "-5%~0%", "-2.4%", "合格"},
            {"喇叭声级", "90-115dB(A)", "102dB(A)", "合格"},
            {"排放（CO）", "≤ 0.6%", "0.42%", "合格"},
            {"排放（HC）", "≤ 80ppm", "62ppm", "合格"},
            {"底盘检查", "无渗漏、无松动", "左后减震渗油", "不合格"},
            {"轮胎胎冠花纹深度", "≥ 1.6mm", "3.2mm", "合格"},
            {"轮胎气压", "符合厂家规定", "符合", "合格"},
            {"悬架系统", "无断裂、无失效", "符合", "合格"},
            {"安全带", "齐全有效", "齐全", "合格"},
            {"安全玻璃", "无破损、标识齐全", "符合", "合格"},
            {"电动窗与门锁", "动作正常", "正常", "合格"}
    };

    public static void main(String[] args) throws IOException {
        new File(PDF_DIR).mkdirs();
        new File(IMG_DIR).mkdirs();
        generateSeal(SEAL);

        Map<String, Object> data = new LinkedHashMap<String, Object>();
        data.put("reportNo", "JC-2026-1019-0173");
        data.put("plateNo", "沪 A·D12345");
        data.put("vehicleType", "小型轿车（纯电动）");
        data.put("ownerName", "××物流有限公司");
        data.put("ownerPhone", "021-6123-8899");
        data.put("inspectDate", "2026年10月19日 10:40");
        data.put("inspectOrg", "××机动车检测有限公司");
        data.put("inspector", "孙磊");
        data.put("reviewer", "郑海");

        int failed = 0;
        for (int i = 0; i < ITEMS.length; i++) {
            String verdict = ITEMS[i][3];
            boolean pass = "合格".equals(verdict);
            if (!pass) {
                failed++;
            }
            int row = i + 1;
            data.put("item" + row + "_1", String.valueOf(row));
            data.put("item" + row + "_2", ITEMS[i][0]);
            data.put("item" + row + "_3", ITEMS[i][1]);
            data.put("item" + row + "_4", ITEMS[i][2]);
            data.put("item" + row + "_5", verdict);
            data.put("item" + row + "_5color", pass ? "#1B5E20" : "#B71C1C");
        }
        boolean allPass = failed == 0;
        data.put("conclusion", allPass ? "合格" : "不合格");
        data.put("conclusionBg", allPass ? "#F0F7F0" : "#FFF5F5");
        data.put("conclusionColor", allPass ? "#1B5E20" : "#B71C1C");
        data.put("totalCount", String.valueOf(ITEMS.length));
        data.put("passCount", String.valueOf(ITEMS.length - failed));
        data.put("failCount", String.valueOf(failed));

        JPdfConfig config = new JPdfConfig();
        config.getLayoutConfig().setFlexLayout(true);
        config.getDoc().setMargins(Arrays.asList(46, 48, 55, 48));
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

    private static void generateSeal(String path) throws IOException {
        int size = 200;
        BufferedImage image = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = image.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Color red = new Color(0xC6, 0x28, 0x28);
        g.setColor(red);
        g.setStroke(new BasicStroke(6f));
        g.drawOval(8, 8, size - 16, size - 16);
        g.setFont(new Font("Microsoft YaHei", Font.BOLD, 13));
        g.drawString("××机动车检测有限公司", 18, 58);
        g.setFont(new Font("Microsoft YaHei", Font.BOLD, 16));
        g.drawString("检测专用章", size / 2 - 40, size / 2 + 26);
        g.dispose();
        ImageIO.write(image, "png", new File(path));
    }
}
