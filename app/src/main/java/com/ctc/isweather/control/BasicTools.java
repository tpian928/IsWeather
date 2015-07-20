package com.ctc.isweather.control;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by chris on 2015/7/19.
 */
public class BasicTools {

    /**
     * Get the current date
     * @return Date
     */
    public static String getDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        return formatter.format(curDate);
    }

    /**
     * get the day of the week
     * @param date Date
     * @return week day
     */
    public static String getWeekDay(String date) {
        String[] dates = date.split("/");
        Calendar calendar = Calendar.getInstance();//获得一个日历
        calendar.set(Integer.valueOf(dates[0]), Integer.valueOf(dates[1]) - 1, Integer.valueOf(dates[2]));//设置当前时间,月份是从0月开始计算
        int number = calendar.get(Calendar.DAY_OF_WEEK);//星期表示1-7，是从星期日开始，
        String[] str = {"", "Sun", "Mon", "Thus", "Wens", "Thur", "Fri", "Sat"};
        //System.out.println(str[number]);
        return str[number];
    }
}
