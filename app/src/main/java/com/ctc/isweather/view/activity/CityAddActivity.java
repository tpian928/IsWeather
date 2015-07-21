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
    private String[] names=new String[]{"����","����","����","����"};//���ݿ���ĳ�����ȡ������������������
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
        lv.setTextFilterEnabled(true);//�������Ϊtrue��ʾlistview��õ�ǰ�����ʱ����Ӧ�û������ƥ�����ɸѡ��ƥ���
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
