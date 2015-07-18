package com.ctc.isweather.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ctc.isweather.R;

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
        return view;
    }

    private String cityname;
    private TextView city_tv;
    private ImageView city_today_ImageView;
    private ImageView city_manage_ImageView;

    public void init(View view){
        city_tv = (TextView) view.findViewById(R.id.title_TextView);
        city_today_ImageView = (ImageView) view.findViewById(R.id.city_today_imageView);
        city_manage_ImageView = (ImageView) view.findViewById(R.id.city_manage_ImageView);
    }


    public void initListener(Bundle bundle){
        cityname = bundle.getString("name");
        city_tv.setText(cityname);
        Log.i("chris","city_tv: " +cityname);

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
    }

    public void getWeatherInfos(){

    }
}
