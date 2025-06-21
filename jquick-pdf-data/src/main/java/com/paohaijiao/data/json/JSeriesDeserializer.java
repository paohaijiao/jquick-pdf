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

package com.paohaijiao.data.json;

import com.google.gson.*;
import com.paohaijiao.data.code.JSeriesType;
import com.paohaijiao.data.series.*;

import java.lang.reflect.Type;

/**
 * @author martin
 */
public class JSeriesDeserializer implements JsonDeserializer<JSeries> {
    @Override
    /**
     * 设置json,typeOfT,context值
     *
     * @param json
     * @param typeOfT
     * @param context
     */
    public JSeries deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        final JsonObject jsonObject = json.getAsJsonObject();
        String _type = jsonObject.get("type").getAsString();
        JSeriesType type = JSeriesType.valueOf(_type);
        JSeries series = null;
        switch (type) {
            case line:
                series = context.deserialize(jsonObject, JLine.class);
                break;
            case bar:
                series = context.deserialize(jsonObject, JBar.class);
                break;
            case scatter:
                series = context.deserialize(jsonObject, JScatter.class);
                break;
            case funnel:
                series = context.deserialize(jsonObject, JFunnel.class);
                break;
            case pie:
                series = context.deserialize(jsonObject, JPie.class);
                break;
            case gauge:
                series = context.deserialize(jsonObject, JGauge.class);
                break;
            case map:
                series = context.deserialize(jsonObject, JMap.class);
                break;
            case lines:
                series = context.deserialize(jsonObject, JLines.class);
                break;
            case effectScatter:
                series = context.deserialize(jsonObject, JEffectScatter.class);
                break;
            case candlestick:
                series = context.deserialize(jsonObject, JCandlestick.class);
                break;
            case graph:
                series = context.deserialize(jsonObject, JGraph.class);
                break;
            case boxplot:
                series = context.deserialize(jsonObject, JBoxplot.class);
                break;
            case parallel:
                series = context.deserialize(jsonObject, JParallel.class);
                break;
            case sankey:
                series = context.deserialize(jsonObject, JSankey.class);
                break;
        }
        return series;
    }
}
