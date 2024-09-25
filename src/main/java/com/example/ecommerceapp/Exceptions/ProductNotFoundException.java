package com.example.ecommerceapp.Exceptions;



public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String message){
        super(message);
    }
}
