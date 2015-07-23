package com.ctc.isweather.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.ctc.isweather.R;
import com.ctc.isweather.control.BasicTools;
import com.ctc.isweather.control.DBTools;
import com.ctc.isweather.control.charts.FinalLineChart;
import com.ctc.isweather.http.WeatherHttp;
import com.ctc.isweather.mode.bean.FutureWeather;
import com.github.mikephil.charting.charts.LineChart;

import java.util.ArrayList;

public class FutureWeatherActivity extends Activity {
    private FutureHandler handler;
    private String cityname;
    private ImageView back;
    private String[] min;
    private String[] max;
    private LineChart chart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_future_weather);

        Bundle bundle = getIntent().getExtras();
        cityname = bundle.getString("cityname", "北京");
        init();
    }

    public void init() {
        chart = (LineChart) findViewById(R.id.chart);
        back = (ImageView) findViewById(R.id.back_ImageView);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FutureWeatherActivity.this.finish();
                startActivity(new Intent(FutureWeatherActivity.this, IndexActivity.class));
            }
        });

        handler = new FutureHandler();
        new Thread() {
            @Override
            public void run() {
                super.run();
                int cityid = DBTools.getIdFromCity(cityname);
                ArrayList<FutureWeather> list = WeatherHttp.getFutureWeather(cityid);
                Log.i("chris", cityname + " : list size: " + list.size() + "; id : " + cityid);
                Message msg = new Message();
                msg.obj = list;
                handler.sendMessage(msg);
            }
        }.start();
    }


    class FutureHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            ArrayList<FutureWeather> list = (ArrayList<FutureWeather>) msg.obj;
            //Log.i("chris","handler");
            if (list.size() > 0) {
                min = new String[list.size()];
                max = new String[list.size()];
                for (int i = 0; i < list.size(); i++) {
                    max[i] = list.get(i).getMaxTemp();
                    min[i] = list.get(i).getMinTemp();
                    Log.i("chris", "最低温度 : " + min[i]);
                }
                /*LineDataSet min_LDS = DrawCharts.getLineDataSet(min, Color.BLUE, Color.YELLOW, Color.YELLOW, "最低温度");
                LineDataSet max_LDS = DrawCharts.getLineDataSet(max, Color.RED, Color.GREEN, Color.GREEN, "最高温度");
                ArrayList<LineDataSet> ldlist = new ArrayList<LineDataSet>();
                ldlist.add(min_LDS);
                ldlist.add(max_LDS);
                LineData ld = DrawCharts.getLineData(6, ldlist);
                DrawCharts.showChart(chart, ld, "温度变化曲线", Color.WHITE, Color.WHITE, Color.WHITE);*/
                // Log.i("chris","draw");

                FinalLineChart.setChart(chart, BasicTools.getWholeWeekdays(BasicTools.getDate()), min, max);
                //FinalLineChart.setChart(chart, BasicTools.getWholeWeekdays(BasicTools.getDate()), min);
            } else {
                Toast.makeText(getApplicationContext(), "No elements", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
