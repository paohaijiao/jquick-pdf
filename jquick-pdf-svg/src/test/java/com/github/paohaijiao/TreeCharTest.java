package com.github.paohaijiao;/*
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

import com.github.paohaijiao.axis.JCategoryAxis;
import com.github.paohaijiao.axis.JValueAxis;
import com.github.paohaijiao.bar.JBarChartsRenderer;
import com.github.paohaijiao.code.JTrigger;
import com.github.paohaijiao.combol.JTreeChartData;
import com.github.paohaijiao.combol.JTreeChartRenderer;
import com.github.paohaijiao.series.JBar;
import org.junit.Test;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName BarCharTest
 * @Description: Description
 * @Author: gou
 * @CreateDate: 2025/6/13 6:52
 * @UpdateUser: UpdateUser
 * @UpdateDate: 2025/6/13 6:52
 * @UpdateRemark:
 * @Version: 1.0
 */
public class TreeCharTest {
    @Test
    public void testBarChar1() throws IOException {
        JTreeChartData data = new JTreeChartData();
        data.setWidth(1100);
        data.setHeight(750);
        data.setTitleText("XX集团组织架构图");
        data.setSubtitleText("Organizational Structure · 2025年度");
        data.setFooterText("数据截止2025年3月 · 商务版");
        data.setRootNodeWidth(240);
        data.setRootNodeHeight(60);
        data.setLevelNodeWidth(190);
        data.setLevelNodeHeight(54);
        data.setLeafNodeWidth(110);
        data.setLeafNodeHeight(48);
        data.setHorizontalSpacing(70);
        data.setVerticalSpacing(60);
        List<JTreeChartData.LegendItem> legends = new ArrayList<>();
        legends.add(new JTreeChartData.LegendItem("一级节点（决策层）", new Color(26, 82, 118)));
        legends.add(new JTreeChartData.LegendItem("二级节点（管理层）", new Color(44, 127, 184)));
        legends.add(new JTreeChartData.LegendItem("三级节点（执行层）", Color.WHITE));
        legends.add(new JTreeChartData.LegendItem("四级节点（基层）", new Color(200, 215, 230)));
        data.setLegendList(legends);
        JTreeChartData.TreeNode root = new JTreeChartData.TreeNode("root", "集团董事会 / 总裁办", "Board of Directors & CEO Office");
        root.setColor(new Color(26, 82, 118));
        JTreeChartData.TreeNode opsCenter = new JTreeChartData.TreeNode("ops_center", "运营管理中心", "Operations Management Center");
        // 供应链管理部
        JTreeChartData.TreeNode supplyChain = new JTreeChartData.TreeNode("supply_chain", "供应链管理部", "Supply Chain Dept");
        supplyChain.addChild(new JTreeChartData.TreeNode("procurement", "采购管理科", "Procurement"));
        supplyChain.addChild(new JTreeChartData.TreeNode("warehouse", "仓储物流科", "Warehouse & Logistics"));
        supplyChain.addChild(new JTreeChartData.TreeNode("vendor", "供应商管理科", "Vendor Management"));
        // 生产管理部
        JTreeChartData.TreeNode production = new JTreeChartData.TreeNode("production", "生产管理部", "Production Dept");
        production.addChild(new JTreeChartData.TreeNode("planning", "生产计划科", "Production Planning"));
        production.addChild(new JTreeChartData.TreeNode("execution", "生产执行科", "Production Execution"));
        production.addChild(new JTreeChartData.TreeNode("maintenance", "设备维护科", "Equipment Maintenance"));
        JTreeChartData.TreeNode quality = new JTreeChartData.TreeNode("quality", "质量管理部", "Quality Management Dept");
        quality.addChild(new JTreeChartData.TreeNode("iqc", "来料检验科", "IQC"));
        quality.addChild(new JTreeChartData.TreeNode("pqc", "制程检验科", "PQC"));
        quality.addChild(new JTreeChartData.TreeNode("oqc", "出货检验科", "OQC"));
        quality.addChild(new JTreeChartData.TreeNode("system", "体系认证科", "System Certification"));

        opsCenter.addChild(supplyChain);
        opsCenter.addChild(production);
        opsCenter.addChild(quality);

        JTreeChartData.TreeNode strategyCenter = new JTreeChartData.TreeNode("strategy_center", "战略发展中心", "Strategy Development Center");
        JTreeChartData.TreeNode investment = new JTreeChartData.TreeNode("investment", "投资管理部", "Investment Dept");
        investment.addChild(new JTreeChartData.TreeNode("equity", "股权投资科", "Equity Investment"));
        investment.addChild(new JTreeChartData.TreeNode("merger", "并购重组科", "M&A"));
        investment.addChild(new JTreeChartData.TreeNode("post", "投后管理科", "Post-investment Mgmt"));

        // 战略规划部
        JTreeChartData.TreeNode strategy = new JTreeChartData.TreeNode("strategy", "战略规划部", "Strategic Planning Dept");
        strategy.addChild(new JTreeChartData.TreeNode("research", "行业研究科", "Industry Research"));
        strategy.addChild(new JTreeChartData.TreeNode("plan", "战略计划科", "Strategic Planning"));
        strategy.addChild(new JTreeChartData.TreeNode("bp", "商业分析科", "Business Analysis"));

        // 创新孵化部
        JTreeChartData.TreeNode innovation = new JTreeChartData.TreeNode("innovation", "创新孵化部", "Innovation Incubation Dept");
        innovation.addChild(new JTreeChartData.TreeNode("tech", "技术预研科", "Tech Research"));
        innovation.addChild(new JTreeChartData.TreeNode("incubate", "项目孵化科", "Project Incubation"));
        innovation.addChild(new JTreeChartData.TreeNode("partner", "生态合作科", "Ecosystem Partnership"));

        strategyCenter.addChild(investment);
        strategyCenter.addChild(strategy);
        strategyCenter.addChild(innovation);
        // 三、市场营销中心
        JTreeChartData.TreeNode marketingCenter = new JTreeChartData.TreeNode("marketing_center", "市场营销中心", "Marketing Center");

        // 品牌管理部
        JTreeChartData.TreeNode brand = new JTreeChartData.TreeNode("brand", "品牌管理部", "Brand Management Dept");
        JTreeChartData.TreeNode pr = new JTreeChartData.TreeNode("pr", "公关传播科", "PR & Communication");
        JTreeChartData.TreeNode event = new JTreeChartData.TreeNode("event", "活动策划科", "Event Planning");
        JTreeChartData.TreeNode design = new JTreeChartData.TreeNode("design", "视觉设计科", "Visual Design");
        brand.addChild(pr);
        brand.addChild(event);
        brand.addChild(design);

        // 数字营销部
        JTreeChartData.TreeNode digital = new JTreeChartData.TreeNode("digital", "数字营销部", "Digital Marketing Dept");
        digital.addChild(new JTreeChartData.TreeNode("seo", "SEO/SEM科", "SEO/SEM"));
        digital.addChild(new JTreeChartData.TreeNode("social", "社交媒体科", "Social Media"));
        digital.addChild(new JTreeChartData.TreeNode("content", "内容营销科", "Content Marketing"));
        digital.addChild(new JTreeChartData.TreeNode("data", "数据运营科", "Data Operations"));
        // 销售管理部
        JTreeChartData.TreeNode sales = new JTreeChartData.TreeNode("sales", "销售管理部", "Sales Management Dept");
        sales.addChild(new JTreeChartData.TreeNode("channel", "渠道销售科", "Channel Sales"));
        sales.addChild(new JTreeChartData.TreeNode("direct", "直销科", "Direct Sales"));
        sales.addChild(new JTreeChartData.TreeNode("ka", "大客户科", "Key Account"));
        sales.addChild(new JTreeChartData.TreeNode("overseas", "海外销售科", "Overseas Sales"));
        marketingCenter.addChild(brand);
        marketingCenter.addChild(digital);
        marketingCenter.addChild(sales);
        //四、技术研发中心
        JTreeChartData.TreeNode techCenter = new JTreeChartData.TreeNode("tech_center", "技术研发中心", "R&D Center");
        // 产品研发部
        JTreeChartData.TreeNode product = new JTreeChartData.TreeNode("product", "产品研发部", "Product R&D Dept");
        product.addChild(new JTreeChartData.TreeNode("frontend", "前端开发科", "Frontend Dev"));
        product.addChild(new JTreeChartData.TreeNode("backend", "后端开发科", "Backend Dev"));
        product.addChild(new JTreeChartData.TreeNode("mobile", "移动开发科", "Mobile Dev"));
        product.addChild(new JTreeChartData.TreeNode("pm", "产品管理科", "Product Management"));

        // 技术架构部
        JTreeChartData.TreeNode architecture = new JTreeChartData.TreeNode("architecture", "技术架构部", "Architecture Dept");
        architecture.addChild(new JTreeChartData.TreeNode("infra", "基础设施科", "Infrastructure"));
        architecture.addChild(new JTreeChartData.TreeNode("security", "信息安全科", "Security"));
        architecture.addChild(new JTreeChartData.TreeNode("data_arch", "数据架构科", "Data Architecture"));
        // 质量测试部
        JTreeChartData.TreeNode qa = new JTreeChartData.TreeNode("qa", "质量测试部", "QA Dept");
        qa.addChild(new JTreeChartData.TreeNode("auto", "自动化测试科", "Auto Testing"));
        qa.addChild(new JTreeChartData.TreeNode("manual", "手动测试科", "Manual Testing"));
        qa.addChild(new JTreeChartData.TreeNode("perf", "性能测试科", "Performance Testing"));

        techCenter.addChild(product);
        techCenter.addChild(architecture);
        techCenter.addChild(qa);

        //五、人力资源中心 =
        JTreeChartData.TreeNode hrCenter = new JTreeChartData.TreeNode("hr_center", "人力资源中心", "HR Center");

        // 招聘管理部
        JTreeChartData.TreeNode recruiting = new JTreeChartData.TreeNode("recruiting", "招聘管理部", "Recruiting Dept");
        recruiting.addChild(new JTreeChartData.TreeNode("campus", "校园招聘科", "Campus Recruiting"));
        recruiting.addChild(new JTreeChartData.TreeNode("social_hr", "社会招聘科", "Social Recruiting"));
        recruiting.addChild(new JTreeChartData.TreeNode("executive", "高端猎聘科", "Executive Search"));
        // 培训发展部
        JTreeChartData.TreeNode training = new JTreeChartData.TreeNode("training", "培训发展部", "Training & Development Dept");
        training.addChild(new JTreeChartData.TreeNode("leadership", "领导力发展科", "Leadership Dev"));
        training.addChild(new JTreeChartData.TreeNode("skill", "专业技能培训科", "Skill Training"));
        training.addChild(new JTreeChartData.TreeNode("online", "在线学习科", "E-Learning"));

        // 薪酬绩效部
        JTreeChartData.TreeNode cnp = new JTreeChartData.TreeNode("cnp", "薪酬绩效部", "Compensation & Performance Dept");
        cnp.addChild(new JTreeChartData.TreeNode("salary", "薪酬管理科", "Salary Mgmt"));
        cnp.addChild(new JTreeChartData.TreeNode("performance", "绩效管理科", "Performance Mgmt"));
        cnp.addChild(new JTreeChartData.TreeNode("benefits", "福利管理科", "Benefits Mgmt"));

        hrCenter.addChild(recruiting);
        hrCenter.addChild(training);
        hrCenter.addChild(cnp);
        //六、财务中心
        JTreeChartData.TreeNode financeCenter = new JTreeChartData.TreeNode("finance_center", "财务中心", "Finance Center");
        // 会计核算部
        JTreeChartData.TreeNode accounting = new JTreeChartData.TreeNode("accounting", "会计核算部", "Accounting Dept");
        accounting.addChild(new JTreeChartData.TreeNode("ar", "应收账款科", "Accounts Receivable"));
        accounting.addChild(new JTreeChartData.TreeNode("ap", "应付账款科", "Accounts Payable"));
        accounting.addChild(new JTreeChartData.TreeNode("gl", "总账科", "General Ledger"));

        // 资金管理部
        JTreeChartData.TreeNode treasury = new JTreeChartData.TreeNode("treasury", "资金管理部", "Treasury Dept");
        treasury.addChild(new JTreeChartData.TreeNode("cash", "现金管理科", "Cash Management"));
        treasury.addChild(new JTreeChartData.TreeNode("financing", "融资管理科", "Financing"));
        treasury.addChild(new JTreeChartData.TreeNode("fx", "外汇管理科", "FX Management"));

        // 预算分析部
        JTreeChartData.TreeNode budgeting = new JTreeChartData.TreeNode("budgeting", "预算分析部", "Budgeting & Analysis Dept");
        budgeting.addChild(new JTreeChartData.TreeNode("budget", "预算编制科", "Budget Planning"));
        budgeting.addChild(new JTreeChartData.TreeNode("cost", "成本分析科", "Cost Analysis"));
        budgeting.addChild(new JTreeChartData.TreeNode("fpna", "财务分析科", "FP&A"));

        financeCenter.addChild(accounting);
        financeCenter.addChild(treasury);
        financeCenter.addChild(budgeting);
        root.addChild(opsCenter);
        root.addChild(strategyCenter);
        root.addChild(marketingCenter);
        root.addChild(techCenter);
        root.addChild(hrCenter);
        root.addChild(financeCenter);

        data.setRootNode(root);
        JOption option = new JOption();
        option.setData(data);
        JTreeChartRenderer jBarChartsRenderer = new JTreeChartRenderer();
        jBarChartsRenderer.render(option,"D://test//tree.svg");
    }
}
