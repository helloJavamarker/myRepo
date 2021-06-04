/*
package com.test.mark.zhang.

import org.apache.commons.lang3.StringUtils;

public enum TradeSourceEnums {
    TB("TB", "淘宝", "", null),
    TMALL("TMALL", "天猫", "", "8"),
    AE("AE", "AE", "", "1"),
    @Deprecated
    LZD("LZD", "LZD", "", "7"),
    LAZADA("LAZADA", "LAZADA", "", "6"),
    B2B("B2B", "B2B", "", null),
    ICBU("ICBU", "ICBU", "", "11"),
    TB_TW("TW", "TMW-台湾", "TB_TW", "8"),
    CBU("1688", "1688", "1688", "4"),
    MEILIHUI("MEILIHUI", "杭州魅俪信息技术有限公司", "233", null),
    NI_SU("NI_SU", "浙江妮素网络科技股份有限公司", "711", null),
    JIA_YI_LE("JIA_YI_LE", "广州家颐乐国际商贸有限公司", "231", null),
    JIAN_AN_XI("JIAN_AN_XI", "健安喜(上海)贸易有限公司", "712", null),
    ZHONG_YUAN_HAI_YUN("ZHONG_YUAN_HAI_YUN", "广州中远海运电商供应链管理有限公司", "710", null),
    TMALL_OVERSEA("TMALL_OVERSEA", "天猫出海", "", "2"),
    IC_YOUZAN("YOUZAN","有赞","713", "YOUZAN"),
    KAOLA("KAOLA", "考拉", "750", "KAOLA"),
    KAOLA_ZT("KAOLA_ZT", "考拉自提", "", ""),
    TAOXIAOPU("TXP","淘小铺","", "TXP"),
    YMATOU("YMATOU","洋码头","801", "YMATOU"),
    TMALL_OVERSEA_SELF("TMALL_OVERSEA_SELF","天猫出海类自营","", "4"),
    TMALL_MARKET_OVERSEA("TMALL_MARKET_OVERSEA", "猫超出海", "", "3"),
    EXTERNAL_ORDER_1688("EXTERNAL_ORDER_1688","1688外单","", "5"),
    EXTERNAL_ORDER_CP("EXTERNAL_ORDER_CP", "CP外单", "", "6"),
    HUAN_QIU_BU_SHOU("HUAN_QIU_BU_SHOU", "环球捕手", "708", "HUAN_QIU_BU_SHOU"),
    BEI_DIAN("BEI_DIAN", "贝店", "802", "BEI_DIAN"),
    YUN_JI("YUN_JI", "云集", "707", "YUN_JI"),
    MI_YA("MI_YA", "蜜芽", "821", "MI_YA"),
    YI_TIAO("YI_TIAO", "一条", "819", "YI_TIAO"),
    SU_NING_GUO_JI("SU_NING_GUO_JI", "苏宁国际", "203", "SU_NING_GUO_JI"),
    DA_V_DIAN("DA_V_DIAN", "大V店", "803", "DA_V_DIAN"),
    WEI_PIN_HUI("WEI_PIN_HUI", "唯品会", "207", "WEI_PIN_HUI"),
    QUAN_QIU_SHI_KE("QUAN_QIU_SHI_KE", "全球时刻", "804", "QUAN_QIU_SHI_KE"),
    DA_LING("DA_LING", "达令", "805", "DA_LING"),
    MO_GU_JIE("MO_GU_JIE", "蘑菇街", "222", "MO_GU_JIE"),
    WEI_NI_HAI_GOU("WEI_NI_HAI_GOU", "唯妮海购", "818", "WEI_NI_HAI_GOU"),
    JU_MEI_YOU_PIN("JU_MEI_YOU_PIN", "聚美优品", "211", "JU_MEI_YOU_PIN"),
    YANG_CONG("YANG_CONG", "洋葱", "806", "YANG_CONG"),
    HAI_PAI_KE("HAI_PAI_KE", "海拍客", "807", "HAI_PAI_KE"),
    HAI_DAI("HAI_DAI", "海带", "808", "HAI_DAI"),
    HAI_ZI_WANG("HAI_ZI_WANG", "孩子王", "809", "HAI_ZI_WANG"),
    XIAO_HEI_YU("XIAO_HEI_YU", "小黑鱼", "810", "XIAO_HEI_YU"),
    PDD("PDD", "拼多多", "701", "PDD"),
    JD("JD", "京东", "214", "JD"),
    SI_KU("SI_KU", "寺库", "811", "SI_KU"),
    QIN_BAO_BAO("QIN_BAO_BAO", "亲宝宝", "812", "QIN_BAO_BAO"),
    WEI_MENG("WEI_MENG", "微盟", "813", "WEI_MENG"),
    WEI_DIAN("WEI_DIAN", "微店", "814", "WEI_DIAN"),
    AI_KU_CUN("AI_KU_CUN", "爱库存", "815", "AI_KU_CUN"),
    MEI_RI_YI_TAO("MEI_RI_YI_TAO", "每日一淘", "816", "MEI_RI_YI_TAO"),
    MEI_TUN_MAMA("MEI_TUN_MAMA", "美囤妈妈", "817", "MEI_TUN_MAMA"),
    SMALL_RED_BOOK("SMALL_RED_BOOK", "小红书", "706", "SMALL_RED_BOOK"),
    BAI_BAO_DIAN_SHANG("BAI_BAO_DIAN_SHANG", "百宝电商", "820", "BAI_BAO_DIAN_SHANG"),
    LIANG_LI_GO("LIANG_LI_GO", "靓丽GO", "822", "LIANG_LI_GO"),
    GLOBAL_KANG_PIN("GLOBAL_KANG_PIN", "环球康品", "823", "GLOBAL_KANG_PIN"),
    YUE_YANG_STORE("YUE_YANG_STORE", "越洋店铺", "824", "YUE_YANG_STORE"),
    WAN_WU("WAN_WU", "万物心选", "825", "WAN_WU"),
    XIAO_MI("XIAO_MI", "小米有品", "826", "XIAO_MI"),
    DAN_CHUANG("DAN_CHUANG", "单创", "827", "DAN_CHUANG"),
    ICC("ICC", "ICC全球跨境商城", "828", "ICC"),
    TIAN_GOU("TIAN_GOU", "大商天狗网", "829", "TIAN_GOU"),
    WAN_GUO("WAN_GUO", "万国优品", "830", "WAN_GUO"),
    BO_LO("BO_LO", "波罗蜜（舶乐蜜）", "831", "BO_LO"),
    NICO("NICO", "年糕妈妈", "832", "NICO"),
    XI_TUAN("XI_TUAN", "喜团", "833", "XI_TUAN"),
    WAN_DOU("WAN_DOU", "豌豆公主", "834", "WAN_DOU"),
    BIE_YANG("BIE_YANG", "别样海外购", "835", "BIE_YANG"),
    TAO_FEN_XIAO("TAO_FEN_XIAO", "淘分销", "836", "TAO_FEN_XIAO"),
    TONG_XUAN("TONG_XUAN", "童选良品", "837", "TONG_XUAN"),
    YI_JIE("YI_JIE", "中石化易捷海购", "838", "YI_JIE"),
    AO_TAO("AO_TAO", "澳淘", "839", "AO_TAO"),
    BAO_MA("BAO_MA", "宝妈时光", "840", "BAO_MA"),
    YI_YOU("YI_YOU", "易优海购（渝新汇）", "841", "YI_YOU"),
    NIU_SHI_LAN("NIU_SHI_LAN", "纽仕兰新云", "842", "NIU_SHI_LAN"),
    HAI_GOU_TIAN_XIA("HAI_GOU_TIAN_XIA", "海购天下（渝新汇）", "843", "HAI_GOU_TIAN_XIA"),
    XIN_OU_HUI("XIN_OU_HUI", "新欧汇（渝新汇）", "844", "XIN_OU_HUI"),
    SHANG_HE_GOU("SHANG_HE_GOU", "上禾购（渝新汇）", "845", "SHANG_HE_GOU"),
    DA_XIAO_BAO_BEI("DA_XIAO_BAO_BEI", "大小宝贝（渝新汇）", "846", "DA_XIAO_BAO_BEI"),
    JU_OU_HUI("JU_OU_HUI", "聚欧惠（渝新汇）", "847", "JU_OU_HUI"),
    JING_MING_DA("JING_MING_DA", "景铭达（渝新汇）", "848", "JING_MING_DA"),
    OU_PIN_JIA("OU_PIN_JIA", "欧品家（渝新汇）", "849", "OU_PIN_JIA"),
    TONG_NIAN_SHI_GUANG("TONG_NIAN_SHI_GUANG", "童年时光（渝新汇）", "850", "TONG_NIAN_SHI_GUANG"),
    BIOCARE("BIOCARE", "BioCare官方小程序商城", "851", "BIOCARE"),
    TAI_XIONG_MALL("TAI_XIONG_MALL", "呔熊商城", "852", "TAI_XIONG_MALL"),
    YOU_A("YOU_A", "友阿海外购", "853", "YOU_A"),
    BAO_MA_LE_GOU("BAO_MA_LE_GOU", "宝妈乐购", "854", "BAO_MA_LE_GOU"),
    BAI_DA_YI_GOU("BAI_DA_YI_GOU", "百大易购", "855", "BAI_DA_YI_GOU"),
    BAI_YANG_MALL("BAI_YANG_MALL", "百洋商城", "856", "BAI_YANG_MALL"),
    QING_YI_MEI("QING_YI_MEI", "倾伊美", "857", "QING_YI_MEI"),
    DAN_DING("DAN_DING", "但丁分销商城", "858", "DAN_DING"),
    AO_XIN("AO_XIN", "澳新国际", "859", "AO_XIN"),
    AO_MAI_JIA("AO_MAI_JIA", "奥买家", "860", "AO_MAI_JIA"),
    DING_XIANG("DING_XIANG", "丁香医生", "861", "DING_XIANG"),
    AI_GOU("AI_GOU", "爱购保税", "862", "AI_GOU"),
    GUO_JI_MA_MI("GUO_JI_MA_MI", "国际妈咪", "863", "GUO_JI_MA_MI"),
    HUI_YANG_GOU("HUI_YANG_GOU", "慧眼购", "864", "HUI_YANG_GOU"),
    HEMA("HEMA", "盒马", "", "HEMA"),
    XING_YUN("XING_YUN", "行云全球汇", "865", "XING_YUN"),
    JI_KE_DUO("JI_KE_DUO", "集客多", "866", "JI_KE_DUO"),
    PETWIFI("PETWIFI", "Petwifi", "867", "PETWIFI"),
    MY_BB_MM("MY_BB_MM", "我的爸爸妈妈", "868", "MY_BB_MM"),
    ONC_MALL("ONC_MALL", "ONC商城", "869", "ONC_MALL"),
    AO_OU_TONG("AO_OU_TONG", "澳欧通（渝新汇）", "870", "AO_OU_TONG"),
    OU_RUI_BAO("OU_RUI_BAO", "欧睿宝（渝新汇）", "871", "OU_RUI_BAO"),
    YI_QIN_BEI("YI_QIN_BEI", "伊亲贝（渝新汇）", "872", "YI_QIN_BEI"),
    HAI_TAO_RI_JI("HAI_TAO_RI_JI", "海淘日记（量贩云仓）", "873", "HAI_TAO_RI_JI"),
    HAI_NAN_LI_DAO("HAI_NAN_LI_DAO", "海南离岛补购", "874", "HAI_NAN_LI_DAO"),
    XIAO_HONG_SHU_FLS("XIAO_HONG_SHU_FLS", "小红书（福利社供货商直发）", "875", "XIAO_HONG_SHU_FLS"),
    WAN_LI_MU("WAN_LI_MU", "万里目", "876", "WAN_LI_MU"),
    XIE_CHENG_YOU_PIN("XIE_CHENG_YOU_PIN", "携程优品商城", "877", "XIE_CHENG_YOU_PIN"),
    YAN_XUAN_WU_YU("YAN_XUAN_WU_YU", "颜选物语", "879", "YAN_XUAN_WU_YU"),
    GONG_LU_SHANG_DIAN("GONG_LU_SHANG_DIAN", "公路商店", "880", "GONG_LU_SHANG_DIAN"),
    PING_AN_DOCTOR("PING_AN_DOCTOR", "平安好医生", "881", "PING_AN_DOCTOR"),
    QI_SE_MALL("QI_SE_MALL", "起色商城", "882", "QI_SE_MALL"),
    MO_KUAI_XI_XUAN("MO_KUAI_XI_XUAN", "魔筷星选", "886", "MO_KUAI_XI_XUAN"),
    LUO_YUAN_BABY("LUO_YUAN_BABY", "洛远宝宝", "887", "LUO_YUAN_BABY"),
    YI_XIN_XUAN("YI_XIN_XUAN", "壹心选", "888", "YI_XIN_XUAN"),
    SHAN_CHA_HUA("SHAN_CHA_HUA", "山茶花", "889", "SHAN_CHA_HUA"),
    FU_YAN_BABY("FU_YAN_BABY", "符言贝贝", "890", "FU_YAN_BABY"),
    CLOUDO("CLOUDO", "CLOUDO", "891", "CLOUDO"),
    PING_AN_YI_QIAN_BAO("PING_AN_YI_QIAN_BAO", "平安壹钱包", "892", "PING_AN_YI_QIAN_BAO"),
    YUN_SHANG_HAI_XUAN("YUN_SHANG_HAI_XUAN", "云商海选", "893", "YUN_SHANG_HAI_XUAN"),
    YUN_SHANG_HUI_XUAN("YUN_SHANG_HUI_XUAN", "云商惠选", "894", "YUN_SHANG_HUI_XUAN"),
    LIKING("LIKING", "LIKING", "895", "LIKING"),
    TANG_YIN_DIAN_ZI("TANG_YIN_DIAN_ZI", "唐印电子", "896", "TANG_YIN_DIAN_ZI"),
    XIN_HUI_BAI_JIA("XIN_HUI_BAI_JIA", "鑫汇百家电子商务", "897", "XIN_HUI_BAI_JIA"),
    YOU_GOU_BEI_OU("YOU_GOU_BEI_OU", "优购北欧", "898", "YOU_GOU_BEI_OU"),
    JIN_JU_LAN("JIN_JU_LAN", "金菊兰科技", "899", "JIN_JU_LAN"),
    NUO_WEI_LV_YUAN("NUO_WEI_LV_YUAN", "诺威绿源", "900", "NUO_WEI_LV_YUAN"),
    TING_HAO_DIAN_ZI("TING_HAO_DIAN_ZI", "庭豪电子", "901", "TING_HAO_DIAN_ZI"),
    SI_LE_JIA("SI_LE_JIA", "思乐家", "902", "SI_LE_JIA"),
    DUO_DIAN("DUO_DIAN", "多点", "904", "DUO_DIAN"),
    NAN_GUA_TAO("NAN_GUA_TAO", "南瓜淘", "905", "NAN_GUA_TAO"),
    XIAO_MANG_DIAN_SHANG("XIAO_MANG_DIAN_SHANG", "小芒电商", "906", "XIAO_MANG_DIAN_SHANG"),
    AI_KU_XING_QIU("AI_KU_XING_QIU", "爱酷星球", "907", "AI_KU_XING_QIU"),
    HUI_HUI_TONG("HUI_HUI_TONG", "惠汇通", "908", "HUI_HUI_TONG"),
    BEI_JIA_BEI("BEI_JIA_BEI", "贝加贝", "909", "BEI_JIA_BEI"),
    GUO_SI_MAMI_AI("GUO_SI_MAMI_AI", "果思妈咪爱", "910", "GUO_SI_MAMI_AI"),
    HUAN_QIU_CHENG_PIN("HUAN_QIU_CHENG_PIN", "寰球诚品", "911", "HUAN_QIU_CHENG_PIN"),
    DA_XIAO_BABY_2("DA_XIAO_BABY_2", "大小宝贝2", "912", "DA_XIAO_BABY_2"),
    JU_OU_HUI_2("JU_OU_HUI_2", "聚欧惠2", "913", "JU_OU_HUI_2"),
    YANG_MA_CHENG_PIN("YANG_MA_CHENG_PIN", "扬玛诚品", "914", "YANG_MA_CHENG_PIN"),
    HAI_NAN_HEI_HU("HAI_NAN_HEI_HU", "海南黑虎", "915", "HAI_NAN_HEI_HU"),
    BO_QI("BO_QI", "波奇网", "916", "BO_QI"),
    CHAN_SHI_GUAN("CHAN_SHI_GUAN", "铲屎官爱省钱", "917", "CHAN_SHI_GUAN")
    ;

    private String code;
    private String desc;
    private String cnOrderSource;
    */
