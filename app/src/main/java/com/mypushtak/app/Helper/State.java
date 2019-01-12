package com.mypushtak.app.Helper;

public class State {
    public static final String TABLE_NAME = "states";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_STATE_NAME = "state_name";

    private int id;
    private String state_name;

    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_STATE_NAME + " TEXT"
                    + ")";

    public State(){

    }

    @Override
    public String toString() {
        return this.state_name;
    }

    public State(int id, String state_name){
        this.id=id;
        this.state_name=state_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getState_name() {
        return state_name;
    }

    public void setState_name(String state_name) {
        this.state_name = state_name;
    }
}
