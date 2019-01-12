package com.mypushtak.app.DatabaseHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.mypushtak.app.Helper.Zipcode;

public class ZipcodeDatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "zipcodes_db";

    public ZipcodeDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Zipcode.CREATE_TABLE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+Zipcode.TABLE_NAME);
        onCreate(db);
    }

    public long insertZipcode(String zipcode) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        // `id` and `timestamp` will be inserted automatically.
        // no need to add them
        values.put(Zipcode.COLUMN_ZIPCODE, zipcode);

        // insert row
        long id = db.insert(Zipcode.TABLE_NAME, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }

    public Boolean getSearch(String zipcode){
        SQLiteDatabase db=this.getReadableDatabase();
        String query="SELECT * FROM "
                + Zipcode.TABLE_NAME
                + " where " + Zipcode.COLUMN_ZIPCODE + " = " + zipcode;
        Cursor cursor=db.rawQuery(query,null);

        if(cursor.getCount()<=0){
            cursor.close();
            return false;
        }
        cursor.close();
        return true;

    }

    public int getZipcodeCount() {
        String countQuery = "SELECT  * FROM " + Zipcode.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();


        // return count
        return count;
    }
}
