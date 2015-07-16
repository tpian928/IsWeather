package com.ctc.isweather.tools;

/**
 * Created by Saty on 15/7/14.
 */
public class Color {
    public static String backgroundClr = "#1c2534";//背景色
    public static String DayForeColor = "#00e1ff";//白天前景色（天气概况模块背景色），折线颜色
    public static String DayGridColor = "#067786";//白天未来天气模块主色调（右侧颜色）
    public static String DayGridColorLight = "0aa5ba";//白天未来天气模块主色调（左侧颜色）
    public static String NightForeColor = "#3f51b5";//白天前景色（天气概况模块背景色），折线颜色
    public static String NightGridColor = "#5677fc";//白天未来天气模块主色调（右侧颜色）
    public static String NightGridColorLight = "8fa5fc";//白天未来天气模块主色调（左侧颜色）

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
