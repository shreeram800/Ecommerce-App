package com.example.ecommerceapp.Repository;

import com.example.ecommerceapp.Entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
