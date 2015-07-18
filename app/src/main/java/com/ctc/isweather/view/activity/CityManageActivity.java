package com.ctc.isweather.view.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.ListView;

import com.ctc.isweather.R;
import com.ctc.isweather.adapter.MyAdapter;

public class CityManageActivity extends Activity {
    private ListView listView_city;
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_manage);

        listView_city = (ListView) findViewById(R.id.listView_city);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        myAdapter = new MyAdapter(this, dm.widthPixels);
        //ListView
        listView_city.setAdapter(myAdapter);
    }

}
