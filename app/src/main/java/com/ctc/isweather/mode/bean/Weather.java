package com.ctc.isweather.mode.bean;

import android.util.Log;

import com.ctc.isweather.http.HttpRequest;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by TPIAN on 15/7/13.
 */
public class Weather {

    /**
     * 基本的数据 sd是湿度 基本信息显示temp,mess用来输出一些错误信息
     */
    private String cityid;
    private String cityname;
    private String pm25;
    private String sd;//湿度
    private String maintemp; //主要温度
    private String mess;
    private String fl,wd;
    private String date;

    public Weather(){

    }

    /**
     * 指数
     */
    private WIndex dressingIndex, carwashIndex, travelIndex, coldIndex, sportsIndex;

    /**
     * 今天明天，后天的具体天气
     */
    private DayWeather todayWeather, tomorrowWeather, aferWeather;

    /**
     * 得到一周温度的数组
     *
     * @return 一周温度的数组
     */
    public ArrayList<String> getTempInWeek() {
        ArrayList<String> tempArr = new ArrayList<String>();

        //从数据库中得到城市id
        this.cityid = "101010100";
        //this.cityid = getCityIdFromDB(cityname);-----need

        try {
            String url = "http://wap.youhubst.com/weather/getweather.php?ID=" + this.cityid;
            log(url);
            String result1 = HttpRequest.sendGet(url, "");

            JSONObject obj = new JSONObject(result1);

            log("objzz " + obj.getString("weatherinfo"));
        } catch (Exception e) {
            System.err.println(e);
        }

        return tempArr;
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

    public String getMess() {
        return mess;
    }

    public void setMess(String mess) {
        this.mess = mess;
    }

    public String getCityid() {
        return cityid;
    }

    public void setCityid(String cityid) {
        this.cityid = cityid;
    }

    public String getMaintemp() {
        return maintemp;
    }

    public void setMaintemp(String maintemp) {
        this.maintemp = maintemp;
    }

    private void log(String str) {
        Log.d("Weatherlog", str);
    }

    public void setFl(String fl){
        this.fl = fl;
    }

    public String getFl(){
        return fl;
    }

    public void setWd(String wd){
        this.wd = wd;
    }

    public String getWd(){
        return wd;
    }

    public void setDate(String date){
        this.date = date;
    }

    public String getDate(){
        return date;
    }
}
