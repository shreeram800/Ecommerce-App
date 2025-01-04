package com.example.ecommerceapp.Service.implimentations;

import com.example.ecommerceapp.Entity.Cart;
import com.example.ecommerceapp.Entity.CartItem;
import com.example.ecommerceapp.Entity.Product;
import com.example.ecommerceapp.Entity.User;
import com.example.ecommerceapp.Exceptions.ProductNotFoundException;
import com.example.ecommerceapp.Repository.CartRepository;
import com.example.ecommerceapp.Service.CartItemService;
import com.example.ecommerceapp.Service.CartService;
import com.example.ecommerceapp.Service.ProductService;
import com.example.ecommerceapp.requests.AddItemRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class CartServiceImp implements CartService {

    private CartRepository cartRepository;
    private CartItemService cartItemService;
    private ProductService productService;
    public CartServiceImp(CartRepository cartRepository, CartItemService cartItemService, ProductService productService) {
        this.cartRepository = cartRepository;
        this.cartItemService = cartItemService;
        this.productService = productService;
    }

    @Override
    public Cart createCart(User user) {
        Cart cart= new Cart();
        cart.setUser(user);

        return cartRepository.save(cart);
    }

    @Override
    public String addCartItem(Long userId, AddItemRequest request) throws ProductNotFoundException {
        Cart cart= cartRepository.findByUserId(userId);
        Product product= productService.getProductById(request.getProductId());

        Optional<CartItem> isPresent= cartItemService.isCartItemExist(cart, product, request.getSize(), userId);

        if(isPresent.isEmpty()){
            CartItem cartItem= new CartItem();
            cartItem.setCart(cart);
            cartItem.setQuantity(request.getQuantity());
            cartItem.setUserId(userId);

            BigDecimal price=product.getDiscountedPrice().multiply(BigDecimal.valueOf((long) request.getQuantity()));

            cartItem.setPrice(price);

            CartItem createdCartItem=cartItemService.createCartItem(cartItem);
            cart.getItemsSet().add(createdCartItem);

        }
        return "Item Add to Cart";
    }

    @Override
    public Cart findUserCart(Long userId) {

        Cart cart = cartRepository.findByUserId(userId);
        BigDecimal totalPrice=BigDecimal.ZERO;
        BigDecimal totalDiscountedPrice=BigDecimal.ZERO;
        int totalItem=0;

        for(CartItem cartItem : cart.getItemsSet()){
            totalPrice= totalPrice.add(cartItem.getPrice());
            totalDiscountedPrice = totalDiscountedPrice.add(cartItem.getDiscountedPrice());
            totalItem+=cartItem.getQuantity();

        }

        cart.setTotalDiscountedPrice(totalDiscountedPrice.doubleValue());
        cart.setTotalItem(totalItem);
        cart.setTotalPrice(totalPrice.doubleValue());
        cart.setDiscount(totalPrice.subtract(totalDiscountedPrice).doubleValue());


        return cartRepository.save(cart);
    }
}
