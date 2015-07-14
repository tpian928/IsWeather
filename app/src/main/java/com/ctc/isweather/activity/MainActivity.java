package com.ctc.isweather.activity;

<<<<<<< HEAD:app/src/main/java/com/ctc/isweather/activity/MainActivity.java
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
=======
import android.content.Intent;
>>>>>>> origin/chris:app/src/main/java/com/ctc/isweather/MainActivity.java
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.ctc.isweather.R;
import com.ctc.isweather.obj.Weather;

public class MainActivity extends ActionBarActivity{

    private Button click;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("MainActivity","onCreate");
        super.onCreate(savedInstanceState);
<<<<<<< HEAD:app/src/main/java/com/ctc/isweather/activity/MainActivity.java
        setContentView(R.layout.activity_main);

        //测试
        Thread thread = new Thread(new Runnable(){
            @Override
            public void run() {
                try {
                    //Your code goes here
                    Weather mWeather = new Weather("北京");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();

=======
        Log.d("MainActivity", "test");
        setContentView(R.layout.activity_main);
        click = (Button) findViewById(R.id.btnSkip);
        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ToastActivity.class);
                startActivity(intent);
            }
        });
>>>>>>> origin/chris:app/src/main/java/com/ctc/isweather/MainActivity.java
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

<<<<<<< HEAD:app/src/main/java/com/ctc/isweather/activity/MainActivity.java

=======
>>>>>>> origin/chris:app/src/main/java/com/ctc/isweather/MainActivity.java
}
