package com.ctc.isweather.view.activity;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ctc.isweather.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class CityAddActivityFragment extends Fragment {

    public CityAddActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_city_add, container, false);
    }
}
