package com.ctc.isweather.interfaces;

import android.location.Address;

import java.util.List;

/**
 * Created by TPIAN on 15/7/18.
 */
public abstract class OnGeocoderFinishedListener {
    public abstract void onFinished(List<Address> results);
}
