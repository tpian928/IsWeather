package com.ctc.isweather.mode.bean;

/**
 * Created by TPIAN on 15/7/18.
 */
public class FutureWeather {

    private String tempmin;
    private String tempmax;
    private String weather;
    private String wind;

    public FutureWeather(){

    }

    public FutureWeather(String tempmin, String tempmax, String weather, String wind) {
        this.tempmin = tempmin;
        this.tempmax = tempmax;
        this.weather = weather;
        this.wind = wind;
    }

    public String getMaxTemp() {
        return tempmin;
    }

    public void setTempmin(String tempmin) {
        this.tempmin = tempmin;
    }

    public String getMinTemp() {
        return tempmax;
    }

    public void setTempmax(String tempmax) {
        this.tempmax = tempmax;
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
}
