package com.lcsc.ding.job;

import com.dingtalk.api.response.OapiAttendanceListResponse;
import com.lcsc.ding.core.util.DingUtil;
import com.lcsc.ding.core.util.HolidayUtil;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.*;

@SpringBootApplication
@EnableScheduling
public class DayWarningTimer {

    @Scheduled(cron = "0 30 9 * * *")
    public void signInTimer() {

        try {

            boolean workDay = new HolidayUtil().isWorkDay();

            // 是工作日就开始核查打卡信息
            if (workDay) {

                Set<String> userIdList = DingUtil.getUserIdList();

                Date now = new Date();

                for (String userId : userIdList) {

                    OapiAttendanceListResponse attendance = DingUtil.getAttendanceByUserId(now, now, userId);
                    List<OapiAttendanceListResponse.Recordresult> recordresult = attendance.getRecordresult();

                    if (CollectionUtils.isEmpty(recordresult)) {

                        DingUtil.push(userId, "尊敬的用户你好，今日你忘记打上班卡了哦");
                    }
                }

            }
        } catch (Exception ex) {

            ex.printStackTrace();
        }
    }

    @Scheduled(cron = "0 30 6,8,10 * * *")
    public void signOutTimer() {

        try {

            boolean workDay = new HolidayUtil().isWorkDay();

            // 是工作日就开始核查打卡信息
            if (workDay) {

                Set<String> userIdList = DingUtil.getUserIdList();

                Date now = new Date();

                Calendar calendar = Calendar.getInstance();
                calendar.setTime(now);
                calendar.set(Calendar.HOUR_OF_DAY, 18);

                for (String userId : userIdList) {

                    OapiAttendanceListResponse attendance = DingUtil.getAttendanceByUserId(now, now, userId);
                    List<OapiAttendanceListResponse.Recordresult> recordresult = attendance.getRecordresult();

                    if(CollectionUtils.isEmpty(recordresult)){

                        recordresult = new ArrayList<>();
                    }

                    boolean isException = true;

                    for (OapiAttendanceListResponse.Recordresult record : recordresult) {

                        if (record.getUserCheckTime().after(calendar.getTime())) {

                            isException = false;
                        }
                    }

                    if (isException) {

                        DingUtil.push(userId, "尊敬的用户你好，今日你忘记打下班卡了哦");
                    }
                }

            }
        } catch (Exception ex) {

            ex.printStackTrace();
        }
    }
}
