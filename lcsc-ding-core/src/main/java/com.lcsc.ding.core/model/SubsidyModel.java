package com.lcsc.ding.core.model;

import java.math.BigDecimal;
import java.util.Date;

public class SubsidyModel {

    private Date processDay;

    private BigDecimal money;

    private Boolean isAgree;

    public Date getProcessDay() {
        return processDay;
    }

    public void setProcessDay(Date processDay) {
        this.processDay = processDay;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public Boolean getAgree() {
        return isAgree;
    }

    public void setAgree(Boolean agree) {
        isAgree = agree;
    }
}
