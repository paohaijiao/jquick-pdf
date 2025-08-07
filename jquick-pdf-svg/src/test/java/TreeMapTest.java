
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
import com.github.paohaijiao.treemap.JTreeMapRenderer;
import com.github.paohaijiao.treemap.JTreeMapSeries;
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
public class TreeMapTest {
    public static JOption createDiskUsageOption() {
        // 创建根节点数据
        JData root = new JData();
        root.setName("Disk Usage");
        root.setValue(1234224); // 根节点值通常设为0或总和

        // 添加子节点
        JData system = new JData();
        system.setName("System");
        system.setValue(104857600); // 100 MB
        root.addChild(system);
        JData systemBin = new JData();
        systemBin.setName("bin");
        systemBin.setValue(52428800); // 50 MB

        JData systemLib = new JData();
        systemLib.setName("lib");
        systemLib.setValue(52428800); // 50 MB

        system.setChildren(Arrays.asList(systemBin, systemLib));

        JData data = new JData();
        data.setName("Data");
        data.setValue(5368709120L); // 5 GB

        JData documents = new JData();
        documents.setName("Documents");
        documents.setValue(1073741824L); // 1 GB

        JData media = new JData();
        media.setName("Media");
        media.setValue(3221225472L); // 3 GB

        JData projects = new JData();
        projects.setName("Projects");
        projects.setValue(1073741824L); // 1 GB

        data.setChildren(Arrays.asList(documents, media, projects));

        JData backup = new JData();
        backup.setName("Backup");
        backup.setValue(2147483648L); // 2 GB

        root.setChildren(Arrays.asList(system, data, backup));

        // 创建TreeMap系列
        JTreeMapSeries series = new JTreeMapSeries("Disk Usage");
        series.data(Arrays.asList(root)); // 将根节点作为数据

        // 创建JOption
        JOption option = new JOption();
        option.title("Disk Usage Visualization");
        option.series(Arrays.asList(series));

        return option;
    }

    @Test
    public void testBarChar1() throws IOException {
        JOption option = TreeMapTest.createDiskUsageOption();

        JTreeMapRenderer renderer = new JTreeMapRenderer();
        renderer.render(option, "d://test//treemap.svg");

    }

}
