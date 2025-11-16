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

import com.github.paohaijiao.JOption;
import com.github.paohaijiao.guage.JGuageOption;
import com.github.paohaijiao.guage.GuageConfig;
import com.github.paohaijiao.guage.JGuageRenderer;
import org.junit.Test;

import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.io.IOException;

/**
 * packageName PACKAGE_NAME
 *
 * @author Martin
 * @version 1.0.0
 * @since 2025/11/16
 */
public class JGaugeTest {
    @Test
    public void testBarChar1() throws IOException, ParserConfigurationException {
        try {
            // 创建配置
            GuageConfig scoreConfig = GuageConfig.builder()
                    .score(75)  // 设置分数为75
                    .pointerColor(new Color(220, 80, 80))  // 红色指针
                    .backgroundColor(new Color(240, 240, 245))  // 浅灰色背景
                    .title("PERFORMANCE")
                    .build();

            JGuageOption option = JGuageOption.builder().scoreMeter(scoreConfig).build();
            JGuageRenderer renderer = new JGuageRenderer();
            JOption option1=new JOption();
            option1.setGuageOption(option);
            renderer.render(option1, "d://test//score_meter_improved.svg");
            System.out.println("SVG生成成功！");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
