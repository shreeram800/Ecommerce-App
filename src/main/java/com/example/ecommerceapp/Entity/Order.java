package com.example.ecommerceapp.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "order_id")
    private Long orderId;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
    private List<OrderItem> items= new ArrayList<>();

    @Column(name = "status_id")
    private String status;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @Column(name = "total_price")
    private Long totalPrice;

    @Column(name = "order_date")
    private LocalDateTime orderDate;

    @Column(name = "delivery_date")
    private LocalDateTime deliveryDate;


    @Embedded
    private PaymentDetails paymentDetails = new PaymentDetails();

    @Column(name = "total_discounted_price")
    private BigDecimal totalDiscountedPrice;

    private Integer discount;

    @Column(name = "total_item")
    private int totalItem;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

}
