package com.ctc.isweather;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;

/**
 * Created by chris on 2015/7/14.
 */
public class ToastActivity extends Activity{
    public Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toast);
        btn = (Button) findViewById(R.id.btnSkip);
    }
}
