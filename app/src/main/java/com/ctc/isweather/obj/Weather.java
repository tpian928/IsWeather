package com.ctc.isweather.obj;

/**
 * Created by TPIAN on 15/7/13.
 */
public class Weather {

    /**
     * 基本的数据 sd是湿度 基本信息显示temp
     */
    private String cityname,pm25,sd;

    /**
     * 指数
     */
    private WIndex dressingIndex,carwashIndex,travelIndex,coldIndex,sportsIndex;

    /**
     *今天明天，后天的具体天气
     */
    private DayWeather todayWeather,tomorrowWeather,aferWeather;

    /**
     * 通过城市名称进行构造，构造的同时获得所有信息
     * @param cityname
     */
    public Weather(String cityname) {
        this.cityname = cityname;

        

    }

    /**
     * get和set
     */
    public String getCityname() {
        return cityname;
    }

    public void setCityname(String cityname) {
        this.cityname = cityname;
    }

    public String getPm25() {
        return pm25;
    }

    public void setPm25(String pm25) {
        this.pm25 = pm25;
    }

    public String getSd() {
        return sd;
    }

    public void setSd(String sd) {
        this.sd = sd;
    }

    public WIndex getCarwashIndex() {
        return carwashIndex;
    }

    public void setCarwashIndex(WIndex carwashIndex) {
        this.carwashIndex = carwashIndex;
    }

    public WIndex getDressingIndex() {
        return dressingIndex;
    }

    public void setDressingIndex(WIndex dressingIndex) {
        this.dressingIndex = dressingIndex;
    }

    public WIndex getTravelIndex() {
        return travelIndex;
    }

    public void setTravelIndex(WIndex travelIndex) {
        this.travelIndex = travelIndex;
    }

    public WIndex getColdIndex() {
        return coldIndex;
    }

    public void setColdIndex(WIndex coldIndex) {
        this.coldIndex = coldIndex;
    }

    public WIndex getSportsIndex() {
        return sportsIndex;
    }

    public void setSportsIndex(WIndex sportsIndex) {
        this.sportsIndex = sportsIndex;
    }

    public DayWeather getTodayWeather() {
        return todayWeather;
    }

    public void setTodayWeather(DayWeather todayWeather) {
        this.todayWeather = todayWeather;
    }

    public DayWeather getTomorrowWeather() {
        return tomorrowWeather;
    }

    public void setTomorrowWeather(DayWeather tomorrowWeather) {
        this.tomorrowWeather = tomorrowWeather;
    }

    public DayWeather getAferWeather() {
        return aferWeather;
    }

    public void setAferWeather(DayWeather aferWeather) {
        this.aferWeather = aferWeather;
    }
}
