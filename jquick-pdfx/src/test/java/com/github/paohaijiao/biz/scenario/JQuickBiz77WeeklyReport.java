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

import com.github.paohaijiao.JOption;
import com.github.paohaijiao.combol.JComboLineBarChartData;
import com.github.paohaijiao.config.JPdfConfig;
import com.github.paohaijiao.enums.JChartType;
import com.github.paohaijiao.executor.JQuickPdfFactory;
import com.github.paohaijiao.factory.JChartRendererFactory;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 场景 77：项目周报 PDF（模板文件 + bind 数据注入版，SVG 图表 + 多页自动分页）。
 *
 * <p>本类不定义任何实体类，全部数据通过 {@link JQuickPdfFactory#bindAll(Map)}
 * 以 key-value 形式注入；版式全部落在模板文件
 * {@code report/biz/77_weekly_report.txt} 中。</p>
 *
 * <p>SVG 图表：用 {@link JChartRendererFactory} 把「计划完成率柱状 + 实际完成率折线」渲染成
 * SVG 字符串，bind 为 {@code progressSvg} 变量，模板中 {@code <svg style="width:470pt">${progressSvg}</svg>}。</p>
 *
 * <p>运行后输出：{@code D:/test/pdf/77_weekly_report.pdf}。</p>
 *
 * @author Martin
 * @version 2.0.0
 */
public class JQuickBiz77WeeklyReport {

    private static final String PDF_DIR = "D:/test/pdf/";
    private static final String OUT_FILE = PDF_DIR + "77_weekly_report.pdf";
    private static final String TEMPLATE = "report/biz/77_weekly_report.txt";

    public static void main(String[] args) throws Exception {
        new File(PDF_DIR).mkdirs();

        // ---------- 1. 组织数据（普通 Map，无实体类）----------
        Map<String, Object> data = new LinkedHashMap<String, Object>();
        data.put("reportNo", "ZB-2026-W42-007");
        data.put("projectName", "PDF 报表引擎 4.2 迭代");
        data.put("reporter", "刘洋");
        data.put("period", "2026年10月12日 - 2026年10月18日");

        // 本周任务：逐条 bind 为 twN_name / twN_owner / twN_progress / twN_status
        List<String[]> thisWeek = new ArrayList<String[]>();
        thisWeek.add(new String[]{"报表引擎 4.2 版本联调，完成 12 个模板迁移", "刘洋", "100%", "已完成"});
        thisWeek.add(new String[]{"修复长表格跨页时表头丢失问题（缺陷 #2311）", "王敏", "100%", "已完成"});
        thisWeek.add(new String[]{"SVG 图表内存泄漏排查，已定位到字体缓存未释放", "赵磊", "85%", "进行中"});
        thisWeek.add(new String[]{"新增骑缝章、公章图片嵌入能力，完成接口设计", "陈静", "70%", "进行中"});
        thisWeek.add(new String[]{"国产化环境（麒麟 + 达梦）适配测试", "孙浩", "45%", "进行中"});
        thisWeek.add(new String[]{"压测环境准备，等待运维开通独立集群", "周凯", "20%", "风险"});
        bindTasks(data, "tw", thisWeek);

        // 下周计划：逐条 bind 为 nwN_xxx
        List<String[]> nextWeek = new ArrayList<String[]>();
        nextWeek.add(new String[]{"完成字体缓存池改造并回归全部 58 份模板", "赵磊", "0%", "进行中"});
        nextWeek.add(new String[]{"骑缝章能力提交测试，覆盖 3 种纸张规格", "陈静", "0%", "进行中"});
        nextWeek.add(new String[]{"国产化适配完成第一轮，输出兼容性问题清单", "孙浩", "0%", "进行中"});
        nextWeek.add(new String[]{"完成 200 并发压测并输出性能报告", "周凯", "0%", "进行中"});
        nextWeek.add(new String[]{"组织业务方评审 5.0 需求草案", "刘洋", "0%", "进行中"});
        bindTasks(data, "nw", nextWeek);

        // 风险与问题
        data.put("risk1", "1. 压测独立集群资源未到位，已影响本周压测计划，风险等级：高，需运维部 10 月 21 日前完成资源分配。");
        data.put("risk2", "2. 国产化环境字体渲染存在 2 处细微差异（标点挤压），已与厂商沟通，暂无明确解决方案。");
        data.put("risk3", "3. 业务方新增 3 项需求，若纳入本迭代将导致延期 3 天，需产品经理确认优先级。");

        data.put("remark", "本周整体完成率 96%，与计划偏差 4%，主要原因为压测环境延迟。下阶段重点工作为字体缓存改造与国产化适配。");

        // ---------- 2. 渲染 ----------
        JPdfConfig config = new JPdfConfig();
        config.getLayoutConfig().setFlexLayout(true);
        config.getDoc().setMargins(Arrays.asList(50, 50, 55, 50));
        config.getFooterConfig().setEnabled(true);
        config.getFooterConfig().setFontSize(8);
        config.getFooterConfig().setPageNumberFormat("第 %d 页");

        JQuickPdfFactory factory = new JQuickPdfFactory(config);
        // 注入 SVG 图表
        factory.bind("progressSvg", buildProgressSvg());
        factory.bindAll(data);
        byte[] pdf = factory.executeResource(TEMPLATE);

        FileOutputStream out = new FileOutputStream(OUT_FILE);
        out.write(pdf);
        out.close();
        System.out.println("已生成：" + OUT_FILE);
    }

    /** 任务数组按 prefix1_name 等形式逐条 bind（模板逐行引用）。 */
    private static void bindTasks(Map<String, Object> data, String prefix, List<String[]> tasks) {
        String[] keys = {"name", "owner", "progress", "status"};
        for (int i = 0; i < tasks.size(); i++) {
            for (int j = 0; j < keys.length; j++) {
                data.put(prefix + (i + 1) + "_" + keys[j], tasks.get(i)[j]);
            }
        }
    }

    /**
     * 构建进度图表：柱为「计划完成率」，折线为「实际完成率」，双序列共用百分比量纲。
     * 图表 API 类不是实体类，渲染为 SVG 字符串后 bind 为变量。
     */
    private static String buildProgressSvg() {
        JComboLineBarChartData chartData = JComboLineBarChartData.builder()
                .title("四周进度对比", "计划完成率 vs 实际完成率")
                .barData(Arrays.asList(25.0, 50.0, 75.0, 100.0))
                .lineData(Arrays.asList(22.0, 48.0, 71.0, 96.0))
                .xAxisLabels(Arrays.asList("第1周", "第2周", "第3周", "第4周"))
                .barColor(new Color(0x1F, 0x4E, 0x79))
                .lineColor(new Color(0xE6, 0x7E, 0x22))
                .leftAxisTitle("完成率（%）")
                .rightAxisTitle("完成率（%）")
                .barLegendText("计划完成率")
                .lineLegendText("实际完成率")
                .footerText("数据来源：项目管理系统")
                .build();

        JOption option = new JOption();
        option.setData(chartData);
        return JChartRendererFactory.renderChart(JChartType.LineBar, option);
    }
}
