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

package com.paohaijiao.data;

/**
 * Description: Event
 *
 * @author martin
 */
public enum JEvent {
    // -------全局通用
    REFRESH("refresh"),
    RESTORE("restore"),
    RESIZE("resize"),
    CLICK("click"),
    DBLCLICK("dblclick"),
    HOVER("hover"),
    //MOUSEWHEEL("mousewheel"),
    // -------业务交互逻辑
    DATA_CHANGED("dataChanged"),
    DATA_ZOOM("dataZoom"),
    DATA_RANGE("dataRange"),
    LEGEND_SELECTED("legendSelected"),
    MAP_SELECTED("mapSelected"),
    PIE_SELECTED("pieSelected"),
    MAGIC_TYPE_CHANGED("magicTypeChanged"),
    DATA_VIEW_CHANGED("dataViewChanged"),
    TIMELINE_CHANGED("timelineChanged"),
    MAP_ROAM("mapRoam"),
    // -------内部通信
    TOOLTIP_HOVER("tooltipHover"),
    TOOLTIP_IN_GRID("tooltipInGrid"),
    TOOLTIP_OUT_GRID("tooltipOutGrid");

    private String event;

    /**
     * 构造函数,参数:event
     *
     * @param event
     */
    private JEvent(String event) {
        this.event = event;
    }

    @Override
    /**
     * 获取toString值
     */
    public String toString() {
        return this.event;
    }
}
