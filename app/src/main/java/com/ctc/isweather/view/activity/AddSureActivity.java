package com.ctc.isweather.view.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.ctc.isweather.R;

/**
 * Created by chris on 2015/7/21.
 */
public class AddSureActivity extends Activity {

    private LinearLayout layout;
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_addsure);
        layout = (LinearLayout) findViewById(R.id.dlg_layout);
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"提示：点击窗口外部关闭窗口！",Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        finish();
        return true;
    }

    /**
     * add the city to the concity
     */
    public void sureBtn(){
        this.finish();
    }

    /**
     * cancel the dialog
     */
    public void cancelBtn(){
        this.finish();
    }
}
