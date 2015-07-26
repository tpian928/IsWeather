package com.ctc.isweather.http;

import android.util.Log;

import com.ctc.isweather.R;
import com.ctc.isweather.control.LocationCtrl;
import com.ctc.isweather.mode.bean.BadWeather;
import com.ctc.isweather.mode.bean.DayWeather;
import com.ctc.isweather.mode.bean.FutureWeather;
import com.ctc.isweather.mode.bean.HourWeather;
import com.ctc.isweather.mode.bean.WIndex;
import com.ctc.isweather.mode.bean.Weather;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by TPIAN on 15/7/16.
 */
public class WeatherHttp {

    public static Weather getWeather(String cityName) throws UnsupportedEncodingException {
        Weather mWeather = new Weather();

        mWeather.setCityname(cityName);
        String newcityName = URLEncoder.encode(cityName,"utf-8");

        //联网获取json

        String result = "";

        try {
            String url = "http://api.map.baidu.com/telematics/v3/weather";
            Log.d("Weather", "url " + url);
            result = HttpRequest.sendGet(url, "location=" + newcityName + "&output=json&ak=256333037387158f732a3601de80cfb3");

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

    /**
     * getFutureWeather get futureWeather(class) with cityid
     * @param cityId cityid
     * @return futureWeather(class)
     * @Warnning: You should check the size of return Arraylist,beacuse it may be empty and this method cannot be used in main thread
     */
    public static ArrayList<FutureWeather> getFutureWeather(int cityId){
        //Log.i("chris","weather : " + cityId);
        ArrayList<FutureWeather> futureWeathers = new ArrayList<FutureWeather>();
        try {

            URL hukd = new URL("http://wap.youhubst.com/weather/getweather.php?ID="+cityId);
            URLConnection tc = hukd.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(tc.getInputStream(), "UTF-8"));
            String line = in.readLine();
            Log.d("f","line is "+line);
            JSONObject obj2 = new JSONObject(line);
            JSONObject infoObj = obj2.getJSONObject("weatherinfo");
            Log.d("test02",infoObj.toString());

            String temp1 = infoObj.getString("temp1");
            String weather1 = infoObj.getString("weather1");
            String wind1 = infoObj.getString("wind1");
            String tempmin1 = temp1.split("~")[0].replaceAll("[^0-9?!\\.]", "");
            String tempmax1 = temp1.split("~")[1].replaceAll("[^0-9?!\\.]", "");
            FutureWeather f1 = new FutureWeather(tempmin1,tempmax1,weather1,wind1);

            String temp2 = infoObj.getString("temp2");
            String weather2 = infoObj.getString("weather2");
            String wind2 = infoObj.getString("wind2");
            String tempmin2 = temp2.split("~")[0].replaceAll("[^0-9?!\\.]", "");
            String tempmax2 = temp2.split("~")[1].replaceAll("[^0-9?!\\.]", "");
            FutureWeather f2 = new FutureWeather(tempmin2,tempmax2,weather2,wind2);

            String temp3 = infoObj.getString("temp3");
            String weather3 = infoObj.getString("weather3");
            String wind3 = infoObj.getString("wind3");
            String tempmin3 = temp3.split("~")[0].replaceAll("[^0-9?!\\.]", "");
            String tempmax3 = temp3.split("~")[1].replaceAll("[^0-9?!\\.]", "");
            FutureWeather f3 = new FutureWeather(tempmin3,tempmax3,weather3,wind3);

            String temp4 = infoObj.getString("temp4");
            String weather4 = infoObj.getString("weather4");
            String wind4 = infoObj.getString("wind1");
            String tempmin4 = temp4.split("~")[0].replaceAll("[^0-9?!\\.]", "");
            String tempmax4 = temp4.split("~")[1].replaceAll("[^0-9?!\\.]", "");
            FutureWeather f4 = new FutureWeather(tempmin4,tempmax4,weather4,wind4);

            String temp5 = infoObj.getString("temp1");
            String weather5 = infoObj.getString("weather1");
            String wind5 = infoObj.getString("wind4");
            String tempmin5 = temp5.split("~")[0].replaceAll("[^0-9?!\\.]", "");
            String tempmax5 = temp5.split("~")[1].replaceAll("[^0-9?!\\.]", "");
            FutureWeather f5 = new FutureWeather(tempmin5,tempmax5,weather5,wind5);

            String temp6 = infoObj.getString("temp5");
            String weather6 = infoObj.getString("weather5");
            String wind6 = infoObj.getString("wind5");
            String tempmin6 = temp5.split("~")[0].replaceAll("[^0-9?!\\.]", "");
            String tempmax6 = temp5.split("~")[1].replaceAll("[^0-9?!\\.]", "");
            FutureWeather f6 = new FutureWeather(tempmin6,tempmax6,weather6,wind6);

            futureWeathers.add(f1);
            futureWeathers.add(f2);
            futureWeathers.add(f3);
            futureWeathers.add(f4);
            futureWeathers.add(f5);
            futureWeathers.add(f6);
            Log.d("test02","futureWeathers size is "+futureWeathers.size());

        } catch (Exception e) {
            System.err.println(e);
        }
        return futureWeathers;
    }

    /**
     *每3小时候一次
     * @param cityName
     * @return get every 3 hour  weather
     */
    public static ArrayList<HourWeather> getHourWeather(String cityName) {
        ArrayList<HourWeather> hourWeathers = new ArrayList<HourWeather>();

        try {
            cityName = URLEncoder.encode(cityName,"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        try {
            URL hukd = new URL("http://v.juhe.cn/weather/forecast3h.php?cityname="+cityName+"&key=d6ac5a3b6054da94df74c0157d65fff8");
            URLConnection tc = hukd.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(tc.getInputStream(), "UTF-8"));
            String line = in.readLine();

            JSONObject obj = new JSONObject(line);

            if (obj.getInt("resultcode")==200){
                JSONArray arr = obj.getJSONArray("result");

                for (int i =0;i<arr.length();i++){
                    HourWeather tmpHourWeather = new HourWeather();
                    JSONObject tmpObj = (JSONObject)arr.get(i);
                    tmpHourWeather.setWeather(tmpObj.getString("weather"));
                    tmpHourWeather.setDate(tmpObj.getString("date"));
                    tmpHourWeather.setEfdate(tmpObj.getString("efdate"));
                    tmpHourWeather.setEndHour(tmpObj.getString("eh"));
                    tmpHourWeather.setSfdate(tmpObj.getString("sfdate"));
                    tmpHourWeather.setStartHour(tmpObj.getString("sh"));
                    tmpHourWeather.setTemp1(tmpObj.getString("temp1"));
                    tmpHourWeather.setTemp2(tmpObj.getString("temp2"));
                    hourWeathers.add(tmpHourWeather);
                }
            }
            else{
                hourWeathers=null;
                System.err.println("bad json");
            }

        }
        catch (Exception e){
            hourWeathers=null;
            System.err.println("getDayWeather "+e);
        }

        return  hourWeathers;
    }

    public void log(String str) {
        Log.d("Weatherlog", str);
    }

    /**
     * 判断用户当前所在城市在未来3个小时有没有恶劣天气
     * @return 如果存在恶劣天气则返回恶劣天气，不存在就返回空
     */
    public static BadWeather getBadWeather() throws UnsupportedEncodingException {
        BadWeather badWeather = new BadWeather();

        String nowhour = new SimpleDateFormat("HH").format(Calendar.getInstance().getTime());
        int warnHour = Integer.parseInt(nowhour)+3;
        Log.d("badweather",nowhour);
        ArrayList<HourWeather> hourWeathers = new ArrayList<HourWeather>();
        if (LocationCtrl.getCityName()!=null){
            hourWeathers = WeatherHttp.getHourWeather(LocationCtrl.getCityName());
            if (hourWeathers.size()>5){
                if (warnHour>=5&&warnHour<8){
                    badWeather = getBadWeatherFromHour(hourWeathers.get(0));
                }
                else if (warnHour>=8&&warnHour<11){
                    badWeather = getBadWeatherFromHour(hourWeathers.get(1));
                }
                else if (warnHour>=11&&warnHour<14){
                    badWeather = getBadWeatherFromHour(hourWeathers.get(2));
                }
                else if (warnHour>=14&&warnHour<17){
                    badWeather = getBadWeatherFromHour(hourWeathers.get(3));
                }
                else if (warnHour>=17&&warnHour<20){
                    badWeather = getBadWeatherFromHour(hourWeathers.get(4));
                }
                else if (warnHour>=20&&warnHour<23){
                    badWeather = getBadWeatherFromHour(hourWeathers.get(5));
                }
            }
            else {
                Log.d("badweather","getHourWeather Exception!");
            }
        }

        return  badWeather;
    }

    //
    public static BadWeather getBadWeatherFromHour(HourWeather hourWeather){
        BadWeather badWeather = new BadWeather();
        String weatherDesc = hourWeather.getWeather();

        if (weatherDesc.equals("雾霾")||weatherDesc.equals("雾")||weatherDesc.equals("霾")||weatherDesc.equals("haze")){
            badWeather.setInfo("雾霾");
        }
        else if (weatherDesc.indexOf("雪")>0){
            badWeather.setInfo("雪");
        }
        else if (weatherDesc.equals("大雨")){
            badWeather.setInfo("大雨");
        }
        else if (weatherDesc.indexOf("雨")>0){
            badWeather.setInfo("雨");
        }
        else if (weatherDesc.equals("暴雨")||weatherDesc.equals("雷阵雨")){
            badWeather.setInfo("暴雨");
        }
        else if (Integer.parseInt(hourWeather.getTemp2().replaceAll("[^0-9?!\\.]", ""))>35){
            badWeather.setInfo("高温");
        }
        else {
            badWeather=null;
        }
        return badWeather;
    }

}
