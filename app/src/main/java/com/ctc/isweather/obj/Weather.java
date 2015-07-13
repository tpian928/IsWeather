package com.ctc.isweather.obj;

import android.util.Log;

import com.ctc.isweather.http.HttpRequest;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by TPIAN on 15/7/13.
 */
public class Weather {

    /**
     * 基本的数据 sd是湿度 基本信息显示temp,mess用来输出一些错误信息
     */
    private String cityname,pm25,sd,mess;

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

        //联网获取json

        String result = "";

        try{
            String url = "http://api.map.baidu.com/telematics/v3/weather";
            Log.d("Weather","url "+url);
            result = HttpRequest.sendGet(url, "location="+cityname+"&output=json&ak=256333037387158f732a3601de80cfb3");

            //Log.d("Weather","json result is "+result);

            JSONObject obj = new JSONObject(result);

            int error = obj.getInt("error");


            //等于0代表没有错误
            if(error==0){
                JSONArray resultArr = obj.getJSONArray("results");
                JSONObject resultObj = (JSONObject)resultArr.get(0);
                String pm25 = resultObj.getString("pm25");
                this.setPm25(pm25);

                JSONArray indexArr = resultObj.getJSONArray("index");

                JSONObject diobj = (JSONObject)indexArr.get(0);
                WIndex index1 = new WIndex();
                index1.setDes(diobj.getString("des"));
                index1.setTipt(diobj.getString("tipt"));
                index1.setTitle(diobj.getString("title"));
                index1.setZs(diobj.getString("zs"));

                JSONObject ciobj = (JSONObject)indexArr.get(1);
                WIndex index2 = new WIndex();
                index2.setDes(ciobj.getString("des"));
                index2.setTipt(ciobj.getString("tipt"));
                index2.setTitle(ciobj.getString("title"));
                index2.setZs(ciobj.getString("zs"));

                JSONObject tiobj = (JSONObject)indexArr.get(2);
                WIndex index3 = new WIndex();
                index3.setDes(tiobj.getString("des"));
                index3.setTipt(tiobj.getString("tipt"));
                index3.setTitle(tiobj.getString("title"));
                index3.setZs(tiobj.getString("zs"));

                JSONObject coldiobj = (JSONObject)indexArr.get(3);
                WIndex index4 = new WIndex();
                index4.setDes(coldiobj.getString("des"));
                index4.setTipt(coldiobj.getString("tipt"));
                index4.setTitle(coldiobj.getString("title"));
                index4.setZs(coldiobj.getString("zs"));

                JSONObject siobj = (JSONObject)indexArr.get(4);
                WIndex index5 = new WIndex();
                index5.setDes(siobj.getString("des"));
                index5.setTipt(siobj.getString("tipt"));
                index5.setTitle(siobj.getString("title"));
                index5.setZs(siobj.getString("zs"));

                this.setCarwashIndex(index2);
                this.setDressingIndex(index1);
                this.setSportsIndex(index5);
                this.setTravelIndex(index3);
                this.setColdIndex(index4);

//                weather_data
                JSONArray dayArr = resultObj.getJSONArray("weather_data");

                JSONObject todayObj = (JSONObject)dayArr.get(0);
                JSONObject tomorrowObj = (JSONObject)dayArr.get(1);
                JSONObject afterObj = (JSONObject)dayArr.get(2);

                DayWeather day1= new DayWeather();
                DayWeather day2= new DayWeather();
                DayWeather day3= new DayWeather();

                day1.setDate(todayObj.getString("date"));
                day1.setTemp(todayObj.getString("temperature"));
                day1.setTempRage(todayObj.getString("temperature"));
                day1.setWeather(todayObj.getString("weather"));
                day1.setWind(todayObj.getString("wind"));

                day2.setDate(tomorrowObj.getString("date"));
                day2.setTemp(tomorrowObj.getString("temperature"));
                day2.setTempRage(tomorrowObj.getString("temperature"));
                day2.setWeather(tomorrowObj.getString("weather"));
                day2.setWind(tomorrowObj.getString("wind"));

                day3.setDate(afterObj.getString("date"));
                day3.setTemp(afterObj.getString("temperature"));
                day3.setTempRage(afterObj.getString("temperature"));
                day3.setWeather(afterObj.getString("weather"));
                day3.setWind(afterObj.getString("wind"));

                this.setTodayWeather(day1);
                this.setTomorrowWeather(day2);
                this.setAferWeather(day3);

                //log(this.getAferWeather().getTempRage());
            }

            else{
                log("wrong");
                this.setMess("wrong");
            }


        }
        catch (Exception e) {
            System.err.println(e);
        }


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

    private void log(String str){
        Log.d("Weatherlog",str);
    }
}
