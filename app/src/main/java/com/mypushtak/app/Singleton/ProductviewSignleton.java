package com.mypushtak.app.Singleton;

public class ProductviewSignleton {

    private  int book_id;
    private  String title;
    private  String thumb;
    private  String author;
    private  int price;
    private int category;
    private int discount;
    private String categoryname;

    public String getCategoryname() {
        return categoryname;
    }

    public void setCategoryname(String categoryname) {
        this.categoryname = categoryname;
    }

    public ProductviewSignleton(String categoryname) {
        this.categoryname = categoryname;
    }

    public ProductviewSignleton()
    {

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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public ProductviewSignleton(int book_id, String title, String thumb, String author, int price, int category, int discount,String categoryname) {

        this.book_id = book_id;
        this.title = title;
        this.thumb = thumb;
        this.author = author;
        this.price = price;
        this.category = category;
        this.discount = discount;
        this.categoryname=categoryname;
    }
}
