package com.bawei.usercenter;

public class UserEntity {

    private int id;
    private String phoneNumber;
    private String pwd;
    private int age;
    private String addres;

    public UserEntity() {
    }

    public UserEntity(int id, String phoneNumber, String pwd, int age, String addres) {
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.pwd = pwd;
        this.age = age;
        this.addres = addres;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddres() {
        return addres;
    }

    public void setAddres(String addres) {
        this.addres = addres;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", pwd='" + pwd + '\'' +
                ", age=" + age +
                ", addres='" + addres + '\'' +
                '}';
    }
}
