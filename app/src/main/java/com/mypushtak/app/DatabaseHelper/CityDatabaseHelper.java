package com.mypushtak.app.DatabaseHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.mypushtak.app.Helper.City;

import java.util.ArrayList;
import java.util.List;

public class CityDatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "cities_db";

    public CityDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(City.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+City.TABLE_NAME);
        onCreate(db);
    }

    public long insertCity(String city, int state_id) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        // `id` and `timestamp` will be inserted automatically.
        // no need to add them
        values.put(City.COLUMN_CITY, city);
        values.put(City.COLUMN_STATE_ID, state_id);

        // insert row
        long id = db.insert(City.TABLE_NAME, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }

    public List<City> getCity(long id) {
        // get readable database as we are not inserting anything

        List<City> cities=new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(City.TABLE_NAME,
                new String[]{City.COLUMN_ID, City.COLUMN_STATE_ID, City.COLUMN_CITY},
                City.COLUMN_STATE_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if(cursor.moveToNext()){
            do {
                City city = new City();
                city.setId(cursor.getInt(cursor.getColumnIndex(City.COLUMN_ID)));
                city.setState_id(cursor.getInt(cursor.getColumnIndex(City.COLUMN_STATE_ID)));
                city.setCity(cursor.getString(cursor.getColumnIndex(City.COLUMN_CITY)));

                cities.add(city);
            } while (cursor.moveToNext());
        }

        // close the db connection
        cursor.close();

        return cities;
    }

    public List<City> getAllCities() {
        List<City> cities = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + City.TABLE_NAME + " ORDER BY " +
                City.COLUMN_ID + " DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                City city = new City();
                city.setId(cursor.getInt(cursor.getColumnIndex(City.COLUMN_ID)));
                city.setCity(cursor.getString(cursor.getColumnIndex(City.COLUMN_CITY)));
                city.setState_id(cursor.getInt(cursor.getColumnIndex(City.COLUMN_STATE_ID)));

                cities.add(city);
            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return notes list
        return cities;
    }

    public int getCitiesCount() {
        String countQuery = "SELECT  * FROM " + City.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();


        // return count
        return count;
    }
}
