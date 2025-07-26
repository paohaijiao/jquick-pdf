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
package com.github.paohaijiao.sample.event;

import com.github.paohaijiao.sample.ReportConstant;
import com.itextpdf.kernel.events.Event;
import com.itextpdf.kernel.events.IEventHandler;

import java.util.Properties;

/**
 * @author laoliangliang
 * @since 2020-05-24 13:53
 */
public class CatalogMoveEvent implements IEventHandler {

    private int startPage = 7;
    private int pageSize = 0;
    private Properties properties;

    public CatalogMoveEvent(Properties properties) {
        this.properties = properties;
    }

    public int getPageSize() {
        return pageSize;
    }

    @Override
    public void handleEvent(Event event) {
        pageSize++;
        properties.setProperty(ReportConstant.CATALOG_SIZE, pageSize + "");
    }
}
