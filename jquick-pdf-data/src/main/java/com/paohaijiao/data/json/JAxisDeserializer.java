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
import com.paohaijiao.data.axis.JAxis;
import com.paohaijiao.data.axis.JCategoryAxis;
import com.paohaijiao.data.axis.JTimeAxis;
import com.paohaijiao.data.axis.JValueAxis;
import com.paohaijiao.data.code.JAxisType;

import java.lang.reflect.Type;

/**
 * @author martin
 */
public class JAxisDeserializer implements JsonDeserializer<JAxis> {
    /**
     * 设置json,typeOfT,context值
     *
     * @param json
     * @param typeOfT
     * @param context
     */
    @Override
    public JAxis deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        final JsonObject jsonObject = json.getAsJsonObject();
        String _type = jsonObject.get("type").getAsString();
        JAxisType type = JAxisType.valueOf(_type);
        JAxis axis = null;
        switch (type) {
            case category:
                axis = context.deserialize(jsonObject, JCategoryAxis.class);
                break;
            case value:
                axis = context.deserialize(jsonObject, JValueAxis.class);
                break;
            case time:
                axis = context.deserialize(jsonObject, JTimeAxis.class);
                break;
        }
        return axis;
    }
}
