package com.lcsc.ding.core.constant;

/**
 * 项目中的常量定义类
 */
public class Constant {
    /**
     * 开发者后台->企业自建应用->选择您创建的E应用->查看->AppKey
     */
    public static final String APP_KEY = "dingbqyieeezkmxm1ytx";
    /**
     * 开发者后台->企业自建应用->选择您创建的E应用->查看->AppSecret
     */
    public static final String APP_SECRET = "_5yIhbpuRFJmHE7Y2vHrEbFoHY2HPiyAQZfv72TginbL9BOouXY8FuUGnAcCM9CS";


    public static final Long AGENT_ID = 213066815L;


    public static final String CORPID = "ding251335d31062a7f535c2f4657eb6378f";

    //请假审批
    public static final String LEAVE_PROCESS_CODE = "PROC-F18CD967-8E97-4D1E-A08E-C744DF73DA8E";

    //车费报销审批  TODO
    public static final String SUBSIDY_PROCESS_CODE = "PROC-F18CD967-8E97-4D1E-A08E-C744DF73DA8E";

    // 补卡审批
    public static final String LACK_CARD_PROCESS_CODE = "PROC-F18CD967-8E97-4D1E-A08E-C744DF73DA8E";

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
     * 审批拒绝
     */
    public static final String PROCESS_RESULT_REFUSE = "refuse";


}
