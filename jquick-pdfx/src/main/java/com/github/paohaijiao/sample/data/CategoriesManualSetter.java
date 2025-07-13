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

import java.util.ArrayList;
import java.util.List;

/**
 * packageName com.github.paohaijiao.sample.data
 *
 * @author Martin
 * @version 1.0.0
 * @since 2025/7/13
 */
public class CategoriesManualSetter {
    public static void setCategoryData(Categories category,
                                       int categoryId,
                                       String code,
                                       String name,
                                       int rank,
                                       String baseImage,
                                       int analysisItems,
                                       int unlockedItems) {
        category.setCategoryId(categoryId);
        category.setCode(code);
        category.setName(name);
        category.setRank(rank);
        category.setBaseImage(baseImage);
        category.setAnalysisItems(analysisItems);
        category.setUnlockedItems(unlockedItems);

        // 设置默认值
        category.setIcon("");
        category.setImage("");
        category.setWeighting(0);
    }

    public static void setItemData(Categories.ItemsBean item,
                                   String code,
                                   String name,
                                   int index,
                                   String level,
                                   String label,
                                   String description,
                                   int rank,
                                   int price,
                                   int itemId) {
        item.setCode(code);
        item.setName(name);
        item.setIndex(index);
        item.setLevel(level);
        item.setLabel(label);
        item.setDescription(description);
        item.setRank(rank);
        item.setPrice(price);
        item.setItemId(itemId);

        // 设置默认值
        item.setIcon("");
        item.setImage("");
        item.setScore(12f);
        item.setTagScore(null);
        item.setRecommend(true);
        item.setLocked(false);
        item.setDirection(null);
        item.setState(null);
    }


}
