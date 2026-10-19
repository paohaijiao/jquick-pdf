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
 * 场景 83：设备巡检报告单（模板文件 + bind 数据注入版）。
 *
 * <p>本类不定义任何实体类，全部数据通过 {@link JQuickPdfFactory#bindAll(Map)} 以 key-value 形式注入；
 * 版式全部落在模板文件 {@code report/biz/83_equipment_inspection.txt} 中。</p>
 *
 * <p>产物目录约定：</p>
 * <ul>
 *   <li>PDF：{@code D:/test/pdf/83_equipment_inspection.pdf}</li>
 *   <li>巡检专用章：{@code D:/test/image/83_seal.png}</li>
 * </ul>
 *
 * @author Martin
 * @version 2.0.0
 */
public class JQuickBiz83EquipmentInspection {

    private static final String PDF_DIR = "D:/test/pdf/";
    private static final String IMG_DIR = "D:/test/image/";
    private static final String OUT_FILE = PDF_DIR + "83_equipment_inspection.pdf";
    private static final String TEMPLATE = "report/biz/83_equipment_inspection.txt";

    public static void main(String[] args) throws IOException {
        mkdirs();
        generateSeal(IMG_DIR + "83_seal.png");

        // 巡检项：名称 / 判定标准 / 状态 / 备注
        String[][] items = {
                {"外观检查", "无变形、无锈蚀、铭牌清晰", "正常", ""},
                {"运行电流", "不超过额定电流 110%", "正常", "实测 86A / 额定 92A"},
                {"三相电压平衡", "不平衡度 ≤ 2%", "正常", "实测 0.8%"},
                {"轴承温度", "≤ 75℃", "异常", "后轴承 82℃，偏高"},
                {"绕组温度", "≤ 105℃", "正常", "78℃"},
                {"振动值", "≤ 4.5 mm/s", "异常", "水平方向 5.2 mm/s"},
                {"润滑油位", "在上下限之间", "正常", ""},
                {"密封状况", "无渗漏", "异常", "轴端轻微渗油"},
                {"冷却风扇", "运转正常无异响", "正常", ""},
                {"接地电阻", "≤ 4Ω", "正常", "1.8Ω"},
                {"控制柜清洁", "无积尘、无虫害", "正常", ""},
                {"接线端子", "紧固无松动、无过热变色", "正常", ""},
                {"绝缘电阻", "≥ 0.5MΩ", "正常", "12MΩ"},
                {"接触器动作", "吸合可靠、无粘连", "正常", ""},
                {"热继电器整定", "与电机铭牌匹配", "正常", "整定 95A"},
                {"变频器参数", "与运行工况一致", "正常", ""},
                {"安全防护罩", "完整、固定可靠", "正常", ""},
                {"急停按钮", "动作有效", "正常", "现场测试"},
                {"仪表显示", "数值正常、无报警", "正常", ""},
                {"运行声音", "无异常噪音", "正常", ""}};
        int abnormal = 0;
        for (String[] item : items) {
            if ("异常".equals(item[2])) {
                abnormal++;
            }
        }

        Map<String, Object> data = new LinkedHashMap<String, Object>();
        data.put("inspectionNo", "XJ-2026-1019-0286");
        data.put("equipmentNo", "SB-DJ-0012");
        data.put("equipmentName", "三相异步电动机（循环水泵驱动）");
        data.put("location", "动力站房 B 区 3# 泵组");
        data.put("inspectDate", "2026年10月19日 09:20");
        data.put("inspector", "李伟");
        data.put("reviewer", "张建军");
        data.put("totalCount", items.length);
        data.put("abnormalCount", abnormal);
        data.put("abnormalLine1", "1. 后轴承温度 82℃，超出 75℃ 判定标准，怀疑润滑脂老化，建议 24 小时内补充或更换润滑脂。");
        data.put("abnormalLine2", "2. 水平方向振动值 5.2 mm/s，超出 4.5 mm/s 限值，需复测联轴器对中情况。");
        data.put("abnormalLine3", "3. 轴端轻微渗油，暂不影响运行，列入下周保养计划处理。");

        // 巡检项逐行 bind：itemRow1~itemRow20
        for (int i = 0; i < items.length; i++) {
            data.put("itemRow" + (i + 1), items[i][0] + "　" + items[i][1] + "　" + items[i][2]);
        }

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

    private static void mkdirs() {
        new File(PDF_DIR).mkdirs();
        new File(IMG_DIR).mkdirs();
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
        g.setFont(new Font("Microsoft YaHei", Font.BOLD, 20));
        g.drawString("巡检专用章", size / 2 - 52, size / 2 + 8);
        g.setFont(new Font("Microsoft YaHei", Font.PLAIN, 11));
        g.drawString("2026-10-19", size / 2 - 30, size / 2 + 34);
        g.dispose();
        ImageIO.write(image, "png", new File(path));
    }
}
