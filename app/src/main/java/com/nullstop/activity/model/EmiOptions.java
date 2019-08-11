package com.nullstop.activity.model;

import com.google.gson.annotations.SerializedName;

public class EmiOptions {
    @SerializedName("user_id")
    public Integer user_id;

    @SerializedName("months_3")
    public Integer months_3;

    @SerializedName("months_6")
    public Integer months_6;

    @SerializedName("months_12")
    public Integer months_12;


    public EmiOptions(Integer user_id, Integer months_3, Integer months_6, Integer months_12) {
        this.user_id = user_id;
        this.months_3 = months_3;
        this.months_6 = months_6;
        this.months_12 = months_12;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getMonths_3() {
        return months_3;
    }

    public void setMonths_3(Integer months_3) {
        this.months_3 = months_3;
    }

    public Integer getMonths_6() {
        return months_6;
    }

    public void setMonths_6(Integer months_6) {
        this.months_6 = months_6;
    }

    public Integer getMonths_12() {
        return months_12;
    }

    public void setMonths_12(Integer months_12) {
        this.months_12 = months_12;
    }
}
