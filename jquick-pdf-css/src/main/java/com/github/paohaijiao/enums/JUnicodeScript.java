package com.github.paohaijiao.enums;

import com.itextpdf.layout.properties.BaseDirection;
import lombok.Getter;

@Getter
public enum JUnicodeScript {
    // Common and Latin scripts
    NO_BIDI("COMMON", Character.UnicodeScript.COMMON),
    DEFAULT_BIDI("latin", Character.UnicodeScript.LATIN),
    LEFT_TO_RIGHT("left_to_right", Character.UnicodeScript.GREEK),

    // Right-to-left scripts
    RIGHT_TO_LATIN("right_to_left_latin", Character.UnicodeScript.LATIN),
    RIGHT_TO_HEBREW("right_to_left_hebrew", Character.UnicodeScript.HEBREW),
    RIGHT_TO_ARABIC("right_to_left_arabic", Character.UnicodeScript.ARABIC),
    RIGHT_TO_SYRIAC("right_to_left_syriac", Character.UnicodeScript.SYRIAC),
    RIGHT_TO_THAANA("right_to_left_thaana", Character.UnicodeScript.THAANA),

    // Indian scripts
    RIGHT_TO_DEVANAGARI("right_to_left_devanagari", Character.UnicodeScript.DEVANAGARI),
    RIGHT_TO_BENGALI("right_to_left_bengali", Character.UnicodeScript.BENGALI),
    RIGHT_TO_GURMUKHI("right_to_left_gurmukhi", Character.UnicodeScript.GURMUKHI),
    RIGHT_TO_GUJARATI("right_to_left_gujarati", Character.UnicodeScript.GUJARATI),
    RIGHT_TO_ORIYA("right_to_left_oriya", Character.UnicodeScript.ORIYA),
    RIGHT_TO_TAMIL("right_to_left_tamil", Character.UnicodeScript.TAMIL),
    RIGHT_TO_TELUGU("right_to_left_telugu", Character.UnicodeScript.TELUGU),
    RIGHT_TO_KANNADA("right_to_left_kannada", Character.UnicodeScript.KANNADA),
    RIGHT_TO_MALAYALAM("right_to_left_malayalam", Character.UnicodeScript.MALAYALAM),
    RIGHT_TO_SINHALA("right_to_left_sinhala", Character.UnicodeScript.SINHALA),

    // Southeast Asian scripts
    RIGHT_TO_THAI("right_to_left_thai", Character.UnicodeScript.THAI),
    RIGHT_TO_LAO("right_to_left_lao", Character.UnicodeScript.LAO),
    RIGHT_TO_TIBETAN("right_to_left_tibetan", Character.UnicodeScript.TIBETAN),
    RIGHT_TO_MYANMAR("right_to_left_myanmar", Character.UnicodeScript.MYANMAR),

    // Other scripts
    RIGHT_TO_GEORGIAN("right_to_left_georgian", Character.UnicodeScript.GEORGIAN),
    RIGHT_TO_HANGUL("right_to_left_hangul", Character.UnicodeScript.HANGUL),
    RIGHT_TO_ETHIOPIC("right_to_left_ethiopic", Character.UnicodeScript.ETHIOPIC),
    RIGHT_TO_CHEROKEE("right_to_left_cherokee", Character.UnicodeScript.CHEROKEE),
    RIGHT_TO_CANADIAN_ABORIGINAL("right_to_left_canadian_aboriginal", Character.UnicodeScript.CANADIAN_ABORIGINAL),
    RIGHT_TO_OGHAM("right_to_left_ogham", Character.UnicodeScript.OGHAM),
    RIGHT_TO_RUNIC("right_to_left_runic", Character.UnicodeScript.RUNIC),
    RIGHT_TO_KHMER("right_to_left_khmer", Character.UnicodeScript.KHMER),
    RIGHT_TO_MONGOLIAN("right_to_left_mongolian", Character.UnicodeScript.MONGOLIAN),
    RIGHT_TO_HIRAGANA("right_to_left_hiragana", Character.UnicodeScript.HIRAGANA),
    RIGHT_TO_KATAKANA("right_to_left_katakana", Character.UnicodeScript.KATAKANA),
    RIGHT_TO_BOPOMOFO("right_to_left_bopomofo", Character.UnicodeScript.BOPOMOFO),
    RIGHT_TO_HAN("right_to_left_han", Character.UnicodeScript.HAN),
    RIGHT_TO_YI("right_to_left_yi", Character.UnicodeScript.YI),

