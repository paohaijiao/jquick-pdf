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

import com.github.paohaijiao.JOption;
import com.github.paohaijiao.axis.JCategoryAxis;
import com.github.paohaijiao.axis.JValueAxis;
import com.github.paohaijiao.code.JTrigger;
import com.github.paohaijiao.config.JPdfConfig;
import com.github.paohaijiao.demo.constant.JQuickConstant;
import com.github.paohaijiao.enums.JChartType;
import com.github.paohaijiao.executor.JQuickPdfFactory;
import com.github.paohaijiao.extension.tree.TreeNode;
import com.github.paohaijiao.factory.JChartRendererFactory;
import com.github.paohaijiao.series.JBar;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 元素与属性全集演示：jquick-pdf 模板元素与样式属性的集中对照示例。
 *
 * <p>与 16~46 的业务报表不同，本示例不承载业务语义，只用于把前 31 份模板未覆盖到的
 * 元素与属性集中演示一遍，方便写模板时直接照抄。覆盖范围：</p>
 * <ul>
 *     <li>标题元素：{@code h1} ~ {@code h6} 共 6 级</li>
 *     <li>文本属性：bold / italic / underline / lineThrough / characterSpacing /
 *     wordSpacing / opacity / color 及其组合</li>
 *     <li>列表三态：{@code listStyleType:disc}、{@code listType=ordered}、
 *     {@code listStyleType:none}</li>
 *     <li>表单元素：{@code button}、{@code checkbox}（含 {@code checked}）、
 *     {@code comboBoxField}、{@code textArea}</li>
 *     <li>结构元素：{@code tree}（绑定 {@link TreeNode}）、{@code tab}、{@code svg}、
 *     {@code lineSeparator}</li>
 *     <li>几何与盒模型属性：{@code borderRadius} 与四角独立圆角、{@code maxWidth} /
 *     {@code minHeight}、{@code verticalAlignment:middle|bottom}、
 *     {@code relativePosition}、{@code angleInRadians}</li>
 *     <li>分页元素：{@code areaBreak}、{@code htmlPageBreak}（next_page / next_area /
 *     last_page）与 {@code keepTogether}</li>
 * </ul>
 *
 * <p>{@code template} 元素（{@code <template>&name</template>}）当前实现会直接抛出
 * “not support http template render”，因此本示例只做说明、不做演示。</p>
 *
 * <p>模板：{@code report/biz/47_element_attribute_gallery.txt}；图表以 SVG 文本绑定到
 * {@code ${svg}}，树形数据绑定到 {@code ${treeRoot}}，输出到
 * {@code D:\test\biz\47_element_attribute_gallery.pdf}。</p>
 *
 * <p>图表尺寸说明：{@code <svg>} 只声明宽度、不声明高度，宽度被页面收窄时高度按原比例
 * 同步缩放，不会纵向拉伸变形。</p>
 *
 * @author Martin
 * @version 1.0.0
 */
public class JQuickBiz47ElementGalleryTest {

    public static final String path = JQuickConstant.path;

    @Test
    public void biz47ElementGallery() throws IOException {
        JOption option = new JOption();
        option.title().text("元素与属性全集演示").subtext("示例柱状图：各分组覆盖的属性项数");
        option.tooltip().trigger(JTrigger.axis);
        JCategoryAxis xAxis = new JCategoryAxis();
        xAxis.data("标题层级", "文本属性", "列表三态", "表单元素", "结构元素", "几何属性");
        option.xAxis(xAxis);
        option.yAxis(new JValueAxis());
        JBar bar = new JBar();
        bar.name("覆盖属性项数").data(6, 8, 3, 5, 4, 6);
        option.series(bar);
        String svg = JChartRendererFactory.renderChart(JChartType.BAR, option);

        // tree 元素需要的数据源：模板中写 <tree>${treeRoot}</tree>，
        // 这里构造一棵三层树演示 tree / TreeNode 的绑定方式。
        TreeNode root = new TreeNode("jquick-pdf 元素");
        TreeNode block = new TreeNode("块级元素");
        block.addChild(new TreeNode("div / p / h1-h6"));
        block.addChild(new TreeNode("table / tr / th / td"));
        block.addChild(new TreeNode("list / li / areaBreak"));
        TreeNode inline = new TreeNode("行内元素");
        inline.addChild(new TreeNode("span / tab"));
        inline.addChild(new TreeNode("lineSeparator"));
        TreeNode field = new TreeNode("表单与图形元素");
        field.addChild(new TreeNode("button / checkbox"));
        field.addChild(new TreeNode("comboBoxField / textArea"));
        field.addChild(new TreeNode("svg / tree"));
        root.addChild(block);
        root.addChild(inline);
        root.addChild(field);

        File dir = new File(path + "biz");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(path + "biz/47_element_attribute_gallery.pdf");
        JPdfConfig config = new JPdfConfig();
        // 开启 flex 行布局，使模板中的 display:flex 生效（页头双栏、分组并排、页脚三栏）
        config.getLayoutConfig().setFlexLayout(true);
        JQuickPdfFactory factory = new JQuickPdfFactory(config);
        factory.bind("svg", svg);
        factory.bind("treeRoot", root);
        byte[] bytes = factory.executeResource("report/biz/47_element_attribute_gallery.txt");
        fileOutputStream.write(bytes);
        fileOutputStream.close();
    }
}
