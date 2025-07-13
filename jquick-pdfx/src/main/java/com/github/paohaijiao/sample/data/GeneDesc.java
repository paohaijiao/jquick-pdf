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
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * packageName com.github.paohaijiao.sample.data
 *
 * @author Martin
 * @version 1.0.0
 * @since 2025/7/13
 */
@NoArgsConstructor
@Data
public class GeneDesc {
    private String og_id;
    private String gene_code;
    private Object geneList;
    private String genotype;
    private String ref_genotype;
    private String label;
    private Object describe;
    private Object risk_level;
    private int relativeValue;
    private String description;


    public static List<GeneDesc> getDatas() {
        List<GeneDesc> geneDescList = new ArrayList<>();

        // 第一个基因数据
        GeneDesc gene1 = new GeneDesc();
        gene1.setOg_id("APOE-735155");
        gene1.setGene_code("APOE");
        gene1.setGeneList(null);
        gene1.setGenotype("T/T");
        gene1.setRef_genotype("T/T");
        gene1.setLabel("治疗效果较好");
        gene1.setDescribe(null);
        gene1.setRisk_level(null);
        gene1.setRelativeValue(1);
        gene1.setDescription("相比CC基因型患者，TT基因型患者治疗效果较好（LDL-胆固醇降低更高）。\n");
        geneDescList.add(gene1);

        // 第二个基因数据
        GeneDesc gene2 = new GeneDesc();
        gene2.setOg_id("SLCO1B1-21737");
        gene2.setGene_code("SLCO1B1");
        gene2.setGeneList(null);
        gene2.setGenotype("T/T");
        gene2.setRef_genotype("T/T");
        gene2.setLabel("患肌病的风险低");
        gene2.setDescribe(null);
        gene2.setRisk_level(null);
        gene2.setRelativeValue(1);
        gene2.setDescription("血药浓度正常，患肌病的风险降低\n");
        geneDescList.add(gene2);

        return geneDescList;
    }

}
