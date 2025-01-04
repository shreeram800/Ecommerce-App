package com.example.ecommerceapp.Repository;

import com.example.ecommerceapp.Entity.Cart;
import com.example.ecommerceapp.Entity.CartItem;
import com.example.ecommerceapp.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    @Query("SELECT ci FROM CartItem ci WHERE ci.cart = :cart AND ci.product = :product AND ci.size = :size AND ci.userId= :user_id")
    Optional<CartItem> isCartItemExist(
            @Param("cart") Cart cart,
            @Param("product") Product product,
            @Param("size") String size,
            @Param("user_id") Long userId
    );

}
