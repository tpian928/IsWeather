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
 * ��ʱ��ÿСʱ����ѯ������������δ��2�����м���������������֪ͨ
 * �����˳�ʱ����̨���񲻹ر�
 * Created by saty on 2015/7/14.
 */
public class NotificationSvc extends Service {
    private long interval = 60*60*1000;//ÿСʱ��ʱ��ȡ
    private static Timer timer = null;

    //����֪ͨ
    public void PushNotification(Intent intent)
    {
        NotificationManager nm = (NotificationManager)NotificationSvc.this.getSystemService(NOTIFICATION_SERVICE);
        Notification.Builder builder = new Notification.Builder(NotificationSvc.this);
        Intent notificationIntent = new Intent(NotificationSvc.this, MainActivity.class);//���֪ͨ��ת��������
        PendingIntent contentIntent = PendingIntent.getActivity(NotificationSvc.this,0,notificationIntent,0);

        //����֪ͨ
        builder.setContentIntent(contentIntent);
        builder.setSmallIcon(R.mipmap.ic_launcher);//֪ͨ��ͼ��
        builder.setTicker(intent.getStringExtra("tickerText")); //����֪ͨ������
        builder.setContentText(intent.getStringExtra("contentText")); //����֪ͨ����
        builder.setContentTitle(intent.getStringExtra("contentTitle"));//����֪ͨ������
        builder.setAutoCancel(true);
        builder.setDefaults(Notification.DEFAULT_ALL);

        //����֪ͨ
        Notification notification = builder.build();
        nm.notify((int)System.currentTimeMillis(),notification);
    }

    //���֪ͨ
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
                //�������ȡ������Ϣ
                //δ��2�����ж�������������ƶ�

                /*intent.putExtra("tickerText","");
                intent.putExtra("contentText","");//�����û�����Ԥ����ʩ
                intent.putExtra("contentTitle","");//�׵磬��磬���꣬�ɺ�������*/
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