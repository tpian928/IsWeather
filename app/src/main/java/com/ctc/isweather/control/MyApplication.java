package com.ctc.isweather.control;

import android.app.Application;
import android.content.Context;

/**
 * Created by TPIAN on 15/7/18.
 */
public class MyApplication extends Application {

    private static Context context;

    public void onCreate(){
        super.onCreate();
        MyApplication.context = getApplicationContext();
    }

    public static Context getAppContext() {
        return MyApplication.context;
    }
}

