package com.ctc.isweather.view.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.ctc.isweather.R;
import com.ctc.isweather.control.BasicTools;
import com.ctc.isweather.control.Icon;
import com.ctc.isweather.control.charts.FinalLineChart;
import com.ctc.isweather.http.WeatherHttp;
import com.ctc.isweather.mode.bean.WIndex;
import com.ctc.isweather.mode.bean.Weather;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;

public class MainActivity extends Fragment{

    static MainActivity newInstance(String cityname){
        MainActivity city = new MainActivity();
        Bundle bundle = new Bundle();
        bundle.putString("name", cityname);
        city.setArguments(bundle);
        return city;
    }

    private String cityname;
    private TextView city_tv;
    private ImageView city_today_ImageView;
    private ImageView city_manage_ImageView;
    private ImageView city_share_ImageView;
    private TextView date,weekday, pm25,temp,dressindex,coldindex,carwashindex,sportindex;
    private LinearLayout tomorrow,after;
    private TextView weekday_today,weekday_tomorrow,weekday_after;
    private TextView date_today,date_tomorrow,date_after;
    private TextView desc_today,desc_tomorrow,desc_after;
    private TextView wind_today,wind_tomorrow,wind_after;
    private ImageView tomorrow_img,afterafter_img,after_img;
    private TextView max_today,max_tomorrow,max_after;
    private TextView min_today,min_tomorrow,min_after;

    //for pull
    PullToRefreshScrollView mPullRefreshScrollView;
    ScrollView mScrollView;

