package com.marry.productsDeal.exceptions;//move ito to .exceptions package

public class NonExistingProductException extends RuntimeException{
    public NonExistingProductException(String msg){
        super(msg);
    }
}
