package com.ctc.isweather.view.activity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.ctc.isweather.R;
import com.ctc.isweather.control.DBTools;
import com.ctc.isweather.http.ConnectRequest;

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
        ConnectRequest.isNetworkAvailable(this);
        //测试
/*        Thread thread = new Thread(new Runnable(){
            @Override
            public void run() {
                try {
                    //Your code goes here
                    Weather mWeather = new Weather("北京");
//                    String color = Color.getColorByTemp(55);
//                    Log.d("color",color);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();*/
        packageName = getPackageName();
        in = this.getApplicationContext().getResources().openRawResource(R.raw.weathersys);
        DBTools.importDB(in,packageName);
        db = DBTools.openDatabase(packageName);


        Button testbtn = (Button) findViewById(R.id.testBtn);
        testbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBTools.importDB(in, packageName);
            }
        });

        Button insertbtn = (Button) findViewById(R.id.insertBtn);
        insertbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBTools.insertInConcity(db, "深圳");

            }
        });

        Button deleteBtn = (Button) findViewById(R.id.deleteBtn);
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBTools.deleteInConcity(db, "深圳");
            }
        });

        Button queryBtn = (Button) findViewById(R.id.queryBtn);
        queryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBTools.QueryInConcity(db);
            }
        });

        Button connectBtn = (Button) findViewById(R.id.connectBtn);
        connectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConnectRequest.isNetworkAvailable(MainActivity.this);
            }
        });



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


}
