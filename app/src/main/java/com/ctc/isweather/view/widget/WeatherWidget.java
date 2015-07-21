package com.ctc.isweather.view.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.widget.RemoteViews;

import com.ctc.isweather.R;
import com.ctc.isweather.control.Icon;
import com.ctc.isweather.control.LocationCtrl;
import com.ctc.isweather.http.WeatherHttp;
import com.ctc.isweather.mode.bean.Weather;
import com.ctc.isweather.view.activity.IndexActivity;
import com.ctc.isweather.view.activity.MainActivity;

/**
 * Implementation of App Widget functionality.
 */
public class WeatherWidget extends AppWidgetProvider {

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        Log.d("widget","onUpdate");
        final int N = appWidgetIds.length;
        for (int i = 0; i < N; i++) {
            updateAppWidget(context, appWidgetManager, appWidgetIds[i]);
        }

        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.weather_widget);
        Intent configIntent = new Intent(context, IndexActivity.class);
        PendingIntent configPendingIntent = PendingIntent.getActivity(context, 0, configIntent, 0);
        remoteViews.setOnClickPendingIntent(R.id.widget_all, configPendingIntent);
        appWidgetManager.updateAppWidget(appWidgetIds, remoteViews);
        Log.d("widgetClick","clicked");
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    static void updateAppWidget(final Context context, final AppWidgetManager appWidgetManager,
                                final int appWidgetId) {

        CharSequence widgetText = context.getString(R.string.appwidget_text);
        // Construct the RemoteViews object

        Thread thread = new Thread(new Runnable(){
            @Override
            public void run() {
                try {
                    //Your code goes here
                    RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.weather_widget);
                    Weather weather = WeatherHttp.getWeather(LocationCtrl.getCityName());
                    views.setTextViewText(R.id.pm_text, "PM2.5 "+weather.getPm25());
                    views.setTextViewText(R.id.temp_text,weather.getMaintemp()+"℃");
                    views.setTextViewText(R.id.weather_text, weather.getTodayWeather().getWeather());
                    Log.d("widget", "weather is " + weather.getTodayWeather().getWeather());
                    views.setImageViewResource(R.id.pic_img, Icon.getWeatherIcon(weather.getTodayWeather().getWeather()));
                    //views.setInt(R.id.widget_all,"setBackgroundColor", Color.BLACK);
                    // Instruct the widget manager to update the widget
                    appWidgetManager.updateAppWidget(appWidgetId, views);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();


    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        String strAction = intent.getAction();
        //Log.d("widget","onReceive action is "+strAction);
        if (AppWidgetManager.ACTION_APPWIDGET_UPDATE.equals(strAction)) {
            /* Do update */
            Log.d("widget","if");
            AppWidgetManager gm = AppWidgetManager.getInstance(context);
            int[] ids = gm.getAppWidgetIds(new ComponentName(context, WeatherWidget.class));
            onUpdate(context,AppWidgetManager.getInstance(context),ids);
        }
    }
}

