package com.ctc.isweather.mode.bean;

/**
 * Created by TPIAN on 15/7/19.
 * 3 hours a class
 */
public class HourWeather {

    private String weather;
    private String temp1;
    private String temp2;
    private String startHour;
    private String endHour;
    private String date; //normally date like 20150719
    private String sfdate; //other format date and hour like 20150719080000
    private String efdate; //other format date and hour like 20150719080000

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getTemp1() {
        return temp1;
    }

    public void setTemp1(String temp1) {
        this.temp1 = temp1;
    }

    public String getTemp2() {
        return temp2;
    }

    public void setTemp2(String temp2) {
        this.temp2 = temp2;
    }

    public String getStartHour() {
        return startHour;
    }

    public void setStartHour(String startHour) {
        this.startHour = startHour;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEndHour() {
        return endHour;
    }

    public void setEndHour(String endHour) {
        this.endHour = endHour;
    }

    public String getSfdate() {
        return sfdate;
    }

    public void setSfdate(String sfdate) {
        this.sfdate = sfdate;
    }

    public String getEfdate() {
        return efdate;
    }

    public void setEfdate(String efdate) {
        this.efdate = efdate;
    }
}
