package com.example.ecommerceapp.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "cart")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "cart_id")
    private List<Product> products;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private User customer;


    @Column(nullable = false, name = "created_at")
    private LocalDateTime createdAt;


    @Column(name = "total_price")
    private Double totalPrice;

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", products=" + products +
                ", customer=" + customer +
                ", createdAt=" + createdAt +
                ", totalPrice=" + totalPrice +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cart cart = (Cart) o;
        return Objects.equals(id, cart.id) && Objects.equals(products, cart.products) && Objects.equals(customer, cart.customer) && Objects.equals(createdAt, cart.createdAt) && Objects.equals(totalPrice, cart.totalPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, products, customer, createdAt, totalPrice);
    }

}
