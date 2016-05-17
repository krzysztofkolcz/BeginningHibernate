package com.onlinetechvision.model;

import java.io.Serializable;

public class Order implements Serializable {

    private static final long serialVersionUID = -2138235868650860555L;
    private int id;
    private String name;

    public Order(int id, String name) {
        this.id = id;
        this.name = name;
    }

    //Getter and Setter Methods...

    @Override
    public String toString() {
        return "Order [id=" + id + ", name=" + name + "]";
    }

}
