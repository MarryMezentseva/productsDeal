package com.marry.productsDeal.entities;

import java.util.HashMap;
import java.util.Map;

public class User {
    private String name;
    private final Map<String, String> properties = new HashMap<>();


    public void setAddress(String address) {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, String> getProperties() {
        return properties;
    }
}
