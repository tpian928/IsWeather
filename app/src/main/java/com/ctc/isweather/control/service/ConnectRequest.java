package com.ctc.isweather.control.service;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

/**
 * Created by chris on 2015/7/16.
 */
public class ConnectRequest {

    /**
     * check whether the network can be used.
     * @param activity
     * @return
     */
    public static boolean isNetworkAvailable(Activity activity){
        Context context = activity.getApplicationContext();
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm == null){
            Toast.makeText(activity,"cm is null",Toast.LENGTH_SHORT).show();
            return false;
        }
        else{
            NetworkInfo[] networkInfos = cm.getAllNetworkInfo();
            if (networkInfos != null && networkInfos.length > 0){
                for(int i = 0; i < networkInfos.length;i++){
                    if (networkInfos[i].getState() == NetworkInfo.State.CONNECTED){
                        //Toast.makeText(activity,"free!",Toast.LENGTH_SHORT).show();
                        return true;
                    }
                }
            }
        }
        return false;
    }

}
