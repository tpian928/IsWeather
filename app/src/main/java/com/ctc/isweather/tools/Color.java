package com.ctc.isweather.tools;

/**
 * Created by Saty on 15/7/14.
 */
public class Color {

    /**
     * 通过给出的天气度数得到对应的色卡元素
     * @param temp int类型的温度
     * @return 16位颜色
     */
    public static String getColorByTemp(int temp){
        String color = "#ffffff";
        //判断温度区间，返回颜色
        if(temp<-5) {
            color = "#3f51b5";
        } else if (temp<0){
            color = "#03a9f4";
        } else if (temp<5){
            color = "#00bcd4";
        } else if (temp<10){
            color = "#009688";
        } else if (temp<15){
            color = "#4caf50";
        } else if (temp<20){
            color = "#8bc34a";
        } else if (temp<25){
            color = "#cddc39";
        } else if (temp<30){
            color = "#ffeb3b";
        } else if (temp<35){
            color = "#ffc107";
        } else if (temp<40){
            color = "#ff9800";
        } else {
            color = "#ff5722";
        }
        return  color;
    }

}
