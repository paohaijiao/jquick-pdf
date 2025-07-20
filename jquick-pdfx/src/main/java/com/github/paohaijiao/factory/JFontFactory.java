///*
// * Licensed under the Apache License, Version 2.0 (the "License");
// * you may not use this file except in compliance with the License.
// * You may obtain a copy of the License at
// *
// *     http://www.apache.org/licenses/LICENSE-2.0
// *
// * Unless required by applicable law or agreed to in writing, software
// * distributed under the License is distributed on an "AS IS" BASIS,
// * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// * See the License for the specific language governing permissions and
// * limitations under the License.
// *
// * Copyright (c) [2025-2099] Martin (goudingcheng@gmail.com)
// */
//package com.github.paohaijiao.factory;
//
//import com.github.paohaijiao.enums.JFontType;
//import com.github.paohaijiao.font.JFontProvider;
//import com.github.paohaijiao.font.impl.JFileFontProvider;
//import com.itextpdf.io.font.PdfEncodings;
//
///**
// * packageName com.github.paohaijiao.factory
// *
// * @author Martin
// * @version 1.0.0
// * @className JFontFactory
// * @date 2025/6/22
// * @description
// */
//public class JFontFactory {
//
//    public static JFontProvider createFontLoader(JFontType type, String fontPath) {
//        return createFontLoader(type, fontPath, PdfEncodings.IDENTITY_H);
//    }
//
//    public static JFontProvider createFontLoader(JFontType type, String fontPath, String encoding) {
//        switch (type) {
////            case SYSTEM:
////                return new JSystemFontProvider(fontPath, encoding);
//            case FILE:
//                return new JFileFontProvider(fontPath, encoding);
//            case RESOURCE:
//                return new JResourceFontProvider(fontPath, encoding);
//            default:
//                throw new IllegalArgumentException("Unsupported font type: " + type);
//        }
//    }
//}
