package com.mypushtak.app.Singleton;

public class CartItems {

    private int book_id;
    private int price;
    private String title;
    private String thumb;
    private String author;
    private int qty;
    private int shipping;
    public int handelling;

    public int getHandelling() {
        return handelling;
    }

    public void setHandelling(int handelling) {
        this.handelling = handelling;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
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

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getShipping() {
        return shipping;
    }

    public void setShipping(int shipping) {
        this.shipping = shipping;
    }

    public CartItems() {

    }

    public CartItems(int book_id, int price, String title, String thumb, String author, int qty, int shipping, int handelling) {

        this.book_id = book_id;
        this.price = price;
        this.title = title;
        this.thumb = thumb;
        this.author = author;
        this.qty = qty;
        this.shipping = shipping;
        this.handelling=handelling;
    }
}
