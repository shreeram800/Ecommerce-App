package com.example.ecommerceapp.requests;

import com.example.ecommerceapp.Entity.Enums.Age;
import lombok.Data;

@Data
public class UpdateCustomerRequest {

    private String name;

    private String email;

    private String phoneNumber;

    private String address;

    private String surname;

    private Age age;

}
