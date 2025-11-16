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
import java.util.LinkedList;
import java.util.List;

/**
 * packageName com.github.paohaijiao.sample
 *
 * @author Martin
 * @version 1.0.0
 * @since 2025/7/13
 */
@Data
public class Categories {
    private int categoryId;
    private String code;
    private String name;
    private String icon;
    private String image;
    private int rank;
    private String baseImage;
    private int weighting;
    private int analysisItems;
    private int unlockedItems;
    private List<ItemsBean> items;

    public static List<Categories> getDatas() {
        List<Categories> list = new LinkedList<>();
        list.add(getData());
        list.add(getData());
        list.add(getData());
        list.add(getData());
        list.add(getData());
        return list;

    }

    public static Categories getData() {
        Categories bloodPressureCategory = new Categories();
        CategoriesManualSetter.setCategoryData(bloodPressureCategory,
                2427, "jxy", "降血压类", 8,
                "http://files.dev.1genehealth.com/gene/d93b59fd9c74a6fc3ee69a26fa6c08e6.png",
                19, 19);
        List<Categories.ItemsBean> items = new ArrayList<>();
        Categories.ItemsBean metoprolol = new Categories.ItemsBean();
        CategoriesManualSetter.setItemData(metoprolol,
                "jxymtle", "美托洛尔", 4, "3A", "谨慎使用",
                "CYP2D6酶活性减弱，药物代谢速率减慢，毒副风险较高，遵医嘱减少本药药物剂量使用。",
                2, 50, 711);
        items.add(metoprolol);
        Categories.ItemsBean nifedipine = new Categories.ItemsBean();
        CategoriesManualSetter.setItemData(nifedipine,
                "jxyxbdp", "硝苯地平", 1, "2A", "酌情使用",
                "对该药物的应答性较差，治疗效果可能较差，建议使用时监测药物疗效，酌情考虑是否增加药物剂量。",
                6, 50, 706);
        items.add(nifedipine);
        bloodPressureCategory.setItems(items);
        return bloodPressureCategory;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getBaseImage() {
        return baseImage;
    }

    public void setBaseImage(String baseImage) {
        this.baseImage = baseImage;
    }

    public int getWeighting() {
        return weighting;
    }

    public void setWeighting(int weighting) {
        this.weighting = weighting;
    }

    public int getAnalysisItems() {
        return analysisItems;
    }

    public void setAnalysisItems(int analysisItems) {
        this.analysisItems = analysisItems;
    }

    public int getUnlockedItems() {
        return unlockedItems;
    }

    public void setUnlockedItems(int unlockedItems) {
        this.unlockedItems = unlockedItems;
    }

    public List<ItemsBean> getItems() {
        return items;
    }

    public void setItems(List<ItemsBean> items) {
        this.items = items;
    }

    public static class ItemsBean {

        private boolean locked;
        private String code;
        private String name;
        private String icon;
        private String image;
        private int index;
        private String level;
        private String label;
        private String description;
        private float score;
        private Object tagScore;
        private boolean recommend;
        private Object direction;
        private int rank;
        private int price;
        private int itemId;
        private Object state;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public float getScore() {
            return score;
        }

        public void setScore(float score) {
            this.score = score;
        }

        public Object getTagScore() {
            return tagScore;
        }

        public void setTagScore(Object tagScore) {
            this.tagScore = tagScore;
        }

        public boolean isRecommend() {
            return recommend;
        }

        public void setRecommend(boolean recommend) {
            this.recommend = recommend;
        }

        public boolean isLocked() {
            return locked;
        }

        public void setLocked(boolean locked) {
            this.locked = locked;
        }

        public Object getDirection() {
            return direction;
        }

        public void setDirection(Object direction) {
            this.direction = direction;
        }

        public int getRank() {
            return rank;
        }

        public void setRank(int rank) {
            this.rank = rank;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public int getItemId() {
            return itemId;
        }

        public void setItemId(int itemId) {
            this.itemId = itemId;
        }

        public Object getState() {
            return state;
        }

        public void setState(Object state) {
            this.state = state;
        }
    }
}
