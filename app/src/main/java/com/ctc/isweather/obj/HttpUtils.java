package com.ctc.isweather.obj;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

import android.util.Log;

public class HttpUtils {
	private static String getJsonContent(String path){
		try {
			Log.i("httpUtils","httpUtils");
			URL url=new URL(path);
			HttpURLConnection connection=(HttpURLConnection) url.openConnection();
			connection.setConnectTimeout(3000);
			connection.setRequestMethod("GET");
			connection.setDoInput(true);
			int code=connection.getResponseCode();
			if(code==200){
				Log.i("httpUtils","code == 200");
				return changeInputString(connection.getInputStream());
			}
		} catch (Exception e) {
			// TODO: handle exception
			Log.i("httpUtils",e.toString());
		}
		return "";
	}

	private static String changeInputString(InputStream inputStream) {

		String jsonString="";
		ByteArrayOutputStream outPutStream=new ByteArrayOutputStream();
		byte[] data=new byte[1024];
		int len=0;
		try {
			while((len=inputStream.read(data))!=-1){
				outPutStream.write(data, 0, len);
			}
			jsonString=new String(outPutStream.toByteArray());
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Log.i("httpUtils",jsonString);
		return jsonString;
	}
	
	public static String getWeather(String cityNumber) {
		 /*101010100�Ǳ������������ȥhttp://www.weather.com.cn/����飬
		 * �Ҿ��ÿ��Ը������ã�����Ϊ�˼������ñ����Ĵ��� */
		/*{"weatherinfo":{"city":"����","cityid":"101010100","temp1":"2��",
		 * "temp2":"6��","weather":"С��ת����","img1":"n7.gif",
		 * "img2":"d1.gif","ptime":"18:00"}}*/
		String jsonString = null;
		String url = "http://www.weather.com.cn/data/cityinfo/"+cityNumber+".html";
		jsonString = getJsonContent(url);
        try {  
            JSONObject jsonObject = new JSONObject(jsonString);
            //Log.i("httpUtils",jsonObject.toString());
            JSONObject weatherObject = jsonObject.getJSONObject("weatherinfo");
            Weather.setCity(weatherObject.getString("city"));
            Weather.setCityid(weatherObject.getString("cityid"));
            Weather.setTemp1(weatherObject.getString("temp1"));
            Weather.setTemp2(weatherObject.getString("temp2"));
            Weather.setWeather(weatherObject.getString("weather"));
            Weather.setPtime(weatherObject.getString("ptime"));
            Weather.setImg1(weatherObject.getString("img1"));
            Weather.setImg2(weatherObject.getString("img2"));
            return weatherObject.getString("weather");
        } catch (Exception e) {
            // TODO: handle exception
        	Log.i("httpUtils",e.toString());
        }
		return "None weatherinfo";    
    }
}
