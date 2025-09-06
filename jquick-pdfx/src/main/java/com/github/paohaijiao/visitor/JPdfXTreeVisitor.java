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
package com.github.paohaijiao.visitor;

import com.github.paohaijiao.config.JTreeNodeConfig;
import com.github.paohaijiao.exception.JAssert;
import com.github.paohaijiao.extension.tree.TreeElement;
import com.github.paohaijiao.extension.tree.TreeNode;
import com.github.paohaijiao.factory.JFontProviderFactory;
import com.github.paohaijiao.model.JStyleAttributes;
import com.github.paohaijiao.parser.JQuickPDFParser;

/**
 * packageName com.paohaijiao.javelin.visitor
 *
 * @author Martin
 * @version 1.0.0
 * @className JPdfXCommonVisitor
 * @date 2025/6/14
 * @description
 */
public class JPdfXTreeVisitor extends JPdfXImageVisitor {

    @Override
    public TreeElement visitTree(JQuickPDFParser.TreeContext ctx) {
        JStyleAttributes style = new JStyleAttributes();
        if (null != ctx.styleEle()) {
            style = visitStyleEle(ctx.styleEle());
        } else {
            style = new JStyleAttributes();
        }
        TreeElement tree = null;
        if (ctx.variable() != null) {
            Object var = visitVariable(ctx.variable());
            JAssert.notNull(var, "the variable not  null");
            TreeNode root = (TreeNode) var;
            tree = new TreeElement(root);
        } else if (ctx.addressOf() != null) {
            String identify = ctx.addressOf().IDENTIFIER().getText();
            JTreeNodeConfig treeConfig = this.config.getTreeConfig();
            JAssert.notNull(treeConfig, "the tree config not  null");
            TreeNode treeNode = treeConfig.drawTree(identify);
            tree = new TreeElement(treeNode);
        }
        if(tree!=null){
            tree.setFont(JFontProviderFactory.defualtFont());
        }
        super.buildStyle(tree, style);
        return tree;
    }


}
