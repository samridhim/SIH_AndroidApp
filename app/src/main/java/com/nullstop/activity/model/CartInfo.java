package com.nullstop.activity.model;

import com.google.gson.annotations.SerializedName;

public class CartInfo {
    @SerializedName("current_credit_score")
    public Float credit_score;

    @SerializedName("quantity")
    public Integer quantity;

    @SerializedName("number_of_transactions")
    public Integer transactions;

    @SerializedName("current_buyer_profile_score")
    public  Float buyers_score;

    public CartInfo(Float credit_score, Integer quantity, Integer transactions, Float buyers_score) {
        this.credit_score = credit_score;
        this.quantity = quantity;
        this.transactions = transactions;
        this.buyers_score = buyers_score;
    }

    public Float getCredit_score() {
        return credit_score;
    }

    public void setCredit_score(Float credit_score) {
        this.credit_score = credit_score;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getTransactions() {
        return transactions;
    }

    public void setTransactions(Integer transactions) {
        this.transactions = transactions;
    }

    public Float getBuyers_score() {
        return buyers_score;
    }

    public void setBuyers_score(Float buyers_score) {
        this.buyers_score = buyers_score;
    }
}
