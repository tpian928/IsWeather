package com.ctc.isweather.mode.bean;

/**
 * Created by TPIAN on 15/7/13.
 */
public class DayWeather {

    /**
     * date 日期
     * temp 温度
     * weather
     * wind 风向
     * tempRage 温度范围
     */
    private String date,temp,weather,wind,tempRage;

    public DayWeather(){}

    public DayWeather(String date,String temp,String weather,String wind,String tempRage){
        setDate(date);
        setTemp(temp);
        setWeather(weather);
        setWind(wind);
        setTempRage(tempRage);
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getWind() {
        return wind;
    }

    public void setWind(String wind) {
        this.wind = wind;
    }

    public String getTempRage() {
        return tempRage;
    }

    public void setTempRage(String tempRage) {
        this.tempRage = tempRage;
    }
}