/**
     * 平台编码，gsdp下发的编码
     *//*

    private String platformCode;
    public static TradeSourceEnums getTradeSourceType(String orderSource) {
        if(Strings.isNullOrEmpty(orderSource)){
            return null;
        }
        for (TradeSourceEnums tradeSourceEnums : TradeSourceEnums.values()) {
            if (orderSource.equals(tradeSourceEnums.getPlatformCode())) {
                return tradeSourceEnums;
            }
        }

        return  "713".equals(orderSource) ? IC_YOUZAN : null;
    }

    public static TradeSourceEnums getTradeSource(String orderSourceCode) {
        for (TradeSourceEnums tradeSourceEnums : TradeSourceEnums.values()) {
            if (tradeSourceEnums.getCode().equals(orderSourceCode)) {
                return tradeSourceEnums;
            }
        }
        return null;
    }

    */
/**
     * 根据code获取描述
     *
     *//*

    public static String getTradeDescByCode(String code){
        for (TradeSourceEnums tradeSourceEnums : TradeSourceEnums.values()) {
            if (tradeSourceEnums.getCode().equals(code)) {
                return tradeSourceEnums.getDesc();
            }
        }
        return null;
    }


    public static TradeSourceEnums getTradeEnumByCode(String code){
        for (TradeSourceEnums tradeSourceEnums : TradeSourceEnums.values()) {
            if (tradeSourceEnums.getCode().equals(code)) {
                return tradeSourceEnums;
            }
        }
        return null;
    }

    public static TradeSourceEnums getSourceByCNOrderSource(String cnOrderSource) {
        for (TradeSourceEnums tradeSourceEnums : TradeSourceEnums.values()) {
            if (StringUtils.isNotBlank(tradeSourceEnums.getCnOrderSource()) && tradeSourceEnums.getCnOrderSource()
                    .equals(
                            cnOrderSource)) {
                return tradeSourceEnums;
            }
        }
        return null;
    }

    */
