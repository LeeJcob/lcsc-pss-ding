package com.lcsc.ding.service.impl;

import com.dingtalk.api.response.OapiAttendanceListResponse;
import com.dingtalk.api.response.OapiProcessinstanceGetResponse;
import com.lcsc.ding.core.constant.Constant;
import com.lcsc.ding.core.model.LateModel;
import com.lcsc.ding.core.model.NoSignModel;
import com.lcsc.ding.core.model.SubsidyModel;
import com.lcsc.ding.core.util.DingUtil;
import com.lcsc.ding.core.util.HolidayUtil;
import com.lcsc.ding.core.util.ServiceResult;
import com.lcsc.ding.service.StatisticsService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.Period;
import org.joda.time.PeriodType;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 统计相关接口实现
 */
@Service
public class StatisticsServiceImpl implements StatisticsService {

    private static HolidayUtil holidayUtil = new HolidayUtil();

    @Override
    public ServiceResult<Map<String, Object>> getLateList(String userId, Integer year, Integer month) {

        Map<String, Object> result = new HashMap<>();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        // 当月第一天
        DateTime dateTime = new DateTime(year, month, 1, 0, 0);
        // 当月最后一天
        DateTime lastDay = dateTime.dayOfMonth().withMaximumValue();
        DateTime endDay = null;

        List<LateModel> lateModels = new ArrayList<>();
        // 考勤结果
        OapiAttendanceListResponse response = null;

        String timeResult = "";

        // 考勤设置  九点上班
        Date baseCheckTime = null;

        // 打卡时间
        Date userCheckTime = null;


        while (lastDay.isAfter(endDay = getLastDayOfWeek(dateTime)) || lastDay.isEqual(endDay)) {

            response = DingUtil.getAttendanceByUserId(dateTime.toDate(), endDay.toDate(), userId);

            dateTime = endDay.plusDays(1);

            if (null == response) {

                continue;
            }

            //根据考勤结果  解析异常考勤
            List<OapiAttendanceListResponse.Recordresult> recordresultList = response.getRecordresult();

            if (CollectionUtils.isEmpty(recordresultList)) {

                continue;
            }


            for (OapiAttendanceListResponse.Recordresult recordresult : recordresultList) {

                if (Constant.CHECKTYPE_OFFDUTY.equals(recordresult.getCheckType())) {

                    continue;
                }

                baseCheckTime = recordresult.getBaseCheckTime();
                DateTime baseCheckDate = new DateTime(baseCheckTime);

                timeResult = recordresult.getTimeResult();

                if (Constant.TIMERESULT_LATE.equals(timeResult) || Constant.TIMERESULT_SERIOUSLATE.equals(timeResult) || Constant.TIMERESULT_ABSENTEEISM.equals(timeResult)) {
                    LateModel lateModel = new LateModel();

                    lateModel.setLateDay(sdf.format(recordresult.getWorkDate()));


                    lateModel.setHasProcess(Boolean.FALSE);
                    userCheckTime = recordresult.getUserCheckTime();
                    lateModel.setSignTime(sdf.format(userCheckTime));
                    DateTime user = new DateTime(userCheckTime);
                    Period p = new Period(baseCheckDate, user, PeriodType.minutes());

                    lateModel.setLateMinutes(p.getMinutes());
                    // 迟到有相关的审批
                    if (StringUtils.isNotEmpty(recordresult.getProcInstId())) {

                        OapiProcessinstanceGetResponse.ProcessInstanceTopVo processInstanceTopVo = DingUtil.getProcessById(recordresult.getProcInstId());

                        if (Constant.PROCESS_RESULT_AGREE.equals(processInstanceTopVo.getResult())) {

                            //  审批是否通过
                            lateModel.setHasProcess(Boolean.TRUE);
                        }

                    }

                    List<String> lateProIds = DingUtil.getProcessByCodeAndId(Constant.LATE_PROCESS_CODE, userId, recordresult.getWorkDate(), lastDay.toDate());

                    if (CollectionUtils.isNotEmpty(lateProIds)) {

                        for (String proId : lateProIds) {

                            OapiProcessinstanceGetResponse.ProcessInstanceTopVo processInstanceTopVo = DingUtil.getProcessById(proId);

                            List<OapiProcessinstanceGetResponse.FormComponentValueVo> formComponentValues = processInstanceTopVo.getFormComponentValues();
                            //日期
                            OapiProcessinstanceGetResponse.FormComponentValueVo formDate = formComponentValues.get(0);

                            //截止
                            //  OapiProcessinstanceGetResponse.FormComponentValueVo formTime = formComponentValues.get(1);
                            if (sdf1.format(baseCheckDate.toDate()).equals(formDate.getValue())) {

                                lateModel.setHasProcess(Boolean.TRUE);
                            }


                        }

                    }

                    lateModels.add(lateModel);
                }

            }

        }

        Integer totalMinutes = 0;
        for (LateModel lateModel : lateModels) {

            if (!lateModel.getHasProcess()) {

                totalMinutes = totalMinutes + lateModel.getLateMinutes();
            }

        }

        result.put("totalMinutes", totalMinutes);
        result.put("late", lateModels);
        return ServiceResult.success(result);
    }

