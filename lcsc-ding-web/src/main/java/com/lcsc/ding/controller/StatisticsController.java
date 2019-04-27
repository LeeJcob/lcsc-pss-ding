package com.lcsc.ding.controller;

import com.lcsc.ding.core.model.NoSignModel;
import com.lcsc.ding.core.util.ServiceResult;
import com.lcsc.ding.service.StatisticsService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
    public ServiceResult<List<NoSignModel>> getNoSignList(String userid, Integer year, Integer month) {

        return this.statisticsService.getNoSignList(userid, year, month);
    }


    /**
     * 交通补贴列表
     *
     * @param year
     * @param month
     * @return
     */
    @GetMapping("/subsidy/list")
    public ServiceResult<Map<String, Object>> getSubsidyList(String userid, Integer year, Integer month) {

        return this.statisticsService.getSubsidyList(userid, year, month);
    }


    /**
     * 提交迟到免扣款
     *
     * @param year
     * @param month
     * @return
     *//*
    @ApiOperation(value = "提交迟到免扣款")
    @PostMapping("/apply/late")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userid", value = "用户id", dataType = "String"),
            @ApiImplicitParam(name = "remark", value = "迟到备注", dataType = "String")
    })

    public ServiceResult<Map<String, Object>> applyLate(String userid, String remark, Integer month) {

        return this.statisticsService.getSubsidyList(userid, year, month);
    }*/

}
