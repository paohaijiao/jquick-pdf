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
package com.github.paohaijiao.visitor.render;

/**
 * 阿拉伯文字形连写（shaping）与 RTL 视觉重排。
 *
 * <p>PDFBox 不执行 OpenType 的 GSUB/GPOS，直接按逻辑序绘制阿拉伯文会得到
 * 互不相连的孤立字形，且视觉顺序左右颠倒。这里按 Unicode「Arabic Presentation
 * Forms」规范，把基础字母替换为词首/词中/词尾/独立形式的码位（字体 cmap 直接
 * 支持这些码位，Noto Sans Arabic 覆盖完整），再把逻辑序重排为视觉序，使 PDF
 * 无需 shaping 引擎即可正确显示 RTL 文本。</p>
 *
 * <p>仅当元素声明 {@code baseDirection:rtl} 时由渲染入口调用。</p>
 */
public final class JArabicShaping {

    private JArabicShaping() {
    }

    /**
     * 连写形式表：{基础码位, 独立形, 词尾形, 词首形, 词中形}，0 表示该形式不存在。
     * 数据依据 UnicodeData 的 Arabic Presentation Forms A/B 区块逐项核对生成。
     */
    private static final int[][] FORMS = {
            {0x0621, 0xFE80, 0x0000, 0x0000, 0x0000},
            {0x0622, 0xFE81, 0xFE82, 0x0000, 0x0000},
            {0x0623, 0xFE83, 0xFE84, 0x0000, 0x0000},
            {0x0624, 0xFE85, 0xFE86, 0x0000, 0x0000},
            {0x0625, 0xFE87, 0xFE88, 0x0000, 0x0000},
            {0x0626, 0xFE89, 0xFE8A, 0xFE8B, 0xFE8C},
            {0x0627, 0xFE8D, 0xFE8E, 0x0000, 0x0000},
            {0x0628, 0xFE8F, 0xFE90, 0xFE91, 0xFE92},
            {0x0629, 0xFE93, 0xFE94, 0x0000, 0x0000},
            {0x062A, 0xFE95, 0xFE96, 0xFE97, 0xFE98},
            {0x062B, 0xFE99, 0xFE9A, 0xFE9B, 0xFE9C},
            {0x062C, 0xFE9D, 0xFE9E, 0xFE9F, 0xFEA0},
            {0x062D, 0xFEA1, 0xFEA2, 0xFEA3, 0xFEA4},
            {0x062E, 0xFEA5, 0xFEA6, 0xFEA7, 0xFEA8},
            {0x062F, 0xFEA9, 0xFEAA, 0x0000, 0x0000},
            {0x0630, 0xFEAB, 0xFEAC, 0x0000, 0x0000},
            {0x0631, 0xFEAD, 0xFEAE, 0x0000, 0x0000},
            {0x0632, 0xFEAF, 0xFEB0, 0x0000, 0x0000},
            {0x0633, 0xFEB1, 0xFEB2, 0xFEB3, 0xFEB4},
            {0x0634, 0xFEB5, 0xFEB6, 0xFEB7, 0xFEB8},
            {0x0635, 0xFEB9, 0xFEBA, 0xFEBB, 0xFEBC},
            {0x0636, 0xFEBD, 0xFEBE, 0xFEBF, 0xFEC0},
            {0x0637, 0xFEC1, 0xFEC2, 0xFEC3, 0xFEC4},
            {0x0638, 0xFEC5, 0xFEC6, 0xFEC7, 0xFEC8},
            {0x0639, 0xFEC9, 0xFECA, 0xFECB, 0xFECC},
            {0x063A, 0xFECD, 0xFECE, 0xFECF, 0xFED0},
            {0x0640, 0x0640, 0x0640, 0x0640, 0x0640},
            {0x0641, 0xFED1, 0xFED2, 0xFED3, 0xFED4},
            {0x0642, 0xFED5, 0xFED6, 0xFED7, 0xFED8},
            {0x0643, 0xFED9, 0xFEDA, 0xFEDB, 0xFEDC},
            {0x0644, 0xFEDD, 0xFEDE, 0xFEDF, 0xFEE0},
            {0x0645, 0xFEE1, 0xFEE2, 0xFEE3, 0xFEE4},
            {0x0646, 0xFEE5, 0xFEE6, 0xFEE7, 0xFEE8},
            {0x0647, 0xFEE9, 0xFEEA, 0xFEEB, 0xFEEC},
            {0x0648, 0xFEED, 0xFEEE, 0x0000, 0x0000},
            {0x0649, 0xFEEF, 0xFEF0, 0x0000, 0x0000},
            {0x064A, 0xFEF1, 0xFEF2, 0xFEF3, 0xFEF4},
            {0x0671, 0xFB50, 0xFB51, 0x0000, 0x0000},
            {0x0677, 0xFBDD, 0x0000, 0x0000, 0x0000},
            {0x0679, 0xFB66, 0xFB67, 0xFB68, 0xFB69},
            {0x067A, 0xFB5E, 0xFB5F, 0xFB60, 0xFB61},
            {0x067B, 0xFB52, 0xFB53, 0xFB54, 0xFB55},
            {0x067E, 0xFB56, 0xFB57, 0xFB58, 0xFB59},
            {0x067F, 0xFB62, 0xFB63, 0xFB64, 0xFB65},
            {0x0680, 0xFB5A, 0xFB5B, 0xFB5C, 0xFB5D},
            {0x0683, 0xFB76, 0xFB77, 0xFB78, 0xFB79},
            {0x0684, 0xFB72, 0xFB73, 0xFB74, 0xFB75},
            {0x0686, 0xFB7A, 0xFB7B, 0xFB7C, 0xFB7D},
            {0x0687, 0xFB7E, 0xFB7F, 0xFB80, 0xFB81},
            {0x0688, 0xFB88, 0xFB89, 0x0000, 0x0000},
            {0x068C, 0xFB84, 0xFB85, 0x0000, 0x0000},
            {0x068D, 0xFB82, 0xFB83, 0x0000, 0x0000},
            {0x068E, 0xFB86, 0xFB87, 0x0000, 0x0000},
            {0x0691, 0xFB8C, 0xFB8D, 0x0000, 0x0000},
            {0x0698, 0xFB8A, 0xFB8B, 0x0000, 0x0000},
            {0x06A4, 0xFB6A, 0xFB6B, 0xFB6C, 0xFB6D},
            {0x06A6, 0xFB6E, 0xFB6F, 0xFB70, 0xFB71},
            {0x06A9, 0xFB8E, 0xFB8F, 0xFB90, 0xFB91},
            {0x06AD, 0xFBD3, 0xFBD4, 0xFBD5, 0xFBD6},
            {0x06AF, 0xFB92, 0xFB93, 0xFB94, 0xFB95},
            {0x06B1, 0xFB9A, 0xFB9B, 0xFB9C, 0xFB9D},
            {0x06B3, 0xFB96, 0xFB97, 0xFB98, 0xFB99},
            {0x06BA, 0xFB9E, 0xFB9F, 0x0000, 0x0000},
            {0x06BB, 0xFBA0, 0xFBA1, 0xFBA2, 0xFBA3},
            {0x06BE, 0xFBAA, 0xFBAB, 0xFBAC, 0xFBAD},
            {0x06C0, 0xFBA4, 0xFBA5, 0x0000, 0x0000},
            {0x06C1, 0xFBA6, 0xFBA7, 0xFBA8, 0xFBA9},
            {0x06C5, 0xFBE0, 0xFBE1, 0x0000, 0x0000},
            {0x06C6, 0xFBD9, 0xFBDA, 0x0000, 0x0000},
            {0x06C7, 0xFBD7, 0xFBD8, 0x0000, 0x0000},
            {0x06C8, 0xFBDB, 0xFBDC, 0x0000, 0x0000},
            {0x06C9, 0xFBE2, 0xFBE3, 0x0000, 0x0000},
            {0x06CB, 0xFBDE, 0xFBDF, 0x0000, 0x0000},
            {0x06CC, 0xFBFC, 0xFBFD, 0xFBFE, 0xFBFF},
            {0x06D0, 0xFBE4, 0xFBE5, 0xFBE6, 0xFBE7},
            {0x06D2, 0xFBAE, 0xFBAF, 0x0000, 0x0000},
            {0x06D3, 0xFBB0, 0xFBB1, 0x0000, 0x0000},
    };

