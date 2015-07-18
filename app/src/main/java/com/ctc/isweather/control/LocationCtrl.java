package com.ctc.isweather.control;

import android.app.Application;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.AsyncTask;

import com.ctc.isweather.interfaces.OnGeocoderFinishedListener;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * Created by TPIAN on 15/7/16.
 */
public class LocationCtrl {

    public static void getCityName(final Location location, final OnGeocoderFinishedListener listener) {
        new AsyncTask<Void, Integer, List<Address>>() {
            @Override
            protected List<Address> doInBackground(Void... arg0) {
                Geocoder coder = new Geocoder(MyApplication.getAppContext(), Locale.CHINESE);
                List<Address> results = null;
                try {
                    results = coder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                } catch (IOException e) {
                    // nothing
                }
                System.out.println("results is "+results);
                return results;
            }

            @Override
            protected void onPostExecute(List<Address> results) {
                if (results != null && listener != null) {
                    listener.onFinished(results);
                }
            }
        }.execute();
    }

}
