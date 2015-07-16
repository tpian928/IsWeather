package com.ctc.isweather.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import com.ctc.isweather.R;
import com.ctc.isweather.activity.MainActivity;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 定时（每小时）轮询监测天气，如果未来2天内有极端天气，则推送通知
 * 程序退出时，后台服务不关闭
 * Created by saty on 2015/7/14.
 */
public class NotificationSvc extends Service {
    private long interval = 60*60*1000;//每小时定时获取
    private static Timer timer = null;

    //推送通知
    public void PushNotification(Intent intent)
    {
        NotificationManager nm = (NotificationManager)NotificationSvc.this.getSystemService(NOTIFICATION_SERVICE);
        Notification.Builder builder = new Notification.Builder(NotificationSvc.this);
        Intent notificationIntent = new Intent(NotificationSvc.this, MainActivity.class);//点击通知跳转至主界面
        PendingIntent contentIntent = PendingIntent.getActivity(NotificationSvc.this,0,notificationIntent,0);

        //设置通知
        builder.setContentIntent(contentIntent);
        builder.setSmallIcon(R.mipmap.ic_launcher);//通知栏图标
        builder.setTicker(intent.getStringExtra("tickerText")); //测试通知栏标题
        builder.setContentText(intent.getStringExtra("contentText")); //下拉通知内容
        builder.setContentTitle(intent.getStringExtra("contentTitle"));//下拉通知栏标题
        builder.setAutoCancel(true);
        builder.setDefaults(Notification.DEFAULT_ALL);

        //启动通知
        Notification notification = builder.build();
        nm.notify((int)System.currentTimeMillis(),notification);
    }

    //清除通知
    public void clearNotification()
    {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(null == timer){
            timer = new Timer();
        }
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                //从网络获取天气信息
                //未来2天内有恶劣情况，启动推动

                /*intent.putExtra("tickerText","");
                intent.putExtra("contentText","");//提醒用户做好预防措施
                intent.putExtra("contentTitle","");//雷电，大风，暴雨，干旱，寒潮*/
                //PushNotification(intent);
            }
        }, 0,interval);

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}