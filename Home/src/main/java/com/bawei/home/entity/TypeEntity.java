package com.bawei.home.entity;

public class TypeEntity {

//    {"id":5470,"category_id":16,"parent_id":0,"category_name":"女装/女士精品"}

    private int id;
    private int category_id;
    private int parent_id;
    private String category_name;

    public TypeEntity() {
    }

    public TypeEntity(int id, int category_id, int parent_id, String category_name) {
        this.id = id;
        this.category_id = category_id;
        this.parent_id = parent_id;
        this.category_name = category_name;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public int getParent_id() {
        return parent_id;
    }

    public void setParent_id(int parent_id) {
        this.parent_id = parent_id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }
}
