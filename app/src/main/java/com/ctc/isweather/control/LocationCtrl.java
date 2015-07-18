package com.ctc.isweather.control;

import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by TPIAN on 15/7/16.
 */
public class LocationCtrl {

    /**
     * getCityName by IP address
     * @return if get the cityName will return the cityName,else return null
     */
    public static String getCityName(){
        String cityName = null;

        try {

            URL hukd = new URL("http://api.map.baidu.com/location/ip?ak=256333037387158f732a3601de80cfb3");
            URLConnection tc = hukd.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(tc.getInputStream(), "UTF-8"));
            String line = in.readLine();
            Log.d("test",line);
            JSONObject obj = new JSONObject(line);


            if (obj.getInt("status")==0){

                JSONObject contentObj = obj.getJSONObject("content");
                JSONObject address_detailObj = contentObj.getJSONObject("address_detail");
                if (address_detailObj.getString("city")!=null){
                    cityName=address_detailObj.getString("city");
                    cityName=cityName.replace("市","");
                }
            }
            else{

                cityName=null;
            }

        } catch (Exception e) {
            System.err.println(e);
            cityName=null;
        }
        Log.d("test","cityname is "+cityName);
        return cityName;
    }

}
