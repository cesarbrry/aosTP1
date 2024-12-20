package com.aos.aosTP1.orderService.controller;

import com.aos.aosTP1.orderService.model.Order;
import com.aos.aosTP1.orderService.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    //get all orders
    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }

    //get via order ID
    @GetMapping("/orders/{orderId}")
    public ResponseEntity<Optional<Order>> getOrderById(@RequestBody Long id) {
        Optional<Order> order = orderService.getOrderById(id);
        return ResponseEntity.ok(order);
    }


    //get via user ID
    @GetMapping("/orders/{userId}")
    public ResponseEntity<List<Order>> getOrdersByUserId(@RequestBody Long userId) {
        List<Order> orders = orderService.getOrdersByUserId(userId);
        return ResponseEntity.ok(orders);
    }

}