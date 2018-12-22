package com.lcsc.ding.core.util;

import com.alibaba.fastjson.JSONObject;
import com.dingtalk.api.response.OapiUserGetResponse;
import com.lcsc.ding.core.constant.Constant;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.io.FileUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DingUtil {

    // 用户信息map缓存
    private static Map<String, OapiUserGetResponse> userInfoMap = new ConcurrentHashMap<String, OapiUserGetResponse>();

    /**
     * 存储user信息
     *
     * @param response
     */
    public static void addUserInfo(OapiUserGetResponse response) {

        if (null == response || null == response.getUserid()) {

            return;
        }

        try {
            if (null == userInfoMap.get(response.getUserid())) {

                FileUtils.writeStringToFile(new File(ResourceUtils.getURL("classpath:").getPath() + Constant.FILE_STORAGE_PATH), JSONObject.toJSONString(response) + System.getProperty("line.separator"), "utf-8", true);
            }
        } catch (IOException e) {

            e.printStackTrace();
        }
        userInfoMap.put(response.getUserid(), response);
    }

    /**
     * 根据userid获取用户信息
     *
     * @param userId
     * @return
     */
    public static OapiUserGetResponse getUserInfo(String userId) {

        // 如果内存中的用户信息为空，那尝试从本地缓存中获取用户信息
        if (MapUtils.isEmpty(userInfoMap)) {

            initUserInfoMap();
        }
        return userInfoMap.get(userId);
    }

    /**
     * 清空内存中的用户信息
     */
    public static void clearUserInfoMap() {

        userInfoMap.clear();
    }

    /**
     * 初始化加载用户信息
     */
    public static void initUserInfoMap() {

        try {

            clearUserInfoMap();
            File file = new File(ResourceUtils.getURL("classpath:").getPath() + Constant.FILE_STORAGE_PATH);

            // 文件存在才需要加载用户信息
            if (file.exists()) {

                List<String> userStrList = FileUtils.readLines(file, "utf-8");
                for (String userStr : userStrList) {

                    OapiUserGetResponse userInfo = JSONObject.parseObject(userStr, OapiUserGetResponse.class);
                    userInfoMap.put(userInfo.getUserid(), userInfo);
                }
            }
        } catch (IOException e) {

            e.printStackTrace();
        }
    }


    /**
     * 获取当前用户的userid
     *
     * @return
     */
    public static String getCurrentUserId() {

        // 登录信息存放到session
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest servletRequest = servletRequestAttributes.getRequest();
        Object currentUser = servletRequest.getSession().getAttribute(Constant.USER_SESSION_KEY);
        if (null != currentUser) {

            OapiUserGetResponse userInfo = (OapiUserGetResponse) currentUser;
            return userInfo.getUserid();
        }

        return null;
    }

}
