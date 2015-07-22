package com.ctc.isweather.view.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.ctc.isweather.R;

/**
 * Created by chris on 2015/7/22.
 */
public class ShowIndexDialog extends Activity {
    private String title,tipt,desc,zs;
    private TextView title_tv,tipt_tv,desc_tv,zs_tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showindex);

        Bundle bundle = getIntent().getExtras();
        title = bundle.getString("title");
        tipt = bundle.getString("tipt");
        desc = bundle.getString("desc");
        zs = bundle.getString("zs");

        tipt_tv = (TextView) findViewById(R.id.tipt_tv);
        desc_tv = (TextView) findViewById(R.id.desc_tv);
        zs_tv = (TextView) findViewById(R.id.zs_tv);

        tipt_tv.setText(tipt);
        desc_tv.setText(desc);
        zs_tv.setText(zs);
    }
}
