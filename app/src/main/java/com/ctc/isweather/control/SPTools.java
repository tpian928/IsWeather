package com.ctc.isweather.control;

import android.app.Activity;
import android.content.SharedPreferences;

import com.ctc.isweather.mode.bean.DayWeather;
import com.ctc.isweather.mode.bean.WIndex;
import com.ctc.isweather.mode.bean.Weather;
import com.ctc.isweather.mode.bean.Wsimple;

/**
 * Created by chris on 2015/7/17.
 */
public class SPTools {
    /**
     * Get the information of weather and record them in the sharepreferences
     * Set the sharepreferences whenever we get the new information from the internet.
     *
     * @param weather  The Weather Class, including many information
     * @param activity To get the acticity
     */
    public static void setSharePreferences(Weather weather, Activity activity) {
        SharedPreferences sp = activity.getSharedPreferences("weatherInfo", 0);
        SharedPreferences.Editor editor = sp.edit();
        // Today's weather
        editor.putString("cityname", weather.getCityname());
        editor.putString("cityid", weather.getCityid());
        editor.putString("pm2.5", weather.getPm25());
        editor.putString("sd", weather.getSd());
        editor.putString("maintemp", weather.getMaintemp());

        // Today's index
        editor.putString("dressingTitle", weather.getDressingIndex().getTitle());
        editor.putString("dressingZs", weather.getDressingIndex().getZs());
        editor.putString("dressingTipt", weather.getDressingIndex().getTipt());
        editor.putString("dressingDes", weather.getDressingIndex().getDes());

        editor.putString("carwashTitle", weather.getCarwashIndex().getTitle());
        editor.putString("carwashZs", weather.getCarwashIndex().getZs());
        editor.putString("carwashTipt", weather.getCarwashIndex().getTipt());
        editor.putString("carwashDes", weather.getCarwashIndex().getDes());

        editor.putString("travelTitle", weather.getTravelIndex().getTitle());
        editor.putString("travelZs", weather.getTravelIndex().getZs());
        editor.putString("travelTipt", weather.getTravelIndex().getTipt());
        editor.putString("travelDes", weather.getTravelIndex().getDes());

        editor.putString("coldTitle", weather.getColdIndex().getTitle());
        editor.putString("coldZs", weather.getColdIndex().getZs());
        editor.putString("coldTipt", weather.getColdIndex().getTipt());
        editor.putString("coldDes", weather.getColdIndex().getDes());

        editor.putString("sportsTitle", weather.getSportsIndex().getTitle());
        editor.putString("sportsZs", weather.getSportsIndex().getZs());
        editor.putString("sportsTipt", weather.getSportsIndex().getTipt());
        editor.putString("sportsDes", weather.getSportsIndex().getDes());

        //  future three days' weather
        editor.putString("todayTempRage", weather.getTodayWeather().getTempRage());
        editor.putString("todayWeather", weather.getTodayWeather().getWeather());
        editor.putString("todayWind", weather.getTodayWeather().getWind());
        editor.putString("todayDate", weather.getTodayWeather().getDate());
        editor.putString("todayTemp", weather.getTodayWeather().getTemp());

        editor.putString("tomorrowTempRage", weather.getTomorrowWeather().getTempRage());
        editor.putString("tomorrowWeather", weather.getTomorrowWeather().getWeather());
        editor.putString("tomorrowWind", weather.getTomorrowWeather().getWind());
        editor.putString("tomorrowDate", weather.getTomorrowWeather().getDate());
        editor.putString("tomorrowTemp", weather.getTomorrowWeather().getTemp());

        editor.putString("afterTempRage", weather.getAferWeather().getTempRage());
        editor.putString("afterWeather", weather.getAferWeather().getWeather());
        editor.putString("afterWind", weather.getAferWeather().getWind());
        editor.putString("afterDate", weather.getAferWeather().getDate());
        editor.putString("afterTemp", weather.getAferWeather().getTemp());
        editor.commit();
    }

    /**
     * Unfinished!!!!!
     * @param activity
     * @return
     */
    public static Weather getShareprefence(Activity activity) {
        SharedPreferences sp = activity.getSharedPreferences("weatherInfo", 0);

        // construction of Wsimple
        String cityname = sp.getString("cityname", "cityname");
        String cityid = sp.getString("cityid", "cityid");
        String pm25 = sp.getString("pm2.5", "pm2.5");
        String sd = sp.getString("sd", "sd");
        String maintemp = sp.getString("maintemp", "maintemp");
        Wsimple wsimple = new Wsimple(cityname,cityid,pm25,sd,maintemp);

        // Construction of indexes.
        WIndex dressingIndex = new WIndex(
                sp.getString("dressingTitle", "dressing"),
                sp.getString("dressingZs", "zs"),
                sp.getString("dressingTipt", "tipt"),
                sp.getString("dressingDes", "des")
        );

        WIndex travelIndex = new WIndex(
                sp.getString("travelTitle", "dressing"),
                sp.getString("travelZs", "zs"),
                sp.getString("travelTipt", "tipt"),
                sp.getString("travelDes", "des")
        );

        WIndex sportIndex = new WIndex(
                sp.getString("sportTitle", "dressing"),
                sp.getString("sportZs", "zs"),
                sp.getString("sportTipt", "tipt"),
                sp.getString("sportDes", "des")
        );

        WIndex cardwashIndex = new WIndex(
                sp.getString("carwashTitle", "dressing"),
                sp.getString("carwashZs", "zs"),
                sp.getString("carwashTipt", "tipt"),
                sp.getString("carwashDes", "des")
        );

        WIndex coldIndex = new WIndex(sp.getString("coldTitle", "dressing"),
                sp.getString("coldZs", "zs"),
                sp.getString("coldTipt", "tipt"),
                sp.getString("coldDes", "des")
        );

        // construction of the weather of future three days
        DayWeather today = new DayWeather(
                sp.getString("todayDate", "todayDate"),
                sp.getString("todayTemp", "todayTemp"),
                sp.getString("todayWeather", "todayWeather"),
                sp.getString("todayWind", "todayWind"),
                sp.getString("todayTempRage", "todayTempRage")
        );

        DayWeather tomorrow = new DayWeather(
                sp.getString("tomorrowDate", "todayDate"),
                sp.getString("tomorrowTemp", "todayTemp"),
                sp.getString("tomorrowWeather", "todayWeather"),
                sp.getString("tomorrowWind", "todayWind"),
                sp.getString("tomorrowTempRage", "todayTempRage")
        );

        DayWeather after = new DayWeather(
                sp.getString("afterDate", "afterDate"),
                sp.getString("afterTemp", "afterTemp"),
                sp.getString("afterWeather", "afterWeather"),
                sp.getString("afterWind", "afterWind"),
                sp.getString("afterTempRage", "afterTempRage")
        );

         //construction of the weather
        return new Weather(wsimple,dressingIndex,cardwashIndex,travelIndex,coldIndex,sportIndex,today,tomorrow,after);
    }


    /**
     * Get the certain information from the sharepreferences.
     * @param variable element name
     * @param activity
     * @return
     */
    public static String getSpName(String variable,Activity activity){
        SharedPreferences sp = activity.getSharedPreferences("weatherInfo",0);
        return sp.getString(variable,null);
    }
}
