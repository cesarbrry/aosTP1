package com.aos.aosTP1.orderService.service;

import com.aos.aosTP1.orderService.model.Order;
import com.aos.aosTP1.orderService.repository.OrderRepository;
import com.aos.aosTP1.userService.model.User;
import com.aos.aosTP1.userService.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service

public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order createOrder(Long userId, String product, int quantity) {

        if (quantity<=0)
        {
            throw new IllegalArgumentException("Quantity is not valid");
        }

        if (!userRepository.existsById(userId))
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        Order order = new Order(userId,product,quantity);
        return orderRepository.save(order);
    }

    public List<Order> getAllOrders()
    {
        List<Order> allOrders = orderRepository.findAll();

        if (allOrders.isEmpty())
        {
            throw new IllegalArgumentException("Order table is empty");
        }

        else
        {
         return allOrders;
        }

    }

    public Optional<Order> getOrderById(Long orderId)
    {
        Optional<Order> orderById = orderRepository.findById(orderId);

        if (orderById.isPresent())
        {
            return orderById;
        }

        else
        {
            throw new RuntimeException("No Order with this Id");
        }

    }

    public List<Order> getOrdersByUserId(Long userId) {
        List<Order> orders = orderRepository.findAllByUserId(userId);
        if (orders == null || orders.isEmpty()) {
            throw new IllegalArgumentException("This User has no orders.");
        }
        return orders;
    }

}