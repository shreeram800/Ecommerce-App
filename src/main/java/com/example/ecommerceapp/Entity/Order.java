package com.example.ecommerceapp.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customer_order")
public class Order {

    @Id
    private Long orderId;

    private String address;

    private String phoneNumber;
    
    @ManyToMany
    @JoinTable(
        name = "order_products",
        joinColumns = @JoinColumn(name = "order_id"),
        inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> products;

    private String status;

    private Long zipCode;
    
    private Long totalPrice;
    
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    

}
