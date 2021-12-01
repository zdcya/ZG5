package com.bawei.home.entity;

public class Goods_sql {

    private String ShortTitle;
    private String PictUrl;
    private String ReservePrice;
    private Integer id;
    private int num = 1;
    private float priceSum;
    private  boolean isCheck =false;


    public Goods_sql(String shortTitle, String pictUrl, String reservePrice, Integer id) {
        ShortTitle = shortTitle;
        PictUrl = pictUrl;
        ReservePrice = reservePrice;
        this.id = id;
    }

    public Goods_sql() {
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public float getPriceSum() {

        float v = Float.parseFloat(this.getReservePrice());

        return (v*getNum());
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
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
