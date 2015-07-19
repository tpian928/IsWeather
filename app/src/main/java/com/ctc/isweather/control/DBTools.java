package com.ctc.isweather.control;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.ctc.isweather.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by chris on 2015/7/15.
 */
public class DBTools {

    public static final String DBNAME = "weathersys.db";


    /**
     * import existed database to /sdcard
     * this.getApplicationContext().getResource().openResource(R.raw.);
     */
    public static void importDB(Activity activity) {
        // store database in the sd card
        String dbpath = "/data/data/" + activity.getPackageName() + "/databases";
        File dir = new File(dbpath);
        if (!dir.exists()) {
            dir.mkdir();
        }

        File file = new File(dir, DBNAME);
        try {
            if (!file.exists()) {
                file.createNewFile();

                // read
                InputStream input = activity.getApplicationContext().getResources().openRawResource(R.raw.weathersys);
                byte[] buffer = new byte[input.available()];
                input.read(buffer);
                input.close();

                Log.i("chris", "read");
                // write
                FileOutputStream out = new FileOutputStream(file);
                out.write(buffer);
                out.flush();
                out.close();

                Log.i("chris", "import database");
            } else {
                Log.i("chris", "database has existed");
            }
        } catch (IOException e) {
            Log.i("chris", "import error");
        }
    }


    /**
     * Database in /sdcard/weathersys.db
     *
     * @return Writable database
     */
    public static SQLiteDatabase openDatabase(String name) {
        return SQLiteDatabase.openDatabase("/data/data/" + name + "/databases/" + DBNAME, null, SQLiteDatabase.OPEN_READWRITE);
    }

    public static SQLiteDatabase openDatabase() {
        return SQLiteDatabase.openDatabase("/data/data/com.ctc.isweather/databases/" + DBNAME, null, SQLiteDatabase.OPEN_READWRITE);
    }

    /**
     * Check whether the record has been existed in Table City.
     *
     * @param db   database
     * @param name cityname
     * @return true, exists; false
     */
    public static boolean existInCity(SQLiteDatabase db, String name) {
        String sql = "select id from city where name=?";
        String[] s = {name};
        Cursor cursor = db.rawQuery(sql, s);
        return cursor.moveToFirst();
    }

    /**
     * Check whether the record has been existed in Table conCity;
     *
     * @param db   database
     * @param name cityname
     * @return true, exists; false doesnt exist;
     */
    public static boolean existInConCity(SQLiteDatabase db, String name) {
        // get the list of city
        ArrayList<String> list = QueryInConcity(db);
        if (list.size() == 0) {
            return false;
        }

        for (String city : list) {
            if (city.compareTo(name) == 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * insert the concerned city into Table conCity
     *
     * @param db   database
     * @param name cityname
     * @return true, insert ok!; false; fail
     */
    public static boolean insertInConcity(SQLiteDatabase db, String name) {

        if (!existInConCity(db, name)) {
            String insert = "insert into concity values(" + getIdFromCity(db, name) + ")";
            db.execSQL(insert);
            Log.i("chris", "The city is inserted!");
            return true;
        } else {
            Log.i("chris", "The city has been concerned!");
            return false;
        }
    }

    /**
     * delete the concerned city into Table conCity
     *
     * @param db   database
     * @param name citynem
     * @return true, delete ok!; false; fail
     */
    public static boolean deleteInConcity(SQLiteDatabase db, String name) {
        if (existInConCity(db, name)) {
            String delete = "delete from concity where id=" + getIdFromCity(db, name);
            db.execSQL(delete);
            Log.i("chris","The city is deleted!");
            return true;
        } else {
            Log.i("chris", "The city has been deleted.");
            return false;
        }
    }


    public static boolean deleteInConcity(String name) {
        SQLiteDatabase db = openDatabase();
        if (existInConCity(db, name)) {
            String delete = "delete from concity where id=" + getIdFromCity(db, name);
            db.execSQL(delete);
            Log.i("chris","The city is deleted!");
            return true;
        } else {
            Log.i("chris", "The city has been deleted.");
            return false;
        }
    }


    /**
     * get all the cities from the table conCity
     *
     * @param db database
     * @return the list of cities.
     */
    public static ArrayList<String> QueryInConcity(SQLiteDatabase db) {
        String sql = "select name from city, concity where city.id = concity.id";
        Cursor cursor = db.rawQuery(sql, null);
        ArrayList<String> cities = new ArrayList<String>();
        while(cursor.moveToNext()) {
            cities.add(cursor.getString(0));
            Log.i("chris","query: " + cursor.getString(0));
        }

        return cities;
    }

    public static ArrayList<String> QueryInConcity() {
        SQLiteDatabase db = openDatabase();
        String sql = "select name from city, concity where city.id = concity.id";
        Cursor cursor = db.rawQuery(sql, null);
        ArrayList<String> cities = new ArrayList<String>();
        while(cursor.moveToNext()) {
            cities.add(cursor.getString(0));
            Log.i("chris","query: " + cursor.getString(0));
        }

        return cities;
    }

    /**
     * Get the id by cityname from table city
     *
     * @param db   database
     * @param name city name
     * @return the id of the city
     */
    public static int getIdFromCity(SQLiteDatabase db, String name) {
        String sqlSelect = "select id from city where name='" + name+ "'";
        Cursor cityCursor = db.rawQuery(sqlSelect, null);
        int id = -1;
        System.out.println("count:"+cityCursor.getCount());
        while(cityCursor.moveToNext()){
            id =  cityCursor.getInt(0);
        }
        return id;
    }

    public static int getIdFromCity(String name) {
        SQLiteDatabase db = openDatabase();
        String sqlSelect = "select id from city where name='" + name+ "'";
        Cursor cityCursor = db.rawQuery(sqlSelect, null);
        int id = -1;
        System.out.println("count:"+cityCursor.getCount());
        while(cityCursor.moveToNext()){
            id =  cityCursor.getInt(0);
        }
        return id;
    }
}
