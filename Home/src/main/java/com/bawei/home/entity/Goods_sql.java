package com.bawei.home.entity;

public class Goods_sql {

    private String ShortTitle;
    private String PictUrl;
    private String ReservePrice;

    public Goods_sql(String shortTitle, String pictUrl, String reservePrice) {
        ShortTitle = shortTitle;
        PictUrl = pictUrl;
        ReservePrice = reservePrice;
    }


    public Goods_sql() {
    }

    public String getShortTitle() {
        return ShortTitle;
    }

    public void setShortTitle(String shortTitle) {
        ShortTitle = shortTitle;
    }

    public String getPictUrl() {
        return PictUrl;
    }

    public void setPictUrl(String pictUrl) {
        PictUrl = pictUrl;
    }

    public String getReservePrice() {
        return ReservePrice;
    }

    public void setReservePrice(String reservePrice) {
        ReservePrice = reservePrice;
    }
}
