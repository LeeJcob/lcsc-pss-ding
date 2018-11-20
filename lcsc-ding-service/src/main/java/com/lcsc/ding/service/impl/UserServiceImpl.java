package com.lcsc.ding.service.impl;

import com.lcsc.ding.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public void test() {
        System.out.print("hello");
    }
}