    @Override
    public ServiceResult<List<NoSignModel>> getNoSignList(String userId, Integer year, Integer month) {

        // 当月第一天
        DateTime dateTime = new DateTime(year, month, 1, 0, 0);

        // 当月最后一天
        DateTime lastDay = dateTime.dayOfMonth().withMaximumValue();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd");

        // 如果当前时间还小于最后天，那lastDay为当前时间
        DateTime nowTime = new DateTime();
        if (lastDay.isAfter(nowTime)) {

            lastDay = nowTime;
        }

        DateTime endDay = dateTime.plusDays(6);

        endDay = endDay.isAfter(lastDay) ? lastDay : endDay;

        // 考勤结果
        OapiAttendanceListResponse response = null;

        List<NoSignModel> noSignModelList = new ArrayList<>();

        while (lastDay.isAfter(endDay) || lastDay.isEqual(endDay)) {

            response = DingUtil.getAttendanceByUserId(dateTime.toDate(), endDay.toDate(), userId);

            //根据考勤结果  解析漏打卡的
            List<OapiAttendanceListResponse.Recordresult> recordresultList = response.getRecordresult();

            dateTime = endDay.plusDays(1);

            if (!endDay.isEqual(lastDay)) {

                endDay = dateTime.plusDays(6);
                endDay = endDay.isAfter(lastDay) ? lastDay : endDay;
            } else {

                endDay = dateTime.plusDays(6);
            }

            if (CollectionUtils.isEmpty(recordresultList)) {

                continue;
            }
            // 循环打卡记录
            for (OapiAttendanceListResponse.Recordresult recordResult : recordresultList) {

                // 不是工作日就过滤掉
                if (!holidayUtil.isWorkDay(recordResult.getWorkDate())) {

                    continue;
                }

                Boolean flag = Boolean.FALSE;

                // 如果有打卡记录，且时间结果不是未打卡
                if ("NotSigned".equals(recordResult.getTimeResult())) {


                    NoSignModel noSignModel = new NoSignModel();
                    noSignModel.setNoSignDay(recordResult.getWorkDate());
                    noSignModel.setNoSignTime(recordResult.getBaseCheckTime());

                    // 请假的code
                    if (StringUtils.isNotEmpty(recordResult.getProcInstId())) {

                        flag = true;

                    } else {

                        // 判断是否有提交漏打卡
                        List<String> noSignProIds = DingUtil.getProcessByCodeAndId(Constant.LACK_CARD_PROCESS_CODE, userId, recordResult.getWorkDate(), new Date());

                        for (String proId : noSignProIds) {

                            OapiProcessinstanceGetResponse.ProcessInstanceTopVo processInstanceTopVo = DingUtil.getProcessById(proId);

                            if (processInstanceTopVo != null) {

                                List<OapiProcessinstanceGetResponse.FormComponentValueVo> formComponentValues = processInstanceTopVo.getFormComponentValues();
                                OapiProcessinstanceGetResponse.FormComponentValueVo date = formComponentValues.get(0);
                                try {
                                    //该审批是当天的  并且排除一天两次漏打卡的情况
                                    if (simpleDateFormat1.parse(date.getValue()).compareTo(recordResult.getWorkDate()) == 0 &&
                                            !simpleDateFormat.parse(date.getValue()).before(recordResult.getBaseCheckTime())) {

                                        flag = true;
                                        break;
                                    }
                                } catch (ParseException e) {

                                    e.printStackTrace();
                                }


                            }
                        }
                    }
                    noSignModel.setHasProcess(flag);

                    noSignModelList.add(noSignModel);

                } else if ("APPROVE".equals(recordResult.getSourceType())) {

                    NoSignModel noSignModel = new NoSignModel();
                    noSignModel.setNoSignDay(recordResult.getWorkDate());
                    noSignModel.setNoSignTime(recordResult.getBaseCheckTime());
                    noSignModel.setHasProcess(true);

                    noSignModelList.add(noSignModel);
                }
            }
        }
        return ServiceResult.success(noSignModelList);
    }

