package com.mypushtak.app.Helper;

public class Zipcode {
    public static final String TABLE_NAME = "zipcodes";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_ZIPCODE = "zipcode";

    private int id;
    private String zipcode;

    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_ZIPCODE + " TEXT"
                    + ")";

    public Zipcode(){

    }

    @Override
    public String toString() {
        return this.zipcode;
    }

    public Zipcode(int id, String zipcode){
        this.id=id;
        this.zipcode=zipcode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }
}
