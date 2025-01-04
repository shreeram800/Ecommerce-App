package com.example.ecommerceapp.Service.implimentations;


import com.example.ecommerceapp.Entity.Cart;
import com.example.ecommerceapp.Entity.CartItem;
import com.example.ecommerceapp.Entity.Product;
import com.example.ecommerceapp.Entity.User;
import com.example.ecommerceapp.Exceptions.CartItemException;
import com.example.ecommerceapp.Exceptions.UserException;
import com.example.ecommerceapp.Repository.CartItemRepository;
import com.example.ecommerceapp.Repository.CartRepository;
import com.example.ecommerceapp.Service.CartItemService;
import com.example.ecommerceapp.Service.UserService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class CartItemServiceImp implements CartItemService {

    private CartItemRepository cartItemRepository;

    private UserService userService;

    private CartRepository cartRepository;

    public CartItemServiceImp(CartItemRepository cartItemRepository, UserService userService, CartRepository cartRepository) {
        this.cartItemRepository = cartItemRepository;
        this.userService=userService;
        this.cartRepository= cartRepository;
    }

    @Override
    public CartItem createCartItem(CartItem item) {

        item.setQuantity(1);
        item.setPrice(item.getProduct().getPrice().multiply(BigDecimal.valueOf(item.getQuantity())));
        item.setDiscountedPrice(item.getProduct().getDiscountedPrice().multiply(BigDecimal.valueOf((long) item.getQuantity())));

         return cartItemRepository.save(item);

    }

    @Override
    public CartItem updateCartItem(Long id, CartItem item) throws CartItemException, UserException {
        CartItem item1= findCartItemById(id);
        User user= userService.getCustomerById(item1.getUserId());

        if(user.getId().equals(id)){
            item1.setQuantity(item.getQuantity());
            item1.setPrice(item1.getProduct().getPrice().multiply(BigDecimal.valueOf((long) item.getQuantity())));
            item1.setDiscountedPrice(item1.getProduct().getDiscountedPrice());

        }

        return cartItemRepository.save(item1);
    }

    @Override
    public Optional<CartItem> isCartItemExist(Cart cart, Product product, String size, Long userId) {

        return cartItemRepository.isCartItemExist(cart, product, size, userId);
    }

    @Override
    public void removeCartItem(Long userId, Long cartItemId) throws CartItemException, UserException {
        CartItem cartItem= findCartItemById(cartItemId);

        User user = userService.getCustomerById(cartItem.getUserId());

        User reqUser= userService.getCustomerById(userId);

        if(user.getId().equals(reqUser.getId())){
            cartItemRepository.deleteById(cartItemId);
        }
        else{
            throw new UserException("you can't remove another users item");
        }

    }

    @Override
    public CartItem findCartItemById(Long cartItemId) throws CartItemException {
        Optional<CartItem> opt= cartItemRepository.findById(cartItemId);

        if(opt.isPresent()){
            return opt.get();
        }
        throw new CartItemException("cart item doesn't exist with id: "+cartItemId);
    }


}
