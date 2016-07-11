package com.bambu.mobile.requestexample.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ken on 11/07/16.
 */
public class Pokemon {

    @SerializedName("name")
    @Expose
    String name;

    @SerializedName("weight")
    @Expose
    String weight;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Pokemon{" +
                "name='" + name + '\'' +
                ", weight='" + weight + '\'' +
                '}';
    }
}
