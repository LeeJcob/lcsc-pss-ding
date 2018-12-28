package com.lcsc.ding.core.vo;

import java.util.Date;

/**
 * 迟到统计VO
 */

public class LateStaticsVO {

    //迟到日期

    private Date lateDay;

    //打卡时间
    private Date checkTime;

    //迟到分钟
    private Integer minutes;

    // 是否提交迟到免扣款
    private Boolean noDeduct;
}
