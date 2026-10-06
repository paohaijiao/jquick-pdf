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
 * 档案封面 Demo 55：档案案卷封面（档案管理场景）
 *
 * <p>业务场景：基建项目竣工资料移交档案室后，为案卷打印封面，需体现全宗号、密级、
 * 案卷题名与档案号等要素，供装盒上架使用。</p>
 *
 * <p>版式：整页 3pt 粗外框 + 顶部全宗号与密级分列两端 + 中部超大字号题名区
 * （单位名 14pt / 案卷类别 22pt / 保管期限 11pt 三级层次）+ 两列「标签 + 下划线值」
 * 案卷信息表 + 底部立卷人与检查人双栏 + 居中单位与年月。</p>
 *
 * <p>本模板为纯版式 Demo，不含图表，因此不需要绑定 {@code svg} 变量；
 * 模板：{@code report/biz/55_archive_cover.txt}，输出：{@code D:\test\biz\55_archive_cover.pdf}。</p>
 *
 * @author Martin
 * @version 1.0.0
 */
public class JQuickBiz55ArchiveCoverTest {

    public static final String path = JQuickConstant.path;

    @Test
    public void biz55ArchiveCover() throws IOException {
        File dir = new File(path + "biz");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(path + "biz/55_archive_cover.pdf");
        JPdfConfig config = new JPdfConfig();
        // 开启 flex 行布局，使模板中的 display:flex 生效（全宗号与密级两端对齐、立卷/检查双栏）
        config.getLayoutConfig().setFlexLayout(true);
        JQuickPdfFactory factory = new JQuickPdfFactory(config);
        byte[] bytes = factory.executeResource("report/biz/55_archive_cover.txt");
        fileOutputStream.write(bytes);
        fileOutputStream.close();
    }
}
