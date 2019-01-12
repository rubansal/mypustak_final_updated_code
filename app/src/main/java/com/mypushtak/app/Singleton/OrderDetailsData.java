package com.mypushtak.app.Singleton;

public class OrderDetailsData {

    private int book_id;
    private int book_inv_id;
    private int coupon_amount;
    private int cod_charge;
    private int order_book_id;
    private int order_id;
    private double i_date;
    private int amount;
    private String payusing;
    private int fast_delivery;
    private int qty;
    private int no_of_book;
    private int shipping_cost;
    private String title;
    private int price;
    private String thumb;

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public int getBook_inv_id() {
        return book_inv_id;
    }

    public void setBook_inv_id(int book_inv_id) {
        this.book_inv_id = book_inv_id;
    }

    public int getCoupon_amount() {
        return coupon_amount;
    }

    public void setCoupon_amount(int coupon_amount) {
        this.coupon_amount = coupon_amount;
    }

    public int getCod_charge() {
        return cod_charge;
    }

    public void setCod_charge(int cod_charge) {
        this.cod_charge = cod_charge;
    }

    public int getOrder_book_id() {
        return order_book_id;
    }

    public void setOrder_book_id(int order_book_id) {
        this.order_book_id = order_book_id;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public double getI_date() {
        return i_date;
    }

    public void setI_date(double i_date) {
        this.i_date = i_date;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getPayusing() {
        return payusing;
    }

    public void setPayusing(String payusing) {
        this.payusing = payusing;
    }

    public int getFast_delivery() {
        return fast_delivery;
    }

    public void setFast_delivery(int fast_delivery) {
        this.fast_delivery = fast_delivery;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getNo_of_book() {
        return no_of_book;
    }

    public void setNo_of_book(int no_of_book) {
        this.no_of_book = no_of_book;
    }

    public int getShipping_cost() {
        return shipping_cost;
    }

    public void setShipping_cost(int shipping_cost) {
        this.shipping_cost = shipping_cost;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getBook_condition() {
        return book_condition;
    }

    public void setBook_condition(String book_condition) {
        this.book_condition = book_condition;
    }

    public int getHandling_charge() {
        return handling_charge;
    }

    public void setHandling_charge(int handling_charge) {
        this.handling_charge = handling_charge;
    }

    public String getRec_name() {
        return rec_name;
    }

    public void setRec_name(String rec_name) {
        this.rec_name = rec_name;
    }

    public double getPincode() {
        return pincode;
    }

    public void setPincode(double pincode) {
        this.pincode = pincode;
    }

    public double getPhone_no() {
        return phone_no;
    }

    public void setPhone_no(double phone_no) {
        this.phone_no = phone_no;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLandmark() {
        return landmark;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
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

    private String book_condition;
    private int handling_charge;
    private String rec_name;
    private double pincode;
    private double phone_no;
    private String address;
    private String landmark;
    private String country;
    private String city;
    private String state_name;

    public OrderDetailsData() {
    }

    public OrderDetailsData(int book_id, int book_inv_id, int coupon_amount, int cod_charge, int order_book_id, int order_id, double i_date, int amount, String payusing, int fast_delivery, int qty, int no_of_book, int shipping_cost, String title, int price, String thumb, String book_condition, int handling_charge, String rec_name, double pincode, double phone_no, String address, String landmark, String country, String city, String state_name) {

        this.book_id = book_id;
        this.book_inv_id = book_inv_id;
        this.coupon_amount = coupon_amount;
        this.cod_charge = cod_charge;
        this.order_book_id = order_book_id;
        this.order_id = order_id;
        this.i_date = i_date;
        this.amount = amount;
        this.payusing = payusing;
        this.fast_delivery = fast_delivery;
        this.qty = qty;
        this.no_of_book = no_of_book;
        this.shipping_cost = shipping_cost;
        this.title = title;
        this.price = price;
        this.thumb = thumb;
        this.book_condition = book_condition;
        this.handling_charge = handling_charge;
        this.rec_name = rec_name;
        this.pincode = pincode;
        this.phone_no = phone_no;
        this.address = address;
        this.landmark = landmark;
        this.country = country;
        this.city = city;
        this.state_name = state_name;
    }
}
