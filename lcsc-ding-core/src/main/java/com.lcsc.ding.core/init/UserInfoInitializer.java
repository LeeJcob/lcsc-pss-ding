package com.lcsc.ding.core.init;

import com.lcsc.ding.core.util.DingUtil;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class UserInfoInitializer {

    @PostConstruct
    private void init(){

        // 初始化加载用户信息
        DingUtil.initUserInfoMap();
    }
}
