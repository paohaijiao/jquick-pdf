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
package com.github.paohaijiao.config;

import com.itextpdf.kernel.geom.PageSize;
import lombok.Data;

import java.util.Arrays;
import java.util.List;

/**
 * packageName com.github.paohaijiao.model.meta
 *
 * @author Martin
 * @version 1.0.0
 * @className JPdfMetaModel
 * @date 2025/6/24
 * @description
 */
@Data
public class JPdfConfig {

    private String workspace="d://test";

    private String templateFile="//template.pdf";

    private String outputFile="//hello.pdf";

    private PageSize defaultPageSize=PageSize.A4;

    private List<Integer> margins= Arrays.asList(0, 0, 0, 0);

    private Boolean reverse=true;

    private JDocConfig doc=new JDocConfig();


    private JPageConfig pageConfig= new JPageConfig();

    private JHeaderConfig headerConfig=new JHeaderConfig();

    private JFooterConfig footerConfig=new JFooterConfig();

    private JWatermarkConfig watermarkConfig=new JWatermarkConfig();

    private JFontConfig fontConfig=new JFontConfig();

    private JSecurityConfig securityConfig=new JSecurityConfig();

    private JGraphConfig graphConfig=new JGraphConfig();

    private JTreeNodeConfig treeConfig = new JTreeNodeConfig();


    public String getTempFilePath() {
        return this.workspace+templateFile;
    }

    public String getOutputFilePath() {
        return this.workspace+outputFile;
    }

}
