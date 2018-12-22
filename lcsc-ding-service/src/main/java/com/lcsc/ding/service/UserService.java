package com.lcsc.ding.service;

public interface UserService {
    public void test();

    /**
     * 根据用户id获取考勤记录
     * @param userId
     */
    public void getAttendanceByUserId(String userId);
}
