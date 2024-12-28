package com.example.ecommerceapp.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customer_order")
public class Order {

    @Id
    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "phone_number")
    private String phoneNumber;
    
    @ManyToMany
    @JoinTable(
        name = "order_products",
        joinColumns = @JoinColumn(name = "order_id"),
        inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> products;

    private String status;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @Column(name = "total_price")
    private Long totalPrice;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(orderId, order.orderId) && Objects.equals(phoneNumber, order.phoneNumber) && Objects.equals(products, order.products) && Objects.equals(status, order.status) && Objects.equals(address, order.address) && Objects.equals(totalPrice, order.totalPrice) && Objects.equals(user, order.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, phoneNumber, products, status, address, totalPrice, user);
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", products=" + products +
                ", status='" + status + '\'' +
                ", address=" + address +
                ", totalPrice=" + totalPrice +
                ", user=" + user +
                '}';
    }
}