    @Override
    public ServiceResult<Map<String, Object>> getSubsidyList(String userId, Integer year, Integer month) {

        Map<String, Object> result = new HashMap<>();
        List<SubsidyModel> subsidyModels = new ArrayList<>();
        // 获取用户所有的报销申请
        DateTime dateTime = new DateTime(year, month, 1, 0, 0);

        // 当月最后一天
        DateTime lastDay = dateTime.dayOfMonth().withMaximumValue();
        List<String> processIds = DingUtil.getProcessByCodeAndId(Constant.SUBSIDY_PROCESS_CODE, userId, dateTime.toDate(), lastDay.toDate());

        for (String process : processIds) {

            // 查询对应的审批
            OapiProcessinstanceGetResponse.ProcessInstanceTopVo processInstanceTopVo = DingUtil.getProcessById(process);

            if (processInstanceTopVo != null) {


                SubsidyModel subsidyModel = new SubsidyModel();
                //  审批是否通过    金额   日期  等
                List<OapiProcessinstanceGetResponse.FormComponentValueVo> formComponentValues = processInstanceTopVo.getFormComponentValues();
                OapiProcessinstanceGetResponse.FormComponentValueVo money = formComponentValues.get(0);
                OapiProcessinstanceGetResponse.FormComponentValueVo date = formComponentValues.get(1);
                subsidyModel.setMoney(new BigDecimal(money.getValue()));
                subsidyModel.setProcessDay(date.getValue());

                subsidyModel.setAgree(Boolean.FALSE);

                if (Constant.PROCESS_RESULT_AGREE.equals(processInstanceTopVo.getResult()) && Constant.PROCESS_RESULT_COMPLETED.equals(processInstanceTopVo.getStatus())) {

                    subsidyModel.setAgree(Boolean.TRUE);
                }

                subsidyModels.add(subsidyModel);
            }

        }

        BigDecimal totalMoney = BigDecimal.ZERO;

        for (SubsidyModel subsidyModel : subsidyModels) {

            if (subsidyModel.getAgree()) {

                totalMoney = totalMoney.add(subsidyModel.getMoney());
            }

        }
        result.put("totalMoney", totalMoney);
        result.put("subsidys", subsidyModels);
        return ServiceResult.success(result);
    }


    private static DateTime getLastDayOfWeek(DateTime beginDate) {

        DateTime lastDay = beginDate.dayOfMonth().withMaximumValue();
        return lastDay.isAfter(beginDate.plusDays(6)) ? beginDate.plusDays(6) : lastDay;
    }
}
