package com.mypushtak.app.Singleton;

public class ProductViewSingleton {

    private static String url;
    private static String category;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public ProductViewSingleton() {
    }

    public ProductViewSingleton(String url, String category) {
        this.url = url;
        this.category = category;
    }
}
