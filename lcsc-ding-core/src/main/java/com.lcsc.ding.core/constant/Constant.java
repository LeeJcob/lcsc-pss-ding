package com.lcsc.ding.core.constant;

/**
 * 项目中的常量定义类
 */
public class Constant {
    /**
     * 开发者后台->企业自建应用->选择您创建的E应用->查看->AppKey
     */
    //测试
    public static final String APP_KEY = "dingbqyieeezkmxm1ytx";
    //正式
    // public static final String APP_KEY = "dingpaqb8hsvp0ejfex0";
    /**
     * 开发者后台->企业自建应用->选择您创建的E应用->查看->AppSecret
     */
    //测试
    public static final String APP_SECRET = "_5yIhbpuRFJmHE7Y2vHrEbFoHY2HPiyAQZfv72TginbL9BOouXY8FuUGnAcCM9CS";
    //正式
    // public static final String APP_SECRET = "Pei44y6G3o3PoPd9VRcopAyCHsFG4XdITMkg6hazCvLlrVZ-TxAQlTpctSLOKJ79";

    //测试
    public static final Long AGENT_ID = 213066815L;
    //正式
    // public static final Long AGENT_ID = 229884416L;

    //测试
    public static final String CORPID = "ding251335d31062a7f535c2f4657eb6378f";
    //正式
    // public static final String CORPID = "ding3dd87e45b2064c1c35c2f4657eb6378f";


    //请假审批 测试
    public static final String LEAVE_PROCESS_CODE = "PROC-F18CD967-8E97-4D1E-A08E-C744DF73DA8E";
    //正式
    //   public static final String LEAVE_PROCESS_CODE = "PROC-6DE15F02-4779-4622-BDC4-6099FB89ED4A";


    //车费报销审批  测试
    public static final String SUBSIDY_PROCESS_CODE = "PROC-RFYJYKDV-8303FQBP1AL3EAU76DMU3-VS1RT6SJ-Q";
    //正式
    // public static final String SUBSIDY_PROCESS_CODE = "PROC-52IKRYIV-LMXPZ9RHVJBFBQP38GIY1-GGV3BT9J-C";


    // 迟到免扣款
    //正式
    //public static final String LATE_PROCESS_CODE = "PROC-QK0LJT9V-EU8M1OGPVL4CW20CQM8A2-EOCOJN4J-J";
    //测试
    public static final String LATE_PROCESS_CODE = "PROC-JFYJB9EV-R704Y9UK42D8E65RBQDL2-MCV5YKTJ-B";

    //CEO
    // public static final String LATE_PROCESS_CODE ="PROC-JFYJFWPV-DA040OG4X8BHI1OUOD982-BJEDFLTJ-I";
    // 补卡审批   测试
    public static final String LACK_CARD_PROCESS_CODE = "PROC-F18CD967-8E97-4D1E-A08E-C744DF73DA8E";

    //正式
    //  public static final String LACK_CARD_PROCESS_CODE = "PROC-EF6YRO35P2-JC9MKWOBSL6S56Z8852X1-188XJN4J-B";

    /**
     * 用户session key
     */
    public static final String USER_SESSION_KEY = "userInfo";

    /**
     * 用户信息持久化文件
     */
    public static final String FILE_STORAGE_PATH = "/userInfo.data";

    /**
     * 节假日配置文件
     */
    public static final String FILE_HOLIDAY_EXCEL_PATH = "/holiday.txt";

    /**
     * 工作日配置文件
     */
    public static final String FILE_WORKDAY_EXCEL_PATH = "/workday.txt";

    /**
     * 正常
     */
    public static final String TIMERESULT_NORMAL = "Normal";
    /**
     * 早退
     */
    public static final String TIMERESULT_EARLY = "Early";
    /**
     * 迟到
     */
    public static final String TIMERESULT_LATE = "Late";
    /**
     * 严重迟到
     */
    public static final String TIMERESULT_SERIOUSLATE = "SeriousLate";
    /**
     * 旷工迟到
     */
    public static final String TIMERESULT_ABSENTEEISM = "Absenteeism";
    /**
     * 未打卡
     */
    public static final String TIMERESULT_NOTSIGNED = "NotSigned";

    /**
     * 上班卡
     */
    public static final String CHECKTYPE_ONDUTY = "OnDuty";

    /**
     * 下班卡
     */
    public static final String CHECKTYPE_OFFDUTY = "OffDuty";

    /**
     * 审批同意
     */
    public static final String PROCESS_RESULT_AGREE = "agree";

    /**
     * 审批完成
     */
    public static final String PROCESS_RESULT_COMPLETED = "COMPLETED";

    /**
     * 审批拒绝
     */
    public static final String PROCESS_RESULT_REFUSE = "refuse";


}