    /** 发音符号（透明字符）：不参与连接判定，映射到对应呈现形式；0 表示保持原样。 */
    private static final int[][] MARKS = {
            {0x064B, 0xFE70},
            {0x064C, 0xFE71},
            {0x064D, 0xFE72},
            {0x064E, 0xFE76},
            {0x064F, 0xFE77},
            {0x0650, 0xFE7A},
            {0x0651, 0xFE7C},
            {0x0652, 0xFE7D},
    };

    /** Lam-Alef 连写：{alef 变体码位, 非连结形, 连结形}。 */
    private static final int[][] LAM_ALEF = {
            {0x0622, 0xFEF5, 0xFEF6},
            {0x0623, 0xFEF7, 0xFEF8},
            {0x0625, 0xFEF9, 0xFEFA},
            {0x0627, 0xFEFB, 0xFEFC},
    };

    /** 把逻辑序的阿拉伯文本替换为呈现形式（上下文连写）。 */
    public static String shape(String text) {
        if (text == null || text.isEmpty() || !hasArabic(text)) {
            return text;
        }
        int[] cps = toCodePoints(text);
        StringBuilder out = new StringBuilder(cps.length);
        boolean prevConnects = false;
        for (int i = 0; i < cps.length; i++) {
            int cp = cps[i];
            int mark = markForm(cp);
            if (mark > 0) {
                out.appendCodePoint(mark > 0 ? mark : cp);
                continue;
            }
            int[] info = lookup(cp);
            if (info == null) {
                out.appendCodePoint(cp);
                prevConnects = false;
                continue;
            }
            boolean joinPrev = prevConnects && info[2] > 0;
            int next = nextLetter(cps, i);
            boolean joinNext = info[3] > 0 && next > 0 && joinsBackward(cps[next]);
            // Lam-Alef 连字：词中用连结形，其余用独立形。
            if (cp == 0x0644 && joinNext) {
                int[] lig = lamAlef(cps[next]);
                if (lig != null) {
                    out.appendCodePoint(lig[joinPrev ? 1 : 0]);
                    prevConnects = false;
                    i = skipLetterAndTransparents(cps, next);
                    continue;
                }
            }
            int form;
            if (joinPrev && joinNext && info[4] > 0) {
                form = info[4];
            } else if (joinPrev) {
                form = info[2];
            } else if (joinNext) {
                form = info[3];
            } else {
                form = info[1];
            }
            out.appendCodePoint(form > 0 ? form : cp);
            prevConnects = info[3] > 0;
        }
        return out.toString();
    }

