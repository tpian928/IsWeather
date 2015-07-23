package com.ctc.isweather.view.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.ctc.isweather.R;
import com.ctc.isweather.http.WeatherHttp;
import com.ctc.isweather.mode.bean.HourWeather;

import java.util.ArrayList;

public class TodayWeatherActivity extends Activity {
    public String cityname;
    private TextView sd,fl,pm;
    private ImageView sd_imageview,fl_imageview,pm_imageview;
    private ArrayList<HourWeather> hw_list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_today_weather);
        init();
    }


    private void init(){
        Bundle bundle = getIntent().getExtras();
        cityname = bundle.getString("cityname","北京");

        sd = (TextView) findViewById(R.id.sd_textview);
        fl = (TextView) findViewById(R.id.fl_textview);
        pm = (TextView) findViewById(R.id.pm_textview);
        sd_imageview = (ImageView) findViewById(R.id.sd_imageview);
        fl_imageview = (ImageView) findViewById(R.id.fl_imageview);
        pm_imageview = (ImageView) findViewById(R.id.pm_imageview);

        sd.setText(bundle.getString("wd"));
        fl.setText(bundle.getString("fl"));
        pm.setText(bundle.getString("pm")  + " μm");
        startThread();
    }

    private void startThread(){
        new Thread(){
            @Override
            public void run() {
                super.run();
                hw_list = WeatherHttp.getHourWeather(cityname);
                Log.i("Chris","list长度: " + hw_list.size());
                for (int i = 0; i < hw_list.size();i++){
                    Log.i("chris",hw_list.get(i).getStartHour() + " --- " + hw_list.get(i).getEndHour());
                }
            }
        }.start();
    }




}
