package com.marry.productsDeal.entities;

public class NonexistentProduct extends Exception{
    public NonexistentProduct(String msg){
        super(msg);
    }
}
