package com.ctc.isweather.view.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.WindowManager;

import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.ctc.isweather.R;
import com.ctc.isweather.control.DBTools;
import com.ctc.isweather.control.MyLocationListener;
import com.ctc.isweather.http.WeatherHttp;


import java.util.ArrayList;

/**
 * Created by chris on 2015/7/17.
 */
public class IndexActivity extends FragmentActivity{
    private ViewPager vpager;
    private ArrayList<Fragment> flist;
    private ArrayList<String> concity;


    // 这是关于地图的
    public LocationClient mLocationClient = null;
    public BDLocationListener myListener = new MyLocationListener();
    private LocationClientOption.LocationMode tempMode = LocationClientOption.LocationMode.Hight_Accuracy;
    private String tempcoor = "gcj02";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);

        WindowManager wm = this.getWindowManager();

//        DBTools.importDB(this);
//        initViewPage();

        //new
        		/*
		 * 地图
		 */
        mLocationClient = new LocationClient(getApplicationContext()); // 声明LocationClient类
        mLocationClient.registerLocationListener(myListener); // 注册监听函数
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);// 设置定位模式
        option.setCoorType("bd09ll");// 返回的定位结果是百度经纬度,默认值gcj02
        option.setScanSpan(500000);// 设置发起定位请求的间隔时间为5000ms
        option.setIsNeedAddress(true);// 返回的定位结果包含地址信息
        option.setNeedDeviceDirect(false);// 返回的定位结果包含手机机头的方向
        mLocationClient.setLocOption(option);
        mLocationClient.start();
        mLocationClient.stop();

        
    }

    public void initViewPage(){
        vpager = (ViewPager) findViewById(R.id.viewpager);
        concity = DBTools.QueryInConcity(DBTools.openDatabase(getPackageName()));
        if (concity.size() > 0) {
            flist = new ArrayList<Fragment>();
            // add the concerned city to the arraylist
            for(String city : concity) {
                flist.add(MainActivity.newInstance(city));
            }

            vpager.setAdapter(new FragmentAdapter(getSupportFragmentManager(), flist));
            vpager.setCurrentItem(0);
        }else{
            //set the MainActvity to the LBS city.
        }


    }

}
