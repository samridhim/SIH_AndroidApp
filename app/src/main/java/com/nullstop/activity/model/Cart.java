package com.nullstop.activity.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Cart {
    @SerializedName("user_id")
    public Integer user_id;
    @SerializedName("product_list")
    public ArrayList<Product> products;

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public Cart(Integer user_id, ArrayList<Product> products) {
        this.user_id = user_id;
        this.products = products;
    }
}
