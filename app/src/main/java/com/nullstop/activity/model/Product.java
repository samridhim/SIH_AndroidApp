package com.nullstop.activity.model;

import com.google.gson.annotations.SerializedName;

public class Product {
    @SerializedName("product_id")
    public Integer product_id;

    @SerializedName("product_name")
    public String product_name;

    @SerializedName("price")
    public Double product_cost;

    @SerializedName("quantity")
    public Integer product_quantity;

    public Product(Integer product_id, String product_name, Double product_cost, Integer product_quantity) {
        this.product_id = product_id;
        this.product_name = product_name;
        this.product_cost = product_cost;
        this.product_quantity = product_quantity;
    }

    public Integer getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Integer product_id) {
        this.product_id = product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public Double getProduct_cost() {
        return product_cost;
    }

    public void setProduct_cost(Double product_cost) {
        this.product_cost = product_cost;
    }

    public Integer getProduct_quantity() {
        return product_quantity;
    }

    public void setProduct_quantity(Integer product_quantity) {
        this.product_quantity = product_quantity;
    }


}
