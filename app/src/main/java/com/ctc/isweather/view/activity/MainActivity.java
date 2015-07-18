package com.ctc.isweather.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ctc.isweather.R;

public class MainActivity extends Fragment{
    private ImageView city_manage_ImageView;
    // each move, need to get the data from the internet
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
        init(getArguments(),view);
        return view;
    }

    private TextView city_tv;
    private ImageView city_iv;
    /**
     * initialize the activity where we need to check whether the network work
     * @param bundle
     * @param view
     */
    public void init(Bundle bundle,View view){
        city_tv = (TextView) view.findViewById(R.id.title_TextView);
        city_tv.setText(bundle.getString("name"));

        // set the main imageview of the certain city
        city_iv = (ImageView) view.findViewById(R.id.city_imageView);
        city_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // some differences between activities and fragments.
                Intent intent = new Intent(getActivity(),TodayWeatherActivity.class);
                startActivity(intent);
            }
        });

        city_manage_ImageView = (ImageView) view.findViewById(R.id.city_manage_ImageView);
        city_manage_ImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),CityManageActivity.class);
                startActivity(intent);
            }
        });
    }

}
