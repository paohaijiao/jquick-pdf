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
 * 场景 69：个人简历 PDF（A4 竖版，单栏全宽，模板文件 + bind 数据注入版）。
 *
 * <p>本类不定义任何实体类，全部数据通过 {@link JQuickPdfFactory#bindAll(Map)}
 * 以 key-value 形式注入；版式全部落在模板文件 {@code report/biz/69_resume.txt} 中。</p>
 *
 * <p>版式说明：顶部为深蓝个人信息横条（头像 + 姓名 + 职位 + 联系方式 + 求职意向 +
 * 专业技能），下方为自我评价、工作经历、项目经历（时间轴）与教育经历，全部铺满版心。
 * 采用单栏全宽而非左右两栏：当前渲染引擎中嵌套 {@code div} 的双栏
 * {@code display:flex} 会退化为上下堆叠，导致右半页空白。</p>
 *
 * <p>产物目录约定：</p>
 * <ul>
 *   <li>PDF：{@code D:/test/pdf/69_resume.pdf}</li>
 *   <li>头像：{@code D:/test/image/69_avatar.png}（模板按字面量引用）</li>
 * </ul>
 *
 * @author Martin
 * @version 2.2.0
 */
public class JQuickBiz69Resume {

    private static final String PDF_DIR = "D:/test/pdf/";
    private static final String IMG_DIR = "D:/test/image/";
    private static final String OUT_FILE = PDF_DIR + "69_resume.pdf";
    private static final String TEMPLATE = "report/biz/69_resume.txt";

    public static void main(String[] args) throws IOException {
        new File(PDF_DIR).mkdirs();
        new File(IMG_DIR).mkdirs();

        // ---------- 1. 图片：头像占位图 ----------
        generateAvatar(IMG_DIR + "69_avatar.png");

        // ---------- 2. 数据（普通 Map，无实体类）----------
        Map<String, Object> data = new LinkedHashMap<String, Object>();
        data.put("name", "陈嘉禾");
        data.put("jobTitle", "高级数据分析师");
        data.put("gender", "女");
        data.put("phone", "138-0000-0000");
        data.put("email", "chenjiahe@example.com");
        data.put("address", "上海市浦东新区××路 88 号");
        data.put("expectLine", "求职意向：数据分析专家 / 数据平台负责人　　期望城市：上海　　到岗时间：1 个月内");
        data.put("tagLine", "7 年经验　·　统计学本科　·　现居上海　·　可出差");
        data.put("selfEvaluation", "7 年数据分析与数据平台建设经验，主导过 3 个从 0 到 1 的指标体系落地项目，"
                + "覆盖零售、政务两类行业；擅长把业务口径翻译成可计算的指标定义，并推动数据质量治理闭环。");

        // 专业技能：逐条 bind 为 skill1~5
        String[] skills = {
                "SQL / 数据建模　精通",
                "Python / Pandas　熟练",
                "指标体系设计　熟练",
                "BI 可视化　熟练",
                "AB 实验设计　了解"
        };
        for (int i = 0; i < skills.length; i++) {
            data.put("skill" + (i + 1), skills[i]);
        }

        // 工作经历：2 段
        data.put("workPeriod1", "2021.06-至今");
        data.put("workTitle1", "××数字科技有限公司");
        data.put("workSubtitle1", "数据平台负责人");
        data.put("workDetail11", "牵头搭建统一指标体系，消除 320 项指标口径冲突");
        data.put("workDetail12", "建设指标血缘与稽核规则，日报延迟由 6 小时降至 1 小时");
        data.put("workDetail13", "带 6 人团队交付集团经营分析看板");

        data.put("workPeriod2", "2018.03-2021.05");
        data.put("workTitle2", "××连锁商业集团");
        data.put("workSubtitle2", "高级数据分析师");
        data.put("workDetail21", "负责门店经营分析，推动 12 家低效门店调整");
        data.put("workDetail22", "搭建商品结构分析模型，滞销库存下降 23%");

        // 项目经历：2 段
        data.put("projectPeriod1", "2023.02-2023.11");
        data.put("projectTitle1", "集团经营指标中台（项目负责人）");
        data.put("projectSubtitle1", "指标定义 / 血缘追溯");
        data.put("projectDetail11", "统一 320 个经营指标定义，交付指标检索、血缘追溯、异常预警三个模块");

        data.put("projectPeriod2", "2022.05-2022.10");
        data.put("projectTitle2", "门店选址数据模型（核心开发）");
        data.put("projectSubtitle2", "回归建模 / 可视化");
        data.put("projectDetail21", "融合客流、竞品、租金三类数据，选址命中率提升 18%");

        // 教育经历
        data.put("eduPeriod1", "2014.09-2018.06");
        data.put("eduTitle1", "××大学");
        data.put("eduSubtitle1", "统计学　本科 / 学士");

        // ---------- 3. 渲染 ----------
        JPdfConfig config = new JPdfConfig();
        // 经历条目的「时间 + 内容」时间轴用 display:flex 实现，必须开启 flex 行布局
        config.getLayoutConfig().setFlexLayout(true);
        config.getDoc().setMargins(Arrays.asList(36, 36, 36, 36));
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
     * 生成头像占位图（120×150 纯色块 + 姓氏），业务中替换为真实照片路径即可。
     */
    private static void generateAvatar(String path) throws IOException {
        BufferedImage image = new BufferedImage(120, 150, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = image.createGraphics();
        g.setColor(new Color(0x9F, 0xB3, 0xC8));
        g.fillRect(0, 0, 120, 150);
        g.setColor(new Color(0x23, 0x40, 0x5C));
        g.setFont(new Font("Microsoft YaHei", Font.BOLD, 48));
        g.drawString("陈", 30, 95);
        g.dispose();
        ImageIO.write(image, "png", new File(path));
    }
}
