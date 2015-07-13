package com.ctc.isweather.obj;

import java.util.HashMap;

public class Weather {
	/*{"weatherinfo":{"city":"����","cityid":"101010100","temp1":"2��",
	 * "temp2":"6��","weather":"С��ת����","img1":"n7.gif",
	 * "img2":"d1.gif","ptime":"18:00"}}*/
	private static String city;
	private static String cityid;
	private static String temp1;
	private static String temp2;
	private static String weather;
	private static String img1;
	private static String img2;
	private static String ptime;

	public static String getCity() {
		return city;
	}
	public static void setCity(String city) {
		Weather.city = city;
	}
	public static String getCityid() {
		return cityid;
	}
	public static void setCityid(String cityid) {
		Weather.cityid = cityid;
	}
	public static String getTemp1() {
		return temp1;
	}
	public static void setTemp1(String temp1) {
		Weather.temp1 = temp1;
	}
	public static String getTemp2() {
		return temp2;
	}
	public static void setTemp2(String temp2) {
		Weather.temp2 = temp2;
	}
	public static String getWeather() {
		return weather;
	}
	public static void setWeather(String weather) {
		Weather.weather = weather;
	}
	public static String getImg1() {
		return img1;
	}
	public static void setImg1(String img1) {
		Weather.img1 = img1;
	}
	public static String getPtime() {
		return ptime;
	}
	public static void setPtime(String ptime) {
		Weather.ptime = ptime;
	}
	public static String getImg2() {
		return img2;
	}
	public static void setImg2(String img2) {
		Weather.img2 = img2;
	}
}