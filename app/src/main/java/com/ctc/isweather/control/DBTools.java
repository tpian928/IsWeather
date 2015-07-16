package com.ctc.isweather.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by chris on 2015/7/15.
 */
public class DBTools {

    public static final String DBPATH = "/sdcard";
    public static final String DBNAME = "weatherSys.db";

    /**
     * insert single data into table conCity
     * 
     * @param db database 
     * @param id city id 
     */
    public static void insertIntoConcity(SQLiteDatabase db, int id) {
        String sqlInsert = "insert into conCity values(" + id + ")";
        db.execSQL(sqlInsert);
    }


    /**
     * import existed database to /sdcard
	 * this.getApplicationContext().getResource().openResource(R.raw.);
     */
    public static void importDB(InputStream input) {
		// store database in the sd card
        String dbpath = "/sdcard";
        File dir = new File(dbpath);
        if (!dir.exists()) {
            dir.mkdir();
        }

        File file = new File(dir, DBNAME);
        try {
            if (!file.exists()) {
                file.createNewFile();

                // read
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
     * Database in /sdcard/weatherSys.db
     *
     * @return Writable database
     */
    public static SQLiteDatabase openDatabase() {
        return SQLiteDatabase.openDatabase(DBPATH + "/" + DBNAME, null, SQLiteDatabase.OPEN_READWRITE);
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
        String sql = "select id from city,conCity where city.id = conCity.id and conCity.name=?";
        String[] s = {name};
        Cursor cursor = db.rawQuery(sql, s);
        return cursor.moveToFirst();
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
            String insert = "insert into conCity values(" + getIdFromCity(db, name) + ")";
            db.execSQL(insert);
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
            String delete = "delete from conCity where id=" + getIdFromCity(db, name);
            db.execSQL(delete);
            return true;
        } else {
            Log.i("chris", "The city has been deleted.");
            return false;
        }
    }


    /**
     * Get the id by cityname from table city
     *
     * @param db   database
     * @param name city name
     * @return the id of the city
     */
    public static int getIdFromCity(SQLiteDatabase db, String name) {
        String sqlSelect = "select id from city where name=?";
        String[] s = {name};
        Cursor cityCursor = db.rawQuery(sqlSelect, s);
        return cityCursor.getInt(0);
    }

}
