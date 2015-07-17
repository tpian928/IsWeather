package com.ctc.isweather.view.activity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;

import com.ctc.isweather.R;

import java.io.InputStream;

public class MainActivity extends ActionBarActivity{

    String packageName;
    InputStream in;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("MainActivity","onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

}
