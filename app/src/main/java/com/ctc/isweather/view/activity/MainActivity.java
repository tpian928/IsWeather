package com.ctc.isweather.view.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ctc.isweather.R;

public class MainActivity extends Fragment{

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

    /**
     * initialize the activity where we need to check whether the network work
     * @param bundle
     * @param view
     */
    public void init(Bundle bundle,View view){
        TextView test = (TextView) view.findViewById(R.id.title_TextView);
        test.setText(bundle.getString("name"));
    }
}
