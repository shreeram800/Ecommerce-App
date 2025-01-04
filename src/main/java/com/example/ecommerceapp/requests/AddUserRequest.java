package com.example.ecommerceapp.requests;

import com.example.ecommerceapp.Entity.Address;
import com.example.ecommerceapp.Entity.Enums.Role;
import lombok.Data;


@Data
public class AddUserRequest {

    private String name;

    private String surname;

    private int age;

    private String password;

    private Role role;

    private Address address;

    private String email;

    private String phoneNumber;

}
