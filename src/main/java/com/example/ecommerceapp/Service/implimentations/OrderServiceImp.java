package com.example.ecommerceapp.Service.implimentations;

import com.example.ecommerceapp.Entity.Address;
import com.example.ecommerceapp.Entity.Order;
import com.example.ecommerceapp.Entity.User;
import com.example.ecommerceapp.Exceptions.OrderException;
import com.example.ecommerceapp.Repository.CartRepository;
import com.example.ecommerceapp.Repository.OrderRepository;
import com.example.ecommerceapp.Service.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class OrderServiceImp implements OrderService {

    private CartRepository cartRepository;

    private CartItemServiceImp cartItemService;
    private OrderRepository orderRepository;

    public OrderServiceImp(CartRepository cartRepository, CartItemServiceImp cartItemService, OrderRepository orderRepository) {
        this.cartRepository = cartRepository;
        this.cartItemService = cartItemService;
        this.orderRepository = orderRepository;
    }

    @Override
    public Order createOrder(User user, Address address) {

        return null;
    }

    @Override
    public Order findOrderById(Long orderId) throws OrderException {
        return null;
    }

    @Override
    public List<Order> userOrderHistory(Long orderId) throws OrderException {
        return null;
    }

    @Override
    public Order placedOrder(Long orderId) throws OrderException {
        return null;
    }

    @Override
    public Order ConfirmedOrder(Long orderId) throws OrderException {
        return null;
    }

    @Override
    public Order shippedOrder(Long orderId) throws OrderException {
        return null;
    }

    @Override
    public Order deliveredOrder(Long orderId) throws OrderException {
        return null;
    }

    @Override
    public Order canceledOrder(Long orderId) throws OrderException {
        return null;
    }
}
