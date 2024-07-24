package com.cycas.analyse.biz.enums;

import com.cycas.analyse.biz.strategy.IllegalStrategy;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum IllegalTypeEnum {

    ZHU_SHE_YONG_AI_SI_AO_MEI_LA_ZUO_NA(1, "注射用艾司奥美拉唑钠、注射用奥美拉唑"),
    ZHU_SHE_YONG_AN_QU_NAN(2, "注射用氨曲南"),
    YAN_SUAN_AN_XIU_SUO_ZHU_SHE_YE(2, "盐酸氨溴索注射液"),
    CAN_QI_FU_ZHENG_ZHU_SHE_YE(2, "参芪扶正注射液"),
    MO_SHAO_CAI_XUE(2, "末梢采血"),
    DONG_JING_MAI_ZHI_GUAN_HU_LI(2, "动静脉置管护理"),
    JU_BU_QIN_RUN_MA_ZUI(2, "局部浸润麻醉"),
    JING_MAI_SHU_YE(2, "静脉输液"),
    XI_TAN_HU_LI(2, "吸痰护理"),
    QI_GUAN_CHA_GUAN_SHU(2, "气管插管术"),
    SI_ZHI_XUE_GUAN_CAI_SE_DUO_PU_LE_CHAO_SHENG(2, "四肢血管彩色多普勒超声"),
    YI_BAN_ZHUAN_XIANG_HU_LI(2, "一般专项护理"),
    XIN_ZANG_CAI_SE_DUO_PU_LE_CHAO_SHENG(2, "心脏彩色多普勒超声"),
    XUE_QI_FEN_XI(2, "血气分析"),
    YAN_SUAN_MO_XI_SHA_XING_LV_HUA_NA_ZHU_SHE_YE(2, "盐酸莫西沙星氯化钠注射液"),
    YI_LIU_GUAN_YIN_LIU(2, "引流管引流(含引流护理及更换引流装置)"),
    ZHU_SHE_YONG_LA_YANG_TOU_BAO_NA(2, "注射用拉氧头孢钠"),
    PAI_LA_XI_LIN_NA_TA_ZUO_BA_TAN_NA(2, "哌拉西林钠他唑巴坦钠"),
    ZHUI_GUAN_NEI_ZHI_GUAN_SHU(2, "椎管内置管术"),
    NIAO_CHEN_ZHA_DING_LIANG(2, "尿沉渣定量"),
    ;

    private int type;
    private String name;

    public static IllegalTypeEnum match(int type) {
        for (IllegalTypeEnum val : values()) {
            if (val.getType() == type) {
                return val;
            }
        }
        return null;
    }
}
