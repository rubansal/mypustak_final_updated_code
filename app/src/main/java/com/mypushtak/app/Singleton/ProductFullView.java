package com.mypushtak.app.Singleton;

public class ProductFullView {
    public int book_id;
    public String title;
    public String thumb;
    public String author;
    public float shipping_cost;
    public int book_inv_id;
    public int donation_req_id;
    public int donor_id;
    public String first_name;
    public String last_name;
    public int book_price;
    public int handelling_charge;

    public int getHandelling_charge() {
        return handelling_charge;
    }

    public void setHandelling_charge(int handelling_charge) {
        this.handelling_charge = handelling_charge;
    }

    public int getBook_price() {

        return book_price;
    }

    public void setBook_price(int book_price) {
        this.book_price = book_price;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public float getShipping_cost() {
        return shipping_cost;
    }

    public void setShipping_cost(float shipping_cost) {
        this.shipping_cost = shipping_cost;
    }

    public int getBook_inv_id() {
        return book_inv_id;
    }

    public void setBook_inv_id(int book_inv_id) {
        this.book_inv_id = book_inv_id;
    }

    public int getDonation_req_id() {
        return donation_req_id;
    }

    public void setDonation_req_id(int donation_req_id) {
        this.donation_req_id = donation_req_id;
    }

    public int getDonor_id() {
        return donor_id;
    }

    public void setDonor_id(int donor_id) {
        this.donor_id = donor_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public ProductFullView() {

    }

    public ProductFullView(int book_id, String title, String thumb, String author, float shipping_cost,int handelling_charge, int book_inv_id, int donation_req_id, int donor_id, String first_name, String last_name,int book_price) {

        this.book_id = book_id;
        this.title = title;
        this.thumb = thumb;
        this.author = author;
        this.shipping_cost = shipping_cost;
        this.book_inv_id = book_inv_id;
        this.donation_req_id = donation_req_id;
        this.donor_id = donor_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.book_price=book_price;
        this.handelling_charge=handelling_charge;
    }
}
