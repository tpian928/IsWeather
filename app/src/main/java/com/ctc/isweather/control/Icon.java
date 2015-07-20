package com.ctc.isweather.control;

import com.ctc.isweather.R;

/**
 * Created by TPIAN on 15/7/20.
 */
public class Icon {

    /**
     * 获取天气对应的图标
     * @param weatherDesc 天气的描述 比如晴
     * @return 如果存在则有类似 @drawable/sunny_day 这样的东西，不存在则返回null
     */
    public static int getWeatherIcon(String weatherDesc){
        int icon = 0;

        if (weatherDesc.equals("晴")){
            icon = R.drawable.sunny_day;
        }
        else if (weatherDesc.indexOf("阴")>0||weatherDesc.indexOf("云")>0){
            icon = R.drawable.cloudy_day;
        }
        else if (weatherDesc.equals("夜晚多云")){
            icon = R.drawable.cloudy_night;
        }
        else if (weatherDesc.equals("雾霾")||weatherDesc.equals("雾")||weatherDesc.equals("霾")||weatherDesc.equals("haze")){
            icon = R.drawable.haze;
        }
        else if (weatherDesc.indexOf("雪")>0){
            icon = R.drawable.snow;
        }
        else if (weatherDesc.equals("大雨")){
            icon = R.drawable.pour;
        }
        else if (weatherDesc.indexOf("雨")>0){
            icon = R.drawable.rain;
        }
        else if (weatherDesc.equals("夜晚")||weatherDesc.equals("晴朗的夜晚")){
            icon = R.drawable.sunny_night;
        }
        else if (weatherDesc.equals("暴雨")||weatherDesc.equals("雷阵雨")){
            icon = R.drawable.thunder_rain;
        }

        return icon;
    }
}
