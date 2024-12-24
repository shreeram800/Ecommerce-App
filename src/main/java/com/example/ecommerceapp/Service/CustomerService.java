package com.example.ecommerceapp.Service;

import com.example.ecommerceapp.Entity.Customer;
import com.example.ecommerceapp.requests.AddCustomerRequest;
import com.example.ecommerceapp.requests.UpdateCustomerRequest;

import java.util.List;

public interface CustomerService {

    List<Customer> getAllCustomer();

    Customer getCustomerById(Long id);

    Customer updateCustomer(UpdateCustomerRequest request, Long id);

    Customer addCustomer(AddCustomerRequest customer);
}
