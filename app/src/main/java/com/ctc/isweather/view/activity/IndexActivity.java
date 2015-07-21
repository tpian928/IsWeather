package com.ctc.isweather.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.ctc.isweather.R;
import com.ctc.isweather.adapter.FragmentAdapter;
import com.ctc.isweather.control.DBTools;
import com.ctc.isweather.control.LocationCtrl;
import com.ctc.isweather.control.service.UpdateWidgetService;

import java.util.ArrayList;

/**
 * Created by chris on 2015/7/17.
 */
public class IndexActivity extends FragmentActivity{
    private ViewPager vpager;
    private MyHandler myHandler;
    private String localCity;
    MyHandler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);

       // init(); // call the thread
        DBTools.importDB(this);
        //测试
        startService(new Intent(this, UpdateWidgetService.class));
    }

    public void init(){
        handler = new MyHandler();
        // get the lbs location
        new Thread(new Runnable(){
            @Override
            public void run() {
                try {
                    //Your code goes here
                    String  lbs = LocationCtrl.getCityName();
                    Message msg = new Message();
                    msg.obj = lbs;
                    handler.sendMessage(msg);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
        //initViewPage();
    }

    public void initViewPage(String citylbs){
        Log.i("chris","LBS city: " + citylbs);
        localCity = citylbs;
        // init the viewpager
        vpager = (ViewPager) findViewById(R.id.viewpager);
        ArrayList<String> concity = DBTools.QueryInConcity(DBTools.openDatabase(getPackageName()));

        ArrayList<Fragment> flist = new ArrayList<Fragment>();
        flist.add(MainActivity.newInstance(citylbs));

        if (concity.size() > 0) {
            for(String city : concity) {
                flist.add(MainActivity.newInstance(city));
            }
        }

        vpager.setAdapter(new FragmentAdapter(getSupportFragmentManager(), flist));
        vpager.setCurrentItem(0);
    }

    /**
     * Class MyHandler.
     */
    class MyHandler extends Handler{
        public MyHandler() {
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String message = String.valueOf(msg.obj);
            initViewPage(message);
        }
    }

    // flash


    @Override
    protected void onPause() {
        super.onPause();
        vpager.removeAllViews();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Toast.makeText(this.getApplicationContext(),"need to delete the city",Toast.LENGTH_SHORT).show();
        // vpager.setAdapter(new FragmentAdapter(getSupportFragmentManager(), null));
        init();
    }
}
