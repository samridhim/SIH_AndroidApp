package com.nullstop.activity.model;

import com.google.gson.annotations.SerializedName;

public class PaymentOptions {
    @SerializedName("user_id")
    public Integer user_id;

    @SerializedName("payment_option_id")
    public Integer payment_option_id;

    public PaymentOptions(Integer user_id, Integer payment_option_id) {
        this.user_id = user_id;
        this.payment_option_id = payment_option_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getPayment_option_id() {
        return payment_option_id;
    }

    public void setPayment_option_id(Integer payment_option_id) {
        this.payment_option_id = payment_option_id;
    }
}
