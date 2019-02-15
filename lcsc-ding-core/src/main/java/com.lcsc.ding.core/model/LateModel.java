package com.lcsc.ding.core.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 迟到详情
 */
public class LateModel implements Serializable {

    /**
     * 日期
     */
    private Date lateDay;

    /**
     * 签到时间
     */
    private Date signTime;

    /**
     * 迟到分钟
     */
    private Integer lateMinutes;

    /**
     * 是否申请
     */
    private Boolean hasProcess;

    public Date getLateDay() {
        return lateDay;
    }

    public void setLateDay(Date lateDay) {
        this.lateDay = lateDay;
    }

    public Date getSignTime() {
        return signTime;
    }

    public void setSignTime(Date signTime) {
        this.signTime = signTime;
    }

    public Integer getLateMinutes() {
        return lateMinutes;
    }

    public void setLateMinutes(Integer lateMinutes) {
        this.lateMinutes = lateMinutes;
    }

    public Boolean getHasProcess() {
        return hasProcess;
    }

    public void setHasProcess(Boolean hasProcess) {
        this.hasProcess = hasProcess;
    }
}
