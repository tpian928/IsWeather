package com.ctc.isweather.view.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.ScrollView;

import com.ctc.isweather.R;
import com.ctc.isweather.adapter.FragmentAdapter;
import com.ctc.isweather.control.DBTools;
import com.ctc.isweather.control.LocationCtrl;
import com.ctc.isweather.control.service.UpdateWidgetService;

import java.util.ArrayList;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;


/**
 * Created by chris on 2015/7/17.
 */

public final class IndexActivity extends FragmentActivity{
    public static boolean refresh = false;
    public static boolean start = true;

    private ViewPager vpager;
    private MyHandler myHandler;
    private String localCity;
    MyHandler handler;

    //for pull
    PullToRefreshScrollView mPullRefreshScrollView;
    ScrollView mScrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);

       // init(); // call the thread
        DBTools.importDB(this);
        //启动服务
        startService(new Intent(this, UpdateWidgetService.class));

        //for pull
        mPullRefreshScrollView = (PullToRefreshScrollView) findViewById(R.id.pull_refresh_scrollview);

        if (mPullRefreshScrollView==null){
            Log.d("mypull","mPullRefreshScrollView is null"+mPullRefreshScrollView);
        }



//        mPullRefreshScrollView.setOnRefreshListener(new OnRefreshListener<ScrollView>() {
//            @Override
//            public void onRefresh(PullToRefreshBase<ScrollView> refreshView) {
//                new GetDataTask().execute();
//            }
//        });
//
//        mScrollView = mPullRefreshScrollView.getRefreshableView();

    }

    //for pull
    private class GetDataTask extends AsyncTask<Void, Void, String[]> {

        @Override
        protected String[] doInBackground(Void... params) {
            // Simulates a background job.
            try {
                Log.d("mypull","test");
                Thread.sleep(4000);
            } catch (InterruptedException e) {
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

    @Override
    protected void onResume() {
        super.onResume();
        //finish();
        init();
    }
}
