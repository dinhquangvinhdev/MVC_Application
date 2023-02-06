package com.example.mvc_application.model;

public class Name {
    private String commom;

    public Name(String commom) {
        this.commom = commom;
    }

    public String getCommom() {
        return commom;
    }

    public void setCommom(String commom) {
        this.commom = commom;
    }

    @Override
    public String toString() {
        return "Name{" +
                "commom='" + commom + '\'' +
                '}';
    }
}
