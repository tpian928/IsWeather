package com.ctc.isweather.view.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.WindowManager;

import com.ctc.isweather.R;
import com.ctc.isweather.control.DBTools;

import java.util.ArrayList;

/**
 * Created by chris on 2015/7/17.
 */
public class IndexActivity extends FragmentActivity{
    private ViewPager vpager;
    private ArrayList<Fragment> flist;
    private ArrayList<String> concity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);

        WindowManager wm = this.getWindowManager();
        initViewPage();

    }

    public void initViewPage(){
        vpager = (ViewPager) findViewById(R.id.viewpager);
        concity = DBTools.QueryInConcity(DBTools.openDatabase(getPackageName()));
        if (concity.size() > 0) {
            flist = new ArrayList<Fragment>();
            // add the concerned city to the arraylist
            for(String city : concity) {
                flist.add(MainActivity.newInstance(city));
            }

            vpager.setAdapter(new FragmentAdapter(getSupportFragmentManager(), flist));
            vpager.setCurrentItem(0);
        }else{
            //set the MainActvity to the LBS city.
        }


    }

}
