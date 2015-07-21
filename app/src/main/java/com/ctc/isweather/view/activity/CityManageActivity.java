package com.ctc.isweather.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.ctc.isweather.R;
import com.ctc.isweather.adapter.MyAdapter;

public class CityManageActivity extends Activity {
    private ListView listView_city;
    private MyAdapter myAdapter;
    private ImageView backBtn;
    private ImageView addBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_manage);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        // init
        listView_city = (ListView) findViewById(R.id.listView_city);
        myAdapter = new MyAdapter(this, dm.widthPixels);
        listView_city.setAdapter(myAdapter);

        // back
        backBtn = (ImageView) findViewById(R.id.back);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // when this class finishes, the main home page cannot flash.
                CityManageActivity.this.finish();
                startActivity(new Intent(CityManageActivity.this,IndexActivity.class));
            }
        });

        // add
        addBtn = (ImageView)findViewById(R.id.add_ImageView);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //jump to the add page
                CityManageActivity.this.finish();
                Intent intent = new Intent(CityManageActivity.this,CityAddActivity.class);
                startActivity(intent);
            }
        });
    }
}
