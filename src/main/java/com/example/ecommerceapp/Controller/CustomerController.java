package com.example.ecommerceapp.Controller;

import com.example.ecommerceapp.requests.AddCustomerRequest;
import com.example.ecommerceapp.Entity.Customer;
import com.example.ecommerceapp.Service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("api/v1/customer")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("s")
    public ResponseEntity<List<Customer>> getAllCustomer(){
        return ResponseEntity.ok(customerService.getAllCustomer());
    }

    public ResponseEntity<Customer> getCustomerById(Long id){
        return ResponseEntity.ok(customerService.getCustomerById(id));
    }
    @PostMapping
    public ResponseEntity<Customer> registerCustomer(AddCustomerRequest customer){
        return ResponseEntity.ok(customerService.addCustomer(customer));
    }


}
