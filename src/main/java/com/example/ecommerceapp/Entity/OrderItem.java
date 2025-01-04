package com.example.ecommerceapp.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @JsonIgnore
    @ManyToOne
    private  Order order;

    @ManyToOne
    private Product product;

    private String size;

    private BigDecimal price;

    private int quantity;

    private Long userId;

    private BigDecimal discountedPrice;

    private LocalDateTime deliveryDate;

    @Override
    public String toString() {
        return "OrderItem{" +
                "Id=" + Id +
                ", order=" + order +
                ", product=" + product +
                ", size='" + size + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", userId=" + userId +
                ", discountedPrice=" + discountedPrice +
                ", deliveryDate=" + deliveryDate +
                '}';
    }

}
