package com.ctc.isweather.view.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.ctc.isweather.R;
import com.ctc.isweather.control.DBTools;

import java.util.ArrayList;

;

public class CityAddActivity extends Activity implements SearchView.OnQueryTextListener, View.OnClickListener {
    private ImageView back;
    private SearchView search;
    private ListView lv;
    private String[] names;
    private ArrayAdapter<String> adapter;
    private int selected;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_add_);

        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(this.getCurrentFocus(), InputMethodManager.SHOW_FORCED);
        initview();
    }

    public void initview()
    {
        // get the array from the database.
        ArrayList<String> citys = DBTools.QueryInCity();
        names = new String[citys.size()];
        for(int i = 0; i < citys.size(); i++){
            names[i] = citys.get(i);
        }

        back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CityAddActivity.this.finish();
                startActivity(new Intent(CityAddActivity.this,CityManageActivity.class));
            }
        });


        search =(SearchView)findViewById(R.id.search);
        search.setOnQueryTextListener(this);
        search.setOnClickListener(this);

        lv=(ListView)findViewById(R.id.lv);
        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,names);
        lv.setAdapter(adapter);
        lv.setTextFilterEnabled(true);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("Chris", "position " + position);
                selected = position;
                new AlertDialog.Builder(CityAddActivity.this)
                        .setTitle("关注城市")
                        .setMessage(names[position] + "，将收入您的关注列表")
                        .setPositiveButton("关注", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // add the city to the concity
                                DBTools.insertInConcity(names[selected]);
                                Log.i("chris", "The city is inserted!");
                                Toast.makeText(CityAddActivity.this.getApplicationContext(),"添加成功",Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("取消",null)
                        .show();

            }
        });
    }

    @Override
    public boolean onQueryTextChange(String arg0) {
        if(arg0.length()!=0){
            lv.setFilterText(arg0);
        }else{
            lv.clearTextFilter();
        }
        return false;
    }
    @Override
    public boolean onQueryTextSubmit(String arg0) {
        /*******************************************************************************************
         * click enter button
         * add city to database
         */
        Log.i("chris","点击按钮: " + arg0);
        return false;
    }

    @Override
    public void onClick(View v) {
        Log.i("chris","Click the search view");
       /* InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(v,InputMethodManager.SHOW_FORCED);*/
    }
}
