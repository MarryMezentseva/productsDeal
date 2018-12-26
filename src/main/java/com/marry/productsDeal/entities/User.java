package com.marry.productsDeal.entities;

import java.util.HashMap;
import java.util.Map;

public class User {
    private String name;
    private String address;
    Map <String, String> products = new HashMap<>();

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
}
