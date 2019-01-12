package com.mypushtak.app.Singleton;

public class Delivery_Address {
    private int address_id;
    private int user_id;
    private String title;
    private String rec_name;
    private int pincode;
    private String address;
    private String landmarkh;
    private int country_id;
    private int state_id;
    private int city_id;
    private long phone_no;
    private String country;
    private String city;
    private String state_name;

    public int getAddress_id() {
        return address_id;
    }

    public void setAddress_id(int address_id) {
        this.address_id = address_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRec_name() {
        return rec_name;
    }

    public void setRec_name(String rec_name) {
        this.rec_name = rec_name;
    }

    public int getPincode() {
        return pincode;
    }

    public void setPincode(int pincode) {
        this.pincode = pincode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLandmarkh() {
        return landmarkh;
    }

    public void setLandmarkh(String landmarkh) {
        this.landmarkh = landmarkh;
    }

    public int getCountry_id() {
        return country_id;
    }

    public void setCountry_id(int country_id) {
        this.country_id = country_id;
    }

    public int getState_id() {
        return state_id;
    }

    public void setState_id(int state_id) {
        this.state_id = state_id;
    }

    public int getCity_id() {
        return city_id;
    }

    public void setCity_id(int city_id) {
        this.city_id = city_id;
    }

    public long getPhone_no() {
        return phone_no;
    }

    public void setPhone_no(long phone_no) {
        this.phone_no = phone_no;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState_name() {
        return state_name;
    }

    public void setState_name(String state_name) {
        this.state_name = state_name;
    }

    public Delivery_Address() {

    }

    public Delivery_Address(int address_id, int user_id, String title, String rec_name, int pincode, String address, String landmarkh, int country_id, int state_id, int city_id, long phone_no, String country, String city, String state_name) {

        this.address_id = address_id;
        this.user_id = user_id;
        this.title = title;
        this.rec_name = rec_name;
        this.pincode = pincode;
        this.address = address;
        this.landmarkh = landmarkh;
        this.country_id = country_id;
        this.state_id = state_id;
        this.city_id = city_id;
        this.phone_no = phone_no;
        this.country = country;
        this.city = city;
        this.state_name = state_name;
    }
}