    // Historical scripts
    RIGHT_TO_OLD_ITALIC("right_to_left_old_italic", Character.UnicodeScript.OLD_ITALIC),
    RIGHT_TO_GOTHIC("right_to_left_gothic", Character.UnicodeScript.GOTHIC),
    RIGHT_TO_DESERET("right_to_left_deseret", Character.UnicodeScript.DESERET),
    RIGHT_TO_INHERITED("right_to_left_inherited", Character.UnicodeScript.INHERITED),

    // Philippine scripts
    RIGHT_TO_TAGALOG("right_to_left_tagalog", Character.UnicodeScript.TAGALOG),
    RIGHT_TO_HANUNOO("right_to_left_hanunoo", Character.UnicodeScript.HANUNOO),
    RIGHT_TO_BUHID("right_to_left_buhid", Character.UnicodeScript.BUHID),
    RIGHT_TO_TAGBANWA("right_to_left_tagbanwa", Character.UnicodeScript.TAGBANWA),

    // Other Unicode scripts
    LIMBU("limbu", Character.UnicodeScript.LIMBU),
    TAI_LE("tai_le", Character.UnicodeScript.TAI_LE),
    LINEAR_B("linear_b", Character.UnicodeScript.LINEAR_B),
    UGARITIC("ugaritic", Character.UnicodeScript.UGARITIC),
    SHAVIAN("shavian", Character.UnicodeScript.SHAVIAN),
    OSMANYA("osmanya", Character.UnicodeScript.OSMANYA),
    CYPRIOT("cypriot", Character.UnicodeScript.CYPRIOT),
    BRAILLE("braille", Character.UnicodeScript.BRAILLE),
    BUGINESE("buginese", Character.UnicodeScript.BUGINESE),
    COPTIC("coptic", Character.UnicodeScript.COPTIC),
    NEW_TAI_LUE("new_tai_lue", Character.UnicodeScript.NEW_TAI_LUE),
    GLAGOLITIC("glagolitic", Character.UnicodeScript.GLAGOLITIC),
    TIFINAGH("tifinagh", Character.UnicodeScript.TIFINAGH),
    SYLOTI_NAGRI("syloti_nagri", Character.UnicodeScript.SYLOTI_NAGRI),
    OLD_PERSIAN("old_persian", Character.UnicodeScript.OLD_PERSIAN),
    KHAROSHTHI("kharoshthi", Character.UnicodeScript.KHAROSHTHI),
    BALINESE("balinese", Character.UnicodeScript.BALINESE),
    CUNEIFORM("cuneiform", Character.UnicodeScript.CUNEIFORM),
    PHOENICIAN("phoenician", Character.UnicodeScript.PHOENICIAN),
    PHAGS_PA("phags_pa", Character.UnicodeScript.PHAGS_PA),
    NKO("nko", Character.UnicodeScript.NKO),
    SUNDANESE("sundanese", Character.UnicodeScript.SUNDANESE),
    BATAK("batak", Character.UnicodeScript.BATAK),
    LEPCHA("lepcha", Character.UnicodeScript.LEPCHA),
    OL_CHIKI("ol_chiki", Character.UnicodeScript.OL_CHIKI),
    VAI("vai", Character.UnicodeScript.VAI),
    SAURASHTRA("saurashtra", Character.UnicodeScript.SAURASHTRA),
    KAYAH_LI("kayah_li", Character.UnicodeScript.KAYAH_LI),
    REJANG("rejang", Character.UnicodeScript.REJANG),
    LYCIAN("lycian", Character.UnicodeScript.LYCIAN),
    CARIAN("carian", Character.UnicodeScript.CARIAN),
    LYDIAN("lydian", Character.UnicodeScript.LYDIAN),
    CHAM("cham", Character.UnicodeScript.CHAM),
    TAI_THAM("tai_tham", Character.UnicodeScript.TAI_THAM),
    TAI_VIET("tai_viet", Character.UnicodeScript.TAI_VIET),
    AVESTAN("avestan", Character.UnicodeScript.AVESTAN),
    EGYPTIAN_HIEROGLYPHS("egyptian_hieroglyphs", Character.UnicodeScript.EGYPTIAN_HIEROGLYPHS),
    SAMARITAN("samaritan", Character.UnicodeScript.SAMARITAN),
    MANDAIC("mandaic", Character.UnicodeScript.MANDAIC),
    LISU("lisu", Character.UnicodeScript.LISU),
    BAMUM("bamum", Character.UnicodeScript.BAMUM),
    JAVANESE("javanese", Character.UnicodeScript.JAVANESE),
    MEETEI_MAYEK("meetei_mayek", Character.UnicodeScript.MEETEI_MAYEK),
    IMPERIAL_ARAMAIC("imperial_aramaic", Character.UnicodeScript.IMPERIAL_ARAMAIC),
    OLD_SOUTH_ARABIAN("old_south_arabian", Character.UnicodeScript.OLD_SOUTH_ARABIAN),
    INSCRIPTIONAL_PARTHIAN("inscriptional_parthian", Character.UnicodeScript.INSCRIPTIONAL_PARTHIAN),
    INSCRIPTIONAL_PAHLAVI("inscriptional_pahlavi", Character.UnicodeScript.INSCRIPTIONAL_PAHLAVI),
    OLD_TURKIC("old_turkic", Character.UnicodeScript.OLD_TURKIC),
    BRAHMI("brahmi", Character.UnicodeScript.BRAHMI),
    KAITHI("kaithi", Character.UnicodeScript.KAITHI),
    MEROITIC_HIEROGLYPHS("meroitic_hieroglyphs", Character.UnicodeScript.MEROITIC_HIEROGLYPHS),
    MEROITIC_CURSIVE("meroitic_cursive", Character.UnicodeScript.MEROITIC_CURSIVE),
    SORA_SOMPENG("sora_sompeng", Character.UnicodeScript.SORA_SOMPENG),
    CHAKMA("chakma", Character.UnicodeScript.CHAKMA),
    SHARADA("sharada", Character.UnicodeScript.SHARADA),
    TAKRI("takri", Character.UnicodeScript.TAKRI),
    MIAO("miao", Character.UnicodeScript.MIAO),
//    CAUCASIAN_ALBANIAN("caucasian_albanian", Character.UnicodeScript.CAUCASIAN_ALBANIAN),
//    BASSA_VAH("bassa_vah", Character.UnicodeScript.BASSA_VAH),
//    DUPLOYAN("duployan", Character.UnicodeScript.DUPLOYAN),
//    ELBASAN("elbasan", Character.UnicodeScript.ELBASAN),
//    GRANTHA("grantha", Character.UnicodeScript.GRANTHA),
//    PAHAWH_HMONG("pahawh_hmong", Character.UnicodeScript.PAHAWH_HMONG),
//    KHOJKI("khojki", Character.UnicodeScript.KHOJKI),
//    LINEAR_A("linear_a", Character.UnicodeScript.LINEAR_A),
//    MAHAJANI("mahajani", Character.UnicodeScript.MAHAJANI),
//    MANICHAEAN("manichaean", Character.UnicodeScript.MANICHAEAN),
//    MENDE_KIKAKUI("mende_kikakui", Character.UnicodeScript.MENDE_KIKAKUI),
//    MODI("modi", Character.UnicodeScript.MODI),
//    MRO("mro", Character.UnicodeScript.MRO),
//    OLD_NORTH_ARABIAN("old_north_arabian", Character.UnicodeScript.OLD_NORTH_ARABIAN),
//    NABATAEAN("nabataean", Character.UnicodeScript.NABATAEAN),
//    PALMYRENE("palmyrene", Character.UnicodeScript.PALMYRENE),
//    PAU_CIN_HAU("pau_cin_hau", Character.UnicodeScript.PAU_CIN_HAU),
//    OLD_PERMIC("old_permic", Character.UnicodeScript.OLD_PERMIC),
//    PSALTER_PAHLAVI("psalter_pahlavi", Character.UnicodeScript.PSALTER_PAHLAVI),
//    SIDDHAM("siddham", Character.UnicodeScript.SIDDHAM),
//    KHUDAWADI("khudawadi", Character.UnicodeScript.KHUDAWADI),
//    TIRHUTA("tirhuta", Character.UnicodeScript.TIRHUTA),
//    WARANG_CITI("warang_citi", Character.UnicodeScript.WARANG_CITI),
//    AHOM("ahom", Character.UnicodeScript.AHOM),
//    ANATOLIAN_HIEROGLYPHS("anatolian_hieroglyphs", Character.UnicodeScript.ANATOLIAN_HIEROGLYPHS),
//    HATRAN("hatran", Character.UnicodeScript.HATRAN),
//    MULTANI("multani", Character.UnicodeScript.MULTANI),
//    OLD_HUNGARIAN("old_hungarian", Character.UnicodeScript.OLD_HUNGARIAN),
//    SIGNWRITING("signwriting", Character.UnicodeScript.SIGNWRITING),
//    ADLAM("adlam", Character.UnicodeScript.ADLAM),
//    BHAIKSUKI("bhaiksuki", Character.UnicodeScript.BHAIKSUKI),
//    MARCHEN("marchen", Character.UnicodeScript.MARCHEN),
//    NEWA("newa", Character.UnicodeScript.NEWA),
//    OSAGE("osage", Character.UnicodeScript.OSAGE),
//    TANGUT("tangut", Character.UnicodeScript.TANGUT),
//    MASARAM_GONDI("masaram_gondi", Character.UnicodeScript.MASARAM_GONDI),
//    NUSHU("nushu", Character.UnicodeScript.NUSHU),
//    SOYOMBO("soyombo", Character.UnicodeScript.SOYOMBO),
//    ZANABAZAR_SQUARE("zanabazar_square", Character.UnicodeScript.ZANABAZAR_SQUARE),
//    HANIFI_ROHINGYA("hanifi_rohingya", Character.UnicodeScript.HANIFI_ROHINGYA),
//    OLD_SOGDIAN("old_sogdian", Character.UnicodeScript.OLD_SOGDIAN),
//    SOGDIAN("sogdian", Character.UnicodeScript.SOGDIAN),
//    DOGRA("dogra", Character.UnicodeScript.DOGRA),
//    GUNJALA_GONDI("gunjala_gondi", Character.UnicodeScript.GUNJALA_GONDI),
//    MAKASAR("makasar", Character.UnicodeScript.MAKASAR),
//    MEDEFAIDRIN("medefaidrin", Character.UnicodeScript.MEDEFAIDRIN),
//    ELYMAIC("elymaic", Character.UnicodeScript.ELYMAIC),
//    NANDINAGARI("nandinagari", Character.UnicodeScript.NANDINAGARI),
//    NYIAKENG_PUACHUE_HMONG("nyiakeng_puachue_hmong", Character.UnicodeScript.NYIAKENG_PUACHUE_HMONG),
//    WANCHO("wancho", Character.UnicodeScript.WANCHO),
//    YEZIDI("yezidi", Character.UnicodeScript.YEZIDI),
//    CHORASMIAN("chorasmian", Character.UnicodeScript.CHORASMIAN),
//    DIVES_AKURU("dives_akuru", Character.UnicodeScript.DIVES_AKURU),
//    KHITAN_SMALL_SCRIPT("khitan_small_script", Character.UnicodeScript.KHITAN_SMALL_SCRIPT),
    UNKNOWN("unknown", Character.UnicodeScript.UNKNOWN);

    private String code;

    private Character.UnicodeScript type;

    private JUnicodeScript(String code, Character.UnicodeScript type) {
        this.code = code;
        this.type = type;
    }
    public static JUnicodeScript codeOf(String code) {
        for (JUnicodeScript type : values()) {
            if (type.code.equals(code)) {
                return type;
            }
        }
        return null;
    }
}
