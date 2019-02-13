package com.lcsc.ding.core.model;

import java.util.Date;

public class NoSignModel {

    private Date noSignDay;

    private Date noSignTime;

    private Boolean hasProcess;

    public Date getNoSignDay() {
        return noSignDay;
    }

    public void setNoSignDay(Date noSignDay) {
        this.noSignDay = noSignDay;
    }

    public Date getNoSignTime() {
        return noSignTime;
    }

    public void setNoSignTime(Date noSignTime) {
        this.noSignTime = noSignTime;
    }

    public Boolean getHasProcess() {
        return hasProcess;
    }

    public void setHasProcess(Boolean hasProcess) {
        this.hasProcess = hasProcess;
    }
}
