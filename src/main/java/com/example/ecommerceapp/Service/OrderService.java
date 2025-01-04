package com.example.ecommerceapp.Service;

import com.example.ecommerceapp.Entity.Address;
import com.example.ecommerceapp.Entity.Order;
import com.example.ecommerceapp.Entity.User;
import com.example.ecommerceapp.Exceptions.OrderException;
import org.aspectj.weaver.ast.Or;

import java.util.List;



public interface OrderService {

    Order createOrder(User user, Address address);

    Order findOrderById(Long orderId) throws OrderException;

    List<Order> userOrderHistory(Long orderId) throws OrderException;

    Order placedOrder(Long orderId ) throws OrderException;

    Order ConfirmedOrder(Long orderId) throws  OrderException;

    Order shippedOrder(Long orderId) throws OrderException;

    Order deliveredOrder(Long orderId) throws OrderException;

    Order canceledOrder(Long orderId ) throws OrderException;


}
