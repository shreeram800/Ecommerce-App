package com.example.ecommerceapp.Service;

import com.example.ecommerceapp.Entity.User;
import com.example.ecommerceapp.requests.AddCustomerRequest;
import com.example.ecommerceapp.requests.UpdateCustomerRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CustomerService {

    List<User> getAllCustomer();

    User getCustomerById(Long id);

    User updateCustomer(UpdateCustomerRequest request, Long id);

    User addCustomer(AddCustomerRequest customer);
    ResponseEntity<?> deleteCustomer(Long id);
}
