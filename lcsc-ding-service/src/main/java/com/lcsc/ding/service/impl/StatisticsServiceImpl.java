package com.lcsc.ding.service.impl;

import com.dingtalk.api.response.OapiAttendanceListResponse;
import com.lcsc.ding.core.constant.Constant;
import com.lcsc.ding.core.util.DingUtil;
import com.lcsc.ding.core.util.ServiceResult;
import com.lcsc.ding.service.StatisticsService;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.Period;
import org.joda.time.PeriodType;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 统计相关接口实现
 */
@Service
public class StatisticsServiceImpl implements StatisticsService {


    @Override
    public ServiceResult getLateList(Integer year, Integer month) {

        // 获取当前用户  TODO
        String userId = "manager4081";

        // 当月第一天
        DateTime dateTime = new DateTime(year, month, 1, 0, 0);

        // 当月最后一天
        DateTime lastDay = dateTime.dayOfMonth().withMaximumValue();

        DateTime endDay = null;

        // 考勤结果
        OapiAttendanceListResponse response = null;

        while (lastDay.isAfter(endDay = getLastDayOfWeek(dateTime)) || lastDay.isEqual(endDay)) {

            response = DingUtil.getAttendanceByUserId(dateTime.toDate(), endDay.toDate(), userId);

            //根据考勤结果  解析异常考勤
            judge(response);

            dateTime = endDay.plusDays(1);
        }


        return null;
    }


    public static void judge(OapiAttendanceListResponse response) {

        List<OapiAttendanceListResponse.Recordresult> recordresultList = response.getRecordresult();

        String timeResult = "";

        // 考勤设置  九点上班
        Date baseCheckTime = null;

        // 打卡时间
        Date userCheckTime = null;

        Integer lateMinutes = 0;
        for (OapiAttendanceListResponse.Recordresult recordresult : recordresultList) {

            if (Constant.CHECKTYPE_OFFDUTY.equals(recordresult.getCheckType())) {

                continue;
            }

            baseCheckTime = recordresult.getBaseCheckTime();
            DateTime baseCheckDate = new DateTime(baseCheckTime);

            timeResult = recordresult.getTimeResult();

            if (Constant.TIMERESULT_LATE.equals(timeResult) || Constant.TIMERESULT_SERIOUSLATE.equals(timeResult) || Constant.TIMERESULT_ABSENTEEISM.equals(timeResult)) {


                userCheckTime = recordresult.getUserCheckTime();
                DateTime user = new DateTime(userCheckTime);
                Period p = new Period(baseCheckDate, user, PeriodType.minutes());

                lateMinutes = lateMinutes + p.getMinutes();

                // 迟到有相关的审批
                if (StringUtils.isNotEmpty(recordresult.getProcInstId())) {

                    DingUtil.getProcessById(recordresult.getProcInstId());

                }

            }

            System.out.println(lateMinutes);
        }
    }


    private static DateTime getLastDayOfWeek(DateTime beginDate) {

        DateTime lastDay = beginDate.dayOfMonth().withMaximumValue();
        return lastDay.isAfter(beginDate.plusDays(6)) ? beginDate.plusDays(6) : lastDay;
    }
}
