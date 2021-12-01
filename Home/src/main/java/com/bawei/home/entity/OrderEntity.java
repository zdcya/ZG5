package com.bawei.home.entity;

import java.util.List;

public class OrderEntity {

    private Integer id;
    private List<Goods_sql> order_goods;
    private Integer isPay = 0;
    private Float paySum;


    public OrderEntity(Integer id, List<Goods_sql> order_goods, Integer isPay, Float paySum) {
        this.id = id;
        this.order_goods = order_goods;
        this.isPay = isPay;
        this.paySum = paySum;
    }

    public OrderEntity() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Goods_sql> getOrder_goods() {
        return order_goods;
    }

    public void setOrder_goods(List<Goods_sql> order_goods) {
        this.order_goods = order_goods;
    }

    public Integer isPay() {
        return isPay;
    }

    public void setPay(Integer pay) {
        isPay = pay;
    }

    public Float getPaySum() {
        return paySum;
    }

    public void setPaySum(Float paySum) {
        this.paySum = paySum;
    }
}
