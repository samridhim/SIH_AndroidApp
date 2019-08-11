package com.nullstop.activity.model;

import com.google.gson.annotations.SerializedName;

public class NumberInstallments {
@SerializedName("user_id")
    public Integer user_id;

@SerializedName("number_of_installments")
    public Integer number_of_installments;

    public NumberInstallments(Integer user_id, Integer number_of_installments) {
        this.user_id = user_id;
        this.number_of_installments = number_of_installments;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getNumber_of_installments() {
        return number_of_installments;
    }

    public void setNumber_of_installments(Integer number_of_installments) {
        this.number_of_installments = number_of_installments;
    }
}
