package com.lcsc.ding.controller;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiUserGetRequest;
import com.dingtalk.api.request.OapiUserGetuserinfoRequest;
import com.dingtalk.api.response.OapiUserGetResponse;
import com.dingtalk.api.response.OapiUserGetuserinfoResponse;
import com.lcsc.ding.core.UserModel;
import com.lcsc.ding.core.constant.Constant;
import com.lcsc.ding.core.constant.URLConstant;
import com.lcsc.ding.core.util.AccessTokenUtil;
import com.lcsc.ding.core.util.DingUtil;
import com.lcsc.ding.core.util.ServiceResult;
import com.lcsc.ding.service.UserService;
import com.taobao.api.ApiException;
import org.apache.tomcat.util.descriptor.web.ContextHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 企业内部E应用Quick-Start示例代码 实现了最简单的免密登录（免登）功能
 */
@RestController
public class IndexController {

    private static final Logger bizLogger = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private UserService userService;

    /**
     * 欢迎页面,通过url访问，判断后端服务是否启动
     */
    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public String welcome() {

        userService.test();
        return "welcome";
    }

    /**
     * 钉钉用户登录，显示当前登录用户的userId和名称
     *
     * @param requestAuthCode 免登临时code
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public ServiceResult login(@RequestParam(value = "authCode") String requestAuthCode) {
        //获取accessToken,注意正是代码要有异常流处理
        String accessToken = AccessTokenUtil.getToken();

        //获取用户信息
        DingTalkClient client = new DefaultDingTalkClient(URLConstant.URL_GET_USER_INFO);
        OapiUserGetuserinfoRequest request = new OapiUserGetuserinfoRequest();
        request.setCode(requestAuthCode);
        request.setHttpMethod("GET");

        OapiUserGetuserinfoResponse response;
        try {
            response = client.execute(request, accessToken);
        } catch (ApiException e) {
            e.printStackTrace();
            return null;
        }
        //3.查询得到当前用户的userId
        // 获得到userId之后应用应该处理应用自身的登录会话管理（session）,避免后续的业务交互（前端到应用服务端）每次都要重新获取用户身份，提升用户体验
        String userId = response.getUserid();
        UserModel userModel = new UserModel();
        userModel.setUserId(userId);

        String userName = saveAndUserLogin(accessToken, userId);
        userModel.setUserName(userName);

        //返回结果
        return ServiceResult.success(userModel);
    }

    /**
     * 存储用户信息并且登录到session中
     *
     * @param accessToken
     * @param userId
     * @return
     */
    private String saveAndUserLogin(String accessToken, String userId) {

        try {
            DingTalkClient client = new DefaultDingTalkClient(URLConstant.URL_USER_GET);
            OapiUserGetRequest request = new OapiUserGetRequest();
            request.setUserid(userId);
            request.setHttpMethod("GET");
            OapiUserGetResponse response = client.execute(request, accessToken);

            // 缓存用户和持久化用户
            DingUtil.addUserInfo(response);

            // 登录信息存放到session
            ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest servletRequest = servletRequestAttributes.getRequest();
            servletRequest.getSession().setAttribute(Constant.USER_SESSION_KEY, response);

            return response.getName();
        } catch (ApiException e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 获取用户考勤记录
     *
     * @param userId
     * @return
     */
    @RequestMapping(value = "/getAttendanceByUserId", method = RequestMethod.GET)
    @ResponseBody
    public void getAttendanceByUserId(String userId) {

        this.userService.getAttendanceByUserId(userId);
    }

}


