package com.lcsc.ding.service;

import com.lcsc.ding.core.model.NoSignModel;
import com.lcsc.ding.core.util.ServiceResult;

import java.util.List;
import java.util.Map;

/**
 * 统计接口
 */
public interface StatisticsService {

    /**
     * 查询一个月内除去迟到免扣款后的迟到次数及时长
     * @param userid
     * @param year
     * @param month
     * @return
     */
    ServiceResult<Map<String, Object>> getLateList(String userid,Integer year, Integer month);


    /**
     * 查询一个月内漏打卡
     *
     * @param userid
     * @param month
     * @return
     */
    ServiceResult<List<NoSignModel>> getNoSignList(String userid, Integer year, Integer month);

    /**
     * 查询一个月的交通补贴
     *
     * @param userid
     * @param year
     * @param month
     * @return
     */
    ServiceResult<Map<String, Object>> getSubsidyList(String userid, Integer year, Integer month);
}
