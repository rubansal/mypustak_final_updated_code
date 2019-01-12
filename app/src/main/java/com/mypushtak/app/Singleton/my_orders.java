package com.mypushtak.app.Singleton;

public class my_orders {
    private long order_id;
    private long i_date;
    private int coupon_amount;
    private int status;
    private int book_id;
    private String thumb;
    private int mrp;
    private String payusing;

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

    private int fast_delivery;
    private int shipping_cost;
    private int handling_charge;

    public long getOrder_id() {
        return order_id;
    }

    public void setOrder_id(long order_id) {
        this.order_id = order_id;
    }

    public long getI_date() {
        return i_date;
    }

    public void setI_date(long i_date) {
        this.i_date = i_date;
    }

    public int getCoupon_amount() {
        return coupon_amount;
    }

    public void setCoupon_amount(int coupon_amount) {
        this.coupon_amount = coupon_amount;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public int getMrp() {
        return mrp;
    }

    public void setMrp(int mrp) {
        this.mrp = mrp;
    }

    public int getShipping_cost() {
        return shipping_cost;
    }

    public void setShipping_cost(int shipping_cost) {
        this.shipping_cost = shipping_cost;
    }

    public int getHandling_charge() {
        return handling_charge;
    }

    public void setHandling_charge(int handling_charge) {
        this.handling_charge = handling_charge;
    }

    public my_orders() {

    }

    public my_orders(long order_id, long i_date, int coupon_amount, String payusing, int status, int book_id, String thumb, int mrp,int fast_delivery, int shipping_cost, int handling_charge) {

        this.order_id = order_id;
        this.i_date = i_date;
        this.coupon_amount = coupon_amount;
        this.payusing=payusing;
        this.status = status;
        this.book_id = book_id;
        this.thumb = thumb;
        this.mrp = mrp;
        this.fast_delivery=fast_delivery;
        this.shipping_cost = shipping_cost;
        this.handling_charge = handling_charge;
    }
}
