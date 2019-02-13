package com.lcsc.ding.core.model;

import java.util.List;

public class LateResult {

    private List<LateModel> lateModels;

    private Integer totalMinutes;

    public List<LateModel> getLateModels() {
        return lateModels;
    }

    public void setLateModels(List<LateModel> lateModels) {
        this.lateModels = lateModels;
    }

    public Integer getTotalMinutes() {
        return totalMinutes;
    }

    public void setTotalMinutes(Integer totalMinutes) {
        this.totalMinutes = totalMinutes;
    }
}
