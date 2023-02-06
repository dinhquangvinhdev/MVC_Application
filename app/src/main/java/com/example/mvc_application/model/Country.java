package com.example.mvc_application.model;

import java.util.List;

public class Country {
    private Name name;
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
}
