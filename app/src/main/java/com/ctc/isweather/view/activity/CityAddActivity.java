package com.ctc.isweather.view.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import com.ctc.isweather.R;
;

public class CityAddActivity extends Activity implements SearchView.OnQueryTextListener {
    private SearchView search;
    private ListView lv;
    private String[] names=new String[]{"张三","李四","王五","王会"};//数据库里的城市名取出来，组成数组可以吗？
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_add_);

        initview();
    }

    public void initview()
    {
        search =(SearchView)findViewById(R.id.search);
        search.setOnQueryTextListener(this);
        lv=(ListView)findViewById(R.id.lv);
        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,names);
        lv.setAdapter(adapter);
        lv.setTextFilterEnabled(true);//这个属性为true表示listview获得当前焦点的时候，相应用户输入的匹配符，筛选出匹配的
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
         * add city to database
         */
        return false;
    }
}
