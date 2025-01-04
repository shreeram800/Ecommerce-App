package com.example.ecommerceapp.Service;

import com.example.ecommerceapp.Entity.Cart;
import com.example.ecommerceapp.Entity.User;
import com.example.ecommerceapp.Exceptions.ProductNotFoundException;
import com.example.ecommerceapp.requests.AddItemRequest;

public interface CartService {
    Cart createCart(User user);

    String addCartItem(Long userId, AddItemRequest request) throws ProductNotFoundException;

    Cart findUserCart(Long userId);
}
