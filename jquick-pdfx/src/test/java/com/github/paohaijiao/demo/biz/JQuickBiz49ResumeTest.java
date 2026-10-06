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
package com.github.paohaijiao.demo.biz;

import com.github.paohaijiao.config.JPdfConfig;
import com.github.paohaijiao.demo.constant.JQuickConstant;
import com.github.paohaijiao.executor.JQuickPdfFactory;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 简历 Demo 49：个人简历（招聘 / 人才档案场景）
 *
 * <p>业务场景：把候选人信息排版成可直接投递的单页简历，左栏集中展示姓名、
 * 联系方式、核心技能与证书，右栏按「个人简介 / 工作经历 / 项目经验 / 教育背景」展开。</p>
 *
 * <p>版式：左右双栏，左栏 185px 深蓝底白字侧栏；右栏正文各小节统一使用「左侧竖条标题」，
 * 工作与项目经历采用「时间 + 左色条」的时间轴结构。</p>
 *
 * <p>本模板为纯版式 Demo，不含图表，因此不需要绑定 {@code svg} 变量；
 * 模板：{@code report/biz/49_resume.txt}，输出：{@code D:\test\biz\49_resume.pdf}。</p>
 *
 * @author Martin
 * @version 1.0.0
 */
public class JQuickBiz49ResumeTest {

    public static final String path = JQuickConstant.path;

    @Test
    public void biz49Resume() throws IOException {
        File dir = new File(path + "biz");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(path + "biz/49_resume.pdf");
        JPdfConfig config = new JPdfConfig();
        // 开启 flex 行布局，使模板中的 display:flex 生效（左右双栏 + 经历时间轴）
        config.getLayoutConfig().setFlexLayout(true);
        JQuickPdfFactory factory = new JQuickPdfFactory(config);
        byte[] bytes = factory.executeResource("report/biz/49_resume.txt");
        fileOutputStream.write(bytes);
        fileOutputStream.close();
    }
}
