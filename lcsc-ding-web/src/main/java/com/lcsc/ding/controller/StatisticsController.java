package com.lcsc.ding.controller;

import com.lcsc.ding.core.util.ServiceResult;
import com.lcsc.ding.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

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
    public ServiceResult getLateList(Integer year,Integer month) {

        return statisticsService.getLateList(year,month);
    }


}
