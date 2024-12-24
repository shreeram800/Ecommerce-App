package com.example.ecommerceapp.Service.implimentations;
import com.example.ecommerceapp.Entity.Customer;
import com.example.ecommerceapp.Repository.CustomerRepo;
import com.example.ecommerceapp.Service.CustomerService;
import com.example.ecommerceapp.requests.AddCustomerRequest;
import com.example.ecommerceapp.requests.UpdateCustomerRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CustomerServiceImp implements CustomerService {

    private final CustomerRepo customerRepo;

    public CustomerServiceImp(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }

    @Override
    public List<Customer> getAllCustomer() {
        return customerRepo.findAll();
    }

    @Override
    public Customer getCustomerById(Long id) {
        return customerRepo.findById(id).orElseThrow(()->new
                ResponseStatusException(HttpStatus.NOT_FOUND,
                            "Customer by id: "+ id + " does not found."));
    }
    @Override
    public Customer addCustomer(AddCustomerRequest request) {

        Customer customer= new Customer(request.getName(),
                request.getSurname(), request.getAddress(),request.getAge()
                ,request.getEmail(),request.getPhoneNumber());

        return customerRepo.save(customer);
    }
    @Override
    public Customer updateCustomer(UpdateCustomerRequest request, Long id) {
        if(!customerRepo.existsById(id)){
            throw new RuntimeException("No customer found by: "+ id);
        }
        Customer newCustomer = getCustomerById(id);
        if(request.getAddress()!=null){
            newCustomer.setAddress(request.getAddress());
        }
        if (request.getName() != null) {
            newCustomer.setName(request.getName());
        }
        if (request.getSurname() != null) {
            newCustomer.setSurname(request.getSurname());
        }
        if (request.getAge() != null) {
            newCustomer.setAge(request.getAge());
        }
        if (request.getEmail() != null) {
            newCustomer.setEmail(request.getEmail());
        }
        if (request.getPhoneNumber() != null) {
            newCustomer.setPhoneNumber(request.getPhoneNumber());
        }
        return customerRepo.save(newCustomer);
    }

}
