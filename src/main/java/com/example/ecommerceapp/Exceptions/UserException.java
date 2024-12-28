package com.example.ecommerceapp.Exceptions;

public class UserException extends Exception {
    public UserException(String emailAlreadyExists) {
        super(emailAlreadyExists);
    }
}
