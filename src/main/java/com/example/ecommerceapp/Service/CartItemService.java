package com.example.ecommerceapp.Service;

import com.example.ecommerceapp.Entity.Cart;
import com.example.ecommerceapp.Entity.CartItem;
import com.example.ecommerceapp.Entity.Product;
import com.example.ecommerceapp.Exceptions.CartItemException;
import com.example.ecommerceapp.Exceptions.UserException;

import java.util.Optional;

public interface CartItemService {

    CartItem createCartItem(CartItem item);

    CartItem updateCartItem(Long id, CartItem item) throws CartItemException, UserException;

    Optional<CartItem> isCartItemExist(Cart cart, Product product, String size, Long userId);

    void removeCartItem(Long userId, Long cartItemId) throws CartItemException, UserException;

    CartItem findCartItemById(Long cartItemId) throws CartItemException;

}