    /**
     * 把逻辑序重排为视觉序（RTL 段落用）：整体反转书写单元顺序，
     * 拉丁字母/数字保持内部正序（数字与英文单词在 RTL 行内仍从左往右读）。
     */
    public static String reorderRtl(String text) {
        if (text == null || text.isEmpty()) {
            return text;
        }
        int[] cps = toCodePoints(text);
        StringBuilder out = new StringBuilder(cps.length);
        int i = cps.length - 1;
        while (i >= 0) {
            if (isLtrChar(cps[i])) {
                // 收集整段 LTR 单元（含贴附的连字符/点号），保持正序输出。
                int end = i;
                int start = i;
                while (start - 1 >= 0 && (isLtrChar(cps[start - 1])
                        || (isNeutralPunctuation(cps[start - 1]) && start - 2 >= 0 && isLtrChar(cps[start - 2])))) {
                    start--;
                }
                for (int k = start; k <= end; k++) {
                    out.appendCodePoint(cps[k]);
                }
                i = start - 1;
            } else {
                out.appendCodePoint(cps[i]);
                i--;
            }
        }
        return out.toString();
    }

    private static boolean hasArabic(String text) {
        for (int i = 0; i < text.length(); ) {
            int cp = text.codePointAt(i);
            if ((cp >= 0x0600 && cp <= 0x06FF) || (cp >= 0xFB50 && cp <= 0xFEFF)) {
                return true;
            }
            i += Character.charCount(cp);
        }
        return false;
    }

    /** 向后找下一个非发音符号码位下标，找不到返回 -1。 */
    private static int nextLetter(int[] cps, int index) {
        for (int k = index + 1; k < cps.length; k++) {
            if (markForm(cps[k]) > 0) {
                continue;
            }
            return k;
        }
        return -1;
    }

    private static int skipLetterAndTransparents(int[] cps, int letterIndex) {
        int k = letterIndex + 1;
        while (k < cps.length && markForm(cps[k]) > 0) {
            k++;
        }
        return k;
    }

    /** 字母能否向右（向后一个字母）连接：拥有词尾形即可。 */
    private static boolean joinsBackward(int cp) {
        int[] info = lookup(cp);
        return info != null && info[2] > 0;
    }

    private static int[] lamAlef(int cp) {
        for (int[] entry : LAM_ALEF) {
            if (entry[0] == cp) {
                return entry;
            }
        }
        return null;
    }

    private static int markForm(int cp) {
        for (int[] entry : MARKS) {
            if (entry[0] == cp) {
                return entry[1];
            }
        }
        return 0;
    }

    private static int[] lookup(int cp) {
        int low = 0;
        int high = FORMS.length - 1;
        while (low <= high) {
            int mid = (low + high) >>> 1;
            if (FORMS[mid][0] < cp) {
                low = mid + 1;
            } else if (FORMS[mid][0] > cp) {
                high = mid - 1;
            } else {
                return FORMS[mid];
            }
        }
        return null;
    }

    /** 拉丁字母、数字与阿拉伯-印度数字：在 RTL 行内保持从左到右的内部顺序。 */
    private static boolean isLtrChar(int cp) {
        return (cp >= 'A' && cp <= 'Z') || (cp >= 'a' && cp <= 'z')
                || (cp >= '0' && cp <= '9') || (cp >= 0x0660 && cp <= 0x0669);
    }

    private static boolean isNeutralPunctuation(int cp) {
        return cp == '-' || cp == '.' || cp == '/' || cp == ':' || cp == ',' || cp == '\'';
    }

    private static int[] toCodePoints(String text) {
        int[] cps = new int[Character.codePointCount(text, 0, text.length())];
        int index = 0;
        for (int i = 0; i < text.length(); ) {
            int cp = text.codePointAt(i);
            cps[index++] = cp;
            i += Character.charCount(cp);
        }
        return cps;
    }
}
