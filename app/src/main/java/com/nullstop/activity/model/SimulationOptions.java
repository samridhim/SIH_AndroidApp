package com.nullstop.activity.model;

import com.google.gson.annotations.SerializedName;

public class SimulationOptions {
    @SerializedName("user_id")
    public  Integer user_id;

    @SerializedName("simulation_option")
    public Integer simulation_option;

    @SerializedName("simulation_sub_option")
    public Integer simulation_sub_option;


    public SimulationOptions(Integer user_id, Integer simulation_option, Integer simulation_sub_option) {
        this.user_id = user_id;
        this.simulation_option = simulation_option;
        this.simulation_sub_option = simulation_sub_option;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getSimulation_option() {
        return simulation_option;
    }

    public void setSimulation_option(Integer simulation_option) {
        this.simulation_option = simulation_option;
    }

    public Integer getSimulation_sub_option() {
        return simulation_sub_option;
    }

    public void setSimulation_sub_option(Integer simulation_sub_option) {
        this.simulation_sub_option = simulation_sub_option;
    }
}
