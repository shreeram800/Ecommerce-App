package com.example.ecommerceapp.Repository;

import com.example.ecommerceapp.Entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address,Long> {
}
