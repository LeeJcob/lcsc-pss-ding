package com.lcsc.ding.core.model;

import java.io.Serializable;

/**
 * 迟到详情
 */
public class LateModel implements Serializable {

    /**
     * 日期
     */
    private String lateDay;

    /**
     * 签到时间
     */
    private String signTime;

    /**
     * 迟到分钟
     */
    private Integer lateMinutes;

    /**
     * 是否申请
     */
    private Boolean hasProcess;

    public String getLateDay() {
        return lateDay;
    }

    public void setLateDay(String lateDay) {
        this.lateDay = lateDay;
    }

    public String getSignTime() {
        return signTime;
    }

    public void setSignTime(String signTime) {
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
