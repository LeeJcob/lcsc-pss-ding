package com.lcsc.ding.service.impl;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiAttendanceListRecordRequest;
import com.dingtalk.api.request.OapiProcessinstanceGetRequest;
import com.dingtalk.api.request.OapiProcessinstanceListidsRequest;
import com.dingtalk.api.response.OapiAttendanceListRecordResponse;
import com.dingtalk.api.response.OapiProcessinstanceGetResponse;
import com.dingtalk.api.response.OapiProcessinstanceListidsResponse;
import com.lcsc.ding.core.constant.Constant;
import com.lcsc.ding.core.util.AccessTokenUtil;
import com.lcsc.ding.service.UserService;
import com.taobao.api.ApiException;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public void test() {

        System.out.print("hello");

    }

    @Override
    public void getAttendanceByUserId(String userId) {



    }





}
