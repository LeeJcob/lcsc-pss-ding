package com.lcsc.ding.core.model;

import java.io.Serializable;
import java.util.Date;

public class LateModel implements Serializable {

    private Date lateDay;

    private Date signTime;

    private Integer lateMinutes;

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