/**
     * 根据传入的code，依照code--platformCode--cnOrderSource的维度顺序去匹配，如果能够匹配，则返回该枚举中对应的desc
     *
     * @param code 不确定的code，可能是code，也可能是platformCode或者cnOrderSource
     * @return 有匹配则是对应的desc，否则是null
     *//*

    public static String getTradeDesByCodeWithThreeTest(String code){
        if(Strings.isNullOrEmpty(code)){
            return null;
        }
        for (TradeSourceEnums tradeSourceEnums : TradeSourceEnums.values()) {
            boolean isCode = StringUtils.isNotBlank(tradeSourceEnums.getCode())&&tradeSourceEnums.getCode().equals(code);
            boolean isPlatformCode = StringUtils.isNotBlank(tradeSourceEnums.getPlatformCode())&&tradeSourceEnums.getPlatformCode().equals(code);
            boolean isCnOrderSource = StringUtils.isNotBlank(tradeSourceEnums.getCnOrderSource())&&tradeSourceEnums.getCnOrderSource().equals(code);
            if (isCode || isPlatformCode || isCnOrderSource) {
                return tradeSourceEnums.getDesc();
            }
        }
        return null;
    }

    public static boolean hemaOTOOrder(String orderSource, String isZT) {
        return TradeSourceEnums.HEMA.getCode().equalsIgnoreCase(orderSource) && GccsFeatureKey.IS_Y.equalsIgnoreCase(
            isZT);
    }
}*/
