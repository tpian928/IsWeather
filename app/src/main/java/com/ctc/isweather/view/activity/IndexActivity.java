package com.ctc.isweather.view.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.WindowManager;

import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.ctc.isweather.R;
import com.ctc.isweather.control.DBTools;
import com.ctc.isweather.control.MyLocationListener;

import java.util.ArrayList;

//ditu
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.LocationClientOption.LocationMode;

/**
 * Created by chris on 2015/7/17.
 */
public class IndexActivity extends FragmentActivity{
    private ViewPager vpager;
    private ArrayList<Fragment> flist;
    private ArrayList<String> concity;

    public LocationClient mLocationClient = null;
    public BDLocationListener myListener = new MyLocationListener();
    private LocationMode tempMode = LocationMode.Hight_Accuracy;
    private String tempcoor = "gcj02";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);

        Log.d("ditu","sb");

        WindowManager wm = this.getWindowManager();
        //initViewPage();

        mLocationClient = new LocationClient(getApplicationContext()); // 声明LocationClient类
        mLocationClient.registerLocationListener(myListener); // 注册监听函数
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationMode.Hight_Accuracy);// 设置定位模式
        option.setCoorType("bd09ll");// 返回的定位结果是百度经纬度,默认值gcj02
        option.setScanSpan(500000);// 设置发起定位请求的间隔时间为5000ms
        option.setIsNeedAddress(true);// 返回的定位结果包含地址信息
        option.setNeedDeviceDirect(false);// 返回的定位结果包含手机机头的方向
        mLocationClient.setLocOption(option);
        mLocationClient.start();
        mLocationClient.stop();

        registerBroadcastReceiver();
        startTimeService();

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

    private void registerBroadcastReceiver() {
        // TODO Auto-generated method stub
        UITimeReceiver receiver = new UITimeReceiver();
        IntentFilter filter = new IntentFilter(TIME_CHANGED_ACTION);
        registerReceiver(receiver, filter);
    }

    public class UITimeReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.i("hehe", "接受到了通知");
            String action = intent.getAction();

            if (EditPicAndTextActivity.TIME_CHANGED_ACTION.equals(action)) {
                Bundle bundle = intent.getExtras();
                String strtime = bundle.getString("time");
                // time.setText(strtime);
                String theWeatherString = bundle.getString("weather");
                weather_TextView.setText(theWeatherString);
                String theLocationString = bundle.getString("location");
                location_TextView.setText(theLocationString);

                tag_TextView.setText(bundle.getString("tag"));

                if(picturePath!=null||photoPath!=null){
                    addPic_TextView.setText("已选择");
                }

                // /*
                // * weather and location
                // */
                // Bundle bundle2=intent.getExtras();
                // String string2=bundle2.getString("weather");
                // weather_TextView.setText(string2);
                // Bundle bundle3=intent.getExtras();
                // String string3=bundle3.getString("location");
                // weather_TextView.setText(string3);

            }
        }
    }

}
