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
package com.github.paohaijiao.sample.data;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * packageName com.github.paohaijiao.sample.data
 *
 * @author Martin
 * @version 1.0.0
 * @since 2025/7/13
 */
@Data
public class Content {

    private String label;
    private String value;

    public static List<Content> getDatas() {
        List<Content> list = new ArrayList<Content>();
        Content content = new Content();
        content.setLabel("药物简介");
        content.setValue("<div>该药为第二代磺脲类的胰岛素促泌剂，具有促进胰岛素分泌，降低血糖的作用。用于治疗二型糖尿病。</div>");
        list.add(content);

        Content content1 = new Content();
        content1.setLabel("常见名称");
        content1.setValue("弗莱因*格列齐特片*达美康*利宁格");
        list.add(content1);

        Content content2 = new Content();
        content2.setLabel("主要适应症");
        content2.setValue("二型糖尿病");
        list.add(content2);

        Content content3 = new Content();
        content3.setLabel("不良反应");
        content3.setValue("<div>1. 偶有轻度恶心、呕吐，上腹痛、便秘、腹泻，红斑、荨麻疹，血小板减少，粒性白细胞减少，贫血等，大多数于停药后消失。</div><div>2. 由于剂型和辅料的差异，还可能存在其他的不良反应，详情请参照所使用药品的说明书中【不良反应】项。</div>");
        list.add(content3);

        Content content4 = new Content();
        content4.setLabel("药物间相互影响");
        content4.setValue("<div>1. 与非甾体抗炎药（特别是水杨酸盐）、磺胺类抗菌药、双香豆素类抗凝剂、单胺氧化酶抑制剂、β－受体阻断剂、苯二氮卓类、四环素、氯霉素、双环己乙哌啶、氯贝丁酯、乙醇等药合用时，用量应减少，以免发生低血糖反应。</div><div>2. 如与其他药物同时使用可能会发生药物相互作用，详情请咨询医师或药师。</div>");
        list.add(content4);

        Content content5 = new Content();
        content5.setLabel("基因解读");
        content5.setValue("<div>格列齐特是一种磺脲类的降糖药物。作用机制主要是刺激胰岛β细胞分泌胰岛素，增加体内胰岛素的水平。磺脲类的降糖药物与KCNJ11基因有关，当KCNJ11-21684为CC基因型时，药效较差，建议遵医嘱优化治疗方案或更换治疗药物。</div>");
        list.add(content5);
        return list;
    }
}
