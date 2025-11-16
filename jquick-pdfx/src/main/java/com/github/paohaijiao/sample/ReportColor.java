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
package com.github.paohaijiao.sample;

import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.DeviceRgb;

/**
 * @author: laoliangliang
 * @description:
 * @create: 2020/5/21 17:45
 **/
public class ReportColor {
    public static Color getThemeColor() {
        return new DeviceRgb(37, 98, 206);
    }

    public static Color getLightBlueColor() {
        return new DeviceRgb(102, 170, 255);
    }

    public static Color getGreenColor() {
        return new DeviceRgb(29, 190, 190);
    }

    public static Color getOrangeColor() {
        return new DeviceRgb(247, 181, 45);
    }

    public static Color getRedColor() {
        return new DeviceRgb(241, 106, 111);
    }
}
