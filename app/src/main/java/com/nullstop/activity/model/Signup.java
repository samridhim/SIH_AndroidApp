package com.nullstop.activity.model;

import com.google.gson.annotations.SerializedName;

public class Signup {
    @SerializedName("email")
    public String email;

    @SerializedName("password")
    public String password;


    @SerializedName("fname")
    public String fname;


    @SerializedName("lname")
    public String lname;


    @SerializedName("age")
    public Integer age;

    @SerializedName("city")
    public String city;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getPincode() {
        return pincode;
    }

    public void setPincode(Integer pincode) {
        this.pincode = pincode;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @SerializedName("pincode")
    public Integer pincode;

    @SerializedName("phone")
    public Long phone;

    @SerializedName("gender")
    public String gender;

    @SerializedName("state")
    public String state;

    @SerializedName("address")
    public  String address;

    @SerializedName("phone_type")
    public String phone_type;


    public Signup(String email, String password, String fname, String lname, Integer age, String city, Integer pincode, Long phone, String gender, String state, String address, String phone_type) {
        this.email = email;
        this.password = password;
        this.fname = fname;
        this.lname = lname;
        this.age = age;
        this.city = city;
        this.pincode = pincode;
        this.phone = phone;
        this.gender = gender;
        this.state = state;
        this.address = address;
        this.phone_type = phone_type;
    }

    public String getPhone_type() {
        return phone_type;
    }

    public void setPhone_type(String phone_type) {
        this.phone_type = phone_type;
    }

}

