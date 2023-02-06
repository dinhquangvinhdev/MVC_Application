package com.example.mvc_application.model;

import com.google.gson.annotations.SerializedName;

public class Name {
    @SerializedName("common")
    private String common;

    public Name(String commom) {
        this.common = commom;
    }

    public String getCommon() {
        return common;
    }

    public void setCommon(String common) {
        this.common = common;
    }

    @Override
    public String toString() {
        return "Name{" +
                "commom='" + common + '\'' +
                '}';
    }
}
