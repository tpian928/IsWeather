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
import com.ctc.isweather.http.WeatherHttp;
import com.ctc.isweather.mode.bean.Weather;

import java.util.Calendar;

public class MainActivity extends Fragment {

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

        tomorrow = (LinearLayout) view.findViewById(R.id.tomorro_LinearLayout);
        after = (LinearLayout) view.findViewById(R.id.after_LinearLayout);
    }


    public void initListener(Bundle bundle){
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
                Intent intent = new Intent(getActivity(),CityManageActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("cityname",cityname);
                intent.putExtras(bundle);
                startActivity(intent);
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

    public String getWeekDay(String date){
        String[] dates = date.split("/");
        Calendar calendar = Calendar.getInstance();//获得一个日历
        calendar.set(Integer.valueOf(dates[0]), Integer.valueOf(dates[1])-1, Integer.valueOf(dates[2]));//设置当前时间,月份是从0月开始计算
        int number = calendar.get(Calendar.DAY_OF_WEEK);//星期表示1-7，是从星期日开始，
        String [] str = {"","Sun","Mon","Thus","Wens","Thur","Fri","Sat"};
        //System.out.println(str[number]);
        return str[number];
    }

    /**
     * Handle Today's weather
     */
    class WeatherHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Weather weather = (Weather)msg.obj;
            // The city id is null
           // Log.i("chris", "send pm25: " + weather.getPm25());

            // set the information below
            pm25.setText("PM2.5  :  " + weather.getPm25());
            temp.setText(weather.getMaintemp());


            // set the indexes
            dressindex.setText(weather.getDressingIndex().getDes());
            coldindex.setText(weather.getColdIndex().getDes());
            carwashindex.setText(weather.getCarwashIndex().getDes());
            sportindex.setText(weather.getSportsIndex().getDes());
        }
    }
}
