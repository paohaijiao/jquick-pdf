
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
import com.github.paohaijiao.JTitle;
import com.github.paohaijiao.data.JData;
import com.github.paohaijiao.style.JItemStyle;
import com.github.paohaijiao.words.JWordCloudRenderer;
import com.github.paohaijiao.words.JWordCloudSeries;
import org.junit.Test;

import java.awt.*;
import java.io.IOException;
import java.util.Arrays;


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
public class WordsCloudTest {

    @Test
    public void testBarChar1() throws IOException {
        JOption option = new JOption()
        .title(new JTitle().text("热门编程语言"))
        .series(Arrays.asList(
           new JWordCloudSeries("语言热度")
               .data(Arrays.asList(
                   new JData("Java", 100),
                   new JData("Python", 85),
                   new JData("JavaScript", 75),
                   new JData("C++", 60),
                   new JData("Go", 50),
                   new JData("Rust", 45),
                   new JData("Kotlin", 40),
                   new JData("Swift", 35),
                   new JData("TypeScript", 30),
                   new JData("Scala", 25)
           ))
        .minFontSize(20)
        .maxFontSize(60)
        .gridSize(10)
        .rotationStep(15)
        .rotationRange(90)
        .textStyle(new JItemStyle().color(Color.BLUE))
        ));

        JWordCloudRenderer renderer = new JWordCloudRenderer();
        renderer.render(option, "d://test//wordcloud.svg");

    }

}
