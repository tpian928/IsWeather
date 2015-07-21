package com.ctc.isweather.view.activity;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.SimpleCursorAdapter;
import com.ctc.isweather.R;

import java.lang.reflect.Field;

public class CityAddActivity extends Activity {
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_add_);

        searchView = (SearchView) findViewById(R.id.searchview);
        searchView.setIconifiedByDefault(false);
        searchView.setSubmitButtonEnabled(true);
        searchView.setQueryHint("add city");

        try {
            Field field = searchView.getClass().getDeclaredField("mSubmitButton");
            field.setAccessible(true);
            ImageView iv = (ImageView) field.get(searchView);
            iv.setImageDrawable(this.getResources().getDrawable(R.drawable.add));
        } catch (Exception e) {
            e.printStackTrace();
        }

        /****************************************************************************************************************************************************
         * 数据库中取数据
         */
        Cursor cursor = this.getTestCursor();

        @SuppressWarnings("deprecation")
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
                R.layout.search_city_text_view, cursor, new String[] { "tb_name" },
                new int[] { R.id.textview });
        searchView.setSuggestionsAdapter(adapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextChange(String str) {
                return false;
            }
            @Override
            public boolean onQueryTextSubmit(String str) {
                //Toast.makeText(CityAddActivity.this, str, Toast.LENGTH_SHORT).show();
                //添加该城市到数据库
                return false;
            }
        });
    }

    //添加suggestion需要的数据，调试用
    public Cursor getTestCursor() {
        SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(
                this.getFilesDir() + "/my.db3", null);
        Cursor cursor = null;
        try {
            String insertSql = "insert into tb_test values (null,?,?)";
            db.execSQL(insertSql, new Object[] { "aa", 1 });
            db.execSQL(insertSql, new Object[] { "ab", 2 });
            db.execSQL(insertSql, new Object[] { "ac", 3 });
            db.execSQL(insertSql, new Object[] { "ad", 4 });
            db.execSQL(insertSql, new Object[] { "ae", 5 });
            String querySql = "select * from tb_test";
            cursor = db.rawQuery(querySql, null);
        } catch (Exception e) {
            String sql = "create table tb_test (_id integer primary key autoincrement,tb_name varchar(20),tb_age integer)";
            db.execSQL(sql);
            String insertSql = "insert into tb_test values (null,?,?)";
            db.execSQL(insertSql, new Object[] { "aa", 1 });
            db.execSQL(insertSql, new Object[] { "ab", 2 });
            db.execSQL(insertSql, new Object[] { "ac", 3 });
            db.execSQL(insertSql, new Object[] { "ad", 4 });
            db.execSQL(insertSql, new Object[] { "ae", 5 });
            String querySql = "select * from tb_test";
            cursor = db.rawQuery(querySql, null);
        }
        return cursor;
    }
}
