package com.example.mvc_application.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Country {
    @SerializedName("name")
    private Name name;
    @SerializedName("capital")
    private List<String> capacity;

    public Country(Name name, List<String> capacity) {
        this.name = name;
        this.capacity = capacity;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public List<String> getCapacity() {
        return capacity;
    }

    public void setCapacity(List<String> capacity) {
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return "Country{" +
                "name=" + name +
                ", capacity=" + capacity +
                '}';
    }
}
