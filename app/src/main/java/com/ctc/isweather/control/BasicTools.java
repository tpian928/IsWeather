package com.ctc.isweather.control;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by chris on 2015/7/19.
 */
public class BasicTools {

    public static String[] weekdays = {"Mon", "Thus", "Wens", "Thur", "Fri", "Sat", "Sun"};

    /**
     * Get the current date
     *
     * @return Date
     */
    public static String getDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        return formatter.format(curDate);
    }

    /**
     * get the day of the week
     *
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

    /**
     * Get the week day of the current date
     *
     * @param date
     * @return
     */
    public static String[] getWholeWeekdays(String date) {
        String weekday = getWeekDay(date);
        int index = -1;
        for (int i = 0; i < weekdays.length; i++) {
            if (weekday.compareTo(weekdays[i]) == 0) {
                index = i;
                break;
            }
        }
        // get the index of the weekday
        String[] days = new String[6];
        days[0] = weekday;
        for (int i = 1; i < 6; i++) {
            days[i] = weekdays[(index+i) % weekdays.length];
        }

        return days;
    }

    public static String[] getDates(String todayDate){
        String[] threeDays = new String[3];
        SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
        Date nowDate = null;
        try {
            nowDate = df.parse(todayDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        threeDays[0] = todayDate;

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date tomorrow = new Date(nowDate.getTime() + 1 * 24 * 60 * 60 * 1000);
        threeDays[1] = simpleDateFormat.format(tomorrow);

        Date after = new Date(nowDate.getTime() + 2 * 24 * 60 * 60 * 1000);
        threeDays[2] = simpleDateFormat.format(after);
        return threeDays;
    }

    public static String getSimpleDate(String date){
        String[] dates = date.split("/");
        return dates[1] + " -" + dates[2];
    }

}
