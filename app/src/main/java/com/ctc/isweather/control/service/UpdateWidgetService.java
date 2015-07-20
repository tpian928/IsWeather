package com.ctc.isweather.control.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

public class UpdateWidgetService extends Service {

    private long interval = 60*60*1000;//每小时定时获取
    private static Timer timer = null;

    public UpdateWidgetService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(null == timer){
            timer = new Timer();//你好
        }
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Log.d("widget","run");
                //android.appwidget.action.APPWIDGET_UPDATE
                sendBroadcast(new Intent("android.appwidget.action.APPWIDGET_UPDATE"));
            }
        }, 0,interval);

        return super.onStartCommand(intent, flags, startId);
    }
}