    // Get the data from the bundle
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_main, container, false);
        init(view);
        initListener(getArguments());
        // Log.i("chris", "Get the information.");
        handler = new WeatherHandler();
        getWeatherInfos();

        //for pull

        mPullRefreshScrollView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ScrollView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ScrollView> refreshView) {
                new GetDataTask().execute();
            }
        });

        mScrollView = mPullRefreshScrollView.getRefreshableView();

        return view;
    }

    private class GetDataTask extends AsyncTask<Void, Void, String[]> {

        @Override
        protected String[] doInBackground(Void... params) {
            // Simulates a background job.
            try {
                Log.d("mypull", "refreshRunning");
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
                //Thread.sleep(4000);
            } catch (Exception e) {

            }
            return null;
        }

        @Override
        protected void onPostExecute(String[] result) {
            // Do some stuff here

            // Call onRefreshComplete when the list has been refreshed.
            mPullRefreshScrollView.onRefreshComplete();

            super.onPostExecute(result);
        }
    }

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

        tomorrow = (LinearLayout) view.findViewById(R.id.tomorrow_LinearLayout);
        after = (LinearLayout) view.findViewById(R.id.after_LinearLayout);

        // basic information setting
        weekday_today = (TextView) view.findViewById(R.id.weekday_today);
        weekday_tomorrow = (TextView) view.findViewById(R.id.weekday_tomorrow);
        weekday_after = (TextView) view.findViewById(R.id.weekday_after);

        date_today = (TextView) view.findViewById(R.id.date_today);
        date_tomorrow = (TextView) view.findViewById(R.id.date_tomorrow);
        date_after = (TextView) view.findViewById(R.id.date_after);

        desc_today = (TextView) view.findViewById(R.id.desc_today);
        desc_tomorrow = (TextView) view.findViewById(R.id.desc_tomorrow);
        desc_after = (TextView) view.findViewById(R.id.desc_after);

        wind_today = (TextView) view.findViewById(R.id.wind_today);
        wind_tomorrow = (TextView) view.findViewById(R.id.wind_tomorrow);
        wind_after = (TextView) view.findViewById(R.id.wind_after);

        tomorrow_img = (ImageView) view.findViewById(R.id.tomorrow_img);
        afterafter_img = (ImageView) view.findViewById(R.id.afterafter_img);
        after_img = (ImageView) view.findViewById(R.id.after_img);

        max_today = (TextView) view.findViewById(R.id.max_today);
        max_tomorrow = (TextView) view.findViewById(R.id.max_tomorrow);
        max_after = (TextView) view.findViewById(R.id.max_after);

        min_today = (TextView) view.findViewById(R.id.min_today);
        min_tomorrow = (TextView) view.findViewById(R.id.min_tomorrow);
        min_after = (TextView) view.findViewById(R.id.min_after);

        //for pull
        mPullRefreshScrollView = (PullToRefreshScrollView)view.findViewById(R.id.pull_refresh_scrollview);
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
                bundle.putString("pm",pm);
                bundle.putString("wd", wd);
                bundle.putString("fl",fl);
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


    private String wd,fl,pm;
    private WIndex dress,sport,carwash,cold;
    /**
     * Handle Today's weather
     */
    class WeatherHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Weather weather = (Weather)msg.obj;

            // get some basic information
            wd = weather.getTodayWeather().getTempRage();
            fl = weather.getTodayWeather().getWind();
            pm = weather.getPm25();

            // set the information below
            pm25.setText("PM2.5  :  " + weather.getPm25());
            temp.setText(weather.getMaintemp());


            // set the indexes
            dress = weather.getDressingIndex();
            sport = weather.getSportsIndex();
            carwash =weather.getCarwashIndex();
            cold = weather.getColdIndex();

            // set basic information
            dressindex.setText(dress.getZs());
            sportindex.setText(sport.getZs());
            carwashindex.setText(carwash.getZs());
            coldindex.setText(cold.getZs());


            // get date
            String date = BasicTools.getDate();
            String[] threeDays = BasicTools.getDates(date);
            Log.i("chris",threeDays[0] +" : " + threeDays[1] + " : " + threeDays[2]);
            date_today.setText(BasicTools.getSimpleDate(threeDays[0]));
            date_tomorrow.setText(BasicTools.getSimpleDate(threeDays[1]));
            date_after.setText(BasicTools.getSimpleDate(threeDays[2]));

            weekday_today.setText(BasicTools.getWeekDay(threeDays[0]));
            weekday_tomorrow.setText(BasicTools.getWeekDay(threeDays[1]));
            weekday_after.setText(BasicTools.getWeekDay(threeDays[2]));

            desc_today.setText(weather.getTodayWeather().getWeather());
            desc_tomorrow.setText(weather.getTomorrowWeather().getWeather());
            desc_after.setText(weather.getAferWeather().getWeather());

            Log.i("chris","温度范围: " + weather.getTodayWeather().getTempRage());

            min_today.setText(weather.getTodayWeather().getTempRage().split("~")[1]);
            max_today.setText(weather.getTodayWeather().getTempRage().split("~")[0] +"℃");
            min_tomorrow.setText(weather.getTomorrowWeather().getTempRage().split("~")[1]);
            max_tomorrow.setText(weather.getTomorrowWeather().getTempRage().split("~")[0] +"℃");
            min_after.setText(weather.getAferWeather().getTempRage().split("~")[1]);
            max_after.setText(weather.getAferWeather().getTempRage().split("~")[0] +"℃");

            wind_today.setText(weather.getTodayWeather().getWind());
            wind_tomorrow.setText(weather.getTomorrowWeather().getWind());
            wind_after.setText(weather.getAferWeather().getWind());

            tomorrow_img.setImageResource(Icon.getWeatherIcon(weather.getTodayWeather().getWeather()));
            afterafter_img.setImageResource(Icon.getWeatherIcon(weather.getTomorrowWeather().getWeather()));
            after_img.setImageResource(Icon.getWeatherIcon(weather.getAferWeather().getWeather()));
            //BasicTools.getWholeWeekdays(BasicTools.getDate());
            city_today_ImageView.setImageResource(Icon.getWeatherIcon(weather.getTodayWeather().getWeather()));
        }
    }
}
