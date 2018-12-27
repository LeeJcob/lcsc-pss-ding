package com.lcsc.ding.service.impl;

import com.lcsc.ding.core.util.DingUtil;
import com.lcsc.ding.core.util.ServiceResult;
import com.lcsc.ding.service.StatisticsService;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

/**
 * 统计相关接口实现
 */
@Service
public class StatisticsServiceImpl implements StatisticsService {


    @Override
    public ServiceResult getLateList(Integer year, Integer month) {

        // 获取当前用户  TODO
        String userId = "manager4081";

        DateTime dateTime = new DateTime(year, month,1,0,0);

        // 这个月最后一天
        DateTime lastDay=  dateTime.dayOfMonth().withMaximumValue();

        // 递归查询
      //  if()
      //  DingUtil.getAttendanceByUserId()

        return null;
    }

    public  static void main (String args[]){
        DateTime dateTime = new DateTime(2019, 1,1,0,0);

        DateTime end = dateTime.withDayOfWeek(7);
        DateTime lastDay=  dateTime.dayOfMonth().withMaximumValue();
        System.out.println( dateTime.toDate());
        System.out.println( end.toDate());

        dateTime=    dateTime.plusDays(6);
        end = dateTime.withDayOfWeek(7);
        System.out.println( dateTime.toDate());
        System.out.println( end.toDate());
        System.out.println( lastDay);

    }
}
