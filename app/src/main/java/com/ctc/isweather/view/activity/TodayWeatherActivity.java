package com.ctc.isweather.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ctc.isweather.R;
import com.ctc.isweather.control.charts.FinalLineChart;
import com.ctc.isweather.http.WeatherHttp;
import com.ctc.isweather.mode.bean.HourWeather;
import com.github.mikephil.charting.charts.LineChart;

import java.util.ArrayList;

public class TodayWeatherActivity extends Activity {
    private ImageView back;
    public String cityname;
    private TextView sd, fl, pm;
    private LineChart mChart;
    MyHandler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_today_weather);
        init();
    }


    private void init() {
        Bundle bundle = getIntent().getExtras();
        cityname = bundle.getString("cityname", "北京");

        mChart = (LineChart) findViewById(R.id.chart);
        sd = (TextView) findViewById(R.id.sd_textview);
        fl = (TextView) findViewById(R.id.fl_textview);
        pm = (TextView) findViewById(R.id.pm_textview);
        back = (ImageView) findViewById(R.id.back_ImageView);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TodayWeatherActivity.this.finish();
                startActivity(new Intent(TodayWeatherActivity.this, IndexActivity.class));
            }
        });

        sd.setText(bundle.getString("wd"));
        fl.setText(bundle.getString("fl"));
        pm.setText(bundle.getString("pm") + " μm");
        handler = new MyHandler();
        startThread();
    }

    private void startThread() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                ArrayList<HourWeather> hw_list = WeatherHttp.getHourWeather(cityname);
                Message msg = new Message();
                msg.obj = hw_list;
                handler.sendMessage(msg);
            }
        }.start();
    }


    class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            ArrayList<HourWeather> list = (ArrayList<HourWeather>) msg.obj;
            String[] min = new String[list.size()];
            String[] max = new String[list.size()];
            for (int i = 0; i < 8; i++) {
                min[i] = list.get(i).getTemp1();
                //max[i] = list.get(i).getTemp2();
            }
            String[] times = {"5:00","8:00","11:00","14:00","17:00","20:00","23:00","2:00"};
            Log.i("chris","size: " +list.size());
            FinalLineChart.setChart(mChart, times, min);
        }
    }
}
