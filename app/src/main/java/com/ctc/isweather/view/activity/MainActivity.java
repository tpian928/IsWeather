package com.ctc.isweather.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ctc.isweather.R;
import com.ctc.isweather.control.charts.FinalLineChart;
import com.ctc.isweather.http.WeatherHttp;
import com.ctc.isweather.mode.bean.WIndex;
import com.ctc.isweather.mode.bean.Weather;

public class MainActivity extends Fragment{

    static MainActivity newInstance(String cityname){
        MainActivity city = new MainActivity();
        Bundle bundle = new Bundle();
        bundle.putString("name", cityname);
        city.setArguments(bundle);
        return city;
    }

    // Get the data from the bundle
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_main, container, false);
        init(view);
        initListener(getArguments());
        // Log.i("chris", "Get the information.");
        getWeatherInfos();
        return view;
    }

    private String cityname;
    private TextView city_tv;
    private ImageView city_today_ImageView;
    private ImageView city_manage_ImageView;
    private ImageView city_share_ImageView;
    private TextView date,weekday, pm25,temp,dressindex,coldindex,carwashindex,sportindex;
    private LinearLayout tomorrow,after;

    public void init(View view){
        city_tv = (TextView) view.findViewById(R.id.title_TextView);
        date  = (TextView) view.findViewById(R.id.date_TextView);
        weekday  = (TextView) view.findViewById(R.id.weekday_TextView);
        pm25 = (TextView) view.findViewById(R.id.pm25_TextView);
        temp  = (TextView) view.findViewById(R.id.temp_TextView);
        dressindex  = (TextView) view.findViewById(R.id.dressIndex_tv);
        coldindex  = (TextView) view.findViewById(R.id.coldIndex_tv);
        carwashindex  = (TextView) view.findViewById(R.id.carwash_tv);
        sportindex  = (TextView) view.findViewById(R.id.sportIndex_tv);

        city_today_ImageView = (ImageView) view.findViewById(R.id.city_today_imageView);
        city_manage_ImageView = (ImageView) view.findViewById(R.id.city_manage_ImageView);
        city_share_ImageView = (ImageView) view.findViewById(R.id.share_ImageView);

        tomorrow = (LinearLayout) view.findViewById(R.id.tomorro_LinearLayout);
        after = (LinearLayout) view.findViewById(R.id.after_LinearLayout);


    }


    public void initListener(Bundle bundle){
        IndexActivity.refresh = false;
        cityname = bundle.getString("name");
        city_tv.setText(cityname);
        // Log.i("chris", "city_tv: " + cityname);

        // turn to ToDayWeather_Activity.
        city_today_ImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), TodayWeatherActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("cityname",cityname);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });


        // turn to CityManage_Activity.
        city_manage_ImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IndexActivity.refresh = true;
               Intent intent = new Intent(getActivity(),CityManageActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("cityname",cityname);
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });

        city_share_ImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), FinalLineChart.class));
            }
        });

        // turn to futureWeather_Activity
        tomorrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),FutureWeatherActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("cityname",cityname);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        dressindex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),ShowIndexDialog.class);
                Bundle bundle = new Bundle();
                bundle.putString("indexname",dress.getTitle());
                bundle.putString("zs",dress.getZs());
                bundle.putString("tipt", dress.getTipt());
                bundle.putString("desc", dress.getDes());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        sportindex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),ShowIndexDialog.class);
                Bundle bundle = new Bundle();
                bundle.putString("title",sport.getTitle());;
                bundle.putString("zs",sport.getZs());
                bundle.putString("tipt",sport.getTipt());
                bundle.putString("desc",sport.getDes());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        carwashindex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ShowIndexDialog.class);
                Bundle bundle = new Bundle();
                bundle.putString("indexname", carwash.getTitle());
                bundle.putString("zs", carwash.getZs());
                bundle.putString("tipt", carwash.getTipt());
                bundle.putString("desc", carwash.getDes());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        coldindex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),ShowIndexDialog.class);
                Bundle bundle = new Bundle();
                bundle.putString("indexname",cold.getTitle());
                bundle.putString("zs",cold.getZs());
                bundle.putString("tipt",cold.getTipt());
                bundle.putString("desc",cold.getDes());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

    }

    WeatherHandler handler;
    /**
     * Two threads will be executed here.
     */
    public void getWeatherInfos(){
        handler = new WeatherHandler();
        new Thread(){
            @Override
            public void run() {
                super.run();
                Weather weather = WeatherHttp.getWeather(cityname);
                //Log.i("chris",weather.getPm25());
                Message msg = new Message();
                msg.obj = weather;
                handler.sendMessage(msg);
            }
        }.start();

    }


    private WIndex dress,sport,carwash,cold;
    /**
     * Handle Today's weather
     */
    class WeatherHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Weather weather = (Weather)msg.obj;

            // set the information below
            pm25.setText("PM2.5  :  " + weather.getPm25());
            temp.setText(weather.getMaintemp());


            // set the indexes
            dress = weather.getDressingIndex();
            sport = weather.getSportsIndex();
            carwash =weather.getCarwashIndex();
            cold = weather.getColdIndex();
            //BasicTools.getWholeWeekdays(BasicTools.getDate());
        }
    }
}
