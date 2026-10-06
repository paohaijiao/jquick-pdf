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
 * 邀请函 Demo 52：会议邀请函（正式函件场景）
 *
 * <p>业务场景：行业协会向会员单位发出会议邀请，需以正式函件格式呈现主办单位、
 * 称谓、邀请事由、会议议程、参会信息与落款日期。</p>
 *
 * <p>版式：函件结构——居中大标题（字距拉开）+ 左对齐称谓 + 两个全角空格缩进的正文段落 +
 * 三列会议议程表 + 三栏参会信息盒 + 右对齐落款 + 页脚联络方式。</p>
 *
 * <p>本模板为纯版式 Demo，不含图表，因此不需要绑定 {@code svg} 变量；
 * 模板：{@code report/biz/52_invitation_letter.txt}，输出：{@code D:\test\biz\52_invitation_letter.pdf}。</p>
 *
 * @author Martin
 * @version 1.0.0
 */
public class JQuickBiz52InvitationLetterTest {

    public static final String path = JQuickConstant.path;

    @Test
    public void biz52InvitationLetter() throws IOException {
        File dir = new File(path + "biz");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(path + "biz/52_invitation_letter.pdf");
        JPdfConfig config = new JPdfConfig();
        // 开启 flex 行布局，使模板中的 display:flex 生效（参会信息三栏、页脚联络方式两端对齐）
        config.getLayoutConfig().setFlexLayout(true);
        JQuickPdfFactory factory = new JQuickPdfFactory(config);
        byte[] bytes = factory.executeResource("report/biz/52_invitation_letter.txt");
        fileOutputStream.write(bytes);
        fileOutputStream.close();
    }
}
