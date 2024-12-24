package com.example.ecommerceapp.Repository;

import com.example.ecommerceapp.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepo extends JpaRepository<Customer,Long> {
}
