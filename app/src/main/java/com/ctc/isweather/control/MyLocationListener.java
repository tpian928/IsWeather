package com.ctc.isweather.control;

import java.security.PublicKey;

import android.text.StaticLayout;
import android.util.Log;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;

public class MyLocationListener implements BDLocationListener {

	public static String outputLocation;
	public static String cityName;
	
	public void onReceiveLocation(BDLocation location) {

		Log.d("ditu","receive");

		if (location == null)
	            return ;
		StringBuffer sb = new StringBuffer(256);
		sb.append("time : ");
		sb.append(location.getTime());
		sb.append("\nerror code : ");
		sb.append(location.getLocType());
		sb.append("\nlatitude : ");
		sb.append(location.getLatitude());
		sb.append("\nlontitude : ");
		sb.append(location.getLongitude());
		sb.append("\nradius : ");
		sb.append(location.getRadius());
		if (location.getLocType() == BDLocation.TypeGpsLocation){
			sb.append("\nspeed : ");
			sb.append(location.getSpeed());
			sb.append("\nsatellite : ");
			sb.append(location.getSatelliteNumber());
		} else if (location.getLocType() == BDLocation.TypeNetWorkLocation){
			sb.append("\naddr : ");
			sb.append(location.getAddrStr());
		} 

		Log.i("ditu", "½ÖµÀÃû×ÖÊÇ"+location.getAddrStr());
		Log.i("ditu", "³ÇÊÐÊÇ"+location.getCity( ));		
		outputLocation=location.getAddrStr();
		cityName=location.getCity();
	}
	
	public static String getTheLocation(){
		
		return outputLocation;
	}
	
	public static String getCityName(){
		return cityName;
	}
	
}
