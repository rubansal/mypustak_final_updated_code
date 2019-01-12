package com.mypushtak.app.Helper;

public class City {
    public static final String TABLE_NAME="cities";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_STATE_ID = "state_id";
    public static final String COLUMN_CITY = "city";

    private int id;
    private int state_id;
    private String city;

    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_STATE_ID + " INTEGER,"
                    + COLUMN_CITY + " TEXT"
                    + ")";

    public City() {

    }

    @Override
    public String toString() {
        return this.city;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getState_id() {
        return state_id;
    }

    public void setState_id(int state_id) {
        this.state_id = state_id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
