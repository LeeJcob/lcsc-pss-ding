package com.lcsc.ding.core.util;

import com.lcsc.ding.core.constant.Constant;
import org.apache.commons.io.FileUtils;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 节假日判断
 */
public class HolidayUtil {

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private List<String> holidays = new ArrayList<String>();
    private List<String> workdays = new ArrayList<String>();

    /**
     * 判断当天是否是工作日 (工作日：true；节假日：false)
     *
     * @return
     */
    public boolean isWorkDay(Date dateTime) {

        if (null == dateTime) {

            dateTime = new Date();
        }

        boolean flag = true;

        initConfig();

        int dateType = getDateType(dateTime);

        //如果excel不存在当前日期。判断是否周六日
        if (dateType == 0) {

            Calendar c = Calendar.getInstance();
            c.setTime(dateTime);
            if (c.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY ||
                    c.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
                flag = false;
            }

        } else {//如果存在当前日期，根据返回的类型判断

            if (dateType == 1) {//节假日
                flag = false;
            } else if (dateType == 2) {//工作日
                flag = true;
            }

        }
        return flag;
    }

    /**
     * 根据判断当前时间是否是节假日还是工作日  (excel中不存在当前日期：0；节假日：1；工作日：2)
     * 如果当前日期在excel中的节假日和工作日都写了，默认的工作日
     *
     * @return
     */
    private int getDateType(Date dateTime) {
        int type = 0;

        String today = sdf.format(dateTime);

        if (holidays.size() > 0) {
            for (String holiday : holidays) {
                if (holiday.equals(today)) {
                    type = 1;
                    break;
                }
            }
        }

        if (workdays.size() > 0) {
            for (String workday : workdays) {
                if (workday.equals(today)) {
                    type = 2;
                }
            }
        }

        return type;
    }


    /**
     * 读取excel中的节假日和工作日
     */
    private void initConfig() {

        try {

            String filePath = ResourceUtils.getURL("classpath:").getPath();

            Integer lastIndex = filePath.lastIndexOf("/lcsc-ding-web");

            File holidayFile = new File(filePath.substring(0, lastIndex) + Constant.FILE_HOLIDAY_EXCEL_PATH);

            if (holidayFile.exists()) {

                holidays = FileUtils.readLines(holidayFile, "utf-8");
            }

            File workdayFile = new File(filePath.substring(0, lastIndex) + Constant.FILE_WORKDAY_EXCEL_PATH);

            if (workdayFile.exists()) {

                workdays = FileUtils.readLines(workdayFile, "utf-8");
            }

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}
