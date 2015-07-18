package com.ctc.isweather.http;

import android.util.Log;

import com.ctc.isweather.mode.bean.DayWeather;
import com.ctc.isweather.mode.bean.WIndex;
import com.ctc.isweather.mode.bean.Weather;
import com.ctc.isweather.mode.bean.Wsimple;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by TPIAN on 15/7/16.
 */
public class WeatherHttp {

    public static Weather getWeather(String cityName){
        Weather mWeather = new Weather();


        mWeather.setCityname(cityName);

        //联网获取json

        String result = "";

        try {
            String url = "http://api.map.baidu.com/telematics/v3/weather";
            Log.d("Weather", "url " + url);
            result = HttpRequest.sendGet(url, "location=" + cityName + "&output=json&ak=256333037387158f732a3601de80cfb3");

            JSONObject obj = new JSONObject(result);

            int error = obj.getInt("error");

            //等于0代表没有错误
            if (error == 0) {
                JSONArray resultArr = obj.getJSONArray("results");
                JSONObject resultObj = (JSONObject) resultArr.get(0);
                String pm25 = resultObj.getString("pm25");
                mWeather.setPm25(pm25);

                JSONArray indexArr = resultObj.getJSONArray("index");

                JSONObject diobj = (JSONObject) indexArr.get(0);
                WIndex index1 = new WIndex();
                index1.setDes(diobj.getString("des"));
                index1.setTipt(diobj.getString("tipt"));
                index1.setTitle(diobj.getString("title"));
                index1.setZs(diobj.getString("zs"));

                JSONObject ciobj = (JSONObject) indexArr.get(1);
                WIndex index2 = new WIndex();
                index2.setDes(ciobj.getString("des"));
                index2.setTipt(ciobj.getString("tipt"));
                index2.setTitle(ciobj.getString("title"));
                index2.setZs(ciobj.getString("zs"));

                JSONObject tiobj = (JSONObject) indexArr.get(2);
                WIndex index3 = new WIndex();
                index3.setDes(tiobj.getString("des"));
                index3.setTipt(tiobj.getString("tipt"));
                index3.setTitle(tiobj.getString("title"));
                index3.setZs(tiobj.getString("zs"));

                JSONObject coldiobj = (JSONObject) indexArr.get(3);
                WIndex index4 = new WIndex();
                index4.setDes(coldiobj.getString("des"));
                index4.setTipt(coldiobj.getString("tipt"));
                index4.setTitle(coldiobj.getString("title"));
                index4.setZs(coldiobj.getString("zs"));

                JSONObject siobj = (JSONObject) indexArr.get(4);
                WIndex index5 = new WIndex();
                index5.setDes(siobj.getString("des"));
                index5.setTipt(siobj.getString("tipt"));
                index5.setTitle(siobj.getString("title"));
                index5.setZs(siobj.getString("zs"));

                mWeather.setCarwashIndex(index2);
                mWeather.setDressingIndex(index1);
                mWeather.setSportsIndex(index5);
                mWeather.setTravelIndex(index3);
                mWeather.setColdIndex(index4);

//                weather_data
                JSONArray dayArr = resultObj.getJSONArray("weather_data");

                JSONObject todayObj = (JSONObject) dayArr.get(0);
                JSONObject tomorrowObj = (JSONObject) dayArr.get(1);
                JSONObject afterObj = (JSONObject) dayArr.get(2);

                DayWeather day1 = new DayWeather();
                DayWeather day2 = new DayWeather();
                DayWeather day3 = new DayWeather();

                day1.setDate(todayObj.getString("date"));
                day1.setTemp(todayObj.getString("temperature"));
                day1.setTempRage(todayObj.getString("temperature"));
                day1.setTemp(todayObj.getString("date").split("：")[1].replaceAll("[^0-9?!\\.]", ""));
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

                mWeather.setTodayWeather(day1);
                mWeather.setTomorrowWeather(day2);
                mWeather.setAferWeather(day3);
                mWeather.setMaintemp(mWeather.getTodayWeather().getTemp());


            } else {
                mWeather.setMess("wrong");
            }

            String url2 = "http://wap.youhubst.com/weather/getweather.php?ID=101010100";

            String result2 = HttpRequest.sendGet(url2, "");

        } catch (Exception e) {
            System.err.println(e);
        }


        return mWeather;
    }

    public void log(String str) {
        Log.d("Weatherlog", str);
    }

}
