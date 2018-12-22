package com.lcsc.ding.service.impl;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiAttendanceListRecordRequest;
import com.dingtalk.api.response.OapiAttendanceListRecordResponse;
import com.lcsc.ding.core.util.AccessTokenUtil;
import com.lcsc.ding.core.util.ServiceResult;
import com.lcsc.ding.service.UserService;
import com.taobao.api.ApiException;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public void test() {
        System.out.print("hello");
    }

    @Override
    public void getAttendanceByUserId(String userId) {

        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/attendance/listRecord");
        OapiAttendanceListRecordRequest request = new OapiAttendanceListRecordRequest();
        request.setCheckDateFrom("2018-12-20 00:00:00");
        request.setCheckDateTo("2018-12-23 00:00:00");
        request.setUserIds(Arrays.asList("manager4081"));
        OapiAttendanceListRecordResponse execute = null;
        try {
            execute = client.execute(request, AccessTokenUtil.getToken());

        } catch (ApiException e) {
            e.printStackTrace();
        }

    }
}
