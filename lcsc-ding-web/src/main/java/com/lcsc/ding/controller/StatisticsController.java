package com.lcsc.ding.controller;

import com.lcsc.ding.core.model.NoSignModel;
import com.lcsc.ding.core.model.SubsidyModel;
import com.lcsc.ding.core.util.ServiceResult;
import com.lcsc.ding.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 页面统计接口
 */
@RestController("/statistics")
public class StatisticsController {

    @Autowired
    private StatisticsService statisticsService;

    /**
     * 根据年 - 月 查询迟到详情接口
     */

    @GetMapping("/late/list")
    public ServiceResult<Map<String, Object>> getLateList(String userid, Integer year, Integer month) {

        return this.statisticsService.getLateList(userid, year, month);
    }

    /**
     * 漏打卡列表
     *
     * @param year
     * @param month
     * @return
     */
    @GetMapping("/nosign/list")
    public ServiceResult<List<NoSignModel>> getNoSignList(String userid,Integer year, Integer month) {

        return this.statisticsService.getNoSignList(userid,year, month);
    }


    /**
     * 交通补贴列表
     *
     * @param year
     * @param month
     * @return
     */
    @GetMapping("/subsidy/list")
    public ServiceResult<Map<String, Object>> getSubsidyList(String userid,Integer year, Integer month) {

        return this.statisticsService.getSubsidyList(userid,year, month);
    }


}
