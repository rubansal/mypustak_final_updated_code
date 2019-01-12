package com.mypushtak.app.Bean;

public class ConstantUrl {

    public static String URL="http://192.168.43.243:8080/";
    public static int full_product_view_price;

    public static int getFull_product_view_price() {
        return full_product_view_price;
    }

    public static void setFull_product_view_price(int full_product_view_price) {
        ConstantUrl.full_product_view_price = full_product_view_price;
    }
}
