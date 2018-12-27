package com.lcsc.ding.service;

import com.lcsc.ding.core.util.ServiceResult;

/**
 * 统计接口
 */
public interface StatisticsService {

    /**
     * 查询一个月内除去迟到免扣款后的迟到次数及时长
     * @param month
     * @param day
     * @return
     */
    ServiceResult getLateList(Integer month, Integer day);
}
