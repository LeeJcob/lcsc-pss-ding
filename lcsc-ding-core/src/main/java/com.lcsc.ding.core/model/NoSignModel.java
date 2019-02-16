package com.lcsc.ding.core.model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 漏卡详情
 */
public class NoSignModel {

    //时间格式
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * 日期
     */
    private String noSignDay;

    /**
     * 未打卡时间
     */
    private String noSignTime;

    /**
     * 是否申请审批
     */
    private Boolean hasProcess;

    public String getNoSignDay() {
        return noSignDay;
    }

    public void setNoSignDay(Date noSignDay) {

        this.noSignDay = simpleDateFormat1.format(noSignDay);
    }

    public String getNoSignTime() {
        return noSignTime;
    }

    public void setNoSignTime(Date noSignTime) {

        this.noSignTime = simpleDateFormat.format(noSignTime);
    }

    public Boolean getHasProcess() {
        return hasProcess;
    }

    public void setHasProcess(Boolean hasProcess) {
        this.hasProcess = hasProcess;
    }
}
