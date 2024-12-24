package com.example.ecommerceapp.requests;

import lombok.Data;

@Data
public class AddCustomerRequest {

    private String name;

    private String email;

    private String phoneNumber;

    private String address;

    private String surname;

    private String age;
}
