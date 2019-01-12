package com.mypushtak.app.DatabaseHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.mypushtak.app.Helper.State;

import java.util.ArrayList;
import java.util.List;

public class StateDatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "states_db";

    public StateDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(State.CREATE_TABLE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+State.TABLE_NAME);
        onCreate(db);
    }

    public long insertNote(String state_name) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        // `id` and `timestamp` will be inserted automatically.
        // no need to add them
        values.put(State.COLUMN_STATE_NAME, state_name);

        // insert row
        long id = db.insert(State.TABLE_NAME, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }

    public State getNote(long id) {
        // get readable database as we are not inserting anything
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(State.TABLE_NAME,
                new String[]{State.COLUMN_ID, State.COLUMN_STATE_NAME},
                State.COLUMN_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        // prepare note object
        State state = new State(
                cursor.getInt(cursor.getColumnIndex(State.COLUMN_ID)),
                cursor.getString(cursor.getColumnIndex(State.COLUMN_STATE_NAME)));

        // close the db connection
        cursor.close();

        return state;
    }

    public List<State> getAllStates() {
        List<State> states = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + State.TABLE_NAME + " ORDER BY " +
                State.COLUMN_ID + " DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                State state = new State();
                state.setId(cursor.getInt(cursor.getColumnIndex(State.COLUMN_ID)));
                state.setState_name(cursor.getString(cursor.getColumnIndex(State.COLUMN_STATE_NAME)));

                states.add(state);
            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return notes list
        return states;
    }

    public int getStatesCount() {
        String countQuery = "SELECT  * FROM " + State.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();


        // return count
        return count;
    }
}
