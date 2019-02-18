package com.lcsc.ding.job;

import com.dingtalk.api.response.OapiAttendanceListResponse;
import com.lcsc.ding.core.constant.Constant;
import com.lcsc.ding.core.util.DingUtil;
import com.lcsc.ding.core.util.HolidayUtil;
import org.apache.commons.collections.CollectionUtils;
import org.joda.time.DateTime;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

@SpringBootApplication
@EnableScheduling
public class DayWarningTimer {

    @Scheduled(cron = "0 30 9 * * *")
    public void signInTimer() {

        try {

            boolean workDay = new HolidayUtil().isWorkDay(null);

            // 是工作日就开始核查打卡信息
            if (workDay) {

                Set<String> userIdList = DingUtil.getUserIdList();

                Date now = new Date();

                for (String userId : userIdList) {

                    OapiAttendanceListResponse attendance = DingUtil.getAttendanceByUserId(now, now, userId);
                    List<OapiAttendanceListResponse.Recordresult> recordresult = attendance.getRecordresult();

                    if (CollectionUtils.isEmpty(recordresult)) {

                        DingUtil.push(userId, "尊敬的用户你好，今日你忘记打上班卡了哦，请及时补卡");
                    }
                }

            }
        } catch (Exception ex) {

            ex.printStackTrace();
        }
    }

    @Scheduled(cron = "0 30 18,20,22 * * *")
    public void signOutTimer() {

        try {

            boolean workDay = new HolidayUtil().isWorkDay(null);

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

                    if (CollectionUtils.isEmpty(recordresult)) {

                        recordresult = new ArrayList<>();
                    }

                    boolean isException = true;

                    for (OapiAttendanceListResponse.Recordresult record : recordresult) {

                        if (record.getUserCheckTime().after(calendar.getTime())) {

                            isException = false;
                        }
                    }

                    if (isException) {

                        DingUtil.push(userId, "尊敬的用户你好，今日你忘记打下班卡了哦，请及时补卡");
                    }
                }

            }
        } catch (Exception ex) {

            ex.printStackTrace();
        }
    }


    @Scheduled(cron = "0 35 9 * * *")
    public void sumbitSubsidyTimer() {

        try {

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");

            String offTime = "22:00:00";

            Date userCheckTime = null;

            //前一天
            Date yesterday = new DateTime().minusDays(1).toDate();

            boolean workDay = new HolidayUtil().isWorkDay(yesterday);

            // 是工作日就开始核查打卡信息
            if (workDay) {

                Set<String> userIdList = DingUtil.getUserIdList();

                for (String userId : userIdList) {

                    OapiAttendanceListResponse attendance = DingUtil.getAttendanceByUserId(yesterday, yesterday, userId);
                    List<OapiAttendanceListResponse.Recordresult> recordresult = attendance.getRecordresult();

                    for (OapiAttendanceListResponse.Recordresult recordresult1 : recordresult) {


                        if (Constant.CHECKTYPE_OFFDUTY.equals(recordresult1.getCheckType()) && Constant.TIMERESULT_NORMAL.equals(recordresult1.getTimeResult())) {

                            userCheckTime = recordresult1.getUserCheckTime();

                            if (simpleDateFormat.parse(simpleDateFormat.format(userCheckTime)).after(simpleDateFormat.parse(offTime))) {

                                DingUtil.push(userId, "您昨晚辛苦加班到十点之后，若有打车，别忘了填报销单哦");
                            }

                        }

                    }
                }

            }
        } catch (Exception ex) {

            ex.printStackTrace();
        }
    }
}
